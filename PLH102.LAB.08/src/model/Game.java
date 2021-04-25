package model;
import java.time.LocalDateTime;
public class Game {

	Player[] players;
    int score[];
    int result;
    String DateTimeOfGame;
    
    public Game(int sc[],String LDT,int res,Player[] ps){
    	this.score=sc;
    	this.result=res;
    	this.players=ps;
    	this.DateTimeOfGame=LDT;		
    }

	public Player[] getPlayers() {
		return players;
	}

	public void setPlayers(Player[] players) {
		this.players = players;
	}

	public int[] getScore() {
		return score;
	}

	public void setScore(int[] score) {
		this.score = score;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public String getLocalDateTime() {
		return DateTimeOfGame;
	}

	public void setLocalDateTime(String localDateTime) {
		DateTimeOfGame = localDateTime;
	}
    
	
    public void DateTimeGame() {
    	LocalDateTime dt= LocalDateTime.now();
    	
    }
    
}
