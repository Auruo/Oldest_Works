class Mining {
	
	private boolean AreXItemsInBag(Client c, int ItemID, int Amount) {
		int ItemCount = 0;
		for (int i = 0; i < c.playerItems.length; i++) {
			if ((c.playerItems[i] - 1) == ItemID) {
				ItemCount++;
			}
			if (ItemCount == Amount) {
				return true;
			}
		}
		return false;
	}
	
	public int GetPickAni(Client c) {
		int PickAni = 0;
		int Pick = c.playerEquipment[c.playerWeapon];
		if (Pick == 1275) {
			PickAni = 624;
		} else if (Pick == 1265) {
			PickAni = 624;
		} else if (Pick == 1267) {
			PickAni = 624;
		} else if (Pick == 1269) {
			PickAni = 624;
		} else if (Pick == 1271) {
			PickAni = 624;
		} else if (Pick == 1273) {
			PickAni = 624;
		} else if (Pick == 1275) {
			PickAni = 624;
		}
		if (AreXItemsInBag(c, 1265, 1)) {
			PickAni = 624;
		} else if (AreXItemsInBag(c, 1267, 1)) {
			PickAni = 624;
		} else if (AreXItemsInBag(c, 1269, 1)) {
			PickAni = 624;
		} else if (AreXItemsInBag(c, 1271, 1)) {
			PickAni = 624;
		} else if (AreXItemsInBag(c, 1273, 1)) {
			PickAni = 624;
		} else if (AreXItemsInBag(c, 1275, 1)) {
			PickAni = 624;
		}
		return PickAni;
	}

	public int CheckPick(Client c) {
		int Pick = c.playerEquipment[c.playerWeapon];
		if (Pick == 1265) {
			return 0;
		} else if (Pick == 1267) {
			return 1;
		} else if (Pick == 1269) {
			return 2;
		} else if (Pick == 1271) {
			return 3;
		} else if (Pick == 1273) {
			return 4;
		} else if (Pick == 1275) {
			return 5;
		}
		if (AreXItemsInBag(c, 1265, 1)) {
			return 0;
		} else if (AreXItemsInBag(c, 1267, 1)) {
			return 1;
		} else if (AreXItemsInBag(c, 1269, 1)) {
			return 2;
		} else if (AreXItemsInBag(c, 1271, 1)) {
			return 3;
		} else if (AreXItemsInBag(c, 1273, 1)) {
			return 4;
		} else if (AreXItemsInBag(c, 1275, 1)) {
			return 5;
		}
		return -1;
	}

	public final static int gems[] = { 1617, 1619, 1621, 1623, 1625, 1627, 1629, 1631, 6571 };
	public boolean Mining = false;
	public int MineTimer; //choptimer
	public int RockLevel; //Treelevel
	public int RockId; // TreeID
	public int RockXp; // TreeXp
	public int OreId; // LogId
	public int MinedId; //StumpId
	public int Gone2for; // Gonefor
	public int HowManyOre; //HowManyLogs
	public int RockX; //treex
	public int RockY; //treey

	public void InitiateMI(Client c, int rock, int x, int y) {
		int PickId = CheckPick(c);
		int Minus2Time = 0;
		RockId = rock;
		if (PickId == 1265) {
			Minus2Time = 0;
		} else if (PickId == 1267) {
			Minus2Time = 3;
		} else if (PickId == 1269) {
			Minus2Time = 6;
		} else if (PickId == 1271) {
			Minus2Time = 12;
		} else if (PickId == 1373) {
			Minus2Time = 18;
		} else if (PickId == 1375) {
			Minus2Time = 25;
		}
		if (RockId == 2491) { //Rune Essence
			MineTimer = 4 + Misc.random(6);
			RockLevel = 1;
			HowManyOre = 1;
			RockXp = 40;
			OreId = 1436;
			MinedId = 450;
			Gone2for = 5 + Misc.random(5);
		} else if (RockId == 2108 || RockId == 2109) { //Clay
			MineTimer = 8 + Misc.random(4);
			RockLevel = 1;
			HowManyOre = 1;
			RockXp = 50;
			OreId = 434;
			MinedId = 450;
			Gone2for = 10 + Misc.random(10);
		} else if (RockId == 2090 || RockId == 2091) { //Copper
			MineTimer = 8 + Misc.random(6);
			RockLevel = 1;
			HowManyOre = 1;
			RockXp = 93;
			OreId = 436;
			MinedId = 450;
			Gone2for = 10 + Misc.random(10);
		} else if (RockId == 2094 || RockId == 2095) { //Tin
			MineTimer = 8 + Misc.random(6);
			RockLevel = 1;
			HowManyOre = 1;
			RockXp = 90;
			OreId = 438;
			MinedId = 450;
			Gone2for = 10 + Misc.random(10);
		} else if (RockId == 10583 || RockId == 10584 || RockId == 2210) { //Blurite
			MineTimer = 20 + Misc.random(15);
			RockLevel = 10;
			HowManyOre = 1;
			RockXp = 120;
			OreId = 668;
			MinedId = 450;
			Gone2for = 30 + Misc.random(50);
		} else if (RockId == 4027) { //Limestone
			MineTimer = 30 + Misc.random(30);
			RockLevel = 1;
			HowManyOre = 1;
			RockXp = 50;
			OreId = 3211;
			MinedId = 450;
			Gone2for = 30 + Misc.random(70);
		} else if (RockId == 2098 || RockId == 2099) { //Gold
			MineTimer = 40 + Misc.random(30);
			RockLevel = 40;
			HowManyOre = 1;
			RockXp = 600;
			OreId = 444;
			MinedId = 450;
			Gone2for = 30 + Misc.random(30);
		} else if (RockId == 2100 || RockId == 2101) { //Silver
			MineTimer = 30 + Misc.random(20);
			RockLevel = 20;
			HowManyOre = 1;
			RockXp = 400;
			OreId = 442;
			MinedId = 450;
			Gone2for = 20 + Misc.random(20);
		} else if (RockId == 14856 || RockId == 2092 || RockId == 14858 || RockId == 2093) { //Iron
			MineTimer = 16 + Misc.random(30);
			RockLevel = 15;
			HowManyOre = 1;
			RockXp = 350;
			OreId = 440;
			MinedId = 450;
			Gone2for = 10 + Misc.random(30);
		} else if (RockId == 14850 || RockId == 2096 || RockId == 14851 || RockId == 2097) { //Coal
			MineTimer = 20 + Misc.random(30);
			RockLevel = 30;
			HowManyOre = 1;
			RockXp = 856;
			OreId = 453;
			MinedId = 450;
			Gone2for = 40 + Misc.random(30);
		} else if (RockId == 14854 || RockId == 2102 || RockId == 2103 || RockId == 14853) { //Mithril
			MineTimer = 30 + Misc.random(50);
			RockLevel = 55;
			HowManyOre = 1;
			RockXp = 1527;
			OreId = 447;
			MinedId = 450;
			Gone2for = 60 + Misc.random(50);
		} else if (RockId == 14863 || RockId == 2105 || RockId == 2104 || RockId == 14862) { //Addy
			MineTimer = 45 + Misc.random(50);
			RockLevel = 70;
			HowManyOre = 1;
			RockXp = 4739;
			OreId = 449;
			MinedId = 450;
			Gone2for = 80 + Misc.random(120);
		} else if (RockId == 14860 || RockId == 2106 || RockId == 2107 || RockId == 14859) { //Rune
			MineTimer = 70 + Misc.random(70);
			RockLevel = 85;
			HowManyOre = 1;
			RockXp = 8318;
			OreId = 451;
			MinedId = 450;
			Gone2for = 120 + Misc.random(240);
		} else if (RockId == 450 || RockId == 451 || RockId == 452 || RockId == 453) { //Empty
			resetMI(c);
			return;
		} else {
			resetMI(c);
			return;
		}
		if (c.freeSlots() == 0) {
			c.Send("You do not have enough inventory space to hold anymore ore!");
			resetMI(c);
			return;
		}
		if (PickId != -1) {
			if (c.getLevelForXP(c.playerXP[14]) < RockLevel) {
				c.Send("You are not a high enough level.");
				c.Send("You must atleast get a level of " + RockLevel + " to mine this rock.");
				resetMI(c);
				return;
			}
			if (!Mining) {
				c.Send("You start to mine the rock.");
			}
			RockX = x;
			RockY = y;
			MineTimer -= (c.playerLevel[14] / 3) + Minus2Time;
			Mining = true;
		} else {
			resetMI(c);
			c.Send("You need a pickaxe to mine this rock!");
		}
	}

	public void resetMI(Client c) {
		Mining = false;
		soundTimer = 0;
		MineTimer = 0;
		RockLevel = 0;
		RockId = 0;
		RockXp = 0;
		OreId = 0;
		MinedId = 0;
		Gone2for = 0;
		HowManyOre = 0;
		RockX = 0;
		RockY = 0;
		repeat2ani = 0;
		c.resetAnimation();
	}
	private int soundTimer = 0;
	public int repeat2ani = 0;
	public int PickAni = 0;

	public void MIprocess(Client c) {
		if (MineTimer > 0) {
			MineTimer--;
		}
		if (MineTimer < 0) {
			MineTimer = 1;
		}
		if (soundTimer > 0) {
			soundTimer--;
		} else {
			soundTimer = 1;
			c.frame174(432, 025, 000);
		}
		if (repeat2ani > 0) {
			repeat2ani--;
		} else {
			c.startAnimation(GetPickAni(c));
			PlayerManager.players[c.playerId].updateRequired = true;
			PlayerManager.players[c.playerId].appearanceUpdateRequired = true;
			repeat2ani = 3;
		}
		if (MineTimer == 0) {
			if (!Removeore(c)) {
				resetMI(c);
				return;
			} else {
				InitiateMI(c, RockId, RockX, RockY);
			}
		}
	}

	public void SpawnRocks(Client c) {
		for (int i = 0; i < Server.Rocks; i++) {
			if (Server.ROCKSPAWN[i] > 0) {
				c.AddGlobalObject(Server.ROCKX[i], Server.ROCKY[i],
				Server.ROCKSTUMP[i], 0, 10);
			}
		}
	}

	public boolean HasOre() {
		for (int i = 0; i < Server.Rocks; i++) {
			if (Server.ROCKX[i] == RockX && Server.ROCKY[i] == RockY) {
				if (Server.ROCKSPAWN[i] > 0) {
					return false;
				} else if (Server.ROCKSPAWN[i] == 0) {
					return true;
				}
			}
		}
		return false;
	}

	public boolean Removeore(Client c) {
		boolean New = true;
		int Rockid = Server.Rocks;
		for (int i = 0; i < Server.Rocks; i++) {
			if (Server.ROCKX[i] == RockX && Server.ROCKY[i] == RockY) {
				if (!HasOre()) {
					resetMI(c);
					return false;
				}
				New = false;
				Server.ORELEFT[i]--;
				c.addSkillXP(RockXp, 14);
				c.addItem(OreId, 1);
				if (Misc.random(50) == 37) {
					c.addItem(gems[Misc.random(8)], 1);
					c.Send("You find a gem in this rock.");
				}
				c.Send("You get some " + c.GetItemName(OreId) + ".");
				if (Server.ORELEFT[i] <= 0) {
					Server.ROCKSPAWN[i] = Gone2for;
					Server.ORE[i] = HowManyOre;
					if (Server.ORE[i] == 0) {
						Server.ORE[i] = 1;
					}
					c.AddGlobalObj(RockX, RockY, MinedId, 0, 10);
					return false;
				}
				return true;
			}
		}
		if (New) {
			Server.ROCKX[Rockid] = RockX;
			Server.ROCKY[Rockid] = RockY;
			Server.ROCKID[Rockid] = RockId;
			Server.ROCKSTUMP[Rockid] = MinedId;
			Server.ORELEFT[Rockid] = HowManyOre;
			if (Server.ORELEFT[Rockid] == 0) {
				Server.ORELEFT[Rockid] = 1;
			}
			Server.Rocks++;
			return true;
		}
		return false;
	}
}