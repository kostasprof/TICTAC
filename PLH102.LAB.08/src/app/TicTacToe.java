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
		if(gc.getModel().getPlayerCatalogue().findPlayerByName("Hal")==null)
		gc.getModel().getPlayerCatalogue().addPlayer(new Player("Hal",0,0,0,0));
		
		if(gc.getModel().getPlayerCatalogue().findPlayerByName("Mr Bean")==null)
			gc.getModel().getPlayerCatalogue().addPlayer(new Player("Mr Bean",0,0,0,0));
	}
	
}
