package model;

import control.GameController;

public class GameModel {
	PlayersCatalogue  playerCatalogue;
	String [] gamePlayers;		
	String[][] gameBoard;
	GameController gc;
 
	Boolean mover;
	int moves;
	
	public GameModel(GameController gc) {
		this.gc=gc;
		gamePlayers = new String[2];
		gameBoard= null;
		playerCatalogue= new PlayersCatalogue();
		mover=false;
		moves=0;
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
		switch(checkWin()) {
			case 0:
				System.out.println("O won");
				break;
				
			case 1:
				System.out.println("X won");
				break;
			case -1:
				break;
				
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
		
}
