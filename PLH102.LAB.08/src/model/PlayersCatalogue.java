package model;

public class PlayersCatalogue {
	private Player[] players;
	
	
	/*public PlayersCatalogue() {
		players = new String[4];
		players[0]="Vasilis";
		players[1]="Nektarios";
		players[2]="Yannis";
		players[3]="Eleni";		
	}*/
	
	
	
	public Player getPlayer(int i) {
		if (i<0 || i>4) {
			return null;
		}
		return players[i];
	}
	
	
	public Player[] getPlayers() {
		return players;
	}
	
}