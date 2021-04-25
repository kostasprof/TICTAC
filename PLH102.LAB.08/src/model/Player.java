package model;

public class Player {

	String name;
	int games;
	int wins;
	int losses;
	int winrate;
	int lossrate;
	int draws;
	
	public Player(String n,int w,int l,int gp,int d) {

	this.name=n;
	this.games=gp;
	this.losses=l;
	this.wins=w;
	this.draws=d;
	}
    
	
	public int getDraws() {
		return draws;
	}

	public void setDraws(int draws) {
		this.draws = draws;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getGames() {
		return games;
	}

	public void setGames(int games) {
		this.games = games;
	}

	public int getWins() {
		return wins;
	}

	public void setWins(int wins) {
		this.wins = wins;
	}

	public int getLosses() {
		return losses;
	}

	public void setLosses(int losses) {
		this.losses = losses;
	}

	public int getWinrate() {
		return winrate;
	}

	public void setWinrate(int winrate) {
		this.winrate = winrate;
	}

	public int getLossrate() {
		return lossrate;
	}

	public void setLossrate(int lossrate) {
		this.lossrate = lossrate;
	}
	
	
}
