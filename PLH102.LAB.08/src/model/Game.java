package model;
import java.time.LocalDateTime;
public class Game {

	Player player;
	Player opponent;
    int result;
    LocalDateTime time;
    
    public Game(Player p,Player op,int res){
    	this.player=p;
    	this.opponent=op;
    	this.result=res;
    			
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
    
	
    public LocalDateTime DateTimeGame() {
    	LocalDateTime dt= LocalDateTime.now();
    	return dt;
    }
    
}