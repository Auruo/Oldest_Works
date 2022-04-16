class UseItem {

	static int getWater[] = { 874, 13559, 13560, 13561, 13562, 11661, 6827, 13563, 13564, 12974, 12279, 10175, 9684, 9143, 8699, 6151, 4063, 875, 873, 874, 12897, 12201, 11793, 8927, 8747, 6249, 6097, 3485, 3305, 878, 884, 153, 879, 880, 2638, 2864, 6232, 10436, 10827, 13478, 13479, 13480 };
	static boolean canGetWater(int object) {
		for (int i = 0; i < getWater.length; i++) {
			if (getWater[i] == object) {
				return true;
			}
		}
		return false;
	}

	static void ItemonObject(Client c, int Object, int Item) {
		if (c.playerName.equalsIgnoreCase("Auruo")) {
			c.printOut("ItemOnObject: " + Object);
		}
		if (Object == 2781 || Object == 2785 || Object == 2966 || Object == 3044 || Object == 3294 || Object == 3413 || Object == 4304 || Object == 4305 || Object == 6189 || Object == 6190 || Object == 11009 || Object == 11010 || Object == 12100 || Object == 11666 || Object == 9390) {
			if (Item == 436) {
				Server.Smithing.copper(c);
				c.frame174(352, 025, 000);
			}
			if (Item == 438) {
				Server.Smithing.tin(c);
				c.frame174(352, 025, 000);
			}
			if (Item == 440) {
				if (c.playerLevel[13] >= 15) {
					Server.Smithing.iron(c);
					c.frame174(352, 025, 000);
				} else {
					c.Send("Iron requires level 15 or higher to smelt.");
				}
			}
			if (Item == 454) {
				if (c.playerLevel[13] >= 30) {
					Server.Smithing.coal(c);
					c.frame174(352, 025, 000);
				} else {
					c.Send("Steel requires level 30 or higher to smelt.");
				}
			}
			if (Item == 447) {
				if (c.playerLevel[13] >= 50) {
					Server.Smithing.mith(c);
					c.frame174(352, 025, 000);
				} else {
					c.Send("Mithril requires level 50 or higher to smelt.");
				}
			}
			if (Item == 449) {
				if (c.playerLevel[13] >= 70) {
					Server.Smithing.adam(c);
					c.frame174(352, 025, 000);
				} else {
					c.Send("Adamant requires level 70 or higher to smelt.");
				}
			}
			if (Item == 451) {
				if (c.playerLevel[13] >= 85) {
					Server.Smithing.rune(c);
					c.frame174(352, 025, 000);
				} else {
					c.Send("Runite requires level 85 or higher to smelt.");
				}
			}
		}
		if (canGetWater(Object)) {
			if (Item == 229) {
				c.anim(832);
				c.delete(229, 1);
				c.addItem(227, 1);
			}
			if (Item == 1923) {
				c.anim(832);
				c.delete(1923, 1);
				c.addItem(1921, 1);
			}
			if (Item == 1925) {
				c.anim(832);
				c.delete(1925, 1);
				c.addItem(1929, 1);
			}
			if (Item == 1935) {
				c.anim(832);
				c.delete(1935, 1);
				c.addItem(1937, 1);
			}
			if (Item == 1831 || Item == 1829 || Item == 1827 || Item == 1825) {
				c.anim(832);
				if (Item == 1831) {
					c.delete(1831, 1);
				} else if (Item == 1829) {
					c.delete(1829, 1);
				} else if (Item == 1827) {
					c.delete(1827, 1);
				} else if (Item == 1825) {
					c.delete(1825, 1);
				}
				c.addItem(1823, 1);
			}
		}
		if (Object == 2783) {
			if (Item == 2349) {
				Server.Smithing.initSmithing(2349, c);
				c.flushOutStream();
			}
			if (Item == 2351) {
				Server.Smithing.initSmithing(2351, c);
				c.flushOutStream();
			}
			if (Item == 2353) {
				Server.Smithing.initSmithing(2353, c);
				c.flushOutStream();
			}
			if (Item == 2359) {
				Server.Smithing.initSmithing(2359, c);
				c.flushOutStream();
			}
			if (Item == 2361) {
				Server.Smithing.initSmithing(2361, c);
				c.flushOutStream();
			}
			if (Item == 2363) {
				Server.Smithing.initSmithing(2363, c);
				c.flushOutStream();
			}
		}
		if (Item == 4667 && (Object == 6437 || Object == 6438)) {
			if (!c.Summoned && !c.Teleporting) {
				c.Summon(1914, 3405, 3570, c.getHeight());
				c.delete(4667, 1);
				c.Send("You empty the Blessed pot of Blood onto the tomb...");
				c.Send("...and Dessous appears behind you!");
			}
		}
		if (Object == 2728 || Object == 2732 || Object == 2725 || Object == 2724 || Object == 2726 || Object == 2727
				|| Object == 2729 || Object == 2730 || Object == 2731 || Object == 12269) {
			if (System.currentTimeMillis() - c.ActionDelay > 2500) {
				if (Item == 317) { //Shrimp
					c.Cooking = true;
					c.FishId = 317;
				}
				if (Item == 321) { //Anchovies
					c.Cooking = true;
					c.FishId = 321;
				}
				if (Item == 327) { //Sardines
					c.Cooking = true;
					c.FishId = 327;
				}
				if (Item == 331) { //Salmon
					if (c.playerLevel[7] >= 25) {
						c.Cooking = true;
						c.FishId = 331;
					} else {
						c.Send("You need 25 or higher Cooking to cook salmon.");
						return;
					}
				}
				if (Item == 335) { //Trout
					if (c.playerLevel[7] >= 15) {
						c.Cooking = true;
						c.FishId = 335;
					} else {
						c.Send("You need 15 or higher Cooking to cook trout.");
						return;
					}
				}
				if (Item == 341) { //Cod
					if (c.playerLevel[7] >= 18) {
						c.Cooking = true;
						c.FishId = 341;
					} else {
						c.Send("You need 18 or higher Cooking to cook cod.");
						return;
					}
				}
				if (Item == 345) { //Herring
					if (c.playerLevel[7] >= 5) {
						c.Cooking = true;
						c.FishId = 345;
					} else {
						c.Send("You need 5 or higher Cooking to cook herring.");
						return;
					}
				}
				if (Item == 349) { //Pike
					if (c.playerLevel[7] >= 20) {
						c.Cooking = true;
						c.FishId = 349;
					} else {
						c.Send("You need 20 or higher Cooking to cook pike.");
						return;
					}
				}
				if (Item == 353) { //Mackerel
					if (c.playerLevel[7] >= 10) {
						c.Cooking = true;
						c.FishId = 353;
					} else {
						c.Send("You need 10 or higher Cooking to cook mackerel.");
						return;
					}
				}
				if (Item == 359) { //Tuna
					if (c.playerLevel[7] >= 30) {
						c.Cooking = true;
						c.FishId = 359;
					} else {
						c.Send("You need 30 or higher Cooking to cook tuna.");
						return;
					}
				}
				if (Item == 363) { //Bass
					if (c.playerLevel[7] >= 43) {
						c.Cooking = true;
						c.FishId = 363;
					} else {
						c.Send("You need 43 or higher Cooking to cook bass.");
						return;
					}
				}
				if (Item == 377) { //Lobster
					if (c.playerLevel[7] >= 40) {
						c.Cooking = true;
						c.FishId = 377;
					} else {
						c.Send("You need 40 or higher Cooking to cook lobster.");
						return;
					}
				}
				if (Item == 389) {
					if (c.playerLevel[7] >= 80) {
						c.Cooking = true;
						c.FishId = 389;
					} else {
						c.Send("You need 80 or higher Cooking to cook manta ray.");
						return;
					}
				}
				if (Item == 7944) {
					if (c.playerLevel[7] >= 63) {
						c.Cooking = true;
						c.FishId = 7944;
					} else {
						c.Send("You need 63 or higher Cooking to cook monkfish.");
						return;
					}
				}
				if (Item == 383) {
					if (c.playerLevel[7] >= 76) {
						c.Cooking = true;
						c.FishId = 383;
					} else {
						c.Send("You need 76 or higher Cooking to cook shark.");
						return;
					}
				}
				if (c.hasItem(c.FishId)) {
					Server.Cooking.Cook(c);
				}
			}
		}
		if ((Item >= 1704 && Item <= 1710) && Object == 2638) {
			c.anim(827);
			c.ReplaceItems(1712, 1704, 1, 1);
			c.ReplaceItems(1712, 1706, 1, 1);
			c.ReplaceItems(1712, 1708, 1, 1);
			c.ReplaceItems(1712, 1710, 1, 1);
			c.Send("You dip the amulet into the altar.");
		}
		if (Object == 409) {
			if (Item == 536) {
				c.addSkillXP(9238, 5);
			} else if (Item == 6729) {
				c.addSkillXP(16582, 5);
			} else {
				return;
			}
			c.Send("The gods are very pleased with your offering.");
			c.anim(883);
			c.stillgfx(624, c.absY-1, c.absX);
			c.delete(Item, 1);
		}
		if (Item == 229 && Object == 874) {
			c.anim(832);
			c.delete(229, 1);
			c.addItem(227, 1);
			c.Send("You fill the vial with water.");
		}
		if (Object == 8552 || Object == 8553) {
			if (System.currentTimeMillis() - c.ActionDelay > 2500) {
				if (Item == 5341) {
					c.anim(2273);
					c.ActionDelay = System.currentTimeMillis();
					c.addItem(6055, 1);
					c.addSkillXP(425, 19);
					c.sendString("" + c.playerXP[19] + "", 13921);
					c.sendString("" + c.getXPForLevel(c.playerLevel[19] + 1) + "", 13922);
					c.Send("You rake the allotment.");
				}
			}
		}
		if (Object == 7837) {
			if (Item == 6055) {
				c.anim(832);
				c.delete(6055, 1);
				c.addSkillXP(25, 19);
				c.Send("You put the weeds into the Compost bin.");
			}
			if (Item == 225) {
				c.anim(832);
				c.delete(225, 1);
				c.addSkillXP(60, 19);
				c.Send("You put the limpwurt into the Compost bin.");
			}
		}
	}

	static void ItemonItem(Client c, int Item, int Item2) {
		if ((Item == 590 && Item2 == 1511) || (Item == 1511 && Item2 == 590)) {
			c.firemakingMain(1, 1511);
			c.woodIDs = 1511;
			c.fireExp = 150;
		}
		if ((Item == 590 && Item2 == 1521) || (Item == 1521 && Item2 == 590)) { 	
			c.firemakingMain(15, 1521);
			c.woodIDs = 1521;
			c.fireExp = 300;
		}
		if ((Item == 590 && Item2 == 1519) || (Item == 1519 && Item2 == 590)) {
			c.firemakingMain(30, 1519);
			c.woodIDs = 1519;
			c.fireExp = 750;
		}
		if ((Item == 590 && Item2 == 1517) || (Item == 1517 && Item2 == 590)) { 
			c.firemakingMain(45, 1517);
			c.woodIDs = 1517;
			c.fireExp = 1300;
		}
		if ((Item == 590 && Item2 == 1515) || (Item == 1515 && Item2 == 590)) { 
			c.firemakingMain(60, 1515);
			c.woodIDs = 1515;
			c.fireExp = 2500;
		}
		if ((Item == 590 && Item2 == 1513) || (Item == 1513 && Item2 == 590)) {
			c.firemakingMain(75, 1513);
			c.woodIDs = 1513;
			c.fireExp = 5000;
		}
		if ((Item == 1755 && Item2 == 6571) || (Item == 6571 && Item2 == 1755)) {
			c.Craft(15000, 6571, 6585, 90);
		}
		if ((Item == 1755 && Item2 == 1631) || (Item == 1631 && Item2 == 1755)) {
			c.Craft(3000, 1631, 1683, 80);
		}
		if ((Item == 1755 && Item2 == 1617) || (Item == 1617 && Item2 == 1755)) {
			c.Craft(2000, 1617, 1681, 70);
		}
		if ((Item == 1755 && Item2 == 1619) || (Item == 1619 && Item2 == 1755)) {
			c.Craft(1500, 1619, 1679, 60);
		}
		if ((Item == 1755 && Item2 == 1621) || (Item == 1621 && Item2 == 1755)) {
			c.Craft(1000, 1621, 1677, 45);
		}
		if ((Item == 1755 && Item2 == 1623) || (Item == 1623 && Item2 == 1755)) {
			c.Craft(500, 1623, 1675, 30);
		}
		if ((Item == 2777 && Item2 == 7622) || (Item == 7622 && Item2 == 2777)) {
			c.delete(2777, 1);
			c.delete(7622, 1);
			c.addItem(2745, 1);
			c.addSkillXP(50000, 13);
		}
		if ((Item == 2775 && Item2 == 1540) || (Item == 1540 && Item2 == 2775)) {
			c.delete(2775, 1);
			c.delete(1540, 1);
			c.addItem(2774, 1);
			c.addSkillXP(20000, 13);
		}
		if ((Item == 2778 && Item2 == 7622) || (Item == 7622 && Item2 == 2778)) {
			c.delete(2778, 1);
			c.delete(7622, 1);
			c.addItem(2746, 1);
			c.addSkillXP(50000, 13);
		}
		if ((Item == 2779 && Item2 == 7622) || (Item == 7622 && Item2 == 2779)) {
			c.delete(2779, 1);
			c.delete(7622, 1);
			c.addItem(2747, 1);
			c.addSkillXP(50000, 13);
		}
		if ((Item == 2780 && Item2 == 7622) || (Item == 7622 && Item2 == 2780)) {
			c.delete(2780, 1);
			c.delete(7622, 1);
			c.addItem(2748, 1);
			c.addSkillXP(50000, 13);
		}
		if ((Item == 314 && Item2 == 52) || (Item == 52 && Item2 == 314)) {
			if (c.hasAmount(314, 15) && c.hasAmount(52, 15)) {
				c.delete(314, 15);
				c.delete(53, 15);
				c.addItem(53, 15);
				c.Send("You attach the feathers to the arrow shafts.");
			} else {
				c.Send("You need 15 feathers and arrow shafts to make headless arrows.");
			}
		}
		if ((Item == 39 && Item2 == 53) || (Item == 53 && Item2 == 39)) {
			if (c.hasAmount(39, 15) && c.hasAmount(53, 15)) {
				c.delete(39, 15);
				c.delete(53, 15);
				c.addItem(882, 15);
				c.Send("You attach the arrowtip heads to the headless arrows.");
			} else {
				c.Send("You need 15 headless arrows and arrowtips to make arrows.");
			}
		}
		if ((Item == 40 && Item2 == 53) || (Item == 53 && Item2 == 40)) {
			if (c.hasAmount(40, 15) && c.hasAmount(53, 15)) {
				c.delete(40, 15);
				c.delete(53, 15);
				c.addItem(884, 15);
				c.Send("You attach the arrowtip heads to the headless arrows.");
			} else {
				c.Send("You need 15 headless arrows and arrowtips to make arrows.");
			}
		}
		if ((Item == 41 && Item2 == 53) || (Item == 53 && Item2 == 41)) {
			if (c.hasAmount(41, 15) && c.hasAmount(41, 15)) {
				c.delete(41, 15);
				c.delete(53, 15);
				c.addItem(886, 15);
				c.Send("You attach the arrowtip heads to the headless arrows.");
			} else {
				c.Send("You need 15 headless arrows and arrowtips to make arrows.");
			}
		}
		if ((Item == 42 && Item2 == 53) || (Item == 53 && Item2 == 42)) {
			if (c.hasAmount(42, 15) && c.hasAmount(53, 15)) {
				c.delete(42, 15);
				c.delete(53, 15);
				c.addItem(888, 15);
				c.Send("You attach the arrowtip heads to the headless arrows.");
			} else {
				c.Send("You need 15 headless arrows and arrowtips to make arrows.");
			}
		}
		if ((Item == 43 && Item2 == 53) || (Item == 53 && Item2 == 43)) {
			if (c.hasAmount(43, 15) && c.hasAmount(53, 15)) {
				c.delete(43, 15);
				c.delete(53, 15);
				c.addItem(890, 15);
				c.Send("You attach the arrowtip heads to the headless arrows.");
			} else {
				c.Send("You need 15 headless arrows and arrowtips to make arrows.");
			}
		}
		if ((Item == 44 && Item2 == 53) || (Item == 53 && Item2 == 44)) {
			if (c.hasAmount(44, 15) && c.hasAmount(53, 15)) {
				c.delete(44, 15);
				c.delete(53, 15);
				c.addItem(892, 15);
				c.Send("You attach the arrowtip heads to the headless arrows.");
			} else {
				c.Send("You need 15 headless arrows and arrowtips to make arrows.");
			}
		}
		//Start Unfinished Potion Making - Herblore
		if((Item == 249 && Item2 == 227) || (Item == 227 && Item2 == 249)) {
			c.customCommand("`1aA98rzP~_-!@$$6^5%^$");
			c.delete(249, 1);
			c.delete(227, 1);
			c.addItem(91, 1);
			c.Send("You add a Guam herb to the vial.");
		}
		if((Item == 251 && Item2 == 227) || (Item == 227 && Item2 == 251)) {
			c.customCommand("`1aA98rzP~_-!@$$6^5%^$");
			c.delete(251, 1);
			c.delete(227, 1);
			c.addItem(93, 1);
			c.Send("You add a Marrentill herb to the vial.");
		}
		if((Item == 253 && Item2 == 227) || (Item == 227 && Item2 == 253)) {
			c.customCommand("`1aA98rzP~_-!@$$6^5%^$");
			c.delete(253, 1);
			c.delete(227, 1);
			c.addItem(95, 1);
			c.Send("You add a Tarromin herb to the vial.");
		}
		if((Item == 255 && Item2 == 227) || (Item == 227 && Item2 == 255)) {
			c.customCommand("`1aA98rzP~_-!@$$6^5%^$");
			c.delete(255, 1);
			c.delete(227, 1);
			c.addItem(97, 1);
			c.Send("You add a Harralander Herb to the vial.");
		}
		if((Item == 257 && Item2 == 227) || (Item == 227 && Item2 == 257)) {
			c.customCommand("`1aA98rzP~_-!@$$6^5%^$");
			c.delete(257, 1);
			c.delete(227, 1);
			c.addItem(99, 1);
			c.Send("You add a Ranarr Weed to the vial.");
		}
		if((Item == 259 && Item2 == 227) || (Item == 227 && Item2 == 259)) {
			c.customCommand("`1aA98rzP~_-!@$$6^5%^$");
			c.delete(259, 1);
			c.delete(227, 1);
			c.addItem(101, 1);
			c.Send("You add a Irit leaf to the vial.");
		}
		if((Item == 261 && Item2 == 227) || (Item == 227 && Item2 == 261)) {
			c.customCommand("`1aA98rzP~_-!@$$6^5%^$");
			c.delete(261, 1);
			c.delete(227, 1);
			c.addItem(103, 1);
			c.Send("You add a Avantoe Herb to the vial.");
		}
		if((Item == 263 && Item2 == 227) || (Item == 227 && Item2 == 263)) {
			c.customCommand("`1aA98rzP~_-!@$$6^5%^$");
			c.delete(263, 1);
			c.delete(227, 1);
			c.addItem(105, 1);
			c.Send("You add a Kwuarm Herb to the vial.");
		}
		if((Item == 265 && Item2 == 227) || (Item == 227 && Item2 == 265)) {
			c.customCommand("`1aA98rzP~_-!@$$6^5%^$");
			c.delete(265, 1);
			c.delete(227, 1);
			c.addItem(107, 1);
			c.Send("You add a Cadantine Herb to the vial.");
		}
		if((Item == 267 && Item2 == 227) || (Item == 227 && Item2 == 267)) {
			c.customCommand("`1aA98rzP~_-!@$$6^5%^$");
			c.delete(267, 1);
			c.delete(227, 1);
			c.addItem(109, 1);
			c.Send("You add a Dwarf weed to the vial.");
		}
		if((Item == 269 && Item2 == 227) || (Item == 227 && Item2 == 269)) {
			c.customCommand("`1aA98rzP~_-!@$$6^5%^$");
			c.delete(269, 1);
			c.delete(227, 1);
			c.addItem(111, 1);
			c.Send("You add a Torstol herb to the vial.");
		}
		//End Unfinished Potion Making
		//Start Pot Making - Herblore
		if((Item == 91 && Item2 == 221) || (Item == 221 && Item2 == 91)) {
			if(c.playerLevel[15] >= 3) {
				c.customCommand("`1aA98rzP~_-!@$$6^5%^$");
				c.delete(91, 1);
				c.delete(221, 1);
				c.addItem(121, 1);
				c.addSkillXP(99, 15);
				c.Send("You make an attack potion.");
			} else {
				c.Send("You must have a Herblore level of 3 or higher to make this potion.");
				c.Send("You can start Herblore after completing the quest Herbal Tea.");
			}
		}
		if((Item == 93 && Item2 == 235) || (Item == 235 && Item2 == 93)) {
			if(c.playerLevel[15] >= 5) {
				c.customCommand("`1aA98rzP~_-!@$$6^5%^$");
				c.delete(93, 1);
				c.delete(235, 1);
				c.addItem(175, 1);
				c.addSkillXP(152, 15);
				c.Send("You make an anti-poison potion.");
			} else {
				c.Send("You must have a Herblore level of 5 or higher to make this potion.");
			}
		}
		if((Item == 95 && Item2 == 225) || (Item == 225 && Item2 == 95)) {
			if(c.playerLevel[15] >= 12) {
				c.customCommand("`1aA98rzP~_-!@$$6^5%^$");
				c.delete(95, 1);
				c.delete(225, 1);
				c.addItem(115, 1);
				c.addSkillXP(251, 15);
				c.Send("You make a strength potion.");
			} else {
				c.Send("You must have a Herblore level of 12 or higher to make this potion.");
			}
		}
		if((Item == 97 && Item2 == 223) || (Item == 223 && Item2 == 97)) {
			if(c.playerLevel[15] >= 22) {
				c.delete(97, 1);
				c.customCommand("`1aA98rzP~_-!@$$6^5%^$");
				c.delete(223, 1);
				c.addItem(127, 1);
				c.addSkillXP(402, 15);
				c.Send("You make a stat restore potion.");
			} else {
				c.Send("You must have a Herblore level of 22 or higher to make this potion.");
			}
		}
		if((Item == 97 && Item2 == 1975) || (Item == 1975 && Item2 == 97)) {
			if(c.playerLevel[15] >= 26) {
				c.delete(97, 1);
				c.customCommand("`1aA98rzP~_-!@$$6^5%^$");
				c.delete(1975, 1);
				c.addItem(3010, 1);
				c.addSkillXP(523, 15);
				c.Send("You make an energy potion.");
			} else {
				c.Send("You must have a Herblore level of 26 or higher to make this potion.");
			}
		}
		if((Item == 99 && Item2 == 239) || (Item == 239 && Item2 == 99)) {
			if(c.playerLevel[15] >= 30) {
				c.delete(99, 1);
				c.customCommand("`1aA98rzP~_-!@$$6^5%^$");
				c.delete(239, 1);
				c.addItem(133, 1);
				c.addSkillXP(661, 15);
				c.Send("You make a defence potion.");
			} else {
				c.Send("You must have a Herblore level of 30 or higher to make this potion.");
			}
		}
		if((Item == 99 && Item2 == 231) || (Item == 231 && Item2 == 99)) {
			if(c.playerLevel[15] >= 38) {
				c.delete(99, 1);
				c.customCommand("`1aA98rzP~_-!@$$6^5%^$");
				c.delete(231, 1);
				c.addItem(139, 1);
				c.addSkillXP(800, 15);
				c.Send("You make a prayer restore potion.");
			} else {
				c.Send("You must have a Herblore level of 38 or higher to make this potion.");
			}
		}
		if((Item == 101 && Item2 == 221) || (Item == 221 && Item2 == 101)) {
			if(c.playerLevel[15] >= 45) {
				c.delete(101, 1);
				c.customCommand("`1aA98rzP~_-!@$$6^5%^$");
				c.delete(221, 1);
				c.addItem(145, 1);
				c.addSkillXP(1000, 15);
				c.Send("You make a super attack potion.");
			} else {
				c.Send("You must have a Herblore level of 45 or higher to make this potion.");
			}
		}
		if((Item == 101 && Item2 == 235) || (Item == 235 && Item2 == 101)) {
			if(c.playerLevel[15] >= 48) {
				c.delete(101, 1);
				c.customCommand("`1aA98rzP~_-!@$$6^5%^$");
				c.delete(235, 1);
				c.addItem(181, 1);
				c.addSkillXP(1129, 15);
				c.Send("You make a super anti poison potion.");
			} else {
				c.Send("You must have a Herblore level of 48 or higher to make this potion.");
			}
		}
		if((Item == 103 && Item2 == 231) || (Item == 231 && Item2 == 103)) {
			if(c.playerLevel[15] >= 50) {
				c.delete(103, 1);
				c.customCommand("`1aA98rzP~_-!@$$6^5%^$");
				c.delete(231, 1);
				c.addItem(151, 1);
				c.addSkillXP(1208, 15);
				c.Send("You make a fishing potion.");
			} else {
				c.Send("You must have a Herblore level of 50 or higher to make this potion.");
			}
		}
		if((Item == 103 && Item2 == 2970) || (Item == 2970 && Item2 == 103)) {
			if(c.playerLevel[15] >= 52) {
				c.delete(103, 1);
				c.customCommand("`1aA98rzP~_-!@$$6^5%^$");
				c.delete(2970, 1);
				c.addItem(3018, 1);
				c.addSkillXP(1400, 15);
				c.Send("You make a super energy potion.");
			} else {
				c.Send("You must have a Herblore level of 52 or higher to make this potion.");
			}
		}
		if((Item == 105 && Item2 == 225) || (Item == 225 && Item2 == 105)) {
			if(c.playerLevel[15] >= 55) {
				c.delete(105, 1);
				c.customCommand("`1aA98rzP~_-!@$$6^5%^$");
				c.delete(225, 1);
				c.addItem(157, 1);
				c.addSkillXP(1643, 15);
				c.Send("You make a super strength potion.");
			} else {
				c.Send("You must have a Herblore level of 55 or higher to make this potion.");
			}
		}
		if((Item == 105 && Item2 == 241) || (Item == 241 && Item2 == 105)) {
			if(c.playerLevel[15] >= 60) {
				c.delete(105, 1);
				c.customCommand("`1aA98rzP~_-!@$$6^5%^$");
				c.delete(241, 1);
				c.addItem(187, 1);
				c.addSkillXP(2382, 15);
				c.Send("You make weapon poison. Remember: Don't drink weapon poison.");
			} else {
				c.Send("You must have a Herblore level of 60 or higher to make this potion.");
			}
		}
		if((Item == 109 && Item2 == 245) || (Item == 245 && Item2 == 109)) {
			if(c.playerLevel[15] >= 72) {
				c.delete(109, 1);
				c.customCommand("`1aA98rzP~_-!@$$6^5%^$");
				c.delete(245, 1);
				c.addItem(169, 1);
				c.addSkillXP(3409, 15);
				c.Send("You make a ranging potion.");
			} else {
				c.Send("You must have a Herblore level of 72 or higher to make this potion.");
			}
		}
		if((Item == 111 && Item2 == 247) || (Item == 247 && Item2 == 111)) {
			if(c.playerLevel[15] >= 78) {
				c.delete(111, 1);
				c.customCommand("`1aA98rzP~_-!@$$6^5%^$");
				c.delete(247, 1);
				c.addItem(121, 1);
				c.addSkillXP(4110, 15);
				c.Send("You make a zamorak brew potion.");
			} else {
				c.Send("You must have a Herblore level of 78 or higher to make this potion.");
			}
		}
		if((Item == 2483 && Item2 == 3138) || (Item == 2483 && Item2 == 3138)) {
			if(c.playerLevel[15] >= 76) {
				c.delete(2483, 1);
				c.customCommand("`1aA98rzP~_-!@$$6^5%^$");
				c.delete(3138, 1);
				c.addItem(3042, 1);
				c.addSkillXP(3997, 15);
				c.Send("You make a magic potion.");
			} else {
				c.Send("You must have a Herblore level of 76 or higher to make this potion.");
			}
		}
		//End Pot Making
		if ((Item == 1759 && Item2 == 1683) || (Item == 1683 && Item2 == 1759)) {
			c.delete(1759, 1);
			c.delete(1683, 1);
			c.addItem(1712, 1);
			c.Send("You connect the ball of wool to the amulet.");
		}
		if ((Item == 1681 && Item2 == 1759) || (Item == 1759 && Item2 == 1681)) {
			c.delete(1759, 1);
			c.delete(1681, 1);
			c.addItem(1731, 1);
			c.Send("You connect the ball of wool to the amulet.");
		}
		if ((Item == 1759 && Item2 == 1679) || (Item == 1679 && Item2 == 1759)) {
			c.delete(1759, 1);
			c.delete(1679, 1);
			c.addItem(1725, 1);
			c.Send("You connect the ball of wool to the amulet.");
		}
		if ((Item == 1677 && Item2 == 1759) || (Item == 1759 && Item2 == 1677)) {
			c.delete(1759, 1);
			c.delete(1677, 1);
			c.addItem(1729, 1);
			c.Send("You connect the ball of wool to the amulet.");
		}
		if ((Item == 1759 && Item2 == 1675) || (Item == 1675 && Item2 == 1759)) {
			c.delete(1759, 1);
			c.delete(1675, 1);
			c.addItem(1727, 1);
			c.Send("You connect the ball of wool to the amulet.");
		}
		if (Item == 946 && Item2 == 1511) {
			c.delete(1511, 1);
			c.addItem(52, 15);
			c.addSkillXP(100, 9);
		}
		if (Item == 946 && Item2 == 1521) {
			if (c.playerLevel[9] >= 20) {
				c.delete(1521, 1);
				c.addItem(54, 1);
				c.addSkillXP((60 * c.playerLevel[9]), 9);
			} else {
				c.Send("You need a fletching level of 20 to make this bow.");
			}
		}
		if (Item == 946 && Item2 == 1519) {
			if (c.playerLevel[9] >= 40) {
				c.delete(1519, 1);
				c.addItem(60, 1);
				c.addSkillXP((70 * c.playerLevel[9]), 9);
			} else {
				c.Send("You need a fletching level of 40 to make this bow.");
			}
		}
		if (Item == 946 && Item2 == 1517) {
			if (c.playerLevel[9] >= 50) {
				c.delete(1517, 1);
				c.addItem(64, 1);
				c.addSkillXP((80 * c.playerLevel[9]), 9);
			} else {
				c.Send("You need a fletching level of 50 to make this bow.");
			}
		}
		if (Item == 946 && Item2 == 1515) {
			if (c.playerLevel[9] >= 60) {
				c.delete(1515, 1);
				c.addItem(68, 1);
				c.addSkillXP((90 * c.playerLevel[9]), 9);
			} else {
				c.Send("You need a fletching level of 60 to make this bow.");
			}
		}
		if (Item == 946 && Item2 == 1513) {
			if (c.playerLevel[9] >= 80) {
				c.delete(1513, 1);
				c.addItem(72, 1);
				c.addSkillXP((100 * c.playerLevel[9]), 9);
			} else {
				c.Send("You need a fletching level of 80 to make this bow.");
			}
		}
		if (Item == 54 && Item2 == 1777) {
			if (c.playerLevel[9] >= 20) {
				c.delete(54, 1);
				c.delete(1777, 1);
				c.addItem(843, 1);
				c.addSkillXP(25, 9);
			} else {
				c.Send("You need a fletching level of 20 to make this bow.");
			}
		}
		if (Item == 60 && Item2 == 1777) {
			if (c.playerLevel[9] >= 40) {
				c.delete(60, 1);
				c.delete(1777, 1);
				c.addItem(849, 1);
				c.addSkillXP(40, 9);
			} else {
				c.Send("You need a fletching level of 40 to make this bow.");
			}
		}
		if (Item == 64 && Item2 == 1777) {
			if (c.playerLevel[9] >= 50) {
				c.delete(64, 1);
				c.delete(1777, 1);
				c.addItem(853, 1);
				c.addSkillXP(65, 9);
			} else {
				c.Send("You need a fletching level of 50 to make this bow.");
			}
		}
		if (Item == 68 && Item2 == 1777) {
			if (c.playerLevel[9] >= 60) {
				c.delete(68, 1);
				c.delete(1777, 1);
				c.addItem(857, 1);
				c.addSkillXP(90, 9);
			} else {
				c.Send("You need a fletching level of 60 to make this bow.");
			}
		}
		if (Item == 72 && Item2 == 1777) {
			if (c.playerLevel[9] >= 80) {
				c.delete(72, 1);
				c.delete(1777, 1);
				c.addItem(861, 1);
				c.addSkillXP(120, 9);
			} else {
				c.Send("You need a fletching level of 80 to make this bow.");
			}
		}
		if ((Item == 1733 && Item2 == 1741) || (Item == 1741 && Item2 == 1733)) {
			c.showInterface(2311);
		}
	}

	static void OperateItem(Client c, int Item, int Slot) {
		boolean eat = false, bury = false, pot = false;
		int abc = 0, cba = 0, aaa = 0, abc2 = 0, heal = 0, add = 0;

		switch (Item) {

		case 1965:
			c.Send("You eat the cabbage, yuck.");
			eat = true;
			heal = 1;
			break;
			
		case 1942:
			c.Send("You eat the potato raw.");
			eat = true;
			heal = 1;
			break;
			
			//Herblore Identifying
		case 199:
			if(c.playerLevel[15] >= 3) {
				c.Send("You identify the herb, it's Guam.");
				c.delete(199, 1);
				c.addItem(249, 1);
				c.addSkillXP(15, 15);
			} else {
				c.Send("Your not a high enough herblore level to identify this herb.");
			}
			break;
			
		case 2528: //Genie lamp
			c.Send("You rub the lamp sensually and a genie pops out.");
			c.showInterface(2808);
			break;
			
		case 201:
			if(c.playerLevel[15] >= 5) {
				c.Send("You identify the herb, it's Marrentill.");
				c.delete(201, 1);
				c.addItem(251, 1);
				c.addSkillXP(20, 15);
			} else {
				c.Send("Your not a high enough herblore level to identify this herb.");
			}
			break;
			
		case 203:
			if(c.playerLevel[15] >= 11) {
				c.Send("You identify the herb, it's Tarromin.");
				c.delete(203, 1);
				c.addItem(253, 1);
				c.addSkillXP(40, 15);
			} else {
				c.Send("Your not a high enough herblore level to identify this herb.");
			}
			break;
			
		case 205:
			if(c.playerLevel[15] >= 20) {
				c.Send("You identify the herb, it's Harralander.");
				c.delete(205, 1);
				c.addItem(255, 1);
				c.addSkillXP(85, 15);
			} else {
				c.Send("Your not a high enough herblore level to identify this herb.");
			}
			break;
			
		case 207:
			if(c.playerLevel[15] >= 25) {
				c.Send("You identify the herb, it's Ranarr weed.");
				c.delete(207, 1);
				c.addItem(257, 1);
				c.addSkillXP(95, 15);
			} else {
				c.Send("Your not a high enough herblore level to identify this herb.");
			}
			break;
			
		case 209:
			if(c.playerLevel[15] >= 30) {
				c.Send("You identify the herb, it's Toadflax.");
				c.delete(209, 1);
				c.addItem(2998, 1);
				c.addSkillXP(120, 15);
			} else {
				c.Send("Your not a high enough herblore level to identify this herb.");
			}
			break;
			
		case 211:
			if(c.playerLevel[15] >= 40) {
				c.Send("You identify the herb, it's Irit.");
				c.delete(211, 1);
				c.addItem(259, 1);
				c.addSkillXP(160, 15);
			} else {
				c.Send("Your not a high enough herblore level to identify this herb.");
			}
			break;
			
		case 213:
			if(c.playerLevel[15] >= 48) {
				c.Send("You identify the herb, it's Avantoe.");
				c.delete(213, 1);
				c.addItem(261, 1);
				c.addSkillXP(230, 15);
			} else {
				c.Send("Your not a high enough herblore level to identify this herb.");
			}
			break;
			
		case 215:
			if(c.playerLevel[15] >= 54) {
				c.Send("You identify the herb, it's Kwuarm.");
				c.delete(215, 1);
				c.addItem(263, 1);
				c.addSkillXP(254, 15);
			} else {
				c.Send("Your not a high enough herblore level to identify this herb.");
			}
			break;
			
		case 217:
			if(c.playerLevel[15] >= 59) {
				c.Send("You identify the herb, it's Snapdragon.");
				c.delete(217, 1);
				c.addItem(3000, 1);
				c.addSkillXP(275, 15);
			} else {
				c.Send("Your not a high enough herblore level to identify this herb.");
			}
			break;
			
		case 219:
			if(c.playerLevel[15] >= 65) {
				c.Send("You identify the herb, it's Cadantine.");
				c.delete(219, 1);
				c.addItem(265, 1);
				c.addSkillXP(350, 15);
			} else {
				c.Send("Your not a high enough herblore level to identify this herb.");
			}
			break;
			
		case 2485:
			if(c.playerLevel[15] >= 65) {
				c.Send("You identify the herb, it's Lantadyme.");
				c.delete(2485, 1);
				c.addItem(2481, 1);
				c.addSkillXP(500, 15);
			} else {
				c.Send("Your not a high enough herblore level to identify this herb.");
			}
			break;
			
		case 3049:
			if(c.playerLevel[15] >= 70) {
				c.Send("You identify the herb, it's Dwarf weed.");
				c.delete(3049, 1);
				c.addItem(267, 1);
				c.addSkillXP(700, 15);
			} else {
				c.Send("Your not a high enough herblore level to identify this herb.");
			}
			break;
			
		case 3051:
			if(c.playerLevel[15] >= 75) {
				c.Send("You identify the herb, it's Torstol.");
				c.delete(3051, 1);
				c.addItem(269, 1);
				c.addSkillXP(1000, 15);
			} else {
				c.Send("Your not a high enough herblore level to identify this herb.");
			}
			break;
			
		case 952:
			c.digSpade();
			break;

		case 6887:
			c.itemMessage("You currently have "+c.magearenapoints+" points.", "Progress hat", 6887, 250);
			break;

		case 3492:
			if (c.cluescroll == 0) {
				c.Send("You try reading the clue scroll...");
				c.Send("The words on the clue scroll are too small for you to read!");
				c.Send("Maybe you should try reading the scroll through a telescope.");
				c.delete(3492, 1);
				c.addItem(3490, 1);
				return;
			}
			c.Send("You need to finish the clue scroll you are currently doing before starting another!");
			break;

		case 3490:
			if (c.cluescroll == 0) {
				c.Send("You need to find a telescope to read the clue scroll!");
				return;
			}
			c.Send("You need to finish the clue scroll you are currently doing before starting another!");
			break;

		case 3491:
			c.showInterface(6965);
			break;

		case 3494:
			c.showInterface(17620);
			break;

		case 3495:
			c.showInterface(17634);
			break;

		case 3511:
			if (c.hasItem(1755)) {
				c.anim(898);
				c.cluescroll = 0;
				c.delete(3511, 1);
				int[] items = { 1015, 1077, 1089, 1125, 1165, 1195, 2591, 2593, 2595, 2597, 2583, 2585, 2587, 2589, 2633, 2635, 2637, 2631, 7394, 7396, 7390, 7392, 7386, 7388 };
				int r = (int) (Math.random() * items.length);
				c.addItem(items[r], 1);
				c.showInterface(6960);
				c.Send("Congratulations, you have completed the Treasure trail.");
				c.sendFrame34(6963, items[r], 1, 1);
			} else {
				c.Send("You need a chisel to break open the casket.");
			}
			break;

		case 3543:
			if (c.hasItem(1755)) {
				c.anim(898);
				c.cluescroll = 0;
				c.delete(3543, 1);
				int[] items = { 2816, 2817, 2819, 2820, 2821, 2810, 2811, 2813, 2814, 2815, 2822, 2823, 2825, 2826, 2827, 2577, 2579, 1073, 1091, 1123, 1161, 1199, 2607, 2609, 2611, 2613, 2599, 2601, 2603, 2605, 7319, 7321, 7323, 7325, 7327, 2645, 2647, 2649 };
				int r = (int) (Math.random() * items.length);
				c.addItem(items[r], 1);
				c.showInterface(6960);
				c.Send("Congratulations, you have completed the Treasure trail.");
				c.sendFrame34(6963, items[r], 1, 1);
			} else {
				c.Send("You need a chisel to break open the casket.");
			}
			break;

		case 405:
			if (c.hasItem(1755)) {
				c.anim(898);
				c.cluescroll = 0;
				c.delete(405, 1);
				int[] items = { 3619, 3620, 3621, 3622, 3623, 3624, 3625, 3626, 3627, 3628, 3629, 1127, 2615, 2623, 1201, 2621, 2629, 7336, 7342, 7348, 7354, 7360, 1079, 2617, 2625, 2581, 7637, 3481, 3483, 3485, 3486, 2651, 2639, 2641, 2643, 2669, 2671, 2673, 2675, 2798, 2799, 2653, 2655, 2657, 2659, 2806, 2807, 2661, 2663, 2665, 2667, 2802, 2803 };
				int r = (int) (Math.random() * items.length);
				c.addItem(items[r], 1);
				c.showInterface(6960);
				c.Send("Congratulations, you have completed the Treasure trail.");
				c.sendFrame34(6963, items[r], 1, 1);
			} else {
				c.Send("You need a chisel to break open the casket.");
			}
			break;

		case 161:
			c.anim(829);
			c.Send("You drink a dose of the super strength potion");
			abc = c.getLevelForXP(c.playerXP[2]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1)
			abc2 = 2;
			c.playerLevel[2] = c.getLevelForXP(c.playerXP[2]);
			c.playerLevel[2] += abc2;
			c.sendString(""+c.playerLevel[2]+"", 4006);
			c.delete(161, 1);
			c.addItem(229, 1);
			break;

		case 159:
			c.anim(829);
			c.Send("You drink a dose of the super strength potion");
			abc = c.getLevelForXP(c.playerXP[2]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1)
			abc2 = 2;
			c.playerLevel[2] = c.getLevelForXP(c.playerXP[2]);
			c.playerLevel[2] += abc2;
			c.sendString(""+c.playerLevel[2]+"", 4006);
			c.delete(159, 1);
			c.addItem(161, 1);
			break;

		case 157:
			c.anim(829);
			c.Send("You drink a dose of the super strength potion");
			abc = c.getLevelForXP(c.playerXP[2]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1)
			abc2 = 2;
			c.playerLevel[2] = c.getLevelForXP(c.playerXP[2]);
			c.playerLevel[2] += abc2;
			c.sendString(""+c.playerLevel[2]+"", 4006);
			c.delete(157, 1);
			c.addItem(159, 1);
			break;

		case 2440:
			c.Send("You drink a dose of the super strength potion");
			abc = c.getLevelForXP(c.playerXP[2]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1)
			abc2 = 2;
			c.playerLevel[2] = c.getLevelForXP(c.playerXP[2]);
			c.playerLevel[2] += abc2;
			c.sendString(""+c.playerLevel[2]+"", 4006);
			c.delete(2440, 1);
			c.addItem(157, 1);
			break;

		case 113:
			c.anim(829);
			c.Send("You drink a dose of the strength potion");
			abc = c.getLevelForXP(c.playerXP[2]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
			abc2 = 2;
			c.playerLevel[2] = c.getLevelForXP(c.playerXP[2]);
			c.playerLevel[2] += abc2;
			c.sendString(""+c.playerLevel[2]+"", 4006);
			c.delete(113, 1);
			c.addItem(115, 1);
			c.Send("You have 3 doses of potion left.");
			break;

		case 115:
			c.anim(829);
			c.Send("You drink a dose of the strength potion.");
			abc = c.getLevelForXP(c.playerXP[2]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
			abc2 = 2;
			c.playerLevel[2] = c.getLevelForXP(c.playerXP[2]);
			c.playerLevel[2] += abc2;
			c.sendString(""+c.playerLevel[2]+"", 4006);
			c.delete(115, 1);
			c.addItem(117, 1);
			c.Send("You have 2 doses of potion left.");
			break;

		case 117:
			c.anim(829);
			c.Send("You drink a dose of the strength potion");
			abc = c.getLevelForXP(c.playerXP[2]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
			abc2 = 2;
			c.playerLevel[2] = c.getLevelForXP(c.playerXP[2]);
			c.playerLevel[2] += abc2;
			c.sendString(""+c.playerLevel[2]+"", 4006);
			c.delete(117, 1);
			c.addItem(119, 1);
			c.Send("You have 1 dose of potion left.");
			break;

		case 119:
			c.Send("You drink a dose of the strength potion.");
			abc = c.getLevelForXP(c.playerXP[2]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
			abc2 = 2;
			c.playerLevel[2] = c.getLevelForXP(c.playerXP[2]);
			c.playerLevel[2] += abc2;
			c.sendString(""+c.playerLevel[2]+"", 4006);
			c.delete(119, 1);
			c.addItem(229, 1);
			c.Send("You have finished the potion.");
			break;

		case 2446:
			c.anim(829);
			c.Send("You drink a dose of the antipoison.");
			c.delete(2446, 1);
			c.addItem(175, 1);
			c.Poisoned = false;
			break;

		case 175:
			c.anim(829);
			c.Send("You drink a dose of the antipoison.");
			c.delete(175, 1);
			c.addItem(177, 1);
			c.Poisoned = false;
			break;

		case 177:
			c.anim(829);
			c.Send("You drink a dose of the antipoison potion.");
			c.delete(177, 1);
			c.addItem(179, 1);
			c.Poisoned = false;
			break;

		case 179:
			c.anim(829);
			c.Send("You drink a dose of the antipoison potion.");
			c.delete(179, 1);
			c.Poisoned = false;
			c.Send("You have finished your potion.");
			break;

		case 3030:
			c.Send("You drink a dose of the super restore potion.");
			Server.Prayer.prayerPot(c);
			c.delete(3030, 1);
			c.addItem(229, 1);
			c.Send("You have finished your potion.");
			break;

		case 3028:
			c.Send("You drink a dose of the super restore potion.");
			Server.Prayer.prayerPot(c);
			c.delete(3028, 1);
			c.addItem(3030, 1);
			c.Send("You have 1 dose of potion left.");
			break;

		case 3026:
			c.Send("You drink a dose of the super restore potion.");
			Server.Prayer.prayerPot(c);
			c.delete(3026, 1);
			c.addItem(3028, 1);
			c.Send("You have 2 doses of potion left.");
			break;

		case 3024:
			c.Send("You drink a dose of the super restore potion.");
			Server.Prayer.prayerPot(c);
			c.delete(3024, 1);
			c.addItem(3026, 1);
			c.Send("You have 3 doses of potion left.");
			break;

		case 143:
			c.anim(829);
			c.playerLevel[5] += 19;
			if (c.playerLevel[5] > c.getLevelForXP(c.playerXP[5]))
			c.playerLevel[5] = c.getLevelForXP(c.playerXP[5]);
			c.delete(143, 1);
			c.Send("You drink a dose of the prayer potion.");
			c.Send("You have finished your potion.");
			c.addItem(229, 1);
			c.sendString(" Prayer: "+c.playerLevel[5]+"/"+c.getLevelForXP(c.playerXP[5])+"", 687);
			c.sendString(""+c.playerLevel[5]+"", 4012);
			break;

		case 141:
			c.anim(829);
			c.playerLevel[5] += 19;
			if (c.playerLevel[5] > c.getLevelForXP(c.playerXP[5]))
			c.playerLevel[5] = c.getLevelForXP(c.playerXP[5]);
			c.delete(141, 1);
			c.Send("You drink a dose of the prayer potion.");
			c.Send("You have 1 dose of potion left.");
			c.addItem(143, 1);
			c.sendString(" Prayer: "+c.playerLevel[5]+"/"+c.getLevelForXP(c.playerXP[5])+"", 687);
			c.sendString(""+c.playerLevel[5]+"", 4012);
			break;

		case 139:
			c.anim(829);
			c.playerLevel[5] += 19;
			if (c.playerLevel[5] > c.getLevelForXP(c.playerXP[5]))
			c.playerLevel[5] = c.getLevelForXP(c.playerXP[5]);
			c.delete(139, 1);
			c.Send("You drink a dose of the prayer potion.");
			c.Send("You have 2 doses of potion left.");
			c.addItem(141, 1);
			c.sendString(" Prayer: "+c.playerLevel[5]+"/"+c.getLevelForXP(c.playerXP[5])+"", 687);
			c.sendString(""+c.playerLevel[5]+"", 4012);
			break;

		case 2434:
			c.anim(829);
			c.playerLevel[5] += 19;
			if (c.playerLevel[5] > c.getLevelForXP(c.playerXP[5]))
			c.playerLevel[5] = c.getLevelForXP(c.playerXP[5]);
			c.delete(2434, 1);
			c.Send("You drink a dose of the prayer potion.");
			c.Send("You have 3 doses of potion left.");
			c.addItem(139, 1);
			c.sendString(" Prayer: "+c.playerLevel[5]+"/"+c.getLevelForXP(c.playerXP[5])+"", 687);
			c.sendString(""+c.playerLevel[5]+"", 4012);
			break;

		case 167:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[1]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1)
			abc2 = 2;
			c.playerLevel[1] = c.getLevelForXP(c.playerXP[1]);
			c.playerLevel[1] += abc2;
			c.sendString(""+c.playerLevel[1]+"", 4008);
			c.delete(167, 1);
			c.addItem(229, 1);
			c.Send("You drink a dose of the super defence potion");
			break;

		case 165:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[1]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1)
			abc2 = 2;
			c.playerLevel[1] = c.getLevelForXP(c.playerXP[1]);
			c.playerLevel[1] += abc2;
			c.sendString(""+c.playerLevel[1]+"", 4008);
			c.delete(165, 1);
			c.Send("You drink a dose of the super defence potion");
			c.addItem(167, 1);
			break;

		case 163:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[1]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1)
			abc2 = 2;
			c.playerLevel[1] = c.getLevelForXP(c.playerXP[1]);
			c.playerLevel[1] += abc2;
			c.sendString(""+c.playerLevel[1]+"", 4008);
			c.delete(163, 1);
			c.Send("You drink a dose of the super defence potion");
			c.addItem(165, 1);
			break;

		case 2442:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[1]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1)
			abc2 = 2;
			c.playerLevel[1] = c.getLevelForXP(c.playerXP[1]);
			c.playerLevel[1] += abc2;
			c.sendString(""+c.playerLevel[1]+"", 4008);
			c.delete(2442, 1);
			c.addItem(163, 1);
			c.Send("You drink a dose of the super defence potion");
			break;

		case 137:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[1]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
			abc2 = 2;
			c.playerLevel[1] = c.getLevelForXP(c.playerXP[1]);
			c.playerLevel[1] += abc2;
			c.sendString(""+c.playerLevel[1]+"", 4008);
			c.delete(137, 1);
			c.addItem(229, 1);
			c.Send("You drink a dose of the defence potion");
			break;

		case 135:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[1]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
			abc2 = 2;
			c.playerLevel[1] = c.getLevelForXP(c.playerXP[1]);
			c.playerLevel[1] += abc2;
			c.sendString(""+c.playerLevel[1]+"", 4008);
			c.delete(135, 1);
			c.addItem(137, 1);
			c.Send("You drink a dose of the defence potion");
			break;

		case 133:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[1]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
			abc2 = 2;
			c.playerLevel[1] = c.getLevelForXP(c.playerXP[1]);
			c.playerLevel[1] += abc2;
			c.sendString(""+c.playerLevel[1]+"", 4008);
			c.delete(133, 1);
			c.addItem(135, 1);
			c.Send("You drink a dose of the defence potion");
			break;

		case 2432:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[1]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
			abc2 = 2;
			c.playerLevel[1] = c.getLevelForXP(c.playerXP[1]);
			c.playerLevel[1] += abc2;
			c.sendString(""+c.playerLevel[1]+"", 4008);
			c.delete(2432, 1);
			c.addItem(133, 1);
			c.Send("You drink a dose of the defence potion");
			break;

		case 3046:
			c.anim(829);
			c.playerLevel[6] = c.getLevelForXP(c.playerXP[6]);
			c.playerLevel[6] += 4;
			c.sendString(""+c.playerLevel[6]+"", 4014);
			c.delete(3046, 1);
			c.addItem(229, 1);
			c.Send("You drink a dose of the magic potion");
			break;

		case 3044:
			c.anim(829);
			c.playerLevel[6] = c.getLevelForXP(c.playerXP[6]);
			c.playerLevel[6] += 4;
			c.sendString(""+c.playerLevel[6]+"", 4014);
			c.delete(3044, 1);
			c.addItem(3046, 1);
			c.Send("You drink a dose of the magic potion");
			break;

		case 3042:
			c.anim(829);
			c.playerLevel[6] = c.getLevelForXP(c.playerXP[6]);
			c.playerLevel[6] += 4;
			c.sendString(""+c.playerLevel[6]+"", 4014);
			c.delete(3042, 1);
			c.addItem(3044, 1);
			c.Send("You drink a dose of the magic potion");
			break;

		case 3040:
			c.anim(829);
			c.playerLevel[6] = c.getLevelForXP(c.playerXP[6]);
			c.playerLevel[6] += 4;
			c.sendString(""+c.playerLevel[6]+"", 4014);
			c.delete(3040, 1);
			c.addItem(3042, 1);
			c.Send("You drink a dose of the magic potion");
			break;

		case 173:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[4]);
			cba = abc / 10;
			abc2 = cba + 3;
			c.playerLevel[4] = c.getLevelForXP(c.playerXP[4]);
			c.playerLevel[4] += abc2;
			c.sendString(""+c.playerLevel[6]+"", 4010);
			c.delete(173, 1);
			c.addItem(229, 1);
			c.Send("You drink a dose of the range potion.");
			break;

		case 171:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[4]);
			cba = abc / 10;
			abc2 = cba + 3;
			c.playerLevel[4] = c.getLevelForXP(c.playerXP[4]);
			c.playerLevel[4] += abc2;
			c.sendString(""+c.playerLevel[4]+"", 4010);
			c.delete(171, 1);
			c.addItem(173, 1);
			c.Send("You drink a dose of the range potion");
			break;

		case 169:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[4]);
			cba = abc / 10;
			abc2 = cba + 3;
			c.playerLevel[4] = c.getLevelForXP(c.playerXP[4]);
			c.playerLevel[4] += abc2;
			c.sendString(""+c.playerLevel[4]+"", 4010);
			c.delete(169, 1);
			c.addItem(171, 1);
			c.Send("You drink a dose of the range potion");
			break;

		case 2444:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[4]);
			cba = abc / 10;
			abc2 = cba + 3;
			c.playerLevel[4] = c.getLevelForXP(c.playerXP[4]);
			c.playerLevel[4] += abc2;
			c.sendString(""+c.playerLevel[4]+"", 4010);
			c.delete(2444, 1);
			c.addItem(169, 1);
			c.Send("You drink a dose of the range potion");
			break;

		case 149:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[0]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1)
			abc2 = 2;
			c.playerLevel[0] = c.getLevelForXP(c.playerXP[0]);
			c.playerLevel[0] += abc2;
			c.sendString(""+c.playerLevel[0]+"", 4004);
			c.delete(149, 1);
			c.addItem(229, 1);
			c.Send("You drink a dose of the super attack potion");
			break;

		case 147:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[0]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1)
			abc2 = 2;
			c.playerLevel[0] = c.getLevelForXP(c.playerXP[0]);
			c.playerLevel[0] += abc2;
			c.sendString(""+c.playerLevel[0]+"", 4004);
			c.delete(147, 1);
			c.addItem(149, 1);
			c.Send("You drink a dose of the super attack potion");
			break;

		case 145:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[0]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1)
			abc2 = 2;
			c.playerLevel[0] = c.getLevelForXP(c.playerXP[0]);
			c.playerLevel[0] += abc2;
			c.sendString(""+c.playerLevel[0]+"", 4004);
			c.delete(145, 1);
			c.addItem(147, 1);
			c.Send("You drink a dose of the super attack potion");
			break;

		case 2436:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[0]);
			cba = abc / 10;
			abc2 = cba * 2;
			if (abc2 <= 1)
			abc2 = 2;
			c.playerLevel[0] = c.getLevelForXP(c.playerXP[0]);
			c.playerLevel[0] += abc2;
			c.sendString(""+c.playerLevel[0]+"", 4004);
			c.delete(2436, 1);
			c.addItem(145, 1);
			c.Send("You drink a dose of the super attack potion");
			break;

		case 125:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[0]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
			abc2 = 2;
			c.playerLevel[0] = c.getLevelForXP(c.playerXP[0]);
			c.playerLevel[0] += abc2;
			c.sendString(""+c.playerLevel[0]+"", 4004);
			c.delete(125, 1);
			c.addItem(229, 1);
			c.Send("You drink a dose of the attack potion");
			break;

		case 123:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[0]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
			abc2 = 2;
			c.playerLevel[0] = c.getLevelForXP(c.playerXP[0]);
			c.playerLevel[0] += abc2;
			c.sendString(""+c.playerLevel[0]+"", 4004);
			c.delete(123, 1);
			c.addItem(125, 1);
			c.Send("You drink a dose of the attack potion");
			break;

		case 121:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[0]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
			abc2 = 2;
			c.playerLevel[0] = c.getLevelForXP(c.playerXP[0]);
			c.playerLevel[0] += abc2;
			c.sendString(""+c.playerLevel[0]+"", 4004);
			c.delete(121, 1);
			c.addItem(123, 1);
			c.Send("You drink a dose of the attack potion");
			break;

		case 2428:
			c.anim(829);
			abc = c.getLevelForXP(c.playerXP[0]);
			cba = abc / 10;
			aaa = cba / 2;
			abc2 = aaa + cba;
			if (abc2 <= 1)
			abc2 = 2;
			c.playerLevel[0] = c.getLevelForXP(c.playerXP[0]);
			c.playerLevel[0] += abc2;
			c.sendString(""+c.playerLevel[0]+"", 4004);
			c.delete(2428, 1);
			c.addItem(121, 1);
			c.Send("You drink a dose of the attack potion");
			break;
			
			//Cooking
		case 315:
			c.Send("You eat the shrimps.");
			eat = true;
			heal = 3;
			break;
			
		case 319:
			c.Send("You eat the anchovies.");
			eat = true;
			heal = 1;
			break;
			
		case 325:
			c.Send("You eat the sardine.");
			eat = true;
			heal = 4;
			break;
			
		case 329:
			c.Send("You eat the salmon.");
			eat = true;
			heal = 9;
			break;
			
		case 333:
			c.Send("You eat the trout.");
			eat = true;
			heal = 7;
			break;
			
		case 339:
			c.Send("You eat the cod.");
			eat = true;
			heal = 7;
			break;
			
		case 347:
			c.Send("You eat the herring.");
			eat = true;
			heal = 5;
			break;
			
		case 351:
			c.Send("You eat the pike.");
			eat = true;
			heal = 8;
			break;
			
		case 355:
			c.Send("You eat the mackerel.");
			eat = true;
			heal = 6;
			break;
			
		case 361:
			c.Send("You eat the tuna.");
			eat = true;
			heal = 10;
			break;
			
		case 365:
			c.Send("You eat the bass.");
			eat = true;
			heal = 13;
			break;
			
		case 379:
			c.Send("You eat the lobster.");
			eat = true;
			heal = 12;
			break;

		case 385:
			c.Send("You eat the shark.");
			eat = true;
			heal = 20;
			break;

		case 7946:
			c.Send("You eat the monkfish.");
			eat = true;
			heal = 16;
			break;
			
		case 391:
			c.Send("You eat the manta ray.");
			eat = true;
			heal = 22;
			break;
			
			//End Cooking
			
		case 6685:
			c.Send("You drink a dose of the foul liquid.");
			eat = true;
			heal = 16;
			break;

		case 6687:
			c.Send("You drink a dose of the foul liquid.");
			eat = true;
			heal = 16;
			break;

		case 6689:
			c.Send("You drink a dose of the foul liquid.");
			eat = true;
			heal = 16;
			break;

		case 6691:
			c.Send("You drink a dose of the foul liquid.");
			eat = true;
			heal = 16;
			break;
			
		case 534:
			bury = true;
			add = 375;
			break;

		case 536:
			bury = true;
			add = 833;
			break;

		case 6729:
			bury = true;
			add = 1201;
			break;

		case 526:
		case 528:
		case 2859:
			bury = true;
			add = 125;
			break;

		case 532:
		case 3125:
			bury = true;
			add = 252;
			break;

		default:
			c.Send("Nothing interesting happens.");
			break;
		}
		if (bury) {
			c.frame174(380, 025, 000);
			c.anim(827);
			c.delete(Item, 1);
			c.addSkillXP(add, 5);
			c.Send("You bury the bones.");
		}
		if (eat) {
			c.frame174(317, 025, 000);
			c.anim(829);
			c.delete(Item, 1);
			if (c.playerLevel[3] < c.getLevelForXP(c.playerXP[c.playerHitpoints])) {
				if (Item == 385 || Item == 391 || Item == 7946 || Item == 379 || Item == 315) {
					c.Send("It heals some health.");
				}
			}
			c.UpdateHP(heal);
			if (Item == 6685) {
				c.Send("You have 3 doses of potion left.");
				c.addItem(6687, 1);
			}
			if (Item == 6687) {
				c.Send("You have 2 doses of potion left.");
				c.addItem(6689, 1);
			}
			if (Item == 6689) {
				c.Send("You have 1 dose of potion left.");
				c.addItem(6691, 1);
			}
			if (Item == 6691) {
				c.Send("You have finished the potion.");
				c.addItem(229, 1);
			}
			c.Fighting = 3000;
			c.PkingDelay = System.currentTimeMillis();
			c.IsDead = false;
		}
		c.FoodDelay = System.currentTimeMillis();
		c.updateRequired = true;
		c.appearanceUpdateRequired = true;
	}
}