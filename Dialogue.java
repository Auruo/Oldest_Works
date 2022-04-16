class Dialogue {

	static void sendDialogue(Client c, int Dialog) {

		switch (Dialog) {

		case 1:
			c.sendChat("You've found a hidden tunnel, do you want to enter?");
			break;

		case 2:
			c.sendOption("Yes, I'm fearless.", "No way, that looks scary!", "", "", 2);
			break;

		case 279:
			c.NPC("Hello, Welcome to the Prayer guild!", "What could I do for you this fine day?", 279);
			break;

		case 280:
			c.Player("Hello Brother Omad,", "I'm feeling pretty bad, could you heal me please?");
			break;

		case 281:
			c.NPC("Sure,", "Anything for a young adventurer!", 279);
			break;

		case 282:
			c.UpdateHP(99);
			c.sendChat("You start feeling better...");
			break;

		case 285:
			c.NPC("Wait! You don't know enough about", "the Prayer skill to enter that! Come back when you do.", 279);
			c.Send("You need 31 or higher Prayer to enter the Prayer guild.");
			break;

		case 500:
			c.sendChat("Are you sure you want to teleport to level 48 Wilderness?");
			break;

		case 501:
			c.sendOption("Yes, I'm not afraid.", "No, I don't want to lose my items!", "", "", 2);
			break;

		case 519:
			c.NPC("Hello,", "Would you like me to fix your broken Barrows armor?", 519);
			break;

		case 520:
			c.Player("Yes,", "I would, please.");
			break;

		case 521:
			c.Repair();
			c.NPC("There you go,", "all of your armor has been fixed.", 519);
			break;
			
		case 599:
			c.NPC("Hello!", "Would you like a make-over?", 599);	
			break;

		case 600:
			c.sendOption("Yes.", "No, I enjoy looking stupid!", "", "", 2);
			c.makeOver = true;
			break;

		case 648:
			c.Player("Hello King Roald,", "What are you doing here?");
			break;

		case 649:
			c.NPC("Hello "+c.playerName+"!", "I'm here to improve the security of SifScape.", 648);
			break;

		case 650:
			c.NPC("I'm offering free Bank pins to c.Players", "who wish to protect there bank items.", 648);
			break;

		case 651:
			c.NPC("Well, since you're here,", "Would you like to have one for yourself?", 648);
			break;

		case 652:
			if (c.Pin1 != -1 && !c.EnteredPin) {
				c.Player("Thanks for offering,", "Although I have a Bank pin already.");
				c.Dialogue = 0;
				c.SendDialogue = false;
				break;
			}
			if (c.Pin1 != -1 && c.EnteredPin) {
				c.Player("No, actually I would,", "like my Bank pin to be reset.");
				c.RemovePin = true;
				break;
			}
			c.Player("Yes I would!", "Anything to help protect my account.");
			break;

		case 653:
			if (c.RemovePin) {
				c.NPC("Oh, I'm sorry to hear that. I've reset", "your Bank pin. Come back if you wish for another.", 648);
				c.Pin1 = -1;
				c.Pin2 = -1;
				c.Pin3 = -1;
				c.Pin4 = -1;
				c.EnteredPin = false;
				c.Save();
				c.Send("Your Bank pin has been reset.");
				c.RemovePin = false;
				break;
			}
			c.RemoveAllWindows();
			c.EnteringPin = 1;
			c.sendString("Enter the 1st digit", 15313);
			c.showInterface(7424);
			c.Dialogue = 0;
			c.SendDialogue = false;
			break;

		case 836:
			c.NPC("Hello "+c.playerName+",", "Would you like to purchase a Shantay pass?", 836);
			break;

		case 837:
			c.Player("Yes,", "I would like one, please.");
			break;

		case 838:
			c.Dialogue = 0;
			c.SendDialogue = false;
			if (!c.hasAmount(995, 5)) {
				c.NPC("You don't have enough gold to buy the pass,", "Come back when you have enough.", 836);
				return;
			}
			c.itemMessage("You purchase a Shantay pass for 5 gold", "", 1854, 250);
			c.delete(995, 5);
			c.addItem(1854, 1);
			break;

		case 1000:
			c.NPC("Hello mate,", "What could I help you with?", 0);
			break;

		case 1001:
			c.Player("I was just wondering...", "Do you happen to sell capes of Accomplishment?");
			break;

		case 1002:
			c.NPC("Well then, you've come to the right place!", "Here you go, "+c.playerName+".", 0);
			break;

		case 1003:
			c.RemoveAllWindows();
			c.Shop = 4;
			c.Dialogue = 0;
			c.SendDialogue = false;
			break;

		case 1526:
			c.NPC("Welcome to Barbarian Assault!","To gain points, simply kill guards, each time","you kill one, you will gain a point. You can","exchange the points for certain items.", 3050);
			break;

		case 1527:
			c.NPC("You currently have "+c.Barbpoints+" points.", "Would you like to exchange your points?", 3050);
			break;

		case 1528:
			c.Player("Sure,", "What could I purchase?");
			break;

		case 1529:
			c.sendOption("Figher torso", "Rune defender", "Barrows gloves", "", 3);
			break;

		case 1590:
			c.NPC("Hello then,", "What can I do for you, "+c.playerName+"?", 1596);
			break;

		case 1591:
			c.Player("Hello Mazchna,", "What do you have for sale?");
			break;

		case 1592:
			c.sendOption("Slayer skillcape", "Facemask", "Nevermind", "", 3);
			break;

		case 1599:
			c.NPC("Hello then,", "What can I do for you, young adventurer?", 1598);
			break;

		case 1600:
			c.Player("Hello,", "Could you get me to Canifas, please?");
			break;

		case 1601:
			c.NPC("I sure can,", "Keep an eye out for some unusual things...", 1598);
			break;

		case 1602:
			c.Tele("Canifas");
			c.RemoveAllWindows();
			c.Dialogue = 0;
			c.SendDialogue = false;
			break;

		case 1679:
			c.NPC("Hello, would you like me to,", "turn that crystal seed into a bow for you?", 1679);
			break;

		case 1680:
			c.Player("Yes,", "I would, please.");
			break;

		case 1681:
			c.delete(995, 130000);
			c.delete(4207, 1);
			c.addItem(4214, 1);
			c.Charges = 100;
			c.NPC("Here you go, your crystal bow has", "been charged! Come back if you need anything else.", 1679);
			break;

		case 1700:
			c.NPC("You need 130,000 gold and a Crystal seed for me,", "to make you a bow! Come back when you have them.", 1679);
			break;

		case 1920:
			if (c.playerLevel[6] < 50 && c.playerLevel[17] < 53) {
				c.NPC("You can't talk to me now!", "Come back when you're strong enough.", 1920);
				c.Dialogue = 0;
				c.SendDialogue = false;
				break;
			}
			c.NPC("Psssh, "+c.playerName+"", "Come here...", 1920);
			break;

		case 1921:
			c.Player("Uhh...", "Who are you?");
			break;

		case 1922:
			c.NPC("I'm Malak, the Vampire. You seem to be", "a strong human, can you do me a favour?", 1920);
			break;

		case 1923:
			c.Player("That depends...", "What do I get out of it?");
			break;

		case 1924:
			c.NPC("Hmm... I'll give you a Diamond for each", "of the Monsters you defeat, they will...", 1920);
			break;

		case 1925:
			c.NPC("Help you along the way to get", "A new magicks stronger then you ever imagined.", 1920);
			break;

		case 1926:
			c.Player("What do you mean...", "by 'The monsters'?");
			break;

		case 1927:
			c.NPC("They are some of the strongest things around and", "they are trying to take over...", 1920);
			break;

		case 1928:
			c.NPC("All of SifScape. They need to be", "defeated before they get even stronger.", 1920);
			break;

		case 1929:
			c.NPC("Come back to me when you are prepared to", "fight the first, named Dessous.", 1920);
			c.sendString("@yel@Desert Treasure", 7332);
			c.q1++;
			c.Dialogue = 0;
			c.SendDialogue = false;
			break;

		case 1931:
			c.NPC("Are you sure...", "That you're ready to fight Dessous?", 1920);
			break;

		case 1932:
			c.sendOption("Yes, I'm ready to fight.", "No, I need to get some things first", "", "", 2);
			break;

		case 1934:
			c.NPC("Great job on defeating Dessous! You still have 3 beasts", "left to defeat. The next one is called Fareed.", 1920);
			break;

		case 1935:
			c.NPC("Come back when you have the required items,", "and you're prepared to battle.", 1920);
			c.q1++;
			c.Dialogue = 0;
			c.SendDialogue = false;
			break;

		case 1937:
			c.NPC("Are you sure...", "That you're ready to fight Fareed?", 1920);
			break;

		case 1938:
			c.sendOption("Yes, I'm ready to fight.", "No, I need to get some things first", "", "", 2);
			break;


		case 1940:
			c.NPC("Great job on defeating Fareed! You still have 2 beasts", "left to defeat. The next one is called Kamil.", 1920);
			break;

		case 1941:
			c.NPC("Talk to me when", "you're prepared to battle.", 1920);
			c.q1++;
			c.Dialogue = 0;
			c.SendDialogue = false;
			break;

		case 1943:
			c.NPC("Are you sure...", "That you're ready to fight Kamil?", 1920);
			break;

		case 1944:
			c.sendOption("Yes, I'm ready to fight.", "No, I need to get some things first", "", "", 2);
			break;

		case 1946:
			c.NPC("Great job on defeating Kamil! You're almost done!", "The last beast is called Damis.", 1920);
			break;

		case 1947:
			c.NPC("Talk to me when", "you're prepared to battle the final beast.", 1920);
			c.q1++;
			c.Dialogue = 0;
			c.SendDialogue = false;
			break;

		case 1949:
			c.NPC("Are you sure...", "That you're ready to fight Damis?", 1920);
			break;

		case 1950:
			c.sendOption("Yes, I'm ready to fight.", "No, I need to get some things first", "", "", 2);
			break;

		case 1952:
			if (c.freeSlots() > 3) {
				c.NPC("You have defeated all of the beasts!", "Thanks for your help, here are all four diamonds.", 1920);
				for (int i = 4670; i < 4674; i++) {
					c.addItem(i, 1);
				}
				c.q1++;
			} else {
				c.NPC("Sorry,", "Come back when you have enough Inventory space.", 1920);
				c.Dialogue = 0;
				c.SendDialogue = false;
			}
			break;

		case 1953:
			c.Player("That's it?", "After all that, I thought I was getting more...");
			break;

		case 1954:
			c.NPC("Haha, calm down "+c.playerName+". You need to travel to the,", "Desert to an Ancient Pyramid with the diamonds...", 1920);
			break;

		case 1955:
			c.NPC("And insert them into there seperate Obelisks,", "so the Magic is restored, and you'll be rewarded.", 1920);
			break;

		case 1956:
			c.Player("Alright,", "I'll be going there now.");
			break;

		case 1971:
			if (c.q1 == 13) {
				c.Player("Hello,", "Malak sent me to help regain your Magic...");
			} else {
				c.NPC("Thanks again,", "for helping us regain our Magic.", 1986);
				c.Dialogue = 0;
				c.SendDialogue = false;
			}
			break;

		case 1972:
			c.NPC("Yes, We have been expecting you.", "We wanted to thank you for all your hard work.", 1986);
			break;

		case 1973:
			c.NPC("Please,", "Accept this as our reward to you.", 1986);
			break;

		case 1974:
			c.RemoveAllWindows();
			c.addSkillXP(20000, 6);
			c.sendString("@gre@Desert Treasure", 7332);
			c.sendString("Quest Points: 3", 663);
			c.sendString("You have completed the Desert Treasure Quest!", 12144);
			c.sendString("3", 12147);
			c.sendString("3 Quest Points", 12150);
			c.sendString("20,000 Magic XP", 12151);
			c.sendString("Ancient Magicks", 12152);
			c.sendString("", 12153);
			c.sendString("", 12154);
			c.sendString("", 12155);
			c.showInterface(12140);
			c.Send("Congratulations! You have completed the Desert Treasure quest.");
			c.q1++;
			c.Dialogue = 0;
			c.SendDialogue = false;
			break;

		case 2024:
			c.Player("Hello,", "What are you doing here?");
			break;

		case 2025:
			c.NPC("AAAAAARRRRRRGGGGGGHHHHHHHHH", "AAAAAAAAAAAAHHHHHHHHHH!", 2024);	
			break;

		case 2026:
			c.Player("Ooookay...", "I'll be going now.");
			break;

		case 3097:
			c.NPC("Welcome to the Magic Training arena,","the kingdom of all which is magic!",3097);
			break;

		case 3098:
			c.NPC("If you wish to enter our minigame,","head north with the progress hat I gave you.",3097);
			break;

		case 3099:
			c.NPC("Although, If you wish to exchange the points you","have recieved, head upstairs to the Rewards guaurdian.",3097);
			break;

		case 3100:
			c.Player("Thank you,", "for all your help.");
			break;

		case 3103:
			c.Player("Hello.", "Could I see the items in your shop, please?");
			break;

		case 3104:
			c.NPC("Ahhh, a mortal seeks our help.","I guess I'll let you pass, this time. ",3103);
			break;

		case 3105:
			c.RemoveAllWindows();
			c.infinityset = true;
			c.sendString("Points: "+c.magearenapoints+"", 10378);
			c.showInterface(10294);
			c.Dialogue = 0;
			c.SendDialogue = false;
			break;
		}
	}
}