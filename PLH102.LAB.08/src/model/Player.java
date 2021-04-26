package model;


public class Player {

	String name;
	int games;
	int wins;
	int losses;
	int draws;
	int score;
	Game[] bestGames;
	Game[] recentGames;
	public Player(String n,int gp,int w,int l,int d) {

	this.name=n;
	this.games=gp;
	this.losses=l;
	this.wins=w;
	this.draws=d;
	bestGames= new Game[5];
	recentGames= new Game[5];
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
	
	public void addRecentGame(Game g) {
		for(int i=0;i<4;i++) {
			if(recentGames[i]==null) {
				recentGames[i]=g;
				return;
			}
			if(i==4) {
				recentGames[0]=recentGames[1];
				recentGames[1]=recentGames[2];
				recentGames[2]=recentGames[3];
				recentGames[3]=recentGames[4];
				recentGames[4]=g;
			}
		}
		
	}
	
	public void addBestGame(Game g) {
		if(bestGames[0]==null) {
			bestGames[0]=g;
			return;
		}
		
		
	}
	
	public int compareGames(Game g1,Game g2) {
		//if(g1.score>g2.score)
		return 0; 
	}
}
