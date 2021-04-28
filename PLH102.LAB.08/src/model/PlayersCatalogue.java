package model;

public class PlayersCatalogue {
	private Player[] players;
	int numofplayers;
	
	public PlayersCatalogue() {
		this.players= new Player[100];
		numofplayers=0;
	}
	
	
	
	public Player getPlayer(int i) {
		if (i<this.numofplayers-1 || i>this.numofplayers-1) {
			return null;
		}
		return players[i];
	}
	
	
	public Player[] getPlayers() {
		return players;
	}
	
	public int getNumofplayers() {
		return numofplayers;
	}



	public void setNumofplayers(int numofplayers) {
		this.numofplayers = numofplayers;
	}



	public void addPlayer(Player i) {
		this.players[numofplayers]=i;
		numofplayers++;
	}
	
	public String getPlayerName(Player i) {
		String n=i.getName();
		return n;
	}
	
	public String[] getPlayersNames() {
		String[] s= new String[numofplayers];
		for(int i=0; i<this.numofplayers; i++) {
			s[i]=players[i].getName();
		}
		return s;
	}
	
	public String getPlayerName(int i) {
		 return players[i].getName();
		
	}
}