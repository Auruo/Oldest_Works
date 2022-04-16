import java.net.*;
import java.sql.*;
import java.io.*;
import java.util.StringTokenizer;
import java.util.Calendar;
import java.util.GregorianCalendar;

class Server implements Runnable {

	public Server() {
	}
	//Woodcutting
	public static int Trees = 0;
	public static int[] TREEX = new int[9999];
	public static int[] TREEY = new int[9999];
	public static int[] TREESPAWN = new int[9999];
	public static int[] LOGSLEFT = new int[9999];
	public static int[] LOGS = new int[9999];
	public static int[] TREEID = new int[9999];
	public static int[] TREESTUMP = new int[9999];
	//Mining
	public static int Rocks = 0;
	public static int[] ROCKX = new int[9999];
	public static int[] ROCKY = new int[9999];
	public static int[] ROCKSPAWN = new int[9999];
	public static int[] ORELEFT = new int[9999];
	public static int[] ORE = new int[9999];
	public static int[] ROCKID = new int[9999];
	public static int[] ROCKSTUMP = new int[9999];
	
	public static final int cycleTime = 500;
	public static boolean updateServer = false;
	public static int updateSeconds = 180; 
	public static long startTime;

	public static void main(java.lang.String args[]) {
		clientHandler = new Server();
		(new Thread(clientHandler)).start();

		EventManager.initialise();
		PlayerManager = new PlayerManager();
		Button = new Button();
		Command = new Command();
		Agility = new Agility();
		GetWepSound = new GetWepSound();
		Smithing = new Smithing();
		Woodcutting = new Woodcutting();
		Mining = new Mining();
		Cooking = new Cooking();
		FishingHandler = new FishingHandler();
		ObjectClickOne = new ObjectClickOne();
		Dialogue = new Dialogue();
		Frame = new Frame();
		Special = new Special();
		Text = new Text();
		Interfaces = new Interfaces();
		NpcManager = new NPCManager();
		item = new Item();
		shop = new Shop();
		Prayer = new Prayer();

		int waitFails = 0;
		long lastTicks = System.currentTimeMillis();
		long totalTimeSpentProcessing = 0;
		int cycle = 0;
		while (!shutdownServer) {
			if (updateServer)
			calcTime();
			PlayerManager.process();
			NpcManager.process();
			item.process();
			shop.process();
			System.gc();
			for (int k = 0; k < Trees; k++) {
				if (TREESPAWN[k] > 0) {
					TREESPAWN[k]--;
				}
				if (TREESPAWN[k] == 0 && LOGSLEFT[k] == 0) {
					Client.AddGlobalObj(TREEX[k], TREEY[k], TREEID[k], 0, 10);
					LOGSLEFT[k] = Misc.random(LOGS[k] + 1);
				}
			}
			for (int k = 0; k < Rocks; k++) {
				if (ROCKSPAWN[k] > 0) {
					ROCKSPAWN[k]--;
				}
				if (ROCKSPAWN[k] == 0 && ORELEFT[k] == 0) {
					Client.AddGlobalObj(ROCKX[k], ROCKY[k], ROCKID[k], 0, 10);
					ORELEFT[k] = Misc.random(ORE[k] + 1);
				}
			}
			long timeSpent = System.currentTimeMillis() - lastTicks;
			totalTimeSpentProcessing += timeSpent;
			if (timeSpent >= cycleTime) {
				timeSpent = cycleTime;
				if (++waitFails > 100) {
					//printOut("Is too slow to run this server.");
				}
			}
			try {
				Thread.sleep(cycleTime-timeSpent);
			} catch(java.lang.Exception _ex) { }
			lastTicks = System.currentTimeMillis();
			cycle++;
			if (cycle % 100 == 0) {
				float time = ((float)totalTimeSpentProcessing)/cycle;
			}
			if (cycle % 3600 == 0) {
				System.gc();
			}
			if (ShutDown == true) {
				if (ShutDownCounter >= 100) {
					shutdownServer = true;
				}
				ShutDownCounter++;
			}
		}
		PlayerManager.destruct();
		clientHandler.killServer();
		clientHandler = null;
		EventManager.getSingleton().shutdown();
	}

	public Socket acceptSocketSafe(ServerSocket x) {
		boolean socketFound = false;
		Socket s = null;
		do {
			try {
				s = x.accept();
				int i = s.getInputStream().read();
				if ((i & 0xFF) == 14) {
					socketFound = true;
				}
			} catch (Exception e) {
			}
		} while (!socketFound);
		return s;
	}

	public static Server clientHandler = null;
	public static java.net.ServerSocket clientListener = null;
	public static boolean shutdownServer = false;
	public static boolean shutdownClientHandler;
	public static int serverlistenerPort = 43594;

	public static Button Button = null;
	public static QuestHandler qHandler = new QuestHandler();
	public static int totalQP = qHandler.checkTotQP();
	public static Command Command = null;
	public static Smithing Smithing = null;
	public static Woodcutting Woodcutting = null;
	public static Mining Mining = null;
	public static Cooking Cooking = null;
	public static Agility Agility = null;
	public static FishingHandler FishingHandler = null;
	public static ObjectClickOne ObjectClickOne = null;
	public static GetWepSound GetWepSound = null;
	public static Dialogue Dialogue = null;
	public static PlayerManager PlayerManager = null;
	public static NPCManager NpcManager = null;
	public static Text Text = null;
	public static Interfaces Interfaces = null;
	public static Frame Frame = null;
	public static Special Special = null;
	public static Item item = null;
	public static Shop shop = null;
	public static Prayer Prayer = null;

	public static void calcTime() {
		long curTime = System.currentTimeMillis();
		updateSeconds = 180 - ((int)(curTime - startTime) / 1000);
		if (updateSeconds == 0) {
			shutdownServer = true;
		}
	}


	public void run() {
		try {
			shutdownClientHandler = false;
			clientListener = new java.net.ServerSocket(serverlistenerPort, 1, null);
			Misc.println("------------------");
			Misc.println("| KasparScape v3 |");
			Misc.println("|     Online     |");
			Misc.println("------------------");
			while(true) {
				java.net.Socket s = acceptSocketSafe(clientListener);
				s.setTcpNoDelay(true);
				String connectingHost = s.getInetAddress().getHostName();
				boolean checkbanned = false;
				try {
					BufferedReader in = new BufferedReader(new FileReader("../../Files/Data/IPBanned.txt"));
					String data = null;
					while ((data = in.readLine()) != null) {
						if (connectingHost.startsWith(data)){
							checkbanned = true;
						}
					}
				} catch (IOException e) {
					System.err.println("The banned IPs file could not be accessed.");
					checkbanned = false;
				}
				if (clientListener != null && checkbanned == false) {
					int Found = -1;
					for (int i = 0; i < MaxConnections; i++) {
						if (Connections[i] == connectingHost) {
							Found = ConnectionCount[i];
							break;
						}
					}
					if (Found < 4) {
						PlayerManager.newPlayerClient(s, connectingHost);
					} else {
						s.close();
					}
				} else {
					s.close();
				}
			}
		} catch(java.io.IOException ioe) {
			if (!shutdownClientHandler) {
				Misc.println("Unable to startup listener on "+serverlistenerPort+" - port already in use?");
			} else {
				Misc.println("ClientHandler was shut down.");
			}
		}
	}
	
	public void killServer() {
		try {
			shutdownClientHandler = true;
			if(clientListener != null) clientListener.close();
			clientListener = null;
		} catch(java.lang.Exception __ex) {
			__ex.printStackTrace();
		}
	}

	public static int EnergyRegian = 0;

	public static int MaxConnections = 25000;
	public static String[] Connections = new String[MaxConnections];
	public static int[] ConnectionCount = new int[MaxConnections];
	public static boolean ShutDown = false;
	public static int ShutDownCounter = 0;
}
