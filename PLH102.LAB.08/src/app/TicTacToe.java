package app;


import control.GameController;
import model.Player;
import model.PlayersCatalogue;


import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
public class TicTacToe {
	
	
	public static void main(String[] args) {		
		
		GameController gc = new GameController();
		
		
		gc.start();
		gc.getModel().getPlayerCatalogue().loadPlayers();
		/*gc.getModel().getPlayerCatalogue().addPlayer(new Player("Grothos",10,5,5,0));
		gc.getModel().getPlayerCatalogue().addPlayer(new Player("Kostas",10,0,10,0));
		gc.getModel().getPlayerCatalogue().addPlayer(new Player("Giannis",10,0,10,0));
		gc.getModel().getPlayerCatalogue().addPlayer(new Player("Lakis",10,10,0,0));
		gc.getModel().getPlayerCatalogue().addPlayer(new Player("Vsam",100,0,500,0));
		*/
		
	}
	
}
