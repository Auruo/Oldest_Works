class Cooking {

	//All are in the order listed - Auruo
	int plus2Plus4[] = { 371, 377, 383, 389, 7944, 353 }; //Swordfish, Lobster, Shark, Manta Ray, Monkfish, Mackerel
	int minus2Add1[] = { 317, 321 }; //Shrimps, Anchovies
	int minus2Add2[] = { 331, 335, 341 }; //Salmon, Trout, Cod
	
	void Cook(Client c) {
		boolean success = true;
		int exp = 0, ran = 0;
		switch (c.FishId) {
			
			//Fish
		case 317: //Shrimp
			exp = 50;
			ran = 30 - c.playerLevel[c.playerCooking];
			break;
			
		case 321: //Anchovies
			exp = 100;
			ran = 35 - c.playerLevel[c.playerCooking];
			break;
			
		case 327: //Sardine
			exp = 150;
			ran = 35 - c.playerLevel[c.playerCooking];
			break;
			
		case 331: //Salmon
			exp = 400;
			ran = 65 - c.playerLevel[c.playerCooking];
			break;
			
		case 335: //Trout
			exp = 200;
			ran = 50 - c.playerLevel[c.playerCooking];
			break;
			
		case 341: //Cod
			exp = 225;
			ran = 53 - c.playerLevel[c.playerCooking];
			break;
			
		case 345: //Herring
			exp = 175;
			ran = 43 - c.playerLevel[c.playerCooking];
			break;
			
		case 349: //Pike
			exp = 350;
			ran = 60 - c.playerLevel[c.playerCooking];
			break;
			
		case 353: //Mackerel
			exp = 175;
			ran = 40 - c.playerLevel[c.playerCooking];
			break;
			
		case 359: //Tuna
			exp = 450;
			ran = 70 - c.playerLevel[c.playerCooking];
			break;
			
		case 363: //Bass
			exp = 550;
			ran = 78 - c.playerLevel[c.playerCooking];
			break;
			
		case 377: //Lobster
			exp = 500;
			ran = 75 - c.playerLevel[c.playerCooking];
			break;

		case 383: //Shark
			exp = 700;
			ran = 100 - c.playerLevel[c.playerCooking];
			break;

		case 7944: //Monkfish
			exp = 600;
			ran = 120 - c.playerLevel[c.playerCooking];
			break;

		case 389: //Manta Ray
			exp = 850;
			ran = 120 - c.playerLevel[c.playerCooking];
			break;
		}
		if (ran < 0) {
			ran = 0;
		}
		if (Misc.random(100) < ran) {
			success = false;
		}
		if (c.hasItem(c.FishId)) {
			c.anim(883);
			c.delete(c.FishId, 1);
			if (c.isList(plus2Plus4, c.FishId)) {
				if (success) {
					c.addItem(c.FishId+2, 1);
					c.addSkillXP(exp * c.playerLevel[7], 7);
					c.Send("You cook a "+c.GetItemName(c.FishId+2)+".");
				} else {
					c.addItem(c.FishId+4, 1);
					c.Send("You burn a "+c.GetItemName(c.FishId+2)+".");
				}
			} else if (c.isList(minus2Add1, c.FishId) || c.isList(minus2Add2, c.FishId)) {
				if (success) {
					c.addItem(c.FishId-2, 1);
					c.addSkillXP(exp * c.playerLevel[7], 7);
					c.Send("You cook some "+c.GetItemName(c.FishId-2)+".");
				} else if (c.isList(minus2Add1, c.FishId)) {
					c.addItem(323, 1);
					c.Send("You burn some "+c.GetItemName(c.FishId-2)+".");
				} else if (c.isList(minus2Add2, c.FishId)) {
					c.addItem(343,1);
					c.Send("You burn a "+c.GetItemName(c.FishId-2)+".");
				}
			} else if (c.FishId == 349) { //Pike
				if (success) {
					c.addItem(c.FishId+2, 1);
					c.addSkillXP(exp * c.playerLevel[7], 7);
					c.Send("You cook some "+c.GetItemName(c.FishId-2)+".");
				} else {
					c.addItem(343,1);
					c.Send("You burn a "+c.GetItemName(c.FishId-2)+".");
				}
			} else if (c.FishId == 327) { //Sardine
				if (success) {
					c.addItem(c.FishId-2, 1);
					c.addSkillXP(exp * c.playerLevel[7], 7);
					c.Send("You cook a sardine.");
				} else {
					c.addItem(369,1);
					c.Send("You burn a sardine.");
				}
			} else if (c.FishId == 359 || c.FishId == 363) {
				if (success) {
					if (c.FishId == 359) {
						c.addItem(c.FishId-2, 1);
						c.addSkillXP(exp * c.playerLevel[7], 7);
						c.Send("You cook the tuna.");
					} else {
						c.addItem(c.FishId+2, 1);
						c.addSkillXP(exp * c.playerLevel[7], 7);
						c.Send("You cook a bass.");
					}
				} else {
					c.addItem(367,1);
					c.Send("You burn it.");
				}
			} else if (c.FishId == 345) { //Herring
				if (success) {
					c.addItem(c.FishId+2, 1);
					c.addSkillXP(exp * c.playerLevel[7], 7);
					c.Send("You cook a herring.");
				} else {
					c.addItem(353,1);
					c.Send("You burn a herring.");
				}
			}
		}
		if (!c.hasItem(c.FishId)) {
			c.Cooking = false;
			c.FishId = 0;
		}
		c.ActionDelay = System.currentTimeMillis();
		c.updateRequired = true;
		c.appearanceUpdateRequired = true;
	}
	
}