package model;
import java.io.Serializable;

public class Player implements Serializable {

	String name;
	int games;
	int wins;
	int losses;
	int draws;
	int score;
	Game[] bestGames;
	Game[] recentGames;
	int bestGamesCount;
	int recentScore=0;
	
	public Player(String n,int gp,int w,int l,int d) {

	this.name=n;
	this.games=gp;
	this.losses=l;
	this.wins=w;
	this.draws=d;
	bestGames= new Game[100];
	recentGames= new Game[5];
	bestGamesCount=0;
	}
    
	
	public Game[] getBestGames() {
		return bestGames;
	}


	public int getRecentScore() {
		return score;
	}


	public void setRecentScore(int recentScore) {
		this.recentScore = recentScore;
	}


	public void setBestGames(Game[] bestGames) {
		this.bestGames = bestGames;
	}


	public Game[] getRecentGames() {
		return recentGames;
	}


	public void setRecentGames(Game[] recentGames) {
		this.recentGames = recentGames;
	}


	public int getBestGamesCount() {
		return bestGamesCount;
	}


	public void setBestGamesCount(int bestGamesCount) {
		this.bestGamesCount = bestGamesCount;
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
		
		calcScore();
		return score;
		
	}

	
	
	public void setScore(int score) {
		this.score = score;
	}

	 public void calcScore() {
		 
		 if(this.games==0) {
			 setScore(0);
			 return;
		 }
		 
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
	
	/*public void addBestGame(Game g) {
		int best;
		if(bestGames[0]==null) {
			bestGames[0]=g;
			bestGamesCount++;
			return;
		}
		for(int i=0;i<5;i++) {
			if(bestGames[i]!=null) {
			best=compareGames(g,bestGames[i]);
			if(best==1) {
				for(int j=bestGamesCount-1;j>i;j--) {
					bestGames[j]=bestGames[j-1];
					}
				bestGames[i]=g;
				bestGamesCount++;
			}
			else if(best==2) {
				continue;
			}
			}
		}
		}*/
	
	public void addBestGame(Game g){
		bestGames[bestGamesCount]=g;
		bestGamesCount++;
		sortGames();
	}
	
	public void sortGames() {
		Game temp;
		for(int i=0;i<bestGames.length-1;i++) {
			for(int j=i;j>0;j--) {
				if(bestGames[i]==null) {
					return;
				}
				if(compareGames(bestGames[j],bestGames[j-1])==1) {
					temp=bestGames[j];
					bestGames[j]=bestGames[j-1];
					bestGames[j-1]=temp;
					
				}
			}
		}
	}
		
		
    
	
	public int compareGames(Game g1,Game g2) {
		if(g1.getResult()>g2.getResult()) {
			return 1;
		}
		if(g1.getResult()<g2.getResult()) {
			return 2;
		}
		if(g1.getResult()==g2.getResult()) {
			if(g1.opponent.getScore()>g2.opponent.getScore()) {
				return 1;
			}
			if(g1.opponent.getScore()<g2.opponent.getScore()) {
				return 2;
			}
			if(g1.opponent.getScore()==g2.opponent.getScore()) {
				//find most recent game
			}
		}
		return 0;
	}
	public void addGame(int i) {
			if(i==1) {
				this.games++;
				this.wins++;
			}
			
			if(i==0) {
				this.draws++;
				this.games++;
			}
			
			if(i==-1) {
				this.games++;
				this.losses++;
			}
		}
	
}
