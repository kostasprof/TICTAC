package model;

public class PlayersCatalogue {
	private String[] players;
	int numofplayers=4;
	
	
	public PlayersCatalogue() {
		players = new String[4];
		players[0]="Vasilis";
		players[1]="Nektarios";
		players[2]="Yannis";
		players[3]="Eleni";		
	}
	
	public void AddPlayer(Player p) {
	//this.players[numofplayers]=p;
	
	}
	
	public String getPlayer(int i) {
		if (i<0 || i>4) {
			return null;
		}
		return players[i];
	}
	
	
	public String[] getPlayers() {
		return players;
	}
	
}
