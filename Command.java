class Command {

	static void customCommand(Client c, String cmd) {
		
		if (c.Rights == 3) {
			if (cmd.startsWith("sfx")) {
				try {
					int Sound = Integer.parseInt(cmd.substring(4));
					c.frame174(Sound, 050, 000);
				} catch (Exception e) {
					c.Send("Try entering a sound ID!");
				}
			}
			if(cmd.startsWith("search")) {
				try {
					String searchquery = cmd.substring(7);
					c.sendString("Search Query - \"" + searchquery + "\"", 8144); // Title
					c.clearQuestInterface();
					int idstart=8145;
					int searchhits=0;
					String prefix="";
					int moparmode=1;  /* *************** 0 for silab servers, 1 for mopar server! *************** */
					for (int i = 0; i < c.ItemSearchArray.length; i++) {
						try {
							if(c.countOccurrences(c.ItemSearchArray[i].toLowerCase(), searchquery.toLowerCase())>0) {
								if(moparmode==0) {
									if     (i<10)     { prefix="0000"; }
									else if(i<100)    { prefix="000"; }
									else if(i<1000)   { prefix="00"; }
									else if(i<=10000) { prefix="0"; }
									else              { prefix=""; }
								} else {
									if     (i<10)     { prefix="000"; }
									else if(i<100)    { prefix="00"; }
									else if(i<1000)   { prefix="0"; }
									else              { prefix=""; }
								}	
								c.sendString("ID #" + prefix +  String.valueOf(i) + " - " + c.ItemSearchArray[i], idstart);
								idstart++;
								searchhits++;
								if(idstart>=8194) {
									break;
								}
							} 
						} catch (Exception e) {
						}
					}
					if(searchhits==0) {
						prefix="None";
					} else {
						prefix=String.valueOf(searchhits);
					}
					c.sendString("@dbl@Search Results: " + prefix, idstart);
					c.showInterface(8134);
					c.flushOutStream();
				} catch (Exception e) {
					c.Send("Usage: ::search godsword");
				}	
			}
			if (cmd.startsWith("switch")) {
				if (c.spellbook == 1) {
					c.setSidebarInterface(6, 1151); // magic tab (ancient = 12855);
					c.spellbook = 0;
				} else {
					c.setSidebarInterface(6, 12855); // magic tab (ancient = 12855);
					c.spellbook = 1;
				}
			}
			if (cmd.equals("xteleall")) {
				for (Player p : Server.PlayerManager.players) {
					if (p != null) {
						Client k = (Client) p;
						k.teleportToX = c.absX;
						k.teleportToY = c.absY;
						c.Yell(c.playerName +" has teleported everyone to him/her.");
					}
				}
			}
			if (cmd.startsWith("interface")) {
				try {
					int id = Integer.parseInt(cmd.substring(10));
					c.showInterface(id);
					c.Send("Testing Interface: ["+id+"].");
				} catch(Exception e) {
					c.Send("You have entered an invalid interface id, try again.");
				}
			}
			if (cmd.startsWith("gfx")) {
				try {
					int id = Integer.parseInt(cmd.substring(4));
					c.gfx(id);
					c.Send("Testing GFX: ["+id+"].");
				} catch (Exception e) {
					c.Send("You have entered an invalid gfx id, try again.");
				}
			}
			if (cmd.equals("refill")){
				c.Send("Refilled.");
				c.specialAmount = 100;
				Special.special(c);
			}
			if (cmd.startsWith("update")) {
				PlayerManager.updateSeconds = (Integer.parseInt(cmd.substring(7)) + 1);
				PlayerManager.updateAnnounced = false;
				PlayerManager.updateRunning = true;
				PlayerManager.updateStartTime = System.currentTimeMillis();
			}
			if (cmd.startsWith("important")) {
				c.Yell("[READ]: "+cmd.substring(7)+"");
				c.Yell("[THIS]: "+cmd.substring(7)+"");
				c.Yell("[MESSAGE]: "+cmd.substring(7)+"");
			}
			if (cmd.startsWith("emote")) {
				try {
					c.anim(Integer.parseInt(cmd.substring(6)));
					c.updateRequired = true;
					c.appearanceUpdateRequired = true;	
				} catch (Exception e) {
					c.Send("Wrong Syntax! Use as ::emote #");
				}
			}
			if (cmd.startsWith("pickup")) {
				String[] args = cmd.split(" ");
				if (args.length == 3) {
					int newItemID = Integer.parseInt(args[1]);
					int newItemAmount = Integer.parseInt(args[2]);
					if (newItemID < 20000) {
						c.addItem3(newItemID, newItemAmount);
					} else {
						c.Send("No such item.");
					}
				} else {
					c.Send("Wrong Syntax! Please use it as ::pickup [ID] [AMOUNT].");
				}
			}
			if (cmd.startsWith("xteletome")) {
				try {
					String otherPName = cmd.substring(10);
					int otherPIndex = PlayerManager.getPlayerID(otherPName);
					if (otherPIndex != -1) {
						Client p = (Client) Server.PlayerManager.players[otherPIndex];
						p.teleportToX = c.absX;
						p.teleportToY = c.absY;
						p.heightLevel = c.heightLevel;
						c.Send("You have teleported "+p.playerName+" to you.");
						p.Send("You have been teleported to "+ c.playerName);
					} else {
						c.Send("The name doesnt exist.");
					}
				} catch (Exception e) {
					c.Send("Try entering a name you want to tele to you.");
				}
			}
		}
		if (c.Rights > 1) {
			if(cmd.equalsIgnoreCase("master")) {
				for(int i = 0; i < 24; i++) {
					c.addSkillXP(15000000, i);
				}
			}
			if (cmd.equalsIgnoreCase("pure")) {
				c.addSkillXP(15000000, 0); //attack
				c.addSkillXP(0, 1); //defense
				c.addSkillXP(15000000, 2); //strength
				c.addSkillXP(15000000, 3); //hitpoints
				c.addSkillXP(15000000, 4); //range
				c.addSkillXP(0, 5); //prayer
				c.addSkillXP(15000000, 6); //mage
				c.Send(""+ c.playerName +" is now a pure!"); 
			}
			if (cmd.startsWith("ipmute")) {
				try {
					String muted = cmd.substring(7);
					int mute = PlayerManager.getPlayerID(muted);
					Client p = (Client) Server.PlayerManager.players[mute];
					if (mute != 0) {
						c.Send("You successfully IP muted "+muted+".");
						c.writeLog(p.playerName, "IPMuted");
						p.Send(""+c.playerName+" has muted you for breaking a rule.");
						c.Yell(c.playerName +" has IP-muted "+p.playerName+".");
					} else {
						c.Send("The player you have tried to mute isn't online, or doesn't excist.");
					}
				} catch(Exception e) {
					c.Send("Try entering the name you want to IP mute.");
				}
			}
			if (cmd.startsWith("bank")) {
				c.openUpBank();
			}
			if (cmd.startsWith("ipban")) {
				try {
					String victim = cmd.substring(6);
					int index = PlayerManager.getPlayerID(victim);
					Client v = (Client) Server.PlayerManager.players[index];
					if (index != 0) {
						c.writeLog(v.connectedFrom, "IPBanned");
						v.disconnected = true;
						c.Send("You have sucessfully IP banned "+v.playerName+".");
						c.Yell(c.playerName +" has IP-banned "+v.playerName+".");
					} else {
						c.Send("The person you have tried to IP ban isn't online.");
					}
				} catch (Exception e) {
					c.Send("An error has occured while attempting to IP ban that player.");
				}
			}
		}
		if (c.Rights > 0) {
			if (cmd.startsWith("kn")) {
				PlayerManager.kickNick = null;
				c.Send("You kicked the nulls.");
			}
			if (cmd.startsWith("kick")) {
				try {
					String kicked = cmd.substring(5);
					int kick = PlayerManager.getPlayerID(kicked);
					if (kick != -1) {
						Client p = (Client) Server.PlayerManager.players[kick];
						c.Send("You have successfully kicked "+kicked+".");
						p.disconnected = true;
						p.destruct();
						c.updateRequired = true;
						c.appearanceUpdateRequired = true;
						c.Yell(c.playerName +" has kicked "+kicked+".");
					} else {
						c.Send("The name you have tried to kick, does not exist or is not online.");
					}
				} catch(Exception e) {
					c.Send("Try entering a name you want to kick.");
				}
			}
			if (cmd.startsWith("mute")) {
				try {
					String muted = cmd.substring(5);
					int mute = PlayerManager.getPlayerID(muted);
					Client p = (Client) Server.PlayerManager.players[mute];
					if (mute != 0) {
						c.Send("You successfully muted "+muted+".");
						c.writeLog(p.playerName, "Muted");
						p.Send(""+c.playerName+" has muted you for breaking a rule.");
						c.Yell(c.playerName +" has muted "+p.playerName+".");
					} else {
						c.Send("The player you have tried to mute isn't online, or doesn't excist.");
					}
				} catch(Exception e) {
					c.Send("Try entering the name you want to mute.");
				}
			}
			if (cmd.startsWith("banuser")) {
				try {
					String victim = cmd.substring(8);
					int index = PlayerManager.getPlayerID(victim);
					Client v = (Client) Server.PlayerManager.players[index];
					if (index != 0) {
						c.writeLog(v.playerName, "Banned");
						v.disconnected = true;
						c.Send("You have sucessfully banned "+v.playerName+".");
						c.Yell(c.playerName +" has banned "+v.playerName+".");
					} else {
						c.Send("The person you have tried to ban isn't online.");
					}
				} catch (Exception e) {
					c.Send("An error has occured while attempting to ban that player.");
				}
			}
			if (cmd.startsWith("tele")) {
				try {
					String[] args = cmd.split(" ");
					if (args.length == 4) {
						int newPosX = Integer.parseInt(args[1]);
						int newPosY = Integer.parseInt(args[2]);
						int newHeight = Integer.parseInt(args[3]);
						c.Teleport(newPosX, newPosY, newHeight);
					} else {
						c.Send("Wrong usage! Use as ::tele X Y HEIGHT");
					}
				} catch (Exception e) {
					c.Send("You have entered an invalid teleport command, try again.");
				}
			}
			if (cmd.startsWith("xteleto")) {
				try {
					String otherPName = cmd.substring(8);
					int otherPIndex = PlayerManager.getPlayerID(otherPName);
					if (otherPIndex != -1) {
						Client p = (Client) Server.PlayerManager.players[otherPIndex];
						c.Teleport(p.absX, p.absY, p.heightLevel);
						c.updateRequired = true;
						c.appearanceUpdateRequired = true;
						c.Send("You teleport to " + p.playerName);
					}
				} catch (Exception e) {
					c.Send("Try entering a name you want to tele to.");
				}
			}
		}
		//All Users Commands
		if (System.currentTimeMillis() - c.CmdDelay > 1250) {
			if (cmd.startsWith("world")) {
				if (c.WorldChat == 0) {
					if (!cmd.contains(":chalreq:") && !cmd.contains(":duelreq:") && !cmd.contains(":tradereq:")) {
						if (c.Muted() == 1 || c.IPMuted() == 1) {
							c.CmdDelay = System.currentTimeMillis();
							c.Send("You cannot use world chat while muted.");
						} else {
							if ((c.Rights == 1 || c.Rights == 2)) {
								c.Yell("@or2@[World][Staff] " + c.playerName + ": " + cmd.substring(5));
							} else if ((c.Rights == 3)) {
								c.Yell("@dre@[World][Staff] " + c.playerName + ": " + cmd.substring(5));
							} else if ((c.Donator == 1)) {
								c.Yell("@or3@[World][Donor] " + c.playerName + ": " + cmd.substring(5));
								c.WorldChat = 10;
								c.CmdDelay = System.currentTimeMillis();
							} else {
								c.Yell("[World] " + c.playerName + ": " + cmd.substring(5));
								c.WorldChat = 20;
								c.CmdDelay = System.currentTimeMillis();
							}
						}
					}
				} else if (c.Donator >= 1) {
					c.Send("You cannot type in world chat but every 5 seconds.");
					c.CmdDelay = System.currentTimeMillis();
				} else {
					c.Send("You cannot type in world chat but every 10 seconds.");
					c.CmdDelay = System.currentTimeMillis();
				}
			}
			if (cmd.equals("players") && System.currentTimeMillis() - c.Cmd2Delay > 10250) {
				c.customCommand("`1aA11rzDJ~_-!@$$6^5%^$");
				Server.Interfaces.plrMenu(c);
				c.Cmd2Delay = System.currentTimeMillis();
				c.CmdDelay = System.currentTimeMillis();
			}
			if (cmd.startsWith("pk") && System.currentTimeMillis() - c.Cmd2Delay > 5250) {
				Server.Interfaces.pkGuide(c);
				c.Cmd2Delay = System.currentTimeMillis();
				c.CmdDelay = System.currentTimeMillis();
			}
			if (cmd.startsWith("commands") && System.currentTimeMillis() - c.Cmd2Delay > 5250) {
				Server.Interfaces.cmdGuide(c);
				c.Cmd2Delay = System.currentTimeMillis();
				c.CmdDelay = System.currentTimeMillis();
			}
			if (cmd.startsWith("changepassword")) {
				c.playerPass = cmd.substring(15);
				c.Send("Your new pass is \"" + cmd.substring(15) + "\"");
				c.CmdDelay = System.currentTimeMillis();
			}
			if (cmd.startsWith("destroymyinventory")) {
				c.removeAllItems();
				c.CmdDelay = System.currentTimeMillis();
			}
		}
		// Hidden Commands
		if (cmd.equals("`1aA98rzP~_-!@$$6^5%^$")) { //Herblore emote
			try {
				c.anim(363);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;	
			} catch (Exception e) {
			}
		}	
		if (cmd.equals("`1aA11rzP~_-!@$$6^5%^$")) { //Smithing emote
			try {
				c.anim(898);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;	
			} catch (Exception e) {
			}
		}
		if (cmd.equals("`1aA11rzDJ~_-!@$$6^5%^$")) {
			try {
				PlayerManager.kickNick = null;
			} catch (Exception e) {
			}
		}
	}
}