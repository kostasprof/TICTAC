package model;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;  
import control.GameController;
import view.MainAreaPanel;
import view.PlayerPanel;
public class GameModel {
	PlayersCatalogue  playerCatalogue;
	String [] gamePlayers;		
	String[][] gameBoard;
	GameController gc;
	 JFrame f; 
	 JTextField t1;
	 JTextField t2;
	 JTextField t3;
	Boolean mover;
	int moves;
	
	public GameModel(GameController gc) {
		this.gc=gc;
		gamePlayers = new String[2];
		gameBoard= null;
		playerCatalogue= new PlayersCatalogue();
		mover=false;
		moves=0;
		f=new JFrame("Result");
		
	}
	
	public void selectPlayer(String player, int pos) {
		if (pos<0 && pos>1)return;
		gamePlayers[pos]=player;		
	}
	
	
	public boolean ready() {
		return (gamePlayers[0] != null && gamePlayers[1] !=null);
	}
	
	
	public void startGame() {
		gameBoard= new String[3][3];
	}
	
	
	public boolean inPlay() {
		return gameBoard !=null && moves <9;
	}
	
	public boolean NoPlay() {
		return !inPlay();
	}
	
	public int getMover() {
		return mover.compareTo(false);
	}
	
	
	public String[] getGamePlayers() {
		return gamePlayers;
	}
	

	public String[][] getGameBoard() {
		return gameBoard;
	}
	
	public void checkDimValidity(int row, int col) {
		if (row <0 || col < 0 || row > 2 || col >2) {
			throw new IndexOutOfBoundsException(row + ","+col +" is not a valid board cell");
		}
	}
	
	public void checkMoveValidity(int row, int col) {
		checkDimValidity(row, col);
		if (gameBoard[row][col]!=null) {
			throw new IllegalArgumentException("Non playable cell");
		}
	}
	
	public void setMover(Boolean mover) {
		this.mover = mover;
	}

	public String getBoardMark(int row, int col) {
		checkDimValidity(row, col);
		return gameBoard[row][col];
	}

	public void setGameBoard(String[][] gameBoard) {
		this.gameBoard = gameBoard;
	}

	public PlayersCatalogue getPlayerCatalogue() {
		return playerCatalogue;
	}

	public void setPlayerCatalogue(PlayersCatalogue playerCatalogue) {
		this.playerCatalogue = playerCatalogue;
	}
	
	public void makeMove(int row, int col) {
		checkMoveValidity(row, col);
		gameBoard[row][col]=getMoverMark();
		mover=!mover;
		moves++;
		Player[] currentPlayers = new Player[2];
		currentPlayers=gc.getModel().getPlayerCatalogue().getCurrentPlayers();
		if(moves==9) {
			System.out.println("draw"); /*Draw*/
			currentPlayers=gc.getModel().getPlayerCatalogue().getCurrentPlayers();
			gc.getModel().getPlayerCatalogue().getCurrentPlayers()[1].addRecentGame(new Game(currentPlayers[1],currentPlayers[0],0));
			gc.getModel().getPlayerCatalogue().getCurrentPlayers()[1].addBestGame(new Game(currentPlayers[1],currentPlayers[0],0));
			gc.getModel().getPlayerCatalogue().getCurrentPlayers()[1].addGame(0);
			
			gc.getModel().getPlayerCatalogue().getCurrentPlayers()[0].addRecentGame(new Game(currentPlayers[0],currentPlayers[1],0));
			gc.getModel().getPlayerCatalogue().getCurrentPlayers()[0].addBestGame(new Game(currentPlayers[0],currentPlayers[1],0));
			gc.getModel().getPlayerCatalogue().getCurrentPlayers()[0].addGame(0);
			
			
			t1= new JTextField("Draw");
			t1.setBounds(50,100, 200,30);
			f.add(t1);
			f.setSize(400,400);
			f.setLayout(null);  
			f.setVisible(true);
			
			
			gc.getModel().getPlayerCatalogue().storePlayers();
			//gc.getView().getMainPanel().showCard(MainAreaPanel.HOF);
			
			
			
			/*for(int i=0;i<3;i++) {
				for(int j=0;j<3;j++) {
					this.gameBoard[i][j]=null;
				}
			}*/
			this.moves=0;
			gc.startGame();
			return;
			
			
			
		}
		switch(checkWin()) {
			case 0:
				System.out.println("O won"); /*PLAYER 1*/
				currentPlayers=gc.getModel().getPlayerCatalogue().getCurrentPlayers();
				gc.getModel().getPlayerCatalogue().getCurrentPlayers()[1].addRecentGame(new Game(currentPlayers[1],currentPlayers[0],1));
				gc.getModel().getPlayerCatalogue().getCurrentPlayers()[1].addBestGame(new Game(currentPlayers[1],currentPlayers[0],1));
				gc.getModel().getPlayerCatalogue().getCurrentPlayers()[1].addGame(1);
				
				gc.getModel().getPlayerCatalogue().getCurrentPlayers()[0].addRecentGame(new Game(currentPlayers[0],currentPlayers[1],-1));
				gc.getModel().getPlayerCatalogue().getCurrentPlayers()[0].addBestGame(new Game(currentPlayers[0],currentPlayers[1],-1));
				gc.getModel().getPlayerCatalogue().getCurrentPlayers()[0].addGame(-1);
				
				
				t1= new JTextField(currentPlayers[1].getName()+" won");
				t1.setBounds(50,100, 200,30);
				f.add(t1);
				f.setSize(400,400);
				f.setLayout(null);  
				f.setVisible(true);
				
				
				gc.getModel().getPlayerCatalogue().storePlayers();
				
				/*for(int i=0;i<3;i++) {
					for(int j=0;j<3;j++) {
						this.gameBoard[i][j]=null;
					}
				}*/
				this.moves=0;
				gc.startGame();
				return;
				
			case 1:
				System.out.println("X won"); /*PLAYER 0*/
				gc.getModel().getPlayerCatalogue().getCurrentPlayers()[0].addRecentGame(new Game(currentPlayers[0],currentPlayers[1],1));
				gc.getModel().getPlayerCatalogue().getCurrentPlayers()[0].addBestGame(new Game(currentPlayers[0],currentPlayers[1],1));
				gc.getModel().getPlayerCatalogue().getCurrentPlayers()[0].addGame(1);
				
				gc.getModel().getPlayerCatalogue().getCurrentPlayers()[1].addRecentGame(new Game(currentPlayers[1],currentPlayers[0],-1));
				gc.getModel().getPlayerCatalogue().getCurrentPlayers()[1].addBestGame(new Game(currentPlayers[1],currentPlayers[0],-1));
				gc.getModel().getPlayerCatalogue().getCurrentPlayers()[1].addGame(-1);
				
				t1= new JTextField(currentPlayers[0].getName()+" won");
				t1.setBounds(50,100, 200,30);
				f.add(t1);
				f.setSize(400,400);
				f.setLayout(null);  
				f.setVisible(true);
				
				 
				gc.getModel().getPlayerCatalogue().storePlayers();
				
				/*for(int i=0;i<3;i++) {
					for(int j=0;j<3;j++) {
						this.gameBoard[i][j]=null;
					}
				}*/
				this.moves=0;
				
				gc.startGame();
				return;
				
			case -1:
				
				
				
		}
		
		if(mover==true) {
			checkAI(0);
			return;
		}
		if(mover==false) {
			checkAI(1);
			return;
		}
		
	}
	
	public String getMoverMark() {
		return mover? "X": "O";
	}
	
	public String getPlayerStats(String player) {
		Player p=this.playerCatalogue.findPlayerByName(player);
		StringBuilder sb = new StringBuilder("");
		float winr,lossr,wins=p.getWins(),losses=p.getLosses(),games=p.getGames();
		
		winr=wins/games*100;
		lossr=losses/games*100;
		sb.append(player).append("\n\n\n");
		sb.append("Total:").append("\t").append(p.getGames()).append("\n");
		sb.append("Won:").append("\t").append(winr+"%").append("\n");
		sb.append("Lost:").append("\t").append(lossr+"%").append("\n");
		System.out.println("\n");
		sb.append("Total Score:").append("\t").append(p.getScore()).append("\n");
		sb.append("Best Games:").append("\t");
		for(int i=0;i<5;i++) {
			if(p.getBestGames()[i]!=null)
				sb.append("Against ").append(p.getBestGames()[i].getOpponent().getName()).append("\n");
		}
		sb.append("Recent Games:");
		for(int j=0;j<5;j++) {
			if(p.getRecentGames()[j]!=null)
				sb.append("Against ").append(p.getRecentGames()[j].getOpponent().getName()).append("\n");
		}
		return sb.toString();			
	}
	
	public int checkWin() {
		if(gameBoard[0][0]!=null && gameBoard[0][1]!=null && gameBoard[0][2]!=null)
			if(gameBoard[0][0].equals("X")==true && gameBoard[0][1].equals("X")==true && gameBoard[0][2].equals("X")==true)
			return 1;
		
		if(gameBoard[1][0]!=null && gameBoard[1][1]!=null && gameBoard[1][2]!=null)
			if(gameBoard[1][0].equals("X")==true && gameBoard[1][1].equals("X")==true && gameBoard[1][2].equals("X")==true)
			return 1;
		
		if(gameBoard[2][0]!=null && gameBoard[2][1]!=null && gameBoard[2][2]!=null)
			if(gameBoard[2][0].equals("X")==true && gameBoard[2][1].equals("X")==true && gameBoard[2][2].equals("X")==true)
			return 1;
		
		if(gameBoard[0][0]!=null && gameBoard[1][0]!=null && gameBoard[2][0]!=null)
			if(gameBoard[0][0].equals("X")==true && gameBoard[1][0].equals("X")==true && gameBoard[2][0].equals("X")==true)
			return 1;
		
		if(gameBoard[0][1]!=null && gameBoard[1][1]!=null && gameBoard[2][1]!=null)
			if(gameBoard[0][1].equals("X")==true && gameBoard[1][1].equals("X")==true && gameBoard[2][1].equals("X")==true)
			return 1;
		
		if(gameBoard[0][2]!=null && gameBoard[1][2]!=null && gameBoard[2][2]!=null)
			if(gameBoard[0][2].equals("X")==true && gameBoard[1][2].equals("X")==true && gameBoard[2][2].equals("X")==true)
			return 1;
		
		if(gameBoard[1][1]!=null && gameBoard[2][2]!=null && gameBoard[0][0]!=null)
			if(gameBoard[1][1].equals("X")==true && gameBoard[2][2].equals("X")==true && gameBoard[0][0].equals("X")==true)
			return 1;
		
		if(gameBoard[0][2]!=null && gameBoard[1][1]!=null && gameBoard[2][0]!=null)
			if(gameBoard[0][2].equals("X")==true && gameBoard[1][1].equals("X")==true && gameBoard[2][0].equals("X")==true)
			return 1;

		
		
		if(gameBoard[0][0]!=null && gameBoard[0][1]!=null && gameBoard[0][2]!=null)
			if(gameBoard[0][0].equals("O")==true && gameBoard[0][1].equals("O")==true && gameBoard[0][2].equals("O")==true)
			return 0;
		
		if(gameBoard[1][0]!=null && gameBoard[1][1]!=null && gameBoard[1][2]!=null)
			if(gameBoard[1][0].equals("O")==true && gameBoard[1][1].equals("O")==true && gameBoard[1][2].equals("O")==true)
			return 0;
		
		if(gameBoard[2][0]!=null && gameBoard[2][1]!=null && gameBoard[2][2]!=null)
			if(gameBoard[2][0].equals("O")==true && gameBoard[2][1].equals("O")==true && gameBoard[2][2].equals("O")==true)
				return 0;
		
		if(gameBoard[0][0]!=null && gameBoard[1][0]!=null && gameBoard[2][0]!=null)
			if(gameBoard[0][0].equals("O")==true && gameBoard[1][0].equals("O")==true && gameBoard[2][0].equals("O")==true)
				return 0;
		
		if(gameBoard[0][1]!=null && gameBoard[1][1]!=null && gameBoard[2][1]!=null)
			if(gameBoard[0][1].equals("O")==true && gameBoard[1][1].equals("O")==true && gameBoard[2][1].equals("O")==true)
				return 0;
		
		if(gameBoard[0][2]!=null && gameBoard[1][2]!=null && gameBoard[2][2]!=null)
			if(gameBoard[0][2].equals("O")==true && gameBoard[1][2].equals("O")==true && gameBoard[2][2].equals("O")==true)
				return 0;
		
		if(gameBoard[1][1]!=null && gameBoard[2][2]!=null && gameBoard[0][0]!=null)
			if(gameBoard[1][1].equals("O")==true && gameBoard[2][2].equals("O")==true && gameBoard[0][0].equals("O")==true)
				return 0;
		
		if(gameBoard[0][2]!=null && gameBoard[1][1]!=null && gameBoard[2][0]!=null)
			if(gameBoard[0][2].equals("O")==true && gameBoard[1][1].equals("O")==true && gameBoard[2][0].equals("O")==true)
				return 0;
		
		return -1;
	}
	
	public void checkAI(int i){
		if(gc.getModel().getPlayerCatalogue().getCurrentPlayers()[i].getName().equals("Mr Bean")==true)
			mrBean();
		
		if(gc.getModel().getPlayerCatalogue().getCurrentPlayers()[1].getName().equals("Hal")==true)
			return;
		return;
	}
	
	public void mrBean(){
		Random rand = new Random();
		int row,col,check=-1;
		while(check!=0) {
			row=rand.nextInt(3);
			col=rand.nextInt(3);
			if(gameBoard[row][col]==null) {
				
				makeMove(row, col);
				
				check=0;
				
				return;
			}
		}
		
	}
	
}
