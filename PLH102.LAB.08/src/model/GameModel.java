package model;
import java.awt.Graphics;
import java.util.ArrayList;
import java.util.Random;
import javax.swing.*;  
import control.GameController;
import view.MainAreaPanel;
import view.PlayerPanel;
import java.time.LocalDateTime;
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
	private static final int MAX_DEPTH = 6;
	
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
		return gameBoard !=null && moves <9 && checkWin()==-1;
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
	/*Takes the row and column that we want to make a move to, makes the move,checks if anybody has won and checks if the game is a draw and then updates the players' 
	stats. If at least one of the players is not AI then it starts a new game when the current game ends and then checks if the next player to play is AI. If so,
	it's function is called*/
	public void makeMove(int row, int col) {
		if(NoPlay()) 
			return;
		checkMoveValidity(row, col);
		gameBoard[row][col]=getMoverMark();
		mover=!mover;
		moves++;
		Player[] currentPlayers = new Player[2];
		currentPlayers=gc.getModel().getPlayerCatalogue().getCurrentPlayers();
		//Checking if somebody has won
		switch(checkWin()) {
			case 0:
				//If O won
				System.out.println("O won"); /*PLAYER 1*/
				currentPlayers=gc.getModel().getPlayerCatalogue().getCurrentPlayers();
				//Update players stats
				gc.getModel().getPlayerCatalogue().getCurrentPlayers()[1].setRecentScore(currentPlayers[1].getScore());
				gc.getModel().getPlayerCatalogue().getCurrentPlayers()[1].addRecentGame(new Game(currentPlayers[1],currentPlayers[0],1,LocalDateTime.now()));
				gc.getModel().getPlayerCatalogue().getCurrentPlayers()[1].addBestGame(new Game(currentPlayers[1],currentPlayers[0],1,LocalDateTime.now()));
				gc.getModel().getPlayerCatalogue().getCurrentPlayers()[1].addGame(1);
				
				gc.getModel().getPlayerCatalogue().getCurrentPlayers()[0].setRecentScore(currentPlayers[0].getScore());
				gc.getModel().getPlayerCatalogue().getCurrentPlayers()[0].addRecentGame(new Game(currentPlayers[0],currentPlayers[1],-1,LocalDateTime.now()));
				gc.getModel().getPlayerCatalogue().getCurrentPlayers()[0].addBestGame(new Game(currentPlayers[0],currentPlayers[1],-1,LocalDateTime.now()));
				gc.getModel().getPlayerCatalogue().getCurrentPlayers()[0].addGame(-1);
				
				//This announces winner
				t1= new JTextField(currentPlayers[1].getName()+" won");
				t1.setBounds(50,100, 200,30);
				f.add(t1);
				f.setSize(400,400);
				f.setLayout(null);  
				f.setVisible(true);
				
				//Storing players with updated stats
				gc.getModel().getPlayerCatalogue().storePlayers();
				
				
				this.moves=0;
				//If both players are AI startGame() is not called
				if(currentPlayers[0].getName().equals("Mr Bean")|| currentPlayers[1].getName().equals("Mr Bean"))
					if(currentPlayers[0].getName().equals("Hal")|| currentPlayers[1].getName().equals("Hal")) {
			            
						return;
					}
				//Starts new game
				gc.startGame();
				
				return;
				
			case 1:
				//If X won
				System.out.println("X won"); /*PLAYER 0*/
				//Update players stats
				gc.getModel().getPlayerCatalogue().getCurrentPlayers()[0].setRecentScore(currentPlayers[0].getScore());
				gc.getModel().getPlayerCatalogue().getCurrentPlayers()[0].addRecentGame(new Game(currentPlayers[0],currentPlayers[1],1,LocalDateTime.now()));
				gc.getModel().getPlayerCatalogue().getCurrentPlayers()[0].addBestGame(new Game(currentPlayers[0],currentPlayers[1],1,LocalDateTime.now()));
				gc.getModel().getPlayerCatalogue().getCurrentPlayers()[0].addGame(1);
				
				gc.getModel().getPlayerCatalogue().getCurrentPlayers()[1].setRecentScore(currentPlayers[1].getScore());
				gc.getModel().getPlayerCatalogue().getCurrentPlayers()[1].addRecentGame(new Game(currentPlayers[1],currentPlayers[0],-1,LocalDateTime.now()));
				gc.getModel().getPlayerCatalogue().getCurrentPlayers()[1].addBestGame(new Game(currentPlayers[1],currentPlayers[0],-1,LocalDateTime.now()));
				gc.getModel().getPlayerCatalogue().getCurrentPlayers()[1].addGame(-1);
				
				//This announces winner
				t1= new JTextField(currentPlayers[0].getName()+" won");
				t1.setBounds(50,100, 200,30);
				f.add(t1);
				f.setSize(400,400);
				f.setLayout(null);  
				f.setVisible(true);
				
				//Storing players with updated stats
				gc.getModel().getPlayerCatalogue().storePlayers();
				
				
				this.moves=0;
				//If both players are AI startGame() is not called
				if(currentPlayers[0].getName().equals("Mr Bean")|| currentPlayers[1].getName().equals("Mr Bean"))
					if(currentPlayers[0].getName().equals("Hal")|| currentPlayers[1].getName().equals("Hal"))
						return;
				
				//Starts new game
				gc.startGame();
				return;
				
			case -1:
			//No wins	
				
				
		}
		//If moves reaches 9 that means that the board is full and no-one won 
		if(moves==9) {
			//The game is a draw
			System.out.println("draw"); /*Draw*/
			//Updating players stats
			currentPlayers=gc.getModel().getPlayerCatalogue().getCurrentPlayers();
			gc.getModel().getPlayerCatalogue().getCurrentPlayers()[1].setRecentScore(currentPlayers[1].getScore());
			gc.getModel().getPlayerCatalogue().getCurrentPlayers()[1].addRecentGame(new Game(currentPlayers[1],currentPlayers[0],0,LocalDateTime.now()));
			gc.getModel().getPlayerCatalogue().getCurrentPlayers()[1].addBestGame(new Game(currentPlayers[1],currentPlayers[0],0,LocalDateTime.now()));
			gc.getModel().getPlayerCatalogue().getCurrentPlayers()[1].addGame(0);
			
			gc.getModel().getPlayerCatalogue().getCurrentPlayers()[0].setRecentScore(currentPlayers[0].getScore());
			gc.getModel().getPlayerCatalogue().getCurrentPlayers()[0].addRecentGame(new Game(currentPlayers[0],currentPlayers[1],0,LocalDateTime.now()));
			gc.getModel().getPlayerCatalogue().getCurrentPlayers()[0].addBestGame(new Game(currentPlayers[0],currentPlayers[1],0,LocalDateTime.now()));
			gc.getModel().getPlayerCatalogue().getCurrentPlayers()[0].addGame(0);
			
			//Draw is announced
			t1= new JTextField("Draw");
			t1.setBounds(50,100, 200,30);
			f.add(t1);
			f.setSize(400,400);
			f.setLayout(null);  
			f.setVisible(true);
			
			//Storing players with updated stats
			gc.getModel().getPlayerCatalogue().storePlayers();
			
			this.moves=0;
			//If both players are AI startGame() is not called
			if(currentPlayers[0].getName().equals("Mr Bean")|| currentPlayers[1].getName().equals("Mr Bean"))
				if(currentPlayers[0].getName().equals("Hal")|| currentPlayers[1].getName().equals("Hal"))
					return;
			
			//Starts new game
			gc.startGame();
			return;
			
			
			
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
	
	public String getOppositeMoverMark() {
		return mover? "O": "X";
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
		
		
		sb.append("Total Score:").append("\t").append(p.getScore()).append("\n");
		sb.append("Recent Score:").append("\t").append(p.getRecentScore()).append("\n");
		sb.append("Best Games:").append("\t");
		for(int i=0;i<5;i++) {
			if(p.getBestGames()[i]!=null)
				sb.append("Against ").append(p.getBestGames()[i].getOpponent().getName()).append("\n");
		}
		sb.append("Recent Games:");
		for(int j=4;j>-1;j--) {
			if(p.getRecentGames()[j]!=null)
				sb.append("Against ").append(p.getRecentGames()[j].getOpponent().getName()).append("\n");
		}
		return sb.toString();			
	}
	
//Checks every possible combination results in a win for X and O and returns 1 if X has a winning combination, 0 if O has a winning combination and -1 if no winning 
//combinations were found 
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

//Receives the next player's number in the currentPlayers array and checks if it is an AI. If it is, the AI's function is called
	public void checkAI(int i){
		if(gc.getModel().getPlayerCatalogue().getCurrentPlayers()[i].getName().equals("Mr Bean")==true) {
			gc.getTimerbean().start();
			return;
		}
			
		
		if(gc.getModel().getPlayerCatalogue().getCurrentPlayers()[i].getName().equals("Hal")==true) {
			gc.getTimerhal().start();
		    return;
		}
		return;	
	}

//A random move is made on the board
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
	

	
	public  int miniMax(int depth,boolean isMax) {
        int value=evaluateBoard();
        int draw=0;
        
        if ( depth == 0 || Math.abs(value)==10) {
            return value;
        }
        
        for(int row=0;row<3;row++) {
        	for(int col=0;col<3;col++) {
        		if(gameBoard[row][col]!=null)
        			draw++;
        	}
        }
        if(draw==9) {
        	return 0;
        }
        if(isMax) {
        	int highestVal = Integer.MIN_VALUE;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (gameBoard[row][col]==null) {
                    	gameBoard[row][col]=getMoverMark();
                        highestVal = Math.max(highestVal, miniMax(depth - 1,false));
                        gameBoard[row][col]=null;
                    }
                }
            }
            return highestVal;
        }
        else {
            int lowestVal = Integer.MAX_VALUE;
            for (int row = 0; row < 3; row++) {
                for (int col = 0; col < 3; col++) {
                    if (gameBoard[row][col]==null) {
                    	gameBoard[row][col]=getOppositeMoverMark();
                        lowestVal = Math.min(lowestVal, miniMax(depth - 1,true));
                        gameBoard[row][col]=null;
                    }
                }
            }
            return lowestVal;
        } 
        
    }

//Makes the best possible move on the board by using the minimax algorithm
    public  void hal() {
        int[] bestMove = new int[]{-1, -1};
        int bestValue = Integer.MIN_VALUE;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (gameBoard[row][col]==null) {
                    gameBoard[row][col]=getMoverMark();
                    int moveValue = miniMax(MAX_DEPTH,false);
                    gameBoard[row][col]=null;
                    if (moveValue > bestValue) {
                        bestMove[0] = row;
                        bestMove[1] = col;
                        bestValue=moveValue;
                    }
                }
            }
        }
        makeMove(bestMove[0],bestMove[1]);
        return;
    }
 //Returns 10 if Hal won or -10 it lost or 0 if noone has won  
    private int evaluateBoard() {
        // Checking Rows 
        for (int row = 0; row < 3; row++){
            if (gameBoard[row][0] == gameBoard[row][1] &&gameBoard[row][1] == gameBoard[row][2]){
                if (gameBoard[row][0] == getMoverMark())
                    return +10;
                else if (gameBoard[row][0] == getOppositeMoverMark())
                    return -10;
            }
        }
     
        // Checking Columns 
        for (int col = 0; col < 3; col++){
            if (gameBoard[0][col] == gameBoard[1][col] &&gameBoard[1][col] == gameBoard[2][col]){
                if (gameBoard[0][col] == getMoverMark())
                    return +10;
     
                else if (gameBoard[0][col] == getOppositeMoverMark())
                    return -10;
            }
        }
     
        // Checking Diagonals 
        if (gameBoard[0][0] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][2]){
            if (gameBoard[0][0] == getMoverMark())
                return +10;
            else if (gameBoard[0][0] == getOppositeMoverMark())
                return -10;
        }
     
        if (gameBoard[0][2] == gameBoard[1][1] && gameBoard[1][1] == gameBoard[2][0])
        {
            if (gameBoard[0][2] == getMoverMark())
                return +10;
            else if (gameBoard[0][2] == getOppositeMoverMark())
                return -10;
        }
     
        // Else if none of them have won then return 0
        return 0;
    }

    
//Testing a variation of Hal to use in a unit test	
    public  int[] testHal() {
        int[] bestMove = new int[]{-1, -1};
        int bestValue = Integer.MIN_VALUE;

        for (int row = 0; row < 3; row++) {
            for (int col = 0; col < 3; col++) {
                if (gameBoard[row][col]==null) {
                    gameBoard[row][col]=getMoverMark();
                    int moveValue = miniMax(MAX_DEPTH,false);
                    gameBoard[row][col]=null;
                    if (moveValue > bestValue) {
                        bestMove[0] = row;
                        bestMove[1] = col;
                        bestValue=moveValue;
                    }
                }
            }
        }
        
        return bestMove;
    }
	
	
}
