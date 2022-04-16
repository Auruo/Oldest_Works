//By Avenged7Fold of Rune-Server
public class GetWepSound {
	public GetWepSound() { }
	public int GetWeaponSound(int playerId)	{
		Client c = (Client) Server.PlayerManager.players[playerId];	
		if (c.playerEquipment[c.playerWeapon] == 772
				|| c.playerEquipment[c.playerWeapon] == 1379
				|| c.playerEquipment[c.playerWeapon] == 1381
				|| c.playerEquipment[c.playerWeapon] == 1383
				|| c.playerEquipment[c.playerWeapon] == 1385
				|| c.playerEquipment[c.playerWeapon] == 1387
				|| c.playerEquipment[c.playerWeapon] == 1389
				|| c.playerEquipment[c.playerWeapon] == 1391
				|| c.playerEquipment[c.playerWeapon] == 1393
				|| c.playerEquipment[c.playerWeapon] == 1395
				|| c.playerEquipment[c.playerWeapon] == 1397
				|| c.playerEquipment[c.playerWeapon] == 1399
				|| c.playerEquipment[c.playerWeapon] == 1401
				|| c.playerEquipment[c.playerWeapon] == 1403
				|| c.playerEquipment[c.playerWeapon] == 1405
				|| c.playerEquipment[c.playerWeapon] == 1407
				|| c.playerEquipment[c.playerWeapon] == 1409
				|| c.playerEquipment[c.playerWeapon] == 9100) { //Staff wack
			return 394;
		}
		if (c.playerEquipment[c.playerWeapon] == 839
				|| c.playerEquipment[c.playerWeapon] == 841
				|| c.playerEquipment[c.playerWeapon] == 843
				|| c.playerEquipment[c.playerWeapon] == 845
				|| c.playerEquipment[c.playerWeapon] == 847
				|| c.playerEquipment[c.playerWeapon] == 849
				|| c.playerEquipment[c.playerWeapon] == 851
				|| c.playerEquipment[c.playerWeapon] == 853
				|| c.playerEquipment[c.playerWeapon] == 855
				|| c.playerEquipment[c.playerWeapon] == 857
				|| c.playerEquipment[c.playerWeapon] == 859
				|| c.playerEquipment[c.playerWeapon] == 861
				|| c.playerEquipment[c.playerWeapon] == 4734
				|| c.playerEquipment[c.playerWeapon] == 2023 //RuneC'Bow
				|| c.playerEquipment[c.playerWeapon] == 4212
				|| c.playerEquipment[c.playerWeapon] == 4214
				|| c.playerEquipment[c.playerWeapon] == 4934
				|| c.playerEquipment[c.playerWeapon] == 9104
				|| c.playerEquipment[c.playerWeapon] == 9107) { //Bows/Crossbows
			return 370;
		}
		if (c.playerEquipment[c.playerWeapon] == 1363
				|| c.playerEquipment[c.playerWeapon] == 1365
				|| c.playerEquipment[c.playerWeapon] == 1367
				|| c.playerEquipment[c.playerWeapon] == 1369
				|| c.playerEquipment[c.playerWeapon] == 1371
				|| c.playerEquipment[c.playerWeapon] == 1373
				|| c.playerEquipment[c.playerWeapon] == 1375
				|| c.playerEquipment[c.playerWeapon] == 1377
				|| c.playerEquipment[c.playerWeapon] == 1349
				|| c.playerEquipment[c.playerWeapon] == 1351
				|| c.playerEquipment[c.playerWeapon] == 1353
				|| c.playerEquipment[c.playerWeapon] == 1355
				|| c.playerEquipment[c.playerWeapon] == 1357
				|| c.playerEquipment[c.playerWeapon] == 1359
				|| c.playerEquipment[c.playerWeapon] == 1361
				|| c.playerEquipment[c.playerWeapon] == 9109) { //BattleAxes/Axes
			return 399;
		}
		if (c.playerEquipment[c.playerWeapon] == 4718
				|| c.playerEquipment[c.playerWeapon] == 6609
				|| c.playerEquipment[c.playerWeapon] == 7808
				|| c.playerEquipment[c.playerWeapon] == 1307
				|| c.playerEquipment[c.playerWeapon] == 1309
				|| c.playerEquipment[c.playerWeapon] == 1311
				|| c.playerEquipment[c.playerWeapon] == 1313
				|| c.playerEquipment[c.playerWeapon] == 1315
				|| c.playerEquipment[c.playerWeapon] == 1317
				|| c.playerEquipment[c.playerWeapon] == 1319) { //2hs/Dharok GreatAxe
			return 400;
		}
		if (c.playerEquipment[c.playerWeapon] == 1321 
				|| c.playerEquipment[c.playerWeapon] == 1323 
				|| c.playerEquipment[c.playerWeapon] == 1325 
				|| c.playerEquipment[c.playerWeapon] == 1327 
				|| c.playerEquipment[c.playerWeapon] == 1329 
				|| c.playerEquipment[c.playerWeapon] == 1331 
				|| c.playerEquipment[c.playerWeapon] == 1333
				|| c.playerEquipment[c.playerWeapon] == 4587) { //Scimitars
			return 396;
		}
		if (c.playerEquipment[c.playerWeapon] == 9110) {
			return 401;
		}
		if (c.playerEquipment[c.playerWeapon] == 9103) {
			return 385;
		}
		if (c.playerEquipment[c.playerWeapon] == -1) { // fists
			return 417;
		}
		if (c.playerEquipment[c.playerWeapon] == 2745 || c.playerEquipment[c.playerWeapon] == 2746 || c.playerEquipment[c.playerWeapon] == 2747 || c.playerEquipment[c.playerWeapon] == 2748) { // Godswords
			return 390;
		}
		if (c.playerEquipment[c.playerWeapon] == 4151) {
			return 1080;
		} else {
			return 398; //Daggers(this is enything that isn't added)
		}
	}
}