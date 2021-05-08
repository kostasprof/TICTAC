package model;
import java.io.Serializable;
import java.time.LocalDateTime;
public class Game implements Serializable {

	Player player;
	Player opponent;
    int result;
    LocalDateTime time;
    
    public Game(Player p,Player op,int res){
    	this.player=p;
    	this.opponent=op;
    	this.result=res;
    	//this.time=t;		
    }

	public Player getPlayers() {
		return player;
	}

	public void setPlayers(Player players) {
		this.player = players;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public LocalDateTime getLocalDateTime() {
		return time;
	}

	public void setLocalDateTime(LocalDateTime localDateTime) {
		time = localDateTime;
	}
    
	
    public Player getPlayer() {
		return player;
	}

	public void setPlayer(Player player) {
		this.player = player;
	}

	public Player getOpponent() {
		return opponent;
	}

	public void setOpponent(Player opponent) {
		this.opponent = opponent;
	}

	public LocalDateTime DateTimeGame() {
    	LocalDateTime dt= LocalDateTime.now();
    	return dt;
    }
    
}