package jUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import control.GameController;
import model.GameModel;
import model.Player;
import model.PlayersCatalogue;
import view.MainWindow;

class TestModel {
String[][] gameBoard;
GameModel model;
GameController gc;
PlayersCatalogue pc;
Player[] cPlayers;
MainWindow mW;
	@BeforeEach
	void setup() {
		gameBoard = new String[3][3];
		
		gc=new GameController();
		model=new GameModel(gc);
		mW= new MainWindow(gc);
		gc.setModel(model);
		model.setGameBoard(gameBoard);
		pc=new PlayersCatalogue();
		model.setPlayerCatalogue(pc);
		cPlayers=new Player[2];
		cPlayers[0]=new Player("Chris",0,0,0,0);
		cPlayers[1]=new Player("Kostaki",0,0,0,0);
		pc.setCurrentPlayers(0, cPlayers[0]);
		pc.setCurrentPlayers(1, cPlayers[1]);
		gc.setView(mW);
	}

	@Test
	void testMakeMove() {
		model.makeMove(0, 0);
		model.makeMove(1, 1);
		assertEquals(model.getBoardMark(0, 0),"O");
		assertEquals(model.getBoardMark(1, 1),"X");
	}
	@Test
	void testCheckWin(){
		model.getGameBoard()[0][0]="X";
		model.getGameBoard()[0][1]="X";
		model.getGameBoard()[0][2]="X";
		assertEquals(model.checkWin(),1);
		
		model.getGameBoard()[0][0]="O";
		model.getGameBoard()[0][1]="O";
		model.getGameBoard()[0][2]="O";
		assertEquals(model.checkWin(),0);
		
		model.getGameBoard()[0][0]=null;
		model.getGameBoard()[0][1]=null;
		model.getGameBoard()[0][2]=null;
		assertEquals(model.checkWin(),-1);
		
		model.getGameBoard()[0][0]="O";
		model.getGameBoard()[0][1]="X";
		model.getGameBoard()[0][2]="O";
		model.getGameBoard()[1][0]="X";
		model.getGameBoard()[1][1]="O";
		model.getGameBoard()[1][2]="X";
		model.getGameBoard()[2][0]="X";
		model.getGameBoard()[2][1]="O";
		model.getGameBoard()[2][2]="X";
		assertEquals(model.checkWin(),-1);
	}
	@Test
	void testHal() {
		int i[]=new int [2];
		model.getGameBoard()[0][0]="O";
		model.getGameBoard()[0][1]="X";
		model.getGameBoard()[1][0]="O";
		model.getGameBoard()[1][1]="X";
		i=model.testHal();
		model.getGameBoard()[i[0]][i[1]]="O";
		assertEquals(model.getGameBoard()[2][0],"O");
		
		model.getGameBoard()[0][0]="X";
		model.getGameBoard()[0][1]="O";
		model.getGameBoard()[1][0]="X";
		model.getGameBoard()[1][1]="O";
		i=model.testHal();
		model.getGameBoard()[i[0]][i[1]]="O";
		assertEquals(model.getGameBoard()[2][0],"O");
	}
}
