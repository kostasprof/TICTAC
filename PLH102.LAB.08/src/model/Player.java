package model;


public class Player {

	String name;
	int games;
	int wins;
	int losses;
	int draws;
	int score;
	Game[] bestGames;
	
	public Player(String n,int gp,int w,int l,int d) {

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

	public int getScore() {
		return score;
	}

	
	
	public void setScore(int score) {
		this.score = score;
	}

	 public void calcScore() {
		 int score=50*(2 * this.wins + this.draws )/this.games;
		 setScore(score);
	 }

}
