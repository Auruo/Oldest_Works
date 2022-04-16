class Interfaces	{

	public void cmdGuide(Client c) {
		if (System.currentTimeMillis() - c.InterDelay > 5000) {
			c.clearQuestInterface();
			c.sendString("Close Window", 8135);//Close Text
			c.sendString(" @blu@Command List / Guide", 8144);//Title
			c.sendString("@blu@What are commands?", 8145);// All text and lines from here and down are centered
			c.sendString("Commands are words that do a certain task for a player.", 8147);
			c.sendString("Example Usage: '::world Hey everyone!'", 8148);
			c.sendString("@blu@Command List: (@yel@Command@bla@, @whi@Usage@bla@)", 8150);
			c.sendString("@yel@::world @bla@| @whi@Allows you to speak to everyone", 8151);
			c.sendString("@yel@::destroymyinventory @bla@| @whi@ Destroys all items in your inventory", 8152);
			c.sendString("@yel@::players @bla@| @whi@Shows who and how many players are online", 8153);
			c.sendString("@yel@::changepassword @bla@| @whi@ Changes your password permanently", 8154);
			c.sendString("@blu@Guide Commands:", 8156);
			c.sendString("@yel@::guide @bla@| @whi@Beginners guide", 8157);
			c.sendString("@yel@::commands @bla@| @whi@Command list & guide", 8158);
			c.sendString("@yel@::pk @bla@| @whi@PK skill guide", 8159);
			c.sendString("@yel@::info @bla@| @whi@Server information guide", 8160);
			c.showInterface(8134);
			c.flushOutStream();
			c.InterDelay = System.currentTimeMillis();
		}
	}
	
	public void pkGuide(Client c) {
		if (System.currentTimeMillis() - c.InterDelay > 5000) {
			c.clearQuestInterface();
			c.sendString("Close Window", 8135);//Close Text
			c.sendString(" @blu@PK Skill Guide", 8144);//Title
			c.sendString("The PK skill is a replacement for Slayer.", 8145);// All text and lines from here and down are centered
			c.sendString("Slayer was replaced because", 8147);
			c.sendString("I (Auruo) thought it was pointless.", 8148);
			c.sendString("@blu@Basic Info on PKing:", 8150);
			c.sendString("@whi@-@bla@When you PK and kill someone or get killed by someone,", 8151);
			c.sendString("your PK rating goes up or down.", 8152);
			c.sendString("@whi@-@bla@If your PK rating is higher than 500,", 8153);
			c.sendString("you automatically gain a PK point every", 8154);
			c.sendString("3 minutes while your in the wilderness.", 8155);
			c.sendString("@yel@::commands @bla@| @whi@Command list & guide", 8156);
			c.sendString("@yel@::pk @bla@| @whi@PK skill guide", 8157);
			c.showInterface(8134);
			c.flushOutStream();
			c.InterDelay = System.currentTimeMillis();
		}
	}
	
	public void plrMenu(Client c) {
		c.clearQuestInterface();
		for (int i = 0; i < Server.PlayerManager.maxPlayers; i++) {
			if (Server.PlayerManager.players[i] != null) {
				c.sendString("@blu@Players Online", 8144);
				c.sendString("@blu@Amount Online: @bla@" + PlayerManager.getPlayerCount(), 8145);
				c.sendString("@blu@Player List:", 8148);
				c.sendString(""+ Server.PlayerManager.players[i].playerName, 8148+i);
			}
		}
		c.sendStringSomething(8143);
		c.showInterface(8134);
		c.flushOutStream();
	}
	
}