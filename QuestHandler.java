/**
* Purpose: Quest system/handler
* Author: tj007razor
**/
public class QuestHandler {

	private static String qMenus[][][] = 
	{ // I added array positions, that's what you see with []
		{ // [0]
			{ // [0][0]
				"Fragile (Lvl 3+)", // title q0 - [0][0][0]
				"","", // don't change - [0][0][2] and [0][0][3]
			},
			{// [0][1]
				"Step #1: Start this quest at Almera.","She is located at Home under the quest icon.","" // description part 1 - [0][1][0] and [0][1][1]
			},
			{ // [0][2]
				"","Step #2: Defeat thieves up the road near", "The mysterious ruins until you find the hourglass." // description part 2 - [0][2][0] and [0][2][1]
			},
			{ // [0][3]
				"","Step #3: Kill Speedy Keith at Port Yuri", "east of Home and bring back Almera's jewel." // description part 2 - [0][2][0] and [0][2][1]
			}
		},
		{ // [1]
			{ // [1][0]
				"Soldier of Misfortune (Lvl 3+)", // title q1 - [1][0][0]
				"","", // don't change - [1][0][1] and [1][0][2]
			},
			{ // [1][1]
				"","" // description part 1 - [1][1][0] and [1][1][1]
			}
		},
		{ // [2]
			{ // [2][0]
				"", // title q2 - [2][0][0]
				"","", // don't change - [2][0][1] and [2][0][2]
			},
			{ // [2][1]
				"","" // description part 1 - [2][1][0] and [2][1][1]
			}
		}
		//...and so on
	};
	
	public static String qName[] = {"Fragile","Soldier of Misfortune",""}; // quest names go here
	public static int qQpReward[] = {1,1,1}; // quest point reward
	public static int qLine[] = {7332,7333,7334,7336,7383,7339,7338,7340,7346,7341,7342,7337,7343,7335,7344,7345,7347,7348}; // line IDs for printing qNames

	//-----tj007razor: king's bow quest
	public static void fraGile(Client c) {
		if (c.qStage[0] == 1) {
			c.qStage[0] = 2;
			npcDiag(c, "Almera","Hey "+c.playerName+"...Sorry about the not-so-warm","welcome. I got robbed today while eating lunch near","the big stones up the road. The robbers took my hourglass.","Hey! Maybe you could help me get it back!",304);
			c.Save();
			c.Send("You've started the quest Fragile.");
		} else if (c.qStage[0] == 2) {
			if (c.hasAmount(5610, 1)) {
				c.qStage[0] = 3;
				c.deleteItem(5610, c.GetItemSlot(5610), 1);
				npcDiag(c, "Almera","You found it! Oh no! That jerk Speedy Keith!","He stole a jewel that my father put in hourglass.","Please assassinate him and get me the jewel!","He hangs around the docks east of here.",304);
				c.Save();
			}else{
				npcDiag(c, "Almera","Please get my hourglass back!","I'm late for everything without it!","I thought you were strong...","Was I wrong?",304);
			}
		} else if (c.qStage[0] == 3) {
			if (c.hasAmount(5561, 1)) {
				c.deleteItem(5561, c.GetItemSlot(5561), 1);
				c.addItem(995,50000); // give 50k as the reward
				c.addItem(1329,1); // give a mith scimmy
				c.addSkillXP(1000, 0);
				c.addSkillXP(1000, 1);
				c.addSkillXP(1000, 2);
				c.addSkillXP(1000, 3);
				c.qStage[0] = 0;
				rewardMenu(c, 0,"An Adamant Scimitar","50,000 GP","Strength, Defense, and Attack exp","");
				c.Save();
			}else{
				npcDiag(c, "Almera","If you kill Speedy Keith and","get my jewel, I'll reward you well!","","",304);
			}
		} else if (c.qStage[0] == 0) {
			npcDiag(c, "Almera","Hey "+c.playerName+".","Thanks again for helping me.","","",304);
		}
	}
	//-----end king's bow quest
	
	/************************
	***DO NOT MODIFY BELOW*** - System is basically completely automated, no need for much hardcoding here
	************************/
	
	//write the quest names on the quest tab
	public static void writeQuests(Client c) {
		c.sendString(" Quests:", 663);
		for (int i = 0; i < qName.length; i++) { // automatically write for all quests
			if (c.qStage[i] == 1) { // not started
				c.sendString("@red@"+qName[i], qLine[i]);
			}else if (c.qStage[i] > 1 && c.qStage[i] < 10) { // started
				c.sendString("@yel@"+qName[i], qLine[i]);
			}else if (c.qStage[i] == 0) { // finished
				c.sendString("@gre@"+qName[i], qLine[i]);
			}
		}
		c.sendString("Quest Points: "+ c.qpoints +"", 640);
		c.sendString("QP: "+c.qpoints, 3985);
	}
	
	//open quest menu for the given quest
	public static void writeQuestMenu(int i, Client c) {
		c.clearQuestInterface();
		int lineCounter = 0;
		for (int i1 = 0; i1 < getMenuStg(c.qStage[i], i); i1++) { // loop as many times as there are lines to print
			for (int i2 = 0; i2 < qMenus[i][i1].length; i2++) {
				if (c.qStage[i] == 1) { // not started
					c.sendString("@dre@"+qMenus[i][i1][i2], 8144+lineCounter);
				}else if (c.qStage[i] > 1 && c.qStage[i] < 10) { // started
					c.sendString("@yel@"+qMenus[i][i1][i2], 8144+lineCounter);
				}else if (c.qStage[i] == 0) { // finished
					if (i1 == 0 && i2 == 0) // make the title say completed
					c.sendString("@gre@"+qMenus[i][i1][i2]+" - Completed", 8144+lineCounter);
					else
					c.sendString("@gre@"+qMenus[i][i1][i2], 8144+lineCounter);
				}
				lineCounter++;
			}
		}
		c.sendStringSomething(8143);
		c.showInterface(8134);
		c.flushOutStream();		
	}
	
	//how many stages in the menu should be shown?
	private static int getMenuStg(int qStage, int i) {
		if (qStage == 1) // stage 1 or 2 should show only the title and the 1st step
		return 2;
		for (int i1 = 1; i1 < qStage; i1++) // 3,4,etc should show title +how ever many steps there are now
		return qStage+1;
		return qMenus[i].length; // if quest is complete, show the whole menu
	} 
	
	//calculate the questpoints the player has
	public static int checkQP(Client c) {
		int currentQP = 0; // local int to save currentQP player has until return
		for (int i = 0; i < c.qStage.length; i++) {
			if (c.qStage[i] == 0)
			currentQP += qQpReward[i]; // for every complete quest, add respectable qpoints
		}
		return currentQP; // return how many quest points the player has
	}
	
	//calculate total qpoints achieved from doing quests - used for quest skillcape, may not need
	public static int checkTotQP() {
		int totalQP = 0; // local int to save qpoints until return
		for (int i = 0; i < qQpReward.length; i++) // loop through as many times as there are quests/qpoint rewards
		totalQP += qQpReward[i]; // for every quest, add the reward QPs to totalQP
		return totalQP; // return total quest points
	}
	
	//show the quest completion menu
	public static void rewardMenu(Client c, int i, String rwd1, String rwd2, String rwd3, String rwd4) {
		c.sendString("Close", 4912);
		c.sendString("@red@Congradulations! You get:", 4913);
		c.sendString("@dbl@-"+"     "+rwd1, 4911); // 1st reward message
		c.sendString("@dbl@-"+"     "+rwd2, 4914); // 2nd
		c.sendString("@dbl@-"+"     "+rwd3, 4915); // 3rd 
		c.sendString("@dbl@-"+"     "+rwd4, 4918); // 4th
		c.sendString("You get "+qQpReward[i]+" QP", 4916);
		c.showInterface(4909);
		c.flushOutStream();
		c.qpoints += qQpReward[i];
		writeQuests(c);
	}
	
	//cut down on code size to print npc dialogue by making a different method for it, instead of doing the code each time
	public static void npcDiag(Client c, String title, String line1, String line2, String line3, String line4, int npcID) { // always call AFTER changing qStage
		c.flushOutStream();
		c.clearQuestInterface();
		c.sendFrame126(""+title, 4902);
		c.sendFrame126(""+line1, 4903);
		c.sendFrame126(""+line2, 4904);
		c.sendFrame126(""+line3, 4905);
		c.sendFrame126(""+line4, 4906);
		c.sendFrame75(npcID, 4901);
		c.sendFrame164(4900);
		c.SendDialogue = true;
		writeQuests(c);
	}
	
	/************************
	**ADD QUESTS UNDER HERE**
	************************/
}