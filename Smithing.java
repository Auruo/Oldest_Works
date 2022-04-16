class Smithing {
	
	public void iron(Client c) {
		if (Misc.random(1) == 0) {
			if (c.clickCount == 0) {
				c.addItem(2351, 1);
				c.delete(440, 1);
				c.Send("You make an iron bar.");
				c.addSkillXP((25 * c.playerLevel[13]), 13);
				c.clickCount = 8;
				c.anim(0x383);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			}
		} else {
			c.delete(440, 1);
			c.Send("You fail to make an iron bar.");
			c.addSkillXP((5 * c.playerLevel[13]), 13);
			c.clickCount = 6;
			c.anim(0x383);
			c.updateRequired = true;
			c.appearanceUpdateRequired = true;
		}
	}
	public void rune(Client c) {
		if (c.hasAmount(454, 8) && c.hasItem(451)) {
			if (c.clickCount == 0) {
				c.addItem(2363, 1);
				c.Send("You make a rune bar.");
				c.delete(451, 1);
				c.delete(454, 8);
				c.addSkillXP((45 * c.playerLevel[13]), 13);
				c.clickCount = 9;
				c.anim(0x383);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			}
		} else {
			c.Send("You need 1 runite ore and 8 (noted) coal to make a rune bar.");
			c.Send("(Noted) meaning withdraw coal as a note from the bank then smelt.");
		}
	}
	public void adam(Client c) {
		if (c.hasAmount(454, 6) && c.hasItem(449)) {
			if (c.clickCount == 0) {
				c.addItem(2361, 1);
				c.Send("You make an adamant bar.");
				c.delete(449, 1);
				c.delete(454, 6);
				c.addSkillXP((40 * c.playerLevel[13]), 13);
				c.clickCount = 8;
				c.anim(0x383);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			}
		} else {
			c.Send("You need 1 adamant ore and 6 (noted) coal to make an adamant bar.");
			c.Send("(Noted) meaning withdraw coal as a note from the bank then smelt.");
		}
	}
	public void mith(Client c) {
		if (c.hasAmount(454, 4) && c.hasItem(447)) {
			if (c.clickCount == 0) {
				c.addItem(2359, 1);
				c.Send("You make a mith bar.");
				c.delete(447, 1);
				c.delete(454, 4);
				c.addSkillXP((35 * c.playerLevel[13]), 13);
				c.clickCount = 7;
				c.anim(0x383);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			}
		} else {
			c.Send("You need 1 mith ore and 4 (noted) coal ore to make a mith bar.");
			c.Send("(Noted) meaning withdraw coal as a note from the bank then smelt.");
		}
	}

	public void coal(Client c) {
		if (c.hasAmount(454, 2) && c.hasItem(440)) {
			if (c.clickCount == 0) {
				c.addItem(2353, 1);
				c.Send("You make a steel bar.");
				c.delete(440, 1);
				c.delete(454, 2);
				c.addSkillXP((30 * c.playerLevel[13]), 13);
				c.clickCount = 6;
				c.anim(0x383);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			}
		} else {
			c.Send("You need 1 iron ore and 2 (noted) coal ore to make a steel bar.");
			c.Send("(Noted) meaning withdraw coal as a note from the bank then smelt.");
		}
	}

	public void copper(Client c) {
		if (c.hasItem(436) && c.hasItem(438)) {
			if (c.clickCount == 0) {
				c.addItem(2349, 1);
				c.Send("You make a Bronze bar.");
				c.delete(438, 1);
				c.delete(436, 1);
				c.addSkillXP((20 * c.playerLevel[13]), 13);
				c.clickCount = 6;
				c.anim(0x383);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			}
		} else {
			c.Send("You need 1 copper ore and 1 tin ore to make a bronze bar.");
		}
	}
	
	public void tin(Client c) {
		if (c.hasItem(436) && c.hasItem(438)) {
			if (c.clickCount == 0) {
				c.addItem(2349, 1);
				c.Send("You make a Bronze bar.");
				c.delete(438, 1);
				c.delete(436, 1);
				c.addSkillXP((20 * c.playerLevel[13]), 13);
				c.clickCount = 6;
				c.anim(0x383);
				c.updateRequired = true;
				c.appearanceUpdateRequired = true;
			}
		} else {
			c.Send("You need 1 copper ore and 1 tin ore to make a bronze bar.");
		}
	}
	public int removeBar(int removeID, Client c) {
		if (removeID == 1205 || removeID == 1351 || removeID == 1103
				|| removeID == 1139 || removeID == 819 || removeID == 1277
				|| removeID == 1422 || removeID == 1075 || removeID == 1155
				|| removeID == 39 || removeID == 1321 || removeID == 1337
				|| removeID == 1087 || removeID == 1173 || removeID == 864
				|| removeID == 1291 || removeID == 1375 || removeID == 1117
				|| removeID == 1189 || removeID == 1307 || removeID == 3095
				|| removeID == 4819) {
			return 2349;
		}
		if (removeID == 1203 || removeID == 1349 || removeID == 1420
				|| removeID == 1137 || removeID == 1279 || removeID == 820
				|| removeID == 4820 || removeID == 1323 || removeID == 40
				|| removeID == 1293 || removeID == 1153 || removeID == 863
				|| removeID == 1175 || removeID == 1335 || removeID == 1363
				|| removeID == 1101 || removeID == 4540 || removeID == 1191
				|| removeID == 3096 || removeID == 1309 || removeID == 1067
				|| removeID == 1081 || removeID == 1115) {
			return 2351;
		}
		if (removeID == 1207 || removeID == 3097 || removeID == 1353
				|| removeID == 1424 || removeID == 1141 || removeID == 1281
				|| removeID == 1325 || removeID == 1295 || removeID == 1157
				|| removeID == 1177 || removeID == 1339 || removeID == 1365
				|| removeID == 1105 || removeID == 1193 || removeID == 1069
				|| removeID == 1083 || removeID == 1311 || removeID == 1119
				|| removeID == 1539 || removeID == 821 || removeID == 41
				|| removeID == 2 || removeID == 2370 || removeID == 865
				|| removeID == 4544) {
			return 2353;
		}
		// Mith
		if (removeID == 1209 || removeID == 3099 || removeID == 1355
				|| removeID == 1428 || removeID == 1143 || removeID == 1285
				|| removeID == 1329 || removeID == 1299 || removeID == 1159
				|| removeID == 1181 || removeID == 1343 || removeID == 1369
				|| removeID == 1109 || removeID == 1197 || removeID == 1071
				|| removeID == 1085 || removeID == 1315 || removeID == 1121
				|| removeID == 822 || removeID == 4822 || removeID == 42
				|| removeID == 42 || removeID == 866) {
			return 2359;
		}
		// Addy
		if (removeID == 1211 || removeID == 3100 || removeID == 1430
				|| removeID == 1145 || removeID == 1287 || removeID == 1331
				|| removeID == 1301 || removeID == 1161 || removeID == 1183
				|| removeID == 1371 || removeID == 1111 || removeID == 1073
				|| removeID == 1091 || removeID == 1317 || removeID == 1123
				|| removeID == 823 || removeID == 4823 || removeID == 43
				|| removeID == 867 || removeID == 1199) {
			return 2361;
		}
		// Rune
		if (removeID == 1213 || removeID == 3101 || removeID == 1432
				|| removeID == 1147 || removeID == 1289 || removeID == 1333
				|| removeID == 1303 || removeID == 1163 || removeID == 1185
				|| removeID == 1347 || removeID == 1373 || removeID == 1113
				|| removeID == 1201 || removeID == 1079 || removeID == 1093
				|| removeID == 1319 || removeID == 1127 || removeID == 824
				|| removeID == 4824 || removeID == 44 || removeID == 868) {
			return 2363;
		}
		return 0;
	}

	public int barsNeeded(int slot, int column, Client c) {
		if (column == 1119) {
			if (slot == 0 || slot == 1) {
				return 1;
			}
			if (slot == 2 || slot == 3) {
				return 2;
			}
			if (slot == 4) {
				return 3;
			}
		}
		if (column == 1120) {
			if (slot == 0 || slot == 1) {
				return 1;
			}
			if (slot == 2 || slot == 3) {
				return 3;
			}
			if (slot == 4) {
				return 2;
			}
		}
		if (column == 1121) {
			if (slot == 0 || slot == 1 || slot == 2) {
				return 3;
			}
			if (slot == 3) {
				return 5;
			}
			if (slot == 4) {
				return 1;
			}
		}
		if (column == 1122) {
			if (slot == 0 || slot == 4) {
				return 1;
			}
			if (slot == 1 || slot == 2) {
				return 2;
			}
			if (slot == 3) {
				return 3;
			}
		}
		if (column == 1123) {
			if (slot == 0 || slot == 1 || slot == 2 || slot == 3 || slot == 4) {
				return 1;
			}
		}
		return 0;
	}

	public int barsNeeded2(int slot, int column, Client c) {
		if (column == 1119) {
			if (slot == 0 || slot == 1) {
				return 5;
			}
			if (slot == 2 || slot == 3) {
				return 10;
			}
			if (slot == 4) {
				return 15;
			}
		}
		if (column == 1120) {
			if (slot == 0 || slot == 1) {
				return 5;
			}
			if (slot == 2 || slot == 3) {
				return 15;
			}
			if (slot == 4) {
				return 10;
			}
		}
		if (column == 1121) {
			if (slot == 0 || slot == 1 || slot == 2) {
				return 15;
			}
			if (slot == 3) {
				return 25;
			}
			if (slot == 4) {
				return 5;
			}
		}
		if (column == 1122) {
			if (slot == 0 || slot == 4) {
				return 5;
			}
			if (slot == 1 || slot == 2) {
				return 10;
			}
			if (slot == 3) {
				return 15;
			}
		}
		if (column == 1123) {
			if (slot == 0 || slot == 1 || slot == 2 || slot == 3 || slot == 4) {
				return 5;
			}
		}
		return 0;
	}

	public int barsNeeded3(int slot, int column, Client c) {
		if (column == 1119) {
			if (slot == 0 || slot == 1) {
				return 10;
			}
			if (slot == 2 || slot == 3) {
				return 20;
			}
			if (slot == 4) {
				return 30;
			}
		}
		if (column == 1120) {
			if (slot == 0 || slot == 1) {
				return 10;
			}
			if (slot == 2 || slot == 3) {
				return 30;
			}
			if (slot == 4) {
				return 20;
			}
		}
		if (column == 1121) {
			if (slot == 0 || slot == 1 || slot == 2) {
				return 30;
			}
			if (slot == 3) {
				return 50;
			}
			if (slot == 4) {
				return 10;
			}
		}
		if (column == 1122) {
			if (slot == 0 || slot == 4) {
				return 10;
			}
			if (slot == 1 || slot == 2) {
				return 20;
			}
			if (slot == 3) {
				return 30;
			}
		}
		if (column == 1123) {
			if (slot == 0 || slot == 1 || slot == 2 || slot == 3 || slot == 4) {
				return 10;
			}
		}
		return 0;
	}

	public int smithXP(int barType, int barAmount, Client c) {
		if (barType == 2349) {
			return barAmount * 100;
		}
		if (barType == 2351) {
			return barAmount * 250;
		}
		if (barType == 2353) {
			return barAmount * 600;
		}
		if (barType == 2359) {
			return barAmount * 2000;
		}
		if (barType == 2361) {
			return barAmount * 7000;
		}
		if (barType == 2363) {
			return barAmount * 17500;
		}
		c.anim(898);
		return 0;
	}

	public boolean canSmith(int item, Client c) {
		if (item == 1205 || item == 1351 && c.playerLevel[13] >= 1) {
			return true;
		}
		if (item == 1422 && c.playerLevel[13] >= 2) {
			return true;
		}
		if (item == 1139 && c.playerLevel[13] >= 3) {
			return true;
		}
		if (item == 1277 || item == 819 && c.playerLevel[13] >= 4) {
			return true;
		}
		if (item == 1321 || item == 39 && c.playerLevel[13] >= 5) {
			return true;
		}
		if (item == 1291 && c.playerLevel[13] >= 6) {
			return true;
		}
		if (item == 1155 || item == 864 && c.playerLevel[13] >= 7) {
			return true;
		}
		if (item == 1173 && c.playerLevel[13] >= 8) {
			return true;
		}
		if (item == 1337 && c.playerLevel[13] >= 9) {
			return true;
		}
		if (item == 1375 && c.playerLevel[13] >= 10) {
			return true;
		}
		if (item == 1103 && c.playerLevel[13] >= 11) {
			return true;
		}
		if (item == 1189 && c.playerLevel[13] >= 12) {
			return true;
		}
		if (item == 3095 && c.playerLevel[13] >= 13) {
			return true;
		}
		if (item == 1307 && c.playerLevel[13] >= 14) {
			return true;
		}
		if (item == 1203 && c.playerLevel[13] >= 15) {
			return true;
		}
		if (item == 1087 || item == 1075
				|| item == 1349 && c.playerLevel[13] >= 16) {
			return true;
		}
		if (item == 1420 && c.playerLevel[13] >= 17) {
			return true;
		}
		if (item == 1117 || item == 1137 && c.playerLevel[13] >= 18) {
			return true;
		}
		if (item == 1279 || item == 820 || item == 4820 && c.playerLevel[13] >= 19) {
			return true;
		}
		if (item == 1323 || item == 40 && c.playerLevel[13] >= 20) {
			return true;
		}
		if (item == 1293 && c.playerLevel[13] >= 21) {
			return true;
		}
		if (item == 1153 || item == 863 && c.playerLevel[13] >= 22) {
			return true;
		}
		if (item == 1175 && c.playerLevel[13] >= 23) {
			return true;
		}
		if (item == 1335 && c.playerLevel[13] >= 24) {
			return true;
		}
		if (item == 1363 && c.playerLevel[13] >= 25) {
			return true;
		}
		if (item == 1101 || item == 4540 && c.playerLevel[13] >= 26) {
			return true;
		}
		if (item == 1191 && c.playerLevel[13] >= 27) {
			return true;
		}
		if (item == 3096 && c.playerLevel[13] >= 28) {
			return true;
		}
		if (item == 1309 && c.playerLevel[13] >= 29) {
			return true;
		}
		if (item == 1207 && c.playerLevel[13] >= 30) {
			return true;
		}
		if (item == 1067 || item == 1081
				|| item == 1353 && c.playerLevel[13] >= 31) {
			return true;
		}
		if (item == 1424 && c.playerLevel[13] >= 32) {
			return true;
		}
		if (item == 1115 || item == 1141 && c.playerLevel[13] >= 33) {
			return true;
		}
		if (item == 1281 || item == 1539 || item == 821 && c.playerLevel[13] >= 34) {
			return true;
		}
		if (item == 1325 || item == 41 && c.playerLevel[13] >= 35) {
			return true;
		}
		if (item == 1295 || item == 2370 && c.playerLevel[13] >= 36) {
			return true;
		}
		if (item == 1157 || item == 865 && c.playerLevel[13] >= 37) {
			return true;
		}
		if (item == 1177 && c.playerLevel[13] >= 38) {
			return true;
		}
		if (item == 1339 && c.playerLevel[13] >= 39) {
			return true;
		}
		if (item == 1365 && c.playerLevel[13] >= 40) {
			return true;
		}
		if (item == 1105 && c.playerLevel[13] >= 41) {
			return true;
		}
		if (item == 1193 && c.playerLevel[13] >= 42) {
			return true;
		}
		if (item == 3097 && c.playerLevel[13] >= 43) {
			return true;
		}
		if (item == 1311 && c.playerLevel[13] >= 44) {
			return true;
		}
		if (item == 1069 || item == 1083 && c.playerLevel[13] >= 46) {
			return true;
		}
		if (item == 1119 && c.playerLevel[13] >= 48) {
			return true;
		}
		if (item == 4544 && c.playerLevel[13] >= 49) {
			return true;
		}
		if (item == 1209 && c.playerLevel[13] >= 50) {
			return true;
		}
		if (item == 1355 && c.playerLevel[13] >= 51) {
			return true;
		}
		if (item == 1428 && c.playerLevel[13] >= 52) {
			return true;
		}
		if (item == 1143 && c.playerLevel[13] >= 53) {
			return true;
		}
		if (item == 1285 || item == 822 || item == 4822 && c.playerLevel[13] >= 54) {
			return true;
		}
		if (item == 1329 || item == 42 && c.playerLevel[13] >= 55) {
			return true;
		}
		if (item == 1299 && c.playerLevel[13] >= 56) {
			return true;
		}
		if (item == 1159 || item == 866 && c.playerLevel[13] >= 57) {
			return true;
		}
		if (item == 1181 && c.playerLevel[13] >= 58) {
			return true;
		}
		if (item == 1343 && c.playerLevel[13] >= 59) {
			return true;
		}
		if (item == 1369 && c.playerLevel[13] >= 60) {
			return true;
		}
		if (item == 1109 && c.playerLevel[13] >= 61) {
			return true;
		}
		if (item == 1197 && c.playerLevel[13] >= 62) {
			return true;
		}
		if (item == 3099 && c.playerLevel[13] >= 63) {
			return true;
		}
		if (item == 1315 && c.playerLevel[13] >= 64) {
			return true;
		}
		if (item == 1071 || item == 1085 && c.playerLevel[13] >= 66) {
			return true;
		}
		if (item == 1121 && c.playerLevel[13] >= 68) {
			return true;
		}
		if (item == 1211 && c.playerLevel[13] >= 70) {
			return true;
		}
		if (item == 1430 && c.playerLevel[13] >= 72) {
			return true;
		}
		if (item == 1145 && c.playerLevel[13] >= 73) {
			return true;
		}
		if (item == 1287 || item == 823 || item == 4823 && c.playerLevel[13] >= 74) {
			return true;
		}
		if (item == 1331 || item == 43 && c.playerLevel[13] >= 75) {
			return true;
		}
		if (item == 1301 && c.playerLevel[13] >= 76) {
			return true;
		}
		if (item == 1161 || item == 867 && c.playerLevel[13] >= 77) {
			return true;
		}
		if (item == 1183 && c.playerLevel[13] >= 78) {
			return true;
		}
		if (item == 1371 && c.playerLevel[13] >= 79) {
			return true;
		}
		if (item == 1111 && c.playerLevel[13] >= 81) {
			return true;
		}
		if (item == 1199 && c.playerLevel[13] >= 82) {
			return true;
		}
		if (item == 3100 && c.playerLevel[13] >= 83) {
			return true;
		}
		if (item == 1317 && c.playerLevel[13] >= 84) {
			return true;
		}
		if (item == 1213 && c.playerLevel[13] >= 85) {
			return true;
		}
		if (item == 1073 || item == 1091
				|| item == 1359 && c.playerLevel[13] >= 86) {
			return true;
		}
		if (item == 1432 && c.playerLevel[13] >= 87) {
			return true;
		}
		if (item == 1123 || item == 1147 && c.playerLevel[13] >= 88) {
			return true;
		}
		if (item == 1289 || item == 824 || item == 4824 && c.playerLevel[13] >= 89) {
			return true;
		}
		if (item == 1333 || item == 44 && c.playerLevel[13] >= 90) {
			return true;
		}
		if (item == 1303 && c.playerLevel[13] >= 91) {
			return true;
		}
		if (item == 1163 || item == 868 && c.playerLevel[13] >= 92) {
			return true;
		}
		if (item == 1185 && c.playerLevel[13] >= 93) {
			return true;
		}
		if (item == 1347 && c.playerLevel[13] >= 94) {
			return true;
		}
		if (item == 1373 && c.playerLevel[13] >= 95) {
			return true;
		}
		if (item == 1113 && c.playerLevel[13] >= 96) {
			return true;
		}
		if (item == 1201 && c.playerLevel[13] >= 97) {
			return true;
		}
		if (item == 3101 && c.playerLevel[13] >= 98) {
			return true;
		}
		if (item == 1319 && c.playerLevel[13] >= 99) {
			return true;
		}
		if (item == 1079 && c.playerLevel[13] >= 99) {
			return true;
		}
		if (item == 1079 || item == 1093 || item == 1319
				|| item == 1127 && c.playerLevel[13] >= 99) {
			return true;
		} else {
			return false;
		}
		// return false;
	}

	public void addItemToSmith(int id, int slot, int column, int amount, Client c) {
		c.outStream.createFrameVarSizeWord(34); // init item to smith screen
		c.outStream.writeWord(column); // Column Across Smith Screen
		c.outStream.writeByte(4); // Total Rows?
		c.outStream.writeDWord(slot); // Row Down The Smith Screen
		c.outStream.writeWord(id + 1); // item
		c.outStream.writeByte(amount); // how many there are?
		c.outStream.endFrameVarSizeWord();
	}

	public void initSmithing(int barType, Client c) {
		c.outStream.createFrame(97);
		c.outStream.writeWord(994);
		if (c.amountOfItem(barType) < 5) {
			c.sendString("@red@5bars@red@", 1112);
		} else {
			c.sendString("@gre@5bars@gre@", 1112);
		}
		if (c.amountOfItem(barType) < 3) {
			c.sendString("@red@3bars@red@", 1109);
			c.sendString("@red@3bars@red@", 1110);
			c.sendString("@red@3bars@red@", 1118);
			c.sendString("@red@3bars@red@", 1111);
			c.sendString("@red@3bars@red@", 1095);
			c.sendString("@red@3bars@red@", 1115);
			c.sendString("@red@3bars@red@", 1090);
		} else {
			c.sendString("@gre@3bars@gre@", 1109);
			c.sendString("@gre@3bars@gre@", 1110);
			c.sendString("@gre@3bars@gre@", 1118);
			c.sendString("@gre@3bars@gre@", 1111);
			c.sendString("@gre@3bars@gre@", 1095);
			c.sendString("@gre@3bars@gre@", 1115);
			c.sendString("@gre@3bars@gre@", 1090);
		}
		if (c.amountOfItem(barType) < 2) {
			c.sendString("@red@2bars@red@", 1113);
			c.sendString("@red@2bars@red@", 1116);
			c.sendString("@red@2bars@red@", 1114);
			c.sendString("@red@2bars@red@", 1089);
			c.sendString("@red@2bars@red@", 8428);
		} else {
			c.sendString("@gre@2bars@gre@", 1113);
			c.sendString("@gre@2bars@gre@", 1116);
			c.sendString("@gre@2bars@gre@", 1114);
			c.sendString("@gre@2bars@gre@", 1089);
			c.sendString("@gre@2bars@gre@", 8428);
		}
		if (c.amountOfItem(barType) < 1) {
			c.sendString("@red@1bar@red@", 1125);
			c.sendString("@red@1bar@red@", 1126);
			c.sendString("@red@1bar@red@", 1127);
			c.sendString("@red@1bar@red@", 1124);
			c.sendString("@red@1bar@red@", 1128);
			c.sendString("@red@1bar@red@", 1129);
			c.sendString("@red@1bar@red@", 1130);
			c.sendString("@red@1bar@red@", 13357);
			c.sendString("@red@1bar@red@", 1131);
			c.sendString("@red@1bar@red@", 11459);
		} else {
			c.sendString("@gre@1bar@gre@", 1125);
			c.sendString("@gre@1bar@gre@", 1126);
			c.sendString("@gre@1bar@gre@", 1127);
			c.sendString("@gre@1bar@gre@", 1124);
			c.sendString("@gre@1bar@gre@", 1128);
			c.sendString("@gre@1bar@gre@", 1129);
			c.sendString("@gre@1bar@gre@", 1130);
			c.sendString("@gre@1bar@gre@", 13357);
			c.sendString("@gre@1bar@gre@", 1131);
			c.sendString("@gre@1bar@gre@", 11459);
		}
		if (barType == 2349) { // Bronze
			if (c.playerLevel[13] < 18) {
				c.sendString("@bla@Plate body@bla@", 1101);
			} else {
				c.sendString("@whi@Plate body@whi@", 1101);
			}
			if (c.playerLevel[13] < 16) {
				c.sendString("@bla@Plate legs@bla@", 1099);
				c.sendString("@bla@Plate skirt@bla@", 1100);
			} else {
				c.sendString("@whi@Plate legs@whi@", 1099);
				c.sendString("@whi@Plate skirt@whi@", 1100);
			}
			if (c.playerLevel[13] < 14) {
				c.sendString("@bla@2 hand sword@bla@", 1088);
			} else {
				c.sendString("@whi@2 hand sword@whi@", 1088);
			}
			if (c.playerLevel[13] < 13) {
				c.sendString("@bla@Claws@bla@", 8429);
			} else {
				c.sendString("@whi@Claws@whi@", 8429);
			}
			if (c.playerLevel[13] < 12) {
				c.sendString("@bla@Kite shield@bla@", 1105);
			} else {
				c.sendString("@whi@Kite shield@whi@", 1105);
			}
			if (c.playerLevel[13] < 11) {
				c.sendString("@bla@Chain body@bla@", 1098);
			} else {
				c.sendString("@whi@Chain body@whi@", 1098);
			}
			if (c.playerLevel[13] < 10) {
				c.sendString("@bla@Battle axe@bla@", 1092);
			} else {
				c.sendString("@whi@Battle axe@whi@", 1092);
			}
			if (c.playerLevel[13] < 9) {
				c.sendString("@bla@Warhammer@bla@", 1083);
			} else {
				c.sendString("@whi@Warhammer@whi@", 1083);
			}
			if (c.playerLevel[13] < 8) {
				c.sendString("@bla@Square shield@bla@", 1104);
			} else {
				c.sendString("@whi@Square shield@whi@", 1104);
			}
			if (c.playerLevel[13] < 7) {
				c.sendString("@bla@Full helm@bla@", 1103);
				c.sendString("@bla@Throwing knives@bla@", 1106);
			} else {
				c.sendString("@whi@Full helm@whi@", 1103);
				c.sendString("@whi@Throwing knives@whi@", 1106);
			}
			if (c.playerLevel[13] < 6) {
				c.sendString("@bla@Long sword@bla@", 1086);
			} else {
				c.sendString("@whi@Long sword@whi@", 1086);
			}
			if (c.playerLevel[13] < 5) {
				c.sendString("@bla@Scimitar@bla@", 1087);
				c.sendString("@bla@Arrowtips@bla@", 1108);
			} else {
				c.sendString("@whi@Scimitar@whi@", 1087);
				c.sendString("@whi@Arrowtips@whi@", 1108);
			}
			if (c.playerLevel[13] < 4) {
				c.sendString("@bla@Sword@bla@", 1085);
				c.sendString("@bla@Dart tips@bla@", 1107);
				c.sendString("@bla@Nails@bla@", 13358);
			} else {
				c.sendString("@whi@Sword@whi@", 1085);
				c.sendString("@whi@Dart tips@whi@", 1107);
				c.sendString("@whi@Nails@whi@", 13358);
			}
			if (c.playerLevel[13] < 3) {
				c.sendString("@bla@Medium helm@bla@", 1102);
			} else {
				c.sendString("@whi@Medium helm@whi@", 1102);
			}
			if (c.playerLevel[13] < 2) {
				c.sendString("@bla@Mace@bla@", 1093);
			} else {
				c.sendString("@whi@Mace@whi@", 1093);
			}
			if (c.playerLevel[13] < 1) {
				c.sendString("@bla@Dagger@bla@", 1094);
				c.sendString("@bla@Axe@bla@", 1091);
			} else {
				c.sendString("@whi@Dagger@whi@", 1094);
				c.sendString("@whi@Axe@whi@", 1091);
			}
			addItemToSmith(1205, 0, 1119, 1, c);
			addItemToSmith(1351, 0, 1120, 1, c);
			addItemToSmith(1103, 0, 1121, 1, c);
			addItemToSmith(1139, 0, 1122, 1, c);
			addItemToSmith(819, 0, 1123, 10, c);
			addItemToSmith(1277, 1, 1119, 1, c);
			addItemToSmith(1422, 1, 1120, 1, c);
			addItemToSmith(1075, 1, 1121, 1, c);
			addItemToSmith(1155, 1, 1122, 1, c);
			addItemToSmith(39, 1, 1123, 15, c);
			addItemToSmith(1321, 2, 1119, 1, c);
			addItemToSmith(1337, 2, 1120, 1, c);
			addItemToSmith(1087, 2, 1121, 1, c);
			addItemToSmith(1173, 2, 1122, 1, c);
			addItemToSmith(864, 2, 1123, 5, c);
			addItemToSmith(1291, 3, 1119, 1, c);
			addItemToSmith(1375, 3, 1120, 1, c);
			addItemToSmith(1117, 3, 1121, 1, c);
			addItemToSmith(1189, 3, 1122, 1, c);
			// addItemToSmith(0,3,1123, c);
			c.sendString("", 1132);
			c.sendString("", 1096);
			addItemToSmith(1307, 4, 1119, 1, c);
			addItemToSmith(3095, 4, 1120, 1, c);
			// addItemToSmith(1307,4,1121, c);
			c.sendString("", 11459);
			c.sendString("", 11461);
			addItemToSmith(4819, 4, 1122, 1, c);
			// addItemToSmith(1307,4,1123, c);
			c.sendString("", 1135);
			c.sendString("", 1134);
		}
		if (barType == 2351) { // Iron
			if (c.playerLevel[13] < 33) {
				c.sendString("@bla@Plate body@bla@", 1101);
			} else {
				c.sendString("@whi@Plate body@whi@", 1101);
			}
			if (c.playerLevel[13] < 31) {
				c.sendString("@bla@Plate legs@bla@", 1099);
				c.sendString("@bla@Plate skirt@bla@", 1100);
			} else {
				c.sendString("@whi@Plate legs@whi@", 1099);
				c.sendString("@whi@Plate skirt@whi@", 1100);
			}
			if (c.playerLevel[13] < 29) {
				c.sendString("@bla@2 hand sword@bla@", 1088);
			} else {
				c.sendString("@whi@2 hand sword@whi@", 1088);
			}
			if (c.playerLevel[13] < 28) {
				c.sendString("@bla@Claws@bla@", 8429);
			} else {
				c.sendString("@whi@Claws@whi@", 8429);
			}
			if (c.playerLevel[13] < 27) {
				c.sendString("@bla@Kite shield@bla@", 1105);
			} else {
				c.sendString("@whi@Kite shield@whi@", 1105);
			}
			if (c.playerLevel[13] < 26) {
				c.sendString("@bla@Chain body@bla@", 1098);
				c.sendString("@bla@Oil lantern frame@bla@", 11461);
			} else {
				c.sendString("@whi@Chain body@whi@", 1098);
				c.sendString("@whi@Oil lantern frame@whi@", 11461);
			}
			if (c.playerLevel[13] < 25) {
				c.sendString("@bla@Battle axe@bla@", 1092);
			} else {
				c.sendString("@whi@Battle axe@whi@", 1092);
			}
			if (c.playerLevel[13] < 24) {
				c.sendString("@bla@Warhammer@bla@", 1083);
			} else {
				c.sendString("@whi@Warhammer@whi@", 1083);
			}
			if (c.playerLevel[13] < 23) {
				c.sendString("@bla@Square shield@bla@", 1104);
			} else {
				c.sendString("@whi@Square shield@whi@", 1104);
			}
			if (c.playerLevel[13] < 22) {
				c.sendString("@bla@Full helm@bla@", 1103);
				c.sendString("@bla@Throwing knives@bla@", 1106);
			} else {
				c.sendString("@whi@Full helm@whi@", 1103);
				c.sendString("@whi@Throwing knives@whi@", 1106);
			}
			if (c.playerLevel[13] < 21) {
				c.sendString("@bla@Long sword@bla@", 1086);
			} else {
				c.sendString("@whi@Long sword@whi@", 1086);
			}
			if (c.playerLevel[13] < 20) {
				c.sendString("@bla@Scimitar@bla@", 1087);
				c.sendString("@bla@Arrowtips@bla@", 1108);
			} else {
				c.sendString("@whi@Scimitar@whi@", 1087);
				c.sendString("@whi@Arrowtips@whi@", 1108);
			}
			if (c.playerLevel[13] < 19) {
				c.sendString("@bla@Sword@bla@", 1085);
				c.sendString("@bla@Dart tips@bla@", 1107);
				c.sendString("@bla@Nails@bla@", 13358);
			} else {
				c.sendString("@whi@Sword@whi@", 1085);
				c.sendString("@whi@Dart tips@whi@", 1107);
				c.sendString("@whi@Nails@whi@", 13358);
			}
			if (c.playerLevel[13] < 18) {
				c.sendString("@bla@Medium helm@bla@", 1102);
			} else {
				c.sendString("@whi@Medium helm@whi@", 1102);
			}
			if (c.playerLevel[13] < 17) {
				c.sendString("@bla@Mace@bla@", 1093);
			} else {
				c.sendString("@whi@Mace@whi@", 1093);
			}
			if (c.playerLevel[13] < 16) {
				c.sendString("@bla@Axe@bla@", 1091);
			} else {
				c.sendString("@whi@Axe@whi@", 1091);
			}
			if (c.playerLevel[13] < 15) {
				c.sendString("@bla@Dagger@bla@", 1094);
			} else {
				c.sendString("@whi@Dagger@whi@", 1094);
			}
			addItemToSmith(1203, 0, 1119, 1, c);
			addItemToSmith(1349, 0, 1120, 1, c);
			addItemToSmith(1101, 0, 1121, 1, c);
			addItemToSmith(1137, 0, 1122, 1, c);
			addItemToSmith(820, 0, 1123, 10, c);
			addItemToSmith(1279, 1, 1119, 1, c);
			addItemToSmith(1420, 1, 1120, 1, c);
			addItemToSmith(1067, 1, 1121, 1, c);
			addItemToSmith(1153, 1, 1122, 1, c);
			addItemToSmith(40, 1, 1123, 15, c);
			addItemToSmith(1323, 2, 1119, 1, c);
			addItemToSmith(1335, 2, 1120, 1, c);
			addItemToSmith(1081, 2, 1121, 1, c);
			addItemToSmith(1175, 2, 1122, 1, c);
			addItemToSmith(863, 2, 1123, 5, c);
			addItemToSmith(1293, 3, 1119, 1, c);
			addItemToSmith(1363, 3, 1120, 1, c);
			addItemToSmith(1115, 3, 1121, 1, c);
			addItemToSmith(1191, 3, 1122, 1, c);
			// addItemToSmith(0,3,1123, c);
			c.sendString("", 1132);
			c.sendString("", 1096);
			addItemToSmith(1309, 4, 1119, 1, c);
			addItemToSmith(3096, 4, 1120, 1, c);
			addItemToSmith(4540, 4, 1121, 1, c);
			addItemToSmith(4820, 4, 1122, 1, c);
			// addItemToSmith(1307,4,1123);
			c.sendString("", 1135);
			c.sendString("", 1134);
		}
		if (barType == 2353) { // Steel
			if (c.playerLevel[13] < 49) {
				c.sendString("@bla@Bullseye lantern@bla@", 11461);
			} else {
				c.sendString("@whi@Bullseye lantern@whi@", 11461);
			}
			if (c.playerLevel[13] < 48) {
				c.sendString("@bla@Plate body@bla@", 1101);
			} else {
				c.sendString("@whi@Plate body@whi@", 1101);
			}
			if (c.playerLevel[13] < 46) {
				c.sendString("@bla@Plate legs@bla@", 1099);
				c.sendString("@bla@Plate skirt@bla@", 1100);
			} else {
				c.sendString("@whi@Plate legs@whi@", 1099);
				c.sendString("@whi@Plate skirt@whi@", 1100);
			}
			if (c.playerLevel[13] < 44) {
				c.sendString("@bla@2 hand sword@bla@", 1088);
			} else {
				c.sendString("@whi@2 hand sword@whi@", 1088);
			}
			if (c.playerLevel[13] < 43) {
				c.sendString("@bla@Claws@bla@", 8429);
			} else {
				c.sendString("@whi@Claws@whi@", 8429);
			}
			if (c.playerLevel[13] < 42) {
				c.sendString("@bla@Kite shield@bla@", 1105);
			} else {
				c.sendString("@whi@Kite shield@whi@", 1105);
			}
			if (c.playerLevel[13] < 41) {
				c.sendString("@bla@Chain body@bla@", 1098);
			} else {
				c.sendString("@whi@Chain body@whi@", 1098);
			}
			if (c.playerLevel[13] < 40) {
				c.sendString("@bla@Battle axe@bla@", 1092);
			} else {
				c.sendString("@whi@Battle axe@whi@", 1092);
			}
			if (c.playerLevel[13] < 39) {
				c.sendString("@bla@Warhammer@bla@", 1083);
			} else {
				c.sendString("@whi@Warhammer@whi@", 1083);
			}
			if (c.playerLevel[13] < 38) {
				c.sendString("@bla@Square shield@bla@", 1104);
			} else {
				c.sendString("@whi@Square shield@whi@", 1104);
			}
			if (c.playerLevel[13] < 37) {
				c.sendString("@bla@Full helm@bla@", 1103);
				c.sendString("@bla@Throwing knives@bla@", 1106);
			} else {
				c.sendString("@whi@Full helm@whi@", 1103);
				c.sendString("@whi@Throwing knives@whi@", 1106);
			}
			if (c.playerLevel[13] < 36) {
				c.sendString("@bla@Long sword@bla@", 1086);
				c.sendString("@bla@Studs@bla@", 1134);
			} else {
				c.sendString("@whi@Long sword@whi@", 1086);
				c.sendString("@whi@Studs@whi@", 1134);
			}
			if (c.playerLevel[13] < 35) {
				c.sendString("@bla@Scimitar@bla@", 1087);
				c.sendString("@bla@Arrowtips@bla@", 1108);
			} else {
				c.sendString("@whi@Scimitar@whi@", 1087);
				c.sendString("@whi@Arrowtips@whi@", 1108);
			}
			if (c.playerLevel[13] < 34) {
				c.sendString("@bla@Sword@bla@", 1085);
				c.sendString("@bla@Dart tips@bla@", 1107);
				c.sendString("@bla@Nails@bla@", 13358);
			} else {
				c.sendString("@whi@Sword@whi@", 1085);
				c.sendString("@whi@Dart tips@whi@", 1107);
				c.sendString("@whi@Nails@whi@", 13358);
			}
			if (c.playerLevel[13] < 33) {
				c.sendString("@bla@Medium helm@bla@", 1102);
			} else {
				c.sendString("@whi@Medium helm@whi@", 1102);
			}
			if (c.playerLevel[13] < 32) {
				c.sendString("@bla@Mace@bla@", 1093);
			} else {
				c.sendString("@whi@Mace@whi@", 1093);
			}
			if (c.playerLevel[13] < 31) {
				c.sendString("@bla@Axe@bla@", 1091);
			} else {
				c.sendString("@whi@Axe@whi@", 1091);
			}
			if (c.playerLevel[13] < 30) {
				c.sendString("@bla@Dagger@bla@", 1094);
			} else {
				c.sendString("@whi@Dagger@whi@", 1094);
			}
			addItemToSmith(1207, 0, 1119, 1, c);
			addItemToSmith(1353, 0, 1120, 1, c);
			addItemToSmith(1105, 0, 1121, 1, c);
			addItemToSmith(1141, 0, 1122, 1, c);
			addItemToSmith(821, 0, 1123, 10, c);
			addItemToSmith(1281, 1, 1119, 1, c);
			addItemToSmith(1424, 1, 1120, 1, c);
			addItemToSmith(1069, 1, 1121, 1, c);
			addItemToSmith(1157, 1, 1122, 1, c);
			addItemToSmith(41, 1, 1123, 15, c);
			addItemToSmith(1325, 2, 1119, 1, c);
			addItemToSmith(1339, 2, 1120, 1, c);
			addItemToSmith(1083, 2, 1121, 1, c);
			addItemToSmith(1177, 2, 1122, 1, c);
			addItemToSmith(865, 2, 1123, 5, c);
			addItemToSmith(1295, 3, 1119, 1, c);
			addItemToSmith(1365, 3, 1120, 1, c);
			addItemToSmith(1119, 3, 1121, 1, c);
			addItemToSmith(1193, 3, 1122, 1, c);
			c.sendString("", 1132);
			c.sendString("", 1096);
			addItemToSmith(1311, 4, 1119, 1, c);
			addItemToSmith(3097, 4, 1120, 1, c);
			addItemToSmith(4544, 4, 1121, 1, c);
			addItemToSmith(1539, 4, 1122, 1, c);
			addItemToSmith(2370, 4, 1123, 1, c);
			if (c.amountOfItem(barType) < 1) {
				c.sendString("@red@1bar@red@", 1135);
			} else {
				c.sendString("@gre@1bar@gre@", 1135);
			}
		}
		if (barType == 2359) { // Mith
			if (c.playerLevel[13] < 68) {
				c.sendString("@bla@Plate body@bla@", 1101);
			} else {
				c.sendString("@whi@Plate body@whi@", 1101);
			}
			if (c.playerLevel[13] < 66) {
				c.sendString("@bla@Plate legs@bla@", 1099);
				c.sendString("@bla@Plate skirt@bla@", 1100);
			} else {
				c.sendString("@whi@Plate legs@whi@", 1099);
				c.sendString("@whi@Plate skirt@whi@", 1100);
			}
			if (c.playerLevel[13] < 64) {
				c.sendString("@bla@2 hand sword@bla@", 1088);
			} else {
				c.sendString("@whi@2 hand sword@whi@", 1088);
			}
			if (c.playerLevel[13] < 63) {
				c.sendString("@bla@Claws@bla@", 8429);
			} else {
				c.sendString("@whi@Claws@whi@", 8429);
			}
			if (c.playerLevel[13] < 62) {
				c.sendString("@bla@Kite shield@bla@", 1105);
			} else {
				c.sendString("@whi@Kite shield@whi@", 1105);
			}
			if (c.playerLevel[13] < 61) {
				c.sendString("@bla@Chain body@bla@", 1098);
			} else {
				c.sendString("@whi@Chain body@whi@", 1098);
			}
			if (c.playerLevel[13] < 60) {
				c.sendString("@bla@Battle axe@bla@", 1092);
			} else {
				c.sendString("@whi@Battle axe@whi@", 1092);
			}
			if (c.playerLevel[13] < 59) {
				c.sendString("@bla@Warhammer@bla@", 1083);
			} else {
				c.sendString("@whi@Warhammer@whi@", 1083);
			}
			if (c.playerLevel[13] < 58) {
				c.sendString("@bla@Square shield@bla@", 1104);
			} else {
				c.sendString("@whi@Square shield@whi@", 1104);
			}
			if (c.playerLevel[13] < 57) {
				c.sendString("@bla@Full helm@bla@", 1103);
				c.sendString("@bla@Throwing knives@bla@", 1106);
			} else {
				c.sendString("@whi@Full helm@whi@", 1103);
				c.sendString("@whi@Throwing knives@whi@", 1106);
			}
			if (c.playerLevel[13] < 56) {
				c.sendString("@bla@Long sword@bla@", 1086);
			} else {
				c.sendString("@whi@Long sword@whi@", 1086);
			}
			if (c.playerLevel[13] < 55) {
				c.sendString("@bla@Scimitar@bla@", 1087);
				c.sendString("@bla@Arrowtips@bla@", 1108);
			} else {
				c.sendString("@whi@Scimitar@whi@", 1087);
				c.sendString("@whi@Arrowtips@whi@", 1108);
			}
			if (c.playerLevel[13] < 54) {
				c.sendString("@bla@Sword@bla@", 1085);
				c.sendString("@bla@Dart tips@bla@", 1107);
				c.sendString("@bla@Nails@bla@", 13358);
			} else {
				c.sendString("@whi@Sword@whi@", 1085);
				c.sendString("@whi@Dart tips@whi@", 1107);
				c.sendString("@whi@Nails@whi@", 13358);
			}
			if (c.playerLevel[13] < 53) {
				c.sendString("@bla@Medium helm@bla@", 1102);
			} else {
				c.sendString("@whi@Medium helm@whi@", 1102);
			}
			if (c.playerLevel[13] < 52) {
				c.sendString("@bla@Mace@bla@", 1093);
			} else {
				c.sendString("@whi@Mace@whi@", 1093);
			}
			if (c.playerLevel[13] < 51) {
				c.sendString("@bla@Axe@bla@", 1091);
			} else {
				c.sendString("@whi@Axe@whi@", 1091);
			}
			if (c.playerLevel[13] < 50) {
				c.sendString("@bla@Dagger@bla@", 1094);
			} else {
				c.sendString("@whi@Dagger@whi@", 1094);
			}
			addItemToSmith(1209, 0, 1119, 1, c);
			addItemToSmith(1355, 0, 1120, 1, c);
			addItemToSmith(1109, 0, 1121, 1, c);
			addItemToSmith(1143, 0, 1122, 1, c);
			addItemToSmith(822, 0, 1123, 10, c);
			addItemToSmith(1285, 1, 1119, 1, c);
			addItemToSmith(1355, 1, 1120, 1, c);
			addItemToSmith(1071, 1, 1121, 1, c);
			addItemToSmith(1159, 1, 1122, 1, c);
			addItemToSmith(42, 1, 1123, 15, c);
			addItemToSmith(1329, 2, 1119, 1, c);
			addItemToSmith(1343, 2, 1120, 1, c);
			addItemToSmith(1085, 2, 1121, 1, c);
			addItemToSmith(1181, 2, 1122, 1, c);
			addItemToSmith(866, 2, 1123, 5, c);
			addItemToSmith(1299, 3, 1119, 1, c);
			addItemToSmith(1369, 3, 1120, 1, c);
			addItemToSmith(1121, 3, 1121, 1, c);
			addItemToSmith(1197, 3, 1122, 1, c);
			// addItemToSmith(0,3,1123, c);
			c.sendString("", 1132);
			c.sendString("", 1096);
			addItemToSmith(1315, 4, 1119, 1, c);
			addItemToSmith(3099, 4, 1120, 1, c);
			// addItemToSmith(4540,4,1121,1, c);
			addItemToSmith(4822, 4, 1122, 1, c);
			// addItemToSmith(1307,4,1123, c);
			c.sendString("", 1135);
			c.sendString("", 1134);
			c.sendString("", 11461);
			c.sendString("", 11459);
		}
		if (barType == 2361) { // Addy
			if (c.playerLevel[13] < 88) {
				c.sendString("@bla@Plate body@bla@", 1101);
			} else {
				c.sendString("@whi@Plate body@whi@", 1101);
			}
			if (c.playerLevel[13] < 86) {
				c.sendString("@bla@Plate legs@bla@", 1099);
				c.sendString("@bla@Plate skirt@bla@", 1100);
			} else {
				c.sendString("@whi@Plate legs@whi@", 1099);
				c.sendString("@whi@Plate skirt@whi@", 1100);
			}
			if (c.playerLevel[13] < 84) {
				c.sendString("@bla@2 hand sword@bla@", 1088);
			} else {
				c.sendString("@whi@2 hand sword@whi@", 1088);
			}
			if (c.playerLevel[13] < 83) {
				c.sendString("@bla@Claws@bla@", 8429);
			} else {
				c.sendString("@whi@Claws@whi@", 8429);
			}
			if (c.playerLevel[13] < 82) {
				c.sendString("@bla@Kite shield@bla@", 1105);
			} else {
				c.sendString("@whi@Kite shield@whi@", 1105);
			}
			if (c.playerLevel[13] < 81) {
				c.sendString("@bla@Chain body@bla@", 1098);
			} else {
				c.sendString("@whi@Chain body@whi@", 1098);
			}
			if (c.playerLevel[13] < 80) {
				c.sendString("@bla@Battle axe@bla@", 1092);
			} else {
				c.sendString("@whi@Battle axe@whi@", 1092);
			}
			if (c.playerLevel[13] < 79) {
				c.sendString("@bla@Warhammer@bla@", 1083);
			} else {
				c.sendString("@whi@Warhammer@whi@", 1083);
			}
			if (c.playerLevel[13] < 78) {
				c.sendString("@bla@Square shield@bla@", 1104);
			} else {
				c.sendString("@whi@Square shield@whi@", 1104);
			}
			if (c.playerLevel[13] < 77) {
				c.sendString("@bla@Full helm@bla@", 1103);
				c.sendString("@bla@Throwing knives@bla@", 1106);
			} else {
				c.sendString("@whi@Full helm@whi@", 1103);
				c.sendString("@whi@Throwing knives@whi@", 1106);
			}
			if (c.playerLevel[13] < 76) {
				c.sendString("@bla@Long sword@bla@", 1086);
			} else {
				c.sendString("@whi@Long sword@whi@", 1086);
			}
			if (c.playerLevel[13] < 75) {
				c.sendString("@bla@Scimitar@bla@", 1087);
				c.sendString("@bla@Arrowtips@bla@", 1108);
			} else {
				c.sendString("@whi@Scimitar@whi@", 1087);
				c.sendString("@whi@Arrowtips@whi@", 1108);
			}
			if (c.playerLevel[13] < 74) {
				c.sendString("@bla@Sword@bla@", 1085);
				c.sendString("@bla@Dart tips@bla@", 1107);
				c.sendString("@bla@Nails@bla@", 13358);
			} else {
				c.sendString("@whi@Sword@whi@", 1085);
				c.sendString("@whi@Dart tips@whi@", 1107);
				c.sendString("@whi@Nails@whi@", 13358);
			}
			if (c.playerLevel[13] < 73) {
				c.sendString("@bla@Medium helm@bla@", 1102);
			} else {
				c.sendString("@whi@Medium helm@whi@", 1102);
			}
			if (c.playerLevel[13] < 72) {
				c.sendString("@bla@Mace@bla@", 1093);
			} else {
				c.sendString("@whi@Mace@whi@", 1093);
			}
			if (c.playerLevel[13] < 71) {
				c.sendString("@bla@Axe@bla@", 1091);
			} else {
				c.sendString("@whi@Axe@whi@", 1091);
			}
			if (c.playerLevel[13] < 70) {
				c.sendString("@bla@Dagger@bla@", 1094);
			} else {
				c.sendString("@whi@Dagger@whi@", 1094);
			}
			addItemToSmith(1211, 0, 1119, 1, c);
			addItemToSmith(1357, 0, 1120, 1, c);
			addItemToSmith(1111, 0, 1121, 1, c);
			addItemToSmith(1145, 0, 1122, 1, c);
			addItemToSmith(823, 0, 1123, 10, c);
			addItemToSmith(1287, 1, 1119, 1, c);
			addItemToSmith(1430, 1, 1120, 1, c);
			addItemToSmith(1073, 1, 1121, 1, c);
			addItemToSmith(1161, 1, 1122, 1, c);
			addItemToSmith(43, 1, 1123, 15, c);
			addItemToSmith(1331, 2, 1119, 1, c);
			addItemToSmith(1345, 3, 1120, 1, c);
			addItemToSmith(1091, 2, 1121, 1, c);
			addItemToSmith(1183, 2, 1122, 1, c);
			addItemToSmith(867, 2, 1123, 5, c);
			addItemToSmith(1301, 3, 1119, 1, c);
			addItemToSmith(1371, 3, 1120, 1, c);
			addItemToSmith(1123, 3, 1121, 1, c);
			addItemToSmith(1199, 3, 1122, 1, c);
			// addItemToSmith(0,3,1123, c);
			c.sendString("", 1132);
			c.sendString("", 1096);
			addItemToSmith(1317, 4, 1119, 1, c);
			addItemToSmith(3100, 4, 1120, 1, c);
			// addItemToSmith(4540,4,1121,1, c);
			addItemToSmith(4823, 4, 1122, 1, c);
			// addItemToSmith(1307,4,1123, c);
			c.sendString("", 1135);
			c.sendString("", 1134);
			c.sendString("", 11461);
			c.sendString("", 11459);
		}
		if (barType == 2363) { // Rune
			if (c.playerLevel[13] < 99) {
				c.sendString("@bla@Plate body@bla@", 1101);
				c.sendString("@bla@Plate legs@bla@", 1099);
				c.sendString("@bla@Plate skirt@bla@", 1100);
				c.sendString("@bla@2 hand sword@bla@", 1088);
			} else {
				c.sendString("@whi@Plate body@whi@", 1101);
				c.sendString("@whi@Plate legs@whi@", 1099);
				c.sendString("@whi@Plate skirt@whi@", 1100);
				c.sendString("@whi@2 hand sword@whi@", 1088);
			}
			if (c.playerLevel[13] < 98) {
				c.sendString("@bla@Claws@bla@", 8429);
			} else {
				c.sendString("@whi@Claws@whi@", 8429);
			}
			if (c.playerLevel[13] < 97) {
				c.sendString("@bla@Kite shield@bla@", 1105);
			} else {
				c.sendString("@whi@Kite shield@whi@", 1105);
			}
			if (c.playerLevel[13] < 96) {
				c.sendString("@bla@Chain body@bla@", 1098);
			} else {
				c.sendString("@whi@Chain body@whi@", 1098);
			}
			if (c.playerLevel[13] < 95) {
				c.sendString("@bla@Battle axe@bla@", 1092);
			} else {
				c.sendString("@whi@Battle axe@whi@", 1092);
			}
			if (c.playerLevel[13] < 94) {
				c.sendString("@bla@Warhammer@bla@", 1083);
			} else {
				c.sendString("@whi@Warhammer@whi@", 1083);
			}
			if (c.playerLevel[13] < 93) {
				c.sendString("@bla@Square shield@bla@", 1104);
			} else {
				c.sendString("@whi@Square shield@whi@", 1104);
			}
			if (c.playerLevel[13] < 92) {
				c.sendString("@bla@Full helm@bla@", 1103);
				c.sendString("@bla@Throwing knives@bla@", 1106);
			} else {
				c.sendString("@whi@Full helm@whi@", 1103);
				c.sendString("@whi@Throwing knives@whi@", 1106);
			}
			if (c.playerLevel[13] < 91) {
				c.sendString("@bla@Long sword@bla@", 1086);
			} else {
				c.sendString("@whi@Long sword@whi@", 1086);
			}
			if (c.playerLevel[13] < 90) {
				c.sendString("@bla@Scimitar@bla@", 1087);
				c.sendString("@bla@Arrowtips@bla@", 1108);
			} else {
				c.sendString("@whi@Scimitar@whi@", 1087);
				c.sendString("@whi@Arrowtips@whi@", 1108);
			}
			if (c.playerLevel[13] < 89) {
				c.sendString("@bla@Sword@bla@", 1085);
				c.sendString("@bla@Dart tips@bla@", 1107);
				c.sendString("@bla@Nails@bla@", 13358);
			} else {
				c.sendString("@whi@Sword@whi@", 1085);
				c.sendString("@whi@Dart tips@whi@", 1107);
				c.sendString("@whi@Nails@whi@", 13358);
			}
			if (c.playerLevel[13] < 88) {
				c.sendString("@bla@Medium helm@bla@", 1102);
			} else {
				c.sendString("@whi@Medium helm@whi@", 1102);
			}
			if (c.playerLevel[13] < 87) {
				c.sendString("@bla@Mace@bla@", 1093);
			} else {
				c.sendString("@whi@Mace@whi@", 1093);
			}
			if (c.playerLevel[13] < 86) {
				c.sendString("@bla@Axe@bla@", 1091);
			} else {
				c.sendString("@whi@Axe@whi@", 1091);
			}
			if (c.playerLevel[13] < 85) {
				c.sendString("@bla@Dagger@bla@", 1094);
			} else {
				c.sendString("@whi@Dagger@whi@", 1094);
			}
			addItemToSmith(1213, 0, 1119, 1, c);
			addItemToSmith(1359, 0, 1120, 1, c);
			addItemToSmith(1113, 0, 1121, 1, c);
			addItemToSmith(1147, 0, 1122, 1, c);
			addItemToSmith(824, 0, 1123, 10, c);
			addItemToSmith(1289, 1, 1119, 1, c);
			addItemToSmith(1432, 1, 1120, 1, c);
			addItemToSmith(1079, 1, 1121, 1, c);
			addItemToSmith(1163, 1, 1122, 1, c);
			addItemToSmith(44, 1, 1123, 15, c);
			addItemToSmith(1333, 2, 1119, 1, c);
			addItemToSmith(1347, 2, 1120, 1, c);
			addItemToSmith(1093, 2, 1121, 1, c);
			addItemToSmith(1185, 2, 1122, 1, c);
			addItemToSmith(868, 2, 1123, 5, c);
			addItemToSmith(1303, 3, 1119, 1, c);
			addItemToSmith(1373, 3, 1120, 1, c);
			addItemToSmith(1127, 3, 1121, 1, c);
			addItemToSmith(1201, 3, 1122, 1, c);
			// addItemToSmith(0,3,1123, c);
			c.sendString("", 1132);
			c.sendString("", 1096);
			addItemToSmith(1319, 4, 1119, 1, c);
			addItemToSmith(3101, 4, 1120, 1, c);
			// addItemToSmith(4540,4,1121,1, c);
			addItemToSmith(4824, 4, 1122, 1, c);
			// addItemToSmith(1307,4,1123, c);
			c.sendString("", 1135);
			c.sendString("", 1134);
			c.sendString("", 11461);
			c.sendString("", 11459);
		}
	}
	
}