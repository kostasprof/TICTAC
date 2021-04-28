package app;


import control.GameController;
import model.Player;
import model.PlayersCatalogue;

public class TicTacToe {
	
	
	public static void main(String[] args) {		
		
		PlayersCatalogue pc=new PlayersCatalogue();
		Player p=new Player("Grothos",5,5,0,0);
		Player e=new Player("Kostas",5,0,5,0);
		pc.addPlayer(p);
		pc.addPlayer(e);
		
		
		GameController gc = new GameController();
		
		gc.start();
		
	}
}
