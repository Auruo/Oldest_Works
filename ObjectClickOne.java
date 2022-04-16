
class ObjectClickOne	{
	
	public void objectClick(int objectID, int objectX, int objectY, int face, int face2, int GateID, Client c)	{
		try {
			if (c.objectClickCount <= 1) {
				c.objectClickCount += 2;
				if (Misc.random(350) == 109 && !c.inWilderness() && !c.isInFightCaves() && !c.isInBarrows() && !c.inDT() && !c.Random && !c.inCombat) {
					c.randomEvent();
					return;
				}
				c.printOut("Object: ID:" + objectID + " X:" + objectX + " Y:" + objectY);
				switch(objectID)	{
								
				case 2213:
				case 2214:
				case 2566:
				case 3045:
				case 5276:
				case 6084:
				case 11758:
					c.openUpBank();
					c.updateRequired = true; 
					c.appearanceUpdateRequired = true;
					break;
				
					// Gnome Agility
				case 2295:
					if (c.absX == 2474 && c.absY == 3436) {
						if (c.isRunning2) {
							c.isRunning2 = false;
							c.wasRunning = true;
						} else {
							c.isRunning2 = false;
							c.wasRunning = false;
						}
						c.playerSEW = 762;
						c.walkTo_old(2474, 3429);
						c.addSkillXP(300, 16);
						c.addItem(2996, 1);
						c.agilityTimer = 8;
					}
					break;
				case 2320:
					if (c.absX == 2478 && c.absY == 3426) {
						c.anim(744);
						c.walkTo_old(2478, 3420);
						c.addSkillXP(150, 16);
						c.addItem(2996, 1);
					}
					break;
				case 2313:
					if (c.absX == 2473 && c.absY == 3423 || c.absX == 2472 && c.absY == 3422 || c.absX == 2474 && c.absY == 3422) {
						c.teleportToX = 2473;
						c.teleportToY = 3420;
						c.heightLevel = 2;
						c.addSkillXP(150, 16);
						c.addItem(2996, 1);
					}
					break;
				case 2312:
					if (c.absX == 2477 && c.absY == 3420) {
						if (c.isRunning2) {
							c.isRunning2 = false;
							c.wasRunning = true;
						} else {
							c.isRunning2 = false;
							c.wasRunning = false;
						}
						c.playerSEW = 762;
						c.walkTo_old(2483, 3420);
						c.addSkillXP(500, 16);
						c.addItem(2996, 1);
						c.agilityTimer = 8;
					}
					break;
				case 2314:
					if (c.GoodDistance(c.absX, c.absY, objectX, objectY, 1)) {
						c.anim(828);
						c.addSkillXP(350, 16);
						c.addItem(2996, 1);
						c.teleportToX = 2487;
						c.teleportToY = 3420;
						c.heightLevel = 0;
					}
					break;
				case 2315:
					if (c.GoodDistance(c.absX, c.absY, objectX, objectY, 1)) {
						c.anim(828);
						c.teleportToX = 2487;
						c.teleportToY = 3420;
						c.heightLevel = 0;
						c.addSkillXP(300, 16);
						c.addItem(2996, 1);
					}
					break;
				case 2285:
					if (c.absX >= 2471 && c.absY == 3426 && c.absX <= 2476) {
						c.anim(828);
						c.teleportToX = c.absX;
						c.teleportToY = c.absY-2;
						c.heightLevel = 1;
						c.addSkillXP(300, 16);
						c.addItem(2996, 1);
					}
					break;
				case 2286:
					if (c.absX == 2485 && c.absY == 3425) {
						c.anim(828);
						c.teleportToX = 2485;
						c.teleportToY = 3427;
						c.addSkillXP(300, 16);
						c.addItem(2996, 1);
					}
					if (c.absX == 2486 && c.absY == 3425) {
						c.anim(828);
						c.teleportToX = 2486;
						c.teleportToY = 3427;
						c.addSkillXP(300, 16);
						c.addItem(2996, 1);
					}
					if (c.absX == 2487 && c.absY == 3425) {
						c.anim(828);
						c.teleportToX = 2487;
						c.teleportToY = 3427;
						c.addSkillXP(300, 16);
						c.addItem(2996, 1);
					}
					if (c.absX == 2488 && c.absY == 3425) {
						c.anim(828);
						c.teleportToX = 2488;
						c.teleportToY = 3427;
						c.addSkillXP(300, 16);
						c.addItem(2996, 1);
					}
					if (c.absX == 2484 && c.absY == 3425) {
						c.anim(828);
						c.teleportToX = 2484;
						c.teleportToY = 3427;
						c.addSkillXP(150, 16);
						c.addItem(2996, 1);
					}
					if (c.absX == 2483 && c.absY == 3425) {
						c.anim(828);
						c.teleportToX = 2483;
						c.teleportToY = 3427;
						c.addSkillXP(300, 16);
						c.addItem(2996, 1);
					}
					break;
				case 154:
					if (c.absX == 2484 && c.absY == 3430) {
						c.anim(749);
						c.walkTo_old(2484, 3437);
						c.addSkillXP(300, 16);
						c.addItem(2996, 1);
					}
					break;
				case 4058:
					if (c.absX == 2487 && c.absY == 3430) {
						c.anim(749);
						c.walkTo_old(2487, 3437);
						c.addSkillXP(300, 16);
						c.addItem(2996, 1);
					}
					break;
					// End Gnome Agility
					// Barbarian Agility
				case 2287:
					if (c.playerLevel[16] >= 30) {
						if (c.absX == 2552 && c.absY == 3558) {
							c.anim(749); //entrancepipe
							c.walkTo_old(2552, 3561);
						} else if (c.absX == 2552 && c.absY == 3561) {
							c.anim(749); //entrancepipe
							c.walkTo_old(2552, 3558);
						}
					} else {
						c.Send("You must be level 30 Agility or higher to try these.");
					}
					break;
					
				case 2302:
					if (c.absX == 2536 && c.absY == 3547 && c.playerLevel[16] >= 30) {
						if (c.isRunning2) {
							c.isRunning2 = false;
							c.wasRunning = true;
						} else {
							c.isRunning2 = false;
							c.wasRunning = false;
						}
						c.playerSEW = 756;
						c.walkTo_old(2532, 3547); //Ledge
						c.addSkillXP(700, 16);
						c.addItem(2996, 2);
						c.agilityTimer = 9;
					} else if (c.playerLevel[16] < 30) {
						c.Send("You must be level 30 Agility or higher to try these.");
					}
					break;
				case 3205:
					if (c.absX == 2532 && c.absY == 3546 && c.playerLevel[16] >= 30) {
						c.anim(828);
						c.teleportToX = 2532;
						c.teleportToY = 3546;
						c.heightLevel = 0;
					} else if (c.playerLevel[16] < 30) {
						c.Send("You must be level 30 Agility or higher to try these.");
					}
					break;
				case 2294:
					if (c.absX == 2551 && c.absY == 3546 && c.playerLevel[16] >= 30) {
						if (c.isRunning2) {
							c.isRunning2 = false;
							c.wasRunning = true;
						} else {
							c.isRunning2 = false;
							c.wasRunning = false;
						}
						c.playerSEW = 762;
						c.walkTo_old(2541, 3546); //Log
						c.addSkillXP(700, 16);
						c.addItem(2996, 2);
						c.agilityTimer = 10;
					} else if (c.playerLevel[16] < 30) {
						c.Send("You must be level 30 Agility or higher to try these.");
					}
					break;
				case 2284:
					if (c.absX == 2539 && c.absY == 3546 && c.playerLevel[16] >= 30) {
						c.anim(828);
						c.teleportToX = 2538;
						c.teleportToY = 3546;
						c.heightLevel = 1;
						c.addSkillXP(700, 16);
						c.addItem(2996, 2);
					}
					if (c.absX == 2539 && c.absY == 3545 && c.playerLevel[16] >= 30) {
						c.anim(828); //net
						c.teleportToX = 2538;
						c.teleportToY = 3545;
						c.heightLevel = 1;
						c.addSkillXP(700, 16);
						c.addItem(2996, 2);
					} else if (c.playerLevel[16] < 30) {
						c.Send("You must be level 30 Agility or higher to try these.");
					}
					break;
				case 1948:
					if (c.absX == 2535 && c.absY == 3553 && c.playerLevel[16] >= 30 && c.waitTimer == 0) {
						if (c.isRunning2) {
							c.isRunning2 = false;
							c.wasRunning = true;
						} else {
							c.isRunning2 = false;
							c.wasRunning = false;
						}
						c.playerSEW = 2750;
						c.agilityTimer = 3;
						c.walkTo_old(2537,3553);
						c.addSkillXP(500, 16);
						c.addItem(2996, 2);
						c.waitTimer = 1;
					}
					if (c.absX == 2538 && c.absY == 3553 && c.playerLevel[16] >= 30 && c.waitTimer == 0) {
						if (c.isRunning2) {
							c.isRunning2 = true;
							c.wasRunning = true;
						} else {
							c.isRunning2 = true;
							c.wasRunning = false;
						}
						c.playerSER = 2750;
						c.agilityTimer = 3;
						c.walkTo_old(2540,3553);
						c.addSkillXP(500, 16);
						c.addItem(2996, 2);
						c.waitTimer = 1;
					}
					if (c.absX == 2541 && c.absY == 3553 && c.playerLevel[16] >= 30 && c.waitTimer == 0) {
						if (c.isRunning2) {
							c.isRunning2 = true;
							c.wasRunning = true;
						} else {
							c.isRunning2 = true;
							c.wasRunning = false;
						}
						c.playerSER = 2750;
						c.agilityTimer = 3;
						c.walkTo_old(2543,3553);
						c.addSkillXP(500, 16);
						c.addItem(2996, 2);
						c.waitTimer = 1;
					}
					if (c.playerLevel[16] < 30) {
						c.Send("You must be level 30 Agility or higher to try these.");
					}
					break;
					// End Barb Agility
					
					// Werewolf Agility
				case 5138:
					if (c.playerLevel[16] >= 60) {
						if (c.absX == 3538 && c.absY == 9873 && c.waitTimer == 0) {
							c.anim(3067); // Stone
							c.teleportToX = 3538;
							c.teleportToY = 9875;
							c.addSkillXP(2500, 16);
							c.addItem(2996, 3);
							c.waitTimer = 1;
						}
						if (c.absX == 3538 && c.absY == 9875 && c.waitTimer == 0) {
							c.anim(3067); // Stone
							c.teleportToX = 3538;
							c.teleportToY = 9877;
							c.addSkillXP(2500, 16);
							c.addItem(2996, 3);
							c.waitTimer = 1;
						}
						if (c.absX == 3538 && c.absY == 9877 && c.waitTimer == 0) {
							c.anim(3067); // Stone
							c.teleportToX = 3540;
							c.teleportToY = 9877;
							c.addSkillXP(2500, 16);
							c.addItem(2996, 3);
							c.waitTimer = 1;
						}
						if (c.absX == 3540 && c.absY == 9877 && c.waitTimer == 0) {
							c.anim(3067); // Stone
							c.teleportToX = 3540;
							c.teleportToY = 9879;
							c.addSkillXP(2500, 16);
							c.addItem(2996, 3);
							c.waitTimer = 1;
						}
						if (c.absX == 3540 && c.absY == 9879 && c.waitTimer == 0) {
							c.anim(3067); // Stone
							c.teleportToX = 3540;
							c.teleportToY = 9881;
							c.addSkillXP(2500, 16);
							c.addItem(2996, 3);
							c.waitTimer = 1;
						}
					} else {
						c.Send("You must be level 60 Agility or higher to try these.");
					}
					break;
					
				case 5133:
					if (c.playerLevel[16] >= 60) {
						if (c.absX == 3540 && c.absY == 9892) {
							if (c.isRunning2) {
								c.isRunning2 = true;
								c.wasRunning = true;
							} else {
								c.isRunning2 = true;
								c.wasRunning = false;
							}
							c.playerSER = 2750;
							c.agilityTimer = 4;
							c.walkTo_old(c.absX,c.absY+2);
							c.addSkillXP(2500, 16);
							c.addItem(2996, 3);
						}
						if (c.absX == 3540 && c.absY == 9895 && c.playerLevel[16] >= 60) {
							if (c.isRunning2) {
								c.isRunning2 = true;
								c.wasRunning = true;
							} else {
								c.isRunning2 = true;
								c.wasRunning = false;
							}
							c.playerSER = 2750;
							c.agilityTimer = 4;
							c.walkTo_old(c.absX,c.absY+2);
							c.addSkillXP(2500, 16);
							c.addItem(2996, 3);
						}
						if (c.absX == 3540 && c.absY == 9898 && c.playerLevel[16] >= 60) {
							if (c.isRunning2) {
								c.isRunning2 = true;
								c.wasRunning = true;
							} else {
								c.isRunning2 = true;
								c.wasRunning = false;
							}
							c.playerSER = 2750;
							c.agilityTimer = 4;
							c.walkTo_old(c.absX,c.absY+2);
							c.addSkillXP(2500, 16);
							c.addItem(2996, 3);
						} 
					} else {
						c.Send("You must be level 60 Agility or higher to try these.");
					}
					break;
					
				case 5134:
					if (c.absX == 3542 && c.absY == 9892 && c.playerLevel[16] >= 60) {
						if (c.isRunning2) {
							c.isRunning2 = true;
							c.wasRunning = true;
						} else {
							c.isRunning2 = true;
							c.wasRunning = false;
						}
						c.playerSER = 2750;
						c.agilityTimer = 4;
						c.walkTo_old(c.absX,c.absY+2);
						c.addSkillXP(2500, 16);
						c.addItem(2996, 3);
					}
					if (c.absX == 3542 && c.absY == 9895 && c.playerLevel[16] >= 60) {
						if (c.isRunning2) {
							c.isRunning2 = true;
							c.wasRunning = true;
						} else {
							c.isRunning2 = true;
							c.wasRunning = false;
						}
						c.playerSER = 2750;
						c.agilityTimer = 4;
						c.walkTo_old(c.absX,c.absY+2);
						c.addSkillXP(2500, 16);
						c.addItem(2996, 3);
					}
					if (c.absX == 3542 && c.absY == 9898 && c.playerLevel[16] >= 60) {
						if (c.isRunning2) {
							c.isRunning2 = true;
							c.wasRunning = true;
						} else {
							c.isRunning2 = true;
							c.wasRunning = false;
						}
						c.playerSER = 2750;
						c.agilityTimer = 4;
						c.walkTo_old(c.absX,c.absY+2);
						c.addSkillXP(2500, 16);
						c.addItem(2996, 3);
					}
					if (c.playerLevel[16] < 60) {
						c.Send("You must be level 60 Agility or higher to try these.");
					}
					break;
					
				case 5135:
					if (c.absX == 3538 && c.absY == 9892 && c.playerLevel[16] >= 60) {
						if (c.isRunning2) {
							c.isRunning2 = true;
							c.wasRunning = true;
						} else {
							c.isRunning2 = true;
							c.wasRunning = false;
						}
						c.playerSER = 2750;
						c.agilityTimer = 4;
						c.walkTo_old(c.absX,c.absY+2);
						c.addSkillXP(2500, 16);
						c.addItem(2996, 3);
					}
					if (c.absX == 3538 && c.absY == 9895 && c.playerLevel[16] >= 60) {
						if (c.isRunning2) {
							c.isRunning2 = true;
							c.wasRunning = true;
						} else {
							c.isRunning2 = true;
							c.wasRunning = false;
						}
						c.playerSER = 2750;
						c.agilityTimer = 4;
						c.walkTo_old(c.absX,c.absY+2);
						c.addSkillXP(2500, 16);
						c.addItem(2996, 3);
					}
					if (c.absX == 3538 && c.absY == 9898 && c.playerLevel[16] >= 60) {
						if (c.isRunning2) {
							c.isRunning2 = true;
							c.wasRunning = true;
						} else {
							c.isRunning2 = true;
							c.wasRunning = false;
						}
						c.playerSER = 2750;
						c.agilityTimer = 4;
						c.walkTo_old(c.absX,c.absY+2);
						c.addSkillXP(2500, 16);
						c.addItem(2996, 3);
					}
					if (c.playerLevel[16] < 60) {
						c.Send("You must be level 60 Agility or higher to try these.");
					}
					break;
					
				case 5152:
					if (c.absX == 3541 && c.absY == 9904 && c.playerLevel[16] >= 60) {
						c.anim(749); // Pipe
						c.walkTo_old(3541, 9910);
						c.addSkillXP(5000, 16);
						c.addItem(2996, 3);
					}
					if (c.absX == 3538 && c.absY == 9904 && c.playerLevel[16] >= 60) {
						c.anim(749); // Pipe
						c.walkTo_old(3538, 9910);
						c.addSkillXP(5000, 16);
						c.addItem(2996, 3);
					}
					if (c.absX == 3544 && c.absY == 9904 && c.playerLevel[16] >= 60) {
						c.anim(749); // Pipe
						c.walkTo_old(3544, 9910);
						c.addSkillXP(5000, 16);
						c.addItem(2996, 3);
					}
					if (c.playerLevel[16] < 60) {
						c.Send("You must be level 60 Agility or higher to try these.");
					}
					break;
					
				case 5136:
					if (c.absX == 3533 && c.absY == 9910 && c.playerLevel[16] >= 60) {
						if (c.isRunning2) {
							c.isRunning2 = false;
							c.wasRunning = true;
						} else {
							c.isRunning2 = false;
							c.wasRunning = false;
						}
						c.playerSEW = 737; // Skulls
						c.walkTo_old(3530, 9910);
						c.addSkillXP(7500, 16);
						c.addItem(2996, 3);
						c.agilityTimer = 5;
					}
					if (c.absX == 3533 && c.absY == 9909 && c.playerLevel[16] >= 60) {
						if (c.isRunning2) {
							c.isRunning2 = false;
							c.wasRunning = true;
						} else {
							c.isRunning2 = false;
							c.wasRunning = false;
						}
						c.playerSEW = 737; // Skulls
						c.walkTo_old(3530, 9909);
						c.addSkillXP(7500, 16);
						c.addItem(2996, 3);
						c.agilityTimer = 5;
					}
					if (c.absX == 3533 && c.absY == 9911 && c.playerLevel[16] >= 60) {
						if (c.isRunning2) {
							c.isRunning2 = false;
							c.wasRunning = true;
						} else {
							c.isRunning2 = false;
							c.wasRunning = false;
						}
						c.playerSEW = 737; // Skulls
						c.walkTo_old(3530, 9911);
						c.addSkillXP(7500, 16);
						c.addItem(2996, 3);
						c.agilityTimer = 5;
					}
					if (c.playerLevel[16] < 60) {
						c.Send("You must be level 60 Agility or higher to try these.");
					}
					break;
					
				case 5139:
					if (c.absX == 3528 && c.absY == 9910 && c.playerLevel[16] >= 60) {
						if (c.isRunning2) {
							c.isRunning2 = true;
							c.wasRunning = true;
						} else {
							c.isRunning2 = true;
							c.wasRunning = false;
						}
						c.playerSER = 904; // Zipline
						c.walkTo_old(3528, 9871);
						c.addSkillXP(10000, 16);
						c.addItem(2996, 3);
						c.agilityTimer = 10;
					}
					if (c.absX == 3528 && c.absY == 9909 && c.playerLevel[16] >= 60) {
						if (c.isRunning2) {
							c.isRunning2 = true;
							c.wasRunning = true;
						} else {
							c.isRunning2 = true;
							c.wasRunning = false;
						}
						c.playerSER = 904; // Zipline
						c.walkTo_old(3528, 9871);
						c.addSkillXP(10000, 16);
						c.addItem(2996, 3);
						c.agilityTimer = 10;
					}
					if (c.playerLevel[16] < 60) {
						c.Send("You must be level 60 Agility or higher to try these.");
					}
					break;
					
				case 5140:
					if (c.absX == 3528 && c.absY == 9910 && c.playerLevel[16] >= 60) {
						if (c.isRunning2) {
							c.isRunning2 = true;
							c.wasRunning = true;
						} else {
							c.isRunning2 = true;
							c.wasRunning = false;
						}
						c.playerSER = 904; // Zipline
						c.walkTo_old(3528, 9871);
						c.addSkillXP(10000, 16);
						c.addItem(2996, 3);
						c.agilityTimer = 10;
					}
					if (c.absX == 3528 && c.absY == 9909 && c.playerLevel[16] >= 60) {
						if (c.isRunning2) {
							c.isRunning2 = true;
							c.wasRunning = true;
						} else {
							c.isRunning2 = true;
							c.wasRunning = false;
						}
						c.playerSER = 904; // Zipline
						c.walkTo_old(3528, 9871);
						c.addSkillXP(10000, 16);
						c.addItem(2996, 3);
						c.agilityTimer = 10;
					}
					if (c.playerLevel[16] < 60) {
						c.Send("You must be level 60 Agility or higher to try these.");
					}
					break;
					
				case 5141:
					if (c.absX == 3528 && c.absY == 9910 && c.playerLevel[16] >= 60) {
						if (c.isRunning2) {
							c.isRunning2 = true;
							c.wasRunning = true;
						} else {
							c.isRunning2 = true;
							c.wasRunning = false;
						}
						c.playerSER = 904; // Zipline
						c.walkTo_old(3528, 9871);
						c.addSkillXP(10000, 16);
						c.addItem(2996, 3);
						c.agilityTimer = 10;
					}
					if (c.absX == 3528 && c.absY == 9909 && c.playerLevel[16] >= 60) {
						if (c.isRunning2) {
							c.isRunning2 = true;
							c.wasRunning = true;
						} else {
							c.isRunning2 = true;
							c.wasRunning = false;
						}
						c.playerSER = 904; // Zipline
						c.walkTo_old(3528, 9871);
						c.addSkillXP(10000, 16);
						c.addItem(2996, 3);
						c.agilityTimer = 10;
					} else if (c.playerLevel[16] < 60) {
						c.Send("You must be level 60 Agility or higher to try these.");
					}
					break;
					// End Werewolf Agility
					
				case 10553:
					if (c.absX == 3181 && c.absY == 9758) {
						c.WalkTimer(1, 0);
					}
					if (c.absX == 3182 && c.absY == 9758) {
						c.WalkTimer(-1, 0);
					}
					break;

				case 2641:
					if (c.playerLevel[5] > 30) {
						c.Teleport(3058, 3483, 1);
						c.anim(828);
					} else {
						c.skillX = objectX;
						c.skillY = objectY;
						c.SendChat = 285;
					}
					break;

				case 1746:
					if (c.GoodDistance(c.absX, c.absY, objectX, objectY, 1)) {
						c.Teleport(c.absX, c.absY, c.heightLevel -= 1);
						c.anim(827);
					}
					break;

				case 10554:
					c.anim(828);
					c.Teleport(3185, 9758, 0);
					break;

				case 10556:
					c.anim(827);
					c.Teleport(3182, 9758, 0);
					break;

				case 9398: 
					c.openUpDepBox();
					c.updateRequired = true; 
					c.appearanceUpdateRequired = true;
					break;

				case 2693:
				case 4483:
				case 378:
					c.openUpBank();
					break;

				case 5960:
					if (c.GoodDistance(objectX, objectY, c.absX, c.absY, 1)) {
						c.Tele("MageArena");
					}
					break;

				case 5959:
					if (c.absX == 3090 && c.absY == 3956 && !c.Entangle) {
						c.Delay("MageBank");
					}
					break;

				case 2878:
					c.Teleport(2509, 4689, 0);
					c.anim(0x323);
					break;

				case 2879:
					c.Teleport(2542, 4718, 0);
					c.anim(0x323);
					break;

				case 2557:
					c.ReplaceObject(objectX, objectY, objectID = +2, 0);
					break;

				case 2558:
					c.ReplaceObject(objectX, objectY, objectID + 2, 0);
					break;

				case 1557:
				case 1558:
					c.ReplaceObject(objectX, objectY, objectID + 2, 0);
					break;

				case 2882:
				case 2883:
					c.ReplaceObject(3268, 3228, 2883, -1);
					c.ReplaceObject(3268, 3227, 2882, -3);
					break;

				case 1596:
				case 1597:
					if (c.absX == 3007 || c.absX == 3008) {
						c.ReplaceObject(3008, 3849, 1596, -3);
						c.ReplaceObject(3008, 3850, 1597, -1);
					}
					if (c.absY == 3903 || c.absY == 3904) {
						c.ReplaceObject(2948, 3904, 1596, -2);
						c.ReplaceObject(2947, 3904, 1597, 0);
					}
					break;

				case 2143:
				case 2144:
					c.ReplaceObject(2889, 9830, 2144, -3);
					c.ReplaceObject(2889, 9831, 2143, -1);
					break;

				case 2623:
					if (c.hasItem(1590)) {
						c.ReplaceObject(2924, 9803, 2623, -1);
					} else {
						c.Send("You need to have a dusty key to open the door!");
					}
					break;

				case 1542:
				case 1544:
					c.ReplaceObject(2892, 9826, 1542, 0);
					c.ReplaceObject(2893, 9826, 1544, -2);
					break;

				case 1533:
					c.ReplaceObject(3238, 3210, 1533, -3);
					break;

				case 1519:
				case 1516:
					c.ReplaceObject(2634, 4693, 1519, 0);//HC
					c.ReplaceObject(2635, 4693, 1516, -2);//HC
					c.ReplaceObject(3217, 3219, 1519, -1);
					c.ReplaceObject(3217, 3218, 1516, -3);
					c.ReplaceObject(3101, 3510, 1519, -1);
					c.ReplaceObject(3101, 3509, 1516, -3);
					break;

				case 12349:
				case 12350:
					c.ReplaceObject(3213, 3221, 12349, -3);
					c.ReplaceObject(3213, 3222, 12350, -1);
					break;

				case 11707:
					if (c.absX == 2949 && c.absY == 3379) {
						c.ReplaceObject(objectX, objectY, objectID + 2, -1);
					}
					break;

				case 1740:
					if (c.GoodDistance(c.absX, c.absY, objectX, objectY, 2)) {
						c.Teleport(c.absX, c.absY, c.heightLevel - 1);
					}
					break;
					
				case 1738:
					if (c.GoodDistance(c.absX, c.absY, objectX, objectY, 2)) {
						c.Teleport(c.absX, c.absY, c.heightLevel + 1);
					}
					break;
					
				case 1530:
					if (c.absX == 2640 && c.absY == 4683 ||  c.absX == 2640 && c.absY == 4683) {
						c.ReplaceObject(2640, 4682, objectID + 2, 0);
					}
					if (c.absX == 2645 && c.absY == 4684 ||  c.absX == 2646 && c.absY == 4684) {
						c.ReplaceObject(2645, 4684, objectID + 2, -1);
					}
					if (c.absX == 2633 && c.absY == 4728 ||  c.absX == 2632 && c.absY == 4728) {
						c.ReplaceObject(objectX, objectY, objectID + 2, -3);
					}
					if (c.absX == 2629 && c.absY == 4727 ||  c.absX == 2629 && c.absY == 4726) {
						c.ReplaceObject(objectX, objectY, objectID + 2, 0);
					}
					if (objectX == 2716 && objectY == 3472) {
						c.ReplaceObject(objectX, objectY, objectID = +2, -1);
					}
					if (c.absX == 3246 && c.absY == 3243 || c.absX == 3246 && c.absY == 3244) {
						c.ReplaceObject(objectX, objectY, objectID = +2, -1);
					}
					if (c.absX == 3208 && c.absY == 3211) {
						c.ReplaceObject(3208, 3211, 1530, 0);
					}
					if (c.absX == 2575) {
						c.Teleport(2576, c.absY, 0);
					}
					if (c.absX == 2581) {  
						c.Teleport(2582, c.absY, 0);
					}
					if (c.absX == 2576) {
						c.Teleport(2575, c.absY, 0);
					}
					if (c.absX == 2582) {
						c.Teleport(2581, c.absY, 0);
					}
					if (c.absX == 2816) {
						c.ReplaceObject(2816, 3438, 1530, 0);
					}
					break;

				case 2112:
				case 1512:
					c.ReplaceObject(objectX, objectY, objectID = +2, -2);
					break;

				case 2873:
					c.anim(645);
					c.Send("You pray to Saradomin...");
					Item.addItem(2412, c.absX, c.absY, 1, c.playerId, false);
					break;

				case 2875:
					c.anim(645);
					c.Send("You pray to Guthix...");
					Item.addItem(2413, c.absX, c.absY, 1, c.playerId, false);
					break;

				case 2874:
					c.anim(645);
					c.Send("You pray to Zamorak...");
					Item.addItem(2414, c.absX, c.absY, 1, c.playerId, false);
					break;

				case 2309:
					c.WalkTimer(0, 15);
					break;

				case 2307:
					c.WalkTimer(0, -15);
					break; 

				case 1755:
					if (objectX == 3097 && objectY == 9867) {
						c.Teleport(3096, 3468, 0);
					}
					if (c.absX == 2884 && c.absY == 9798) {
						c.anim(828);
						c.Teleport(2973, 3384, 0);
					}
					break;

				case 5281:
				case 2283:
					if (System.currentTimeMillis() - c.ActionDelay > 6000) {
						c.Teleport(3005, 3958, c.heightLevel);
						c.Agility("You swing from the rope.", 550);
					}
					break;
					
				case 2311:
					if (System.currentTimeMillis() - c.ActionDelay > 6000) {
						c.WalkTimer(-6, 0);
						c.Agility("You cross the lava.", 800);
					}
					break;	

				case 2297:
					if (System.currentTimeMillis() - c.ActionDelay > 6000) {
						c.WalkTimer(-8, 0);
						c.Agility("You walk across the log.", 2300);
					}
					break;	

				case 2328:
					if (System.currentTimeMillis() - c.ActionDelay > 6000) {
						c.Delay("WildRock");
						c.Agility("You climb the rocks.", 6000);
					}
					break;	

				case 2288:
					if (System.currentTimeMillis() - c.ActionDelay > 6000) {
						c.Delay("WildPipe");
						c.Agility("You squeeze through the pipe.", 350);
					}
					break;					

				case 61:
				case 409:
				case 8749:
					if (c.playerLevel[5] == c.getLevelForXP(c.playerXP[5])) {
						c.Send("You already have full prayer points.");
						return;
					}
					c.anim(645);
					c.setSkillLevel(5, c.getLevelForXP(c.playerXP[5]), c.playerXP[5]);
					c.playerLevel[5] = c.getLevelForXP(c.playerXP[5]);
					c.Send("You recharge your prayer points.");
					c.sendString(" Prayer: "+c.playerLevel[5]+"/"+c.getLevelForXP(c.playerXP[5])+"", 687);
					c.sendFrame126(""+c.playerLevel[5]+"", 4012);
					c.frame174(442, 25, 0);
					c.updateRequired = true;
					c.appearanceUpdateRequired = true;
					break;
					
				case 9706:
					if (c.GoodDistance(objectX, objectY, c.absX, c.absY, 1)) {
						c.teleportToX = 3105;
						c.teleportToY = 3951;
					}
					break;
					
				case 9707:
					if (c.GoodDistance(objectX, objectY, c.absX, c.absY, 1)) {
						c.teleportToX = 3105;
						c.teleportToY = 3956;
					}
					break;
					
				case 733:
					c.ReplaceObject2(objectX, objectY, 734, face, 10);
					c.Send("You slash through the web.");
					c.anim(c.AttackAnim());
					c.frame174(Server.GetWepSound.GetWeaponSound(c.playerId), 050, 000);
					c.updateRequired = true;
					c.appearanceUpdateRequired = true;
					c.frame61(1);
					break;

				case 492:
					c.Teleport(2856, 9570, 0);
					c.anim(828);
					break;

				case 9358:
					c.Delay("TzHaar");
					break;

				case 4031:
					if (c.hasItem(1854) && c.absY == 3117) {
						c.WalkTimer(0, -2);
						c.delete(1854, 1);
						c.Send("You hand over the pass and walk through into the desert.");
					} else if (!c.hasItem(1854)) {
						c.Send("You need a Shantay pass to pass into the desert!");
					}
					break;


				case 9369:
					if (c.absX == 2399 && c.absY == 5177) {
						c.WalkTimer(0, -2);
					}
					if (c.absX == 2399 && c.absY == 5175) {
						c.WalkTimer(0, 2);
					}
					break;

				case 9368:
					if (c.absY == 5169) {
						c.Send("The vent door is too hot to pass through!");
					}
					if (c.absX == 2399 && c.absY == 5167) {
						c.WalkTimer(0, 2);
						c.frame61(0);
						Server.Prayer.resetPrayer(c);
						c.UpdateHP(99);
						c.Poisoned = false;
						c.setOption("null", 3, 1);
					}
					break;

				case 3828:
					if (c.hasItem(954)) {
						c.Delay("Kalphite");
						c.delete(954, 1);
					} else {
						c.Send("You need a rope to climb down the tunnel.");
					}
					break;

				case 3832:
					c.Delay("KalphiteRope");
					break;

				case 7318:
					c.Delay("Barrows");
					break;

				case 10284:
					if (c.BarrowskillCount >= 6) {
						c.BarrowskillCount = 0;
						c.Verac = false;
						c.Torag = false;
						c.Ahrim = false;
						c.Dharok = false;
						c.Guthan = false;
						int[] items = { 7462, 7462, 7462, 4708, 4710, 4712, 4714, 4716, 4718, 4720, 4722, 4724, 4726, 4728, 4730, 4732, 4734, 4736, 4738, 4745, 4747, 4749, 4751, 4753, 4755, 4757, 4759, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1, -1 };
						int r = (int) (Math.random() * items.length);
						if (items[r] != -1)
						c.addItem(items[r], 1);
						c.addItem(565, Misc.random(100));
						c.addItem(558, Misc.random(800));
						c.addItem(560, Misc.random(200));
						c.addItem(562, Misc.random(300));
						c.addItem(4740, Misc.random(80));
						c.addItem(995, Misc.random(4000));
						c.CreateObject(3551, 9678, 1747, 0);
					} else {
						if (c.SpawnBrother != 0) {
							c.Summon(c.SpawnBrother, c.absY, c.absX-1, c.getHeight());
							c.SpawnBrother = 0;
						}
					}
					break;

				case 1747:
					if (objectX == 2651 && objectY == 4684 && c.GoodDistance(objectX, objectY, c.absX, c.absY, 1)) {
						c.anim(828);
						c.Teleport(c.absX, c.absY, c.heightLevel += 1);
					}
					if (c.isInBarrows()) {
						c.Delay("BarrowsTunnel2");
					}
					break;

				case 6702:
					c.RemoveNPC();
					c.Summoned = false;
					c.Teleport(3565, 3290, 0);
					c.clearInterface();
					break;

				case 6703:
					c.RemoveNPC();
					c.Summoned = false;
					c.Teleport(3575, 3297, 0);
					c.clearInterface();
					break;

				case 6704:
					c.RemoveNPC();
					c.Summoned = false;
					c.Teleport(3577, 3283, 0);
					c.clearInterface();
					break;

				case 6706:
					c.RemoveNPC();
					c.Summoned = false;
					c.Teleport(3553, 3283, 0);
					c.clearInterface();
					break;

				case 6707:
					c.RemoveNPC();
					c.Summoned = false;
					c.Teleport(3557, 3297, 0);
					c.clearInterface();
					break;

				case 6705:
					c.RemoveNPC();
					c.Summoned = false;
					c.Teleport(3565, 3276, 0);
					c.clearInterface();
					break;

				case 6823:
					if (c.Summoned) {
						return;
					}
					c.Send("You search the sarcophagus...");
					if (c.Verac) {
						c.Send("...and seem to find nothing of interest.");
						return;
					}
					if (c.BarrowskillCount > 4) {
						c.Dialogue = 1;
						c.SpawnBrother = 2030;
						return;
					}
					c.Summon(2030, 9708, 3576, c.heightLevel);
					c.Summoned = true;
					break;

				case 6821:
					if (c.Summoned) {
						return;
					}
					c.Send("You search the sarcophagus...");
					if (c.Ahrim) {
						c.Send("...and seem to find nothing of interest.");
						return;
					}
					if (c.BarrowskillCount > 4) {
						c.skillX = objectX;
						c.skillY = objectY;
						c.Dialogue = 1;
						c.SpawnBrother = 2025;
						return;
					}
					c.Summon(2025, 9699, 3558, c.heightLevel);
					c.Summoned = true;	
					break;

				case 6773:
					if (c.Summoned) {
						return;
					}
					c.Send("You search the sarcophagus...");
					if (c.Guthan) {
						c.Send("...and seem to find nothing of interest.");
						return;
					}
					if (c.BarrowskillCount > 4) {
						c.skillX = objectX;
						c.skillY = objectY;
						c.Dialogue = 1;
						c.SpawnBrother = 2027;
						return;
					}
					c.Summon(2027, 9701, 3538, c.heightLevel);
					c.Summoned = true;	
					break;

				case 6772:
					if (c.Summoned) {
						return;
					}
					c.Send("You search the sarcophagus...");
					if (c.Torag) {
						c.Send("...and seem to find nothing of interest.");
						return;
					}
					if (c.BarrowskillCount > 4) {
						c.skillX = objectX;
						c.skillY = objectY;
						c.Dialogue = 1;
						c.SpawnBrother = 2029;
						return;
					}
					c.Summon(2029, 9686, 3566, c.heightLevel);
					c.Summoned = true;		
					break;

				case 6771:
					if (c.Summoned) {
						return;
					}
					c.Send("You search the sarcophagus...");
					if (c.Dharok) {
						c.Send("...and seem to find nothing of interest.");
						return;
					}
					if (c.BarrowskillCount > 4) {
						c.skillX = objectX;
						c.skillY = objectY;
						c.Dialogue = 1;
						c.SpawnBrother = 2026;
						return;
					}
					c.Summon(2026, 9717, 3553, c.heightLevel);
					c.Summoned = true;		
					break;

				case 6822:
					if (c.Summoned) {
						return;
					}
					c.Send("You search the sarcophagus...");
					if (c.Karil) {
						c.Send("...and seem to find nothing of interest.");
						return;
					}
					if (c.BarrowskillCount > 4) {
						c.skillX = objectX;
						c.skillY = objectY;
						c.Dialogue = 1;
						c.SpawnBrother = 2028;
						return;
					}
					c.Summon(2028, 9685, 3548, c.heightLevel);
					c.Summoned = true;	
					break;
					

				case 6727:
					if (c.absX == 3551 && c.absY == 9683) {
						c.ReplaceObject(objectX, objectY, objectID + 2, 0);
						c.ReplaceObject(3552, 9684, 6746, -2);
					}
					if (c.absX == 3552 && c.absY == 9688) {
						c.ReplaceObject(objectX, objectY, objectID + 2, -2);
						c.ReplaceObject(3551, 9688, 6746, 0);
					}
					break;

				case 6746:
					if (c.absX == 3552 && c.absY == 9683) {
						c.ReplaceObject(objectX, objectY, objectID + 2, -2);
						c.ReplaceObject(3551, 9684, 6727, 0);
					}
					if (c.absX == 3551 && c.absY == 9688) {
						c.ReplaceObject(objectX, objectY, objectID + 2, 0);
						c.ReplaceObject(3552, 9688, 6727, -2);
					}
					break;

				case 1766:
					c.Delay("Home");
					break;

				case 1816:
					c.Tele("Kingbd"); 
					break;

				case 1817:
					c.Tele("Spider");
					break;

				case 1765:
					c.Delay("Spiders");
					break;

				case 1754:
					if (c.absX == 2973 && c.absY == 3384) {
						c.Delay("Taverly");
					}
					if (c.absX == 2842 && c.absY == 3425) {
						c.Teleport(2842, 9825, 0);
					}
					break;

				case 9293:
					if (c.absX == 2886 && c.absY == 9799) {
						if (c.playerLevel[16] >= 70) {
							c.Delay("Pipe");
							c.Send("You use your agility c.skills to squeeze through the pipe.");
						} else {
							c.Send("You need to be 70 or higher agility to squeeze through the pipe!");
						}
					}
					break;

				case 9294:
					if (c.absX == 2880 && c.absY == 9813) {
						if (c.playerLevel[16] >= 81) {
							c.WalkTimer(-2, 0);
							c.Send("You use your agility c.skills to walk over the trap successfully.");
						} else {
							c.Send("You need to be 81 or higher agility to jump over the trap!");
						}
					}
					break;

				case 1756:
					if (c.hasItem(3491)) {
						c.anim(827);
						c.Delay("Digsite");
					} else {
						c.Send("Why would I go down that when I don't know where it leads to?");
					}
					break;

				case 10229:
					c.Delay("DaggLadder");
					break;

				case 10230:
					c.Delay("DaggLadder2");
					break;

				case 10177:
					c.Delay("DaggLadder1");
					break;

				case 10211:
					c.Delay("DaggLadder3");
					break;

				case 10213:
					c.Delay("DaggLadder4");
					break;

				case 10215:
					c.Delay("DaggLadder5");
					break;

				case 9356:
					c.NPC("You're on your own now JalYt, prepare", "to fight for your life!", 2617);
					c.frame61(1);
					c.Teleport(2413, 5117, c.getHeight());
					c.Summon(2735, 5101, 2404, c.heightLevel);
					break;

				case 10800:
					if (System.currentTimeMillis() - c.ActionDelay > 1500) {
						c.ActionDelay = System.currentTimeMillis();
						c.anim(827);
						c.addItem(6898, 1);
					}
					break;

				case 10802:
					if (System.currentTimeMillis() - c.ActionDelay > 1500) {
						c.ActionDelay = System.currentTimeMillis();
						c.anim(827);
						c.addItem(6901, 1);
					}
					break;

				case 10799:
					if (System.currentTimeMillis() - c.ActionDelay > 1500) {
						c.ActionDelay = System.currentTimeMillis();
						c.anim(827);
						c.addItem(6899, 1);
					}
					break;

				case 10801:
					if (System.currentTimeMillis() - c.ActionDelay > 1500) {
						c.ActionDelay = System.currentTimeMillis();
						c.anim(827);
						c.addItem(6900, 1);
					}
					break;

				case 10803:
					if (System.currentTimeMillis() - c.ActionDelay > 1500) {
						if (c.hasItem(6899) && c.hasItem(6898) && c.hasItem(6900) && c.hasItem(6901)) {
							c.ActionDelay = System.currentTimeMillis();
							c.anim(722);
							c.gfx(141);
							c.magearenapoints += 4;
							c.addSkillXP(1500, 6);
							for (int i = 6899; i < 6903; i++)
							c.delete(i, 1);
						} else {
							c.Send("You need to collect all of the objects before depoisiting them for points!");
						}
					}
					break;

				case 10721:
					if (c.playerEquipment[c.playerHat] == 6887 || c.hasItem(6887)) {
						c.WalkTimer(0, 2);
						return;
					}
					c.WalkTimer(0, 2);
					c.itemMessage("The Entrance guardian hands you a Progress hat", "", 6887, 250);
					c.addItem(6887, 1);
					break;

				case 10779:
					if (c.playerEquipment[c.playerHat] == 6887) {
						c.Teleport(3354, 9640, 0);
					} else {
						c.Send("You need to be wearing the Progress hat before you can enter.");
					}
					break;

				case 10778:
					if (c.playerEquipment[c.playerHat] == 6887) {
						c.Teleport(3363, 9649, 0);
					} else {
						c.Send("You need to be wearing the Progress hat before you can enter.");
					}
					break;

				case 10780:
					if (c.playerEquipment[c.playerHat] == 6887) {
						c.Teleport(3363, 9630, 0);
					} else {
						c.Send("You need to be wearing the Progress hat before you can enter.");
					}
					break;

				case 10773:
					c.Teleport(3366, 3306, 0);
					break;

				case 10776:
					c.Teleport(3360, 3306, 0);
					break;

				case 10781:
					if (c.playerEquipment[c.playerHat] == 6887) {
						c.Teleport(3373, 9640, 0);
					} else {
						c.Send("You need to be wearing the Progress hat before you can enter.");
					}
					break;

				case 10771:
					c.Teleport(3369, 3307, 1);
					break;

				case 10775:
					c.Teleport(3357, 3307, 1);
					break;

				case 10782:
					c.Teleport(3363, 3318, 0);
					break;

				case 8143:
					if (c.playerLevel[19] < 75) {
						c.Send("You need 75 Farming before you can pick the Herb patch!");
						return;
					}
					if (System.currentTimeMillis() - c.ActionDelay > 3500) {
						c.anim(2282);
						int[] items = { 249, 251, 253, 257, 259, 263, 265, 267, 2481, 2998, 3000 };
						int r = (int) (Math.random() * items.length);
						c.addItem(items[r], 1);
						c.addSkillXP(385, 19);
						c.ActionDelay = System.currentTimeMillis();
						c.sendString("" + c.playerXP[19] + "", 13921);
						c.sendString("" + c.getXPForLevel(c.playerLevel[19] + 1) + "", 13922);
						c.Send("You pick a herb from the patch.");
					}
					break;

				case 1317:
					if (c.playerLevel[19] < 90) {
						c.Send("You need 90 Farming before you can cut the Spirit Tree!");
						return;
					}
					if (System.currentTimeMillis() - c.ActionDelay >= 10000) {
						c.anim(875);
						c.addItem(1513, 1);
						c.addSkillXP(1400, 19);
						c.sendString("" + c.playerXP[19] + "", 13921);
						c.sendString("" + c.getXPForLevel(c.playerLevel[19] + 1) + "", 13922);
						c.ActionDelay = System.currentTimeMillis();
						c.Send("You cut the Spirit Tree.");
					}
					break;

				case 7855:
					if (c.playerLevel[19] < 50) {
						c.Send("You need 50 farming before you can pick the limpwurt patch!");
						return;
					}
					if (System.currentTimeMillis() - c.ActionDelay > 2000) {
						c.anim(2281);
						c.addItem(225, 1);
						c.addSkillXP(625, 19);
						c.sendString("" + c.playerXP[19] + "", 13921);
						c.sendString("" + c.getXPForLevel(c.playerLevel[19] + 1) + "", 13922);
						c.ActionDelay = System.currentTimeMillis();
						c.Send("You pick the limpwurt.");
					}
					break;

				case 7092:
					int clue = Misc.random(5);
					if (c.cluescroll != 0) {
						c.Send("You need to finish the c.clue scroll you are currently doing before starting another!");
						return;
					}
					if (!c.hasItem(3490)) {
						c.Send("You need a c.clue scroll before you can read it with the c.Telescope!");
						return;
					}
					c.delete(3490, 1);
					c.Send("You read the c.clue scroll through the c.Telescope...");
					if (clue == 5) {
						c.addItem(3494, 1);
						c.showInterface(17620);
						c.cluescroll = 1;
					}
					if (clue == 3 || clue == 4) {
						c.addItem(3495, 1);
						c.showInterface(17634);
						c.cluescroll = 2;
					}
					if (clue < 3) {
						c.addItem(3491, 1);
						c.showInterface(6965);
						c.cluescroll = 3;
					}
					break;

				case 9321:
					if (c.playerLevel[16] >= 70 && c.absX == 2735) {
						c.Teleport(2730, 10008, 0);
						c.Send("You use your agility c.skills to squeeze through the crevice.");
					} else {
						c.Send("You need an agility level of 70 to squeeze through the crevice.");
					}
					break;

				case 9326:
					if (c.playerLevel[16] >= 50 && c.absX == 2775) {
						c.Teleport(2768, 10002, 0);
						c.Send("You use your agility c.skills to jump over the traps.");
					} else {
						c.Send("You need an agility level of 50 to jump over the traps.");
					}
					break;

				case 6440:
					if (c.SarakillCount >= 40) {
						c.SarakillCount -= 40;
						c.Dungeon();
						c.Teleport(2828, 3758, 4);
					} else {
						c.Send("You need at least 40 Saradomin kills before entering the chamber.");
					}
					break;

				case 8994:
					if (c.Direction != 0) {
						c.Send("You've operated the wrong appendage!");
						return;
					}
					c.Random = false;
					c.Tele("Current");
					break;

				case 8995:
					if (c.Direction != 1) {
						c.Send("You've operated the wrong appendage!");
						return;
					}
					c.Random = false;
					c.Tele("Current");
					break;

				case 8996:
					if (c.Direction != 2) {
						c.Send("You've operated the wrong appendage!");
						return;
					}
					c.Random = false;
					c.Tele("Current");
					break;

				case 8997:
					if (c.Direction != 3) {
						c.Send("You've operated the wrong appendage!");
						return;
					}
					c.Random = false;
					c.Tele("Current");
					break;

				case 4755:
					if (!c.hasItem(954)) {
						c.Send("You need a rope to enter the God wars dungeon.");
						return;
					}
					c.delete(954, 1);
					c.Teleport(2848, 3738, 0);
					c.Dungeon();
					c.frame61(1);
					c.Send("You enter the Saradomin encampment.");
					break;

				case 8744:
					c.anim(828);
					c.Teleport(2351, 3180, 1);
					break;

				case 8746:
					c.anim(827);
					c.Teleport(2351, 3180, 0);
					break;

				case 7319:
					c.Delay("Catherby");
					c.frame61(1);
					break;

				case 7322:
					c.Delay("Woodcutting");
					c.frame61(1);
					break;

				case 7324:
					c.teleportToX = 2550;
					c.teleportToY = 3759;
					c.heightLevel = 0;
					c.frame61(1);
					break;

				case 8929:
					c.teleportToX = 2442;
					c.teleportToY = 10147;
					c.heightLevel = 0;
					c.frame61(1);
					break;

				case 8966:
					c.teleportToX = 2523;
					c.teleportToY = 3740;
					c.heightLevel = 0;
					c.frame61(1);
					break;

				case 9357:
					c.RemoveNPC();
					c.healersCount = 0;
					c.healers = false;
					c.teleblock = false;
					c.frame61(0);
					c.clearInterface();
					c.Teleport(2441, 5171, 0);
					break;

				case 9391:
					c.clearInterface();
					c.Teleport(2398, 5150, 0);
					c.setSidebarInterface(6, 3209);
					c.Stuck = true;
					c.teleblock = true;
					c.npcId = 1666;
					c.isNpc = true;
					c.updateRequired = true;
					c.appearanceUpdateRequired = true;
					break;

				case 9319:
					if (c.playerLevel[16] > 60) {
						c.Delay("SlayerUp");
					} else {
						c.Send("You need 61 or higher Agility to climb up the chain!");
					}
					break;

				case 9320:
					if (c.playerLevel[16] > 60) {
						c.Delay("SlayerDown");
					} else {
						c.Send("You need 61 or higher Agility to climb up the chain!");
					}
					break;

				case 4487:
				case 4490:
					c.ReplaceObject(3428, 3535, 4487, 0);
					c.ReplaceObject(3429, 3535, 4490, -2);
					break;

				case 10529:
				case 10527:
					c.ReplaceObject(3426, 3555, 10527, 0);
					c.ReplaceObject(3427, 3555, 10529, -2);
					break;

				case 4493:
					c.Teleport(3433, 3537, 1);
					break;

				case 4494:
					c.Teleport(3438, 3537, 0);
					break;

				case 2478:
					c.runecraft(1, 75, 556, 30, 45, 60);
					break;

				case 2480:
					c.runecraft(5, 100, 555, 30, 45, 60);
					break;

				case 2481:
					c.runecraft(9, 125, 557, 45, 55, 65);
					break;

				case 2482:
					c.runecraft(14, 150, 554, 50, 60, 70);
					break;

				case 2483:
					c.runecraft(20, 175, 559, 55, 65, 75);
					break;

				case 2487:
					c.runecraft(35, 225, 562, 60, 70, 80);
					break;

				case 2484:
					c.runecraft(40, 200, 553, 60, 70, 80);
					break;

				case 2486:
					c.runecraft(44, 250, 561, 60, 74, 91);
					break;

				case 2485:
					c.runecraft(54, 275, 563, 65, 79, 93);
					break;

				case 2488:
					c.runecraft(65, 300, 560, 72, 84, 96);
					break;

				case 2490:
					c.runecraft(80, 325, 565, 89, 94, 99);
					break;

				case 7139:
					c.Send("You step into the mysterious rift and end up in the air temple");
					c.Teleport(2845, 4832, 0);
					break;

				case 7137:
					c.Send("You step into the mysterious rift and end up in the water temple");
					c.Teleport(2713, 4836, 0);
					break;

				case 7130:
					c.Send("You step into the mysterious rift and end up in the earth temple");
					c.Teleport(2660, 4839, 0);
					break;

				case 7129:
					c.Send("You step into the mysterious rift and end up in the fire temple");
					c.Teleport(2584, 4836, 0);
					break;

				case 7131:
					c.Send("You step into the mysterious rift and end up in the body temple");
					c.Teleport(2527, 4833, 0);
					break;

				case 7135:
					c.Send("You step into the mysterious rift and end up in the law temple");
					c.Teleport(2464, 4834, 0);
					break;

				case 7133:
					c.Send("You step into the mysterious rift and end up in the nature temple");
					c.Teleport(2398, 4841, 0);
					break;

				case 7132:
					c.Send("You step into the mysterious rift and end up in the astral temple");
					c.Teleport(2162, 4833, 0);
					break;

				case 7134:
					c.Send("You step into the mysterious rift and end up in the chaos temple");
					c.Teleport(2269, 4843, 0);
					break;

				case 7136:
					c.Send("You step into the mysterious rift and end up in the death temple");
					c.Teleport(2209, 4836, 0);
					break;

				case 7141:
					c.Send("You step into the mysterious rift and end up in the blood temple");
					c.Teleport(1948, 5008, 0);
					break;

				default:
					break;
				}
			}
		} catch(Exception E) {
		}
	}
}