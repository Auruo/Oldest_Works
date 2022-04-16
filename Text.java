class Text {
	void UpdateText(Client c) {
		c.sendString("Total Lvl: "+c.getTotalLvl+"", 3984);
		if (c.PkStats) {
			c.sendString("                 @red@PK Stats:", 5450);//header
			c.sendString("@or2@PKing Level: @yel@"+ c.playerLevel[18], 3206);
			c.sendString("@or2@PKing XP: @yel@"+ c.playerXP[18], 5449);
			c.sendString("@or2@PK Deaths: @yel@"+ c.pkDeaths, 11941);
			c.sendString("@or2@Kills: @yel@"+ c.PkKills +"", 4287); 
			c.sendString("@or2@PK Rating: @yel@"+ c.pkRating, 4288);
			c.sendString("@or2@Last Person Killed:", 4289); 
			if (c.KilledPlayer != null) {
				c.sendString("@yel@"+ c.KilledPlayer, 4290);
			} else {
				c.sendString("@red@~@yel@Nobody@red@~", 4290);
			}
			c.sendString("@or2@You Were Last Killed By:", 4291);
			if (c.KilledBy != null) {
				c.sendString("@yel@"+ c.KilledBy, 4292);
			} else {
				c.sendString("@red@~@yel@Nobody@red@~", 4292);
			}
		}
		if (c.AllStats) {
			c.sendString("                 @gr1@All Stats:", 5450);//header
			c.sendString("@or2@Total Level: @yel@"+ c.getTotalLvl, 3206);
			c.sendString("@or2@Total XP: @yel@"+ c.getTotalXP, 5449);
			c.sendString("@or2@Combat Level: @yel@"+ c.combat, 11941);
			c.sendString("", 4287);
			c.sendString("@or2@Lifetime Deaths: @yel@"+ c.deaths, 4288);
			c.sendString("", 4289); 
			c.sendString("", 4290);
			c.sendString("", 4291);
			c.sendString("", 4292);
		}
		if (c.NpcStats) {
			c.sendString("                 @whi@NPC Stats:", 5450);//header
			c.sendString("@or2@Lifetime NPC Kills: @yel@"+ c.NpcKills, 3206);
			c.sendString("", 5449);
			c.sendString("", 11941);
			c.sendString("", 4287);
			c.sendString("", 4288);
			c.sendString("", 4289); 
			c.sendString("", 4290);
			c.sendString("", 4291);
			c.sendString("", 4292);
		}
	}
	void LoadText(Client c) {
		c.sendString("Players On:", 148);
		c.sendString("      Location:", 183);
		c.sendString("                                       Design your character.", 3649); //player design screen
		
		c.sendString("Your", 963);//top music header
		c.sendString("Stats", 8934);//under above
		c.sendString("  PK", 6272);//box 1 near above
		c.sendString(" All", 6271);//box 2 near above
		c.sendString(" NPC", 9926);//box 3 near above
		c.sendString("", 4439);//nex2 header

		
		//		c.sendString("@or2@PKing Level: @yel@"+ c.playerLevel[18] +"", 3206);
		//		c.sendString("@or2@PKing XP: @yel@"+ c.playerXP[18] +"", 5449);
		//		c.sendString("", 11941);
		//		c.sendString("@or2@Kills: @yel@"+ c.PkKills +"", 4287); 
		//		c.sendString("@or2@Last Person Killed:", 4289); 
		//		if (c.KilledPlayer != null) {
		//			c.sendString("@yel@"+ c.KilledPlayer +"", 4290);
		//			} else {
		//			c.sendString("@red@~@yel@Nobody@red@~", 4290);
		//			}
		c.sendString("", 11134); 
		//		c.sendString("@or2@You Were Last Killed By:", 4291);
		//		if (c.KilledBy != null) {
		//			c.sendString("@yel@"+ c.KilledBy +"", 4292);
		//			} else {
		//			c.sendString("@red@~@yel@Nobody@red@~", 4292);
		//			}
		
		// MUSIC
		c.sendString("", 4293); 
		c.sendString("", 4294); 
		c.sendString("", 4295); 
		c.sendString("", 4296);
		c.sendString("", 8935); 
		c.sendString("", 4297); 
		c.sendString("", 4298); 
		c.sendString("", 4299); 
		c.sendString("", 4300); 
		c.sendString("", 4301); 
		c.sendString("", 4302); 
		c.sendString("", 4303); 
		c.sendString("", 4304);
		c.sendString("", 14872); 
		c.sendString("", 664);
		c.sendString("", 8971); 
		c.sendString("", 4306); 
		c.sendString("", 7454);
		c.sendString("", 4864);
		c.sendString("", 12128);
		c.sendString("", 4307);
		c.sendString("", 4308);
		c.sendString("", 4309);
		c.sendString("", 14871);
		c.sendString("", 12127);
		c.sendString("", 8968);
		c.sendString("", 12844);
		c.sendString("", 4310);
		c.sendString("", 10111);
		c.sendString("", 8565);
		c.sendString("", 14242);
		c.sendString("", 15494);
		c.sendString("", 17508);
		c.sendString("", 4311);
		c.sendString("", 11476);	
		
		
		c.sendString("~ Rules ~", 5951);
		c.sendString("Below are the rules you must follow in order to play SifScape.", 5966);
		c.sendString("Report rule breakers on the forum, remember to take a screenshot!", 5967);
		c.sendString("Failing to abide by the rules WILL get you banned!", 5968);
		c.sendString("@red@Notice:@yel@ Entering a name and clicking a rule will do NOTHING.", 5983);
		c.sendString("", 5969);
		c.sendString("                           Below are the rules you must follow to play SifScape.", 5970);
		c.sendString("1. Do not abuse bugs.", 5971);
		c.sendString("2. Do not scam passwords or items.", 5972);
		c.sendString("3. Do not falsely claim to be staff.", 5973);
		c.sendString("4. Do not advertise other servers.", 5974);
		c.sendString("5. Do not spam.", 5975);
		c.sendString("6. Do not disrespect staff.", 5976);
		c.sendString("7. Do not encourage others to break rules.", 5977);
		c.sendString("8. Do not share accounts.", 5978);
		c.sendString("9. Try not to dodge the chat filter.", 5979);
		c.sendString("10. English only in world chat.", 5980);
		c.sendString("@yel@More rules are on the website.", 5981);
		c.sendString("", 5982);
		
		c.sendString("PKing XP:", 12169);
		c.sendString("Bank pin", 14923);
		c.sendString("1", 14884);
		c.sendString("2", 14885);
		c.sendString("3", 14886);
		c.sendString("4", 14887);
		c.sendString("5", 14888);
		c.sendString("6", 14889);
		c.sendString("7", 14890);
		c.sendString("8", 14891);
		c.sendString("9", 14892);
		c.sendString("", 6968);
		c.sendString("", 6969);
		c.sendString("Dig at an area thats filled with dirt and", 6970);
		c.sendString("fossils, thats located in the north-east", 6971);
		c.sendString("part of an area which draws many", 6972);
		c.sendString("Archoelogists from around the world.", 6973);
		c.sendString("", 6974);
		c.sendString("", 6975);
		c.sendString("   SifScape", 640);
		c.sendString("@or1@Items kept on Death", 7343);
		c.sendString("", 4963);
		c.sendString("", 4964);
		c.sendString("@or1@Saradomin:", 4965);
		c.sendString("", 4967);
		c.sendString("", 4968);
		c.sendString("      @or1@NPC Killcount", 4969);
		c.sendString("@or1@Items Kept on Death", 6825);
		c.sendString("   Ok", 6827);
		c.sendString("", 6839);
		c.sendString("", 6840);
		c.sendString("Items Kept", 6020);
		c.sendString("@or1@items will be lost upon death.", 6837);
		c.sendString("", 6838);
		c.sendString("Magic Training Arena Exchange", 10361);
		c.sendString("Cost", 10379);
		c.sendString("Points: "+c.magearenapoints+"", 10378);
		c.sendString("Infinity set", 10374);
		c.sendString("Mage's set", 10375);
		c.sendString("Vengeance spells", 6557);
		c.sendString("Ancient staff", 16159);
		c.sendString("1000 Points", 10376);
		c.sendString("800 Points", 10377);
		c.sendString("200 Points", 6559);
		c.sendString("100 Points", 16160);
		c.sendString("", 2437);
		c.sendString("", 2438);
		c.sendString("", 12338);
		c.sendString("", 12339);
		c.sendString("Right click to buy, Choose ammount you want, Click item for the price.", 3903);
		c.sendString("Home Teleport", 13037);
		c.sendString("Level 60: Barb Assault Teleport", 13047);
		c.sendString("Level 66: Colony Teleport", 13055);
		c.sendString("Level 72: Canifas Teleport", 13063);
		c.sendString("Level 78: Magic Bank Teleport", 13071);
		c.sendString("Level 84: Ice Plateu Teleport", 13081);
		c.sendString("Level 90: Vengeance-other", 13089);
		c.sendString("Level 94: Vengeance", 13097);
		c.sendString("Lets another rebound damage", 13090);
		c.sendString("Rebound damage to an opponent ", 13098);
		c.sendString("Home Teleport", 1300);
		c.sendString("No Runes Required", 1301);
		c.sendString("Level 31: Lumbridge Teleport", 1325);
		c.sendString("No Runes Required", 1326);
		c.sendString("Level 37: Falador Teleport", 1350);
		c.sendString("No Runes Required", 1351);
		c.sendString("Level 45: Ardougne Teleport", 1382);
		c.sendString("No Runes Required", 1383);
		c.sendString("Magic Training Arena Teleport", 1415);
		c.sendString("No Runes Required", 1416);
		c.sendString("Barbarian Assault Teleport", 1454);
		c.sendString("No Runes Required", 1455);
		c.sendString("God Wars Teleport", 7457);
		c.sendString("No Runes Required", 7458);
		c.sendString("Mage Bank Teleport", 18472);
		c.sendString("No Runes Required", 18473);
		c.sendString("" + c.playerXP[19] + "", 13921);
		c.sendString("" + c.getXPForLevel(c.playerLevel[19] + 1) + "", 13922);	
		//Quest Crap
		c.sendString("Quest Points: "+ c.qpoints +"", 640); //Main Header
		c.sendString("", 7333);
		c.sendString("", 7334);
		c.sendString("", 7336);
		c.sendString("", 7383);
		c.sendString("", 7339);
		c.sendString("", 7338);
		c.sendString("", 7340);
		c.sendString("", 7346);
		c.sendString("", 7341);
		c.sendString("", 7342);
		c.sendString("", 7337);
		c.sendString("Items Kept", 7343);
		c.sendString("", 7335);
		c.sendString("", 7344);
		c.sendString("", 7345);
		c.sendString("", 7347);
		c.sendString("", 7348);
		c.sendString("", 682);    //header
		c.sendString("", 12772);
		c.sendString("", 673);
		c.sendString("", 7352);
		c.sendString("", 17510);
		c.sendString("", 7353);
		c.sendString("", 12129);
		c.sendString("", 8438);
		c.sendString("", 12852);
		c.sendString("", 15841);
		c.sendString("", 7354);
		c.sendString("", 7355);
		c.sendString("", 7356);
		c.sendString("", 8679);
		c.sendString("", 7459);
		c.sendString("", 16149);
		c.sendString("", 6987);
		c.sendString("", 7357);
		c.sendString("", 12836);
		c.sendString("", 7358);
		c.sendString("", 7359);
		c.sendString("", 14169);
		c.sendString("", 10115);
		c.sendString("", 14604);
		c.sendString("", 7360);
		c.sendString("", 12282);
		c.sendString("", 13577);
		c.sendString("", 12839);
		c.sendString("", 7361);
		c.sendString("", 16128);
		c.sendString("", 11857);
		c.sendString("", 7362);
		c.sendString("", 7363);
		c.sendString("", 7364);
		c.sendString("", 10135);
		c.sendString("", 4508);
		c.sendString("", 18517);
		c.sendString("", 11907);
		c.sendString("", 7365);
		c.sendString("", 7366);
		c.sendString("", 7367);
		c.sendString("", 13389);
		c.sendString("", 15487);
		c.sendString("", 7368);
		c.sendString("", 11132);
		c.sendString("", 7369);
		c.sendString("", 12389);
		c.sendString("", 13974);
		c.sendString("", 6027);
		c.sendString("", 7370);
		c.sendString("", 8137);
		c.sendString("", 7371);
		c.sendString("", 12345);
		c.sendString("", 7372);
		c.sendString("", 8115);
		c.sendString("", 18684);
		c.sendString("", 15499);
		c.sendString("", 18306);
		c.sendString("", 668);
		c.sendString("", 8576);
		c.sendString("", 12139);
		c.sendString("", 14912);
		c.sendString("", 7373);
		c.sendString("", 7374);
		c.sendString("", 8969);
		c.sendString("", 15352);
		c.sendString("", 7375);
		c.sendString("", 7376);
		c.sendString("", 15098);
		c.sendString("", 15592);
		c.sendString("", 249);
		c.sendString("", 1740);
		c.sendString("", 15235);
		c.sendString("", 3278);
		c.sendString("", 7378);
		c.sendString("", 6518);
		c.sendString("", 7379);
		c.sendString("", 7380);
		c.sendString("", 7381);
		c.sendString("", 11858);
		c.sendString("", 191);
		c.sendString("", 9927);
		c.sendString("", 6024);
		c.sendString("", 7349);
		c.sendString("", 7350);
		c.sendString("", 7351);
		c.sendString("", 13356);	
		//End Quest Crap
	}

}