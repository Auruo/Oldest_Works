public class FishingHandler {

	public int SalmonOrTrout[] =
	{
		335, 335, 335, 331
	};
	public int ShrimpsOrAnchovies[] =
	{
		317, 321
	};
	public int SardineOrHerring[] =
	{
		327, 345
	};
	public int TunaOrSwordfish[] =
	{
		359, 359, 359, 371
	};
	public int BigNet[] =
	{
		405, 407, 353, 401, 1061, 1059
	};
	public int BigNetAndCod[] =
	{
		405, 407, 353, 401, 1061, 1059, 341
	};
	public int BigNetAndBass[] =
	{
		405, 407, 353, 401, 1061, 1059, 341, 363
	};
	
	public int WhatFish = -1;
	public boolean isFishing = false;
	public int FishTimer = -1;
	public int EmoteTimer = -1;
	public boolean FirstAnim = false;
	public int Fish = -1;
	public int NPCX = -1;
	public int NPCY = -1;
	
	public void NoSpace(int ID)
	{
		Client c = (Client) Server.PlayerManager.players[ID];
		c.Send("You can't carry any more fish.");
		FishTimer = -1;
		EmoteTimer = -1;
		c.resetAnimation();
		isFishing = false;
	}
	public void resetAll(Client c) {
		FishTimer = -1;
		EmoteTimer = -1;
		Fish = -1;
		isFishing = false;
		FirstAnim = false;
		c.resetAnimation();
		c.RemoveAllWindows();
	}
	public void ResetFishing(int ID)
	{
		Client c = (Client) Server.PlayerManager.players[ID];
		FishTimer = -1;
		EmoteTimer = -1;
		isFishing = false;
		c.resetAnimation();
		FirstAnim = false;
	}

	public void CatchShark(int ID)
	{
		Client c = (Client) Server.PlayerManager.players[ID];
		if (c.freeSlots() > 0)
		{
			resetAll(c);
			isFishing = true;
			FishTimer = (Misc.random(40) + 20) - (c.playerLevel[10] / 9);
			Fish = 14;
			FishEmote(ID);
			EmoteTimer = 6;
		}
		else NoSpace(ID);
	}

	public void CatchBigNetAndCod(int ID)
	{
		WhatFish = randomBigNetAndCod();
		Client c = (Client) Server.PlayerManager.players[ID];
		if (c.freeSlots() > 0)
		{
			resetAll(c);
			isFishing = true;
			FishTimer = (Misc.random(30) + 10) - (c.playerLevel[10] / 9);
			Fish = 13;
			FishEmote(ID);
			EmoteTimer = 6;
		}
		else NoSpace(ID);
	}

	public void CatchBigNetAndBass(int ID)
	{
		WhatFish = randomBigNetAndBass();
		Client c = (Client) Server.PlayerManager.players[ID];
		if (c.freeSlots() > 0)
		{
			resetAll(c);
			isFishing = true;
			FishTimer = (Misc.random(30) + 10) - (c.playerLevel[10] / 9);
			Fish = 12;
			FishEmote(ID);
			EmoteTimer = 6;
		}
		else NoSpace(ID);
	}

	public void CatchBigNet(int ID)
	{
		WhatFish = randomBigNet();
		Client c = (Client) Server.PlayerManager.players[ID];
		if (c.freeSlots() > 0)
		{
			resetAll(c);
			isFishing = true;
			FishTimer = (Misc.random(30) + 10) - (c.playerLevel[10] / 9);
			Fish = 11;
			FishEmote(ID);
			EmoteTimer = 6;
		}
		else NoSpace(ID);
	}

	public void CatchTunaAndSwordfish(int ID)
	{
		Client c = (Client) Server.PlayerManager.players[ID];
		WhatFish = randomTunaOrSwordfish();
		if (c.freeSlots() > 0)
		{
			resetAll(c);
			isFishing = true;
			FishTimer = (Misc.random(40) + 15) - (c.playerLevel[10] / 9);
			Fish = 10;
			FishEmote(ID);
			EmoteTimer = 6;
		}
		else NoSpace(ID);
	}

	public void CatchTuna(int ID)
	{
		Client c = (Client) Server.PlayerManager.players[ID];
		if (c.freeSlots() > 0)
		{
			resetAll(c);
			isFishing = true;
			FishTimer = (Misc.random(15) + 10) - (c.playerLevel[10] / 9);
			Fish = 8;
			FishEmote(ID);
			EmoteTimer = 6;
		}
		else NoSpace(ID);
	}

	public void CatchLobster(int ID)
	{
		Client c = (Client) Server.PlayerManager.players[ID];
		if (c.freeSlots() > 0)
		{
			resetAll(c);
			isFishing = true;
			FishTimer = (Misc.random(25) + 15) - (c.playerLevel[10] / 9);
			Fish = 9;
			FishEmote(ID);
			EmoteTimer = 6;
		}
		else NoSpace(ID);
	}

	public void CatchSardineAndHerring(int ID)
	{
		Client c = (Client) Server.PlayerManager.players[ID];
		WhatFish = randomSardineOrHerring();
		if (c.freeSlots() > 0)
		{
			resetAll(c);
			isFishing = true;
			FishTimer = (Misc.random(10) + 10) - (c.playerLevel[10] / 9);
			Fish = 3;
			FishEmote(ID);
			EmoteTimer = 6;
		}
		else NoSpace(ID);
	}

	public void CatchSardine(int ID)
	{
		Client c = (Client) Server.PlayerManager.players[ID];
		if (c.freeSlots() > 0)
		{
			resetAll(c);
			isFishing = true;
			FishTimer = (Misc.random(10) + 10) - (c.playerLevel[10] / 9);
			Fish = 2;
			FishEmote(ID);
			EmoteTimer = 6;
		}
		else NoSpace(ID);
	}

	public void CatchPike(int ID)
	{
		Client c = (Client) Server.PlayerManager.players[ID];
		if (c.freeSlots() > 0)
		{
			resetAll(c);
			isFishing = true;
			FishTimer = (Misc.random(10) + 10) - (c.playerLevel[10] / 9);
			Fish = 6;
			FishEmote(ID);
			EmoteTimer = 6;
		}
		else NoSpace(ID);
	}

	public void CatchShrimps(int ID)
	{
		Client c = (Client) Server.PlayerManager.players[ID];
		if (c.freeSlots() > 0)
		{
			resetAll(c);
			isFishing = true;
			FishTimer = (Misc.random(10) + 10) - (c.playerLevel[10] / 9);
			Fish = 1;
			FishEmote(ID);
			EmoteTimer = 6;
		}
		else NoSpace(ID);
	}

	public void CatchShrimpsAndAnchovies(int ID)
	{
		Client c = (Client) Server.PlayerManager.players[ID];
		WhatFish = randomShrimpsOrAnchovies();
		if (c.freeSlots() > 0)
		{
			resetAll(c);
			isFishing = true;
			FishTimer = (Misc.random(10) + 10) - (c.playerLevel[10] / 9);
			Fish = 4;
			FishEmote(ID);
			EmoteTimer = 6;
		}
		else NoSpace(ID);
	}

	public void CatchTrout(int ID)
	{
		Client c = (Client) Server.PlayerManager.players[ID];
		if (c.freeSlots() > 0)
		{
			resetAll(c);
			isFishing = true;
			FishTimer = (Misc.random(10) + 10) - (c.playerLevel[10] / 9);
			Fish = 5;
			FishEmote(ID);
			EmoteTimer = 6;
		}
		else NoSpace(ID);
	}

	public void CatchSalmonAndTrout(int ID)
	{
		Client c = (Client) Server.PlayerManager.players[ID];
		WhatFish = randomSalmonOrTrout();
		if (c.freeSlots() > 0)
		{
			resetAll(c);
			isFishing = true;
			FishTimer = (Misc.random(10) + 10) - (c.playerLevel[10] / 9);
			Fish = 7;
			FishEmote(ID);
			EmoteTimer = 6;
		}
		else NoSpace(ID);
	}

	public int randomSalmonOrTrout()
	{
		return SalmonOrTrout[(int) (Math.random() * SalmonOrTrout.length)];
	}

	public int randomShrimpsOrAnchovies()
	{
		return ShrimpsOrAnchovies[(int) (Math.random() * ShrimpsOrAnchovies.length)];
	}

	public int randomSardineOrHerring()
	{
		return SardineOrHerring[(int) (Math.random() * SardineOrHerring.length)];
	}

	public int randomTunaOrSwordfish()
	{
		return TunaOrSwordfish[(int) (Math.random() * TunaOrSwordfish.length)];
	}

	public int randomBigNet()
	{
		return BigNet[(int) (Math.random() * BigNet.length)];
	}

	public int randomBigNetAndCod()
	{
		return BigNetAndCod[(int) (Math.random() * BigNetAndCod.length)];
	}

	public int randomBigNetAndBass()
	{
		return BigNetAndBass[(int) (Math.random() * BigNetAndBass.length)];
	}

	public void FirstFish(int NPCID, int ID)
	{
		Client c = (Client) Server.PlayerManager.players[ID];
		switch (NPCID)
		{
		case 313: //Big net fishing
			if (c.playerLevel[10] >= 16 && 22 <= c.playerLevel[10])
			{
				if (c.hasItem(305))
				{
					CatchBigNet(ID);
					c.Send("You cast out your net...");
				}
				else
				{
					c.Send("You need big fishing net to fish here.");
				}
			}
			else if (c.playerLevel[10] >= 23 && 45 <= c.playerLevel[10])
			{
				if (c.hasItem(305))
				{
					CatchBigNetAndCod(ID);
					c.Send("You cast out your net...");
				}
				else
				{
					c.Send("You need big fishing net to fish here.");
				}
			}
			else if (c.playerLevel[10] >= 46)
			{
				if (c.hasItem(305))
				{
					CatchBigNetAndBass(ID);
					c.Send("You cast out your net...");
				}
				else
				{
					c.Send("You need big fishing net to fish here.");
				}
			}
			else if (c.playerLevel[10] <= 15)
			{
				c.Send("You need at least level 16 fishing to catch here.");
			}
			c.TurnPlayerTo(NPCX, NPCY);
			break;
		case 309: //Trout or Salmon
			if (c.playerLevel[10] >= 20 && 30 >= c.playerLevel[10])
			{
				if (c.hasItem(309))
				{
					if (c.hasItem(314))
					{
						CatchTrout(ID);
						c.Send("You attempt to catch a fish.");
					}
					else
					{
						c.Send("You need feather to fish here.");
					}
				}
				else
				{
					c.Send("You need fly fishing rod to fish here.");
				}
			}
			else if (c.playerLevel[10] >= 30)
			{
				if (c.hasItem(309))
				{
					if (c.hasItem(314))
					{
						CatchSalmonAndTrout(ID);
						c.Send("You attempt to catch a fish.");
					}
					else
					{
						c.Send("You need feather to fish here.");
					}
				}
				else
				{
					c.Send("You need fly fishing rod to fish here.");
				}
			}
			else if (c.playerLevel[10] <= 19)
			{
				c.Send("You need at least level 20 fishing to catch trout.");
			}
			c.TurnPlayerTo(NPCX, NPCY);
			break;
			
		case 235: //Shrimps or Anchovies
		case 316:
			if (c.playerLevel[10] <= 14)
			{
				if (c.hasItem(303))
				{
					CatchShrimps(ID);
					c.Send("You cast out your net...");
				}
				else
				{
					c.Send("You need small fishing net to fish here.");
				}
			}
			else
			{
				if (c.hasItem(303))
				{
					CatchShrimpsAndAnchovies(ID);
					c.Send("You cast out your net...");
				}
				else
				{
					c.Send("You need small fishing net to fish here.");
				}
			}
			c.TurnPlayerTo(NPCX, NPCY);
			break;
		case 312: //Lobster
			if (c.playerLevel[10] >= 40)
			{
				if (c.hasItem(301))
				{
					CatchLobster(ID);
					c.Send("You attempt to catch a lobster.");
				}
				else
				{
					c.Send("You need lobster pot to fish here.");
				}
			}
			else
			{
				c.Send("You need at least level 40 fishing to catch lobster.");
			}
			c.TurnPlayerTo(NPCX, NPCY);
			break;
		}
	}

	public void SecondFish(int NPCID, int ID)
	{
		Client c = (Client) Server.PlayerManager.players[ID];
		switch (NPCID)
		{
		case 313: //Shark
			if (c.playerLevel[10] >= 76)
			{
				if (c.hasItem(311))
				{
					CatchShark(ID);
					c.Send("You attempt to catch a shark or manta ray.");
				}
				else
				{
					c.Send("You need harpoon to fish here.");
				}
			}
			else
			{
				c.Send("You need at least level 76 fishing to catch shark.");
			}
			c.TurnPlayerTo(NPCX, NPCY);
			break;
		case 309: //Pike
			if (c.playerLevel[10] >= 25)
			{
				if (c.hasItem(307))
				{
					if (c.hasItem(313))
					{
						CatchPike(ID);
						c.Send("You attempt to catch a fish.");
					}
					else
					{
						c.Send("You need fishing bait to fish here.");
					}
				}
				else
				{
					c.Send("You need fishing rod to fish here.");
				}
			}
			else
			{
				c.Send("You need at least level 25 fishing to catch pike.");
			}
			c.TurnPlayerTo(NPCX, NPCY);
			break;
		case 316: //Sardine or Herring
			if (c.playerLevel[10] >= 5 && 9 >= c.playerLevel[10])
			{
				if (c.hasItem(307))
				{
					if (c.hasItem(313))
					{
						CatchSardine(ID);
						c.Send("You attempt to catch a fish.");
					}
					else
					{
						c.Send("You need fishing bait to fish here.");
					}
				}
				else
				{
					c.Send("You need fishing rod to fish here.");
				}
			}
			else if (c.playerLevel[10] >= 10)
			{
				if (c.hasItem(307))
				{
					if (c.hasItem(313))
					{
						CatchSardineAndHerring(ID);
						c.Send("You attempt to catch a fish.");
					}
					else
					{
						c.Send("You need fishing bait to fish here.");
					}
				}
				else
				{
					c.Send("You need fishing rod to fish here.");
				}
			}
			else if (c.playerLevel[10] <= 4)
			{
				c.Send("You need at least level 5 fishing to catch sardine.");
			}
			c.TurnPlayerTo(NPCX, NPCY);
			break;
		case 312: //Tuna or Swordfish
			if (c.playerLevel[10] <= 49 && 35 <= c.playerLevel[10])
			{
				if (c.hasItem(311))
				{
					CatchTuna(ID);
					c.Send("You attempt to catch a fish.");
				}
				else
				{
					c.Send("You need harpoon to fish here.");
				}
			}
			else if (c.playerLevel[10] >= 50)
			{
				if (c.hasItem(311))
				{
					CatchTunaAndSwordfish(ID);
					c.Send("You attempt to catch a fish.");
				}
				else
				{
					c.Send("You need harpoon to fish here.");
				}
			}
			else if (c.playerLevel[10] <= 34)
			{
				c.Send("You need at least level 35 fishing to catch tuna.");
			}
			c.TurnPlayerTo(NPCX, NPCY);
			break;
		}
	}
	public void FishEmote(int ID)
	{
		Client c = (Client) Server.PlayerManager.players[ID];
		switch (Fish)
		{
		case 1: //Small fishing net
		case 4:
			c.anim(621);
			break;
		case 5: //Fishing rod
		case 6:
		case 7:
		case 2:
		case 3:
			if (FirstAnim)
			{
				c.anim(623);
			}
			else
			{
				c.anim(622);
				FirstAnim = true;
			}
			break;
		case 8: //Harpoon
		case 10:
		case 14:
			c.anim(618);
			break;
		case 9: //Lobster pot
			c.anim(619);
			break;
		case 13: //Big fishing net
		case 11:
		case 12:
			c.anim(620);
			break;
		}
	}

	public void Fishing(int ID)
	{
		Client c = (Client) Server.PlayerManager.players[ID];
		switch (Fish)
		{
		case 1: //Shrimp
			c.addItem(317, 1);
			c.addSkillXP(50, 10);
			c.Send("You catch some shrimps.");
			CatchShrimps(ID);
			break;
		case 2: //Sardine
			c.addItem(327, 1);
			c.addSkillXP(75, 10);
			c.deleteItem(313, c.getItemSlot(313), 1);
			c.Send("You catch a sardine.");
			CatchSardine(ID);
			break;
		case 3: //Sardine or Herring
			if (WhatFish == 327)
			{
				c.addItem(327, 1);
				c.addSkillXP(75, 10);
				c.deleteItem(313, c.getItemSlot(313), 1);
				c.Send("You catch a sardine.");
			}
			else
			{
				c.addItem(345, 1);
				c.addSkillXP(100, 10);
				c.deleteItem(313, c.getItemSlot(313), 1);
				c.Send("You catch a herring.");
			}
			CatchSardineAndHerring(ID);
			break;
		case 4: //Shrimp or Anchovies
			if (WhatFish == 317)
			{
				c.addItem(317, 1);
				c.addSkillXP(50, 10);
				c.Send("You catch some shrimps.");
			}
			else
			{
				c.addItem(321, 1);
				c.addSkillXP(75, 10);
				c.Send("You catch some anchovies.");
			}
			CatchShrimpsAndAnchovies(ID);
			break;
		case 5: //Trout
			c.addItem(335, 1);
			c.addSkillXP(150, 10);
			c.deleteItem(314, c.getItemSlot(314), 1);
			c.Send("You catch a trout.");
			CatchTrout(ID);
			break;
		case 6: //Pike
			c.addItem(349, 1);
			c.addSkillXP(250, 10);
			c.deleteItem(313, c.getItemSlot(313), 1);
			c.Send("You catch a pike.");
			CatchPike(ID);
			break;
		case 7: //Trout or Salmon
			if (WhatFish == 335)
			{
				c.addItem(335, 1);
				c.addSkillXP(333, 10);
				c.deleteItem(314, c.getItemSlot(314), 1);
				c.Send("You catch a trout.");
			}
			else
			{
				c.addItem(331, 1);
				c.addSkillXP(350, 10);
				c.deleteItem(314, c.getItemSlot(314), 1);
				c.Send("You catch a salmon.");
			}
			CatchSalmonAndTrout(ID);
			break;
		case 8: //Tuna
			c.addItem(359, 1);
			c.addSkillXP(500, 10);
			c.Send("You catch a tuna.");
			CatchTuna(ID);
			break;
		case 9: //Lobster
			c.addItem(377, 1);
			c.addSkillXP(750, 10);
			c.Send("You catch a lobster.");
			CatchLobster(ID);
			break;
		case 10: //Tuna or Swordfish
			if (WhatFish == 359)
			{
				c.addItem(359, 1);
				c.addSkillXP(500, 10);
				c.Send("You catch a tuna.");
			}
			else
			{
				c.addItem(371, 1);
				c.addSkillXP(1250, 10);
				c.Send("You catch a swordfish.");
			}
			CatchTunaAndSwordfish(ID);
			break;
		case 11: //Big net
			if (WhatFish == 405)
			{
				c.addItem(405, 1);
				c.addSkillXP(30, 10);
				c.Send("You catch a casket.");
			}
			else if (WhatFish == 407)
			{
				c.addItem(407, 1);
				c.addSkillXP(30, 10);
				c.Send("You catch a oyster.");
			}
			else if (WhatFish == 353)
			{
				c.addItem(353, 1);
				c.addSkillXP(50, 10);
				c.Send("You catch a mackerel.");
			}
			else if (WhatFish == 401)
			{
				c.addItem(401, 1);
				c.addSkillXP(3, 10);
				c.Send("You catch seaweed.");
			}
			else if (WhatFish == 1061)
			{
				c.addItem(1061, 1);
				c.addSkillXP(3, 10);
				c.Send("You catch leather boots.");
			}
			else if (WhatFish == 1059)
			{
				c.addItem(1059, 1);
				c.addSkillXP(3, 10);
				c.Send("You catch leather gloves.");
			}
			CatchBigNet(ID);
			break;
		case 12: //Big net and bass
			if (WhatFish == 405)
			{
				c.addItem(405, 1);
				c.addSkillXP(30, 10);
				c.Send("You catch a casket.");
			}
			else if (WhatFish == 407)
			{
				c.addItem(407, 1);
				c.addSkillXP(30, 10);
				c.Send("You catch an oyster.");
			}
			else if (WhatFish == 353)
			{
				c.addItem(353, 1);
				c.addSkillXP(50, 10);
				c.Send("You catch a mackerel.");
			}
			else if (WhatFish == 401)
			{
				c.addItem(401, 1);
				c.addSkillXP(3, 10);
				c.Send("You catch seaweed.");
			}
			else if (WhatFish == 1061)
			{
				c.addItem(1061, 1);
				c.addSkillXP(3, 10);
				c.Send("You catch leather boots.");
			}
			else if (WhatFish == 1059)
			{
				c.addItem(1059, 1);
				c.addSkillXP(3, 10);
				c.Send("You catch leather gloves.");
			}
			else if (WhatFish == 341)
			{
				c.addItem(341, 1);
				c.addSkillXP(244, 10);
				c.Send("You catch a cod.");
			}
			else if (WhatFish == 363)
			{
				c.addItem(363, 1);
				c.addSkillXP(300, 10);
				c.Send("You catch a bass.");
			}
			CatchBigNetAndBass(ID);
			break;
		case 13: //Big net and cod
			if (WhatFish == 405)
			{
				c.addItem(405, 1);
				c.addSkillXP(30, 10);
				c.Send("You catch a casket.");
			}
			else if (WhatFish == 407)
			{
				c.addItem(407, 1);
				c.addSkillXP(30, 10);
				c.Send("You catch an oyster.");
			}
			else if (WhatFish == 353)
			{
				c.addItem(353, 1);
				c.addSkillXP(50, 10);
				c.Send("You catch a mackerel.");
			}
			else if (WhatFish == 401)
			{
				c.addItem(401, 1);
				c.addSkillXP(3, 10);
				c.Send("You catch seaweed.");
			}
			else if (WhatFish == 1061)
			{
				c.addItem(1061, 1);
				c.addSkillXP(3, 10);
				c.Send("You catch leather boots.");
			}
			else if (WhatFish == 1059)
			{
				c.addItem(1059, 1);
				c.addSkillXP(3, 10);
				c.Send("You catch leather gloves.");
			}
			else if (WhatFish == 341)
			{
				c.addItem(341, 1);
				c.addSkillXP(244, 10);
				c.Send("You catch a cod.");
			}
			CatchBigNetAndCod(ID);
			break;
		case 14: //Shark or Manta Ray
			int whichFish = Misc.random(20);
			if (whichFish <= 14) {
				c.addItem(383, 1);
				c.addSkillXP(2500, 10);
				c.Send("You catch a shark.");
			} else {
				c.addItem(389, 1);
				c.addSkillXP(3000, 10);
				c.Send("You catch a manta ray.");
			}
			CatchShark(ID);
			break;
		}
	}
}