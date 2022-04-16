class Agility {
	
	public void agilityShop(Client c, int type) { //Agility Ticket Exchange
		int sRand = Misc.random(5000);
		if (type == 1) {
			if (c.hasAmount(2996, 10)) {
				c.delete(2996, 10);
				c.addSkillXP(500, 16);
				if (sRand >= 600 && sRand <= 650) {
					c.addItem(2999,50);
					c.Send("The shopkeeper hands you 50 toadflax! Lucky!");
				}
				if (sRand == 4353) {
					int hMisc = 1038 + Misc.random(11);
					c.addItem(c.randomPartyHat(),1);
					c.Send("The shopkeeper hands you a party hat! Super lucky!");
				}
			} else {
				c.Send("You need 10 Tickets to buy the experience.");
			}
		} else if (type == 2) {
			if (c.hasAmount(2996, 3)) {
				c.delete(2996, 3);
				c.addItem(2998, 1);
				if (sRand >= 1300 && sRand <= 1450) {
					c.addItem(2999,50);
					c.Send("The shopkeeper hands you 50 toadflax! Lucky!");
				}
				if (sRand == 4305) {
					int hMisc = 1038 + Misc.random(11);
					c.addItem(hMisc,1);
					c.Send("The shopkeeper hands you a party hat! Super lucky!");
				}
			} else {
				c.Send("You need 3 Tickets to buy Toadflax herb.");
			}
		} else if (type == 3) {
			if (c.hasAmount(2996, 25)) {
				c.delete(2996, 25);
				c.addSkillXP(1400, 16);
				if (sRand == 4304) {
					int hMisc = 1038 + Misc.random(11);
					c.addItem(hMisc,1);
					c.Send("The shopkeeper hands you a party hat! Super lucky!");
				}
			} else {
				c.Send("You need 25 Tickets to buy the experience.");
			}
		} else if (type == 4) {
			if (c.hasAmount(2996, 100)) {
				c.delete(2996, 100);
				c.addSkillXP(6000, 16);
				if (sRand == 4303) {
					int hMisc = 1038 + Misc.random(11);
					c.addItem(hMisc,1);
					c.Send("The shopkeeper hands you a party hat! Super lucky!");
				}
			} else {
				c.Send("You need 100 Tickets to buy the experience.");
			}
		} else if (type == 5) {
			if (c.hasAmount(2996, 800)) {
				c.delete(2996, 800);
				c.addItem(2997, 1);
				c.addItem(2651, 1);
				c.Send("The shopkeeper decided to throw in a free pirate hat.");
			} else {
				c.Send("You need 800 Tickets to buy the Pirate hook's.");
			}
		} else if (type == 6) {
			if (c.hasAmount(2996, 1000)) {
				c.delete(2996, 1000);
				c.addSkillXP(60000, 16);
				if (sRand == 4302) {
					int hMisc = 1038 + Misc.random(11);
					c.addItem(hMisc,1);
					c.Send("The shopkeeper hands you a party hat! Super lucky!");
				}
			} else {
				c.Send("You need 1000 Tickets to buy the experience.");
			}
		} else if (type == 7) {
			if (c.hasAmount(2996, 10)) {
				c.delete(2996, 10);
				c.addItem(3000, 1);
				if (sRand >= 2500 && sRand <= 2600) {
					c.addItem(3001,50);
					c.Send("The shopkeeper accidentally gave you 50 extra Snapdragon!");
				}
			} else {
				c.Send("You need 10 Tickets to buy the Snapdragon herb.");
			}
		} else if (type == 8) {
			if (c.hasAmount(2996, 1)) {
				c.delete(2996, 1);
				c.addSkillXP(50, 16);
				if (sRand >= 2500 && sRand <= 2510) {
					c.addItem(3001,50);
					c.Send("The shopkeeper accidentally gave you 50 Snapdragon!");
				}
			} else {
				c.Send("You need 1 ticket to buy experience.");
			}
		}
	}
	
	public void resetAgility(Client c, int playerId) {
		c.playerSEW = 0x333;
		c.playerSER = 0x338;
		c.agilityTimer = 0;
		c.walkTo_old(c.absX, c.absY);
		if (c.wasRunning) {
			c.isRunning2 = true;
		} else {
			c.isRunning2 = false;
		}
	}
	
}