package model;

public class PlayersCatalogue {
	private Player[] players;
	int numofplayers;
	Player[] bestPlayers;
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
	
	public Player findPlayerByName(String name) {
		for(Player p: this.players) {
			if(p!=null&&p.getName().equals(name)) {
				return p;
			}
		
		}
		return null;
	}
	
	
	public void sortPlayers() {
		Player temp;
		for(int i=0;i<players.length-1;i++) {
			for(int j=i;j>0;j--) {
				if(players[i]==null) {
					return;
				}
				if(players[j].getScore()>players[j-1].getScore()) {
					temp=players[j];
					players[j]=players[j-1];
					players[j-1]=temp;
					
				}
			}
		}
	}
		
	
}