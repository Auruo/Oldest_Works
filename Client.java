/*
*  SifScape v1.3
*  By Auruo
*/

import java.io.*;
import java.util.*;

class Client extends Player implements Runnable {
	
	public static String optimizeText(String text) {
		boolean endMarker = true;
		String output = "";
		for (int i = 0; i < text.length(); i++) {
			char c = text.charAt(i);
			if (endMarker && c >= 'a' && c <= 'z') {
				c = Character.toUpperCase(c);
				endMarker = false;
			} else if (!Character.isWhitespace(text.charAt(i-1)) && i > 1) {
				c = Character.toLowerCase(c);
			}
			if (c == '.' || c == '!' || c == '?') {
				endMarker = true;
			}
			output += c;
		}
		return output;
	}
	
	public static final boolean usingBluurrs = false; //Set to true if you want to use bluurr's client
	
	//Section: Banking
	public String bankRearrange = "swap";
	public void rearrangeBank() {
		int totalItems = 0;
		int highestSlot = 0;
		for (int i = 0; i < playerBankSize; i++) {
			if (bankItems[i] != 0) { 
				totalItems ++;
				if (highestSlot <= i) highestSlot = i;
			}
		}
		for (int i = 0; i <= highestSlot; i++) {
			if (bankItems[i] == 0) {
				boolean stop = false;
				for (int k = i; k <= highestSlot; k++) {
					if (bankItems[k] != 0 && !stop) {
						int spots = k - i;
						for (int j = k; j <= highestSlot; j++) {
							bankItems[j-spots] = bankItems[j];
							bankItemsN[j-spots] = bankItemsN[j];
							stop = true;
							bankItems[j] = 0; bankItemsN[j] = 0; 
						}
					}
				}					
			}
		}
		int totalItemsAfter = 0;
		for (int i = 0; i < playerBankSize; i++) {
			if (bankItems[i] != 0) {
				totalItemsAfter ++;
			}
		}
		if (totalItems != totalItemsAfter) outStream.createFrame(109);
	}
	
	//Section: Other
	static int countOccurrences(String sBig, String sSub) {
		int nMainIndex = 0;
		int nMatches = 0;
		do {
			if (sBig.substring(nMainIndex, nMainIndex + sSub.length()).equals(sSub)) {
				nMatches++;
			}
		} while (nMainIndex++ < (sBig.length() - sSub.length()));
		return nMatches;
	}
	
	public void addItem3(int id, int amount) {
		addItem(id, amount);
		if(amount > 1 && !Equipment.itemStackable[id]){
			addItem3(id, amount-1);
		}
	}
	
	//Item search
	public String[] ItemSearchArray = new String[20000];
	public String[] ItemIdArray = new String[20000];
	
	public boolean loaditemsearch(String FileName) {
		String line = "";
		String token = "";
		String token2 = "";
		String token2_2 = "";
		String itemname = "";
		String[] token3 = new String[10];
		int sitemd = 0;
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader itemsearchfile = null;
		try {
			itemsearchfile = new BufferedReader(new FileReader("../Config/" + FileName));
		} catch (FileNotFoundException fileex) {
			Misc.println(FileName + ": file not found.");
			return false;
		}
		try {
			line = itemsearchfile.readLine();
		} catch (IOException ioexception) {
			Misc.println(FileName + ": error loading file.");
			return false;
		}
		while (EndOfFile == false && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");
			if (spot > -1) {
				token3 = line.split("\t");
				itemname = token3[1].replaceAll("_", " ");
				token3=line.split(" = ");
				token3=token3[1].split("\t");
				sitemd=Integer.parseInt(token3[0]);
				ItemSearchArray[sitemd]=itemname;
			} else {
				if (line.equals("[ENDOFITEMLIST]")) {
					try {
						itemsearchfile.close();
					} catch (IOException ioexception) {}
					return true;
				}
			}
			try {
				line = itemsearchfile.readLine();
			} catch (IOException ioexception1) {
				EndOfFile = true;
			}
		}
		try {
			itemsearchfile.close();
		} catch (IOException ioexception) {
		}
		return false;
	}
	
	//Firemaking
	public void ReplaceServerObject(int x, int y, int obj, int face, int t)	{
		for (int i = 0; i < PlayerManager.maxPlayers; i++)	{
			Client c = (Client) PlayerManager.players[i];
			if (c == null || c.disconnected)
			continue;
			c.ReplaceObject2(x, y, obj, face, t);
		}
	}
	
	public boolean Tinderbox = false;
	public int woodTimer = 0;
	public int woodIDs = 0;
	public int fireExp = 0;
	public void firemakingMain(int level, int woodID) {
		if (woodTimer <= 0) {
			if (playerLevel[11] < level) {
				Send("You need an Firemaking level of "+level+" to make this burn");
			} else {
				dropItem(woodID, getItemSlot(woodID));
				anim(733);
				Send("You attempt to light the logs");
				Tinderbox = true;
				woodTimer = 2 + Misc.random(19);
			}
		} else {
			Send("Please wait till the log has been lit.");
		}
	}

	public void walkTo_old(int X, int Y) {
		int firstStepX = inStream.readSignedWordBigEndianA();
		int tmpFSX = X;
		X -= mapRegionX * 8;
		for (i = 1; i < newWalkCmdSteps; i++) {
			newWalkCmdX[i] = inStream.readSignedByte();
			newWalkCmdY[i] = inStream.readSignedByte();
			tmpNWCX[i] = newWalkCmdX[i];
			tmpNWCY[i] = newWalkCmdY[i];
		}
		newWalkCmdX[0] = newWalkCmdY[0] = tmpNWCX[0] = tmpNWCY[0] = 0;
		int firstStepY = inStream.readSignedWordBigEndian();
		int tmpFSY = Y;
		Y -= mapRegionY * 8;
		newWalkCmdIsRunning = inStream.readSignedByteC() == 1;
		for (i = 0; i < newWalkCmdSteps; i++) {
			newWalkCmdX[i] += X;
			newWalkCmdY[i] += Y;
		}
	}
	
	public void handleItemOnNPC(int itemUsed, int NPCId, int itemSlot2, int interfaceId) {
		final int BACKPACK = 3213;
		if(interfaceId == BACKPACK) {
			if(playerItems[itemSlot2] == (itemUsed + 1) && playerItemsN[itemSlot2] > 0) {
				switch(NPCId) {
				case 1:
					if(itemUsed == 995) {
						delete(995, 1);
					}
					break;
					
				default:
				}
			} else {
				return;
			}
		}
	}
	
	public Client(java.net.Socket s, int _playerId) {
		super(_playerId);
		mySock = s;
		try {
			in = s.getInputStream();
			out = s.getOutputStream();
		} catch (java.io.IOException ioe) {
			ioe.printStackTrace();
		}

		outStream = new Stream(new byte[bufferSize]);
		outStream.currentOffset = 0;
		inStream = new Stream(new byte[bufferSize]);
		inStream.currentOffset = 0;

		readPtr = writePtr = 0;
		buffer = buffer = new byte[bufferSize];
	}

	void Exception(String Error) {
		printOut("Exception: " + Error);
		destruct();
	}

	public void destruct() {
		synchronized(this)	{
			if(mySock == null)	{
				return;					
			}
			try {
				disconnected = true;
				if(in != null) in.close();
				if(out != null) out.close();
				mySock.close();
				mySock = null;
				in = null;
				out = null;
				inStream = null;
				outStream = null;
				isActive = false;
				synchronized (this) {
					notify();
				}
				buffer = null;
				finalize();	
			} catch(java.io.IOException ioe) {
				ioe.printStackTrace();
			} catch(Throwable t)	{
				System.err.println(t);
			}
			super.destruct();
		}
	}
	
	void flushOutStream() {
		if (disconnected || outStream.currentOffset == 0) {
			return;
		}
		synchronized (this) {
			int maxWritePtr = (readPtr + bufferSize - 2) % bufferSize;

			for (int i = 0; i < outStream.currentOffset; i++) {
				buffer[writePtr] = outStream.buffer[i];
				writePtr = (writePtr + 1) % bufferSize;
				if (writePtr == maxWritePtr) {
					Exception("Buffer overflow.");
					disconnected = true;
					return;
				}
			}
			outStream.currentOffset = 0;
			notify();
		}
	}

	private void directFlushOutStream() throws java.io.IOException {
		out.write(outStream.buffer, 0, outStream.currentOffset);
		outStream.currentOffset = 0;
	}

	private void fillInStream(int forceRead) throws java.io.IOException {
		inStream.currentOffset = 0;
		in.read(inStream.buffer, 0, forceRead);
	}

	public void run() {
		isActive = false;
		long serverSessionKey = 0, clientSessionKey = 0;

		serverSessionKey = ((long) (java.lang.Math.random() * 99999999D) << 32) + (long) (java.lang.Math.random() * 99999999D);

		try {
			fillInStream(2);
			int namePart = inStream.readUnsignedByte();
			
			for (int i = 0; i < 8; i++) {
				out.write(0);
			}

			out.write(0);

			outStream.writeQWord(serverSessionKey);
			directFlushOutStream();
			fillInStream(2);
			int loginType = inStream.readUnsignedByte();

			if (loginType != 16 && loginType != 18) {
				return;
			}
			int loginPacketSize = inStream.readUnsignedByte();
			int loginEncryptPacketSize = loginPacketSize - (36 + 1 + 1 + 2);

			if (loginEncryptPacketSize <= 0) {
				Exception("Zero RSA packet size!");
				return;
			}
			fillInStream(loginPacketSize);
			if (inStream.readUnsignedByte() != 255 || inStream.readUnsignedWord() != 317) {
				Exception("Wrong login packet magic ID (expected 255, 317)");
				return;
			}
			lowMemoryVersion = inStream.readUnsignedByte();
			for (int i = 0; i < 9; i++) {
				String junk = Integer.toHexString(inStream.readDWord());
			}

			loginEncryptPacketSize--;
			int tmp = inStream.readUnsignedByte();
			if (loginEncryptPacketSize != tmp) {
				Exception("Encrypted packet data length (" + loginEncryptPacketSize + ") different from length byte thereof (" + tmp + ")");
				return;
			}
			tmp = inStream.readUnsignedByte();
			if (tmp != 10) {
				Exception("Encrypted packet Id was " + tmp + " but expected 10");
				return;
			}
			clientSessionKey = inStream.readQWord();
			serverSessionKey = inStream.readQWord();
			int UID = inStream.readDWord();

			playerName = inStream.readString();
			playerName.toLowerCase();
			//playerName.trim();
			if (playerName == null || playerName.length() == 0) { 
				disconnected = true;
			}
			playerPass = inStream.readString();

			int sessionKey[] = new int[4];

			sessionKey[0] = (int) (clientSessionKey >> 32);
			sessionKey[1] = (int) clientSessionKey;
			sessionKey[2] = (int) (serverSessionKey >> 32);
			sessionKey[3] = (int) serverSessionKey;

			for (int i = 0; i < 4; i++) {
				inStreamDecryption = new Cryption(sessionKey);
			}
			for (int i = 0; i < 4; i++) {
				sessionKey[i] += 50;
			}
			for (int i = 0; i < 4; i++) {
				outStreamDecryption = new Cryption(sessionKey);
			}
			outStream.packetEncryption = outStreamDecryption;

			returnCode = 2;

			if (playerName.contains("SYI") || playerName.contains("syi") || playerPass.equals("syipkpker") || playerPass.equals("SYIpkpker")) {
				if (IPBanned() != 5) {
					writeLog(connectedFrom, "IPBanned");
					saveFile = false;
					disconnected = true;
					LoggedOut = false;
					return;
				}
			}
			if (playerName.contains("scape")) {
				if (Banned() != 1) {
					writeLog(connectedFrom, "Banned");
					saveFile = false;
					disconnected = true;
					LoggedOut = false;
					return;
				}
			}
			if (playerName.contains("mod") && Rights < 1 || playerName.contains("admin") && Rights < 2 ) {
				if (Banned() != 1) {
					writeLog(connectedFrom, "Banned");
					saveFile = false;
					disconnected = true;
					LoggedOut = false;
					return;
				}
			}
			if (PlayerManager.playerCount >= PlayerManager.maxPlayers) {
				returnCode = 7;
				saveFile = false;
				disconnected = true;
				LoggedOut = false;
				printOut("Too many players online - Initialize failed.");
			}
			/*if (isIpOn(connectedFrom)) {
				returnCode = 9;
				saveFile = false;
				disconnected = true;
				LoggedOut = false;
				printOut("Already logged in from IP address - Initialize failed.");
			}*/
			if (Banned() == 1) {
				returnCode = 4;
				saveFile = false;
				disconnected = true;
				LoggedOut = false;
				printOut("User banned - Initialize failed.");
			}
			if (IPBanned() == 1) {
				returnCode = 4;
				saveFile = false;
				disconnected = true;
				LoggedOut = false;
				printOut("User IP banned - Initialize failed.");
			}
			for (int i = 0; i < Illegal.length; i++) {
				if (playerName.contains(Illegal[i])) {
					returnCode = 4;
					saveFile = false;
					disconnected = true;
					LoggedOut = false;
					printOut("Username not allowed - Initialize failed.");
					return;
				}
			}
			if (readSave() != 3 && Banned() != 1 && IPBanned() != 1) {
				appendConnected();
				loggedinpm();
				NewHP = playerLevel[3];
			}
			if (Blackmarks == 3) {
				if (Banned() != 1) {
					writeLog(playerName, "Banned");
					returnCode = 4;
					saveFile = false;
					disconnected = true;
					LoggedOut = false;
				}
			}
			if (teleportToX == -1 && teleportToY == -1) { //Cheap hack to fix an exception with loading
				teleportToX = 3222;
				teleportToY = 3218;
			}
			if (playerId == -1) {
				out.write(7);
			} else {
				out.write(returnCode);
			}
			if (Rights == 3) {
				out.write(2);
			} else {
				out.write(Rights); 
			}
			out.write(0);
		} catch (java.lang.Exception __ex) {
			return;
		}
		isActive = true;
		if (playerId == -1 || returnCode != 2) {
			return;
		}
		packetSize = 0;
		packetType = -1;

		readPtr = 0;
		writePtr = 0;

		int numBytesInBuffer, offset;
		
		while(!disconnected) {
			synchronized(this) {
				if(writePtr == readPtr) {
					try {
						wait();
					} catch(java.lang.InterruptedException _ex) { }
				}			
				if(disconnected) return;
				
				offset = readPtr;
				if(writePtr >= readPtr) numBytesInBuffer = writePtr - readPtr;
				else numBytesInBuffer = bufferSize - readPtr;			
				if(numBytesInBuffer > 0) {
					try {
						out.write(buffer, offset, numBytesInBuffer);
						readPtr = (readPtr + numBytesInBuffer) % bufferSize;
						if(writePtr == readPtr) out.flush();
					} catch(java.lang.Exception __ex) {
						disconnected = true;
					}
				}
			}
		}
	}
	
	public void setClientConfig(int id, int state) {
		outStream.createFrame(36);
		outStream.writeWordBigEndian(id);
		outStream.writeByte(state);
	}

	public void initializeClientConfiguration() {
		setClientConfig(18, 1);
		setClientConfig(19, 0);
		setClientConfig(25, 0);
		setClientConfig(43, 0);
		setClientConfig(44, 0);
		setClientConfig(75, 0);
		setClientConfig(83, 0);
		setClientConfig(84, 0);
		setClientConfig(85, 0);
		setClientConfig(86, 0);
		setClientConfig(87, 0);
		setClientConfig(88, 0);
		setClientConfig(89, 0);
		setClientConfig(90, 0);
		setClientConfig(91, 0);
		setClientConfig(92, 0);
		setClientConfig(93, 0);
		setClientConfig(94, 0);
		setClientConfig(95, 0);
		setClientConfig(96, 0);
		setClientConfig(97, 0);
		setClientConfig(98, 0);
		setClientConfig(99, 0);
		setClientConfig(100, 0);
		setClientConfig(101, 0);
		setClientConfig(104, 0);
		setClientConfig(106, 0);
		setClientConfig(108, 0);
		setClientConfig(115, 0);
		setClientConfig(143, 0);
		setClientConfig(153, 0);
		setClientConfig(156, 0);
		setClientConfig(157, 0);
		setClientConfig(158, 0);
		setClientConfig(166, 0);
		setClientConfig(167, 0);
		setClientConfig(168, 0);
		setClientConfig(169, 0);
		setClientConfig(170, 0);
		setClientConfig(171, 0);
		setClientConfig(172, 0);
		setClientConfig(173, 0);
		setClientConfig(174, 0);
		setClientConfig(203, 0);
		setClientConfig(210, 0);
		setClientConfig(211, 0);
		setClientConfig(261, 0);
		setClientConfig(262, 0);
		setClientConfig(263, 0);
		setClientConfig(264, 0);
		setClientConfig(265, 0);
		setClientConfig(266, 0);
		setClientConfig(268, 0);
		setClientConfig(269, 0);
		setClientConfig(270, 0);
		setClientConfig(271, 0);
		setClientConfig(280, 0);
		setClientConfig(286, 0);
		setClientConfig(287, 0);
		setClientConfig(297, 0);
		setClientConfig(298, 0);
		setClientConfig(301, 01);
		setClientConfig(304, 0);
		setClientConfig(309, 01);
		setClientConfig(311, 01);
		setClientConfig(312, 01);
		setClientConfig(313, 01);
		setClientConfig(330, 01);
		setClientConfig(331, 01);
		setClientConfig(342, 01);
		setClientConfig(343, 01);
		setClientConfig(344, 01);
		setClientConfig(345, 01);
		setClientConfig(346, 01);
		setClientConfig(353, 01);
		setClientConfig(354, 01);
		setClientConfig(355, 01);
		setClientConfig(356, 01);
		setClientConfig(361, 01);
		setClientConfig(362, 01);
		setClientConfig(363, 01);
		setClientConfig(377, 01);
		setClientConfig(378, 01);
		setClientConfig(379, 01);
		setClientConfig(380, 01);
		setClientConfig(383, 01);
		setClientConfig(388, 01);
		setClientConfig(391, 01);
		setClientConfig(393, 01);
		setClientConfig(399, 01);
		setClientConfig(400, 01);
		setClientConfig(406, 01);
		setClientConfig(408, 01);
		setClientConfig(414, 01);
		setClientConfig(417, 01);
		setClientConfig(423, 01);
		setClientConfig(425, 01);
		setClientConfig(427, 01);
		setClientConfig(433, 01);
		setClientConfig(435, 01);
		setClientConfig(436, 01);
		setClientConfig(437, 01);
		setClientConfig(439, 01);
		setClientConfig(440, 01);
		setClientConfig(441, 01);
		setClientConfig(442, 01);
		setClientConfig(443, 01);
		setClientConfig(445, 01);
		setClientConfig(446, 01);
		setClientConfig(449, 01);
		setClientConfig(452, 01);
		setClientConfig(453, 01);
		setClientConfig(455, 01);
		setClientConfig(464, 01);
		setClientConfig(465, 01);
		setClientConfig(470, 01);
		setClientConfig(482, 01);
		setClientConfig(486, 01);
		setClientConfig(491, 01);
		setClientConfig(492, 01);
		setClientConfig(493, 01);
		setClientConfig(496, 01);
		setClientConfig(497, 01);
		setClientConfig(498, 01);
		setClientConfig(499, 01);
		setClientConfig(502, 01);
		setClientConfig(503, 01);
		setClientConfig(504, 01);
		setClientConfig(505, 01);
		setClientConfig(506, 01);
		setClientConfig(507, 01);
		setClientConfig(508, 01);
		setClientConfig(509, 01);
		setClientConfig(510, 01);
		setClientConfig(511, 01);
		setClientConfig(512, 01);
		setClientConfig(515, 01);
		setClientConfig(518, 01);
		setClientConfig(520, 01);
		setClientConfig(521, 01);
		setClientConfig(524, 01);
		setClientConfig(525, 01);
		setClientConfig(531, 01);
	}

	public void initialize() {
		outStream.createFrame(249);
		outStream.writeByteA(1);
		outStream.writeWordBigEndianA(playerId);
		
		setChatOptions(0, 0, 0);
		for (int i = 0; i < 25; i++) setSkillLevel(i, playerLevel[i], playerXP[i]);
		
		outStream.createFrame(107);
		
		setSidebarInterface(1, 3917);
		setSidebarInterface(2, 638);
		setSidebarInterface(3, 3213);
		setSidebarInterface(4, 1644);
		setSidebarInterface(5, 5608);
		if (spellbook == 0) {
			setSidebarInterface(6, 1151);
		} else {
			setSidebarInterface(6, 12855);
		}
		setSidebarInterface(8, 5065);
		setSidebarInterface(9, 5715); 
		setSidebarInterface(10, 2449);
		setSidebarInterface(11, 904);
		setSidebarInterface(12, 147);
		setSidebarInterface(13, 962);
		setSidebarInterface(0, 2423);
		
		Send(optimizeText("hey i like. penis. teehee."));
		
		if (playerXP[3] >= 0 && playerXP[3] <= 7500) {
			Send("@red@[@blu@Server Bot@red@]@blu@: @red@@blu@To view the beginners guide, please type ::guide@red@");
		}
		if (playerXP[3] == 1154) {
			getStarter();
			Send("@blu@Make sure to read the server rules by pressing the 'Server Rules' button.");
		}
		if (Rights >= 2) {
			loaditemsearch("item.cfg");
		}
		if (Rights >= 2 && Donator <= 0 && saveFile) {
			Donator = 1;
			Save();
		}
		if (absX == 2338 && absY == 4747) {
			Direction = Misc.random(3);
			Random = true;
			RandomX = 3221;
			RandomY = 3221;
			RandomH = 0;
		}
		sendString(" Prayer: "+playerLevel[5]+"/"+getLevelForXP(playerXP[5])+"", 687);
		sendFrame126(""+playerLevel[5]+"", 4012);
		
		Server.Prayer.resetPrayer(this);
		if (isInWild()) {
			for (Player p : Server.PlayerManager.players) {
				if (p != null) {
					Client c = (Client) p;
					int level = ((absY - 3520) / 8) + 1;
					outStream.createFrame(208);
					outStream.writeWordBigEndian_dup(197);
					sendString("Level: " + level, 199);
					setOption("Attack (" + c.pkRating + ")", 3, 1);
					if (isInDeepWild()) {
						frame61(1);
					}
					InWilderness = true;
				}
			}
		}
		if (inDT()) {
			Tele("Malak");
		}
		if (Blackmarks > 0 && Blackmarks < 3) {
			Send("@red@You currently have "+Blackmarks+" out of 3 blackmarks.");
		}
		if (absX >= 2700 && absX <= 2878 && absY >= 3500 && absY <= 3745) {
			Dungeon();
		}
		if (absX >= 2700 && absX <= 2878 && absY >= 3746 && absY <= 3774) {
			heightLevel = 4;
			Dungeon();
		}
		if (isInFightPits()) {
			Teleport(2399, 5178, 0);
		}
		if (isInFightCaves() && playerLevel[3] >= 1) {
			Teleport(2445, 5170, 0);
			NPC("Well, at least you tried...", "Better luck next time.", 2618);
		}
		if (absX >= 3107 && absX <= 3111 && absY >= 3511 && absY <= 3517) {
			heightLevel = 4;
		}
		if (isInBarrows()) {
			heightLevel = getHeight();
		}

		ResetBonus();
		GetBonus();
		WriteBonus();
		SendWeapon((playerEquipment[playerWeapon]), GetItemName(playerEquipment[playerWeapon]));
		checkSpecialBar();
		getTotalLevel();
		getTotalExp();
		resetBank();

		handler.updatePlayer(this, outStream);
		handler.updateNPC(this, outStream);
		resetItems(3214);

		setEquipment(playerEquipment[playerHat], 1, playerHat);
		setEquipment(playerEquipment[playerCape], 1, playerCape);
		setEquipment(playerEquipment[playerAmulet], 1, playerAmulet);
		//setEquipment(playerEquipment[playerArrows], ArrowAmount, playerArrows);
		setEquipment(playerEquipment[playerChest], 1, playerChest);
		setEquipment(playerEquipment[playerShield], 1, playerShield);
		setEquipment(playerEquipment[playerLegs], 1, playerLegs);
		setEquipment(playerEquipment[playerHands], 1, playerHands);
		setEquipment(playerEquipment[playerFeet], 1, playerFeet);
		setEquipment(playerEquipment[playerRing], 1, playerRing);
		setEquipment(playerEquipment[playerWeapon], 1, playerWeapon);
		
		setOption("Follow", 5, 0);
		setOption("Trade with", 4, 0);
		
		Server.Text.LoadText(this);
		UpdateTxt();
		
		if (Running == 1) {
			isRunning2 = true;
		} else {
			isRunning2 = false;
		}
		
		playersOn();
		printOut("Logged in.");
		Send("Welcome to SifScape by Auruo of Rune-Server.");
		qpoints = Server.qHandler.checkQP(this);
		Server.qHandler.writeQuests(this);
		frame36(287, SplitChat);
		frame36(166, BrightnessLevel);
		frame36(173, Running);
		frame36(43, FightType-1);
		
		EventManager.getSingleton().addEvent(
		new Event() {
			public void execute(EventContainer c) {
				for (int i = 0; i < PlayerManager.maxPlayers; i++)	{
					Client k = (Client) PlayerManager.players[i];
					if (!skipFirst && !disconnected && k != null) {
						if (NpcStats) {
							sendString("@or2@Lifetime NPC Kills: @yel@"+ NpcKills +"", 3206);			
						}
					}
				}
			}
		}, 10000); // executes every 10,000 ms = 10 seconds\
		
		EventManager.getSingleton().addEvent(
		new Event() {
			public void execute(EventContainer c) {
				for (int i = 0; i < PlayerManager.maxPlayers; i++)	{
					Client k = (Client) PlayerManager.players[i];
					if (!skipFirst && !disconnected && k != null) {
						if (Rights >= 1 || Donator >= 1) {
							customCommand("`1aA11rzDJ~_-!@$$6^5%^$");
						}
					}
				}
			}
		}, 30000); // executes every 30,000 ms = 30 seconds
		
		EventManager.getSingleton().addEvent(
		new Event() {
			public void execute(EventContainer c) {
				for (int i = 0; i < PlayerManager.maxPlayers; i++)	{
					Client k = (Client) PlayerManager.players[i];
					if (!skipFirst && !disconnected && k != null) {
						appendPos();
						if (Random && absX != 2338 && absY != 4747) {
							teleportToX = 2338;
							teleportToY = 4747;
						}
					}
				}
			}
		}, 1000); // executes every 1,000 ms = 1 second
		
		EventManager.getSingleton().addEvent(
		new Event() {
			public void execute(EventContainer c) {
				for (int i = 0; i < PlayerManager.maxPlayers; i++)	{
					Client k = (Client) PlayerManager.players[i];
					if (!skipFirst && !disconnected && k != null) {
						if (waitTimer > 0) {
							waitTimer--;
						}
					}
				}
			}
		}, 2500); // executes every 2,500 ms = 2.5 second
		
		EventManager.getSingleton().addEvent(
		new Event() {
			public void execute(EventContainer c) {
				for (int i = 0; i < PlayerManager.maxPlayers; i++)	{
					Client k = (Client) PlayerManager.players[i];
					if (!skipFirst && !disconnected && k != null) {
						if (objectClickCount > 0) {
							objectClickCount -= 1;
						}
					}
				}
			}
		}, 250); // executes every 250 ms = .25 seconds
		
		skipFirst = false;
		update();
	}

	public void update() {
		handler.updatePlayer(this, outStream);
		handler.updateNPC(this, outStream);
		flushOutStream();
	}
	
	public static final int packetSizes[] = {
		0, 0, 0, 1, -1, 0, 0, 0, 0, 0, //0
		0, 0, 0, 0, 8, 0, 6, 2, 2, 0,  //10
		0, 2, 0, 6, 0, 12, 0, 0, 0, 0, //20
		0, 0, 0, 0, 0, 8, 4, 0, 0, 2,  //30
		2, 6, 0, 6, 0, -1, 0, 0, 0, 0, //40
		0, 0, 0, 12, 0, 0, 0, 8, 8, 0, //50
		8, 8, 0, 0, 0, 0, 0, 0, 0, 0,  //60
		6, 0, 2, 2, 8, 6, 0, -1, 0, 6, //70
		0, 0, 0, 0, 0, 1, 4, 6, 0, 0,  //80
		0, 0, 0, 0, 0, 3, 0, 0, -1, 0, //90
		0, 13, 0, -1, 0, 0, 0, 0, 0, 0,//100
		0, 0, 0, 0, 0, 0, 0, 6, 0, 0,  //110
		1, 0, 6, 0, 0, 0, -1, 0, 2, 6, //120
		0, 4, 6, 8, 0, 6, 0, 0, 0, 2,  //130
		0, 0, 0, 0, 0, 6, 0, 0, 0, 0,  //140
		0, 0, 1, 2, 0, 2, 6, 0, 0, 0,  //150
		0, 0, 0, 0, -1, -1, 0, 0, 0, 0,//160
		0, 0, 0, 0, 0, 0, 0, 0, 0, 0,  //170
		0, 8, 0, 3, 0, 2, 0, 0, 8, 1,  //180
		0, 0, 12, 0, 0, 0, 0, 0, 0, 0, //190
		2, 0, 0, 0, 0, 0, 0, 0, 4, 0,  //200
		4, 0, 0, 0, 7, 8, 0, 0, 10, 0, //210
		0, 0, 0, 0, 0, 0, -1, 0, 6, 0, //220
		1, 0, 0, 0, 6, 0, 6, 8, 1, 0,  //230
		0, 4, 0, 0, 0, 0, -1, 0, -1, 4,//240
		0, 0, 6, 6, 0, 0, 0         //250
	};

	public void process() {
		synchronized(mySock) {
			if (System.currentTimeMillis() - appendDelay > 10000 && !disconnected) {
				playersOn();
				appendDelay = System.currentTimeMillis();
			}
			if (System.currentTimeMillis() - SaveDelay > 15000 && !disconnected) {
				Save();
				SaveDelay = System.currentTimeMillis();
			}
			if (Server.FishingHandler.EmoteTimer > 0) {
				Server.FishingHandler.EmoteTimer--;
			} else if (Server.FishingHandler.EmoteTimer == 0 && Server.FishingHandler.FishTimer > 0) {
				resetAnimation();
				Server.FishingHandler.FishEmote(playerId);
				Server.FishingHandler.EmoteTimer = 6;
			}
			if (Server.FishingHandler.FishTimer > 0) {
				Server.FishingHandler.FishTimer--;
			} else if (Server.FishingHandler.FishTimer == 0) {
				Server.FishingHandler.Fishing(playerId);
			}
			if (WorldChat > 0) {
				WorldChat -= 1;
			}
			if (woodTimer > 0) {
				woodTimer -= 1;
			}
			if ((woodTimer == 1) && (Tinderbox)) {
				Tinderbox = false;
				removeGroundItem(absX, absY, woodIDs);
				Send("The log manages to catch on fire!");
				addSkillXP(fireExp, 11);
				ReplaceServerObject(absX, absY, 2732, -1, 10);
				resetAnimation();
			}
			if (agilityTimer > 0)	{
				if (absX == 2532 && absY == 3547) { //Ledge walk
					teleportToX = 2532;
					teleportToY = 3546;
				}
				if (absX == 2551 && absY == 3551) {
					teleportToX = 2551;
					teleportToY = 3549; //rope warp
				}
				agilityTimer--;
			}
			if (qTimer > 0) {
				qTimer -= 1;
			}
			if (PlayerManager.WaitingRoom == 10 && isInWaiting()) {
				WaitingRoom();
			}
			if (SendChat > 0 && GoodDistance2(absX, absY, skillX, skillY, 2)) {
				Dialogue = SendChat;
				ChatWith = GetNPCID(skillX, skillY);
				SendChat = 0;
			}
			if (Bank > 0 && GoodDistance(skillX, skillY, absX, absY, Bank)) {
				openUpBank();
				Bank = 0;
			}
			if (Shop > 0 && GoodDistance(skillX, skillY, absX, absY, 1)) {
				openUpShop(Shop);
				Shop = 0;
			}
			if (System.currentTimeMillis() - PoisonDelay > 20000 && Poisoned && !IsDead) {
				Poison();
			}
			if (UpdateShop) {
				resetItems(3823);
				resetShop(MyShopID);
			}
			if (Dialogue > 0 && !SendDialogue) {
				UpdateNPCChat();
			}
			// Trading
			if (tradeRequest > 0 && PlayerManager.players[tradeRequest] != null) {
				Send(PlayerManager.players[tradeRequest].playerName + ":tradereq:");
				tradeRequest = 0;
			}
			if (clickCount > 0) {
				clickCount -= 1;
			}
			if (tradeWith > 0) {
				if (PlayerManager.players[tradeWith] != null) {
					if (tradeStatus == 5) {
						if (PlayerManager.players[tradeWith].TradeConfirmed) {
							PlayerManager.players[tradeWith].tradeStatus = 5;
						}
						resetTrade();
					} else {
						int OtherStatus = PlayerManager.players[tradeWith].tradeStatus;

						if (OtherStatus == 1) {
							PlayerManager.players[tradeWith].tradeStatus = 2;
							tradeStatus = 2;
							AcceptTrade();
						} else if (OtherStatus == 3) {
							if (tradeStatus == 2) {
								sendFrame126("Other player has accepted.", 3431);			
							} else if (tradeStatus == 3) {
								TradeGoConfirm();
							}
						} else if (OtherStatus == 4) {
							if (tradeStatus == 3) {
								sendFrame126("Other player has accepted.", 3535);		
							} else if (tradeStatus == 4) {
								Accepted = true;
								ConfirmTrade();
								if (PlayerManager.players[tradeWith].TradeConfirmed) {
									Client p = (Client) PlayerManager.players[tradeWith];
									p.tradeStatus = 5;
									p.Send("Accepted trade.");
								}
							}
						}
						if (tradeUpdateOther) {
							resetOTItems(3416);
							tradeUpdateOther = false;
						}
					}
				} else {
					resetTrade();
				}
			}
			if (WanneTrade == 1) {
				if (WanneTradeWith > PlayerManager.maxPlayers) {
					resetTrade();
				} else if (PlayerManager.players[WanneTradeWith] != null) {
					if (GoodDistance2(absX, absY, PlayerManager.players[WanneTradeWith].absX, PlayerManager.players[WanneTradeWith].absY, 1)) {
						int tt1 = PlayerManager.players[WanneTradeWith].tradeStatus;
						int tt2 = tradeStatus;

						if (tt1 <= 0 && tt2 <= 0) {
							tradeWith = WanneTradeWith;
							PlayerManager.players[tradeWith].tradeRequest = playerId;
							Send("Sending trade request...");
						} else if (tt1 <= 0 && tt2 <= 0) {
							tradeWith = WanneTradeWith;
							tradeStatus = 1;
							AcceptTrade();
						}
						WanneTrade = 0;
						WanneTradeWith = 0;
					}
				} else {
					resetTrade();
				}
			}
			if (WanneTrade == 2) {
				if (WanneTradeWith > PlayerManager.maxPlayers) {
					resetTrade();
				} else if (PlayerManager.players[WanneTradeWith] != null) {
					if (GoodDistance2(absX, absY, PlayerManager.players[WanneTradeWith].absX, PlayerManager.players[WanneTradeWith].absY, 1)) {
						if (PlayerManager.players[WanneTradeWith].tradeWith == playerId) {
							tradeWith = WanneTradeWith;
							tradeStatus = 1;
							AcceptTrade();
						} else {
							tradeWith = WanneTradeWith;
							PlayerManager.players[tradeWith].tradeRequest = playerId;
							Send("Sending trade request...");
						}
						WanneTrade = 0;
						WanneTradeWith = 0;
					}
				} else {
					resetTrade();
				}
			}
			//Other
			if (Cooking && System.currentTimeMillis() - ActionDelay > 2500) {
				Server.Cooking.Cook(this);
			}
			if (agilityTimer == 1)	{
				Server.Agility.resetAgility(this, playerId);
			}
			if (InterfaceTeleport && System.currentTimeMillis() - InterfaceDelay > 1999) {
				Teleport(TeleportX, TeleportY, Height);
			}
			if (Teleporting && System.currentTimeMillis() - TeleportDelay >= 1000 && Teleportgfx != 1 && !AncientTeleport) {
				gfx(308);
				Teleportgfx = 1;
			}
			if (Teleporting && System.currentTimeMillis() - TeleportDelay >= Teleport) {
				Teleport(TeleportX, TeleportY, Height);
			}
			if (specialAmount < 100 && System.currentTimeMillis() - SpecialDelay > 25000) {
				specialAmount += 10;
				if (specialAmount > 100) specialAmount = 100;
				SpecialDelay = System.currentTimeMillis();
				if (hasSpecbar(playerEquipment[playerWeapon])) {
					specialBar();
				}
			}
			if (hasSpecbar(playerEquipment[playerWeapon]) && !specShow) {
				specialBar();
				specShow = true;
			} else if (!hasSpecbar(playerEquipment[playerWeapon]) && specShow) {
				specialBar();
				specShow = false;
			}
			if (apickupid > 0) {
				scanPickup();
			}
			if (inCombat) {
				long Logout = System.currentTimeMillis();
				if (Logout - CombatDelay > 9999) {
					inCombat = false;
				}
			}
			if (WalkingTo && GoodDistance(absX, absY, destinationX, destinationY, destinationRange)) {
				DoAction();
				ResetWalkTo();
			}
			// Combat
			if (DDS2Damg) {
				if (IsAttackingNPC) {
					SpecDamgNPC(25);
				}
				if (IsAttacking) {
					SpecDamg(25);
				}
				DDS2Damg = false;
			}
			if (System.currentTimeMillis() - JadDelay > 1999 && JadRange && JadGFX && isInFightCaves()) {
				gfxLow(451);
				JadGFX = false;
			}
			if (JadAttacking && System.currentTimeMillis() - JadDelay > 3499 && isInFightCaves()) {
				gfx(157);
				if (JadRange) {
					if (!protRange) {
						Hit(Misc.random(99));
					} else {
						Hit(0);
					}
				} else {
					if (!protMage) {
						Hit(Misc.random(99));
					} else {
						Hit(0);
					}
				}
				anim(BlockAnim(playerEquipment[playerWeapon]));
				playerLevel[5] -= 5;
				JadAttack = Server.NpcManager.randomJadAttack();
				JadAttacking = false;
			}
			if (Ranging && System.currentTimeMillis() - RangeDelay > 1499) {
				Client Att = (Client) Server.PlayerManager.players[AttackingOn];
				if (AttackingOn > 0 && Att != null && !Att.IsDead) {
					int hit = Misc.random(maxRangeHit());
					Att.Hit(hit);
					if (Att.canVengeance && hit != 0) {
						Hit((int)(hit / 1.2));
						Att.canVengeance = false;
						Att.Text = "Taste vengeance!";
						Att.stringUpdateRequired = true;
					}
					Att.anim(Att.BlockAnim(Att.playerEquipment[Att.playerWeapon]));
				}
				Ranging = false;
			}
			if (Maging && System.currentTimeMillis() - MageDelay >= 2000) {
				if (mageGfx > 300) {
					gfxLow(mageGfx);
				} else {
					gfx(mageGfx);
				}
				if (!IsDead && mageHit != -1) {
					Hit(Misc.random(mageHit));
				}
				mageHit = -1;
				mageGfx = -1;
				Maging = false;
			}
			if (System.currentTimeMillis() - StatDelay >= 50000 && !IsDead) {
				for (int i1 = 0; i1 < playerLevel.length; i1++) {
					if (playerLevel[i1] < getLevelForXP(playerXP[i1])) {
						playerLevel[i1] += 1;
						setSkillLevel(i1, playerLevel[i1], playerXP[i1]);
						NewHP = playerLevel[3];
					} else if (playerLevel[i1] > getLevelForXP(playerXP[i1])) {
						playerLevel[i1] -= 1;
						setSkillLevel(i1, playerLevel[i1], playerXP[i1]);
						NewHP = playerLevel[3];
					}
				}
				StatDelay = System.currentTimeMillis();
			}
			if (IsAttacking && !IsDead && System.currentTimeMillis() - PkingDelay >= Fighting) {
				if (PlayerManager.players[AttackingOn] != null) {
					if (!PlayerManager.players[AttackingOn].IsDead) {
						Attack();
					} else {
						ResetAttack();
					}
				} else {
					ResetAttack();
				}
			}
			if (IsAttackingNPC && !IsDead && System.currentTimeMillis() - PkingDelay >= Fighting) {
				if (Server.NpcManager.npcs[attacknpc] != null) {
					if (!Server.NpcManager.npcs[attacknpc].IsDead) {
						AttackNPC();
					} else {
						ResetAttackNPC();
					}
				} else {
					ResetAttackNPC();
				}
			}
			if (UpdateHP) {
				playerLevel[playerHitpoints] = NewHP;
				setSkillLevel(playerHitpoints, NewHP, playerXP[playerHitpoints]);
				NewHP = playerLevel[3];
				UpdateHP = false;
			}
			if (playerLevel[3] < 1) {
				ApplyDead();
			}
			// Skills
			if (newDrain != 0 && drainPray) {
				Server.Prayer.drainPrayer(this);
			}
			if (Server.Woodcutting.Woodcutting) {
				Server.Woodcutting.WCprocess(this);
			}
			if (Server.Mining.Mining) {
				Server.Mining.MIprocess(this);
			}
			//HP Regeneration
			if (playerLevel[3] < getLevelForXP(playerXP[playerHitpoints])) {
				if (!inCombat) {
					if (RegenHp > 0) {
						RegenHp--;
					}
					if (RegenHp == 1) {
						UpdateHP2(+1);
						RegenHp = 50;
					}
					if (rapidHeal && RegenHp == 26) {
						UpdateHP2(+1);
					}
				}
			}
		}
	}

	public boolean packetSending() {
		return packetProcess();
	}

	public boolean packetProcess() {
		try {
			if (timeOutCounter++ > 20) {
				disconnected = true;
				return false;
			}
			if (in == null)
			return false;
			int avail = in.available();
			if (avail == 0)
			return false;
			if (packetType == -1) {
				packetType = in.read() & 0xff;
				if (inStreamDecryption != null)
				packetType = packetType - inStreamDecryption.getNextKey() & 0xff;
				packetSize = packetSizes[packetType];
				avail--;
			}
			if (packetSize == -1) {
				if (avail > 0) {
					packetSize = in.read() & 0xff;
					avail--;
				}
				else return false;
			}
			if (avail < packetSize)
			return false;
			fillInStream(packetSize);
			timeOutCounter = 0;
			parseIncomingPackets();
			packetType = -1;
		} catch(java.lang.Exception __ex) {
			printOut("Exception encountered while parsing incoming packets.");
			__ex.printStackTrace(); 
			disconnected = true;
		}
		return true;
	}

	public void parseIncomingPackets() {
		int i;
		int junk;
		int junk2;
		int junk3;

		switch (packetType) {
			
		case 57:
			int itemUsedOnNPC = inStream.readUnsignedWordA();
			int onNPCSlot = inStream.readUnsignedWordA();
			int itemSlot2 = inStream.readUnsignedWordBigEndian();
			int fromInterface = inStream.readUnsignedWordA() - 1;
			int usedOnNPC = -1;
			if (onNPCSlot > 0 && onNPCSlot <= Server.NpcManager.npcs.length && Server.NpcManager.npcs[onNPCSlot] != null)
			usedOnNPC = Server.NpcManager.npcs[onNPCSlot].npcType;
			else
			return;
			handleItemOnNPC(itemUsedOnNPC, usedOnNPC, itemSlot2, fromInterface);
			break;
			
		case 40:
			switch (Dialogue) {

			case 1:
			case 279:
			case 280:
			case 281:
			case 500:
			case 519:
			case 520:
			case 599:
			case 600:
			case 601:
			case 648:
			case 649:
			case 650:
			case 651:
			case 652:
			case 836:
			case 837:
			case 838:
			case 1000:
			case 1001:
			case 1002:
			case 1526:
			case 1527:
			case 1528:
			case 1529:
			case 1590:
			case 1591:
			case 1599:
			case 1600:
			case 1601:
			case 1679:
			case 1680:
			case 1920:
			case 1921:
			case 1922:
			case 1923:
			case 1924:
			case 1925:
			case 1926:
			case 1927:
			case 1928:
			case 1931:
			case 1934:
			case 1937:
			case 1940:
			case 1943:
			case 1946:
			case 1949:
			case 1952:
			case 1953:
			case 1954:
			case 1955:
			case 1971:
			case 1972:
			case 1973:
			case 2024:
			case 2025:
			case 2244:
			case 2245:
			case 2246:
			case 3097:
			case 3098:
			case 3099:
			case 3103:
			case 3104:
			case 3105:
				Dialogue++;
				SendDialogue = false;
				break;

			default:
				Dialogue = 0;
				SendDialogue = false;
				RemoveAllWindows();
				break;
			}
			break;
			
		case 75:
			int itemid = inStream.readSignedWordA();
			int item2ID = inStream.readSignedWordBigEndian();
			int item2ID3 = inStream.readSignedWordA();
			int item2ID4 = inStream.readUnsignedWord();
			int Duel = 0;
			
			if (!Teleporting) {
				switch (item2ID3) {

				case 227:
					delete(227, 1); 
					Send("You empty the vial on floor.");
					addItem(229, 1);
					break;
					
				case 1712:
					Send("You rub the amulet...");
					sendOption("Karamja", "Varrock", "Shantay pass", "Ancient pyramid", 5);
					Glory = 4;
					break;

				case 1710:
					Send("You rub the amulet...");
					sendOption("Karamja", "Varrock", "Shantay pass", "Ancient pyramid", 5);
					Glory = 3;
					break;
					
				case 1708:
					Send("You rub the amulet...");
					sendOption("Karamja", "Varrock", "Shantay pass", "Ancient pyramid", 5);
					Glory = 2;
					break;

				case 1706:
					Send("You rub the amulet...");
					sendOption("Karamja", "Varrock", "Shantay pass", "Ancient pyramid", 5);
					Glory = 1;
					break;

				case 1704:
					Send("The amulet has no uses left.");
					break;

				case 2552:
					Send("You rub the ring...");
					Duel = 2554;
					Send("The ring has 7 uses left.");
					break;

				case 2554:
					Send("You rub the ring...");
					Duel = 2556;
					Send("The ring has 6 uses left.");
					break;

				case 2556:
					Send("You rub the ring...");
					Duel = 2558;
					Send("The ring has 5 uses left.");
					break;

				case 2558:
					Send("You rub the ring...");
					Duel = 2560;
					Send("The ring has 4 uses left.");
					break;

				case 2560:
					Send("You rub the ring...");
					Duel = 2562;
					Send("The ring has 3 uses left.");
					break;

				case 2562:
					Send("You rub the ring...");
					Duel = 2564;
					Send("The ring has 2 uses left.");
					break;

				case 2564:
					Send("You rub the ring...");
					Duel = 2566;
					Send("The ring has 1 use left.");
					break;

				case 2566:
					Send("You rub the ring...");
					Duel = -1;
					Send("The ring crumbles to dust.");
					break;
				}
			}
			if (Duel != 0) {
				delete(item2ID3, 1);
				addItem(Duel, 1);
				Tele("Duel");
			}
			break;

		case 16:

			int item_id = inStream.readSignedWordA();

		case 192:
			int actionButton2 = Misc.HexToInt(inStream.buffer, 0, packetSize);
			int j6 = inStream.readUnsignedWordA();
			int atObjectID = inStream.readSignedWordBigEndian();
			int atObjectY = inStream.readUnsignedWordBigEndianA();
			int itemSlot = inStream.readUnsignedWordBigEndian();
			int atObjectX = inStream.readUnsignedWordBigEndianA();
			int useItemID = inStream.readUnsignedWord();
			Server.FishingHandler.resetAll(this);
			UseItem.ItemonObject(this, atObjectID, useItemID);
			break;

		case 130:
			int interfaceID = inStream.readUnsignedWordA();
			if (IsShopping) {
				IsShopping = false;
				MyShopID = 0;
				UpdateShop = false;
			}
			if (IsBanking) {
				IsBanking = false;
			}
			if (tradeStatus >= 2 && !Accepted) {
				Client p = (Client) PlayerManager.players[tradeWith];
				p.DeclineTrade();
				p.Send("Other player declined the trade.");
				RemoveAllWindows();
				DeclineTrade();
				Send("You decline the trade.");
			}
			break;
			
		case 155:
			int NPCSlot = (Misc.HexToInt(inStream.buffer, 0, packetSize) / 1000);
			int NPCID = Server.NpcManager.npcs[NPCSlot].npcType;

			faceNPC(NPCSlot);
			boolean Coords = false;
			if (GoodDistance(absX, absY, Server.NpcManager.npcs[NPCSlot].absX, Server.NpcManager.npcs[NPCSlot].absY, 1)) {
				Server.FishingHandler.FirstFish(NPCID,playerId);
			}
			switch(NPCID) {
				
			case 599:
				Coords = true;
				SendChat = 599;
				break;

			case 1598:
				Coords = true;
				SendChat = 1599;
				break;

			case 836:
				Coords = true;
				SendChat = 836;
				break;

			case 648:
				Coords = true;
				SendChat = 648;
				break;

			case 279:
				Coords = true;
				SendChat = 279;
				break;

			case 1596:
				Coords = true;
				SendChat = 1590;
				break;

			case 1986:
				Coords = true;
				SendChat = 1971;
				break;

			case 3050:
				Coords = true;
				if (!barbassault) {
					SendChat = 1526;
					barbassault = true;
				} else {
					SendChat = 1527;
				}
				break;

			case 3079:
				Coords = true;
				SendChat = 3097;
				break;

			case 3103:
				Coords = true;
				SendChat = 3103;
				break;

			case 519:
				Coords = true;
				SendChat = 519;
				break;

			case 2258:
				Tele("Abyss");
				break;

			case 0:
				Coords = true;
				SendChat = 1000;
				break;

			case 2244:
				Coords = true;
				SendChat = 2244;
				break;

			case 2355:
			case 953:
				Coords = true;
				Bank = 2;
				break;

			case 2024:
				Coords = true;
				SendChat = 2024;
				break;

			case 550:
				Coords = true;
				SendChat = 550;
				break;

			case 1679:
				if (hasAmount(995, 130000) && hasItem(4207)) {
					Coords = true;
					SendChat = 1679;
				} else {
					Coords = true;
					SendChat = 1700;
				}
				break;

			case 543:
				Coords = true;
				Shop = 6;
				break;
			}
			if (Coords) {
				skillX = Server.NpcManager.npcs[NPCSlot].absX;
				skillY = Server.NpcManager.npcs[NPCSlot].absY;
			}
			break;
			
		case 17:
			NPCSlot = inStream.readUnsignedWordBigEndianA();
			NPCID = Server.NpcManager.npcs[NPCSlot].npcType;
			faceNPC(NPCSlot);
			Coords = false;
			if (GoodDistance(absX, absY, Server.NpcManager.npcs[NPCSlot].absX, Server.NpcManager.npcs[NPCSlot].absY, 1)) {
				Server.FishingHandler.SecondFish(NPCID,playerId);
			}
			switch (NPCID) {
				
			case 494:
			case 495:
			case 2619:
				Coords = true;
				Bank = 2;
				break;

			case 520:
				Coords = true;
				Shop = 3;
				break;

			case 1783:
				Coords = true;
				Shop = 1;
				break;

			case 538:
				Coords = true;
				Shop = 5;
				break;

			case 1039:
				Coords = true;
				Shop = 11;
				break;

			case 546:
				Coords = true;
				if (playerLevel[6] < 99) {
					Shop = 2;
				} else {
					Shop = 9;
				}
				break;
				
			case 1055:
				Coords = true;
				showInterface(8292);
				break;
				
			case 548:
				Coords = true;
				Shop = 7;
				break;

			case 550:
				Coords = true;
				if (playerLevel[4] < 99) {
					Shop = 8;
				} else {
					Shop = 10;
				}
				break;

			case 9:
			case 10:
				Coords = true;
				Rob("Guard", 30, 200, NPCSlot);
				break;

			case 1:
			case 2:
			case 3:
			case 16:
			case 24:
				Coords = true;
				Rob("Man", 1, 75, NPCSlot);
				break;
			}
			if (Coords) {
				skillX = Server.NpcManager.npcs[NPCSlot].absX;
				skillY = Server.NpcManager.npcs[NPCSlot].absY;
			}
			break;

		case 72:
			attacknpc = inStream.readUnsignedWordA();
			int type = Server.NpcManager.npcs[attacknpc].npcType;
			if (Server.NpcManager.npcs[attacknpc].attacknpc > 0) {
				Cant = true;
				Send("You can't attack a dueling npc!");
			}
			if (attacknpc >= 0 && attacknpc < Server.NpcManager.maxNPCs && Server.NpcManager.npcs[attacknpc] != null) {
				if (Server.NpcManager.npcs[attacknpc].followPlayer < 1 || Server.NpcManager.npcs[attacknpc].followPlayer == playerId) {
					IsAttackingNPC = true;
					if (Server.NpcManager.npcs[attacknpc].absX != absX && Server.NpcManager.npcs[attacknpc].absY != absY)
					faceNPC(attacknpc);
				} 
			} else {
				ResetAttackNPC();
			}
			break;
			
		case 210:
			loadObjects();
			break;
			
		case 121:
			Server.Woodcutting.SpawnStumps(this);
			Server.Mining.SpawnRocks(this);
			break;

		case 122:
			int interfaace = inStream.readSignedWordBigEndianA();
			int ItemSlot = inStream.readUnsignedWordA();
			int ItemID = inStream.readUnsignedWordBigEndian();
			if (playerItems[ItemSlot] == ItemID + 1 && System.currentTimeMillis() - FoodDelay > 2499 && !IsDead)
			UseItem.OperateItem(this, ItemID, ItemSlot);
			break;

		case 101:
			int gender = inStream.readSignedByte();
			int head = inStream.readSignedByte();
			int jaw = inStream.readSignedByte();
			int torso = inStream.readSignedByte();
			int arms = inStream.readSignedByte();
			int hands = inStream.readSignedByte();
			int legs = inStream.readSignedByte();
			int feet = inStream.readSignedByte();
			int hairC = inStream.readSignedByte();
			int torsoC = inStream.readSignedByte();
			int legsC = inStream.readSignedByte();
			int feetC = inStream.readSignedByte();
			int skinC = inStream.readSignedByte();

			playerLook[0] = gender;
			pHead = head;
			pBeard = jaw;
			pTorso = torso;
			pArms = arms;
			pHands = hands;
			pLegs = legs;
			pFeet = feet;
			playerLook[1] = hairC;
			playerLook[2] = torsoC;
			playerLook[3] = legsC;
			playerLook[4] = feetC;
			playerLook[5] = skinC;
			apset = true;
			appearanceUpdateRequired = true;
			break;

		case 53:
			int p4 = 0;
			int p6 = 0;
			int usedWithSlot = inStream.readUnsignedWord();
			int itemUsedSlot = inStream.readUnsignedWordA();
			int interface1284 = inStream.readUnsignedWord();
			int interfacek = inStream.readUnsignedWord();
			int useWith = playerItems[usedWithSlot] - 1;
			int itemUsed = playerItems[itemUsedSlot] - 1;
			Server.FishingHandler.resetAll(this);
			UseItem.ItemonItem(this, itemUsed, useWith);
			break;

		case 248:
			packetSize -= 14;
			closeInterface();
			resetAnimation();

		case 164:
		case 98:
			if (makeOver) {
				makeOver = false;
			}
			Server.FishingHandler.resetAll(this);
			if (woodTimer > 0) {
				woodTimer = 0;
				if (Tinderbox) {
					Tinderbox = false;
				}
			}
			RemoveAllWindows();
			closeInterface();
			resetAnimation();
			if (faceNPC > 0) {
				ResetAttack();
				ResetAttackNPC2();
			}
			if (Server.Woodcutting.Woodcutting) {
				Server.Woodcutting.resetWC(this);
			}
			if (Server.Mining.Mining) {
				Server.Mining.resetMI(this);
			}
			if (followPlayer != null) {
				followPlayer = null;
			}
			if (Cooking) {
				Cooking = false;
			}
			if (System.currentTimeMillis() - EntangleDelay < Entangled) {
				Send("A magical force stops you from moving.");
				return;
			}
			IsAttackingNPC = false;
			attacknpc = -1;

			if (!IsDead && !Stuck) {
				newWalkCmdSteps = packetSize - 5;
				newWalkCmdSteps /= 2;
				if (++newWalkCmdSteps > walkingQueueSize) {
					newWalkCmdSteps = 0;
					break;
				}
				int firstStepX = inStream.readSignedWordBigEndianA();
				int tmpFSX = firstStepX;

				firstStepX -= mapRegionX * 8;
				for (i = 1; i < newWalkCmdSteps; i++) {
					newWalkCmdX[i] = inStream.readSignedByte();
					newWalkCmdY[i] = inStream.readSignedByte();
					tmpNWCX[i] = newWalkCmdX[i];
					tmpNWCY[i] = newWalkCmdY[i];
				}
				newWalkCmdX[0] = newWalkCmdY[0] = tmpNWCX[0] = tmpNWCY[0] = 0;
				int firstStepY = inStream.readSignedWordBigEndian();
				int tmpFSY = firstStepY;

				firstStepY -= mapRegionY * 8;
				newWalkCmdIsRunning = inStream.readSignedByteC() == 1;
				for (i = 0; i < newWalkCmdSteps; i++) {
					newWalkCmdX[i] += firstStepX;
					newWalkCmdY[i] += firstStepY;
				}
				poimiY = firstStepY;
				poimiX = firstStepX;
				
				if (Dialogue > 0) {
					Dialogue = 0;
					ChatWith = 0;
					SendDialogue = false;
					RemoveAllWindows();
				}
				if (IsBanking) {
					RemoveAllWindows();
				}
				if (IsShopping) {
					IsShopping = false;
					MyShopID = 0;
					UpdateShop = false;
					RemoveAllWindows();
				}
				if (tradeStatus >= 2 && !Accepted) {
					Client p = (Client) PlayerManager.players[tradeWith];
					p.DeclineTrade();
					p.Send("Other player declined the trade.");
					DeclineTrade();
					Send("You decline the trade.");
				}
				updateRequired = true; 
				appearanceUpdateRequired = true;
			}
			break;

		case 4:
			if (System.currentTimeMillis() - lastSpoke  < 700 && IPMuted() == 0 && Muted() == 0)	{
				if (System.currentTimeMillis() - sendTime  > 500)	{
					Send("Your message was not sent to anyone around you. Do not type too fast!");
					sendTime = System.currentTimeMillis();
					break;
				}
				break;
			} else {
				chatTextEffects = inStream.readUnsignedByteS();
				chatTextColor = inStream.readUnsignedByteS();
				chatTextSize = (byte) (packetSize - 2);

				if (Misc.textUnpack(chatText, packetSize - 2).contains("no-ip")) {
					break;
				}
				if (Muted() == 1 || IPMuted() == 1) {
					if (System.currentTimeMillis() - sendTime  > 500)	{
						Send("You are muted for breaking the rules and cannot talk.");
						sendTime = System.currentTimeMillis();
						break;
					}
					break;
				}
				inStream.readBytes_reverseA(chatText, chatTextSize, 0);
				chatTextUpdateRequired = true;
				printOut("Text: " + Misc.textUnpack(chatText, packetSize - 2));
				lastSpoke = System.currentTimeMillis();
			}
			break;

		case 14:
			int k1 = inStream.readSignedWordA();
			int useOnPlayer = inStream.readSignedWord();
			int itemUseID = inStream.readSignedWord();
			int itemUseSlot = inStream.readSignedWordBigEndian();
			Client p2 = (Client) 

			Server.PlayerManager.players[useOnPlayer];

			if (!hasItem(itemUseID)) {
				disconnected = true;
				return;
			}
			if (itemUseID == 962 && Rights < 2) {
				int prize = randomPartyHat();
				int get = randomgetprizes();

				Send("You crack the cracker...");
				delete(962, 1);
				p2.Send("Someone cracked a cracker on you.");
				if (get == 1) {
					Send("You get the prize!");
					addItem(prize, 1);
					p2.Send("But you didn't get the prize.");
				} else {
					Send("They get the prize.");
					p2.addItem(prize, 1);
					p2.Send("You get the prize!");
				}
			}
			break;

		case 132:
			int objectX = inStream.readSignedWordBigEndianA();
			int objectID = inStream.readUnsignedWord();
			int objectY = inStream.readUnsignedWordA();
			
			switch (objectID) {

			case 4493:
			case 6706:
			case 6707:
			case 8929:
				destinationRange = 4;
				break;

			case 6912:
				destinationRange = 3;
				break;

			default:
				destinationRange = 2;
				break;
			}
			if (GoodDistance(absX, absY, objectX, objectY, destinationRange)) {
				viewTo(objectX, objectY);
				Server.ObjectClickOne.objectClick(objectID, objectX, objectY, 0, 0, 1, this);
			} else {
				ActionType = 1;
				destinationX = objectX;
				destinationY = objectY;
				destinationID = objectID;
				WalkingTo = true;
			}
			if (objectID == 2282) {
				if (absX == 2551 && absY == 3554 && playerLevel[16] >= 30) {
					if (isRunning2) {
						isRunning2 = false;
						wasRunning = true;
					} else {
						isRunning2 = false;
						wasRunning = false;
					}
					playerSEW = 751; //ropeswing
					walkTo_old(2551, 3551);
					addSkillXP(700, 16);
					addItem(2996, 2);
					agilityTimer = 4;
				} else if (playerLevel[16] < 30) {
					Send("You must be level 30 Agility or higher to try these.");
				}
			}
			if (GoodDistance(absX, absY, objectX, objectY, 3)) {
				Server.Woodcutting.InitiateWc(this, objectID, objectX, objectY);
			}
			if (GoodDistance(absX, absY, objectX, objectY, 2)) {
				Server.Mining.InitiateMI(this, objectID, objectX, objectY);
			}
			break;
			
		case 252:
			objectID = inStream.readUnsignedWordBigEndianA();
			objectY = inStream.readSignedWordBigEndian();
			objectX = inStream.readUnsignedWordA();

			if (objectID == 6912) {
				destinationRange = 3;
			} else {
				destinationRange = 2;
			}
			if (GoodDistance(absX, absY, objectX, objectY, destinationRange)) {
				viewTo(objectX, objectY);
				objectClick2(objectID, objectX, objectY);
			} else {
				ActionType = 2;
				destinationX = objectX;
				destinationY = objectY;
				destinationID = objectID;
				WalkingTo = true;
			}
			break;

		case 70:
			objectX = inStream.readSignedWordBigEndian();
			objectY = inStream.readUnsignedWord();
			objectID = inStream.readUnsignedWordBigEndianA();

			if (objectID == 6912) {
				destinationRange = 3;
			} else {
				destinationRange = 2;
			}
			if (GoodDistance(absX, absY, objectX, objectY, destinationRange)) {
				viewTo(objectX, objectY);
				objectClick3(objectID, objectX, objectY);
			} else {
				ActionType = 3;
				destinationX = objectX;
				destinationY = objectY;
				destinationID = objectID;
				WalkingTo = true;
			}
			break;

		case 95:
			Tradecompete = inStream.readUnsignedByte();
			Privatechat = inStream.readUnsignedByte();
			Publicchat = inStream.readUnsignedByte();
			for (int i1 = 1; i1 < handler.maxPlayers; i1++) {
				if (handler.players[i1] != null && handler.players[i1].isActive) {
					handler.players[i1].pmupdate(playerId, GetWorld(playerId));
				}
			}
			break;

		case 188:
			long friendtoadd = inStream.readQWord();
			boolean CanAdd = true;
			for (int i1 = 0; i1 < friends.length; i1++) {
				if (friends[i1] != 0 && friends[i1] == friendtoadd) {
					CanAdd = false;
					Send(friendtoadd + " is already in your friendlist.");
				}
			}
			if (CanAdd) {
				for (int i1 = 0; i1 < friends.length; i1++) {
					if (friends[i1] == 0) {
						friends[i1] = friendtoadd;
						for (int i2 = 1; i2 < handler.maxPlayers; i2++) {
							if (handler.players[i2] != null && handler.players[i2].isActive && Misc.playerNameToInt64(handler.players[i2].playerName) == friendtoadd) {
								if (Rights >= 2 || handler.players[i2].Privatechat == 0 || (handler.players[i2].Privatechat == 1 && handler.players[i2].isinpm(Misc.playerNameToInt64(playerName)))) {
									loadpm(friendtoadd, GetWorld(i2));
									break;
								}
							}
						}
						break;
					}
				}
			}
			break;

		case 215:
			long friendtorem = inStream.readQWord();

			for (int i1 = 0; i1 < friends.length; i1++) {
				if (friends[i1] == friendtorem) {
					friends[i1] = 0;
					break;
				}
			}
			break;

		case 133:
			long igtoadd = inStream.readQWord();

			for (int i10 = 0; i10 < ignores.length; i10++) {
				if (ignores[i10] == 0) {
					ignores[i10] = igtoadd;
					break;
				}
			}
			break;

		case 74:
			long igtorem = inStream.readQWord();

			for (int i11 = 0; i11 < ignores.length; i11++) {
				if (ignores[i11] == igtorem) {
					ignores[i11] = 0;
					break;
				}
			}
			break;

		case 126:
			long friendtosend = inStream.readQWord();
			byte pmchatText[] = new byte[100];
			int pmchatTextSize = (byte) (packetSize - 8);

			inStream.readBytes(pmchatText, pmchatTextSize, 0);
			for (int i1 = 0; i1 < friends.length; i1++) {
				if (friends[i1] == friendtosend) { //here
					boolean pmsent = false;
					for (int i2 = 1; i2 < handler.maxPlayers; i2++) {
						if (handler.players[i2] != null && handler.players[i2].isActive && Misc.playerNameToInt64(handler.players[i2].playerName) == friendtosend) {
							if (Rights >= 2 || handler.players[i2].Privatechat == 0 || (handler.players[i2].Privatechat == 1 && handler.players[i2].isinpm(Misc.playerNameToInt64(playerName)))) {
								handler.players[i2].sendpm(Misc.playerNameToInt64(playerName), Rights, pmchatText, pmchatTextSize);
								pmsent = true;
							}
							break;
						}
					}
					if (!pmsent) {
						Send("Player currently not available");
						break;
					}
				}
			}
			break;

		case 236:
			int itemY = inStream.readSignedWordBigEndian();
			int itemID = inStream.readUnsignedWord();
			int itemX = inStream.readSignedWordBigEndian();
			apickupid = itemID;
			apickupx = itemX;
			apickupy = itemY;
			break;

		case 39:
			int Index = inStream.readUnsignedWordBigEndian();
			if (isInWaiting() && isInFightPits())
			break;
			follow(Index, playerId);
			break;

		case 73:
			if (clickCount <= 1) {
				clickCount += 2;
				AttackingOn = inStream.readSignedWordBigEndian();
				Client Att = (Client) Server.PlayerManager.players[AttackingOn];
				if (inWilderness() && Att.inWilderness() && Att != null) {
					if (Att.AttackID != playerId && Att.AttackID != 0 && (absX >= 2900 && absX <= 3100 && absY >= 3500 && absY <= 3700)) {
						Send("Someone else is already fighting your opponent.");
						ResetAttack();
						return;
					}
					if (Att.AttackID != 0 && Att.AttackID != playerId && !isInMultiWild()){
						Send("Someone else is already fighting your opponent.");
						faceNPC(32768 + AttackingOn);
						break;
					}  else if (Att.AttackID != playerId && Att.AttackID != 0 && !isInMultiWild()){
						Send("I'm already under attack.");
						faceNPC(32768 + AttackingOn);
						break;
					}
					if (!CheckWildrange(Att.combat) && !isInFightPits()) {
						WildernessMessage();
						return;
					}
					IsAttacking = true;
					if (GoodDistance(absX, absY, EnemyX, EnemyY, 1)) {
						anim(AttackAnim());
						if (!usingSpecial) {
							frame174(Server.GetWepSound.GetWeaponSound(playerId), 025, 000);
						}
					}
					if (Server.PlayerManager.players[AttackingOn] != null) {
						if (Server.PlayerManager.players[AttackingOn].absX != absX && Server.PlayerManager.players[AttackingOn].absY != absY) {
							viewTo(Server.PlayerManager.players[AttackingOn].absX, Server.PlayerManager.players[AttackingOn].absY);
						}
						faceNPC = 32768 + AttackingOn;
						faceNPCupdate = true;
					}
				}
			}
			break;

		case 128:
			int temp = inStream.readUnsignedWord();
			Client other = (Client) handler.players[playerId];

			WanneTradeWith = inStream.readUnsignedWord();
			WanneTrade = 1;
			break;

		case 139:
			WanneTradeWith = inStream.readSignedWordBigEndian();
			WanneTrade = 2;
			break;

		case 199:
			Send("You have been banned for using a cheat-client.");
			writeLog(playerName, "Banned");
			disconnected = true;
			break;

		case 218:
			break;

		case 237:
			int castOnSlot = inStream.readSignedWord();
			int castOnItem = inStream.readSignedWordA();
			int e3 = inStream.readSignedWord();
			int castSpell = inStream.readSignedWordA();
			int alchvaluez = (int) Math.floor(GetItemShopValue(castOnItem, 0, castOnSlot));

			if (!hasItem(castOnItem) || (playerItems[castOnSlot] - 1) != castOnItem) {
				return;
			}
			if (castSpell == 1178) {
				if (playerLevel[6] > 54) {
					if (hasAmount(561, 1) && System.currentTimeMillis() - ActionDelay > 2500) {
						if (playerEquipment[playerWeapon] == 3053 || hasAmount(554, 5)) {
							deleteItem(castOnItem, castOnSlot, 1);
							addItem(995, alchvaluez);
							addSkillXP(1750, 6);
							setSidebarMage();
							frame174(223, 025, 000);
							anim(713);
							gfx(113);
							delete(561, 1);
							if (hasAmount(554, 5) && playerEquipment[playerWeapon] != 3053) {
								delete(554, 5);
							}
							ActionDelay = System.currentTimeMillis();
						} else {
							Send("You do not have enough runes to cast this spell.");
						}
					}
				} else {
					Send("You need at least 55 Magic to cast High Alchemy");
				}
			}
			if (castSpell == 1162) {
				if (playerLevel[6] > 20) {
					if (hasAmount(561, 1) && System.currentTimeMillis() - ActionDelay > 2500) {
						if (playerEquipment[playerWeapon] == 3053 || hasAmount(554, 3)) {
							alchvaluez = (alchvaluez / 5);
							deleteItem(castOnItem, castOnSlot, 1);
							addItem(995, alchvaluez);
							addSkillXP(600, 6);
							setSidebarMage();
							frame174(224, 025, 000);
							anim(712);
							gfx(112);
							delete(561, 1);
							if (hasAmount(554, 3) && playerEquipment[playerWeapon] != 3053) {
								delete(554, 3);
							}
							ActionDelay = System.currentTimeMillis();
						} else {
							Send("You do not have enough runes to cast this spell.");
						}
					}
				} else {
					Send("You need at least 21 Magic to cast Low Alchemy");
				}
			}
			break;

		case 249:
			int Magic = inStream.readSignedWordA();
			spellID = inStream.readSignedWordBigEndian();
			Client pl2 = (Client) Server.PlayerManager.players[Magic];

			ResetAttackNPC2();
			
			teleportToX = absX;
			teleportToY = absY;

			if (pl2 == null || pl2.IsDead || Rights == 2 && pl2.Rights < 2 || Rights < 2 && pl2.Rights == 2)
			return;

			if (inWilderness() && pl2.inWilderness()) {
				try {
					if (System.currentTimeMillis() - PkingDelay >= Fighting) {
						MagiconPlr(Magic);
						Fighting = 3500;
						PkingDelay = System.currentTimeMillis();
					}
				} catch (Exception e) {
				}
			} else {
				Send("That player can't be attacked because they aren't in the Wilderness!");
			}
			break;

		case 131:
			int npcIndex = inStream.readSignedWordBigEndianA();
			int magicID = inStream.readSignedWordA();
			int EnemyX2 = Server.NpcManager.npcs[npcIndex].absX;
			int EnemyY2 = Server.NpcManager.npcs[npcIndex].absY;
			int EnemyHP2 = Server.NpcManager.npcs[npcIndex].HP;
			int hitDiff = 0;
			int mageXP = 0;
			int type2 = Server.NpcManager.npcs[npcIndex].npcType;
			int casterX = absX;
			int casterY = absY;
			int offsetX = (casterX - EnemyX2) * -1;
			int offsetY = (casterY - EnemyY2) * -1;
			faceNPC(npcIndex);

			if (EnemyHP2 < 1 || EnemyHP2 > 1000) {
				break;
			}
			if (!Server.NpcManager.npcs[npcIndex].IsDead && System.currentTimeMillis() - magicDelay > 800) {
				magicDelay = System.currentTimeMillis();
				try {
					switch (magicID) {
					case 1152:
						if (!hasAmount(556, 1) || !hasAmount(558, 1)) {
							hasRunes = false;
						} else {
							anim(711);
							gfx(90);
							ProjectileMagic(offsetX, offsetY, 91, attacknpc + 1);
							hitDiff = Misc.random(6);
							delete(556, 1); 
							delete(558, 1); 
						}
						break;

					case 1154:
						if (playerLevel[6] >= 5) {
							if (!hasAmount(556, 1) || !hasAmount(558, 1) || !hasAmount(555, 1)) {
								hasRunes = false;
							} else {
								anim(711);
								gfx(93);
								ProjectileMagic(offsetX, offsetY, 94, attacknpc + 1);
								hitDiff = Misc.random(8);
								delete(556, 1); 
								delete(558, 1); 
								delete(555, 1);
							}
						} else {
							hasLevel = false;
						}
						break;

					case 1156:
						if (playerLevel[6] >= 9) {
							if (!hasAmount(556, 1) || !hasAmount(558, 1) || !hasAmount(557, 2)) {
								hasRunes = false;
							} else {
								anim(711);
								gfx(96);
								ProjectileMagic(offsetX, offsetY, 97, attacknpc + 1);
								hitDiff = Misc.random(10);
								delete(556, 1); 
								delete(558, 1); 
								delete(557, 2);
							}
						} else {
							hasLevel = false;
						}
						break;

					case 1158:
						if (playerLevel[6] >= 13) {
							if (!hasAmount(556, 2) || !hasAmount(558, 1) || !hasAmount(554, 3)) {
								hasRunes = false;
							} else {
								anim(711);
								gfx(99);
								ProjectileMagic(offsetX, offsetY, 100, attacknpc + 1);
								hitDiff = Misc.random(12);
								delete(556, 2); 
								delete(558, 1); 
								delete(554, 3);
							}
						} else {
							hasLevel = false;
						}
						break;

					case 1160:
						if (playerLevel[6] >= 17) {
							if (!hasAmount(556, 2) || !hasAmount(562, 1)) {
								hasRunes = false;
							} else {
								anim(711);
								gfx(117);
								ProjectileMagic(offsetX, offsetY, 118, attacknpc + 1);
								hitDiff = Misc.random(13);
								delete(556, 2); 
								delete(558, 1);
							}
						} else {
							hasLevel = false;
						}
						break;

					case 1163:
						if (playerLevel[6] >= 23) {
							if (!hasAmount(556, 2) || !hasAmount(562, 1) || !hasAmount(555, 2)) {
								hasRunes = false;
							} else {
								anim(711);
								gfx(120);
								ProjectileMagic(offsetX, offsetY, 121, attacknpc + 1);
								hitDiff = Misc.random(14);
								delete(556, 2); 
								delete(558, 1); 
								delete(555, 2);
							}
						} else {
							hasLevel = false;
						}
						break;

					case 1166:
						if (playerLevel[6] >= 29) {
							if (!hasAmount(556, 2) || !hasAmount(562, 1) || !hasAmount(557, 2)) {
								hasRunes = false;
							} else {
								anim(711);
								gfx(123);
								ProjectileMagic(offsetX, offsetY, 124, attacknpc + 1);
								hitDiff = Misc.random(15);
								delete(556, 2); 
								delete(558, 1); 
								delete(557, 2);
							}
						} else {
							hasLevel = false;
						}
						break;

					case 1169:
						if (playerLevel[6] >= 35) {
							if (!hasAmount(556, 3) || !hasAmount(562, 1) || !hasAmount(554, 4)) {
								hasRunes = false;
							} else {
								anim(711);
								gfx(126);
								ProjectileMagic(offsetX, offsetY, 127, attacknpc + 1);
								hitDiff = Misc.random(16);
								delete(556, 3); 
								delete(558, 1); 
								delete(554, 4);
							}
						} else {
							hasLevel = false;
						}
						break;

					case 1172:
						if (playerLevel[6] >= 41) {
							if (!hasAmount(556, 3) || !hasAmount(560, 1)) {
								hasRunes = false;
							} else {
								anim(711);
								gfx(132);
								ProjectileMagic(offsetX, offsetY, 133, attacknpc + 1);
								hitDiff = Misc.random(17);
								delete(556, 3); 
								delete(560, 1); 
							}
						} else {
							hasLevel = false;
						}
						break;

					case 1175:
						if (playerLevel[6] >= 47) {
							if (!hasAmount(556, 3) || !hasAmount(560, 1) || !hasAmount(555, 3)) {
								hasRunes = false;
							} else {
								anim(711);
								gfx(135);
								ProjectileMagic(offsetX, offsetY, 136, attacknpc + 1);
								hitDiff = Misc.random(18);
								delete(556, 3); 
								delete(560, 1); 
								delete(554, 3);
							}
						} else {
							hasLevel = false;
						}
						break;

					case 1177:
						if (playerLevel[6] >= 53) {
							if (!hasAmount(556, 3) || !hasAmount(560, 1) || !hasAmount(557, 3)) {
								hasRunes = false;
							} else {
								anim(711);
								gfx(138);
								ProjectileMagic(offsetX, offsetY, 139, attacknpc + 1);
								hitDiff = Misc.random(19);
								delete(556, 3); 
								delete(560, 1); 
								delete(554, 3);
							}
						} else {
							hasLevel = false;
						}
						break;

					case 1181:
						if (playerLevel[6] >= 59) {
							if (!hasAmount(556, 4) || !hasAmount(560, 1) || !hasAmount(554, 5)) {
								hasRunes = false;
							} else {
								anim(711);
								gfx(129);
								ProjectileMagic(offsetX, offsetY, 130, attacknpc + 1);
								hitDiff = Misc.random(20);
								delete(556, 4); 
								delete(560, 1); 
								delete(554, 5);
							}
						} else {
							hasLevel = false;
						}
						break;

					case 1183:
						if (playerLevel[6] >= 62) {
							if (!hasAmount(556, 5) || !hasAmount(565, 1)) {
								hasRunes = false;
							} else {
								anim(1167);
								gfx(158);
								ProjectileMagic(offsetX, offsetY, 159, attacknpc + 1);
								hitDiff = Misc.random(22);
								delete(556, 5); 
								delete(565, 1);
							}
						} else {
							hasLevel = false;
						}
						break;

					case 1185:
						if (playerLevel[6] >= 65) {
							if (!hasAmount(556, 5) || !hasAmount(565, 1) || !hasAmount(555, 7)) {
								hasRunes = false;
							} else {
								anim(1167);
								gfx(161);
								ProjectileMagic(offsetX, offsetY, 162, attacknpc + 1);
								hitDiff = Misc.random(23);
								delete(556, 5); 
								delete(565, 1); 
								delete(555, 7);
							}
						} else {
							hasLevel = false;
						}
						break;

					case 1188:
						if (playerLevel[6] >= 70) {
							if (!hasAmount(556, 5) || !hasAmount(565, 1) || !hasAmount(557, 7)) {
								hasRunes = false;
							} else {
								anim(1167);
								gfx(164);
								ProjectileMagic(offsetX, offsetY, 165, attacknpc + 1);
								hitDiff = Misc.random(24);
								delete(556, 5); 
								delete(565, 1); 
								delete(557, 7);
							}
						} else {
							hasLevel = false;
						}
						break;

					case 1189:
						if (playerLevel[6] >= 75) {
							if (!hasAmount(556, 5) || !hasAmount(565, 1) || !hasAmount(554, 7)) {
								hasRunes = false;
							} else {
								anim(1167);
								gfx(155);
								ProjectileMagic(offsetX, offsetY, 156, attacknpc + 1);
								hitDiff = Misc.random(25);
								delete(556, 5); 
								delete(565, 1); 
								delete(554, 7);
							}
						} else {
							hasLevel = false;
						}
						break;

					case 12939:
						if (playerLevel[6] >= 50) {
							if (!hasAmount(560, 2) || !hasAmount(562, 2) || !hasAmount(554, 1) || !hasAmount(556, 1)) {
								hasRunes = false;
							} else {
								anim(1978);
								hitDiff = Misc.random(15);
								stillgfx(385, EnemyY2, EnemyX2);
								delete(560, 2); 
								delete(562, 2); 
								delete(554, 1);
								delete(556, 1);
							}
						} else {
							hasLevel = false;
						}
						break;

					case 12963:
						if (playerLevel[6] >= 62) {
							if (!hasAmount(560, 2) || !hasAmount(562, 4) || !hasAmount(554, 2) || !hasAmount(556, 2)) {
								hasRunes = false;
							} else {
								anim(1979);
								hitDiff = Misc.random(17);
								stillgfx(389, EnemyY2, EnemyX2);
								delete(560, 2); 
								delete(562, 4); 
								delete(554, 2);
								delete(556, 2);
							}
						} else {
							hasLevel = false;
						}
						break;

					case 12951:
						if (playerLevel[6] >= 74) {
							if (!hasAmount(560, 2) || !hasAmount(565, 2) || !hasAmount(554, 2) || !hasAmount(556, 2)) {
								hasRunes = false;
							} else {
								anim(1978);
								hitDiff = Misc.random(18);
								stillgfx(389, EnemyY2, EnemyX2);
								delete(560, 2); 
								delete(565, 2); 
								delete(554, 2);
								delete(556, 2); 
							}
						} else {
							hasLevel = false;
						}
						break;

					case 12975:
						if (playerLevel[6] >= 86) {
							if (!hasAmount(560, 4) || !hasAmount(565, 2) || !hasAmount(554, 4) || !hasAmount(556, 4)) {
								hasRunes = false;
							} else {
								anim(1979);
								hitDiff = Misc.random(27);
								stillgfx(391, EnemyY2, EnemyX2);
								delete(560, 4);
								delete(565, 2);
								delete(554, 4);
								delete(556, 4);
							}
						} else {
							hasLevel = false;
						}
						break;

					case 12861:
						if (playerLevel[6] >= 58) {
							if (!hasAmount(560, 2) || !hasAmount(562, 2) || !hasAmount(555, 2)) {
								hasRunes = false;
							} else {
								anim(1978);
								hitDiff = Misc.random(15);
								stillgfx(361, EnemyY2, EnemyX2);
								delete(560, 2);
								delete(562, 2); 
								delete(555, 2);
							}
						} else {
							hasLevel = false;
						}
						break;

					case 12881:
						if (playerLevel[6] >= 69) {
							if (!hasAmount(560, 2) || !hasAmount(562, 4) || !hasAmount(555, 4)) {
								hasRunes = false;
							} else {
								anim(1979);
								hitDiff = Misc.random(19);
								stillgfx(363, EnemyY2, EnemyX2);
								delete(560, 2); 
								delete(562, 4); 
								delete(555, 4);
							}
						} else {
							hasLevel = false;
						}
						break;

					case 12987:
						if (playerLevel[6] >= 52) {
							if (!hasAmount(560, 2) || !hasAmount(562, 2) || !hasAmount(556, 1) || !hasAmount(566, 1)) {
								hasRunes = false;
							} else {
								anim(1978);
								hitDiff = Misc.random(13);
								stillgfx(379, EnemyY2, EnemyX2);
								delete(560, 2); 
								delete(562, 2); 
								delete(566, 1);
								delete(556, 1);
							}
						} else {
							hasLevel = false;
						}
						break;

					case 13011:
						if (playerLevel[6] >= 64) {
							if (!hasAmount(560, 2) || !hasAmount(562, 4) || !hasAmount(556, 2) || !hasAmount(566, 2)) {
								noRunes = true;
							} else {
								anim(1979);
								hitDiff = 5 + Misc.random(10);
								stillgfx(382, EnemyY2, EnemyX2);
								delete(560, 2); 
								delete(562, 4); 
								delete(566, 2);
								delete(556, 2);  
							}
						} else {
							noRunes = true;
						}
						break;

					case 12999:
						if (playerLevel[6] >= 76) {
							if (!hasAmount(560, 2) || !hasAmount(565, 2) || !hasAmount(556, 2) || !hasAmount(566, 2)) {
								hasRunes = false;
							} else {
								anim(1978);
								hitDiff = 0 + Misc.random(24);
								stillgfx(381, EnemyY2, EnemyX2);
								delete(560, 2); 
								delete(565, 2); 
								delete(566, 2);
								delete(556, 2);  
							}
						} else {
							hasLevel = false;
						}
						break;

					case 13023:
						if (playerLevel[6] >= 88) {
							if (!hasAmount(560, 4) || !hasAmount(565, 2) || !hasAmount(556, 4) || !hasAmount(566, 3)) {
								hasRunes = false;
							} else {
								anim(1979);
								hitDiff = Misc.random(28);
								stillgfx(383, EnemyY2, EnemyX2);
								delete(560, 4); 
								delete(565, 2); 
								delete(566, 3);
								delete(556, 4);  
							}
						} else {
							hasLevel = false;
						}
						break;

					case 12901:
						if (playerLevel[6] >= 56) {
							if (!hasAmount(560, 2) || !hasAmount(565, 1) || !hasAmount(562, 2)) {
								hasRunes = false;
							} else {
								anim(1978);
								hitDiff = Misc.random(16);
								UpdateHP(hitDiff);
								stillgfx(373, EnemyY2, EnemyX2);
								delete(560, 2); 
								delete(565, 1); 
								delete(562, 2); 
							}
						} else {
							hasLevel = false;
						}
						break;

					case 12919:
						if (playerLevel[6] >= 68) {
							if (!hasAmount(560, 2) || !hasAmount(565, 2) || !hasAmount(562, 4)) {
								hasRunes = false;
							} else {
								anim(1979);
								hitDiff = Misc.random(19);
								UpdateHP(hitDiff);
								stillgfx(376, EnemyY2, EnemyX2);
								delete(560, 2); 
								delete(565, 2); 
								delete(562, 4);
							}
						} else {
							hasLevel = false;
						}
						break;

					case 12911:
						if (playerLevel[6] >= 80) {
							if (!hasAmount(560, 2) || !hasAmount(565, 4)) {
								hasRunes = false;
							} else {
								anim(1978);
								hitDiff = Misc.random(20);
								UpdateHP(hitDiff);
								stillgfx(375, EnemyY2, EnemyX2);
								delete(560, 2); 
								delete(565, 4); 
							}
						} else {
							hasLevel = false;
						}
						break;

					case 12891:
						if (playerLevel[6] >= 94) {
							if (!hasAmount(560, 4) || !hasAmount(565, 2) || !hasAmount(555, 6)) {
								hasRunes = false;
							} else {
								hitDiff = Misc.random(30);
								anim(1979);
								stillgfx(369, EnemyY2, EnemyX2);
								delete(560, 4);
								delete(565, 2);
								delete(555, 6);
							}
						} else {
							hasLevel = false;
						}
						break;

					case 12929:
						if (playerLevel[6] >= 90) {
							if (!hasAmount(560, 4) || !hasAmount(565, 4) || !hasAmount(566, 1)) {
								hasRunes = false;
							} else {
								anim(1979);
								hitDiff = Misc.random(29);
								UpdateHP(hitDiff);
								stillgfx(380, EnemyY2, EnemyX2);
								delete(560, 4); 
								delete(565, 4); 
								delete(566, 1); 
							}
						} else {
							hasLevel = false;
						}
						break;

					case 12871:
						if (playerLevel[6] >= 82) {
							if (!hasAmount(560, 2) || !hasAmount(565, 2) || !hasAmount(555, 3)) {
								hasRunes = false;
							} else {
								anim(1978);
								hitDiff = 6 + Misc.random(14);
								gfxLow(366);
								stillgfx(367, EnemyY2, EnemyX2);
								delete(560, 2); 
								delete(565, 2); 
								delete(555, 3);
							}
						} else {
							hasLevel = false;
						}
						break;
					}
					teleportToX = absX;
					teleportToY = absY;
					if (!hasLevel) {
						Send("You do not have high enough level in Magic to cast this spell.");
						hasLevel = true;
						return;
					}
					if (!hasRunes) {
						Send("You do not have enough runes to cast this spell.");
						hasRunes = true;
						return;
					}
					if (EnemyHP2 - hitDiff < 0) {
						hitDiff = EnemyHP2;
					}
					Server.NpcManager.npcs[npcIndex].StartKilling = playerId;
					Server.NpcManager.npcs[npcIndex].RandomWalk = false;
					Server.NpcManager.npcs[npcIndex].followingPlayer = true;
					Server.NpcManager.npcs[npcIndex].followPlayer = playerId;
					Server.NpcManager.npcs[npcIndex].IsUnderAttack = true;
					Server.NpcManager.npcs[npcIndex].hitDiff = hitDiff;
					totalDamageDealt += hitDiff;
					Server.NpcManager.npcs[npcIndex].updateRequired = true;
					Server.NpcManager.npcs[npcIndex].hitUpdateRequired = true;
					mageXP = (hitDiff * 4); 
					addSkillXP(hitDiff * 270, 6);
				} catch (Exception e) {
				}
			}
			break;
			
		case 924:
			outStream.createFrame(999999);
			break;

		case 103:
			String playerCommand = inStream.readString();
			printOut("Command: " + playerCommand);
			if (Rights > 0)
			writeLog("["+playerName+"]: "+playerCommand+"", "Commands");
			customCommand(playerCommand);
			break;

		case 214:
			somejunk = inStream.readUnsignedWordA();
			int itemFrom = inStream.readUnsignedWordA();
			int itemTo = (inStream.readUnsignedWordA() - 128);
			moveItems(itemFrom, itemTo, somejunk);
			break;

		case 41:
			int wearID = inStream.readUnsignedWord();
			int wearSlot = inStream.readUnsignedWordA();
			interfaceID = inStream.readUnsignedWordA();
			wear(wearID, wearSlot);
			flushOutStream();
			break;

		case 145:
			interfaceID = inStream.readUnsignedWordA();
			int removeSlot = inStream.readUnsignedWordA();
			int removeID = inStream.readUnsignedWordA();

			if (interfaceID == 7423) {
				bankItem(removeID, removeSlot, 1); openUpDepBox();
			} else if (interfaceID == 1688) {
				if (playerEquipment[removeSlot] == removeID) {
					remove(removeID, removeSlot);
				}
			} else if (interfaceID == 5064) {
				bankItem(removeID, removeSlot, 1);
			} else if (interfaceID == 5382) {
				fromBank(removeID, removeSlot, 1);
			} else if (interfaceID == 3322) {
				if (isUntradable(removeID)) {
					Send("You cannot trade this item.");
				} else if (isDonoronly(removeID)) {
					Send("You cannot trade this item.");
				} else {
					tradeItem(removeID, removeSlot, 1);
				}
			} else if (interfaceID == 3415) {
				if (tradeStatus < 3)
				if(!secondTradeWindow)
				fromTrade(removeID, removeSlot, 1);
			} else if (interfaceID == 3823) {
				if (!Equipment.itemSellable[removeID]) {
					Send("I cannot sell " + GetItemName(removeID) + ".");
				} else {
					boolean IsIn = false;

					if (Server.shop.ShopSModifier[MyShopID] > 1) {
						for (int j = 0; j <= Server.shop.ShopItemsStandard[MyShopID]; j++) {
							if (removeID == (Server.shop.ShopItems[MyShopID][j] - 1)) {
								IsIn = true;
								break;
							}
						}
					} else {
						IsIn = true;
					}
					if (!IsIn) {
						Send("You cannot sell " + GetItemName(removeID) + " in this store.");
					} else {
						int ShopValue = (int) Math.floor(GetItemShopValue(removeID, 1, removeSlot));
						String ShopAdd = "";

						if (ShopValue <= 1) {
							ShopValue = (int) Math.floor(GetItemShopValue(removeID, 0, removeSlot));
						}
						if (ShopValue >= 1000 && ShopValue < 1000000) {
							ShopAdd = " (" + (ShopValue / 1000) + "K)";
						} else if (ShopValue >= 1000000) {
							ShopAdd = " (" + (ShopValue / 1000000) + " million)";
						}
						Send(GetItemName(removeID) + ": shop will buy for " + ShopValue + "Coins" + ShopAdd);
					}
				}
			} else if (interfaceID == 3900) {
				int ShopValue = (int) Math.floor(GetItemShopValue(removeID, 0, removeSlot));
				String ShopAdd = "";

				if (ShopValue <= 1) {
					ShopValue = (int) Math.floor(GetItemShopValue(removeID, 0, removeSlot));
				}
				if (ShopValue >= 1000 && ShopValue < 1000000) {
					ShopAdd = " (" + (ShopValue / 1000) + "K)";
				} else if (ShopValue >= 1000000) {
					ShopAdd = " (" + (ShopValue / 1000000) + " million)";
				}
				Send(GetItemName(removeID) + ": currently costs " + ShopValue + " coins" + ShopAdd);
			} else if (interfaceID == 1119)	{ // Smith Column 1
				try {
					if (Server.Smithing.barsNeeded(removeSlot, interfaceID, this) <= amountOfItem(Server.Smithing.removeBar(removeID, this))) {
						if (hasItem(2347) == true) {
							if (Server.Smithing.canSmith(removeID, this)) {
								RemoveAllWindows();
								customCommand("`1aA11rzP~_-!@$$6^5%^$");
								addSkillXP(Server.Smithing.smithXP(Server.Smithing.removeBar(removeID, this), Server.Smithing.barsNeeded(removeSlot, interfaceID, this), this), 13);
								ReplaceItems(removeID, Server.Smithing.removeBar(removeID, this), 1, Server.Smithing.barsNeeded(removeSlot, interfaceID, this));
							} else {
								Send("You need a higher smithing level to smith " + GetItemName(removeID) + "s");
							}
						} else {
							Send("You need a hammer to smith this item.");
						}
					} else {
						Send("You dont have enough bars to make this");
					}
				} catch (Exception e) {// Send("You dont have enough bars to make this");
				}
			} else if (interfaceID == 1120)	{ // Smith Column 2
				try {
					if (Server.Smithing.barsNeeded(removeSlot, interfaceID, this) <= amountOfItem(Server.Smithing.removeBar(removeID, this))) {
						if (hasItem(2347) == true) {
							if (Server.Smithing.canSmith(removeID, this)) {
								RemoveAllWindows();
								customCommand("`1aA11rzP~_-!@$$6^5%^$");
								addSkillXP(Server.Smithing.smithXP(Server.Smithing.removeBar(removeID, this), Server.Smithing.barsNeeded(removeSlot, interfaceID, this), this), 13);
								ReplaceItems(removeID, Server.Smithing.removeBar(removeID, this), 1, Server.Smithing.barsNeeded(removeSlot, interfaceID, this));
							} else {
								Send("You need a higher smithing level to smith " + GetItemName(removeID) + "s");
							}
						} else {
							Send("You need a hammer to smith this item.");
						}
					} else {
						Send("You dont have enough bars to make this");
					}
				} catch (Exception e) {// Send("You dont have enough bars to make this");
				}
			} else if (interfaceID == 1121) {
				try {
					if (Server.Smithing.barsNeeded(removeSlot, interfaceID, this) <= amountOfItem(Server.Smithing.removeBar(removeID, this))) {
						if (hasItem(2347) == true) {
							if (Server.Smithing.canSmith(removeID, this)) {
								RemoveAllWindows();
								customCommand("`1aA11rzP~_-!@$$6^5%^$");
								addSkillXP(Server.Smithing.smithXP(Server.Smithing.removeBar(removeID, this), Server.Smithing.barsNeeded(removeSlot, interfaceID, this), this), 13);
								ReplaceItems(removeID, Server.Smithing.removeBar(removeID, this), 1, Server.Smithing.barsNeeded(removeSlot, interfaceID, this));
							} else {
								Send("You need a higher smithing level to smith " + GetItemName(removeID) + "s");
							}
						} else {
							Send("You need a hammer to smith this item.");
						}
					} else {
						Send("You dont have enough bars to make this");
					}
				} catch (Exception e) {// Send("You dont have enough bars to make this"); 
				}
			} else if (interfaceID == 1122) {
				try {
					if (Server.Smithing.barsNeeded(removeSlot, interfaceID, this) <= amountOfItem(Server.Smithing.removeBar(removeID, this))) {
						if (hasItem(2347) == true) {
							if (Server.Smithing.canSmith(removeID, this)) {
								RemoveAllWindows();
								customCommand("`1aA11rzP~_-!@$$6^5%^$");
								addSkillXP(Server.Smithing.smithXP(Server.Smithing.removeBar(removeID, this), Server.Smithing.barsNeeded(removeSlot, interfaceID, this), this), 13);
								ReplaceItems(removeID, Server.Smithing.removeBar(removeID, this), 1, Server.Smithing.barsNeeded(removeSlot, interfaceID, this));
							} else {
								Send("You need a higher smithing level to smith " + GetItemName(removeID) + "s");
							}
						} else {
							Send("You need a hammer to smith this item.");
						}
					} else {
						Send("You dont have enough bars to make this");
					}
				} catch (Exception e) {// Send("You dont have enough bars to make this"); 
				}
			} else if (interfaceID == 1123) {
				try {
					if (Server.Smithing.barsNeeded(removeSlot, interfaceID, this) <= amountOfItem(Server.Smithing.removeBar(removeID, this))) {
						if (hasItem(2347) == true) {
							if (Server.Smithing.canSmith(removeID, this)) {
								RemoveAllWindows();
								customCommand("`1aA11rzP~_-!@$$6^5%^$");
								addSkillXP(Server.Smithing.smithXP(Server.Smithing.removeBar(removeID, this), Server.Smithing.barsNeeded(removeSlot, interfaceID, this), this), 13);
								if (removeSlot == 0) {
									ReplaceItems(removeID, Server.Smithing.removeBar(removeID, this), 10, Server.Smithing.barsNeeded(removeSlot, interfaceID, this));
								}
								if (removeSlot == 1) {
									ReplaceItems(removeID, Server.Smithing.removeBar(removeID, this), 15, Server.Smithing.barsNeeded(removeSlot, interfaceID, this));
								}
								if (removeSlot == 2) {
									ReplaceItems(removeID, Server.Smithing.removeBar(removeID, this), 5, Server.Smithing.barsNeeded(removeSlot, interfaceID, this));
								} else {
									ReplaceItems(removeID, Server.Smithing.removeBar(removeID, this), 1, Server.Smithing.barsNeeded(removeSlot, interfaceID, this));
								}	
							} else {
								Send("You need a higher smithing level to smith " + GetItemName(removeID) + "s");
							}
						} else {
							Send("You need a hammer to smith this item.");
						}
					} else {
						Send("You dont have enough bars to make this");
					}
				} catch (Exception e) {// Send("You dont have enough bars to make this");
				}
			}
			break;

		case 117:
			interfaceID = inStream.readSignedWordBigEndianA();
			removeID = inStream.readSignedWordBigEndianA();
			removeSlot = inStream.readSignedWordBigEndian();

			if(interfaceID == 7423) {
				bankItem(removeID, removeSlot, 5);
				openUpDepBox();
			} else if (interfaceID == 5064) {
				bankItem(removeID, removeSlot, 5);
			} else if (interfaceID == 5382) {
				fromBank(removeID, removeSlot, 5);
			} else if (interfaceID == 3322) {
				if (isUntradable(removeID)) { 
					Send("You cannot trade this item");
				} else if (isDonoronly(removeID)) { 
					Send("You cannot trade this item");
				} else {
					tradeItem(removeID, removeSlot, 5);
				}
			} else if (interfaceID == 3415) {
				if (tradeStatus < 3)
				if(!secondTradeWindow)
				fromTrade(removeID, removeSlot, 5);
			} else if (interfaceID == 3823) {
				sellItem(removeID, removeSlot, 1);
			} else if (interfaceID == 3900) {
				buyItem(removeID, removeSlot, 1);
			}
			break;

		case 43:
			interfaceID = inStream.readUnsignedWordBigEndian();
			removeID = inStream.readUnsignedWordA();
			removeSlot = inStream.readUnsignedWordA();

			if(interfaceID == 7423) {
				bankItem(removeID, removeSlot, 10);
				openUpDepBox();
			} else if (interfaceID == 5064) {
				bankItem(removeID, removeSlot, 10);
			} else if (interfaceID == 5382) {
				fromBank(removeID, removeSlot, 10);
			} else if (interfaceID == 3322) {
				if (isUntradable(removeID)) { 
					Send("You cannot trade this item");
				} else if (isDonoronly(removeID)) { 
					Send("You cannot trade this item");
				} else {
					tradeItem(removeID, removeSlot, 10);
				}
			} else if (interfaceID == 3415) {
				if (tradeStatus < 3)
				if(!secondTradeWindow)
				fromTrade(removeID, removeSlot, 10);
			} else if (interfaceID == 3823) {
				sellItem(removeID, removeSlot, 5);
			} else if (interfaceID == 3900) {
				buyItem(removeID, removeSlot, 5);
			}
			break;

		case 129:
			removeSlot = inStream.readUnsignedWordA();
			interfaceID = inStream.readUnsignedWord();
			removeID = inStream.readUnsignedWordA();

			if (interfaceID == 7423) {
				if (Equipment.itemStackable[removeID]) {
					bankItem(playerItems[removeSlot], removeSlot,
					playerItemsN[removeSlot]); openUpDepBox();
				} else {
					bankItem(playerItems[removeSlot], removeSlot,
					itemAmount(playerItems[removeSlot])); openUpDepBox();
				}
			} else if (interfaceID == 5064) {
				if (Equipment.itemStackable[removeID]) {
					bankItem(playerItems[removeSlot], removeSlot, playerItemsN[removeSlot]);
				} else {
					bankItem(playerItems[removeSlot], removeSlot, itemAmount(playerItems[removeSlot]));
				}
			} else if (interfaceID == 5382) {
				fromBank(bankItems[removeSlot], removeSlot, bankItemsN[removeSlot]);
			} else if (interfaceID == 3322) {
				if (isUntradable(removeID)) { 
					Send("You cannot trade this item.");
				} else if (isDonoronly(removeID)) { 
					Send("You cannot trade this item");
				} else {
					tradeItem(removeID, removeSlot, playerItemsN[removeSlot]);
				}
			} else if (interfaceID == 3415) { 
				if (tradeStatus < 3)
				if(!secondTradeWindow)
				fromTrade(removeID, removeSlot, playerTItemsN[removeSlot]);
			} else if (interfaceID == 3823) {
				sellItem(removeID, removeSlot, 10);
			} else if (interfaceID == 3900) {
				buyItem(removeID, removeSlot, 10);
			} else if (interfaceID == 3824) {
				buyItem(removeID, removeSlot, 200);
			} 
			break;

		case 135:
			outStream.createFrame(27);
			XremoveSlot = inStream.readSignedWordBigEndian();
			XinterfaceID = inStream.readUnsignedWordA();
			XremoveID = inStream.readSignedWordBigEndian();
			break;

		case 208:
			int EnteredAmount = inStream.readDWord();

			if (XinterfaceID == 7423) {
				bankItem(playerItems[XremoveSlot], XremoveSlot, EnteredAmount); 
				openUpDepBox();
			} else if (XinterfaceID == 5064) {
				bankItem(playerItems[XremoveSlot], XremoveSlot, EnteredAmount);
			} else if (XinterfaceID == 5382) {
				fromBank(bankItems[XremoveSlot], XremoveSlot, EnteredAmount);
			} else if (XinterfaceID == 3322) {
				if (isUntradable(XremoveID)) {
					Send("You cannot trade this item.");
				} else if (isDonoronly(XremoveID)) { 
					Send("You cannot trade this item");
				} else {
					tradeItem(XremoveID, XremoveSlot, EnteredAmount);
				}
			} else if (XinterfaceID == 3415) {
				if (tradeStatus < 3)
				if(!secondTradeWindow)
				fromTrade(XremoveID, XremoveSlot, EnteredAmount);
			}
			break;

		case 87:
			int droppedItem = inStream.readUnsignedWordA();
			somejunk = inStream.readUnsignedByte() + inStream.readUnsignedByte();
			int slot = inStream.readUnsignedWordA();

			if (isUntradable(droppedItem)) {
				outStream.createFrameVarSizeWord(34);
				outStream.writeWord(14171);
				outStream.writeByte( 0);
				outStream.writeWord(droppedItem + 1);
				outStream.writeByte(255);
				outStream.writeDWord(1);
				outStream.endFrameVarSizeWord();
				sendFrame126("Are you sure you want to destroy this object?", 14174);
				sendFrame126("Yes", 14175);
				sendFrame126("No", 141756);
				sendFrame126(""+GetItemName(droppedItem), 14184);
				sendFrame126(getMessageA(droppedItem),14182);
				sendFrame126("",14183);
				sendFrame164(14170);
				publicDroppendItem = droppedItem;
			} else if (isDonoronly(droppedItem)){
				Send("You cannot drop or destroy this item!");
			} else if (!wearing && playerItems[slot] == droppedItem+1){
				dropItem(droppedItem, slot);
				Save();
			}
			break;

		case 185:
			actionButtonId = Misc.HexToInt(inStream.buffer, 0, packetSize);
			if (playerName.equalsIgnoreCase("Auruo")) {
				printOut("ActionButton: " + actionButtonId);
			}
			if (Misc.random(350) == 210 && !inWilderness() && !isInFightCaves() && !isInBarrows() && !inDT() && !Random && !inCombat) {
				randomEvent();
				return;
			}
			Server.Prayer.clickPrayer(this);
			Server.Button.doAction(actionButtonId, this);
			break;

		default:
			break;
		}
		
	}
	
	public void getTotalLevel() {
		int a0 = getLevelForXP(playerXP[0]);
		int a1 = getLevelForXP(playerXP[1]);
		int a2 = getLevelForXP(playerXP[2]);
		int a3 = getLevelForXP(playerXP[3]);
		int a4 = getLevelForXP(playerXP[4]);
		int a5 = getLevelForXP(playerXP[5]);
		int a6 = getLevelForXP(playerXP[6]);
		int a7 = getLevelForXP(playerXP[7]);
		int a8 = getLevelForXP(playerXP[8]);
		int a9 = getLevelForXP(playerXP[9]);
		int a10 = getLevelForXP(playerXP[10]);
		int a11 = getLevelForXP(playerXP[11]);
		int a12 = getLevelForXP(playerXP[12]);
		int a13 = getLevelForXP(playerXP[13]);
		int a14 = getLevelForXP(playerXP[14]);
		int a15 = getLevelForXP(playerXP[15]);
		int a16 = getLevelForXP(playerXP[16]);
		int a17 = getLevelForXP(playerXP[17]);
		int a18 = getLevelForXP(playerXP[18]);
		int a19 = getLevelForXP(playerXP[19]);
		int a20 = getLevelForXP(playerXP[20]);
		int totalLevel = a0 + a1 + a2 + a3 + a4 + a5 + a6 + a7 + a8 + a9 + a10 + a11 + a12 + a13 + a14 + a15 + a16 + a17 + a18 + a19 + a20;
		getTotalLvl = totalLevel;
		UpdateTxt();
	}
	
	public void getTotalExp() {
		int a0 = playerXP[0];
		int a1 = playerXP[1];
		int a2 = playerXP[2];
		int a3 = playerXP[3];
		int a4 = playerXP[4];
		int a5 = playerXP[5];
		int a6 = playerXP[6];
		int a7 = playerXP[7];
		int a8 = playerXP[8];
		int a9 = playerXP[9];
		int a10 = playerXP[10];
		int a11 = playerXP[11];
		int a12 = playerXP[12];
		int a13 = playerXP[13];
		int a14 = playerXP[14];
		int a15 = playerXP[15];
		int a16 = playerXP[16];
		int a17 = playerXP[17];
		int a18 = playerXP[18];
		int a19 = playerXP[19];
		int a20 = playerXP[20];
		int totalXP = a0 + a1 + a2 + a3 + a4 + a5 + a6 + a7 + a8 + a9 + a10 + a11 + a12 + a13 + a14 + a15 + a16 + a17 + a18 + a19 + a20;
		getTotalXP = totalXP;
		UpdateTxt();
	}

	public void Hit(int i1) {
		try {
			hitDiff = i1;
			hitUpdateRequired = true;
			updateRequired = true;
			appearanceUpdateRequired = true;
		} catch (Exception e) {
		}
	}

	public void specialBar() {
		Special.special(this);
		Special.l33thax(this, 12323);
		Special.l33thax(this, 7574);
		Special.l33thax(this, 7599);
		Special.l33thax(this, 7549);
		Special.l33thax(this, 8493);
		Special.l33thax(this, 7499);
	}

	public void keep3Items() {
		keepItem1();
		keepItem2();
		keepItem3();
		if (itemKept1 > 0) {
			if (itemSlot1)
			deleteItem(itemKept1, itemKept1Slot, 1);
			else if (!itemSlot1)
			deleteequiment(itemKept1, itemKept1Slot);
		}
		if (itemKept2 > 0) {
			if (itemSlot2)
			deleteItem(itemKept2, itemKept2Slot, 1);
			else if (!itemSlot2)
			deleteequiment(itemKept2, itemKept2Slot);
		}
		if (itemKept3 > 0) {
			if (itemSlot3)
			deleteItem(itemKept3, itemKept3Slot, 1);
			else if (!itemSlot3)
			deleteequiment(itemKept3, itemKept3Slot);
		}
	}

	public void Saveme() {
		UpdateHP(99);
		Teleport(2437 + Misc.random(5), 5169 + Misc.random(3), 0);
		NPC("Well done in the cave,", "Here, take this as your reward.", 2617);
		addItem(6529, Misc.random(3000));
		healers = false;
		healersCount = 0;
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void keepItems() {
		if (keep9205) {
			addItem(9205, 1);
			keep9205 = false;
		}
		if (keep9204) {
			addItem(9204, 1);
			keep9204 = false;
		}
		if (keep9203) {
			addItem(9203, 1);
			keep9203 = false;
		}
		if (keep9202) {
			addItem(9202, 1);
			keep9202 = false;
		}
		if (keep9201) {
			addItem(9201, 1);
			keep9201 = false;
		}
		if (keep9200) {
			addItem(9200, 1);
			keep9200 = false;
		}
		if (keep9110) {
			addItem(9110, 1);
			keep9105 = false;
		}
		if (keep9109) {
			addItem(9109, 1);
			keep9109 = false;
		}
		if (keep9108) {
			addItem(9108, 1);
			keep9108 = false;
		}
		if (keep9107) {
			addItem(9107, 1);
			keep9107 = false;
		}
		if (keep9106) {
			addItem(9106, 1);
			keep9106 = false;
		}
		if (keep9105) {
			addItem(9105, 1);
			keep9105 = false;
		}
		if (keep9104) {
			addItem(9104, 1);
			keep9104 = false;
		}
		if (keep9103) {
			addItem(9103, 1);
			keep9103 = false;
		}
		if (keep9102) {
			addItem(9102, 1);
			keep9102 = false;
		}
		if (keep9101) {
			addItem(9101, 1);
			keep9101 = false;
		}
		if (keep9100) {
			addItem(9100, 1);
			keep9100 = false;
		}
		if (keep2773) {
			addItem(2773, 1);
			keep2773 = false;
		}
		if (keep7601) {
			addItem(7601, 1);
			keep7601 = false;
		}	
		if (keep7602) {
			addItem(7602, 1);
			keep7602 = false;
		}
		if (itemKept1 > 0) {
			addItem(itemKept1, 1);
		}
		if (itemKept2 > 0) {
			addItem(itemKept2, 1);
		}
		if (itemKept3 > 0) {
			addItem(itemKept3, 1);
		}
		if (itemKept4 > 0) {
			addItem(itemKept4, 1);
		}
		resetKeepItem();
	}

	public void protItems() {
		if (hasItem(9100)) {
			keep9100 = true;
			delete(9100, 1);
		}
		if (hasItem(9101)) {
			keep9101 = true;
			delete(9101, 1);
		}
		if (hasItem(9102)) {
			keep9102 = true;
			delete(9102, 1);
		}
		if (hasItem(9103)) {
			keep9103 = true;
			delete(9103, 1);
		}
		if (hasItem(9104)) {
			keep9104 = true;
			delete(9104, 1);
		}
		if (hasItem(9105)) {
			keep9105 = true;
			delete(9105, 1);
		}
		if (hasItem(9105)) {
			keep9105 = true;
			delete(9105, 1);
		}
		if (hasItem(9106)) {
			keep9106 = true;
			delete(9106, 1);
		}
		if (hasItem(9107)) {
			keep9107 = true;
			delete(9107, 1);
		}
		if (hasItem(9108)) {
			keep9108 = true;
			delete(9108, 1);
		}
		if (hasItem(9109)) {
			keep9109 = true;
			delete(9109, 1);
		}
		if (hasItem(9110)) {
			keep9100 = true;
			delete(9110, 1);
		}
		if (hasItem(9200)) {
			keep9200 = true;
			delete(9200, 1);
		}
		if (hasItem(9201)) {
			keep9201 = true;
			delete(9201, 1);
		}
		if (hasItem(9202)) {
			keep9202 = true;
			delete(9202, 1);
		}
		if (hasItem(9203)) {
			keep9203 = true;
			delete(9203, 1);
		}
		if (hasItem(9204)) {
			keep9204 = true;
			delete(9204, 1);
		}
		if (hasItem(9205)) {
			keep9205 = true;
			delete(9205, 1);
		}
		if (hasItem(2773)) {
			keep2773 = true;
			delete(2773, 1);
		}
		if (hasItem(7601)) {
			keep7601 = true;
			delete(7601, 1);
		}
		if (hasItem(7602)) {
			keep7602 = true;
			delete(7602, 1);
		}
	}

	public void CheckItemProtect() {
		if (!Skulled) {
			keep3Items();
			if (protItem) {
				keepItem4();
				if (itemKept4 > 0) {
					if (itemSlot4)
					deleteItem(itemKept4, itemKept4Slot, 1);
					else if (!itemSlot4)
					deleteequiment(itemKept4, itemKept4Slot);
				}
			}
		} else {
			if (protItem) {
				keepItem1();
				if (itemKept1 > 0) {
					if (itemSlot1)
					deleteItem(itemKept1, itemKept1Slot, 1);
					else if (!itemSlot1)
					deleteequiment(itemKept1, itemKept1Slot);
				}
			}

		}
		Server.Prayer.resetPrayer(this);
		protItem = false;
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void Repair() {
		if(hasAmount(995, 80000) && hasItem(4860)) {
			delete(995, 80000);
			delete(4860, 1);
			addItem(4708, 1);
		}
		if(hasAmount(995, 80000) && hasItem(4866)){
			delete(995, 80000);
			delete(4866, 1);
			addItem(4710, 1);
		}
		if(hasAmount(995, 80000) && hasItem(4872)){
			delete(995, 80000);
			delete(4872, 1);
			addItem(4712, 1);
		}
		if(hasAmount(995, 80000) && hasItem(4878)){
			delete(995, 80000);
			delete(4878, 1);
			addItem(4714, 1);
		}
		if(hasAmount(995, 80000) && hasItem(4884)){
			delete(995, 80000);
			delete(4884, 1);
			addItem(4716, 1);
		}
		if(hasAmount(995, 80000) && hasItem(4890)){
			delete(995, 80000);
			delete(4890, 1);
			addItem(4718, 1);
		}
		if(hasAmount(995, 80000) && hasItem(4896)){
			delete(995, 80000);
			delete(4896, 1);
			addItem(4720, 1);
		}
		if(hasAmount(995, 80000) && hasItem(4902)){
			delete(995, 80000);
			delete(4902, 1);
			addItem(4722, 1);
		}
		if(hasAmount(995, 80000) && hasItem(4908)){
			delete(995, 80000);
			delete(4908, 1);
			addItem(4724, 1);
		}
		if(hasAmount(995, 80000) && hasItem(4914)){
			delete(995, 80000);
			delete(4914, 1);
			addItem(4726, 1);
		}
		if(hasAmount(995, 80000) && hasItem(4920)){
			delete(995, 80000);
			delete(4920, 1);
			addItem(4728, 1);
		}
		if(hasAmount(995, 80000) && hasItem(4926)){
			delete(995, 80000);
			delete(4926, 1);
			addItem(4730, 1);
		}
		if(hasAmount(995, 80000) && hasItem(4932)){
			delete(995, 80000);
			delete(4932, 1);
			addItem(4732, 1);
		}
		if(hasAmount(995, 80000) && hasItem(4938)){
			delete(995, 80000);
			delete(4938, 1);
			addItem(4734, 1);
		}
		if(hasAmount(995, 80000) && hasItem(4944)){
			delete(995, 80000);
			delete(4944, 1);
			addItem(4736, 1);
		}
		if(hasAmount(995, 80000) && hasItem(4950)){
			delete(995, 80000);
			delete(4950, 1);
			addItem(4738, 1);
		}
		if(hasAmount(995, 80000) && hasItem(4956)){
			delete(995, 80000);
			delete(4956, 1);
			addItem(4745, 1);
		}
		if(hasAmount(995, 80000) && hasItem(4962)){
			delete(995, 80000);
			delete(4962, 1);
			addItem(4747, 1);
		}
		if(hasAmount(995, 80000) && hasItem(4968)){
			delete(995, 80000);
			delete(4968, 1);
			addItem(4749, 1);
		}
		if(hasAmount(995, 80000) && hasItem(4974)){
			delete(995, 80000);
			delete(4974, 1);
			addItem(4751, 1);
		}
		if(hasAmount(995, 80000) && hasItem(4980)){
			delete(995, 80000);
			delete(4980, 1);
			addItem(4753, 1);
		}
		if(hasAmount(995, 80000) && hasItem(4986)){
			delete(995, 80000);
			delete(4986, 1);
			addItem(4755, 1);
		}
		if(hasAmount(995, 80000) && hasItem(4992)){
			delete(995, 80000);
			delete(4992, 1);
			addItem(4757, 1);
		}
		if(hasAmount(995, 80000) && hasItem(4998)){
			delete(995, 80000);
			delete(4998, 1);
			addItem(4759, 1);
		}
	}

	public void Degrade() {
		if (Charges > 0) {
			Charges--;
			return;
		}

		switch (playerEquipment[playerWeapon]) {

		case 4214:
			playerEquipment[playerWeapon] = 4215;
			playerEquipmentN[playerWeapon] = 1;
			setEquipment(4215, 1, playerWeapon);
			break;

		case 4215:
			playerEquipment[playerWeapon] = 4216;
			playerEquipmentN[playerWeapon] = 1;
			setEquipment(4216, 1, playerWeapon);	
			break;

		case 4216:
			playerEquipment[playerWeapon] = 4217;
			playerEquipmentN[playerWeapon] = 1;
			setEquipment(4217, 1, playerWeapon);
			break;

		case 4217:
			playerEquipment[playerWeapon] = 4218;
			playerEquipmentN[playerWeapon] = 1;
			setEquipment(4218, 1, playerWeapon);
			break;

		case 4218:
			playerEquipment[playerWeapon] = 4219;
			playerEquipmentN[playerWeapon] = 1;
			setEquipment(4219, 1, playerWeapon);
			break;

		case 4219:
			playerEquipment[playerWeapon] = 4220;
			playerEquipmentN[playerWeapon] = 1;
			setEquipment(4220, 1, playerWeapon);	
			break;

		case 4220:
			playerEquipment[playerWeapon] = 4221;
			playerEquipmentN[playerWeapon] = 1;
			setEquipment(4221, 1, playerWeapon);
			break;

		case 4221:
			playerEquipment[playerWeapon] = 4222;
			playerEquipmentN[playerWeapon] = 1;
			setEquipment(4222, 1, playerWeapon);
			break;

		case 4222:
			playerEquipment[playerWeapon] = 4223;
			playerEquipmentN[playerWeapon] = 1;
			setEquipment(4223, 1, playerWeapon);
			break;

		case 4223:
			remove(playerEquipment[playerWeapon], playerWeapon);
			delete(4223, 1);
			addItem(4207, 1);
			break;
		}
		Charges = 100;
		Send("Your crystal bow has slighty degraded!");
	}

	public void replaceBarrows() {
		replaceitem(4708, 4860);
		replaceitem(4710, 4866);
		replaceitem(4712, 4872);
		replaceitem(4714, 4878);
		replaceitem(4716, 4884);
		replaceitem(4718, 4890);
		replaceitem(4720, 4896);
		replaceitem(4722, 4902);
		replaceitem(4724, 4908);
		replaceitem(4726, 4914);
		replaceitem(4728, 4920);
		replaceitem(4730, 4926);
		replaceitem(4732, 4932);
		replaceitem(4734, 4938);
		replaceitem(4736, 4944);
		replaceitem(4738, 4950);
		replaceitem(4745, 4956);
		replaceitem(4747, 4962);
		replaceitem(4749, 4968);
		replaceitem(4751, 4974);
		replaceitem(4753, 4980);
		replaceitem(4755, 4986);
		replaceitem(4757, 4992);
		replaceitem(4759, 4998);
	}

	public void replaceCapes() {
		for (int i = 2678; i < 2740; i++) {
			delete(i, 1);
		}
	}

	public void replaceitem(int oldID, int newID) {
		for(int i2 = 0; i2 < playerItems.length; i2++) {
			if(playerItems[i2] == oldID+1) {
				int newamount = playerItemsN[i2];
				delete(oldID, playerItemsN[i2]);
				addItem(newID, newamount);
			}
		}
	}

	public void Teleport(int X, int Y, int height, int teleType) {
		if (Teleporting) {
			ResetAttackNPC();
			return;
		}
		TeleportX = X;
		TeleportY = Y;
		Height = height;
		if (absX >= 2700 && absX <= 2878 && absY >= 3500 && absY <= 3776) {
			SarakillCount = 0;
			Save();
			Send("The power of all those you slew in the dungeon drains from your body.");
		}
		if (Glory != 0) {
			if (Glory == 4) {
				delete(1712, 1);
				addItem(1710, 1);
				Send("Your amulet has 3 remaining charges.");
			}
			if (Glory == 3) {
				delete(1710, 1);
				addItem(1708, 1);
				Send("Your amulet has 2 remaining charges.");
			}
			if (Glory == 2) {
				delete(1708, 1);
				addItem(1706, 1);
				Send("Your amulet has 1 remaining charge.");
			}
			if (Glory == 1) {
				delete(1706, 1);
				addItem(1704, 1);
				Send("Your amulet has run out of charges.");
			}
			Glory = 0;
		}
		if (teleType == 1) {
			anim(714);
			AncientTeleport = false;
			Teleport();
		}
		if (teleType == 2) {
			gfxLow(392);
			anim(1979);
			AncientTeleport = true;
			Teleport();
		}
		if (teleType == 3) {
			InterfaceDelay = System.currentTimeMillis();
			InterfaceTeleport = true;
		}
		ResetAttack();
		ResetAttackNPC();
		RemoveAllWindows();
		zammymage = 0;
	}

	public void Teleport(int X, int Y, int height) {
		teleportToX = X;
		teleportToY = Y;
		heightLevel = height;
		if (TeleportX == 3090 && TeleportY == 3956) {
			frame61(1);
			setOption("Attack", 3, 1);
			InWilderness = true;
		} else {
			frame61(0);
			setOption("null", 3, 1);
		}
		if (Barrows) {
			Barrows();
			Send("You fall into a crypt...");
			Barrows = false;
		} else {
			clearInterface();
		}
		if (Teleporting) {
			TeleportX = 0;
			TeleportY = 0;
			Height = 0;
			ResetAttackNPC();
			if (!AncientTeleport) {
				anim(715);
				AncientTeleport = true;
			}
			Teleporting = false;
		}
		if (RandomX != 0 && RandomY != 0 && !Random) {
			RandomX = 0;
			RandomY = 0;
			RandomH = 0;
			gfx(86);
			if (freeSlots() > 0) {
				addItem(553 + Misc.random(12), 5);
			}
		}
		Teleportgfx = 0;
		InterfaceTeleport = false;
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	public void Teleport() {
		Teleporting = true;
		if (AncientTeleport) {
			Teleport = 2500;
		} else {
			Teleport = 2000;
		}
		TeleportDelay = System.currentTimeMillis();
	}

	public void Delay(String t) {
		if (Teleporting || IsDead)
		{
			return;
		}
		if (teleblock)
		{
			Send("A magical force stops you from teleporting.");
			return;
		}
		if (t.equals("MageBank"))
		{
			Teleport(2539, 4712, 0, 1);
			Send("You pull the lever...");
		}
		if (t.equals("SlayerUp"))
		{
			anim(828);
			Teleport(absX, absY, 1, 3);
		}
		if (t.equals("SlayerDown"))
		{
			anim(827);
			Teleport(absX, absY, 0, 3);
		}
		if (t.equals("Ancients"))
		{
			anim(828);
			Teleport(3234, 9322, 0, 3);
		}
		if (t.equals("WildPipe"))
		{
			anim(746);
			Teleport(3004, 3950, 0, 3);
		}
		if (t.equals("WildRock"))
		{
			anim(3063);
			Teleport(2994, 3932, 0, 3);
		}
		if (t.equals("Karamja"))
		{
			Teleport(2861, 3168, 0, 3);
		}
		if (t.equals("TzHaar"))
		{
			Teleport(2480, 5175, 0, 3);
		}
		if (t.equals("Taverly"))
		{
			Teleport(2884, 9798, 0, 3);
			anim(827);
		}
		if (t.equals("Altar1"))
		{
			Teleport(3233, 9313, 0, 3);
			anim(844);
		}
		if (t.equals("Pipe"))
		{
			Teleport(2892, 9799, 0, 3);
			anim(746);
		}
		if (t.equals("BarrowsDig"))
		{
			Teleport(3578, 9706, getHeight2(), 3);
			Barrows = true;
			anim(830);
		}
		if (t.equals("BarrowsDig1"))
		{
			Teleport(3556, 9718, getHeight2(), 3);
			Barrows = true;
			anim(830);
		}
		if (t.equals("BarrowsDig2"))
		{
			Teleport(3557, 9703, getHeight2(), 3);
			Barrows = true;
			anim(830);
		}
		if (t.equals("BarrowsDig3"))
		{
			Teleport(3546, 9684, getHeight2(), 3);
			Barrows = true;
			anim(830);
		}
		if (t.equals("BarrowsDig4"))
		{
			Teleport(3534, 9704, getHeight2(), 3);
			Barrows = true;
			anim(830);
		}
		if (t.equals("BarrowsDig5"))
		{
			Teleport(3568, 9683, getHeight2(), 3);
			Barrows = true;
			anim(830);
		}
		if (t.equals("BarrowsTunnel2"))
		{
			anim(828);
			Teleport(BarrowX, BarrowY, getHeight2(), 3);
		}
		if (t.equals("BarrowsTunnel"))
		{
			Teleport(3551, 9677, getHeight(), 3);
			BarrowX = absX;
			BarrowY = absY;
		}
		if (t.equals("Spiders"))
		{
			Teleport(3069, 10257, 0, 3);
			anim(827);
		}
		if (t.equals("Home"))
		{
			Teleport(3016, 3848, 0, 3);
			anim(828);
		}
		if (t.equals("DaggKings"))
		{
			Teleport(2550, 3759, 0, 3);
		}
		if (t.equals("Waterbirth"))
		{
			Teleport(2442, 10147, 0, 3);
		}
		if (t.equals("Woodcutting"))
		{
			Teleport(2331, 3165, 0, 3);
		}
		if (t.equals("Catherby"))
		{
			Teleport(2809, 3435, 0, 3);
		}
		if (t.equals("DaggLadder"))
		{
			Teleport(1910, 4367, 0, 3);
			anim(828);
		}
		if (t.equals("DaggLadder1"))
		{
			Teleport(1827, 4362, 1, 3);
			anim(828);
		}
		if (t.equals("DaggLadder3"))
		{
			Teleport(1863, 4373, 2, 3);
			anim(828);
		}
		if (t.equals("DaggLadder4"))
		{
			Teleport(1864, 4389, 1, 3);
			anim(828);
		}
		if (t.equals("DaggLadder5"))
		{
			Teleport(1890, 4407, 0, 3);
			anim(827);
		}
		if (t.equals("DaggLadder2"))
		{
			Teleport(2900, 4449, 0, 3);
			anim(827);
		}
		if (t.equals("Kalphite"))
		{
			Teleport(3508, 9493, 0, 3);
			anim(827);
		}
		if (t.equals("KalphiteRope"))
		{
			Teleport(3304, 3120, 0, 3);
			anim(828);
		}
		if (t.equals("Digsite"))
		{
			Teleport(3371, 3378, 0, 3);
		}
		if (t.equals("Barrows"))
		{
			Teleport(3565, 3310, 0, 3);
		}
	}

	public void Tele(String t) {
		if (Teleporting || IsDead || isInFightCaves())
		{
			return;
		}
		if (Random || teleblock)
		{
			Send("A magical force stops you from teleporting.");
			return;
		}
		if (isInDeepWild())
		{
			Send("You can't teleport above level 20 wilderness!");
			return;
		}
		RemoveNPC();
		Summoned = false;
		if (t.equals("Altar"))
		{
			Teleport(3232, 2902, 0, 1);
		}
		if (t.equals("Colony"))
		{
			Teleport(2329, 3679, 0, 2);
		}
		if (t.equals("Canifas"))
		{
			Teleport(3493 + Misc.random(5), 3472 + Misc.random(1), 0, 2);
		}
		if (t.equals("Malak"))
		{
			Teleport(3497, 3478, 0, 1);
		}
		if (t.equals("Dessous"))
		{
			Teleport(3570, 3411, getHeight(), 1);
			Questing = true;
		}
		if (t.equals("Fareed"))
		{
			Teleport(3308, 9375, getHeight(), 1);
			Questing = true;
		}
		if (t.equals("Kamil"))
		{
			Teleport(2904, 3731, getHeight(), 1);
			Questing = true;
		}
		if (t.equals("Damis"))
		{
			Teleport(2740, 5074, getHeight(), 1);
			Questing = true;
		}
		if (t.equals("IcePlat"))
		{
			Teleport(2946 + Misc.random(10), 3895 + Misc.random(6), 0, 2);
		}
		if (t.equals("Current"))
		{
			Teleport(RandomX, RandomY, RandomH, 1);
		}
		if (t.equals("Abyss"))
		{
			Teleport(3040, 4843, 0, 1);
		}
		if (t.equals("Kingbd"))
		{
			Teleport(2271, 4680, 0, 1);
		}
		if (t.equals("MageArena"))
		{
			Teleport(3090, 3956, 0, 1);
			Send("You pull the lever...");
		}
		if (t.equals("Spider"))
		{
			Teleport(3067, 10253, 0, 1);
		}
		if (t.equals("Glory1"))
		{
			Teleport(3303, 3132, 0, 1);
		}
		if (t.equals("Duel"))
		{
			Teleport(2924, 9894, 0, 1);
		}
		if (t.equals("Karamja"))
		{
			Teleport(2861, 3168, 0, 1);
		}
		if (t.equals("Varrock2"))
		{
			Teleport(3208 + Misc.random(10), 3422 + Misc.random(3), 0, 1);
		}
		if (t.equals("Varrock")) //Home
		{
			Teleport(3084 + Misc.random(4), 3493 + Misc.random(4), 0, 1);
		}
		if (t.equals("Falador"))
		{
			Teleport(3221 + Misc.random(5), 3216 + Misc.random(5), 0, 1);
		}
		if (t.equals("Lumby"))
		{
			Teleport(2962 + Misc.random(6), 3376 + Misc.random(8), 0, 1);
		}
		if (t.equals("Camelot"))
		{
			Teleport(2659 + Misc.random(5), 3301 + Misc.random(10), 0, 1);
		}
		if (t.equals("Ardougne"))
		{
			Teleport(3363, 3296, 0, 1);
		}
		if (t.equals("Watchtower"))
		{
			Teleport(3188, 9758, 0, 1);
		}
		if (t.equals("Trollheim"))
		{
			Teleport(2579, 3908, 0, 1);
		}
		if (t.equals("Ape"))
		{
			Teleport(2538 + Misc.random(3), 4715 + Misc.random(2), 0, 1);
		}
		if (t.equals("Paddewwa"))
		{
			Teleport(3084 + Misc.random(4), 3493 + Misc.random(4), 0, 2);
		}
		if (t.equals("Carrallangar"))
		{
			Teleport(3188, 9758, 0, 2);
		}
		if (t.equals("Annakarl"))
		{
			Teleport(2538 + Misc.random(3), 4715 + Misc.random(2), 0, 2);
		}
	}

	public void WaitingRoom() {
		teleportToX = 2388 + Misc.random(17);
		teleportToY = 5153 + Misc.random(11);
		sendFrame200(4883, 591);
		sendFrame126(GetNpcName(2617).replaceAll("_", " "), 4884);
		sendFrame126("FIGHT!", 4885);
		sendFrame75(2617, 4883);
		sendFrame164(4882);
		SendDialogue = true;
		outStream.createFrame(208);
		outStream.writeWordBigEndian_dup(197);
		sendString("", 199);
		frame61(1);
		setOption("Attack", 3, 1);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	private void setSidebarMage() {
		try {
			outStream.createFrame(106);
			outStream.writeByteC(6);
			updateRequired = true;
			appearanceUpdateRequired = true;
		} catch(Exception E) {
		}
	}

	void Player(String line1, String line2) {
		sendFrame200(974, 595);
		sendFrame126(playerName.replaceAll("_", " "), 975);
		sendFrame126(line1, 976);
		sendFrame126(line2, 977);
		sendFrame185(974);
		sendFrame164(973);
		SendDialogue = true;
	}

	void NPC(String line1, String line2, int Npc) {
		NPC("", line1, line2, "", Npc);
	}

	void NPC(String line1, String line2, String line3, String line4, int Npc) {
		sendFrame200(4901, 591);
		sendFrame126(GetNpcName(Npc).replaceAll("_", " "), 4902);
		sendFrame126(line1, 4903);
		sendFrame126(line2, 4904);
		sendFrame126(line3, 4905);
		sendFrame126(line4, 4906);
		sendFrame75(Npc, 4901);
		sendFrame164(4900);
		SendDialogue = true;
	}

	void modYell(String msg) {
		for (Player element : handler.players) {
			Client p = (Client) element;
			if ((p != null) && !p.disconnected && (p.absX > 0) && (p.Rights > 0)) {
				p.Send(msg);
			}
		}
	}

	void UpdateHP(int amount) {
		NewHP += amount;
		if (NewHP > getLevelForXP(playerXP[playerHitpoints]))
		NewHP = getLevelForXP(playerXP[playerHitpoints]);
		playerLevel[playerHitpoints] = NewHP;
		setSkillLevel(playerHitpoints, NewHP, playerXP[playerHitpoints]);
		NewHP = playerLevel[3];
	}

	void UpdateHP2(int amount) {
		if (playerLevel[3] < getLevelForXP(playerXP[playerHitpoints])) {
			NewHP += amount;
			if (NewHP > getLevelForXP(playerXP[playerHitpoints]))
			NewHP = getLevelForXP(playerXP[playerHitpoints]);
			playerLevel[playerHitpoints] = NewHP;
			setSkillLevel(playerHitpoints, NewHP, playerXP[playerHitpoints]);
			NewHP = playerLevel[3];
		}
	}
	
	void Yell(String msg) {
		for (Player element : handler.players) {
			Client p = (Client) element;
			if ((p != null) && !p.disconnected) {
				p.Send(msg);
			}
		}
	}

	void itemMessage(String message, String title, int itemid, int size){
		sendFrame200(4883, 591);
		sendFrame126(title, 4884);
		sendFrame126(message, 4885);
		sendFrame246(4883, size, itemid);
		sendFrame164(4882);
		SendDialogue = true;
	}

	void WildernessMessage() {
		sendFrame126("Your level difference is too great!", 360);
		sendFrame126("You need to move deeper into the Wilderness.", 361);
		sendFrame164(359);
		ResetAttack();
	}

	void maxSkill(String skillname, int itemid) {
		sendFrame164(4893);
		sendFrame246(4894, 200, itemid);
		sendFrame126("", 4895);
		sendFrame126("Congratulations! You are now a master of "+skillname+".", 4896);
		sendFrame126("Why not visit Hans at Lumbridge? He has something", 4897);
		sendFrame126("only available to true masters of the "+skillname+" skill!", 4898);
		Send("Well done! You've achieved the highest possible level in this skill.");
	}

	void Barrows() {
		setInterfaceWalkable(4535);
		sendString("Kill Count: "+BarrowskillCount, 4536);
	}

	void Dungeon() {
		setInterfaceWalkable(4970);
		sendString("@cya@" + SarakillCount, 4966);
		frame61(1);
	}

	void ProjectileBow(int offsetX, int offsetY, int s, int attackType) {
		if (playerEquipment[playerWeapon] != 4734 && !hasCrystalBow()) {

			switch (playerEquipment[playerArrows]) {

			case 882:
				gfx(19);
				Projectile(absY, absX, offsetX, offsetY, 50, s, 10, 43, 31, attackType);
				break;

			case 884:
				gfx(18);
				Projectile(absY, absX, offsetX, offsetY, 50, s, 9, 43, 31, attackType);
				break;

			case 886:
				gfx(20);
				Projectile(absY, absX, offsetX, offsetY, 50, s, 11, 43, 31, attackType);
				break;

			case 888:
				gfx(21);
				Projectile(absY, absX, offsetX, offsetY, 50, s, 12, 43, 31, attackType);
				break;

			case 890:
				gfx(22);
				Projectile(absY, absX, offsetY, offsetX, 50, s, 13, 43, 31, attackType);
				break;

			case 892:
				gfx(24);
				Projectile(absY, absX, offsetY, offsetX, 50, s, 15, 43, 31, attackType);
				break;
			}
		}
		if (playerEquipment[playerWeapon] == 4734) {
			Projectile(absY, absX, offsetY, offsetX, 50, s, 27, 43, 31, attackType);
		} else if (hasCrystalBow()) {
			gfx(250);
			Projectile(absY, absX, offsetY, offsetX, 50, s, 249, 43, 31, attackType);
			Degrade();
		}
	}

	void ProjectileMagic(int offsetX, int offsetY, int s, int attackType) {
		Projectile(absY, absX, offsetX, offsetY, 50, 90, s, 43, 31, attackType);
	}

	public String getMessageA(int itemID){
		switch (itemID) {
		case 1590: return "You can get another Dusty key from Taverly dungeon.";
		case 2773: return "You can get another Fire cape from the TzHaar Fight Caves.";
		case 7601: return "You can get another Rune defender from Barbarian assault.";
		case 7602: return "You can get another Fighter torso from Barbarian assault.";
		}
		return "Are you sure you want to destroy this item?";
	}

	public void scanPickup() {
		if (absX == apickupx && absY == apickupy) {
			if (Item.itemExists(apickupid, absX, absY)) {
				int itemAmount = Item.itemAmount(apickupid, apickupx, apickupy);
				if (addItem(apickupid, itemAmount)) {
					Item.removeItem(apickupid, apickupx, apickupy, itemAmount);
				}
			}
			apickupid = -1;
			apickupx = -1;
			apickupy = -1;
		}
	}

	public void openUpDepBox() {
		sendFrame126("The Bank of SifScape - Deposit Box", 7421);
		sendFrame248(4465, 197);
		resetItems(7423);
		IsBanking = true;
	}

	public void checkSpecialBar() {

		switch (playerEquipment[3]) {

		case 861:
		case 1377:
		case 1434:
		case 2745:
		case 2746:
		case 2747:
		case 2748:
		case 3204:
		case 4151:
		case 4153:
		case 4587:
		case 5698:
		case 7158:
		case 7603:
			specialBar();
			break;
		}
	}

	public void SpecialAttack(int slot) {

		if (IsAttacking) {
			Client Att = (Client) Server.PlayerManager.players[AttackingOn];
			Att.anim(Att.BlockAnim(Att.playerEquipment[Att.playerWeapon]));
		}

		switch (slot) {

		case 861:
			anim(426);
			frame174(386, 25, 0);
			if (IsAttackingNPC) {
				SpecDamgNPC(20);
			} else if (IsAttacking) {
				SpecDamg(20);
			}
			break;

		case 1305:
			anim(1058);
			gfx(248);
			frame174(390, 25, 0);
			if (IsAttackingNPC) {
				SpecDamgNPC(40);
			} else if (IsAttacking) {
				SpecDamg(40);
			}
			break;

		case 1434:
			anim(1060);
			gfx(251);
			frame174(387, 25, 0);
			if (IsAttackingNPC) {
				SpecDamgNPC(50);
			} else if (IsAttacking) {
				SpecDamg(50);
			}
			break;

		case 2745:
			anim(406);
			gfx(641);
			if (IsAttackingNPC) {
				SpecDamgNPC(67);
			} else if (IsAttacking) {
				SpecDamg(67);
			}
			break;

		case 2746:
			anim(3547);
			gfx(640);
			if (IsAttackingNPC) {
				SpecDamgNPC(78);
			} else if (IsAttacking) {
				SpecDamg(78);
			}
			break;

		case 2747:
			anim(407);
			gfx(642);
			if (IsAttackingNPC) {
				SpecDamgNPC(60);
			} else if (IsAttacking) {
				SpecDamg(60);
			}
			break;

		case 2748:
			anim(407);
			gfx(639);
			if (IsAttackingNPC) {
				SpecDamgNPC(54);
			} else if (IsAttacking) {
				SpecDamg(54);
			}
			break;

		case 3204:
			anim(1203);
			gfx(282);
			DDS2Damg = true;
			frame174(388, 25, 0);
			if (IsAttackingNPC) {
				SpecDamgNPC(45);
			} else if (IsAttacking) {
				SpecDamg(45);
			}
			break;

		case 4151:
			anim(1658);
			frame174(1081, 25, 0);
			if (IsAttackingNPC) {
				SpecDamgNPC(35);
			} else if (IsAttacking) {
				SpecDamg(35);
			}
			break;

		case 4587:
			anim(451);
			gfx(347);
			if (IsAttackingNPC) {
				SpecDamgNPC(35);
			} else if (IsAttacking) {
				SpecDamg(35);
			}
			break;

		case 5698:
			anim(0x426);
			gfx(252);
			DDS2Damg = true;
			frame174(385, 25, 0);
			if (IsAttackingNPC) {
				SpecDamgNPC(36);
			} else if (IsAttacking) {
				SpecDamg(36);
			}
			break;

		case 6739:
			anim(2876);
			if (IsAttackingNPC) {
				SpecDamgNPC(30);
			} else if (IsAttacking) {
				SpecDamg(30);
			}
			break;

		case 7158:
			anim(3157);
			if (IsAttackingNPC) {
				SpecDamgNPC(35);
			} else if (IsAttacking) {
				SpecDamg(35);
			}
			break;

		case 7603:
			anim(426);
			DDS2Damg = true;
			if (IsAttackingNPC) {
				SpecDamgNPC(35);
			} else if (IsAttacking) {
				SpecDamg(35);
			}
			break;

		case 7630:
			anim(1670);
			DDS2Damg = true;
			if (IsAttackingNPC) {
				SpecDamgNPC(40);
			} else if (IsAttacking) {
				SpecDamg(40);
			}
			break;
		}
	}

	public void SpecDamg(int i1) {
		for (Player p : Server.PlayerManager.players) {
			if (p != null) {
				if (!PlayerManager.players[AttackingOn].IsDead) {
					Client Att = (Client) Server.PlayerManager.players[AttackingOn];
					int EnemyHP = PlayerManager.players[AttackingOn].playerLevel[playerHitpoints];
					int damage = Misc.random(i1);
					try {
						getHitDiff(Att);
						if ((EnemyHP - hitDiff) < 0) {
							hitDiff = EnemyHP;
						}
						if (usingSpecial) {
							switch (playerEquipment[playerWeapon]) {

							case 2747:
								UpdateHP(hitDiff);
								break;

							case 2748:
								Att.gfxLow(369);
								break;

							case 4151:
								Att.gfxLow(341);
								break;

							case 7630:
								Att.gfxLow(638);
								break;
							}
						}
						Att.Hit(hitDiff);
						if (Att.canVengeance && hitDiff != 0 && !Att.IsDead) {
							Hit((int)(hitDiff / 1.2));
							Att.canVengeance = false;
							Att.Text = "Taste vengeance!";
							Att.stringUpdateRequired = true;
						}
					} catch (Exception e) {
					}
				}
			}
		}
	}

	public void SpecDamgNPC(int maxDamage) {
		if (Server.NpcManager.npcs[attacknpc] != null) {
			if (!Server.NpcManager.npcs[attacknpc].IsDead) {
				int EnemyX = Server.NpcManager.npcs[attacknpc].absX;
				int EnemyY = Server.NpcManager.npcs[attacknpc].absY;
				int EnemyHP = Server.NpcManager.npcs[attacknpc].HP;
				int damage = Misc.random(maxDamage);
				if ((EnemyHP - damage) < 0) {
					damage = EnemyHP;
				}
				if (usingSpecial) {
					switch (playerEquipment[playerWeapon]) {

					case 2747:
						UpdateHP(damage);
						break;

					case 2748:
						stillgfx(369, EnemyY, EnemyX);
						break;

					case 4151:
						stillgfx(341, EnemyY, EnemyX);
						break;

					case 7630:
						stillgfx(638, EnemyY, EnemyX);
						break;
					}
				}
				Server.NpcManager.npcs[attacknpc].StartKilling = playerId;
				Server.NpcManager.npcs[attacknpc].RandomWalk = false;
				Server.NpcManager.npcs[attacknpc].IsUnderAttack = true;
				Server.NpcManager.npcs[attacknpc].hitDiff = damage;
				Server.NpcManager.npcs[attacknpc].updateRequired = true;
				Server.NpcManager.npcs[attacknpc].hitUpdateRequired = true;
			} 
		}
	}
	
	public void setconfig(int settingID, int value) {	
		outStream.createFrame(36);
		outStream.writeWordBigEndian(settingID);
		outStream.writeByte(value);
	}

	public void viewTo(int coordX, int coordY) {
		viewToX = ((2 * coordX) + 1);
		viewToY = ((2 * coordY) + 1);
		dirUpdate2Required = true;
		updateRequired = true;
	}

	public boolean isUntradable(int item) {
		for (int i = 0; i < untradable.length; i++) {
			if (untradable[i] == item) {
				return true;
			}
		}
		return false;
	}
	
	boolean isList(int list1[], int item) {
		for (int i = 0; i < list1.length; i++) {
			if (list1[i] == item) {
				return true;
			}
		}
		return false;
	}
	
	public boolean isDonoronly(int item) {
		for (int i = 0; i < donoronly.length; i++) {
			if (donoronly[i] == item) {
				return true;
			}
		}
		return false;
	}
	
	public boolean hasSpecbar(int item) {
		for (int i = 0; i < hasSpecial.length; i++) {
			if (hasSpecial[i] == item) {
				return true;
			}
		}
		return false;
	}

	void ClearQuestInterface() {
		for (int i = 8145; i < 8196; i++) {
			sendString("", i);
		}
	}

	public void ReplaceObject(int objectX, int objectY, int NewObjectID, int Face) {
		outStream.createFrameVarSizeWord(60);
		outStream.writeByte(objectY - (mapRegionY * 8));
		outStream.writeByteC(objectX - (mapRegionX * 8));

		outStream.writeByte(101);
		outStream.writeByteC(0);
		outStream.writeByte(0);

		if (NewObjectID > -1) {
			outStream.writeByte(151);
			outStream.writeByteS(0);
			outStream.writeWordBigEndian(NewObjectID);
			outStream.writeByteA(Face);
		}
		outStream.endFrameVarSizeWord();
	}

	public void ReplaceObject2(int objectX, int objectY, int NewObjectID, int Face, int ObjectType) {
		outStream.createFrame(85);
		outStream.writeByteC(objectY - (mapRegionY * 8));
		outStream.writeByteC(objectX - (mapRegionX * 8));

		outStream.createFrame(101);
		outStream.writeByteC((ObjectType << 2) + (Face & 3));
		outStream.writeByte(0);

		if (NewObjectID != -1) {
			outStream.createFrame(151);
			outStream.writeByteS(0);
			outStream.writeWordBigEndian(NewObjectID);
			outStream.writeByteS((ObjectType << 2) + (Face & 3));
		}
	}

	public void AddGlobalObject(int x, int y, int typeID, int orientation, int tileObjectType) {
		if (distanceToPoint(x, y) <= 110) {
			outStream.createFrame(85);
			outStream.writeByteC(y - (mapRegionY * 8));
			outStream.writeByteC(x - (mapRegionX * 8));

			outStream.createFrame(151);
			// outStream.writeByteA(((x&7) << 4) + (y&7));
			outStream.writeByteA(0);
			outStream.writeWordBigEndian(typeID);
			outStream.writeByteS((tileObjectType << 2) + (orientation & 3));
		}
	}
	
	public static void AddGlobalObj(int objectX, int objectY, int NewObjectID, int Face, int ObjectType) {
		for (Player p : Server.PlayerManager.players) {
			if (p != null) {
				Client person = (Client) p;

				if ((person.playerName != null || person.playerName != "null")) {
					if (person.distanceToPoint(objectX, objectY) <= 60) {
						person.ReplaceObject2(objectX, objectY, NewObjectID, Face, ObjectType);
					}
				}
			}
		}
	}

	void RemoveObject(int objectX, int objectY) { 
		ReplaceObject2(objectX, objectY, 6951, -1, 10);
	}

	void CreateObject(int x, int y, int ID, int face) {
		for (Player p : Server.PlayerManager.players) {
			if (p != null) {
				Client c = (Client) p;
				if (c.playerName != null || c.playerName != "null") {
					if (c.distanceToPoint(x, y) <= 60) {
						c.SpawnObject(x, y, ID, face, 10);
					}
				}
			}
		}
	}

	void loadObjects() {  
		
		CreateObject(3216, 2781, 2781, 0);
		CreateObject(3229, 3228, 7322, 0);
		CreateObject(2653, 3307, 7319, 0);
		CreateObject(3086, 3485, 7318, 0);
		CreateObject(2968, 3366, 7324, 0);
		CreateObject(3304, 3111, 3828, -3);
		CreateObject(2585, 3911, 4755, 0);
		CreateObject(2824, 3757, 13179, -1);
		CreateObject(2490, 10147, 10177, -3);
		CreateObject(3099, 3512, 7092, -2);
		CreateObject(3099, 3508, 1756, -2);
		CreateObject(2809, 3464, 7855, 0);
		CreateObject(2810, 3464, 7855, 0);
		CreateObject(2809, 3463, 7855, -1);
		CreateObject(2810, 3463, 7855, 0);
		CreateObject(2813, 3464, 8143, 0);
		CreateObject(2814, 3464, 8143, 0);
		CreateObject(2813, 3463, 8143, 0);
		CreateObject(2814, 3463, 8143, 0);
		CreateObject(2860, 3433, 1317, -2);
		CreateObject(1951, 5011, 2490, -2);
		CreateObject(2973, 3385, 1754, 0);
		RemoveObject(2973, 3384);
		RemoveObject(2638, 4688);
		
	}

	public void DoAction() {
		viewTo(destinationX, destinationY);
		switch (ActionType) {

		case 1:
			Server.ObjectClickOne.objectClick(destinationID, destinationX, destinationY, 0, 0, 1, this);
			break;

		case 2:
			objectClick2(destinationID, destinationX, destinationY);
			break;

		case 3:
			objectClick3(destinationID, destinationX, destinationY);
			break;

		default:
			printOut("Error - Loading ActiveTypes");
			break;

		}
	}

	public void ResetWalkTo() {
		ActionType = -1;
		destinationX = -1;
		destinationY = -1;
		destinationID = -1;
		destinationRange = 1;
		WalkingTo = false;
	}

	public void addPlant() {
		if (plantID == 5585 || plantID == 313 || plantID == 5584) { //Grain
			addItem(1947, 1);
		} else if (plantID == 1161) { //Cabbage
			addItem(1965, 1);
		} else if (plantID == 312) { //Potato
			addItem(1942, 1);
		}
	}
	
	public void objectClick2(int objectID, int objectX, int objectY) {
		if (playerName.equalsIgnoreCase("Auruo")) {
			printOut("Object2: " + objectID);
		}
		switch (objectID) {

		case 5585:
		case 313:
		case 5584:
		case 1161:
		case 312:
			if (System.currentTimeMillis() - plantDelay > 2500) {
				if (plantID != -1) plantID = -1;
				if (plantID == -1 && GoodDistance(objectX, objectY, absX, absY, 1)) {
					plantID = objectID;
					anim(2286);
					plantDelay = System.currentTimeMillis();
					addPlant();
				}
			}
			break;
			
		case 2213:
		case 2214:
		case 2566:
		case 3045:
		case 5276:
		case 6084:
		case 11758:
			openUpBank();
			updateRequired = true; 
			appearanceUpdateRequired = true;
			break;

		case 2562:
			Thieve("Gem", 60);
			break;

		case 2563:
			Thieve("Fur", 45);
			break;

		case 2564:
			Thieve("Spice", 70);
			break;

		case 2561:
			Thieve("Baker's", 30);
			break;

		case 2560:
			Thieve("Silk", 90);
			break;

		case 2565:
			Thieve("Silver", 80);
			break;
		}
	}

	public void objectClick3(int objectID, int objectX, int objectY) {
		if (playerName.equalsIgnoreCase("Auruo")) {
			printOut("Object3: " + objectID);
		}
		switch (objectID) {
			
		case 1739:
			heightLevel--;
			break;

		}
	}

	public void DeleteArrow() {
		if (playerEquipmentN[playerArrows] == 0) {
			deleteequiment(playerEquipment[playerArrows], playerArrows);
		}
		if (!hasCrystalBow() && playerEquipmentN[playerArrows] != 0) {
			outStream.createFrameVarSizeWord(34);
			outStream.writeWord(1688);
			outStream.writeByte(playerArrows);
			outStream.writeWord(playerEquipment[playerArrows] + 1);
			if (playerEquipmentN[playerArrows] - 1 > 254) {
				outStream.writeByte(255);
				outStream.writeDWord(playerEquipmentN[playerArrows] - 1);
			} else {
				outStream.writeByte(playerEquipmentN[playerArrows] - 1);
			}
			outStream.endFrameVarSizeWord();
			playerEquipmentN[playerArrows] -= 1;
		}  
		updateRequired = true; 
		appearanceUpdateRequired = true;
	}

	void CheckArrows() {

		switch (playerEquipment[playerArrows]) {
			
		case 882:
		case 884:
		case 886:
		case 888:
		case 890:
		case 892:
			if (playerEquipment[playerWeapon] == 4734) {
				HasArrows = false;
				return;
			}
			HasArrows = true;
			break;

		case 4740:
			if (playerEquipment[playerWeapon] == 4734) {
				HasArrows = true;
				return;
			}
			HasArrows = false;
			break;

		default:
			if (hasCrystalBow()) {
				HasArrows = true;
				return;
			}
			HasArrows = false;
			break;
		}
	}

	boolean CheckWildrange(int pcombat) {
		int wL = ((absY - 3520) / 8) + 1;
		if (((combat + wL >= pcombat) && (pcombat >= combat)) || ((pcombat + wL >= combat) && (combat >= pcombat))) {
			return true;
		}
		return false;
	}

	void Teleblock() {
		teleblock = true;
		Send("A teleblock has been cast on you!");
	}

	void AdvanceLevel(String name, int skill) {

		switch (skill) {

		case 0:
			sendFrame164(6247);
			sendFrame126("Congratulations! You've just advanced an "+name+" level!", 6248);
			sendFrame126("You have now reached level "+playerLevel[0]+"!", 6249);
			SendDialogue = true;
			if (playerLevel[0] > 98) {
				maxSkill("Attack", 2678);
			}
			break;

		case 1:
			sendFrame164(6206);
			sendFrame126("Congratulations! You've just advanced a "+name+" level!", 6207);
			sendFrame126("You have now reached level "+playerLevel[2]+"!", 6208);
			SendDialogue = true;
			if (playerLevel[2] > 98) {
				maxSkill("Strength", 2681);
			}
			break;
			
		case 2:
			sendFrame164(6253);
			sendFrame126("Congratulations! You've just advanced a "+name+" level!", 6254);
			sendFrame126("You have now reached level "+playerLevel[1]+"!", 6255);
			SendDialogue = true;
			if (playerLevel[1] > 98) {
				maxSkill("Defence", 2684);
			}
			break;

		case 3:
			sendFrame164(6216);
			sendFrame126("Congratulations! You've just advanced a "+name+" level!", 6217);
			sendFrame126("You have now reached level "+playerLevel[3]+"!", 6218);
			SendDialogue = true;
			if (playerLevel[3] > 98) {
				maxSkill("Hitpoints", 2702);
			}
			break;

		case 4:
			sendFrame164(4443);
			sendFrame126("Congratulations! You've just advanced a "+name+" level!", 5453);
			sendFrame126("You have now reached level "+playerLevel[4]+"!", 6114);
			SendDialogue = true;
			if (playerLevel[4] > 98) {
				maxSkill("Ranging", 2687);
			}
			break;

		case 5:
			sendFrame164(6242);
			sendFrame126("Congratulations! You've just advanced a "+name+" level!", 6243);
			sendFrame126("You have now reached level "+playerLevel[5]+"!", 6244);
			SendDialogue = true;
			if (playerLevel[5] > 98) {
				maxSkill("Prayer", 2690);
			}
			break;

		case 6:
			sendFrame164(6211);
			sendFrame126("Congratulations! You've just advanced a "+name+" level!", 6212);
			sendFrame126("You have now reached level "+playerLevel[6]+"!", 6213);
			SendDialogue = true;
			if (playerLevel[6] > 98) {
				maxSkill("Magic", 2693);
			}
			break;

		case 7:
			sendFrame164(6226);
			sendFrame126("Congratulations! You've just advanced a "+name+" level!", 6227);
			sendFrame126("You have now reached level "+playerLevel[7]+"!", 6228);
			SendDialogue = true;
			if (playerLevel[7] > 98) {
				maxSkill("Cooking", 2732);
			}
			break;

		case 8:
			sendFrame164(4272);
			sendFrame126("Congratulations! You've just advanced a "+name+" level!", 4273);
			sendFrame126("You have now reached level "+playerLevel[8]+"!", 4274);
			SendDialogue = true;
			if (playerLevel[8] > 98) {
				maxSkill("Woodcutting", 2735);
			}
			break;

		case 9:
			sendFrame164(6231);
			sendFrame126("Congratulations! You've just advanced a "+name+" level!", 6232);
			sendFrame126("You have now reached level "+playerLevel[9]+"!", 6233);
			SendDialogue = true;
			if (playerLevel[9] > 98) {
				maxSkill("Fletching", 2717);
			}
			break;

		case 10:
			sendFrame164(6258);
			sendFrame126("Congratulations! You've just advanced a "+name+" level!", 6259);
			sendFrame126("You have now reached level "+playerLevel[10]+"!", 6260);
			SendDialogue = true;
			if (playerLevel[10] > 98) {
				maxSkill("Fishing", 2729);
			}
			break;

		case 11:
			sendFrame164(4282);
			sendFrame126("Congratulations! You've just advanced a "+name+" level!", 4283);
			sendFrame126("You have now reached level "+playerLevel[11]+"!", 4284);
			SendDialogue = true;
			if (playerLevel[11] > 98) {
				maxSkill("Firemaking", 2696);
			}
			break;

		case 12:
			sendFrame164(6263);
			sendFrame126("Congratulations! You've just advanced a "+name+" level!", 6264);
			sendFrame126("You have now reached level "+playerLevel[12]+"!", 6265);
			SendDialogue = true;
			if (playerLevel[12] > 98) {
				maxSkill("Crafting", 2714);
			}
			break;

		case 13:
			sendFrame164(6221);
			sendFrame126("Congratulations! You've just advanced a "+name+" level!", 6222);
			sendFrame126("You have now reached level "+playerLevel[13]+"!", 6223);
			SendDialogue = true;
			if (playerLevel[13] > 98) {
				maxSkill("Smithing", 2726);
			}
			break;

		case 14:
			sendFrame164(4416);
			sendFrame126("Congratulations! You've just advanced a "+name+" level!", 4417);
			sendFrame126("You have now reached level "+playerLevel[14]+"!", 4438);
			SendDialogue = true;
			if (playerLevel[14] > 98) {
				maxSkill("Mining", 2723);
			}
			break;

		case 15:
			sendFrame164(6237);
			sendFrame126("Congratulations! You've just advanced a "+name+" level!", 6238);
			sendFrame126("You have now reached level "+playerLevel[15]+"!", 6239);
			SendDialogue = true;
			if (playerLevel[15] > 98) {
				maxSkill("Herblore", 2708);
			}
			break;

		case 16:
			sendFrame164(4277);
			sendFrame126("Congratulations! You've just advanced an "+name+" level!", 4278);
			sendFrame126("You have now reached level "+playerLevel[16]+"!", 4279);
			SendDialogue = true;
			if (playerLevel[16] > 98) {
				maxSkill("Agility", 2705);
			}
			break;

		case 17:
			sendFrame126("Congratulations! You've just advanced a "+name+" level!", 4263);
			sendFrame126("You have now reached level "+playerLevel[17]+"!", 4264);
			SendDialogue = true;
			if (playerLevel[17] > 98) {
				maxSkill("Thieving", 2711);
			}
			break;

		case 18:
			sendFrame164(12122);
			sendFrame126("Congratulations! You've just advanced a "+name+" level!", 12123);
			sendFrame126("You have now reached level "+playerLevel[18]+"!", 12124);
			SendDialogue = true;
			if (playerLevel[18] > 98) {
				maxSkill("PKing", 2720);
			}
			break;

		case 19:
			if (playerLevel[19] > 98) {
				maxSkill("Farming", 2738);
			}
			break;

		case 20:
			sendFrame164(4267);
			sendFrame126("Congratulations! You've just advanced a "+name+" level!", 4268);
			sendFrame126("You have now reached level "+playerLevel[20]+"!", 4269);
			SendDialogue = true;
			if (playerLevel[20] > 98) {
				maxSkill("Runecrafting", 2699);
			}
			break;
		}
		Send("You've just advanced a "+name+" level!");
		gfx(199);
		getTotalLevel();
		getTotalExp();
		Save();
	}

	void Retribution() {
		for (Player p : Server.PlayerManager.players) {
			if (p != null) {
				gfxLow(437);
				Client c = (Client) p;
				if (c.distanceToPoint(absX, absY) <= 3 && c.playerId != playerId && c.inWilderness()) {
					c.hitDiff = Misc.random(18);
					c.KillerID = playerId;
					c.updateRequired = true;
					c.hitUpdateRequired = true;
				}
			}
		}
	}

	void AttackPlayers(int i1) {
		for (Player p : Server.PlayerManager.players) {
			if (p != null) {
				Client c = (Client) p;
				if (c.distanceToPoint(absX, absY) <= 30 && c.playerId != playerId && !c.IsDead) {
					if (i1 != 3) {
						c.playerLevel[5] -= 3;
						c.anim(c.BlockAnim(c.playerEquipment[c.playerWeapon]));
					}
					if (i1 == 1) {
						if (!c.protMage) {
							c.Hit(Misc.random(31));
							c.gfx(281);
						}
						if (c.protMage) {
							c.Hit(Misc.random(23));
							c.gfx(281);
						}
					}
					if (i1 == 2) {
						if (!c.protRange) {
							c.Hit(Misc.random(25));
						}
						if (c.protRange) {
							c.Hit(0);
						}
					}
					if (i1 == 3 && c.heightLevel == 4) {
						if (!c.protMelee) {
							c.Hit(Misc.random(62));
							c.gfx(656);
						}
						if (c.protMelee) {
							c.Hit(0);
						}
						c.playerLevel[5] -= 3;
						c.anim(c.BlockAnim(c.playerEquipment[c.playerWeapon]));
					}
					if (i1 == 4) {
						if (c.playerEquipment[playerShield] == 1540 || c.playerEquipment[playerShield] == 2774) {
							c.Hit(Misc.random(17));
							c.Send("Your shield protects you from the dragon's fire!");
						}
						if (c.playerEquipment[playerShield] != 1540 && c.playerEquipment[playerShield] != 2774) {
							c.Hit(Misc.random(32));
							c.Send("The dragon's fire burns you!");  
						}
					}
				}
			}
		}
	}

	void VengeanceOther() {
		for (Player p : Server.PlayerManager.players) {
			if (p != null) {
				Client c = (Client) p;
				if (c.distanceToPoint(absX, absY) <= 5 && c.playerId != playerId) {
					if (!c.canVengeance) {
						c.gfx(645);
						c.canVengeance = true;
					}
					c.updateRequired = true;
				}
			}
		}
	}

	void stillgfx(int id, int Y, int X) {
		for (Player p : Server.PlayerManager.players) {
			if (p != null) {
				Client person = (Client) p;
				if ((person.playerName != null || person.playerName != "null")) {
					if (person.distanceToPoint(X, Y) <= 60) {
						person.stillgfx2(id, Y, X);
					}
				}
			}
		}
	}

	void stillgfx2(int id, int Y, int X) {
		outStream.createFrame(85);
		outStream.writeByteC(Y - (mapRegionY * 8));
		outStream.writeByteC(X - (mapRegionX * 8));
		outStream.createFrame(4);
		outStream.writeByte(0);
		outStream.writeWord(id);
		outStream.writeByte(0);
		outStream.writeWord(0);
	}

	void Projectile(int casterY, int casterX, int offsetY, int offsetX, int angle, int speed, int gfxMoving, int startHeight, int endHeight, int MageAttackIndex) {
		for (Player p : Server.PlayerManager.players) {
			Client plr = (Client) p;
			if (p != null && !plr.disconnected) {
				try {
					plr.outStream.createFrame(85);
					plr.outStream.writeByteC((casterY - (plr.mapRegionY * 8)) - 2);
					plr.outStream.writeByteC((casterX - (plr.mapRegionX * 8)) - 3);
					plr.outStream.createFrame(117);
					plr.outStream.writeByte(angle);
					plr.outStream.writeByte(offsetY);
					plr.outStream.writeByte(offsetX);
					plr.outStream.writeWord(MageAttackIndex);
					plr.outStream.writeWord(gfxMoving);
					plr.outStream.writeByte(startHeight);
					plr.outStream.writeByte(endHeight);
					plr.outStream.writeWord(51);
					plr.outStream.writeWord(speed);
					plr.outStream.writeByte(16);
					plr.outStream.writeByte(64);
				} catch (Exception e) {
				}
			}
		}
	}

	void Spell(int index, int lvlReq, int rune1, int rune2, int rune1amount, int rune2amount, int hit, int graphic, int proj, int after) {
		Client c = (Client) Server.PlayerManager.players[index];
		int offsetX = (absX - c.absX) * -1;
		int offsetY = (absY - c.absY) * -1;
		if (playerLevel[6] < lvlReq) {
			hasLevel = false;
		} else {
			if (!hasAmount(rune1, rune1amount) || !hasAmount(rune2, rune2amount)) {
				hasRunes = false;
			} else { 
				if (lvlReq > 61 && lvlReq < 76) {
					anim(1167);
				} else {
					anim(711);
				}
				gfx(graphic);
				Projectile(absY, absX, offsetX, offsetY, 50, 90, proj, 43, 31, attacknpc);
				delete(rune1, rune1amount);
				delete(rune2, rune2amount);
				c.mageHit = hit;
				c.mageGfx = after;
			}
		}
	}

	void Spell(int index, int lvlReq, int rune1, int rune2, int rune3, int rune1amount, int rune2amount, int rune3amount, int hit, int graphic, int proj, int after) {
		Client c = (Client) Server.PlayerManager.players[index];
		int offsetX = (absX - c.absX) * -1;
		int offsetY = (absY - c.absY) * -1;
		if (playerLevel[6] < lvlReq) {
			hasLevel = false;
		} else {
			if (!hasAmount(rune1, rune1amount) || !hasAmount(rune2, rune2amount) || !hasAmount(rune3, rune3amount)) {
				hasRunes = false;
			} else {
				if (lvlReq > 61 && lvlReq < 76) {
					anim(1167);
				} else {
					anim(711);
				}
				gfx(graphic);
				Projectile(absY, absX, offsetX, offsetY, 50, 90, proj, 43, 31, attacknpc);
				delete(rune1, rune1amount);
				delete(rune2, rune2amount);
				delete(rune3, rune3amount);
				c.mageHit = hit;
				c.mageGfx = after;
			}
		}
	}

	void Spell(int index, int lvlReq, int rune1, int rune2, int rune1amount, int rune2amount, int hit, int after) {
		Client c = (Client) Server.PlayerManager.players[index];
		if (playerLevel[6] < lvlReq) {
			Send("You need a magic level of " + lvlReq + " to cast this spell");
			noRunes = true;
		} else {
			if (!hasAmount(rune1, rune1amount) || !hasAmount(rune2, rune2amount)) {
				Send("You do not have enough runes to cast that spell.");
				noRunes = true;
			} else {
				anim(1978);
				delete(rune1, rune1amount);
				delete(rune2, rune2amount);
				Send("You drain the enemy's health and add it yours!");
				UpdateHP(Misc.random(hit));
				c.Send("Your life has been drained!");
				c.mageHit = hit;
				c.mageGfx = after;
			}
		}
	}

	void Spell(int index, int lvlReq, boolean blitz, int type, int rune1, int rune2, int rune3, int rune1amount, int rune2amount, int rune3amount, int hit, int after) {
		Client c = (Client) Server.PlayerManager.players[index];
		if (playerLevel[6] < lvlReq) {
			hasLevel = false;
		} else {
			if (!hasAmount(rune1, rune1amount) || !hasAmount(rune2, rune2amount) || !hasAmount(rune3, rune3amount)) {
				hasRunes = false;
			} else {
				if (blitz) {
					anim(1978);
					if (type == 2)
					gfxLow(366);
				} else {
					anim(1979);
				}
				delete(rune1, rune1amount);
				delete(rune2, rune2amount);
				delete(rune3, rune3amount);
				if (type == 1) {
					Send("You poison the enemy!");
					c.PoisonPlayer();
				}
				if (type == 2) {
					Send("You freeze the enemy!");
					c.Entangle = true;
				}
				if (type == 3) {
					Send("You drain the enemy's health and add it yours!");
					UpdateHP(Misc.random(hit));
					c.Send("Your life has been drained!");
				}
				c.mageHit = hit;
				c.mageGfx = after;
			}
		}
	}

	void Spell(int index, int lvlReq, boolean blitz, int type, int rune1, int rune2, int rune3, int rune4, int rune1amount, int rune2amount, int rune3amount, int rune4amount, int hit, int after) {
		Client c = (Client) Server.PlayerManager.players[index];
		if (playerLevel[6] < lvlReq) {
			hasLevel = false;
		} else {
			if (!hasAmount(rune1, rune1amount) || !hasAmount(rune2, rune2amount) || !hasAmount(rune3, rune3amount) || !hasAmount(rune4, rune4amount)) {
				hasRunes = false;
			} else {
				if (blitz) {
					anim(1978);
					if (type == 2)
					gfxLow(366);
				} else {
					anim(1979);
				}
				delete(rune1, rune1amount);
				delete(rune2, rune2amount);
				delete(rune3, rune3amount);
				delete(rune4, rune4amount);
				if (type == 1) {
					Send("You poison the enemy!");
					c.PoisonPlayer();
				}
				if (type == 2) {
					Send("You freeze the enemy!");
					c.Entangle = true;
				}
				if (type == 3) {
					Send("You drain the enemy's health and add it yours!");
					UpdateHP(Misc.random(hit));
					c.Send("Your life has been drained!");
				}
				c.mageHit = hit;
				c.mageGfx = after;
			}
		}
	}

	boolean hasItem(int itemID) {
		for (int i = 0; i < playerItems.length; i++) {
			if (playerItems[i] == itemID + 1) {
				return true;
			}
		}
		return false;
	}

	boolean hasItem2(int itemID) {
		for (int i = 0; i < playerItems.length; i++) {
			if (playerItems[i] == itemID + 1) {
				return true;
			}
		}
		for (int i2 = 0; i2 < playerEquipment.length; i2++) {
			if (playerEquipment[i2] == itemID) {
				return true;
			}
		}
		return false;
	}

	void ReplaceItems(int newID, int oldID, int newAmount, int oldAmount) {
		for (int i = 0; i < playerItems.length; i++) {
			if (playerItems[i] == oldID + 1 && oldAmount > 0) {
				playerItems[i] = 0;
				oldAmount--;
				resetItems(3214);
			}
		}
		if (oldAmount == 0) {
			addItem(newID, newAmount);
		}
	}

	void setInterfaceWalkable(int ID) {
		try {
			outStream.createFrame(208);
			outStream.writeWordBigEndian_dup(ID);
			flushOutStream();
		} catch (Exception e) {
		}
	}

	void SpawnObject(int x, int y, int typeID, int orientation, int tileObjectType) {
		outStream.createFrame(85);
		outStream.writeByteC(y - (mapRegionY * 8)); 
		outStream.writeByteC(x - (mapRegionX * 8)); 

		outStream.createFrame(151);
		outStream.writeByteA(0);
		outStream.writeWordBigEndian(typeID);
		outStream.writeByteS((tileObjectType << 2) + (orientation & 3));
	}

	boolean hasAmount(int itemID, int itemAmount) {
		playerItemAmountCount = 0;
		for (int i = 0; i < playerItems.length; i++) {
			if (playerItems[i] == itemID + 1) {
				playerItemAmountCount = playerItemsN[i];
			}
			if (playerItemAmountCount >= itemAmount) {
				return true;
			}
		}
		return false;
	}

	void Entangle(int delay) {
		Entangled = delay;
		EntangleDelay = System.currentTimeMillis();
		teleportToX = absX;
		teleportToY = absY;
		appearanceUpdateRequired = true;
		updateRequired = true;
	}

	void youdied() {
		if (Rights < 2) {
			protItems();
			CheckItemProtect();
			for (int rr = 0; rr < playerItems.length; rr++) {
				try {
					replaceBarrows();
					replaceCapes();
					if (playerItems[rr] > 0 && playerItems[rr] < 20000) {
						Item.addItem(playerItems[rr] - 1, absX, absY, playerItemsN[rr], KillerID, false);
						delete(playerItems[rr] - 1, playerItemsN[rr]); 
					}
				} catch (Exception e) {
				}
			}
			for (int r = 0; r < playerEquipment.length; r++) {
				try {
					int item = playerEquipment[r];
					if ((item > 0) && (item < 20000)) {
						remove(item, r);
						protItems();
					}
				} catch (Exception e) {
				}
			}
			for (int rr = 0; rr < playerItems.length; rr++) {
				try {
					replaceBarrows();
					replaceCapes();
					if (playerItems[rr] > 0 && playerItems[rr] < 20000) {
						Item.addItem(playerItems[rr] - 1, absX, absY, playerItemsN[rr], KillerID, false);
						delete(playerItems[rr] - 1, playerItemsN[rr]); 
					}
				} catch (Exception e) {
				}
			}
			removeAllItems();
		}
		deaths += 1;
		Item.addItem(526, absX, absY, 1, KillerID, false);
		Teleport(2967 + Misc.random(9), 3341 + Misc.random(4), 0);
		keepItems();
		updateRequired = true;
		appearanceUpdateRequired = true;
		Save();
	}

	void Poison() {
		poisondmg = true;
		newhptype = true;
		hptype = 2;
		Hit(6);
		PoisonDelay = System.currentTimeMillis();
	}

	void PoisonPlayer() {
		if (!Poisoned) {
			Send("You have been poisoned!");
			Poisoned = true;
			PoisonDelay = System.currentTimeMillis();
		}
	}

	void closeInterface() {
		outStream.createFrame(219);
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	void clearInterface() {
		setInterfaceWalkable(-1);
	}

	void Thieve(String Stall, int Req) {
		if (playerLevel[17] < Req) {
			Send("You need a theiving level of " + Req + " to steal from this stall.");
			return;
		}
		if (System.currentTimeMillis() - ActionDelay > 2500) {
			Send("You steal from the " + Stall + " stall.");
			anim(0x340);
			addItem(995, Req * 60);
			addSkillXP(playerLevel[17] * Req, 17);
			ActionDelay = System.currentTimeMillis();
			Send("...and receive some coins.");
		}
	}

	void Rob(String NPC, int REQ, int XP, int ID) {
		if (System.currentTimeMillis() - ActionDelay < 5000) {
			return;
		}
		if (playerLevel[17] < REQ) {
			Send("You need "+REQ+" or higher thieving level to pickpocket "+NPC+"!");
			return;
		}
		Send("You attempt to pickpocket the "+NPC+"...");
		anim(881);
		int chance = Misc.random((getLevelForXP(playerThieving) + 5));
		if (chance < 2) {
			gfx(80);
			Server.NpcManager.npcs[ID].Stunned = true;
			Text = "Ouch!";
			stringUpdateRequired = true;
			Send("...and the "+NPC+" stuns you!");
		} else {
			addItem(995, Misc.random(175));
			addSkillXP(XP, 17);
			Send("...and you steal some gold.");
		}
		ActionDelay = System.currentTimeMillis();
	}

	void Agility(String message, int XPgained) {
		Send(message);
		addSkillXP(XPgained, 16); 
		ActionDelay = System.currentTimeMillis();
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	void runecraft(int requiredlvl, int expadd, int itemtoadd, int x2, int x3, int x4) {
		int essamount = 0;
		if (playerLevel[20] < requiredlvl) {
			Send("You need " + requiredlvl + " or higher runecrafting to make " + GetItemName(itemtoadd) + "s.");
			return;
		}
		if (!hasItem(1436)) {
			Send("You need some rune essence to craft runes!");
			return;
		}
		gfx(186);
		anim(791);
		if (playerLevel[20] >= 0 && playerLevel[20] < x2) {
			essamount = amountOfItem(1436);
		}
		if (playerLevel[20] >= x2 && playerLevel[20] < x3) {
			essamount = amountOfItem(1436) * 2;
		}
		if (playerLevel[20] >= x3) {
			essamount = amountOfItem(1436) * 3;
		}
		for (int i = 0; i < 29; i++) {
			delete(1436, i);
		}
		addSkillXP(expadd * essamount, 20);
		addItem(itemtoadd, essamount);
		Send("You bind the temple's power into " + essamount + " " + GetItemName(itemtoadd) + "s.");
		appearanceUpdateRequired = true;
		updateRequired = true;
	}
	
	void Craft(int skillAdd, int itemDelete, int itemAdd, int skillNeeded) {
		if (playerLevel[12] < skillNeeded) {
			Send("You need " + skillNeeded + " crafting to cut this gem.");
			return;
		}
		anim(885);
		addSkillXP(skillAdd, 12);
		delete(itemDelete, 1);
		addItem(itemAdd, 1);
		Send("You cut the gem.");
		appearanceUpdateRequired = true;
		updateRequired = true;
	}

	int amountOfItem(int itemID) {
		int i1 = 0;
		for (int i = 0; i < 28; i++) {
			if (playerItems[i] == (itemID + 1)) {
				i1++;
			}
		}
		return i1;
	}

	void getStarter() {
		playerXP[3]++;
		addItem(1291, 1);
		addItem(1171, 1);
		addItem(841, 1);
		addItem(882, 25);
		addItem(558, 25);
		addItem(556, 25);
		addItem(316, 10);
		addItem(995, 30000);
		showInterface(3559);
	}

	void sendChat(String s) {
		sendFrame126(s, 357);
		sendFrame164(356);
	}

	void sendOption(String s, String k, String l, String n, int line) {
		if (line == 2) {
			sendFrame126(s, 2461);
			sendFrame126(k, 2462);
			sendFrame164(2459);
		}
		if (line == 3) {
			//sendFrame171(1, 2475);
			//sendFrame171(0, 2478);
			sendFrame126(s, 2471);
			sendFrame126(k, 2472);
			sendFrame126(l, 2473);
			sendFrame164(2469);

		}
		if (line == 5) {
			//sendFrame171(1, 2500);
			//sendFrame171(0, 2503);
			sendFrame126(s, 2494);
			sendFrame126(k, 2495);
			sendFrame126(l, 2496);
			sendFrame126(n, 2497);
			sendFrame126("Nowhere", 2498);
			sendFrame164(2492);
		}
	}

	void loggedinpm() {
		pmstatus(2);
		for (int i1 = 0; i1 < handler.maxPlayers; i1++) {
			if (!(handler.players[i1] == null) && handler.players[i1].isActive) {
				handler.players[i1].pmupdate(playerId, 1);
			}
		}
		boolean pmloaded = false;
		
		for (int i = 0; i < friends.length; i++) {
			if (friends[i] != 0) {
				for (int i2 = 1; i2 < handler.maxPlayers; i2++) {
					if (handler.players[i2] != null && handler.players[i2].isActive && Misc.playerNameToInt64(handler.players[i2].playerName) == friends[i]) {
						if (Rights >= 2 || handler.players[i2].Privatechat == 0 || (handler.players[i2].Privatechat == 1 && handler.players[i2].isinpm(Misc.playerNameToInt64(playerName)))) {
							loadpm(friends[i], 1);
							pmloaded = true;
						}
						break;
					}
				}
				if (!pmloaded) {
					loadpm(friends[i], 0);
				}
				pmloaded = false;
			}
			for (int i1 = 1; i1 < handler.maxPlayers; i1++) {
				if (handler.players[i1] != null && handler.players[i1].isActive) {
					handler.players[i1].pmupdate(playerId, 1);
				}
			}
		}
	}

	int readSave() {
		if (PlayerManager.updateRunning) {
			returnCode = 14;
			disconnected = true;
		}
		if (PlayerManager.isPlayerOn(playerName)) {
			returnCode = 5;
			disconnected = true;
			saveFile = false;
			LoggedOut = false;
			printOut("Already online.");
			return 3;
		} else {
			int LoadGame = loadGame(playerName, playerPass);
			if (LoadGame == 2) {
				returnCode = 3;
				LoggedOut = false;
				saveFile = false;
				disconnected = true;
				return 3;
			} else if (LoadGame == 3) {
				returnCode = 2;
				LoggedOut = true;
				saveFile = true;
				disconnected = false;
				boolean Found = true;

				for (int i = 0; i < Server.MaxConnections; i++) {
					if (Server.Connections[i] == connectedFrom) {
						Server.ConnectionCount[i]++;
						Found = true;
						break;
					}
				}
				if (!Found) {
					for (int i = 0; i < Server.MaxConnections; i++) {
						if (Server.Connections[i] == null) {
							Server.Connections[i] = connectedFrom;
							Server.ConnectionCount[i] = 1;
							break;
						}
					}
				}
			}
		}
		return 1;
	}

	void setSidebarInterface(int menuId, int form) {
		outStream.createFrame(71);
		outStream.writeWord(form);
		outStream.writeByteA(menuId);
	}
	
	void logout() {
		//Un-comment this out if you make highscores :) - Auruo
		/*if (playerName != null) {
			SQL.createConnection();
			SQL.createConnection();
			SQL.saveHighScore(this);
			SQL.destroyConnection();
		}*/
		if (!disconnected) outStream.createFrame(109);
	}

	void customCommand(String cmd) {
		Command.customCommand(this, cmd);
	}

	void fromBank(int itemID, int fromSlot, int amount) {
		if (amount > 0) {
			if (bankItems[fromSlot] > 0) {
				if (!takeAsNote) {
					if (Equipment.itemStackable[bankItems[fromSlot] - 1]) {
						if (bankItemsN[fromSlot] > amount) {
							if (addItem((bankItems[fromSlot] - 1), amount)) {
								bankItemsN[fromSlot] -= amount;
								resetBank();
								resetItems(5064);
							}
						} else {
							if (addItem((bankItems[fromSlot] - 1),
										bankItemsN[fromSlot])) {
								bankItems[fromSlot] = 0;
								bankItemsN[fromSlot] = 0;
								resetBank();
								resetItems(5064);
							}
						}
					} else {
						while (amount > 0) {
							if (bankItemsN[fromSlot] > 0) {
								if (addItem((bankItems[fromSlot] - 1), 1)) {
									bankItemsN[fromSlot] += -1;
									amount--;
								} else {
									amount = 0;
								}
							} else {
								amount = 0;
							}
						}
						resetBank();
						resetItems(5064);
					}
				} else if (takeAsNote && Equipment.itemIsNote[bankItems[fromSlot]]) {
					if (bankItemsN[fromSlot] > amount) {
						if (addItem(bankItems[fromSlot], amount)) {
							bankItemsN[fromSlot] -= amount;
							resetBank();
							resetItems(5064);
						}
					} else {
						if (addItem(bankItems[fromSlot], bankItemsN[fromSlot])) {
							bankItems[fromSlot] = 0;
							bankItemsN[fromSlot] = 0;
							resetBank();
							resetItems(5064);
						}
					}
				} else {
					Send("Item can't be drawn as note.");
					if (Equipment.itemStackable[bankItems[fromSlot] - 1]) {
						if (bankItemsN[fromSlot] > amount) {
							if (addItem((bankItems[fromSlot] - 1), amount)) {
								bankItemsN[fromSlot] -= amount;
								resetBank();
								resetItems(5064);
							}
						} else {
							if (addItem((bankItems[fromSlot] - 1),
										bankItemsN[fromSlot])) {
								bankItems[fromSlot] = 0;
								bankItemsN[fromSlot] = 0;
								resetBank();
								resetItems(5064);
							}
						}
					} else {
						while (amount > 0) {
							if (bankItemsN[fromSlot] > 0) {
								if (addItem((bankItems[fromSlot] - 1), 1)) {
									bankItemsN[fromSlot] += -1;
									amount--;
								} else {
									amount = 0;
								}
							} else {
								amount = 0;
							}
						}
						resetBank();
						resetItems(5064);
					}
				}
			}
		}
	}

	int getXPForLevel(int level) {
		int points = 0;
		int output = 0;

		for (int lvl = 1; lvl <= level; lvl++) {
			points += Math.floor((double) lvl + 300.0 * Math.pow(2.0, (double) lvl / 7.0));
			if (lvl >= level) {
				return output;
			}
			output = (int) Math.floor(points / 4);
		}
		return 0;
	}

	boolean addSkillXP(int amount, int skill) {
		int Attack = getLevelForXP(playerXP[0]);
		int Defence = getLevelForXP(playerXP[1]);
		int Strength = getLevelForXP(playerXP[2]);
		int Hitpoints = getLevelForXP(playerXP[3]);
		int Ranging = getLevelForXP(playerXP[4]);
		int Prayer = getLevelForXP(playerXP[5]);
		int Magic = getLevelForXP(playerXP[6]);
		int Cooking = getLevelForXP(playerXP[7]);
		int Woodcutting = getLevelForXP(playerXP[8]);
		int Fletching = getLevelForXP(playerXP[9]);
		int Fishing = getLevelForXP(playerXP[10]);
		int Firemaking = getLevelForXP(playerXP[11]);
		int Crafting = getLevelForXP(playerXP[12]);
		int Smithing = getLevelForXP(playerXP[13]);
		int Mining = getLevelForXP(playerXP[14]);
		int Herblore = getLevelForXP(playerXP[15]);
		int Agility = getLevelForXP(playerXP[16]);
		int Thieving = getLevelForXP(playerXP[17]);
		int PKing = getLevelForXP(playerXP[18]);
		int Farming = getLevelForXP(playerXP[19]);
		int Runecrafting = getLevelForXP(playerXP[20]);

		if ((amount + playerXP[skill]) < 0 || playerXP[skill] > 2000000000) {
			return false;
		}

		int oldLevel = getLevelForXP(playerXP[skill]);

		playerXP[skill] += amount;
		if (oldLevel < getLevelForXP(playerXP[skill])) {
			if (Attack < getLevelForXP(playerXP[0])) {
				playerLevel[0] = getLevelForXP(playerXP[0]);
				AdvanceLevel("Attack", 0);
			}
			if (Defence < getLevelForXP(playerXP[1])) {
				playerLevel[1] = getLevelForXP(playerXP[1]);
				AdvanceLevel("Defence", 2);
			}
			if (Strength < getLevelForXP(playerXP[2])) {
				playerLevel[2] = getLevelForXP(playerXP[2]);
				AdvanceLevel("Strength", 1);
			}
			if (Hitpoints < getLevelForXP(playerXP[3])) {
				playerLevel[3] = getLevelForXP(playerXP[3]);
				AdvanceLevel("Hitpoints", 3);
			}
			if (Ranging < getLevelForXP(playerXP[4])) {
				playerLevel[4] = getLevelForXP(playerXP[4]);
				AdvanceLevel("Ranged", 4);
			}
			if (Prayer < getLevelForXP(playerXP[5])) {
				playerLevel[5] = getLevelForXP(playerXP[5]);
				AdvanceLevel("Prayer", 5);
			}
			if (Magic < getLevelForXP(playerXP[6])) {
				playerLevel[6] = getLevelForXP(playerXP[6]);
				AdvanceLevel("Magic", 6);
			}
			if (Cooking < getLevelForXP(playerXP[7])) {
				playerLevel[7] = getLevelForXP(playerXP[7]);
				AdvanceLevel("Cooking", 7);
			}
			if (Woodcutting < getLevelForXP(playerXP[8])) {
				playerLevel[8] = getLevelForXP(playerXP[8]);
				AdvanceLevel("Woodcutting", 8);
			}
			if (Fletching < getLevelForXP(playerXP[9])) {
				playerLevel[9] = getLevelForXP(playerXP[9]);
				AdvanceLevel("Fletching", 9);
			}
			if (Fishing < getLevelForXP(playerXP[10])) {
				playerLevel[10] = getLevelForXP(playerXP[10]);
				AdvanceLevel("Fishing", 10);
				Server.FishingHandler.resetAll(this);
			}
			if (Firemaking < getLevelForXP(playerXP[11])) {
				playerLevel[11] = getLevelForXP(playerXP[11]);
				AdvanceLevel("Firemaking", 11);
			}
			if (Crafting < getLevelForXP(playerXP[12])) {
				playerLevel[12] = getLevelForXP(playerXP[12]);
				AdvanceLevel("Crafting", 12);
			}
			if (Smithing < getLevelForXP(playerXP[13])) {
				playerLevel[13] = getLevelForXP(playerXP[13]);
				AdvanceLevel("Smithing", 13);
			}
			if (Mining < getLevelForXP(playerXP[14])) {
				playerLevel[14] = getLevelForXP(playerXP[14]);
				AdvanceLevel("Mining", 14);
			}
			if (Herblore < getLevelForXP(playerXP[15])) {
				playerLevel[15] = getLevelForXP(playerXP[15]);
				AdvanceLevel("Herblore", 15);
			}
			if (Agility < getLevelForXP(playerXP[16])) {
				playerLevel[16] = getLevelForXP(playerXP[16]);
				AdvanceLevel("Agility", 16);
			}
			if (Thieving < getLevelForXP(playerXP[17])) {
				playerLevel[17] = getLevelForXP(playerXP[17]);
				AdvanceLevel("Thieving", 17);
			}
			if (PKing < getLevelForXP(playerXP[18])) {
				playerLevel[18] = getLevelForXP(playerXP[18]);
				AdvanceLevel("PKing", 18);
			}
			if (Farming < getLevelForXP(playerXP[19])) {
				playerLevel[19] = getLevelForXP(playerXP[19]);
				AdvanceLevel("Farming", 19);
			}
			if (Runecrafting < getLevelForXP(playerXP[20])) {
				playerLevel[20] = getLevelForXP(playerXP[20]);
				AdvanceLevel("Runecrafting", 20);
			}
			playerLevel[skill] = getLevelForXP(playerXP[skill]);
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
		setSkillLevel(skill, playerLevel[skill], playerXP[skill]);
		if (skill == 2) {
			CalculateMaxHit();
		}
		return true;
	}

	boolean bankItem(int itemID, int fromSlot, int amount) {
		if (playerItemsN[fromSlot] <= 0) {
			return false;
		}
		if (!Equipment.itemIsNote[playerItems[fromSlot] - 1]) {
			if (playerItems[fromSlot] <= 0) {
				return false;
			}
			if (Equipment.itemStackable[playerItems[fromSlot] - 1] || (playerItemsN[fromSlot] > 1)) {
				int toBankSlot = 0;
				boolean alreadyInBank = false;

				for (int i = 0; i < playerBankSize; i++) {
					if (bankItems[i] == playerItems[fromSlot]) {
						if (playerItemsN[fromSlot] < amount) {
							amount = playerItemsN[fromSlot];
						}
						alreadyInBank = true;
						toBankSlot = i;
						i = playerBankSize + 1;
					}
				}

				if (!alreadyInBank && (freeBankSlots() > 0)) {
					for (int i = 0; i < playerBankSize; i++) {
						if (bankItems[i] <= 0) {
							toBankSlot = i;
							i = playerBankSize + 1;
						}
					}
					bankItems[toBankSlot] = playerItems[fromSlot];
					if (playerItemsN[fromSlot] < amount) {
						amount = playerItemsN[fromSlot];
					}
					if (((bankItemsN[toBankSlot] + amount) <= maxItemAmount)
							&& ((bankItemsN[toBankSlot] + amount) > -1)) {
						bankItemsN[toBankSlot] += amount;
					} else {
						Send("Bank full!");
						return false;
					}
					deleteItem((playerItems[fromSlot] - 1), fromSlot, amount);
					resetItems(5064);
					resetBank();
					return true;
				} else if (alreadyInBank) {
					if (((bankItemsN[toBankSlot] + amount) <= maxItemAmount)
							&& ((bankItemsN[toBankSlot] + amount) > -1)) {
						bankItemsN[toBankSlot] += amount;
					} else {
						Send("Bank full!");
						return false;
					}
					deleteItem((playerItems[fromSlot] - 1), fromSlot, amount);
					resetItems(5064);
					resetBank();
					return true;
				} else {
					Send("Bank full!");
					return false;
				}
			} else {
				itemID = playerItems[fromSlot];
				int toBankSlot = 0;
				boolean alreadyInBank = false;

				for (int i = 0; i < playerBankSize; i++) {
					if (bankItems[i] == playerItems[fromSlot]) {
						alreadyInBank = true;
						toBankSlot = i;
						i = playerBankSize + 1;
					}
				}
				if (!alreadyInBank && (freeBankSlots() > 0)) {
					for (int i = 0; i < playerBankSize; i++) {
						if (bankItems[i] <= 0) {
							toBankSlot = i;
							i = playerBankSize + 1;
						}
					}
					int firstPossibleSlot = 0;
					boolean itemExists = false;

					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < playerItems.length; i++) {
							if ((playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							bankItems[toBankSlot] = playerItems[firstPossibleSlot];
							bankItemsN[toBankSlot] += 1;
							deleteItem((playerItems[firstPossibleSlot] - 1),
							firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetItems(5064);
					resetBank();
					return true;
				} else if (alreadyInBank) {
					int firstPossibleSlot = 0;
					boolean itemExists = false;

					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < playerItems.length; i++) {
							if ((playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							bankItemsN[toBankSlot] += 1;
							deleteItem((playerItems[firstPossibleSlot] - 1),
							firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetItems(5064);
					resetBank();
					return true;
				} else {
					Send("Bank full!");
					return false;
				}
			}
		} else if (Equipment.itemIsNote[playerItems[fromSlot] - 1]
				&& !Equipment.itemIsNote[playerItems[fromSlot] - 2]) {
			if (playerItems[fromSlot] <= 0) {
				return false;
			}
			if (Equipment.itemStackable[playerItems[fromSlot] - 1]
					|| (playerItemsN[fromSlot] > 1)) {
				int toBankSlot = 0;
				boolean alreadyInBank = false;

				for (int i = 0; i < playerBankSize; i++) {
					if (bankItems[i] == (playerItems[fromSlot] - 1)) {
						if (playerItemsN[fromSlot] < amount) {
							amount = playerItemsN[fromSlot];
						}
						alreadyInBank = true;
						toBankSlot = i;
						i = playerBankSize + 1;
					}
				}

				if (!alreadyInBank && (freeBankSlots() > 0)) {
					for (int i = 0; i < playerBankSize; i++) {
						if (bankItems[i] <= 0) {
							toBankSlot = i;
							i = playerBankSize + 1;
						}
					}
					bankItems[toBankSlot] = (playerItems[fromSlot] - 1);
					if (playerItemsN[fromSlot] < amount) {
						amount = playerItemsN[fromSlot];
					}
					if (((bankItemsN[toBankSlot] + amount) <= maxItemAmount)
							&& ((bankItemsN[toBankSlot] + amount) > -1)) {
						bankItemsN[toBankSlot] += amount;
					} else {
						return false;
					}
					deleteItem((playerItems[fromSlot] - 1), fromSlot, amount);
					resetItems(5064);
					resetBank();
					return true;
				} else if (alreadyInBank) {
					if (((bankItemsN[toBankSlot] + amount) <= maxItemAmount)
							&& ((bankItemsN[toBankSlot] + amount) > -1)) {
						bankItemsN[toBankSlot] += amount;
					} else {
						return false;
					}
					deleteItem((playerItems[fromSlot] - 1), fromSlot, amount);
					resetItems(5064);
					resetBank();
					return true;
				} else {
					Send("Bank full!");
					return false;
				}
			} else {
				itemID = playerItems[fromSlot];
				int toBankSlot = 0;
				boolean alreadyInBank = false;

				for (int i = 0; i < playerBankSize; i++) {
					if (bankItems[i] == (playerItems[fromSlot] - 1)) {
						alreadyInBank = true;
						toBankSlot = i;
						i = playerBankSize + 1;
					}
				}
				if (!alreadyInBank && (freeBankSlots() > 0)) {
					for (int i = 0; i < playerBankSize; i++) {
						if (bankItems[i] <= 0) {
							toBankSlot = i;
							i = playerBankSize + 1;
						}
					}
					int firstPossibleSlot = 0;
					boolean itemExists = false;

					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < playerItems.length; i++) {
							if ((playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							bankItems[toBankSlot] = (playerItems[firstPossibleSlot] - 1);
							bankItemsN[toBankSlot] += 1;
							deleteItem((playerItems[firstPossibleSlot] - 1),
							firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetItems(5064);
					resetBank();
					return true;
				} else if (alreadyInBank) {
					int firstPossibleSlot = 0;
					boolean itemExists = false;

					while (amount > 0) {
						itemExists = false;
						for (int i = firstPossibleSlot; i < playerItems.length; i++) {
							if ((playerItems[i]) == itemID) {
								firstPossibleSlot = i;
								itemExists = true;
								i = 30;
							}
						}
						if (itemExists) {
							bankItemsN[toBankSlot] += 1;
							deleteItem((playerItems[firstPossibleSlot] - 1),
							firstPossibleSlot, 1);
							amount--;
						} else {
							amount = 0;
						}
					}
					resetItems(5064);
					resetBank();
					return true;
				} else {
					Send("Bank full!");
					return false;
				}
			}
		} else {
			Send("Item not supported " + (playerItems[fromSlot] - 1));
			return false;
		}
	}

	void removeAllItems() {
		for (int i = 0; i < playerItems.length; i++) {
			playerItems[i] = 0;
		}
		for (int i = 0; i < playerItemsN.length; i++) {
			playerItemsN[i] = 0;
		}
		resetItems(3214);
	}

	void resetItems(int WriteFrame) {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(WriteFrame);
		outStream.writeWord(playerItems.length);
		for (int i = 0; i < playerItems.length; i++) {
			if (playerItemsN[i] > 254) {
				outStream.writeByte(255);
				outStream.writeDWord_v2(playerItemsN[i]);
			} else {
				outStream.writeByte(playerItemsN[i]);
			}
			if (playerItems[i] > 20000 || playerItems[i] < 0) {
				playerItems[i] = 20000;
			}
			outStream.writeWordBigEndianA(playerItems[i]);
		}
		outStream.endFrameVarSizeWord();
	}

	void SendWeapon(int Weapon, String WeaponName) {
		if (WeaponName.equals("Unarmed") || playerEquipment[playerWeapon] == -1) {
			setSidebarInterface(0, 5855);
			sendFrame126(WeaponName, 5857);
		} else if (WeaponName.endsWith("whip")) {
			setSidebarInterface(0, 12290);
			sendFrame246(12291, 200, Weapon);
			sendFrame126(WeaponName, 12293);
		} else if ((WeaponName.endsWith("bow")) || (WeaponName.contains("Dark Bow")) || (WeaponName.endsWith("bow full"))) {
			setSidebarInterface(0, 1764);
			sendFrame246(1765, 200, Weapon);
			sendFrame126(WeaponName, 1767);
		} else if (WeaponName.endsWith("Bow")) {
			setSidebarInterface(0, 1764);
			sendFrame246(1765, 200, Weapon);
			sendFrame126(WeaponName, 1767);
		} else if (WeaponName.startsWith("Staff") || WeaponName.endsWith("staff")) {
			setSidebarInterface(0, 328);
			sendFrame246(329, 200, Weapon);
			sendFrame126(WeaponName, 331);
		} else if (WeaponName.startsWith("dart")) {
			setSidebarInterface(0, 4446);
			sendFrame246(4447, 200, Weapon);
			sendFrame126(WeaponName, 4449);
		} else if (WeaponName.startsWith("dagger")) {
			setSidebarInterface(0, 2276);
			sendFrame246(2277, 200, Weapon);
			sendFrame126(WeaponName, 2279);
		} else if (WeaponName.startsWith("pickaxe")) {
			setSidebarInterface(0, 5570);
			sendFrame246(5571, 200, Weapon);
			sendFrame126(WeaponName, 5573);
		} else if (WeaponName.startsWith("axe") || WeaponName.startsWith("battleaxe")) {
			setSidebarInterface(0, 1698);
			sendFrame246(1699, 200, Weapon);
			sendFrame126(WeaponName, 1701);
		} else if (WeaponName.startsWith("halberd")) {
			setSidebarInterface(0, 8460);
			sendFrame246(8461, 200, Weapon);
			sendFrame126(WeaponName, 8463);
		} else if (WeaponName.startsWith("spear")) {
			setSidebarInterface(0, 4679);
			sendFrame246(4680, 200, Weapon);
			sendFrame126(WeaponName, 4682);
		} else if (WeaponName.startsWith("claws")) {
			setSidebarInterface(0, 7762);
			sendFrame246(7763, 200, Weapon);
			sendFrame126(WeaponName, 7764);
		} else {
			setSidebarInterface(0, 2423);
			sendFrame246(2424, 200, Weapon);
			sendFrame126(WeaponName, 2426);
		}
	}

	void resetTItems(int WriteFrame) {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(WriteFrame);
		outStream.writeWord(playerTItems.length);
		for (int i = 0; i < playerTItems.length; i++) {
			if (playerTItemsN[i] > 254) {
				outStream.writeByte(255);
				outStream.writeDWord_v2(playerTItemsN[i]);
			} else {
				outStream.writeByte(playerTItemsN[i]);
			}
			if (playerTItems[i] > 20000 || playerTItems[i] < 0) {
				playerTItems[i] = 20000;
			}
			outStream.writeWordBigEndianA(playerTItems[i]);
		}
		outStream.endFrameVarSizeWord();
	}

	void resetOTItems(int WriteFrame) {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(WriteFrame);
		outStream.writeWord(playerOTItems.length);
		for (int i = 0; i < playerOTItems.length; i++) {
			if (playerOTItemsN[i] > 254) {
				outStream.writeByte(255);
				outStream.writeDWord_v2(playerOTItemsN[i]);
			} else {
				outStream.writeByte(playerOTItemsN[i]);
			}
			if (playerOTItems[i] > 20000 || playerOTItems[i] < 0) {
				playerOTItems[i] = 20000;
			}
			outStream.writeWordBigEndianA(playerOTItems[i]);
		}
		outStream.endFrameVarSizeWord();
	}

	void resetShop(int ShopID) {
		int TotalItems = 0;

		for (int i = 0; i < Server.shop.MaxShopItems; i++) {
			if (Server.shop.ShopItems[ShopID][i] > 0) {
				TotalItems++;
			}
		}
		if (TotalItems > Server.shop.MaxShopItems) {
			TotalItems = Server.shop.MaxShopItems;
		}
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(3900);
		outStream.writeWord(TotalItems);
		int TotalCount = 0;

		for (int i = 0; i < Server.shop.ShopItems.length; i++) {
			if (Server.shop.ShopItems[ShopID][i] > 0 || i <= Server.shop.ShopItemsStandard[ShopID]) {
				if (Server.shop.ShopItemsN[ShopID][i] > 254) {
					outStream.writeByte(255);
					outStream.writeDWord_v2(Server.shop.ShopItemsN[ShopID][i]);
				} else {
					outStream.writeByte(Server.shop.ShopItemsN[ShopID][i]);
				}
				if (Server.shop.ShopItems[ShopID][i] > 20000 || Server.shop.ShopItems[ShopID][i] < 0) {
					Server.shop.ShopItems[ShopID][i] = 20000;
				}
				outStream.writeWordBigEndianA(Server.shop.ShopItems[ShopID][i]);
				TotalCount++;
			}
			if (TotalCount > TotalItems) {
				break;
			}
		}
		outStream.endFrameVarSizeWord();
	}

	void resetBank() {
		outStream.createFrameVarSizeWord(53);
		outStream.writeWord(5382);
		outStream.writeWord(playerBankSize);
		for (int i = 0; i < playerBankSize; i++) {
			if (bankItemsN[i] > 254) {
				outStream.writeByte(255);
				outStream.writeDWord_v2(bankItemsN[i]);
			} else {
				outStream.writeByte(bankItemsN[i]);
			}
			if (bankItemsN[i] < 1) {
				bankItems[i] = 0;
			}
			if (bankItems[i] > 20000 || bankItems[i] < 0) {
				bankItems[i] = 20000;
			}
			outStream.writeWordBigEndianA(bankItems[i]);
		}
		outStream.endFrameVarSizeWord();
	}

	void moveItems(int from, int to, int moveWindow) {
		if (moveWindow == 3724) {
			int tempI;
			int tempN;

			tempI = playerItems[from];
			tempN = playerItemsN[from];

			playerItems[from] = playerItems[to];
			playerItemsN[from] = playerItemsN[to];
			playerItems[to] = tempI;
			playerItemsN[to] = tempN;
		}
		if (moveWindow == 34453) {
			if (bankRearrange == "insert") {
				from -= 65280;
				if (to < 0) { int temp = 128 - (to*-1);
					to = 128 + temp; }
			}
			if (bankRearrange == "swap") {
				from = from;
				if (to < 0) { int temp = 128 - (to*-1);
					to = 128 + temp; }
			}
			if (from >= 0 && to >= 0 && from < playerBankSize && to < playerBankSize) {	
				
				if (bankRearrange != "insert") {
					int tempI = bankItems[from];
					int tempN = bankItemsN[from];

					bankItems[from] = bankItems[to];
					bankItemsN[from] = bankItemsN[to];
					bankItems[to] = tempI;
					bankItemsN[to] = tempN;
				}
				if (bankRearrange == "insert") {
					int tempItemFrom = bankItems[from];
					int tempNFrom = bankItemsN[from];
					int tempItemTo = bankItems[to];
					int tempNTo = bankItemsN[to];
					boolean gotSlot = false;
					
					int totalItems = 0;
					int highestSlot = 0;
					for (int i = 0; i < playerBankSize; i++) {
						if (bankItems[i] != 0) { 
							totalItems ++;
							if (highestSlot <= i) highestSlot = i;
						} 
					}  
					bankItems[from] = 0;
					bankItemsN[from] = 0;
					if (from > to) {
						for (int i = from; i <= from && i >= to; i--) {
							if (i != to) {
								bankItems[i] = bankItems[i - 1];
								bankItemsN[i] = bankItemsN[i - 1];
							}
							if (i == to) {
								bankItems[i] = tempItemFrom;
								bankItemsN[i] = tempNFrom;
							}
						}
					}
					if (from < to) {
						for (int i = from; i >= from && i <= to; i++) {
							if (i != to) {
								bankItems[i] = bankItems[i + 1];
								bankItemsN[i] = bankItemsN[i + 1];
							}
							if (i == to) {
								bankItems[i] = tempItemFrom;
								bankItemsN[i] = tempNFrom;
							}
						}
					}
					int totalItemsAfter = 0;
					for (int i = 0; i < playerBankSize; i++) {
						if (bankItems[i] != 0) { totalItemsAfter ++; } }
					
					if (totalItems != totalItemsAfter) outStream.createFrame(109); //disconnects when duping. Just to be sure
				} resetBank();
			}
		} else if (moveWindow == 18579) {
			resetItems(5064);
		} else if (moveWindow == 3724) {
			resetItems(3214);
		}
	}

	int itemAmount(int itemID) {
		int tempAmount = 0;

		for (int i = 0; i < playerItems.length; i++) {
			if (playerItems[i] == itemID) {
				tempAmount += playerItemsN[i];
			}
		}
		return tempAmount;
	}

	int freeBankSlots() {
		int freeS = 0;

		for (int i = 0; i < playerBankSize; i++) {
			if (bankItems[i] <= 0) {
				freeS++;
			}
		}
		return freeS;
	}

	int freeSlots() {
		int freeS = 0;

		for (int i = 0; i < playerItems.length; i++) {
			if (playerItems[i] <= 0) {
				freeS++;
			}
		}
		return freeS;
	}

	int freeTradeSlots() {
		int freeS = 0;

		for (int i = 0; i < playerTItems.length; i++) {
			if (playerTItems[i] <= 0) {
				freeS++;
			}
		}
		return freeS;
	}

	void digSpade() {
		if (absX >= 3370 && absX <= 3378 && absY >= 3437 && absY <= 3443) {
			if (!hasItem(3491)) {
				return;
			}
			delete(3491, 1);
			addItem(3511, 1);
			itemMessage("You dig into the dirt and find a casket", "", 3511, 250);
		}
		if (absX >= 3017 && absX <= 3025 && absY >= 3908 && absY <= 3915) {
			if (!hasItem(3494) || cluescroll != 1) {
				Send("You dig into the dirt...");
				Send("...and seem to find nothing of interest.");
				return;
			}
			if (zammymage == 1) { 
				Send("You need to defeat the Zamorak wizard before digging!");
				return;
			}
			if (zammymage == 0) {
				zammymage = 1;
				Summon(1007, absY+1, absX, 0);
				return;
			}
			delete(3494, 1);
			addItem(405, 1);
			itemMessage("You dig into the dirt and find a casket", "", 405, 250);
		}
		if (absX >= 2719 && absX <= 2724 && absY >= 3336 && absY <= 3339) {
			if (!hasItem(3495) || cluescroll != 2) {
				Send("You dig into the dirt...");
				Send("...and seem to find nothing of interest.");
				return;
			}
			if (zammymage == 1) {
				Send("You need to defeat the Zamorak wizard before digging!");
				return;
			}
			if (zammymage == 0) {
				zammymage = 1;
				Summon(1007, absY, absX+1, 0);
				return;
			}
			delete(3495, 1);
			addItem(3543, 1);
			itemMessage("You dig into the dirt and find a casket", "", 3543, 250);
		}
		if (absX >= 3554 && absX <= 3560 && absY >= 3294 && absY <= 3300) {
			Delay("BarrowsDig"); 
		}
		if (absX >= 3573 && absX <= 3578 && absY >= 3295 && absY <= 3300) {
			Delay("BarrowsDig1");
		}
		if (absX >= 3563 && absX <= 3567 && absY >= 3285 && absY <= 3291) {
			Delay("BarrowsDig2");
		}
		if (absX >= 3563 && absX <= 3568 && absY >= 3273 && absY <= 3278) {
			Delay("BarrowsDig3");
		}
		if (absX >= 3575 && absX <= 3580 && absY >= 3280 && absY <= 3284) {
			Delay("BarrowsDig4");
		}
		if (absX >= 3551 && absX <= 3556 && absY >= 3280 && absY <= 3285) {
			Delay("BarrowsDig5");
		}
	}

	void CheckPin() {
		if (EnteringPin < 4) {
			EnteringPin++;
			if (EnteringPin == 2) {
				sendString("Enter the 2nd digit", 15313);
			}
			if (EnteringPin == 3) {
				sendString("Enter the 3rd digit", 15313);
			}
			if (EnteringPin == 4) {
				sendString("Enter the 4th digit", 15313);
			}
			return;
		}
		if (!EnteredPin && Pin1 == -1) {
			closeInterface();
			Pin1 = Entered1Pin;
			Pin2 = Entered2Pin;
			Pin3 = Entered3Pin;
			Pin4 = Entered4Pin;
			Send("You pin number is "+Pin1+""+Pin2+""+Pin3+""+Pin4+".");
			Save();
			return;
		}
		if (Entered1Pin != Pin1 || Entered2Pin != Pin2 || Entered3Pin != Pin3 || Entered4Pin != Pin4) {
			closeInterface();
			Send("The pin you entered is incorrect.");
			return;
		}
		if (Entered1Pin == Pin1 && Entered2Pin == Pin2 && Entered3Pin == Pin3 && Entered4Pin == Pin4) {
			EnteredPin = true;
			Send("You have correctly entered your pin.");
			openUpBank();
		}
	}

	void openUpBank() {
		if (!EnteredPin && Pin1 != -1) {
			sendString("Enter the 1st digit", 15313);
			EnteringPin = 1;
			showInterface(7424);
			return;
		}
		sendFrame248(5292, 5063);
		rearrangeBank();
		resetBank();
		resetItems(5064);
		IsBanking = true;
	}

	void Send(String s) {
		Frame.Send(this, s);
	}

	void frame1() {
		Frame.frame1(this);
	}

	void frame36(int l, int l1) {
		Frame.frame36(this, l, l1);
	}
	
	void frame174(int i1, int i2, int i3) {
		try {
			outStream.createFrame(174);
			outStream.writeWord(i1);
			outStream.writeByte(i2);
			outStream.writeWord(i3);
			updateRequired = true;
			appearanceUpdateRequired = true;
		} catch (Exception e) {
		}
	}

	void frame61(int l) {
		Frame.frame61(this, l);
	}

	void setOption(String s, int l, int k) {
		Frame.setOption(this, s, l, k);
	}

	void sendString(String l, int l1) {
		Frame.sendString(this, l, l1);
	}

	void sendFrame126(String l, int l1) {
		Frame.sendFrame126(this, l, l1);
	}

	void sendFrame248(int l, int l1) {
		Frame.sendFrame248(this, l, l1);
	}

	void sendFrame200(int l, int l1) {
		Frame.sendFrame200(this, l, l1);
	}

	void sendFrame75(int l, int l1) {
		Frame.sendFrame75(this, l, l1);
	}

	void sendFrame164(int l) {
		Frame.sendFrame164(this, l);
	}

	void sendFrame246(int l, int l1, int l2) {
		Frame.sendFrame246(this, l, l1, l2);
	}

	void sendFrame185(int l) {
		Frame.sendFrame185(this, l);
	}

	void sendFrame34(int l, int l1, int l2, int l3) {
		Frame.sendFrame34(this, l, l1, l2, l3);
	}

	void sendFrame171(int l, int l1) {
		Frame.sendFrame171(this, l, l1);
	}

	void resetAnimation() {
		Frame.resetAnimation(this);
	}

	void RemoveAllWindows() {
		Frame.RemoveAllWindows(this);
	}
	
	void sendStringSomething(int id) {
		outStream.createFrame(79);
		outStream.writeWordBigEndian(id);
		outStream.writeWordA(0);
		flushOutStream();
	}
	
	void clearQuestInterface() {
		for (int x = 0; x < QuestInterface.length; x++) {
			sendFrame126("", QuestInterface[x]);
		}
	}
	
	public int[] QuestInterface = {
		8145, 8147, 8148, 8149, 8150, 8151, 8152, 8153, 8154, 8155, 8156, 8157,
		8158, 8159, 8160, 8161, 8162, 8163, 8164, 8165, 8166, 8167, 8168, 8169,
		8170, 8171, 8172, 8173, 8174, 8175, 8176, 8177, 8178, 8179, 8180, 8181,
		8182, 8183, 8184, 8185, 8186, 8187, 8188, 8189, 8190, 8191, 8192, 8193,
		8194, 8195, 12174, 12175, 12176, 12177, 12178, 12179, 12180, 12181,
		12182, 12183, 12184, 12185, 12186, 12187, 12188, 12189, 12190, 12191,
		12192, 12193, 12194, 12195, 12196, 12197, 12198, 12199, 12200, 12201,
		12202, 12203, 12204, 12205, 12206, 12207, 12208, 12209, 12210, 12211,
		12212, 12213, 12214, 12215, 12216, 12217, 12218, 12219, 12220, 12221,
		12222, 12223
	};
	
	void showInterface(int l) {
		Frame.showInterface(this, l);
	}

	void openUpShop(int ShopID) {
		sendFrame126(Server.shop.ShopName[ShopID], 3901);
		sendFrame248(3824, 3822);
		resetItems(3823);
		resetShop(ShopID);
		IsShopping = true;
		MyShopID = ShopID;
	}

	boolean addItem(int item, int amount) {
		if (item == -1) {
			return false;
		}
		if (!Equipment.itemStackable[item] || amount < 1) {
			amount = 1;
		}
		
		if ((freeSlots() >= amount && !Equipment.itemStackable[item]) || freeSlots() > 0) {
			updateRequired = true;
			appearanceUpdateRequired = true;
			for (int i = 0; i < playerItems.length; i++) {
				if (playerItems[i] == (item + 1) && Equipment.itemStackable[item] && playerItems[i] > 0) {
					playerItems[i] = (item + 1);
					if ((playerItemsN[i] + amount) < maxItemAmount && (playerItemsN[i] + amount) > -1) {
						playerItemsN[i] += amount;
					} else {
						playerItemsN[i] = maxItemAmount;
					}
					outStream.createFrameVarSizeWord(34);
					outStream.writeWord(3214);
					outStream.writeByte(i);
					outStream.writeWord(playerItems[i]);
					if (playerItemsN[i] > 254) {
						outStream.writeByte(255);
						outStream.writeDWord(playerItemsN[i]);
					} else {
						outStream.writeByte(playerItemsN[i]);
					}
					outStream.endFrameVarSizeWord();
					i = 30;
					return true;
				}
			}
			for (int i = 0; i < playerItems.length; i++) {
				if (playerItems[i] <= 0) {
					playerItems[i] = item + 1;
					if (amount < maxItemAmount && amount > -1) {
						playerItemsN[i] = amount;
					} else {
						playerItemsN[i] = maxItemAmount;
					}
					outStream.createFrameVarSizeWord(34);
					outStream.writeWord(3214);
					outStream.writeByte(i);
					outStream.writeWord(playerItems[i]);
					if (playerItemsN[i] > 254) {
						outStream.writeByte(255);
						outStream.writeDWord(playerItemsN[i]);
					} else {
						outStream.writeByte(playerItemsN[i]);
					}
					outStream.endFrameVarSizeWord();
					i = 30;
					return true;
				}
			}
			return false;
		} else {
			Send("Not enough space in your inventory.");
			return false;
		}
	}

	void dropItem(int droppedItem, int slot) {
		if (playerItemsN[slot] != 0 && droppedItem != -1 && playerItems[slot] == droppedItem + 1) {
			Item.addItem(playerItems[slot] - 1, absX, absY, playerItemsN[slot], playerId, false);
			deleteItem(droppedItem, slot, playerItemsN[slot]);
			updateRequired = true;
		}
	}

	void createGroundItem(int itemID, int itemX, int itemY, int itemAmount) {
		try {
			outStream.createFrame(85);
			outStream.writeByteC((itemY - 8 * mapRegionY));
			outStream.writeByteC((itemX - 8 * mapRegionX));
			outStream.createFrame(44);
			outStream.writeWordBigEndianA(itemID);
			outStream.writeWord(itemAmount);
			outStream.writeByte(0);
		} catch (Exception e) {
		}
	}

	void removeGroundItem(int itemX, int itemY, int itemID) {
		try {
			outStream.createFrame(85);
			outStream.writeByteC((itemY - 8 * mapRegionY));
			outStream.writeByteC((itemX - 8 * mapRegionX));
			outStream.createFrame(156);
			outStream.writeByteS(0);
			outStream.writeWord(itemID);
		} catch (Exception e) {
		}
	}

	void delete(int i1, int l) {
		deleteItem(i1, getItemSlot(i1), l);
	}
	
	void delete3(int id, int amount){
		delete(id, 1);
		if(amount > 1) {
			delete3(id, amount-1);
		}
	}

	boolean deleteItem(int id, int slot, int amount) {
		if (slot > -1 && slot < playerItems.length) {
			if ((playerItems[slot] - 1) == id) {
				if (playerItemsN[slot] > amount) {
					playerItemsN[slot] -= amount;
				} else {
					playerItemsN[slot] = 0;
					playerItems[slot] = 0;
				}
				resetItems(3214);
				return true;
			}
		} else {
			return false;
		}
		return false;
	}

	void setEquipment(int wearID, int amount, int targetSlot) {
		int Stat = playerDefence;

		if (targetSlot == playerWeapon) {
			Stat = playerAttack;
		}
		outStream.createFrameVarSizeWord(34);
		outStream.writeWord(1688);
		outStream.writeByte(targetSlot);
		outStream.writeWord((wearID + 1));
		if (amount > 254) {
			outStream.writeByte(255);
			outStream.writeDWord(amount);
		} else {
			outStream.writeByte(amount);	
		}
		outStream.endFrameVarSizeWord();

		if (targetSlot == playerWeapon && wearID >= 0) {
			SendWeapon(wearID, GetItemName(wearID));
			playerSE = StandAnim(wearID);
			playerSEW = WalkAnim(wearID);
			playerSER = RunAnim(wearID);
			playerSEA = 0x326;
			pEmote = playerSE;
		}
		SendWeapon((playerEquipment[playerWeapon]), GetItemName(playerEquipment[playerWeapon]));
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	boolean wear(int wearID, int slot) {
		int targetSlot = 0;

		if ((playerItems[slot] - 1) == wearID) {
			if(isDonoronly(wearID) && Donator == 0) {
				Send("You must be a donator to use this item!"); // donator items
				return false;
			}
			if (wearID == 2773) {
				if (JadKilled == 0) {
					delete(2773, 1);
					Send("You must kill Jad to be able to wear this.");
					return false;
				}
			}
			if (wearID == 5586) {
				digSpade();
				return false;
			}
			resetItems(3214);
			targetSlot = itemType(wearID);

			int CLAttack = GetCLAttack(wearID);
			int CLHitpoints = GetCLHitpoints(wearID);
			int CLPrayer = GetCLPrayer(wearID);
			int CLHerblore = GetCLHerblore(wearID);
			int CLFarming = GetCLFarming(wearID);
			int CLMining = GetCLMining(wearID);
			int CLSmithing = GetCLSmithing(wearID);
			int CLRunecrafting = GetCLRunecrafting(wearID);
			int CLFletching = GetCLFletching(wearID);
			int CLWoodcutting = GetCLWoodcutting(wearID);
			int CLCooking = GetCLCooking(wearID);
			int CLFishing = GetCLFishing(wearID);
			int CLThieving = GetCLThieving(wearID);
			int CLAgility = GetCLAgility(wearID);
			int CLPKing = GetCLPKing(wearID);
			int CLDefence = GetCLDefence(wearID);
			int CLStrength = GetCLStrength(wearID);
			int CLMagic = GetCLMagic(wearID);
			int CLRanged = GetCLRanged(wearID);
			boolean GoFalse = false;

			int[] two_hand = { 841, 843, 849, 853, 857, 861, 1319, 2745, 2746, 2747, 2748, 4153, 4214, 4215, 4216, 4217, 4218, 4219, 4220, 4221, 4222, 4223, 4710, 4718, 4726, 4734, 4747, 4755, 6528, 7158, 9109, 9106, 9100, 9104, 9107, 9103, 9102, 7603, 7630, 1307, 1309, 1311, 1313, 1315, 1317, 1319 };
			for (int a = 0; a < two_hand.length; a++) {
				if (wearID == two_hand[a] && playerEquipment[playerShield] > 0) {
					if (!hasItem(-1)) {
						Send("You don't have enough inventory space to wield that item.");
						return false;
					} else {
						remove(playerEquipment[playerShield], playerShield);
					}
				}
				if (itemType(wearID) == playerShield && playerEquipment[playerWeapon] == two_hand[a]) {
					if (!hasItem(-1)) {
						Send("You don't have enough inventory space to wield that item.");
						return false;
					} else {
						remove(playerEquipment[playerWeapon], playerWeapon);
					}
				}
			}
			if (playerLevel[playerAttack] - CLAttack < 0) {
				GoFalse = true;
				Send("You aren't high enough level to wear this!");
				Send("You need " + CLAttack + " Attack to equip this item.");
			}
			if (getLevelForXP(playerXP[playerHitpoints]) - CLHitpoints < 0) {
				GoFalse = true;
				Send("You aren't high enough level to wear this!");
				Send("You need " + CLHitpoints + " Hitpoints to equip this item.");
			}
			if (getLevelForXP(playerXP[playerPrayer]) - CLPrayer < 0) {
				GoFalse = true;
				Send("You aren't high enough level to wear this!");
				Send("You need " + CLPrayer + " Prayer to equip this item.");
			}
			if (playerLevel[playerHerblore] - CLHerblore < 0) {
				GoFalse = true;
				Send("You aren't high enough level to wear this!");
				Send("You need " + CLHerblore + " Herblore to equip this item.");
			}
			if (playerLevel[playerFarming] - CLFarming < 0) {
				GoFalse = true;
				Send("You aren't high enough level to wear this!");
				Send("You need " + CLFarming + " Farming to equip this item.");
			}
			if (playerLevel[playerMining] - CLMining < 0) {
				GoFalse = true;
				Send("You aren't high enough level to wear this!");
				Send("You need " + CLMining + " Mining to equip this item.");
			}
			if (playerLevel[playerSmithing] - CLSmithing < 0) {
				GoFalse = true;
				Send("You aren't high enough level to wear this!");
				Send("You need " + CLSmithing + " Smithing to equip this item.");
			}
			if (playerLevel[playerRunecrafting] - CLRunecrafting < 0) {
				GoFalse = true;
				Send("You aren't high enough level to wear this!");
				Send("You need " + CLRunecrafting + " Runecrafting to equip this item.");
			}
			if (playerLevel[playerFletching] - CLFletching < 0) {
				GoFalse = true;
				Send("You aren't high enough level to wear this!");
				Send("You need " + CLFletching + " Fletching to equip this item.");
			}
			if (playerLevel[playerWoodcutting] - CLWoodcutting < 0) {
				GoFalse = true;
				Send("You aren't high enough level to wear this!");
				Send("You need " + CLWoodcutting + " Woodcutting to equip this item.");
			}
			if (playerLevel[playerCooking] - CLCooking < 0) {
				GoFalse = true;
				Send("You aren't high enough level to wear this!");
				Send("You need " + CLCooking + " Cooking to equip this item.");
			}
			if (playerLevel[playerFishing] - CLFishing < 0) {
				GoFalse = true;
				Send("You aren't high enough level to wear this!");
				Send("You need " + CLFishing + " Fishing to equip this item.");
			}
			if (playerLevel[playerThieving] - CLThieving < 0) {
				GoFalse = true;
				Send("You aren't high enough level to wear this!");
				Send("You need " + CLThieving + " Thieving to equip this item.");
			}
			if (playerLevel[playerAgility] - CLAgility < 0) {
				GoFalse = true;
				Send("You aren't high enough level to wear this!");
				Send("You need " + CLAgility + " Agility to equip this item.");
			}
			if (playerLevel[playerPKing] - CLPKing < 0) {
				GoFalse = true;
				Send("You aren't high enough level to wear this!");
				Send("You need " + CLPKing + " PKing to equip this item.");
			}
			if (playerLevel[playerDefence] - CLDefence < 0) {
				GoFalse = true;
				Send("You aren't high enough level to wear this!");
				Send("You need " + CLDefence + " Defence to equip this item.");
			}
			if (playerLevel[playerStrength] - CLStrength < 0) {
				GoFalse = true;
				Send("You aren't high enough level to wear this!");
				Send("You need " + CLStrength + " Strength to equip this item.");
			}
			if (playerLevel[playerMagic] - CLMagic < 0) {
				GoFalse = true;
				Send("You aren't high enough level to wear this!");
				Send("You need " + CLMagic + " Magic to equip this item.");
			}
			if (playerLevel[playerRanged] - CLRanged < 0) {
				GoFalse = true;
				Send("You aren't high enough level to wear this!");
				Send("You need " + CLRanged + " Ranging to equip this item.");
			}
			if (GoFalse) {
				return false;
			}
			int wearAmount = playerItemsN[slot];
			if (wearAmount < 1) {
				return false;
			}
			wearing = true;
			if (slot >= 0 && wearID >= 0) {
				deleteItem(wearID, slot, wearAmount);
				if (playerEquipment[targetSlot] != wearID && playerEquipment[targetSlot] >= 0) {
					addItem(playerEquipment[targetSlot], playerEquipmentN[targetSlot]);
					resetItems(3214);
				} else if (Equipment.itemStackable[wearID] && playerEquipment[targetSlot] == wearID) {
					wearAmount = playerEquipmentN[targetSlot] + wearAmount;
				} else if (playerEquipment[targetSlot] >= 0) {
					addItem(playerEquipment[targetSlot], playerEquipmentN[targetSlot]);
					resetItems(3214);
				}
			}
			outStream.createFrameVarSizeWord(34);
			outStream.writeWord(1688);
			outStream.writeByte(targetSlot);
			outStream.writeWord(wearID + 1);
			if (wearAmount > 254) {
				outStream.writeByte(255);
				outStream.writeDWord(wearAmount);
			} else {
				outStream.writeByte(wearAmount);
			}
			outStream.endFrameVarSizeWord();
			playerEquipment[targetSlot] = wearID;
			playerEquipmentN[targetSlot] = wearAmount;
			if (targetSlot == playerWeapon) {
				SendWeapon(wearID, GetItemName(wearID));
				playerSE = StandAnim(wearID);
				playerSEW = WalkAnim(wearID);
				playerSER = RunAnim(wearID);
				playerSEA = 0x326;
				pEmote = playerSE;
			}
			ResetBonus();
			GetBonus();
			WriteBonus();
			checkSpecialBar();
			SendWeapon((playerEquipment[playerWeapon]), GetItemName(playerEquipment[playerWeapon]));
			updateRequired = true;
			appearanceUpdateRequired = true;
			wearing = false;
			return true;
		}
		return false;
	}

	int itemType(int item) {
		for (int i = 0; i < Equipment.capes.length; i++) {
			if (item == Equipment.capes[i]) {
				return playerCape;
			}
		}
		for (int i = 0; i < Equipment.hats.length; i++) {
			if (item == Equipment.hats[i]) {
				return playerHat;
			}
		}
		for (int i = 0; i < Equipment.boots.length; i++) {
			if (item == Equipment.boots[i]) {
				return playerFeet;
			}
		}
		for (int i = 0; i < Equipment.gloves.length; i++) {
			if (item == Equipment.gloves[i]) {
				return playerHands;
			}
		}
		for (int i = 0; i < Equipment.shields.length; i++) {
			if (item == Equipment.shields[i]) {
				return playerShield;
			}
		}
		for (int i = 0; i < Equipment.amulets.length; i++) {
			if (item == Equipment.amulets[i]) {
				return playerAmulet;
			}
		}
		for (int i = 0; i < Equipment.arrows.length; i++) {
			if (item == Equipment.arrows[i]) {
				return playerArrows;
			}
		}
		for (int i = 0; i < Equipment.rings.length; i++) {
			if (item == Equipment.rings[i]) {
				return playerRing;
			}
		}
		for (int i = 0; i < Equipment.body.length; i++) {
			if (item == Equipment.body[i]) {
				return playerChest;
			}
		}
		for (int i = 0; i < Equipment.legs.length; i++) {
			if (item == Equipment.legs[i]) {
				return playerLegs;
			}
		}
		return playerWeapon;
	}

	void remove(int wearID, int slot) {
		if (addItem(playerEquipment[slot], playerEquipmentN[slot])) {
			resetItems(3214);
			playerEquipment[slot] = -1;
			playerEquipmentN[slot] = 0;
			outStream.createFrame(34);
			outStream.writeWord(6);
			outStream.writeWord(1688);
			outStream.writeByte(slot);
			outStream.writeWord(0);
			outStream.writeByte(0);
			ResetBonus();
			GetBonus();
			WriteBonus();
			if (slot == playerWeapon) {
				SendWeapon(-1, "Unarmed");
				playerSE = StandAnim(wearID);
				playerSEW = WalkAnim(wearID);
				playerSER = RunAnim(wearID);
				playerSEA = 0x326;
			}
			SendWeapon((playerEquipment[playerWeapon]), GetItemName(playerEquipment[playerWeapon]));
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
	}

	void GetHeadIcon() {
		if (headIcon == 0) {
			headIcon = 64;
		}
		if (headIcon == 1) {
			headIcon = 65;
		}
		if (headIcon == 2) {
			headIcon = 66;
		}
		if (headIcon == 4) {
			headIcon = 68;
		}
		if (headIcon == 8) {
			headIcon = 72;
		}
		if (headIcon == 16) {
			headIcon = 80;
		}
		if (headIcon == 32) {
			headIcon = 96;
		}
		appearanceUpdateRequired = true;
		updateRequired = true;
	}

	void deleteequiment(int wearID, int slot) {
		playerEquipment[slot] = -1;
		playerEquipmentN[slot] = 0;
		outStream.createFrame(34);
		outStream.writeWord(6);
		outStream.writeWord(1688);
		outStream.writeByte(slot);
		outStream.writeWord(0);
		outStream.writeByte(0);
		ResetBonus();
		GetBonus();
		WriteBonus();
		if (slot == playerWeapon) {
			SendWeapon(-1, "Unarmed");
		}
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	void setChatOptions(int publicChat, int privateChat, int tradeBlock) {
		outStream.createFrame(206);
		outStream.writeByte(publicChat);
		outStream.writeByte(privateChat);
		outStream.writeByte(tradeBlock);
	}

	void calculateSpecial() {
		resetAnimation();
		boolean noAmount = false;

		switch (playerEquipment[playerWeapon]) {

		case 6739:
			if (specialAmount == 100) {
				specialAmount -= 100;
			} else {
				noAmount = true;
			}
			break;

		case 3204:
			if (specialAmount > 39) {
				specialAmount -= 40;
			} else {
				noAmount = true;
			}
			break;

		case 861:
			if (specialAmount > 59) {
				specialAmount -= 60;
			} else {
				noAmount = true;
			}
			break;

		case 7603:
			if (specialAmount > 69) {
				specialAmount -= 70;
			} else {
				noAmount = true;
			}
			break;

		case 5698:
			if (specialAmount > 24) {
				specialAmount -= 25;
			} else {
				noAmount = true;
			}
			break;

		case 4151:
			if (specialAmount > 49) {
				specialAmount -= 50;
			} else {
				noAmount = true;
			}
			break;
			
		case 1305:
			if (specialAmount > 24) {
				specialAmount -= 25;
			} else {
				noAmount = true;
			}
			break;

		case 2745:
			if (specialAmount > 99) {
				specialAmount -= 100;
			} else {
				noAmount = true;
			}
			break;

		case 2746:
			if (specialAmount > 49) {
				specialAmount -= 50;
			} else {
				noAmount = true;
			}
			break;

		case 2747:
			if (specialAmount > 49) {
				specialAmount -= 50;
			} else {
				noAmount = true;
			}
			break;

		case 2748:
			if (specialAmount > 99) {
				specialAmount -= 100;
			} else {
				noAmount = true;
			}
			break;

		case 7630:
			if (specialAmount > 99) {
				specialAmount -= 100;
			} else {
				noAmount = true;
			}
			break;

		case 1434:
			if (specialAmount > 24) {
				specialAmount -= 25;
			} else {
				noAmount = true;
			}
			break;

		case 7158:
			if (specialAmount > 39) {
				specialAmount -= 40;
			} else {
				noAmount = true;
			}
			break;

		case 4587:
			if (specialAmount > 74) {
				specialAmount -= 75;
			} else {
				noAmount = true;
			}
			break;

		default:
			noAmount = true;
			break;
		}
		if (noAmount) {
			resetAnimation();
			Send("You do not have enough special energy left.");
			return;
		}
		SpecialAttack(playerEquipment[playerWeapon]);
	}

	void getHitDiff(Client Atk) {
		int aBonus = 0;
		int rand_att = Misc.random(playerLevel[0])*3 + Misc.random(AtkPray * 8);
		int rand_def = (int) (0.65 * Misc.random(Atk.playerLevel[1]) + Atk.DefPray * 8);
		if (Atk.protMelee)
		rand_def += Misc.random(100);
		if (FightType == 1)
		aBonus += (int) (playerBonus[1] / 20);
		int random_u = Misc.random(playerBonus[1] + aBonus) * 2;
		int dBonus = 0;
		if (Atk.FightType == 4)
		dBonus += (int) (Atk.playerBonus[6] / 20);
		int random_def = Misc.random(Atk.playerBonus[6] + dBonus + Atk.AtkPray * 8);
		if ((random_u >= random_def) && (rand_att > rand_def)) {
			CalculateMaxHit();
			hitDiff = Misc.random(playerMaxHit);
			if (Atk.protMelee)
			hitDiff /= 2;
		} else {
			hitDiff = 0;
		}
	}

	boolean Attack() {
		Client Atk = (Client) Server.PlayerManager.players[AttackingOn];
		boolean UseBow = false;
		int EnemyX = PlayerManager.players[AttackingOn].absX;
		int EnemyY = PlayerManager.players[AttackingOn].absY;
		int EnemyHP = PlayerManager.players[AttackingOn].playerLevel[playerHitpoints];
		int casterX = absX;
		int casterY = absY;
		int offsetX = (casterX - EnemyX) * -1;
		int offsetY = (casterY - EnemyY) * -1;
		// 3 seconds
		int[] Wep1 = { -1, 1277, 1279, 1281, 1283, 1285, 1287, 1289, 1291, 1325, 1327, 1329, 1331, 1333, 4151, 4153, 4587, 5698, 6528, 6562, 7409, 7630, 9101, 9110, 9117, 1321, 1323 };
		// 3.5 seconds
		int[] Wep2 = { 2769, 1420, 1422, 1424, 1426, 1428, 1430, 1432, 1434, 1237, 1239, 1241, 1243, 1245, 1247, 1249, 1249, 1275, 1291, 1293, 1295, 1297, 1299, 1301, 1303, 1305, 6897, 1359, 1434, 2815, 2821, 2827, 4710, 4726, 4747, 4755 };
		// 4 seconds
		int[] Wep3 = { 1363, 1365, 1367, 1369, 1371, 1373, 1375, 1335, 1337, 1339, 1341, 1343, 1345, 1347, 1307, 1309, 1311, 1313, 1315, 1317, 1319, 1265, 1319, 2745, 2746, 2747, 2748, 3053, 3204, 6739, 7158 };
		// 5 seconds
		int[] Wep4 = { 1379, 1381, 1383, 1385, 1387, 1389, 1391, 1393, 1395, 1397, 1399, 1401, 1403, 1405, 1407, 1409, 1411, 1413, 1415, 1417, 1373, 1377, 4675, 4718, 6914 };
		// 2.5 seconds
		int[] Wep5 = { 9105, 1203, 1205, 1207, 1209, 1211, 1213, 1215, 1217 };
		int[] Bow1 = { 839, 841, 843, 845, 847, 849, 851, 853, 855, 857, 859, 861, 4214, 4215, 4216, 4217, 4218, 4219, 4220, 4221, 4222, 4223, 4734, 6724 };
		int[] Bow2 = { 839, 841, 843, 845, 847, 849, 851, 853, 855, 857, 859, 861, 4734, 6724 };
		//note: add axes
		for (int i = 0; i < Wep5.length; i++) {
			if (playerEquipment[playerWeapon] == Wep5[i]) {
				Fighting = 2500;
			}
		}
		for (int i = 0; i < Wep1.length; i++) {
			if (playerEquipment[playerWeapon] == Wep1[i]) {
				Fighting = 3000;
			}
		}
		for (int i = 0; i < Wep2.length; i++) {
			if (playerEquipment[playerWeapon] == Wep2[i]) {
				Fighting = 3500;
			}
		}
		for (int i = 0; i < Wep3.length; i++) {
			if (playerEquipment[playerWeapon] == Wep3[i]) {
				Fighting = 4000;
			}
		}
		for (int i = 0; i < Wep4.length; i++) {
			if (playerEquipment[playerWeapon] == Wep4[i]) {
				Fighting = 5000;
			}
		}
		for (int i = 0; i < Bow1.length; i++) {
			if (playerEquipment[playerWeapon] == Bow1[i]) {
				UseBow = true;
				CheckArrows();
			}
		}
		for (int i = 0; i < Bow2.length; i++) {
			if (playerEquipment[playerWeapon] == Bow2[i]) {
				Fighting = 2500;
			}
		}
		if (hasCrystalBow()) {
			Fighting = 4000;
		}
		if (playerEquipment[playerWeapon] == 6724) {
			Fighting = 5000;
		}
		if (playerEquipment[playerWeapon] == 9103) {
			Fighting = 1000;
		}
		if (playerEquipment[playerWeapon] == 9109) {
			Fighting = 5500;
		}
		if (playerEquipment[playerWeapon] == 9107 || playerEquipment[playerWeapon] == 9104) {
			Fighting = 2250;
			UseBow = true;
			CheckArrows();
		}
		if (playerEquipment[playerWeapon] == 7603) {
			Fighting = 5000;
			UseBow = true;
			CheckArrows();
			if (!usingSpecial) {
				DDS2Damg = true;
			}
		}

		resetAnimation();
		getHitDiff(Atk);
		PkingDelay = System.currentTimeMillis();

		if (!inWilderness() || !Atk.inWilderness()) {
			ResetAttack();
			Send("That player can't be attacked because they aren't in the Wilderness!");
			return false;
		} else {
			AttackID = Atk.playerId;
			Atk.AttackID = playerId;
			TurnPlayerTo(EnemyX, EnemyY);
			if (smite) {
				Atk.playerLevel[5] -= hitDiff/4;
			}
			if (Atk.absX == absX && Atk.absY == absY) {
				WalkTimer(-1, 0);
			}
			if (!Skulled && Atk.SkulledOn != playerName && !isInFightPits() && (GoodDistance(EnemyX, EnemyY, absX, absY, 1) || UseBow)) {
				Skulled = true;
				SkulledOn = Atk.playerName;
				GetHeadIcon();
			}
			if (!UseBow) {
				follow(AttackingOn, playerId);
				if (GoodDistance(EnemyX, EnemyY, absX, absY, 1)) {
					if (!usingSpecial) {
						anim(AttackAnim());
						if (!usingSpecial) {
							frame174(Server.GetWepSound.GetWeaponSound(playerId), 025, 000);
						}
						Atk.anim(Atk.BlockAnim(Atk.playerEquipment[Atk.playerWeapon]));
						if ((EnemyHP - hitDiff) < 0)
						hitDiff = EnemyHP;
						Atk.Hit(hitDiff);
						if (Atk.canVengeance && hitDiff != 0) {
							Hit((int)(hitDiff / 1.2));
							Atk.canVengeance = false;
							Atk.Text = "Taste vengeance!";
							Atk.stringUpdateRequired = true;
						}
					} else {
						calculateSpecial();
						usingSpecial = false;
						Special.special(this);
					}
					if (playerEquipment[playerWeapon] == 5698) {
						Atk.PoisonPlayer();
					}
					Atk.KillerID = playerId;
					addSkillXP(hitDiff, 3);
				}
			} else {
				if (GoodDistance(EnemyX, EnemyY, absX, absY, 7)) {
					if (followPlayer != null)
					followPlayer = null;
					teleportToX = absX;
					teleportToY = absY;
				} else {
					follow(AttackingOn, playerId);
				}
				if (!HasArrows) { 
					ResetAttack();
					if (playerEquipment[playerWeapon] == 4734) {
						Send("You need bolt racks to use that bow!");
					} else {
						Send("You don't have any arrows to use with your bow!");
					}
					return false;
				} else {
					anim(AttackAnim());
					if (!usingSpecial) {
						frame174(Server.GetWepSound.GetWeaponSound(playerId), 025, 000);
					}
					ProjectileBow(offsetX, offsetY, 90, -AttackingOn-1);
					RangeDelay = System.currentTimeMillis();
					Ranging = true;
					if (!hasCrystalBow() && playerEquipment[playerWeapon] != 4734) {
						DeleteArrow();
						if (Misc.random(1) == 0)
						DropArrowsPlr();
					}
					if (usingSpecial) {
						if (playerEquipment[playerWeapon] == 861) {
							if (specialAmount > 59) {
								Projectile(absY, absX, offsetX, offsetY, 50, 78, 249, 43, 31, attacknpc+1);
								Projectile(absY, absX, offsetX, offsetY, 50, 70, 249, 43, 31, attacknpc+1);
							}
							calculateSpecial();
						}
						if (playerEquipment[playerWeapon] == 7603) {
							if (specialAmount > 69) {
								Projectile(absY, absX, offsetX, offsetY, 50, 78, 643, 43, 31, attacknpc+1);
								Projectile(absY, absX, offsetX, offsetY, 50, 70, 643, 43, 31, attacknpc+1);
							}
							calculateSpecial();
						}
						usingSpecial = false;
						Special.special(this);
					}
					Atk.KillerID = playerId;
				}
				return true;
			}
		}
		return false;
	}

	boolean ResetAttack() {
		AttackID = 0;
		followPlayer = null;
		IsAttacking = false;
		AttackingOn = 0;
		spellID = 0;
		hitDiff = 0;
		resetAnimation();
		IsUsingSkill = false;
		pEmote = playerSE;
		return true;
	}

	private void appendSetFocusDestination(Stream str) {
		str.writeWordBigEndianA(FocusPointX);
		str.writeWordBigEndian(FocusPointY);
	}
	
	void Killer() {
		if (PlayerManager.players[KillerID] != null) {
			if (KillerID != playerId) {
				Client k = (Client) Server.PlayerManager.players[KillerID];
				k.Send("You have defeated "+playerName+".");
				if (combat > k.combat || pkRating > k.pkRating) {
					k.addSkillXP((47350 + Misc.random(99)), 18);
				} else {
					k.addSkillXP((43430 + Misc.random(99)), 18);
				}
				pkDeaths += 1;
				k.KilledPlayer = playerName;
				KilledBy = k.playerName;
				k.PkKills += 1;
				if (combat > k.combat || pkRating > k.pkRating) {
					k.pkRating += 10;
					k.pkPoints += 2;
					if (pkRating >= 10) {
						pkRating -= 10;
					} else {
						pkRating = 0;
					}
				} else {
					k.pkRating += 5;
					k.pkPoints += 1;
					if (pkRating >= 5) {
						pkRating -= 5;
					} else {
						pkRating = 0;
					}
				}
				k.ResetAttack();
				k.Save();
				Save();
				k.UpdateTxt();
				UpdateTxt();
			}
		}
	}

	void UpdateTxt() {
		Server.Text.UpdateText(this);
	}
	
	void Dead() {
		SkulledOn = "";
		Questing = false;
		Poisoned = false;
		Skulled = false;
		teleblock = false;
		Summoned = false;
		InWilderness = false;
		KillerID = playerId;
		Death = 3;
		Entangled = 0;
		frame61(0);
		Server.Prayer.resetPrayer(this);
		setOption("null", 3, 1);
		pEmote = playerSE;
		updateRequired = true;
		appearanceUpdateRequired = true;
	}

	boolean ApplyDead() {
		if (!IsDead) {
			DeathDelay = System.currentTimeMillis();
			IsDead = true;
			Death = 1;
			if (ret) {
				Retribution();
			}
			if (IsAttacking) {
				ResetAttack();
			}
			if (IsAttackingNPC) {
				ResetAttackNPC();
			}
			RemoveNPC();
		}
		if (System.currentTimeMillis() - DeathDelay > 1500 && Death == 1 && IsDead) {
			Death = 2;
			anim(2304);
			updateRequired = true;
			appearanceUpdateRequired = true;
		}
		if (System.currentTimeMillis() - DeathDelay > 3500 && Death == 2 && IsDead) {
			if (isInFightPits() && !isInFightCaves()) {
				Teleport(2399, 5170, 0);
			}
			if (!isInFightPits() && isInFightCaves()) {
				Saveme();
			}
			if (!isInFightPits() && !isInFightCaves()) {
				youdied();
				Killer();
			}
			clearInterface();
			Dead();
		}
		if (System.currentTimeMillis() - DeathDelay > 3500 && Death == 3 && IsDead) {
			IsDead = false;
			Death = 0;
			UpdateHP(99);
			playerLevel[5] = getLevelForXP(playerXP[5]);
			frame1();
			Send("Oh dear, you have died!");
		}
		return true;
	}

	void setSkillLevel(int skillNum, int currentLevel, int XP) {
		if (skillNum == 0) {
			sendString("" + playerLevel[0] + "", 4004);
			sendString("" + getLevelForXP(playerXP[0]) + "", 4005);
		}
		if (skillNum == 2) {
			sendString("" + playerLevel[2] + "", 4006);
			sendString("" + getLevelForXP(playerXP[2]) + "", 4007);
		}
		if (skillNum == 1) {
			sendString("" + playerLevel[1] + "", 4008);
			sendString("" + getLevelForXP(playerXP[1]) + "", 4009);
		}
		if (skillNum == 4) {
			sendString("" + playerLevel[4] + "", 4010);
			sendString("" + getLevelForXP(playerXP[4]) + "", 4011);
		}
		if (skillNum == 5) {
			sendString("" + playerLevel[5] + "", 4012);
			sendString("" + getLevelForXP(playerXP[5]) + "", 4013);
		}
		if (skillNum == 6) {
			sendString("" + playerLevel[6] + "", 4014);
			sendString("" + getLevelForXP(playerXP[6]) + "", 4015);
		}
		if (skillNum == 3) {
			sendString("" + playerLevel[3] + "", 4016);
			sendString("" + getLevelForXP(playerXP[3]) + "", 4017);
		}
		if (skillNum == 16) {
			sendString("" + playerLevel[16] + "", 4018);
			sendString("" + getLevelForXP(playerXP[16]) + "", 4019);
		}
		if (skillNum == 15) {
			sendString("" + playerLevel[15] + "", 4020);
			sendString("" + getLevelForXP(playerXP[15]) + "", 4021);
		}
		if (skillNum == 17) {
			sendString("" + playerLevel[17] + "", 4022);
			sendString("" + getLevelForXP(playerXP[17]) + "", 4023);
		}
		if (skillNum == 12) {
			sendString("" + playerLevel[12] + "", 4024);
			sendString("" + getLevelForXP(playerXP[12]) + "", 4025);
		}
		if (skillNum == 9) {
			sendString("" + playerLevel[9] + "", 4026);
			sendString("" + getLevelForXP(playerXP[9]) + "", 4027);
		}
		if (skillNum == 14) {
			sendString("" + playerLevel[14] + "", 4028);
			sendString("" + getLevelForXP(playerXP[14]) + "", 4029);
		}
		if (skillNum == 13) {
			sendString("" + playerLevel[13] + "", 4030);
			sendString("" + getLevelForXP(playerXP[13]) + "", 4031);
		}
		if (skillNum == 10) {
			sendString("" + playerLevel[10] + "", 4032);
			sendString("" + getLevelForXP(playerXP[10]) + "", 4033);
		}
		if (skillNum == 7) {
			sendString("" + playerLevel[7] + "", 4034);
			sendString("" + getLevelForXP(playerXP[7]) + "", 4035);
		}
		if (skillNum == 11) {
			sendString("" + playerLevel[11] + "", 4036);
			sendString("" + getLevelForXP(playerXP[11]) + "", 4037);
		}
		if (skillNum == 8) {
			sendString("" + playerLevel[8] + "", 4038);
			sendString("" + getLevelForXP(playerXP[8]) + "", 4039);
		}
		if (skillNum == 20) {
			sendString("" + playerLevel[20] + "", 4152);
			sendString("" + getLevelForXP(playerXP[20]) + "", 4153);
		}
		if (skillNum == 18) {
			sendString("" + playerLevel[18] + "", 12166);
			sendString("" + getLevelForXP(playerXP[18]) + "", 12167);
		}
		if (skillNum == 19) {
			sendString("" + playerLevel[19] + "", 13926);
			sendString("" + getLevelForXP(playerXP[19]) + "", 13927);
		} else {
			outStream.createFrame(134);
			outStream.writeByte(skillNum);
			outStream.writeDWord_v1(XP);
			outStream.writeByte(currentLevel);
		}
	}

	void randomEvent() {
		int RandomE = Misc.random(10000);
		if (RandomE >= 9995 && RandomE <= 10000) {
			int Item = 2581 + Misc.random(94);
			addItem(Item, 1);
			Send("You found an item!");
		} else if (RandomE >= 500 && RandomE <= 1500) {
			Send("A weird lamp appears in your hand!");
			addItem(2528,1);
		} else {
			RandomX = absX;
			RandomY = absY;
			RandomH = heightLevel;
			Random = true;
			Direction = Misc.random(3);
			Teleport(2338, 4747, 0);
			Send("You've been teleported to Lost and found.");
			Send("You must select the correct appendage to be teleported out safely.");
		}
	}

	int GetItemSlot(int ItemID) {
		for (int i = 0; i < playerItems.length; i++) {
			if ((playerItems[i] - 1) == ItemID) {
				return i;
			}
		}
		return -1;
	}

	int GetXItemsInBag(int ItemID) {
		int ItemCount = 0;

		for (int i = 0; i < playerItems.length; i++) {
			if ((playerItems[i] - 1) == ItemID) {
				ItemCount++;
			}
		}
		return ItemCount;
	}

	void pmstatus(int status) {
		outStream.createFrame(221);
		outStream.writeByte(status);
	}

	public boolean isinpm(long l) {
		for (int i = 0; i < friends.length; i++) {
			if (friends[i] != 0) {
				if (l == friends[i]) {
					return true;
				}
			}
		}
		return false;
	}

	public void pmupdate(int pmid, int world) {
		long l = Misc.playerNameToInt64(handler.players[pmid].playerName);

		if (handler.players[pmid].Privatechat == 0) {
			for (int i = 0; i < friends.length; i++) {
				if (friends[i] != 0) {
					if (l == friends[i]) {
						loadpm(l, world);
						return;
					}
				}
			}
		} else if (handler.players[pmid].Privatechat == 1) {
			for (int i1 = 0; i1 < friends.length; i1++) {
				if (friends[i] != 0) {
					if (l == friends[i1]) {
						if (handler.players[pmid].isinpm(Misc.playerNameToInt64(playerName)) && Rights > 2) {
							loadpm(l, world);
							return;
						} else {
							loadpm(l, 0);
							return;
						}
					}
				}
			}
		} else if (handler.players[pmid].Privatechat == 2) {
			for (int i2 = 0; i2 < friends.length; i2++) {
				if (friends[i] != 0) {
					if (l == friends[i2] && Rights < 2) {
						loadpm(l, 0);
						return;
					}
				}
			}
		}
	}

	public void sendpm(long name, int rights, byte[] chatmessage, int messagesize) {
		outStream.createFrameVarSize(196);
		outStream.writeQWord(name);
		outStream.writeDWord(handler.lastchatid++);
		outStream.writeByte(rights);
		outStream.writeBytes(chatmessage, messagesize, 0);
		outStream.endFrameVarSize();
	}

	public void loadpm(long name, int world) {	
		if (world != 0) {
			world += 9;
		} else if (world == 0) {
			world += 1;
		}
		outStream.createFrame(50);
		outStream.writeQWord(name);
		outStream.writeByte(world);
	}

	void MagiconPlr(int index) {
		int playerIndex = index;
		int EnemyX = Server.PlayerManager.players[playerIndex].absX;
		int EnemyY = Server.PlayerManager.players[playerIndex].absY;
		int EnemyHP = Server.PlayerManager.players[playerIndex].playerLevel[playerHitpoints];
		int hitDiff = 0;
		
		Client c = (Client) Server.PlayerManager.players[playerIndex];
		faceNPC(32768 + index);
		
		if (c.AttackID != 0 && c.AttackID != playerId) {
			resetAnimation();
			Send("Someone else is already fighting your opponent.");
			c.mageHit = 0;
			c.mageGfx = -1;
			c.MageDelay = 0;
			return;
		} else {
			AttackID = c.playerId;
			c.AttackID = playerId;
		}
		if (playerLevel[6] > 0 && playerLevel[3] > 0 && System.currentTimeMillis() - magicDelay > 800) {
			int casterX = absX;
			int casterY = absY;
			int offsetX = (casterX - EnemyX) * -1;
			int offsetY = (casterY - EnemyY) * -1;
			
			magicDelay = System.currentTimeMillis();
			
			switch (spellID) {

			case 1152: // Wind strike
				Spell(index, 1, 556, 558, 1, 1, 8, 90, 91, 92);
				break;

			case 1154: // Water strike
				Spell(index, 5, 555, 558, 556, 1, 1, 1, 9, 93, 94, 95);
				break;

			case 1156: // Earth strike
				Spell(index, 9, 557, 558, 556, 2, 1, 1, 10, 96, 97, 98);
				break;

			case 1158: // Fire strike
				Spell(index, 13, 554, 558, 556, 3, 1, 2, 11, 99, 100, 101);
				break;

			case 1160: // Wind bolt
				Spell(index, 17, 562, 556, 2, 1, 13, 117, 118, 119);
				break;

			case 1163: // Water bolt
				Spell(index, 23, 562, 556, 555, 2, 1, 2, 14, 120, 121, 122);
				break;

			case 1166: // Earth bolt
				Spell(index, 29, 562, 556, 557, 2, 1, 2, 15, 123, 124, 125);
				break;

			case 1169: // Fire bolt
				Spell(index, 35, 562, 556, 554, 3, 1, 4, 16, 126, 127, 128);
				break;

			case 1172: // Wind blast
				Spell(index, 41, 556, 560, 3, 1, 17, 132, 133, 134);
				break;

			case 1175: // Water blast
				Spell(index, 47, 556, 560, 555, 3, 1, 3, 18, 135, 136, 137);
				break;

			case 1177: // Earth blast
				Spell(index, 53, 556, 560, 557, 3, 1, 3, 19, 138, 139, 140);
				break;

			case 1181: // Fire blast
				Spell(index, 59, 556, 560, 554, 4, 1, 5, 20, 129, 130, 131);
				break;

			case 1183: // Wind wave
				Spell(index, 62, 556, 565, 5, 1, 22, 158, 159, 160);
				break;

			case 1185: // Water wave
				Spell(index, 65, 556, 565, 555, 5, 1, 7, 23, 161, 162, 163);
				break;

			case 1188: // Earth wave
				Spell(index, 70, 556, 565, 557, 5, 1, 7, 24, 164, 165, 166);
				break;

			case 1189: // Fire wave
				Spell(index, 75, 556, 565, 554, 5, 1, 7, 25, 155, 156, 157);
				break;

			case 12975: // Smoke barrage
				Spell(index, 86, false, 1, 560, 565, 554, 556, 4, 2, 4, 4, 27, 391);
				break;

			case 12881: // Ice burst
				Spell(index, 70, false, 2, 562, 560, 555, 4, 2, 4, 20, 363);
				break;

			case 12861: // Ice rush
				Spell(index, 58, true, 2, 562, 560, 555, 2, 2, 2, 18, 361);
				break;

			case 12939: // Smoke rush
				Spell(index, 50, true, 1, 562, 560, 554, 556, 2, 2, 1, 1, 16, 385);
				break;

			case 12963: // Smoke burst
				Spell(index, 62, false, 1, 562, 560, 554, 556, 4, 2, 2, 2, 19, 390);
				break;

			case 12951: // Smoke blitz
				Spell(index, 74, true, 1, 560, 565, 554, 556, 2, 2, 2, 2, 23, 388);
				break;

			case 12987: // Shadow rush
				Spell(index, 52, true, 0, 562, 560, 556, 566, 2, 2, 1, 1, 16, 380);
				break;

			case 13011: // Shadow burst
				Spell(index, 64, false, 0, 562, 560, 556, 566, 4, 2, 2, 2, 20, 380);
				break;

			case 12999: // Shadow blitz
				Spell(index, 76, true, 0, 560, 565, 556, 566, 2, 2, 2, 2, 24, 383);
				break;

			case 12901: // Blood rush
				Spell(index, 56, true, 3, 562, 560, 565, 2, 2, 1, 19, 374);
				break;

			case 12919: // Blood burst
				Spell(index, 68, false, 3, 562, 560, 565, 4, 2, 2, 21, 373);
				break;

			case 12891: // Ice barrage
				Spell(index, 94, false, 2, 560, 565, 555, 4, 2, 6, 30, 369);
				break;

			case 12929: // Blood barrage
				Spell(index, 92, false, 3, 560, 565, 566, 4, 4, 1, 29, 376);
				break;

			case 13023: // Shadow barrage
				Spell(index, 88, false, 0, 560, 565, 556, 566, 4, 2, 4, 3, 28, 383);
				break;

			case 12871: // Ice blitz
				Spell(index, 82, true, 2, 560, 565, 555, 2, 2, 3, 26, 367);
				break;

			case 12911: // Blood blitz
				Spell(index, 80, 560, 565, 2, 4, 25, 375);
				break;

			case 1592:
				if (playerLevel[6] >= 78) {
					if (!hasAmount(561, 4) || !hasAmount(557, 5) || !hasAmount(555, 5)) {
						hasRunes = false;
					} else {
						anim(718);
						gfx(177);
						Projectile(absY, absX, offsetX, offsetY, 50, 90, 178, 43, 31, attacknpc);
						c.mageHit = 5;
						c.mageGfx = 179;
						if (System.currentTimeMillis() - c.EntangleDelay > c.Entangled) {
							c.Entangle(12000);
							c.Send("A bind spell has been cast upon you!");
						}
						delete(561, 4); 
						delete(557, 5); 
						delete(555, 5);
					}
				} else {
					hasLevel = false;
				}
				break;

			case 12445:
				if (playerLevel[6] >= 85) {
					if (!hasAmount(562, 1) || !hasAmount(560, 1) || !hasAmount(563, 1)) {
						hasRunes = false;
					} else {
						anim(1819);
						Projectile(absY, absX, offsetX, offsetY, 50, 90, 344, 43, 31, attacknpc);
						c.mageGfx = 345;
						c.mageHit = -1;
						c.Block = true;
						delete(562, 1);
						delete(563, 1);
						delete(560, 1);
					}
				} else {
					hasLevel = false;
				}
				break;
			}
		}
		if ((EnemyHP - hitDiff) < 0) {
			hitDiff = EnemyHP;
		}
		if (!CheckWildrange(c.combat) && inWilderness() && !isInFightPits()) {
			WildernessMessage();
			c.Maging = false;
			c.mageHit = -1;
			c.mageGfx = -1;
			c.Entangled = 0;
		} else {
			if (!hasLevel) {
				Send("You do not have high enough level in Magic to cast this spell.");
				c.mageHit = -1;
				c.mageGfx = -1;
				c.Maging = false;
				hasLevel = true;
				return;
			}
			if (!hasRunes) {
				Send("You do not have enough runes to cast this spell.");
				c.mageHit = -1;
				c.mageGfx = -1;
				c.Maging = false;
				hasRunes = true;
				return;
			}
			int Splash = Misc.random((playerBonus[3]) + 5);
			if (Splash < 3) {
				c.mageHit = -1;
				c.mageGfx = 85;
			} else {
				if (c.protMage) {
					c.mageHit -= Misc.random(4);
				}
				if (c.Block && !c.teleblock) {
					c.Teleblock();
					c.Block = false;
				}
				if (c.Entangle && System.currentTimeMillis() - c.EntangleDelay > c.Entangled) {
					c.Entangle(24000);
					c.Send("You are frozen!");
					c.Entangle = false;
				}
			}
			c.Maging = true;
			c.MageDelay = System.currentTimeMillis();
			if (c.SkulledOn != playerName && !isInFightPits() && !noRunes) {
				Skulled = true;
				SkulledOn = c.playerName;
				GetHeadIcon();
			}
		}
		int hit = Misc.random(c.mageHit);
		c.KillerID = playerId;
		addSkillXP(hit * 150, 6);
		addSkillXP(hit, 3);
		appearanceUpdateRequired = true;
		updateRequired = true;
	}

	boolean AttackNPC() {
		int EnemyX = Server.NpcManager.npcs[attacknpc].absX;
		int EnemyY = Server.NpcManager.npcs[attacknpc].absY;
		int EnemyHP = Server.NpcManager.npcs[attacknpc].HP;
		int hitDiff = 0;
		int casterX = absX;
		int casterY = absY;
		int offsetX = (casterX - EnemyX) * -1;
		int offsetY = (casterY - EnemyY) * -1;
		faceNPC(attacknpc);

		if (absX == EnemyX && absY == EnemyY) {
			WalkTimer(-1, 0);
		}
		if (!healers && isInFightCaves() && Server.NpcManager.npcs[attacknpc].npcType == 2745 && Server.NpcManager.npcs[attacknpc].HP <= 175){
			Summon(2746, 5072, 2382, heightLevel);
			Summon(2746, 5081, 2415, heightLevel);
			Summon(2746, 5107, 2410, heightLevel);
			Summon(2746, 5103, 2388, heightLevel);
			healers = true;
		}
		if (healers && isInFightCaves() && Server.NpcManager.npcs[attacknpc].npcType == 2745 && healersCount <= 12 && Server.NpcManager.npcs[attacknpc].HP <= 350) {
			healJad();
			stillgfx(444, EnemyY+1, EnemyX+1);
		}
		if (Server.NpcManager.npcs[attacknpc].followPlayer < 1 || Server.NpcManager.npcs[attacknpc].followPlayer == playerId) {
			boolean UseBow = false;
			// 3 seconds
			int[] Wep1 = { -1, 1277, 1279, 1281, 1283, 1285, 1287, 1289, 1291, 1325, 1327, 1329, 1331, 1333, 4151, 4153, 4587, 5698, 6528, 6562, 7409, 7630, 9101, 9110, 9117, 1321, 1323 };
			// 3.5 seconds
			int[] Wep2 = { 2769, 1420, 1422, 1424, 1426, 1428, 1430, 1432, 1434, 1237, 1239, 1241, 1243, 1245, 1247, 1249, 1249, 1275, 1291, 1293, 1295, 1297, 1299, 1301, 1303, 1305, 6897, 1359, 1434, 2815, 2821, 2827, 4710, 4726, 4747, 4755 };
			// 4 seconds
			int[] Wep3 = { 1363, 1365, 1367, 1369, 1371, 1373, 1375, 1335, 1337, 1339, 1341, 1343, 1345, 1347, 1307, 1309, 1311, 1313, 1315, 1317, 1319, 1265, 1319, 2745, 2746, 2747, 2748, 3053, 3204, 6739, 7158 };
			// 5 seconds
			int[] Wep4 = { 1379, 1381, 1383, 1385, 1387, 1389, 1391, 1393, 1395, 1397, 1399, 1401, 1403, 1405, 1407, 1409, 1411, 1413, 1415, 1417, 1373, 1377, 4675, 4718, 6914 };
			// 2.5 seconds
			int[] Wep5 = { 9105, 1203, 1205, 1207, 1209, 1211, 1213, 1215, 1217 };
			int[] Bow1 = { 839, 841, 843, 845, 847, 849, 851, 853, 855, 857, 859, 861, 4214, 4215, 4216, 4217, 4218, 4219, 4220, 4221, 4222, 4223, 4734, 6724 };
			int[] Bow2 = { 839, 841, 843, 845, 847, 849, 851, 853, 855, 857, 859, 861, 4734, 6724 };
			//note: add axes
			for (int i = 0; i < Wep5.length; i++) {
				if (playerEquipment[playerWeapon] == Wep5[i]) {
					Fighting = 2500;
				}
			}
			for (int i = 0; i < Wep1.length; i++) {
				if (playerEquipment[playerWeapon] == Wep1[i]) {
					Fighting = 3000;
				}
			}
			for (int i = 0; i < Wep2.length; i++) {
				if (playerEquipment[playerWeapon] == Wep2[i]) {
					Fighting = 3500;
				}
			}
			for (int i = 0; i < Wep3.length; i++) {
				if (playerEquipment[playerWeapon] == Wep3[i]) {
					Fighting = 4000;
				}
			}
			for (int i = 0; i < Wep4.length; i++) {
				if (playerEquipment[playerWeapon] == Wep4[i]) {
					Fighting = 5000;
				}
			}
			for (int i = 0; i < Bow1.length; i++) {
				if (playerEquipment[playerWeapon] == Bow1[i]) {
					UseBow = true;
					CheckArrows();
				}
			}
			for (int i = 0; i < Bow2.length; i++) {
				if (playerEquipment[playerWeapon] == Bow2[i]) {
					Fighting = 2500;
				}
			}
			if (hasCrystalBow()) {
				Fighting = 4000;
			}
			if (playerEquipment[playerWeapon] == 6724) {
				Fighting = 5000;
			}
			if (playerEquipment[playerWeapon] == 9103) {
				Fighting = 1000;
			}
			if (playerEquipment[playerWeapon] == 9109) {
				Fighting = 5500;
			}
			if (playerEquipment[playerWeapon] == 9107 || playerEquipment[playerWeapon] == 9104) {
				Fighting = 2250;
				UseBow = true;
				CheckArrows();
			}
			if (playerEquipment[playerWeapon] == 7603) {
				Fighting = 5000;
				UseBow = true;
				CheckArrows();
				if (!usingSpecial) {
					DDS2Damg = true;
				}
			}
			if (playerEquipment[playerHat] == 4724 && playerEquipment[playerChest] == 4728 && playerEquipment[playerLegs] == 4730 && playerEquipment[playerWeapon] == 4726) {
				if (Misc.random(5) == 1) {
					gfx(398);
					UpdateHP(hitDiff);
				}
			}

			PkingDelay = System.currentTimeMillis();
			
			if (!UseBow) {
				if (GoodDistance(EnemyX, EnemyY, absX, absY, 1)) {
					anim(AttackAnim());
					if (!usingSpecial) {
						frame174(Server.GetWepSound.GetWeaponSound(playerId), 025, 000);
					}
					CalculateMaxHit();
					hitDiff = Misc.random(playerMaxHit);
					if ((EnemyHP - hitDiff) < 0) {
						hitDiff = EnemyHP;
					}
					if (playerEquipment[playerWeapon] == 5698) {
						Server.NpcManager.PoisonNPC(attacknpc);
					}
					if (usingSpecial) {
						calculateSpecial();
						usingSpecial = false;
						Special.special(this);
					}
					Server.NpcManager.npcs[attacknpc].animNumber = Server.NpcManager.GetNPCBlockAnim(Server.NpcManager.npcs[attacknpc].npcType);
					Server.NpcManager.npcs[attacknpc].hitDiff = hitDiff;
					Server.NpcManager.npcs[attacknpc].Killing[playerId] += hitDiff;
					Server.NpcManager.npcs[attacknpc].updateRequired = true;
					Server.NpcManager.npcs[attacknpc].hitUpdateRequired = true;
					Server.NpcManager.npcs[attacknpc].StartKilling = playerId;
					Server.NpcManager.npcs[attacknpc].RandomWalk = false;
					Server.NpcManager.npcs[attacknpc].followingPlayer = true;
					Server.NpcManager.npcs[attacknpc].followPlayer = playerId;
					Server.NpcManager.npcs[attacknpc].IsUnderAttack = true;
					totalDamageDealt += hitDiff;
					if (FightType == 1) {
						addSkillXP(140 * hitDiff, 0);
					} else if (FightType == 2 || FightType == 3) {
						addSkillXP(140 * hitDiff, 2);
					} else if (FightType == 4) {
						addSkillXP(140 * hitDiff, 1);
					}
					addSkillXP(80 * hitDiff, 3);
				}
			} else {
				teleportToX = absX;
				teleportToY = absY;
				if (!HasArrows) { 
					if (playerEquipment[playerWeapon] == 4734) {
						Send("You need bolt racks to use that bow!");
					} else {
						Send("You don't have any arrows to use with your bow!");
					}
					ResetAttackNPC();
				} else if (HasArrows) {
					anim(AttackAnim());
					if (playerEquipment[playerCape] != 7634 && !hasCrystalBow()) {
						DeleteArrow();
						if (Misc.random(1) == 0)
						DropArrowsNPC();
					}
					ProjectileBow(offsetX, offsetY, 70, attacknpc + 1);
					hitDiff = Misc.random(maxRangeHit2());
					if (usingSpecial) {
						if (playerEquipment[playerWeapon] == 861) {
							if (specialAmount > 59) {
								Projectile(absY, absX, offsetX, offsetY, 50, 78, 249, 43, 31, attacknpc+1);
								Projectile(absY, absX, offsetX, offsetY, 50, 70, 249, 43, 31, attacknpc+1);
							}
							calculateSpecial();
						}
						if (playerEquipment[playerWeapon] == 7603 && specialAmount >= 70) {
							if (specialAmount > 69) {
								Projectile(absY, absX, offsetX, offsetY, 50, 78, 643, 43, 31, attacknpc+1);
								Projectile(absY, absX, offsetX, offsetY, 50, 70, 643, 43, 31, attacknpc+1);
							}
							calculateSpecial();
						}
						usingSpecial = false;
						Special.special(this);
					}
					if ((EnemyHP - hitDiff) < 0) {
						hitDiff = EnemyHP;
					}
					Server.NpcManager.npcs[attacknpc].hitDiff = hitDiff;
					Server.NpcManager.npcs[attacknpc].Killing[playerId] += hitDiff;
					Server.NpcManager.npcs[attacknpc].updateRequired = true;
					Server.NpcManager.npcs[attacknpc].hitUpdateRequired = true;
					Server.NpcManager.npcs[attacknpc].StartKilling = playerId;
					Server.NpcManager.npcs[attacknpc].RandomWalk = false;
					Server.NpcManager.npcs[attacknpc].followingPlayer = true;
					Server.NpcManager.npcs[attacknpc].followPlayer = playerId;
					Server.NpcManager.npcs[attacknpc].IsUnderAttack = true;
					totalDamageDealt += hitDiff;
					addSkillXP(140 * hitDiff, 4);
					addSkillXP(80 * hitDiff, 3);
				}
			}
			return true;
		} else {
			ResetAttackNPC2();
		}
		return false;
	}
	
	boolean ResetAttackNPC() {
		if (attacknpc > -1 && attacknpc < Server.NpcManager.maxNPCs) {
			Server.NpcManager.npcs[attacknpc].IsUnderAttack = false;
			Server.NpcManager.npcs[attacknpc].followingPlayer = false;
			Server.NpcManager.npcs[attacknpc].followPlayer = 0;
			Server.NpcManager.npcs[attacknpc].RandomWalk = true;
			Server.NpcManager.npcs[attacknpc].StartKilling = 0;
		}
		IsAttackingNPC = false;
		attacknpc = -1;
		resetAnimation();
		pEmote = playerSE;
		faceNPC = 65535;
		faceNPCupdate = true;
		return true;
	}
	
	boolean ResetAttackNPC2() {
		IsAttackingNPC = false;
		attacknpc = -1;
		resetAnimation();
		pEmote = playerSE;
		faceNPC = 65535;
		faceNPCupdate = true;
		return true;
	}

	int GetNPCID(int coordX, int coordY) {
		for (int i = 0; i < Server.NpcManager.maxNPCs; i++) {
			if (Server.NpcManager.npcs[i] != null) {
				if (Server.NpcManager.npcs[i].absX == coordX && Server.NpcManager.npcs[i].absY == coordY) {
					return Server.NpcManager.npcs[i].npcType;
				}
			}
		}
		return 1;
	}

	String GetNpcName(int NpcID) {
		for (int i = 0; i < Server.NpcManager.maxListedNPCs; i++) {
			if (Server.NpcManager.NpcList[i] != null) {
				if (Server.NpcManager.NpcList[i].npcId == NpcID) {
					return Server.NpcManager.NpcList[i].npcName;
				}
			}
		}
		return "Unknown NPC:" + NpcID;
	}

	String GetItemName(int ItemID) {
		for (int i = 0; i < Server.item.MaxListedItems; i++) {
			if (Server.item.ItemList[i] != null) {
				if (Server.item.ItemList[i].itemId == ItemID) {
					return " " + Server.item.ItemList[i].itemName;
				}
				if (ItemID == -1) {
					return " Unarmed";
				}
			}
		}
		return "Unknown Item: " + ItemID;
	}

	double GetItemShopValue(int ItemID, int Type, int fromSlot) {
		double ShopValue = 1;
		double Overstock = 0;
		double TotPrice = 0;

		for (int i = 0; i < Server.item.MaxListedItems; i++) {
			if (Server.item.ItemList[i] != null) {
				if (Server.item.ItemList[i].itemId == ItemID) {
					ShopValue = Server.item.ItemList[i].ShopValue;
				}
			}
		}

		TotPrice = (ShopValue * 1);

		if (Server.shop.ShopBModifier[MyShopID] == 1) {
			TotPrice *= 1;
			if (Type == 1) {
				TotPrice *= 1;
			}
		} else if (Type == 1) {
			TotPrice *= 1;
		}
		return TotPrice;
	}

	int GetUnnotedItem(int ItemID) {
		int NewID = 0;
		String NotedName = "";

		for (int i = 0; i < Server.item.MaxListedItems; i++) {
			if (Server.item.ItemList[i] != null) {
				if (Server.item.ItemList[i].itemId == ItemID) {
					NotedName = Server.item.ItemList[i].itemName;
				}
			}
		}
		for (int i = 0; i < Server.item.MaxListedItems; i++) {
			if (Server.item.ItemList[i] != null) {
				if (Server.item.ItemList[i].itemName == NotedName) {
					if (!Server.item.ItemList[i].itemDescription.startsWith("Swap this note at any bank for a")) {
						NewID = Server.item.ItemList[i].itemId;
						break;
					}
				}
			}
		}
		return NewID;
	}

	public void appendPos() {
		try {
			sendString("X: " + absX + " Y: " + absY, 184);
		} catch (Exception e) {
		}
	}
	
	public void playersOn() {
		try {
			sendString("" + PlayerManager.getPlayerCount(), 149);
		} catch (Exception e) {
		}
	}
	
	void ResetBonus() {
		for (int i = 0; i < playerBonus.length; i++) {
			playerBonus[i] = 0;
		}
	}

	void GetBonus() {
		for (int i = 0; i < playerEquipment.length; i++) {
			if (playerEquipment[i] > -1) {
				for (int j = 0; j < 9999; j++) {
					if (Server.item.ItemList[j] != null) {
						if (Server.item.ItemList[j].itemId == playerEquipment[i]) {
							for (int k = 0; k < playerBonus.length; k++) {
								playerBonus[k] += Server.item.ItemList[j].Bonuses[k];
							}
							break;
						}
					}
				}
			}
		}
	}

	void WriteBonus() {
		int offset = 0;
		String send = "";

		for (int i = 0; i < playerBonus.length; i++) {
			if (playerBonus[i] >= 0) {
				send = BonusName[i] + ": +" + playerBonus[i];
			} else {
				send = BonusName[i] + ": -" + java.lang.Math.abs(playerBonus[i]);
			}
			
			if (i == 10) {
				offset = 1;
			}
			sendFrame126(send, (1675 + i + offset));
		}
		CalculateMaxHit();
	}

	void CalculateMaxHit() {
		double MaxHit = 0;
		int StrBonus = playerBonus[10];
		int Strength = playerLevel[playerStrength];

		if (strLow) {
			Strength += (Strength*0.05);
		} else if (strMid) {
			Strength += (Strength*0.10);
		} else if (strHigh) {
			Strength += (Strength*0.15);
		} else if (chiv) {
			Strength += (Strength*0.20);
		} else if (piety) {
			Strength += (Strength*0.25);
		}
		if (FightType == 1 || FightType == 4) {
			MaxHit += ((double) ((double) StrBonus + Strength) / ((double) 6.8275862068965517241379310344828));
		} else if (FightType == 2) {
			MaxHit += ((double) ((double) StrBonus + Strength) / ((double) 6.6551724137931034482758620689655));
		} else if (FightType == 5) {
			MaxHit += ((double) ((double) StrBonus + Strength) / ((double) 6.6551724137931034482758620689655));
		} else if (FightType == 3) {
			MaxHit += ((double) ((double) StrBonus + Strength) / ((double) 6.7586206896551724137931034482759));
		}
		if (FullDharokEquipped()) {
			MaxHit += (getLevelForXP(playerXP[playerHitpoints]) - playerLevel[playerHitpoints]) / 2;
		}
		playerMaxHit = (int) Math.floor(MaxHit);
	}

	boolean GoodDistance(int objectX, int objectY, int playerX, int playerY, int distance) {
		for (int i = 0; i <= distance; i++) {
			for (int j = 0; j <= distance; j++) {
				if ((objectX + i) == playerX && ((objectY + j) == playerY || (objectY - j) == playerY || objectY == playerY)) {
					return true;
				} else if ((objectX - i) == playerX && ((objectY + j) == playerY || (objectY - j) == playerY || objectY == playerY)) {
					return true;
				} else if (objectX == playerX && ((objectY + j) == playerY || (objectY - j) == playerY || objectY == playerY)) {
					return true;
				}
			}
		}
		return false;
	}

	boolean GoodDistance2(int objectX, int objectY, int playerX, int playerY, int distance) {
		for (int i = 0; i <= distance; i++) {
			for (int j = 0; j <= distance; j++) {
				if (objectX == playerX && ((objectY + j) == playerY || (objectY - j) == playerY || objectY == playerY)) {
					return true;
				} else if (objectY == playerY && ((objectX + j) == playerX || (objectX - j) == playerX || objectX == playerX)) {
					return true;
				}
			}
		}
		return false;
	}

	void AcceptTrade() {
		sendFrame248(3323, 3321);
		resetItems(3322);
		resetTItems(3415);
		resetOTItems(3416);
		Save();
		sendFrame126("Trading With: " + PlayerManager.players[tradeWith].playerName, 3417);
		sendFrame126("", 3431);
	}

	void DeclineTrade() {
		for (int i = 0; i < playerTItems.length; i++) {
			if (playerTItems[i] > 0 && !Accepted) {
				fromTrade((playerTItems[i] - 1), i, playerTItemsN[i]);
			}
		}
		Save();
		resetItems(3214);
		resetTrade();
		RemoveAllWindows();
	}


	void resetTrade() {
		tradeWith = 0;
		tradeStatus = 0;
		tradeUpdateOther = false;
		WanneTrade = 0;
		WanneTradeWith = 0;
		TradeConfirmed = false;
		Accepted = false;
		for (int i = 0; i < playerTItems.length; i++) {
			playerTItems[i] = 0;
			playerTItemsN[i] = 0;
			playerOTItems[i] = 0;
			playerOTItemsN[i] = 0;
		}
		Save();
	}

	void ConfirmTrade() {
		if (!TradeConfirmed) {
			RemoveAllWindows();
			for (int i = 0; i < playerOTItems.length; i++) {
				if (playerOTItems[i] > 0) {
					addItem((playerOTItems[i] - 1), playerOTItemsN[i]);
					saveTrade(PlayerManager.players[tradeWith].playerName + " trades item: " + (playerOTItems[i] - 1) + " amount: " + playerOTItemsN[i] + ".");
				}
			}
			resetItems(3214);
			TradeConfirmed = true;
			Save();
			Send("Accepted trade.");
		}
	}

	void TradeGoConfirm() {
		secondTradeWindow = true;
		sendFrame248(3443, 3213);
		resetItems(3214);
		String SendTrade = "Absolutely nothing!";
		String SendAmount = "";
		int Count = 0;

		for (int i = 0; i < playerTItems.length; i++) {
			if (playerTItems[i] > 0) {
				if (playerTItemsN[i] >= 1000 && playerTItemsN[i] < 1000000) {
					SendAmount = "@cya@" + (playerTItemsN[i] / 1000) + "K @whi@(" + playerTItemsN[i] + ")";
				} else if (playerTItemsN[i] >= 1000000) {
					SendAmount = "@gre@" + (playerTItemsN[i] / 1000000) + " million @whi@(" + playerTItemsN[i] + ")";
				} else {
					SendAmount = "" + playerTItemsN[i];
				}
				if (Count == 0) {
					SendTrade = GetItemName((playerTItems[i] - 1));
				} else {
					SendTrade = SendTrade + "\\n" + GetItemName((playerTItems[i] - 1));
				}
				if (Equipment.itemIsNote[(playerTItems[i] - 1)] || Equipment.itemStackable[(playerTItems[i] - 1)]) {
					SendTrade = SendTrade + " x " + SendAmount;
				}
				Count++;
			}
		}
		sendFrame126(SendTrade, 3557);
		SendTrade = "Absolutely nothing!";
		SendAmount = "";
		Count = 0;
		for (int i = 0; i < playerOTItems.length; i++) {
			if (playerOTItems[i] > 0) {
				if (playerOTItemsN[i] >= 1000 && playerOTItemsN[i] < 1000000) {
					SendAmount = "@cya@" + (playerOTItemsN[i] / 1000) + "K @whi@(" + playerOTItemsN[i] + ")";
				} else if (playerOTItemsN[i] >= 1000000) {
					SendAmount = "@gre@" + (playerOTItemsN[i] / 1000000) + " million @whi@(" + playerOTItemsN[i] + ")";
				} else {
					SendAmount = "" + playerOTItemsN[i];
				}
				if (Count == 0) {
					SendTrade = GetItemName((playerOTItems[i] - 1));
				} else {
					SendTrade = SendTrade + "\\n" + GetItemName((playerOTItems[i] - 1));
				}
				if (Equipment.itemIsNote[(playerOTItems[i] - 1)] || Equipment.itemStackable[(playerOTItems[i] - 1)]) {
					SendTrade = SendTrade + " x " + SendAmount;
				}
				Count++;
			}
		}
		sendFrame126(SendTrade, 3558);
	}

	boolean fromTrade(int itemID, int fromSlot, int amount) {
		if (amount > 0 && (itemID + 1) == playerTItems[fromSlot]) {
			if (amount > playerTItemsN[fromSlot]) {
				amount = playerTItemsN[fromSlot];
			}
			addItem((playerTItems[fromSlot] - 1), amount);
			if (amount == playerTItemsN[fromSlot]) {
				playerTItems[fromSlot] = 0;
				PlayerManager.players[tradeWith].playerOTItems[fromSlot] = 0;
			}
			playerTItemsN[fromSlot] -= amount;
			PlayerManager.players[tradeWith].playerOTItemsN[fromSlot] -= amount;
			resetItems(3322);
			resetTItems(3415);
			PlayerManager.players[tradeWith].tradeUpdateOther = true;
			if (PlayerManager.players[tradeWith].tradeStatus == 3) {
				PlayerManager.players[tradeWith].tradeStatus = 2;
				sendFrame126("", 3431);
			}
			return true;
		}
		return false;
	}

	boolean tradeItem(int itemID, int fromSlot, int amount) {
		if (tradeWith > 0) {
			if (PlayerManager.players[tradeWith] == null) {
				DeclineTrade();
				Send("An error has occured while trading.");
				return false;
			}
		} else {
			DeclineTrade();
			Send("An error has occured while trading.");
			return false;
		}
		if (amount > 0 && itemID == (playerItems[fromSlot] - 1)) {
			if (amount > playerItemsN[fromSlot]) {
				amount = playerItemsN[fromSlot];
			}
			boolean IsInTrade = false;

			for (int i = 0; i < playerTItems.length; i++) {
				if (playerTItems[i] == playerItems[fromSlot]) {
					if (Equipment.itemStackable[(playerItems[fromSlot] - 1)] || Equipment.itemIsNote[(playerItems[fromSlot] - 1)]) {
						playerTItemsN[i] += amount;
						PlayerManager.players[tradeWith].playerOTItemsN[i] += amount;
						IsInTrade = true;
						break;
					}
				}
			}
			if (!IsInTrade) {
				for (int i = 0; i < playerTItems.length; i++) {
					if (playerTItems[i] <= 0) {
						playerTItems[i] = playerItems[fromSlot];
						playerTItemsN[i] = amount;
						PlayerManager.players[tradeWith].playerOTItems[i] = playerItems[fromSlot];
						PlayerManager.players[tradeWith].playerOTItemsN[i] = amount;
						break;
					}
				}
			}
			if (amount == playerItemsN[fromSlot]) {
				playerItems[fromSlot] = 0;
			}
			playerItemsN[fromSlot] -= amount;
			resetItems(3322);
			resetTItems(3415);
			PlayerManager.players[tradeWith].tradeUpdateOther = true;
			if (PlayerManager.players[tradeWith].tradeStatus == 3) {
				PlayerManager.players[tradeWith].tradeStatus = 2;
				sendFrame126("", 3431);
			}
			return true;
		}
		return false;
	}

	boolean sellItem(int itemID, int fromSlot, int amount) {
		if (amount > 0 && itemID == (playerItems[fromSlot] - 1)) {
			if (Server.shop.ShopSModifier[MyShopID] > 1) {
				boolean IsIn = false;

				for (int i = 0; i <= Server.shop.ShopItemsStandard[MyShopID]; i++) {
					if (itemID == (Server.shop.ShopItems[MyShopID][i] - 1)) {
						IsIn = true;
						break;
					}
				}
				if (!IsIn) {
					Send("You cannot sell " + GetItemName(itemID) + " in this store.");
					return false;
				}
			}
			if (!Equipment.itemSellable[(playerItems[fromSlot] - 1)]) {
				Send("I cannot sell " + GetItemName(itemID) + ".");
				return false;
			}
			if (amount > playerItemsN[fromSlot] && (Equipment.itemIsNote[(playerItems[fromSlot] - 1)] || Equipment.itemStackable[(playerItems[fromSlot] - 1)])) {
				amount = playerItemsN[fromSlot];
			} else if (amount > GetXItemsInBag(itemID) && !Equipment.itemIsNote[(playerItems[fromSlot] - 1)] && !Equipment.itemStackable[(playerItems[fromSlot] - 1)]) {
				amount = GetXItemsInBag(itemID);
			}
			double ShopValue;
			double TotPrice;
			int TotPrice2;
			int Overstock;

			for (int i = amount; i > 0; i--) {
				TotPrice2 = (int) Math.floor(GetItemShopValue(itemID, 1, fromSlot));
				if (freeSlots() > 0 && !Equipment.itemStackable[itemID]) {
					if (!Equipment.itemIsNote[itemID]) {
						deleteItem(itemID, GetItemSlot(itemID), 1);
					} else {
						deleteItem(itemID, fromSlot, 1);
					}
					addItem(995, TotPrice2);
					addShopItem(itemID, 1);
				} else {
					Send("Not enough space in your inventory.");
					break;
				}
			}
			resetItems(3823);
			resetShop(MyShopID);
			UpdatePlayerShop();
			return true;
		}
		return true;
	}

	boolean buyItem(int itemID, int fromSlot, int amount) {
		if (amount > 0 && itemID == (Server.shop.ShopItems[MyShopID][fromSlot] - 1)) {
			if (amount > Server.shop.ShopItemsN[MyShopID][fromSlot]) {
				amount = Server.shop.ShopItemsN[MyShopID][fromSlot];
			}
			double ShopValue;
			double TotPrice;
			int TotPrice2;
			int Overstock;
			int Slot = 0;
			int Slot2 = 0;
			int Slot3 = 0;

			for (int i = amount; i > 0; i--) {
				TotPrice2 = (int) Math.floor(GetItemShopValue(itemID, 0, fromSlot));
				Slot = GetItemSlot(995);
				if (Slot == -1) {
					Send("You don't have enough coins.");
					break;
				}

				if (MyShopID == 4) {
					if (itemID == 2678 && hasAmount(995, 99000) && playerLevel[0] == 99) {
						addItem(2679, 1);
					} else if (itemID == 2678 && hasAmount(995, 99000) && playerLevel[0] < 99) {
						Send("You aren't high enough level to purchase the Attack skillcape.");
						return false;

					}
					if (itemID == 2681 && hasAmount(995, 99000) && playerLevel[2] == 99) {
						addItem(2682, 1);
					} else if (itemID == 2681 && hasAmount(995, 99000) && playerLevel[2] < 99) {
						Send("You aren't high enough level to purchase the Strength skillcape.");
						return false;
					}
					if (itemID == 2684 && hasAmount(995, 99000) && playerLevel[1] == 99) {
						addItem(2685, 1);
					} else if (itemID == 2684 && hasAmount(995, 99000) && playerLevel[1] < 99) {
						Send("You aren't high enough level to purchase the Defence skillcape.");
						return false;
					}
					if (itemID == 2690 && hasAmount(995, 99000) && playerLevel[5] == 99) {
						addItem(2691, 1);
					} else if (itemID == 2690 && hasAmount(995, 99000) && playerLevel[5] < 99) {
						Send("You aren't high enough level to purchase the Prayer skillcape.");
						return false;
					}
					if (itemID == 2696 && hasAmount(995, 99000) && playerLevel[11] == 99) {
						addItem(2697, 1);
					} else if (itemID == 2696 && hasAmount(995, 99000) && playerLevel[11] < 99) {
						Send("You aren't high enough level to purchase the Firemaking skillcape.");
						return false;
					}
					if (itemID == 2699 && hasAmount(995, 99000) && playerLevel[20] == 99) {
						addItem(2700, 1);
					} else if (itemID == 2699 && hasAmount(995, 99000) && playerLevel[20] < 99) {
						Send("You aren't high enough level to purchase the Runecrafting skillcape.");
						return false;
					}
					if (itemID == 2702 && hasAmount(995, 99000) && playerLevel[3] == 99) {
						addItem(2703, 1);
					} else if (itemID == 2702 && hasAmount(995, 99000) && playerLevel[3] < 99) {
						Send("You aren't high enough level to purchase the Hitpoints skillcape.");
						return false;
					}
					if (itemID == 2705 && hasAmount(995, 99000) && playerLevel[16] == 99) {
						addItem(2706, 1);
					} else if (itemID == 2705 && hasAmount(995, 99000) && playerLevel[16] < 99) {
						Send("You aren't high enough level to purchase the Agility skillcape.");
						return false;
					}
					if (itemID == 2708 && hasAmount(995, 99000) && playerLevel[15] == 99) {
						addItem(2709, 1);
					} else if (itemID == 2708 && hasAmount(995, 99000) && playerLevel[15] < 99) {
						Send("You aren't high enough level to purchase the Herblore skillcape.");
						return false;
					}
					if (itemID == 2711 && hasAmount(995, 99000) && playerLevel[17] == 99) {
						addItem(2712, 1);
					} else if (itemID == 2711 && hasAmount(995, 99000) && playerLevel[17] < 99) {
						Send("You aren't high enough level to purchase the Thieving skillcape.");
						return false;
					}
					if (itemID == 2714 && hasAmount(995, 99000) && playerLevel[12] == 99) {
						addItem(2715, 1);
					} else if (itemID == 2714 && hasAmount(995, 99000) && playerLevel[12] < 99) {
						Send("You aren't high enough level to purchase the Crafting skillcape.");
						return false;
					}
					if (itemID == 2717 && hasAmount(995, 99000) && playerLevel[9] == 99) {
						addItem(2718, 1);
					} else if (itemID == 2717 && hasAmount(995, 99000) && playerLevel[9] < 99) {
						Send("You aren't high enough level to purchase the Fletching skillcape.");
						return false;
					}
					if (itemID == 2723 && hasAmount(995, 99000) && playerLevel[14] == 99) {
						addItem(2724, 1);
					} else if (itemID == 2723 && hasAmount(995, 99000) && playerLevel[14] < 99) {
						Send("You aren't high enough level to purchase the Mining skillcape.");
						return false;
					}
					if (itemID == 2726 && hasAmount(995, 99000) && playerLevel[13] == 99) {
						addItem(2727, 1);
					} else if (itemID == 2726 && hasAmount(995, 99000) && playerLevel[13] < 99) {
						Send("You aren't high enough level to purchase the Smithing skillcape.");
						return false;
					}
					if (itemID == 2729 && hasAmount(995, 99000) && playerLevel[10] == 99) {
						addItem(2730, 1);
					} else if (itemID == 2729 && hasAmount(995, 99000) && playerLevel[10] < 99) {
						Send("You aren't high enough level to purchase the Fishing skillcape.");
						return false;
					}
					if (itemID == 2732 && hasAmount(995, 99000) && playerLevel[7] == 99) {
						addItem(2733, 1);
					} else if (itemID == 2732 && hasAmount(995, 99000) && playerLevel[7] < 99) {
						Send("You aren't high enough level to purchase the Cooking skillcape.");
						return false;
					}
					if (itemID == 2735 && hasAmount(995, 99000) && playerLevel[8] == 99) {
						addItem(2736, 1);
					} else if (itemID == 2735 && hasAmount(995, 99000) && playerLevel[8] < 99) {
						Send("You aren't high enough level to purchase the Woodcutting skillcape.");
						return false;
					}
					if (itemID == 2738 && hasAmount(995, 99000) && playerLevel[19] == 99) {
						addItem(2739, 1);
					} else if (itemID == 2738 && hasAmount(995, 99000) && playerLevel[19] < 99) {
						Send("You aren't high enough level to purchase the Farming skillcape.");
						return false;
					}
				}
				if (MyShopID == 9) {
					if (itemID == 2693 && hasAmount(995, 99000) && playerLevel[6] == 99) {
						addItem(2694, 1);
					} else if (itemID == 2693 && hasAmount(995, 99000) && playerLevel[6] < 99) {
						Send("You aren't high enough level to purchase the Magic skillcape.");
						return false;
					}
				}
				if (MyShopID == 10) {
					if (itemID == 2687 && hasAmount(995, 99000) && playerLevel[4] == 99) {
						addItem(2688, 1);
					} else if (itemID == 2687 && hasAmount(995, 99000) && playerLevel[4] < 99) {
						Send("You aren't high enough level to purchase the Ranging skillcape.");
						return false;
					}
				}
				if (MyShopID == 12) {
					if (itemID == 2720 && hasAmount(995, 99000) && playerLevel[18] == 99) {
						addItem(2721, 1);
					} else if (itemID == 2720 && hasAmount(995, 99000) && playerLevel[18] < 99) {
						Send("You aren't high enough level to purchase the PKing skillcape.");
						return false;
					}
				}

				if (TotPrice2 <= 1) {
					TotPrice2 = (int) Math.floor(GetItemShopValue(itemID, 0, fromSlot));
				}
				if (playerItemsN[Slot] >= TotPrice2) {
					if (freeSlots() > 0) {
						deleteItem(995, GetItemSlot(995), TotPrice2);
						addItem(itemID, 1);
						Server.shop.ShopItemsN[MyShopID][fromSlot] -= 1;
						Server.shop.ShopItemsDelay[MyShopID][fromSlot] = 0;
						if ((fromSlot + 1) > Server.shop.ShopItemsStandard[MyShopID]) {
							Server.shop.ShopItems[MyShopID][fromSlot] = 0;
						}
					} else {
						Send("Not enough space in your inventory.");
						break;
					}
				} else {
					Send("You don't have enough coins.");
					break;
				}
			}
			resetItems(3823);
			resetShop(MyShopID);
			UpdatePlayerShop();
			return true;
		}
		return false;
	}

	void UpdatePlayerShop() {
		for (int i = 1; i < PlayerManager.maxPlayers; i++) {
			if (PlayerManager.players[i] != null) {
				if (PlayerManager.players[i].IsShopping && PlayerManager.players[i].MyShopID == MyShopID && i != playerId) {
					PlayerManager.players[i].UpdateShop = true;
				}
			}
		}
	}

	boolean addShopItem(int itemID, int amount) {
		boolean Added = false;

		if (amount <= 0) {
			return false;
		}
		if (Equipment.itemIsNote[itemID]) {
			itemID = GetUnnotedItem(itemID);
		}
		for (int i = 0; i < Server.shop.ShopItems.length; i++) {
			if ((Server.shop.ShopItems[MyShopID][i] - 1) == itemID) {
				Server.shop.ShopItemsN[MyShopID][i] += amount;
				Added = true;
			}
		}
		if (!Added) {
			for (int i = 0; i < Server.shop.ShopItems.length; i++) {
				if (Server.shop.ShopItems[MyShopID][i] == 0) {
					Server.shop.ShopItems[MyShopID][i] = (itemID + 1);
					Server.shop.ShopItemsN[MyShopID][i] = amount;
					Server.shop.ShopItemsDelay[MyShopID][i] = 0;
					break;
				}
			}
		}
		return true;
	}

	void UpdateNPCChat() {
		Server.Dialogue.sendDialogue(this, Dialogue);
	}

	int GetCLAttack(int ItemID) {
		if (ItemID == 2678 || ItemID == 2679) {
			return 99;
		}
		if (ItemID == 4718|| ItemID == 4747|| ItemID == 4718|| ItemID == 4726) {
			return 70;
		}
		if (ItemID == 2745|| ItemID == 2746|| ItemID == 2747|| ItemID == 2748|| ItemID == 7630) {
			return 75;
		}
		if (ItemID == 3202 || ItemID == 3101) {
			return 40;
		}
		if (ItemID == 7158) {
			return 60;
		}
		if (ItemID == -1) {
			return 1;
		}
		String ItemName = GetItemName(ItemID);
		if (ItemName.startsWith("Bronze")) {
			return 1;
		}
		if (ItemName.startsWith("Iron")) {
			return 1;
		}
		if (ItemName.startsWith("Steel")) {
			return 5;
		}
		if (ItemName.startsWith("Black")) {
			return 10;
		}
		if (ItemName.startsWith("Mithril")) {
			return 20;
		}
		if (ItemName.startsWith("Adamant")) {
			return 30;
		}
		if (ItemName.startsWith("Rune")) {
			return 40;
		}
		if (ItemName.startsWith("Dragon")) {
			return 60;
		}
		if (ItemName.endsWith("axe")) {
			return 1;
		}
		if (ItemName.endsWith("whip") || ItemName.endsWith("Ahrims staff") || ItemName.endsWith("Torags hammers") || ItemName.endsWith("Veracs flail") || ItemName.endsWith("Guthans warspear") || ItemName.endsWith("Dharoks greataxe")) {
			return 70;
		}
		return 1;
	}

	int GetCLHitpoints(int ItemID) {
		if (ItemID == 2702 || ItemID == 2703) {
			return 99;
		}
		return 1;
	}

	int GetCLPrayer(int ItemID) {
		if (ItemID == 2690 || ItemID == 2691) {
			return 99;
		}
		return 1;
	}

	int GetCLHerblore(int ItemID) {
		if (ItemID == 2708 || ItemID == 2709) {
			return 99;
		}
		return 1;
	}

	int GetCLFarming(int ItemID) {
		if (ItemID == 2738 || ItemID == 2739) {
			return 99;
		}
		return 1;
	}

	int GetCLMining(int ItemID) {
		if (ItemID == 2723 || ItemID == 2724) {
			return 99;
		}
		return 1;
	}

	int GetCLSmithing(int ItemID) {
		if (ItemID == 2726 || ItemID == 2727) {
			return 99;
		}
		return 1;
	}

	int GetCLRunecrafting(int ItemID) {
		if (ItemID == 2699 || ItemID == 2700) {
			return 99;
		}
		return 1;
	}

	int GetCLFletching(int ItemID) {
		if (ItemID == 2717 || ItemID == 2718) {
			return 99;
		}
		return 1;
	}

	int GetCLWoodcutting(int ItemID) {
		if (ItemID == 2735 || ItemID == 2736) {
			return 99;
		}
		return 1;
	}

	int GetCLCooking(int ItemID) {
		if (ItemID == 2732 || ItemID == 2733) {
			return 99;
		}
		return 1;
	}

	int GetCLFishing(int ItemID) {
		if (ItemID == 2729 || ItemID == 2730) {
			return 99;
		}
		return 1;
	}

	int GetCLThieving(int ItemID) {
		if (ItemID == 2711 || ItemID == 2712) {
			return 99;
		}
		return 1;
	}

	int GetCLAgility(int ItemID) {
		if (ItemID == 2705 || ItemID == 2706) {
			return 99;
		}
		return 1;
	}

	int GetCLPKing(int ItemID) {
		if (ItemID == 2720 || ItemID == 2721) {
			return 99;
		}
		return 1;
	}

	int GetCLDefence(int ItemID) {
		if (ItemID == 2684 || ItemID == 2685) {
			return 99;
		}
		if (ItemID == 7606 || ItemID == 7607 || ItemID == 2774 || ItemID == 3619 || ItemID == 3620 || ItemID == 3621 || ItemID == 3622 || ItemID == 3623 || ItemID == 3624 || ItemID == 3625 || ItemID == 3626 || ItemID == 3627 || ItemID == 3628 || ItemID == 3629) {
			return 75;
		}
		if (ItemID == 7461) {
			return 60;
		}
		if (ItemID == 7610) {
			return 55;
		}
		if (ItemID == 7462) {
			return 45;
		}
		if (ItemID == 3751 || ItemID == 3748 || ItemID == 3749 || ItemID == 3751 || ItemID == 3753 || ItemID == 3755 || ItemID == 7601 || ItemID == 7602) {
			return 45;
		}
		if (ItemID == 2806 || ItemID == 2807 || ItemID == 2808 || ItemID == 2809 || ItemID == 2802 || ItemID == 2803 || ItemID == 2804 || ItemID == 2805 || ItemID == 2799 || ItemID == 2800 || ItemID == 2801 || ItemID == 2798 || ItemID == 2503 || ItemID == 7460 || ItemID == 2501 || ItemID == 1163 || ItemID == 7460 || ItemID == 1135 || ItemID == 1127 || ItemID == 1079 || ItemID == 1093 || ItemID == 1201 || ItemID == 1185 || ItemID == 4131) {
			return 40;
		}
		if (ItemID == 7459 || ItemID == 4089 || ItemID == 4091 || ItemID == 4093 || ItemID == 4095 || ItemID == 4097 || ItemID == 4099 || ItemID == 4101 || ItemID == 4103 || ItemID == 4105 || ItemID == 4107 || ItemID == 4109 || ItemID == 4111 || ItemID == 4113 || ItemID == 4115 || ItemID == 4117) {
			return 20;
		}
		if (ItemID == 2497 || ItemID == 892 || ItemID == 882 || ItemID == 886 || ItemID == 2491 || ItemID == 1065 || ItemID == 1099 || ItemID == 2489 || ItemID == 2495 || ItemID == 2493 || ItemID == 2487) {
			return 1;
		}
		String ItemName = GetItemName(ItemID);
		if (ItemName.startsWith("Bronze")) {
			return 1;
		} else if (ItemName.startsWith("Iron")) {
			return 1;
		} else if (ItemName.startsWith("Initiate")) {
			return 20;
		} else if (ItemName.startsWith("Infinity")) {
			return 20;
		} else if (ItemName.startsWith("Steel")) {
			return 5;
		} else if (ItemName.startsWith("Mithril")) {
			return 20;
		} else if (ItemName.startsWith("Adamant")) {
			return 30;
		} else if (ItemName.startsWith("Rune")) {
			return 40;
		} else if (ItemName.startsWith("Dragon")) {
			return 60;
		} else if (ItemName.startsWith("dragon")) {
			return 60;
		} else if (ItemName.endsWith("axe")) {
			return 1;
		}
		return 1;
	}

	int GetCLStrength(int ItemID) {
		if (ItemID == 2681 || ItemID == 2682) {
			return 99;
		}
		if (ItemID == 6528) {
			return 60;
		}
		if (ItemID == 2745|| ItemID == 2746|| ItemID == 2747|| ItemID == 2748|| ItemID == 7630) {
			return 75;
		}
		return 1;
	}

	int GetCLMagic(int ItemID) {
		if (ItemID == 2693 || ItemID == 2694) {
			return 99;
		}
		if (ItemID == 3623 || ItemID == 3624 || ItemID == 3625) {
			return 75;
		}
		if (ItemID == 6918 || ItemID == 6916 || ItemID == 6924 || ItemID == 6920 || ItemID == 6922) {
			return 70;
		}
		if (ItemID == 4675 || ItemID == 2412 || ItemID == 2414 || ItemID == 2413) {
			return 60;
		}
		String ItemName = GetItemName(ItemID);
		if (ItemName.startsWith("Ahrim")) {
			return 70;
		}
		return 1;
	}

	int GetCLRanged(int ItemID) {
		if (ItemID == 2687 || ItemID == 2688) {
			return 99;
		}
		if (ItemID == 3626 || ItemID == 3627 || ItemID == 3628 || ItemID == 3629) {
			return 75;
		}
		if (ItemID == 2806 || ItemID == 2807 || ItemID == 2808 || ItemID == 2809 || ItemID == 2761 || ItemID == 2802 || ItemID == 2803 || ItemID == 2804 || ItemID == 2805 || ItemID == 2799 || ItemID == 2800 || ItemID == 2801 || ItemID == 2798 || ItemID == 2501 || ItemID == 2495 || ItemID == 2489 || ItemID == 7603) {
			return 60;
		}
		if (ItemID == 859 || ItemID == 861) {
			return 50;
		}
		if (ItemID == 1135 || ItemID == 1099 || ItemID == 1065 || ItemID == 2577 || ItemID == 2581) {
			return 40;
		}
		if (ItemID == 2497) {
			return 70;
		}
		String ItemName = GetItemName(ItemID);
		if (ItemName.startsWith("Karil")) {
			return 70;
		}
		if (ItemName.startsWith("Crystal")) {
			return 70;
		}
		if (ItemName.startsWith("Seercull")) {
			return 70;
		}
		return 1;
	}

	int GetWorld(int PlayerID) {
		try {
			return 1;
		} catch (Exception e) {
			printOut(e.toString());
			return 1;
		}
	}

	int loadGame(String playerName, String playerPass) {
		String line = "";
		String token = "";
		String token2 = "";
		String[] token3 = new String[3];
		boolean EndOfFile = false;
		int ReadMode = 0;
		BufferedReader characterfile = null;
		BufferedReader characterfile2 = null;
		boolean File1 = false;
		boolean File2 = false;
		String FTPAdress = "";
		int World = GetWorld(playerId);

		if (World == 2) {
		}
		try {
			characterfile = new BufferedReader(new FileReader("../../Files/Players/Characters/" + playerName + ".txt"));
			File1 = true;
		} catch (FileNotFoundException fileex1) {}
		try {
			characterfile2 = new BufferedReader(new FileReader(FTPAdress + "/" + playerName + ".txt"));
			File2 = true;
		} catch (FileNotFoundException fileex2) {}
		if (File1 && File2) {
			File myfile1 = new File("../../Files/Players/Characters/" + playerName + ".txt");
			File myfile2 = new File(FTPAdress + "/" + playerName + ".txt");

			if (myfile1.lastModified() < myfile2.lastModified()) {
				characterfile = characterfile2;
			}
		} else if (!File1 && File2) {
			characterfile = characterfile2;
		} else if (!File1 && !File2) {
			return 3;
		}
		try {
			line = characterfile.readLine();
		} catch (IOException ioexception) {
			printOut("Error loading file.");
			return 3;
		}
		while (!EndOfFile && line != null) {
			line = line.trim();
			int spot = line.indexOf("=");

			if (spot > -1) {
				token = line.substring(0, spot);
				token = token.trim();
				token2 = line.substring(spot + 1);
				token2 = token2.trim();
				token3 = token2.split("\t");
				switch (ReadMode) {

				case 1:
					if (token.equals("character-username")) {
						if (playerName.equalsIgnoreCase(token2)) {} else {
							return 2;
						}
					} else if (token.equals("character-password")) {
						if (playerPass.equalsIgnoreCase(token2)) {} else {
							return 2;
						}
					}
					break;

				case 2:
					if (token.equals("character-height")) {
						heightLevel = Integer.parseInt(token2);
					} else if (token.equals("character-posx")) {
						teleportToX = Integer.parseInt(token2);
					} else if (token.equals("character-posy")) {
						teleportToY = Integer.parseInt(token2);
					} else if (token.equals("character-rights")) {
						Rights = Integer.parseInt(token2);
					} else if (token.equals("character-donator")) {
						Donator = Integer.parseInt(token2);
					} else if (token.equals("character-spellbook")) {
						spellbook = Integer.parseInt(token2);
					} else if (token.equals("character-specialamount")) {
						specialAmount = Integer.parseInt(token2);
					} else if (token.equals("character-blackmarks")) {
						Blackmarks = Integer.parseInt(token2);
					} else if (token.equals("character-kills")) {
						PkKills = Integer.parseInt(token2);
					} else if (token.equals("character-deaths")) {
						deaths = Integer.parseInt(token2);
					} else if (token.equals("character-pkdeaths")) {
						pkDeaths = Integer.parseInt(token2);
					} else if (token.equals("character-pkrating")) {
						pkRating = Integer.parseInt(token2);
					} else if (token.equals("character-pkpoints")) {
						pkPoints = Integer.parseInt(token2);
					} else if (token.equals("character-npckills")) {
						NpcKills = Integer.parseInt(token2);
					} else if (token.equals("character-pin1")) {
						Pin1 = Integer.parseInt(token2);
					} else if (token.equals("character-pin2")) {
						Pin2 = Integer.parseInt(token2);
					} else if (token.equals("character-pin3")) {
						Pin3 = Integer.parseInt(token2);
					} else if (token.equals("character-pin4")) {
						Pin4 = Integer.parseInt(token2);
					}
					break;

				case 3:
					if (token.equals("character-sarakillcount")) {
						SarakillCount = Integer.parseInt(token2);
					} else if (token.equals("character-barbkillcount")) {
						Barbpoints = Integer.parseInt(token2);
					} else if (token.equals("character-barrkillcount")) {
						BarrowskillCount = Integer.parseInt(token2);
					} else if (token.equals("character-magearenapoints")) {
						magearenapoints = Integer.parseInt(token2);
					} else if (token.equals("character-canvengeance")) {
						vengeanceSpell = Integer.parseInt(token2);
					} else if (token.equals("character-crysbowcharges")) {
						Charges = Integer.parseInt(token2);
					} else if (token.equals("character-fightcaves")) {
						JadKilled = Integer.parseInt(token2);
					} else if (token.equals("character-cluelevel")) {
						cluescroll = Integer.parseInt(token2);
					}
					break;
					
				case 4:
					if (token.equals("character-quests")) {
						for (int i = 0; i < qName.length; i++) {
							qStage[i] = Integer.parseInt(token3[i]);
						}
					} else if (token.equals("character-qpoints")) {
						qpoints = Integer.parseInt(token2);
					}
					break;

				case 5:
					if (token.equals("character-equip")) {
						playerEquipment[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						playerEquipmentN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					}
					break;

				case 6:
					if (token.equals("character-look")) {
						playerLook[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
					}
					if (token.equals("character-head")) {
						pHead = Integer.parseInt(token2);
					}
					if (token.equals("character-torso")) {
						pTorso = Integer.parseInt(token2);
					}
					if (token.equals("character-arms")) {
						pArms = Integer.parseInt(token2);
					}
					if (token.equals("character-hands")) {
						pHands = Integer.parseInt(token2);
					}
					if (token.equals("character-legs")) {
						pLegs = Integer.parseInt(token2);
					}
					if (token.equals("character-feet")) {
						pFeet = Integer.parseInt(token2);
					}
					if (token.equals("character-beard")) {
						pBeard = Integer.parseInt(token2);
					}
					break;

				case 7:
					if (token.equals("character-skill")) {
						playerLevel[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						playerXP[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					}
					break;

				case 8:
					if (token.equals("character-item")) {
						playerItems[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						playerItemsN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					}
					break;

				case 9:
					if (token.equals("character-bank")) {
						bankItems[Integer.parseInt(token3[0])] = Integer.parseInt(token3[1]);
						bankItemsN[Integer.parseInt(token3[0])] = Integer.parseInt(token3[2]);
					}
					break;

				case 10:
					if (token.equals("character-friend")) {
						friends[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
					}
					break;

				case 11:
					if (token.equals("character-ignore")) {
						ignores[Integer.parseInt(token3[0])] = Long.parseLong(token3[1]);
					}
					break;
				case 12:
					if (token.equals("character-chatsplit")) {
						SplitChat = Integer.parseInt(token2);
					} else if (token.equals("character-brightness")) {
						BrightnessLevel = Integer.parseInt(token2);
					} else if (token.equals("character-fighttype")) {
						FightType = Integer.parseInt(token2);
					} else if (token.equals("character-running")) {
						Running = Integer.parseInt(token2);
					}
					break;
				}
			} else {
				if (line.equals("[ACCOUNT]")) {
					ReadMode = 1;
				} else if (line.equals("[CHARACTER]")) {
					ReadMode = 2;
				} else if (line.equals("[MINIGAMES]")) {
					ReadMode = 3;
				} else if (line.equals("[QUESTS]")) {
					ReadMode = 4;
				} else if (line.equals("[EQUIPMENT]")) {
					ReadMode = 5;
				} else if (line.equals("[LOOK]")) {
					ReadMode = 6;
				} else if (line.equals("[SKILLS]")) {
					ReadMode = 7;
				} else if (line.equals("[ITEMS]")) {
					ReadMode = 8;
				} else if (line.equals("[BANK]")) {
					ReadMode = 9;
				} else if (line.equals("[FRIENDS]")) {
					ReadMode = 10;
				} else if (line.equals("[IGNORES]")) {
					ReadMode = 11;
				} else if (line.equals("[SETTINGS]")) {
					ReadMode = 12;
				} else if (line.equals("[EOF]")) {
					try {
						characterfile.close();
					} catch (IOException ioexception) {}
					return 1;
				}
			}
			try {
				line = characterfile.readLine();
			} catch (IOException ioexception1) {
				EndOfFile = true;
			}
		}
		try {
			characterfile.close();
		} catch (IOException ioexception) {
		}
		return 3;
	}

	boolean Save() {
		BufferedWriter characterfile = null;

		try {
			characterfile = new BufferedWriter(new FileWriter("../../Files/Players/Characters/" + playerName + ".txt"));

			characterfile.write("[ACCOUNT]", 0, 9);
			characterfile.newLine();
			characterfile.write("character-username = ", 0, 21);
			characterfile.write(playerName, 0, playerName.length());
			characterfile.newLine();
			characterfile.write("character-password = ", 0, 21);
			characterfile.write(playerPass, 0, playerPass.length());
			characterfile.newLine();
			characterfile.newLine();

			characterfile.write("[CHARACTER]", 0, 11);
			characterfile.newLine();
			characterfile.write("character-height = ", 0, 19);
			characterfile.write(Integer.toString(heightLevel), 0, Integer.toString(heightLevel).length());
			characterfile.newLine();
			characterfile.write("character-posx = ", 0, 17);
			characterfile.write(Integer.toString(absX), 0, Integer.toString(absX).length());
			characterfile.newLine();
			characterfile.write("character-posy = ", 0, 17);
			characterfile.write(Integer.toString(absY), 0, Integer.toString(absY).length());
			characterfile.newLine();
			characterfile.write("character-rights = ", 0, 19);
			characterfile.write(Integer.toString(Rights), 0, Integer.toString(Rights).length());
			characterfile.newLine();
			characterfile.write("character-donator = ", 0, 20);
			characterfile.write(Integer.toString(Donator), 0, Integer.toString(Donator).length());
			characterfile.newLine();
			characterfile.write("character-spellbook = ", 0, 22);
			characterfile.write(Integer.toString(spellbook), 0, Integer.toString(spellbook).length());
			characterfile.newLine();
			characterfile.write("character-specialamount = ", 0, 26);
			characterfile.write(Integer.toString(specialAmount), 0, Integer.toString(specialAmount).length());
			characterfile.newLine();
			characterfile.write("character-blackmarks = ", 0, 23);
			characterfile.write(Integer.toString(Blackmarks), 0, Integer.toString(Blackmarks).length());
			characterfile.newLine();
			characterfile.write("character-kills = ", 0, 18);
			characterfile.write(Integer.toString(PkKills), 0, Integer.toString(PkKills).length());
			characterfile.newLine();
			characterfile.write("character-deaths = ", 0, 19);
			characterfile.write(Integer.toString(deaths), 0, Integer.toString(deaths).length());
			characterfile.newLine();
			characterfile.write("character-pkdeaths = ", 0, 21);
			characterfile.write(Integer.toString(pkDeaths), 0, Integer.toString(pkDeaths).length());
			characterfile.newLine();
			characterfile.write("character-pkpoints = ", 0, 21);
			characterfile.write(Integer.toString(pkPoints), 0, Integer.toString(pkPoints).length());
			characterfile.newLine();
			characterfile.write("character-pkrating = ", 0, 21);
			characterfile.write(Integer.toString(pkRating), 0, Integer.toString(pkRating).length());
			characterfile.newLine();
			characterfile.write("character-npckills = ", 0, 21);
			characterfile.write(Integer.toString(NpcKills), 0, Integer.toString(NpcKills).length());
			characterfile.newLine();
			characterfile.write("character-pin1 = ", 0, 17);
			characterfile.write(Integer.toString(Pin1), 0, Integer.toString(Pin1).length());
			characterfile.newLine();
			characterfile.write("character-pin2 = ", 0, 17);
			characterfile.write(Integer.toString(Pin2), 0, Integer.toString(Pin2).length());
			characterfile.newLine();
			characterfile.write("character-pin3 = ", 0, 17);
			characterfile.write(Integer.toString(Pin3), 0, Integer.toString(Pin3).length());
			characterfile.newLine();
			characterfile.write("character-pin4 = ", 0, 17);
			characterfile.write(Integer.toString(Pin4), 0, Integer.toString(Pin4).length());
			characterfile.newLine();
			characterfile.newLine();

			characterfile.write("[SETTINGS]", 0, 10);
			characterfile.newLine();
			characterfile.write("character-chatsplit = ", 0, 22);
			characterfile.write(Integer.toString(SplitChat), 0, Integer.toString(SplitChat).length());
			characterfile.newLine();			
			characterfile.write("character-brightness = ", 0, 23);
			characterfile.write(Integer.toString(BrightnessLevel), 0, Integer.toString(BrightnessLevel).length());
			characterfile.newLine();
			characterfile.write("character-fighttype = ", 0, 22);
			characterfile.write(Integer.toString(FightType), 0, Integer.toString(FightType).length());
			characterfile.newLine();
			characterfile.write("character-running = ", 0, 20);
			characterfile.write(Integer.toString(Running), 0, Integer.toString(Running).length());
			characterfile.newLine();
			characterfile.newLine();
			
			characterfile.write("[QUESTS]", 0, 8);
			characterfile.newLine();
			characterfile.write("character-quests = ", 0, 19);
			for (int i = 0; i < qName.length; i++) {
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(qStage[i]), 0, Integer.toString(qStage[i]).length());
			}
			characterfile.newLine();
			characterfile.write("character-qpoints = ", 0, 20);
			characterfile.write(Integer.toString(qpoints), 0, Integer.toString(qpoints).length());
			characterfile.newLine();
			characterfile.newLine();
			
			characterfile.write("[MINIGAMES]", 0, 11);
			characterfile.newLine();
			characterfile.write("character-sarakillcount = ", 0, 26);
			characterfile.write(Integer.toString(SarakillCount), 0, Integer.toString(SarakillCount).length());
			characterfile.newLine();
			characterfile.write("character-barbkillcount = ", 0, 26);
			characterfile.write(Integer.toString(Barbpoints), 0, Integer.toString(Barbpoints).length());
			characterfile.newLine();
			characterfile.write("character-barrkillcount = ", 0, 26);
			characterfile.write(Integer.toString(BarrowskillCount), 0, Integer.toString(BarrowskillCount).length());
			characterfile.newLine();
			characterfile.write("character-magearenapoints = ", 0, 28);
			characterfile.write(Integer.toString(magearenapoints), 0, Integer.toString(magearenapoints).length());
			characterfile.newLine();
			characterfile.write("character-canvengeance = ", 0, 25);
			characterfile.write(Integer.toString(vengeanceSpell), 0, Integer.toString(vengeanceSpell).length());
			characterfile.newLine();
			characterfile.write("character-crysbowcharges = ", 0, 27);
			characterfile.write(Integer.toString(Charges), 0, Integer.toString(Charges).length());
			characterfile.newLine();
			characterfile.write("character-fightcaves = ", 0, 23);
			characterfile.write(Integer.toString(JadKilled), 0, Integer.toString(JadKilled).length());
			characterfile.newLine();
			characterfile.write("character-cluelevel = ", 0, 22);
			characterfile.write(Integer.toString(cluescroll), 0, Integer.toString(cluescroll).length());
			characterfile.newLine();
			characterfile.newLine();

			characterfile.write("[EQUIPMENT]", 0, 11);
			characterfile.newLine();
			for (int i = 0; i < playerEquipment.length; i++) {
				characterfile.write("character-equip = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerEquipment[i]), 0, Integer.toString(playerEquipment[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerEquipmentN[i]), 0, Integer.toString(playerEquipmentN[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.newLine();
			}
			characterfile.newLine();

			characterfile.write("[LOOK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < playerLook.length; i++) {
				characterfile.write("character-look = ", 0, 17);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerLook[i]), 0, Integer.toString(playerLook[i]).length());
				characterfile.newLine();
				characterfile.write("character-head = ", 0, 17);
				characterfile.write(Integer.toString(pHead), 0, Integer.toString(pHead).length());
				characterfile.newLine();
				characterfile.write("character-torso = ", 0, 18);
				characterfile.write(Integer.toString(pTorso), 0, Integer.toString(pTorso).length());
				characterfile.newLine();
				characterfile.write("character-arms = ", 0, 17);
				characterfile.write(Integer.toString(pArms), 0, Integer.toString(pArms).length());
				characterfile.newLine();
				characterfile.write("character-hands = ", 0, 18);
				characterfile.write(Integer.toString(pHands), 0, Integer.toString(pHands).length());
				characterfile.newLine();
				characterfile.write("character-legs = ", 0, 17);
				characterfile.write(Integer.toString(pLegs), 0, Integer.toString(pLegs).length());
				characterfile.newLine();
				characterfile.write("character-feet = ", 0, 17);
				characterfile.write(Integer.toString(pFeet), 0, Integer.toString(pFeet).length());
				characterfile.newLine();
				characterfile.write("character-beard = ", 0, 18);
				characterfile.write(Integer.toString(pBeard), 0, Integer.toString(pBeard).length());
				characterfile.newLine();
			}
			characterfile.newLine();

			characterfile.write("[SKILLS]", 0, 8);
			characterfile.newLine();
			for (int i = 0; i < playerLevel.length; i++) {
				characterfile.write("character-skill = ", 0, 18);
				characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerLevel[i]), 0, Integer.toString(playerLevel[i]).length());
				characterfile.write("	", 0, 1);
				characterfile.write(Integer.toString(playerXP[i]), 0, Integer.toString(playerXP[i]).length());
				characterfile.newLine();
			}
			characterfile.newLine();

			characterfile.write("[ITEMS]", 0, 7);
			characterfile.newLine();
			for (int i = 0; i < playerItems.length; i++) {
				if (playerItems[i] > 0) {
					characterfile.write("character-item = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(playerItems[i]), 0, Integer.toString(playerItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(playerItemsN[i]), 0, Integer.toString(playerItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();

			characterfile.write("[BANK]", 0, 6);
			characterfile.newLine();
			for (int i = 0; i < bankItems.length; i++) {
				if (bankItems[i] > 0) {
					characterfile.write("character-bank = ", 0, 17);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(bankItems[i]), 0, Integer.toString(bankItems[i]).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Integer.toString(bankItemsN[i]), 0, Integer.toString(bankItemsN[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();

			characterfile.write("[FRIENDS]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < friends.length; i++) {
				if (friends[i] > 0) {
					characterfile.write("character-friend = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(friends[i]), 0, Long.toString(friends[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();

			characterfile.write("[IGNORES]", 0, 9);
			characterfile.newLine();
			for (int i = 0; i < ignores.length; i++) {
				if (ignores[i] > 0) {
					characterfile.write("character-ignore = ", 0, 19);
					characterfile.write(Integer.toString(i), 0, Integer.toString(i).length());
					characterfile.write("	", 0, 1);
					characterfile.write(Long.toString(ignores[i]), 0, Long.toString(ignores[i]).length());
					characterfile.newLine();
				}
			}
			characterfile.newLine();

			characterfile.write("[EOF]", 0, 5);
			characterfile.newLine();
			characterfile.newLine();
			characterfile.close();
		} catch (IOException ioexception) {
			return false;
		}
		return true;
	}

	int Banned() {
		try {
			BufferedReader in = new BufferedReader(new FileReader("../../Files/Data/Banned.txt"));
			String Data = null;

			while ((Data = in.readLine()) != null) {
				if (playerName.equalsIgnoreCase(Data)) {
					return 1;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	int IPBanned() {
		try {
			BufferedReader in = new BufferedReader(new FileReader("../../Files/Data/IPBanned.txt"));
			String Data = null;

			while ((Data = in.readLine()) != null) {
				if (connectedFrom.equalsIgnoreCase(Data)) {
					return 1;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	int Muted() {
		try {
			BufferedReader in = new BufferedReader(new FileReader("../../Files/Data/Muted.txt"));
			String Data = null;

			while ((Data = in.readLine()) != null) {
				if (playerName.equalsIgnoreCase(Data)) {
					return 1;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	int IPMuted() {
		try {
			BufferedReader in = new BufferedReader(new FileReader("../../Files/Data/IPMuted.txt"));
			String Data = null;

			while ((Data = in.readLine()) != null) {
				if (connectedFrom.equalsIgnoreCase(Data)) {
					return 1;
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return 0;
	}

	private int KillerID = playerId;
	public int i;
	public static final int bufferSize = 20000;
	private int somejunk;
	private java.net.Socket mySock;
	private java.io.InputStream in;
	private java.io.OutputStream out;
	public byte buffer[] = null;
	public int readPtr, writePtr;
	public Cryption inStreamDecryption = null, outStreamDecryption = null;
}