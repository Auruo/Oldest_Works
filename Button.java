class Button {

	void doAction(int b, Client c) {
		if (System.currentTimeMillis() - c.ButtonDelay < 300) {
			return;
		}
		switch(b) {
		case 64073:
			c.showInterface(16460);
			break;
		case 32197:
			if (System.currentTimeMillis() - c.ButtonDelay > 1005) {
				Server.Agility.agilityShop(c, 1);
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;
		case 32195:
			if (System.currentTimeMillis() - c.ButtonDelay > 1005) {
				Server.Agility.agilityShop(c, 8);
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;
		case 32190:
			if (System.currentTimeMillis() - c.ButtonDelay > 1005) {
				Server.Agility.agilityShop(c, 2);
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;
		case 32198:
			if (System.currentTimeMillis() - c.ButtonDelay > 1005) {
				Server.Agility.agilityShop(c, 3);
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;
		case 32199:
			if (System.currentTimeMillis() - c.ButtonDelay > 1005) {
				Server.Agility.agilityShop(c, 4);
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;
		case 32189:
			if (System.currentTimeMillis() - c.ButtonDelay > 1005) {
				Server.Agility.agilityShop(c, 5);
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;
		case 32200:
			if (System.currentTimeMillis() - c.ButtonDelay > 1005) {
				Server.Agility.agilityShop(c, 6);
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;
		case 32201:
			if (System.currentTimeMillis() - c.ButtonDelay > 1005) {
				Server.Agility.agilityShop(c, 7);
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;
			
		case 10252:
			if (System.currentTimeMillis() - c.ButtonDelay > 750) {
				c.genieSelect = 0;
				c.Send("You select Attack");
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;
			
		case 10253:
			if (System.currentTimeMillis() - c.ButtonDelay > 750) {
				c.genieSelect = 2;
				c.Send("You select Strength");
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;
			
		case 10254:
			if (System.currentTimeMillis() - c.ButtonDelay > 750) {
				c.genieSelect = 4;
				c.Send("You select Ranged");
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;
			
		case 10255:
			if (System.currentTimeMillis() - c.ButtonDelay > 750) {
				c.genieSelect = 6;
				c.Send("You select Magic");
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;
			
		case 11000:
			if (System.currentTimeMillis() - c.ButtonDelay > 750) {
				c.genieSelect = 1;
				c.Send("You select Defence");
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;
			
		case 11001:
			if (System.currentTimeMillis() - c.ButtonDelay > 750) {
				c.genieSelect = 3;
				c.Send("You select Hitpoints");
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;
			
		case 11002:
			if (System.currentTimeMillis() - c.ButtonDelay > 750) {
				c.genieSelect = 5;
				c.Send("You select Prayer");
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;
			
		case 11003:
			if (System.currentTimeMillis() - c.ButtonDelay > 750) {
				c.genieSelect = 16;
				c.Send("You select Agility");
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;
			
		case 11004:
			if (System.currentTimeMillis() - c.ButtonDelay > 750) {
				c.genieSelect = 15;
				c.Send("You select Herblore");
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;
			
		case 11005:
			if (System.currentTimeMillis() - c.ButtonDelay > 750) {
				c.genieSelect = 17;
				c.Send("You select Thieving");
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;
			
		case 11006:
			if (System.currentTimeMillis() - c.ButtonDelay > 750) {
				c.genieSelect = 12;
				c.Send("You select Crafting");
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;
			
		case 11007:
			if (System.currentTimeMillis() - c.ButtonDelay > 750) {
				c.genieSelect = 20;
				c.Send("You select Runecrafting");
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;
			
		case 47002:
			if (System.currentTimeMillis() - c.ButtonDelay > 750) {
				c.genieSelect = 18;
				c.Send("You select PKing.");
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;
			
		case 54090:
			if (System.currentTimeMillis() - c.ButtonDelay > 750) {
				c.genieSelect = 19;
				c.Send("You select Farming");
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;
			
		case 11008:
			if (System.currentTimeMillis() - c.ButtonDelay > 750) {
				c.genieSelect = 14;
				c.Send("You select Mining");
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;
			
		case 11009:
			if (System.currentTimeMillis() - c.ButtonDelay > 750) {
				c.genieSelect = 13;
				c.Send("You select Smithing");
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;
			
		case 11010:
			if (System.currentTimeMillis() - c.ButtonDelay > 750) {
				c.genieSelect = 10;
				c.Send("You select Fishing");
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;
			
		case 11011:
			if (System.currentTimeMillis() - c.ButtonDelay > 750) {
				c.genieSelect = 7;
				c.Send("You select Cooking");
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;
			
		case 11012:
			if (System.currentTimeMillis() - c.ButtonDelay > 750) {
				c.genieSelect = 11;
				c.Send("You select Firemaking");
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;
			
		case 11013:
			if (System.currentTimeMillis() - c.ButtonDelay > 750) {
				c.genieSelect = 8;
				c.Send("You select Woodcutting");
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;
			
		case 11014:
			if (System.currentTimeMillis() - c.ButtonDelay > 750) {
				c.genieSelect = 9;
				c.Send("You select Fletching");
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;
			
		case 11015:
			if(c.hasAmount(2528,1) == true){
				c.addSkillXP((int)(Math.random()*5500)+7500,c.genieSelect);
				c.deleteItem(2528, c.getItemSlot(2528), 1);
				c.Send("The genie flies back inside the lamp.");
				c.Send("The lamp mysteriously vanishes.");
				c.RemoveAllWindows();
			}
			break;
			
			//Stats
		case 24125:
			if (System.currentTimeMillis() - c.ButtonDelay > 1005 && !c.PkStats) {
				c.AllStats = false;
				c.PkStats = true;
				c.NpcStats = false;
				Server.Text.UpdateText(c);
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;
		case 24126:
			if (System.currentTimeMillis() - c.ButtonDelay > 1005 && !c.AllStats) {
				c.AllStats = true;
				c.PkStats = false;
				c.NpcStats = false;
				Server.Text.UpdateText(c);
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;
		case 38197:
			if (System.currentTimeMillis() - c.ButtonDelay > 1005 && !c.NpcStats) {
				c.AllStats = false;
				c.PkStats = false;
				c.NpcStats = true;
				Server.Text.UpdateText(c);
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;
			
			//Settings
		case 3173:
			c.SoundOn = 0;
			break;
		case 3174:
			c.SoundOn = 1;
			break;
		case 3175:
			c.SoundOn = 1;
			break;
		case 3176:
			c.SoundOn = 1;
			break;
		case 3177:
			c.SoundOn = 1;
			break;
			
		case 3189:
			c.SplitChat = 1;
			break;
		case 3190:
			c.SplitChat = 0;
			break;
			
		case 3138:
			c.BrightnessLevel = 1;
			break;
		case 3140:
			c.BrightnessLevel = 2;
			break;
		case 3142:
			c.BrightnessLevel = 3;
			break;
		case 3144:
			c.BrightnessLevel = 4;
			break;
			
			//Quests
		case 28164: // quest 0
			if (System.currentTimeMillis() - c.ButtonDelay > 1005) {
				Server.qHandler.writeQuestMenu(0, c);
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;
		case 28165: // quest 1
			if (System.currentTimeMillis() - c.ButtonDelay > 1005) {
				Server.qHandler.writeQuestMenu(1, c);
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;
		case 28166: // quest 2
			if (System.currentTimeMillis() - c.ButtonDelay > 1005) {
				Server.qHandler.writeQuestMenu(2, c);
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;
			
		case 31195: //insert
			c.bankRearrange = "insert";
			break;
			
		case 31194: //swap
			c.bankRearrange = "swap";
			break;
			
		case 28175:
			if (System.currentTimeMillis() - c.ButtonDelay > 1005) {
				int amt = 0;
				if (c.IsDead)
				return;
				for (int k = 0; k < 4; k++) {
					c.sendFrame34(6822, -1, k, 1);
				}
				if (c.Skulled) {
					if (c.protItem) {
						c.keepItem1();
						if (c.itemKept1 > 0) {
							c.sendFrame34(6822, c.itemKept1, 0, 1);
						}
						c.sendString("@or1@Max items kept on death: ~ 1 ~", 6835);
					} else {
						c.sendString("@or1@Max items kept on death: ~ 0 ~", 6835);
					}
				} else {
					c.keepItem1();
					if (c.itemKept1 > 0) {
						c.sendFrame34(6822, c.itemKept1, 0, 1);
						amt++;
					}
					c.keepItem2();
					if (c.itemKept2 > 0) {
						c.sendFrame34(6822, c.itemKept2, 1, 1);
						amt++;
					}
					c.keepItem3();
					if (c.itemKept3 > 0) {
						c.sendFrame34(6822, c.itemKept3, 2, 1);
						amt++;
					}
					if (c.protItem) {
						c.keepItem4();
						if (c.itemKept4 > 0) {
							c.sendFrame34(6822, c.itemKept4, 3, 1);
							amt++;
						}
					}
					c.sendString("@or1@Max items kept on death: ~ "+amt+" ~", 6835);
				}
				if (c.isInFightPits() || c.isInFightCaves()) {
					c.sendString("  @or1@You are in a @gre@Safe@or1@ area, no", 6836);
				} else {
					c.sendString("@or1@You are in a @red@Dangerous@or1@ area,", 6836);
				}
				c.resetKeepItem();
				c.showInterface(6733);
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;

		case 59136:
			if (System.currentTimeMillis() - c.TeleDelay > 4100) {
				c.Teleport(2384, 5157, 0);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.TeleDelay = System.currentTimeMillis();
			}
			break;

		case 59137:
			if (System.currentTimeMillis() - c.TeleDelay > 4100) {
				c.Teleport(2409, 5158, 0);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.TeleDelay = System.currentTimeMillis();
			}
			break;

		case 59138:
			if (System.currentTimeMillis() - c.TeleDelay > 4100) {
				c.Teleport(2411, 5137, 0);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.TeleDelay = System.currentTimeMillis();
			}
			break;

		case 59139:
			if (System.currentTimeMillis() - c.TeleDelay > 4100) {
				c.Teleport(2388, 5138, 0);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.TeleDelay = System.currentTimeMillis();
			}
			break;

		case 59135:
			if (System.currentTimeMillis() - c.TeleDelay > 4100) {
				c.Teleport(2398, 5150, 0);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.TeleDelay = System.currentTimeMillis();
			}
			break;

		case 40132:
			c.infinityset = true;
			c.magesbook = false;
			c.masterwand = false;
			c.ancientstaff = false;
			break;

		case 40133:
			c.infinityset = false;
			c.magesbook = true;
			c.masterwand = false;
			c.ancientstaff = false;
			break;

		case 25155:
			c.infinityset = false;
			c.magesbook = false;
			c.ancientstaff = false;
			c.masterwand = true;
			break;

		case 25160:
			c.infinityset = false;
			c.magesbook = false;
			c.ancientstaff = true;
			c.masterwand = false;
			break;

		case 40122:
			if (c.infinityset && !c.ancientstaff && !c.magesbook && !c.masterwand) {
				if (c.magearenapoints < 1000) {
					c.Send("You don't have enough points to purchase the Infinity set.");
				} else {
					c.magearenapoints -= 1000;
					c.addItem(6918, 1);
					c.addItem(6916, 1);
					c.addItem(6924, 1);
					c.addItem(6920, 1);
				}
			}
			if (!c.infinityset && c.ancientstaff && !c.magesbook && !c.masterwand) {
				if (c.magearenapoints < 100) {
					c.Send("You don't have enough points to purchase the Ancient staff.");
				} else {
					c.magearenapoints -= 100;
					c.addItem(4675, 1);
				}
			}
			if (!c.infinityset && !c.ancientstaff && c.magesbook && !c.masterwand) {
				if (c.magearenapoints < 800) {
					c.Send("You don't have enough points to purchase the Mage's set.");
				} else {
					c.magearenapoints -= 800;
					c.addItem(6914, 1);
					c.addItem(6889, 1);
				}
			}
			if (!c.infinityset && !c.ancientstaff && !c.magesbook && c.masterwand) {
				if (c.magearenapoints < 200) {
					c.Send("You don't have enough points to purchase the Ancient spellbook.");
				} else {
					c.magearenapoints -= 200;
					c.vengeanceSpell = 1;
					c.addItem(553, 50);
					c.itemMessage("The rewards guardian teaches you the Ancient spells", "", 553, 250);
				}
			}
			c.sendString("Points: "+c.magearenapoints+"", 10378);
			break;

		case 17111:
			c.Teleport(2399, 5171, 0);
			c.Stuck = false;
			c.teleblock = false;
			c.isNpc = false;
			if (c.spellbook == 0) {
				c.setSidebarInterface(6, 1151);
			} else {
				c.setSidebarInterface(6, 12855);
			}
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
			break;

		case 55095:
			for (int i = 3490; i < 3496; i++) {
				if (c.isUntradable(i)) {
					c.cluescroll = 0;
				}
			}
			c.deleteItem(c.publicDroppendItem, c.GetItemSlot(c.publicDroppendItem), 1);
			c.closeInterface();
			break;

		case 55096:
			c.closeInterface();
			break;

		case 9157:
			if (!c.makeOver) {
				if (c.Dialogue == 2) {
					c.Delay("BarrowsTunnel");
					c.Dialogue = 0;
					c.SendDialogue = false;
					c.RemoveAllWindows();
					break;
				}
				c.Tele("IcePlat");
				c.RemoveAllWindows();
				c.Dialogue = 0;
				c.SendDialogue = false;
				break;
			}
			if (c.makeOver) {
				c.RemoveAllWindows();
				c.showInterface(3559);
				c.apset = true;
				c.Dialogue = 0;
				c.SendDialogue = false;
				c.makeOver = false;
				break;
			}
			break;

		case 9158:
			c.RemoveAllWindows();
			c.Dialogue = 0;
			c.SendDialogue = false;
			c.makeOver = false;
			break;

		case 4140:
			if (System.currentTimeMillis() - c.TeleDelay > 4100) {
				c.Tele("Varrock");
				c.TeleDelay = System.currentTimeMillis();
			}
			break;

		case 4143:
			if (System.currentTimeMillis() - c.TeleDelay > 4100) {
				if (c.playerLevel[6] > 30) {
					c.Tele("Falador");
				} else {
					c.Send("You must have level 30 or higher magic to cast this.");
					return;
				}
				c.TeleDelay = System.currentTimeMillis();
			}
			break;

		case 4146:
			if (System.currentTimeMillis() - c.TeleDelay > 4100) {
				if (c.playerLevel[6] > 37) {
					c.Tele("Lumby");
				} else {
					c.Send("You must have level 37 or higher magic to cast this.");
					return;
				}
				c.TeleDelay = System.currentTimeMillis();
			}
			break;

		case 4150:
			if (System.currentTimeMillis() - c.TeleDelay > 4100) {
				if (c.playerLevel[6] > 45) {
					c.Tele("Camelot");
				} else {
					c.Send("You must have level 45 or higher magic to cast this.");
					return;
				}
				c.TeleDelay = System.currentTimeMillis();
			}
			break;

		case 6004:
			if (System.currentTimeMillis() - c.TeleDelay > 4100) {
				c.Tele("Ardougne");
				c.TeleDelay = System.currentTimeMillis();
			}
			break;

		case 6005:
			if (System.currentTimeMillis() - c.TeleDelay > 4100) {
				c.Tele("Watchtower");
				c.TeleDelay = System.currentTimeMillis();
			}
			break;

		case 29031: 
			if (System.currentTimeMillis() - c.TeleDelay > 4100) {
				c.Tele("Trollheim");
				c.TeleDelay = System.currentTimeMillis();
			}
			break;

		case 72038: 
			if (System.currentTimeMillis() - c.TeleDelay > 4100) {
				c.Tele("Ape");
				c.TeleDelay = System.currentTimeMillis();
			}
			break;

		case 50235: 
			if (System.currentTimeMillis() - c.TeleDelay > 4100) {
				c.Tele("Paddewwa");
				c.TeleDelay = System.currentTimeMillis();
			}
			break;

		case 50245:
			if (System.currentTimeMillis() - c.TeleDelay > 4100) {
				c.Tele("Carrallangar");
				c.TeleDelay = System.currentTimeMillis();
			}
			break;

		case 50253:
			if (System.currentTimeMillis() - c.TeleDelay > 4100) {
				c.Tele("Colony");
				c.TeleDelay = System.currentTimeMillis();
			}
			break;

		case 51005:
			if (System.currentTimeMillis() - c.TeleDelay > 4100) {
				c.Tele("Canifas");
				c.TeleDelay = System.currentTimeMillis();
			}
			break;

		case 51013: 
			if (System.currentTimeMillis() - c.TeleDelay > 4100) {
				c.Tele("Annakarl");
				c.TeleDelay = System.currentTimeMillis();
			}
			break;

		case 51023: 
			if (c.playerLevel[6] < 84) {
				c.Send("You need 84 or higher magic to cast the Ice Plateu teleport.");
			} else {
				c.skillX = c.absX;
				c.skillY = c.absY;
				c.SendChat = 500;
			}
			break;

		case 51031: 
			if (c.vengeanceSpell == 0) {
				c.Send("You need to purchase access to this spell from the Magic Training arena!");
				return;
			}
			if (c.playerLevel[6] < 90) {
				c.Send("You need to have 90 or higher magic to cast Vengeance-other!");
				return;
			}
			if (System.currentTimeMillis() - c.VengeanceDelay < 30000) {
				c.Send("You can only cast Vengeance-other spells every 30 seconds.");
				return;
			}
			if (!c.hasAmount(560, 2) || !c.hasAmount(557, 10) || !c.hasAmount(553, 3)) {
				c.Send("You do not have enough runes to cast this spell.");
				return;
			}
			c.delete(560, 2);
			c.delete(557, 10);
			c.delete(553, 3);
			c.VengeanceDelay = System.currentTimeMillis();
			c.VengeanceOther();
			c.addSkillXP(100, 6);
			c.anim(725);
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
			break;

		case 51039:
			if (c.vengeanceSpell == 0) {
				c.Send("You need to purchase access to this spell from the Magic Training arena!");
				return;
			}
			if (c.playerLevel[6] < 94) {
				c.Send("You need to have 94 or higher magic to cast Vengeance!");
				return;
			}
			if (System.currentTimeMillis() - c.VengeanceDelay < 30000 && !c.canVengeance) {
				c.Send("You can only cast Vengeance spells every 30 seconds.");
				return;
			}
			if (c.canVengeance) {
				c.Send("You already have the vengeance spell casted.");
				return;
			}
			if (!c.hasAmount(560, 2) || !c.hasAmount(557, 10) || !c.hasAmount(553, 3)) {
				c.Send("You do not have enough runes to cast this spell.");
				return;
			}
			c.delete(560, 2);
			c.delete(557, 10);
			c.delete(553, 3);
			c.VengeanceDelay = System.currentTimeMillis();
			c.canVengeance = true;
			c.gfx(644);
			c.addSkillXP(112, 6);
			c.anim(1914);
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
			break;

		case 29063:
			if (c.playerEquipment[c.playerWeapon] == 1377) {
				if (c.specialAmount < 100) {
					c.Send("You do not have enough special energy left.");
				} else {
					c.anim(1670);
					c.gfxLow(246);
					c.specialAmount -= 100;
				}
			}
			c.specialBar();
			break;

		case 29163:
			if (c.playerEquipment[c.playerWeapon] == 4153) {
				if (c.IsAttackingNPC) {
					if (c.specialAmount < 50) {
						c.Send("You do not have enough special energy left.");
					} else {
						c.anim(1667);
						c.gfx(340);
						c.SpecDamgNPC(40);
						c.specialAmount -= 50;
						c.PkingDelay = System.currentTimeMillis();
					}
				}
				if (c.IsAttacking) {
					if (c.specialAmount < 50) {
						c.Send("You do not have enough special energy left.");
					} else {
						c.anim(1667);
						c.gfx(340);
						c.SpecDamg(40);
						c.specialAmount -= 50;
						c.PkingDelay = System.currentTimeMillis();
					}
				}
			} else {
				if (c.specialAmount > 0) {
					if (c.usingSpecial) {
						c.usingSpecial = false;
					} else {
						c.usingSpecial = true;
					}
				} else {
					c.Send("You do not have enough special energy left.");
				}
			}
			Special.special(c);
			break;

		case 29113:
		case 33033:
		case 29138:
		case 48023:
			if (c.specialAmount > 0) {
				if (c.usingSpecial) {
					c.usingSpecial = false;
				} else {
					c.usingSpecial = true;
				}
			} else {
				c.Send("You do not have enough special energy left.");
			}
			Special.special(c);
			break;

		case 14067:
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
			c.RemoveAllWindows();
			break;

		case 9118:
			c.RemoveAllWindows();
			break;

		case 33190:
			if (c.hasAmount(1734, 1) && c.hasItem(1741)) {
				c.addItem(1059, 1);
				c.delete(1734, 1);
				c.delete(1741, 1);
				c.addSkillXP((12 * c.playerLevel[12]), 12);
				c.Send("You make some gloves!");
			} else {
				c.Send("You need thread and 1 piece of leather to make this!");
			}
			break;

		case 33193:
			if (c.playerLevel[12] > 3) {
				if (c.hasAmount(1734, 1) && c.hasItem(1741)) {
					c.addItem(1061, 1);
					c.deleteItem(1734, c.getItemSlot(1734), 1);
					c.deleteItem(1741, c.getItemSlot(1741), 1);
					c.addSkillXP((15 * c.playerLevel[12]), 12);
					c.Send("You make some boots!");
				} else {
					c.Send("You need thread and 1 piece of leather to make this!");
				}
			} else {
				c.Send("You need 4 crafting or higher to make this!");
			}
			break;

		case 33205:
			if (c.playerLevel[12] > 8) {
				if (c.hasAmount(1734, 2) && c.hasItem(1741)) {
					c.addItem(1167, 1);
					c.deleteItem(1734, c.getItemSlot(1734), 2);
					c.deleteItem(1741, c.getItemSlot(1741), 1);
					c.addSkillXP((15 * c.playerLevel[12]), 12);
					c.Send("You make a leather cowl.");
				} else {
					c.Send("You need 2 thread and 1 piece of leather to make this!");
				}
			} else {
				c.Send("You need 9 crafting or higher to make this!");
			}
			break;

		case 33196:
			if (c.playerLevel[12] > 13) {
				if (c.hasAmount(1734, 2) && c.hasItem(1741)) {
					c.addItem(1063, 1);
					c.deleteItem(1734, c.getItemSlot(1734), 2);
					c.deleteItem(1741, c.getItemSlot(1741), 1);
					c.addSkillXP((20 * c.playerLevel[12]), 12);
					c.Send("You make some leather vambraces.");
				} else {
					c.Send("You need 2 thread and 1 piece of leather to make this!");
				}
			} else {
				c.Send("You need 14 crafting or higher to make this!");
			}
			break;

		case 33199:
			if (c.playerLevel[12] > 27) {
				if (c.hasItem(1741) && c.hasAmount(1734, 4)) {
					c.addItem(1095, 1);
					c.deleteItem(1734, c.getItemSlot(1734), 3);
					c.deleteItem(1741, c.getItemSlot(1741), 1);
					c.addSkillXP((25 * c.playerLevel[12]), 12);
					c.Send("You make some leather chaps.");
				} else {
					c.Send("You need 3 thread and 1 piece of soft leather to make this!");
				}
			} else {
				c.Send("You need 28 crafting or higher to make this!");
			}
			break;

		case 33187:
			if (c.playerLevel[12] > 33) {
				if (c.hasAmount(1734, 4) && c.hasItem(1741)) {
					c.addItem(1129, 1);
					c.deleteItem(1734, c.getItemSlot(1734), 4);
					c.deleteItem(1741, c.getItemSlot(1741), 1);
					c.addSkillXP((30 * c.playerLevel[12]), 12);
					c.Send("You make a leather body.");
				} else {
					c.Send("You need 4 thread and 1 pieces of soft leather to make this!");
				}
			} else {
				c.Send("You need 34 crafting or higher to make this!");
			}
			break;

		case 33202:
			if (c.playerLevel[12] > 36) {
				if (c.hasAmount(1734, 5) && c.hasItem(1741)) {
					c.addItem(1169, 1);
					c.deleteItem(1734, c.getItemSlot(1734), 5);
					c.deleteItem(1741, c.getItemSlot(1741), 1);
					c.addSkillXP((35 * c.playerLevel[12]), 12);
					c.Send("You make a coif.");
				} else {
					c.Send("You need 5 thread and 1 piece of soft leather to make this!");
				}
			} else {
				c.Send("You need 37 crafting or higher to make this!");
			}
			break;

		case 153:
			c.Running = 1;
			c.isRunning2 = true;
			break;

		case 152:
			c.Running = 0;
			c.isRunning2 = false;
			break;

		case 168:
			if (System.currentTimeMillis() - c.ButtonDelay > 1000) {
				c.anim(0x357);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;

		case 169:
			if (System.currentTimeMillis() - c.ButtonDelay > 1000) {
				c.anim(0x358);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;

		case 162:
			if (System.currentTimeMillis() - c.ButtonDelay > 1000) {
				c.anim(0x359);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;

		case 164:
			if (System.currentTimeMillis() - c.ButtonDelay > 1000) {
				c.anim(0x35A);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;

		case 165:
			if (System.currentTimeMillis() - c.ButtonDelay > 1000) {
				c.anim(0x35B);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;

		case 161:
			if (System.currentTimeMillis() - c.ButtonDelay > 1000) {
				c.anim(0x35C);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;

		case 170:
			if (System.currentTimeMillis() - c.ButtonDelay > 1000) {
				c.anim(0x35D);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;

		case 171:
			if (System.currentTimeMillis() - c.ButtonDelay > 1000) {
				c.anim(0x35E);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;

		case 163:
			if (System.currentTimeMillis() - c.ButtonDelay > 1000) {
				c.anim(0x35F);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;

		case 167:
			if (System.currentTimeMillis() - c.ButtonDelay > 1000) {
				c.anim(0x360);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;

		case 172:
			if (System.currentTimeMillis() - c.ButtonDelay > 1000) {
				c.anim(0x361);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;

		case 166:
			if (System.currentTimeMillis() - c.ButtonDelay > 1000) {
				c.anim(866);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;

		case 52050:
			if (System.currentTimeMillis() - c.ButtonDelay > 1000) {
				c.anim(0x839);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;

		case 52051:
			if (System.currentTimeMillis() - c.ButtonDelay > 1000) {
				c.anim(0x83A);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;

		case 52052:
			if (System.currentTimeMillis() - c.ButtonDelay > 1000) {
				c.anim(0x83B);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;

		case 52053:
			if (System.currentTimeMillis() - c.ButtonDelay > 1000) {
				c.anim(0x83C);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;

		case 52054:
			if (System.currentTimeMillis() - c.ButtonDelay > 1000) {
				c.anim(0x83D);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;

		case 52055:
			if (System.currentTimeMillis() - c.ButtonDelay > 1000) {
				c.anim(0x83E);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;

		case 52056:
			if (System.currentTimeMillis() - c.ButtonDelay > 1000) {
				c.anim(0x83F);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;

		case 52057:
			if (System.currentTimeMillis() - c.ButtonDelay > 1000) {
				c.anim(0x840);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;

		case 52058:
			if (System.currentTimeMillis() - c.ButtonDelay > 1000) {
				c.anim(0x841);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;

		case 43092:
			if (System.currentTimeMillis() - c.ButtonDelay > 1000) {
				c.anim(0x558);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;

		case 2155:
			if (System.currentTimeMillis() - c.ButtonDelay > 1000) {
				c.anim(0x46B);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;

		case 25103:
			if (System.currentTimeMillis() - c.ButtonDelay > 1000) {
				c.anim(0x46A);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;

		case 25106:
			if (System.currentTimeMillis() - c.ButtonDelay > 1000) {
				c.anim(0x469);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;

		case 2154:
			if (System.currentTimeMillis() - c.ButtonDelay > 1000) {
				c.anim(0x468);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;

		case 52071:
			if (System.currentTimeMillis() - c.ButtonDelay > 1000) {
				c.anim(0x84F);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;

		case 52072:
			if (System.currentTimeMillis() - c.ButtonDelay > 1000) {
				c.anim(0x850);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
				c.ButtonDelay = System.currentTimeMillis();
			}
			break;

		case 9125:
		case 22228:
		case 48010:
		case 21200:
		case 1080:
		case 6168:
		case 6236:
		case 17102:
		case 8234:
			c.FightType = 1;
			c.SkillID = 0;
			break;

		case 9126:
		case 22229:
		case 21201:
		case 1078:
		case 6169:
		case 33019:
		case 18078:
		case 8235:
			c.FightType = 4;
			c.SkillID = 1;
			break;

		case 9127:
		case 48009:
		case 33018:
		case 6234:
		case 18077:
		case 18080:
		case 18079:
		case 17100:
			c.FightType = 3;
			c.SkillID = 3;
			break;

		case 9128:
		case 22230:
		case 21203:
		case 21202:
		case 1079:
		case 6171:
		case 6170:
		case 33020:
		case 6235:
		case 17101:
		case 8237:
		case 8236:
			c.FightType = 2;
			c.SkillID = 2;
			break;

		case 9154:
			if (c.isInFightCaves()) {
				c.NPC("Sorry...", "You can't logout of the Fight caves!", 2617);
				return;
			}
			if (c.inCombat) {
				c.Send("You must wait 10 seconds out of being in combat to logout.");
				return;
			}
			c.Save();
			c.logout();
			break;

		case 21011:
			c.takeAsNote = false;
			break;

		case 21010:
			c.takeAsNote = true;
			break;

		case 13092:
			if (c.tradeWith > 0) {
				if (PlayerManager.players[c.tradeWith].tradeStatus == 2) {
					c.tradeStatus = 3;
					c.sendFrame126("Waiting for other player...", 3431);
				} else if (PlayerManager.players[c.tradeWith].tradeStatus == 3) {
					c.tradeStatus = 3;
				}
			}
			break;

		case 13218:
			if (c.tradeWith > 0) {
				if (PlayerManager.players[c.tradeWith].tradeStatus == 3) {
					c.tradeStatus = 4;
					c.sendFrame126("Waiting for other player...", 3535);
				} else if (PlayerManager.players[c.tradeWith].tradeStatus == 4) {
					c.tradeStatus = 4;
				}
			}
			break;

		case 9167:
			if (c.Dialogue == 1592) {
				if (c.playerLevel[18] > 98) {
					if (c.hasAmount(995, 99000)) {
						c.delete(995, 99000);
						c.addItem(2720, 1);
						c.addItem(2721, 1);
					} else {
						c.Send("You don't have enough coins to buy the Slayer skillcape.");
					}
				} else {
					c.Send("You need 99 Slayer to purchase the Slayer skillcape.");
				}
				c.RemoveAllWindows();
				c.Dialogue = 0;
				c.SendDialogue = false;
				break;
			}
			if (c.Barbpoints >= 50) {
				c.addItem(7602, 1);
				c.Barbpoints -= 50;
				c.itemMessage("You purchase the Figher torso for 50 points", "", 7602, 250);
			} else {
				c.NPC("Sorry "+c.playerName+",", "You need 50 points for the Fighter torso.", 3050);
			}
			break;

		case 9168:
			if (c.Dialogue == 1592) {
				if (c.hasAmount(995, 500)) {
					c.delete(995, 500);
					c.addItem(4164, 1);
				} else {
					c.Send("You don't have enough coins to buy the Facemask.");
				}
				c.RemoveAllWindows();
				c.Dialogue = 0;
				c.SendDialogue = false;
				break;
			}
			if (c.Barbpoints >= 35) {
				c.addItem(7601, 1);
				c.Barbpoints -= 35;
				c.itemMessage("You purchase the Rune defender for 35 points", "", 7601, 250);
			} else {
				c.NPC("Sorry "+c.playerName+",", "You need 35 points for the Rune defender.", 3050);
			}
			break;

		case 9169:
			if (c.Dialogue == 1592) {
				c.RemoveAllWindows();
				c.Dialogue = 0;
				c.SendDialogue = false;
				break;
			}
			if (c.Barbpoints >= 20) {
				c.addItem(7462, 1);
				c.Barbpoints -= 20;
				c.itemMessage("You purchase the Barrows gloves for 20 points", "", 7462, 250);
			} else {
				c.NPC("Sorry "+c.playerName+",", "You need 20 points for the Barrows gloves.", 3050);
			}
			break;

		case 9190:
			c.Tele("Karamja");
			break;

		case 9191:
			c.Tele("Varrock2");
			break;

		case 9192:
			c.Tele("Glory1");
			break;

		case 9193:
			if (c.q1 == 14) {
				c.Tele("Altar");
			} else {
				c.RemoveAllWindows();
				c.Dialogue = 0;
				c.SendDialogue = false;
				c.Send("You can't use that Teleport until you've finished the Desert Treasure quest!");
			}
			break;

		case 9194:
			c.Glory = 0;
			c.RemoveAllWindows();
			break;

		case 58025:
			if (c.EnteringPin == 1) {
				c.Entered1Pin = 0;
			}
			if (c.EnteringPin == 2) {
				c.Entered2Pin = 0;
			}
			if (c.EnteringPin == 3) {
				c.Entered3Pin = 0;
			}
			if (c.EnteringPin == 4) {
				c.Entered4Pin = 0;
			}
			c.CheckPin();
			break;

		case 58026:
			if (c.EnteringPin == 1) {
				c.Entered1Pin = 1;
			}
			if (c.EnteringPin == 2) {
				c.Entered2Pin = 1;
			}
			if (c.EnteringPin == 3) {
				c.Entered3Pin = 1;
			}
			if (c.EnteringPin == 4) {
				c.Entered4Pin = 1;
			}
			c.CheckPin();
			break;

		case 58027:
			if (c.EnteringPin == 1) {
				c.Entered1Pin = 2;
			}
			if (c.EnteringPin == 2) {
				c.Entered2Pin = 2;
			}
			if (c.EnteringPin == 3) {
				c.Entered3Pin = 2;
			}
			if (c.EnteringPin == 4) {
				c.Entered4Pin = 2;
			}
			c.CheckPin();
			break;

		case 58028:
			if (c.EnteringPin == 1) {
				c.Entered1Pin = 3;
			}
			if (c.EnteringPin == 2) {
				c.Entered2Pin = 3;
			}
			if (c.EnteringPin == 3) {
				c.Entered3Pin = 3;
			}
			if (c.EnteringPin == 4) {
				c.Entered4Pin = 3;
			}
			c.CheckPin();
			break;

		case 58029:
			if (c.EnteringPin == 1) {
				c.Entered1Pin = 4;
			}
			if (c.EnteringPin == 2) {
				c.Entered2Pin = 4;
			}
			if (c.EnteringPin == 3) {
				c.Entered3Pin = 4;
			}
			if (c.EnteringPin == 4) {
				c.Entered4Pin = 4;
			}
			c.CheckPin();
			break;

		case 58030:
			if (c.EnteringPin == 1) {
				c.Entered1Pin = 5;
			}
			if (c.EnteringPin == 2) {
				c.Entered2Pin = 5;
			}
			if (c.EnteringPin == 3) {
				c.Entered3Pin = 5;
			}
			if (c.EnteringPin == 4) {
				c.Entered4Pin = 5;
			}
			c.CheckPin();
			break;

		case 58031:
			if (c.EnteringPin == 1) {
				c.Entered1Pin = 6;
			}
			if (c.EnteringPin == 2) {
				c.Entered2Pin = 6;
			}
			if (c.EnteringPin == 3) {
				c.Entered3Pin = 6;
			}
			if (c.EnteringPin == 4) {
				c.Entered4Pin = 6;
			}
			c.CheckPin();
			break;

		case 58032:
			if (c.EnteringPin == 1) {
				c.Entered1Pin = 7;
			}
			if (c.EnteringPin == 2) {
				c.Entered2Pin = 7;
			}
			if (c.EnteringPin == 3) {
				c.Entered3Pin = 7;
			}
			if (c.EnteringPin == 4) {
				c.Entered4Pin = 7;
			}
			c.CheckPin();
			break;

		case 58033:
			if (c.EnteringPin == 1) {
				c.Entered1Pin = 8;
			}
			if (c.EnteringPin == 2) {
				c.Entered2Pin = 8;
			}
			if (c.EnteringPin == 3) {
				c.Entered3Pin = 8;
			}
			if (c.EnteringPin == 4) {
				c.Entered4Pin = 8;
			}
			c.CheckPin();
			break;

		case 58034:
			if (c.EnteringPin == 1) {
				c.Entered1Pin = 9;
			}
			if (c.EnteringPin == 2) {
				c.Entered2Pin = 9;
			}
			if (c.EnteringPin == 3) {
				c.Entered3Pin = 9;
			}
			if (c.EnteringPin == 4) {
				c.Entered4Pin = 9;
			}
			c.CheckPin();
			break;

		case 58074:
			c.closeInterface();
			break;

		default:
			break;
		}
	}
}