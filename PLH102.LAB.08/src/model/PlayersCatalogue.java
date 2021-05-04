package model;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;



import java.io.ObjectOutputStream;
import java.io.FileNotFoundException;
public class PlayersCatalogue {
	private Player[] players;
	int numofplayers;
	Player[] bestPlayers;
	public PlayersCatalogue() {
		this.players= new Player[100];
		numofplayers=0;
	}
	
	
	
	public Player getPlayer(int i) {
		if (i<this.numofplayers-1 || i>this.numofplayers-1) {
			return null;
		}
		return players[i];
	}
	
	
	public Player[] getPlayers() {
		return players;
	}
	
	public int getNumofplayers() {
		return numofplayers;
	}



	public void setNumofplayers(int numofplayers) {
		this.numofplayers = numofplayers;
	}



	public void addPlayer(Player i) {
		this.players[numofplayers]=i;
		storePlayer();
		numofplayers++;
	}
	
	public String getPlayerName(Player i) {
		String n=i.getName();
		return n;
	}
	
	public String[] getPlayersNames() {
		String[] s= new String[numofplayers];
		for(int i=0; i<this.numofplayers; i++) {
			s[i]=players[i].getName();
		}
		return s;
	}
	
	public String getPlayerName(int i) {
		 return players[i].getName();
		
	}
	
	public Player findPlayerByName(String name) {
		for(Player p: this.players) {
			if(p!=null&&p.getName().equals(name)) {
				return p;
			}
		
		}
		return null;
	}
	
	
	public void sortPlayers() {
		Player temp;
		for(int i=0;i<players.length-1;i++) {
			for(int j=i;j>0;j--) {
				if(players[i]==null) {
					return;
				}
				if(players[j].getScore()>players[j-1].getScore()) {
					temp=players[j];
					players[j]=players[j-1];
					players[j-1]=temp;
					
				}
			}
		}
	}
	
	public void storePlayer() {
		ObjectOutputStream os = null;
		FileOutputStream fos = null;
		try {
			fos = new FileOutputStream("players.txt");			
			os = new ObjectOutputStream(fos);
			for (Player s: players) {
				os.writeObject(s);
			}
			
		} catch (FileNotFoundException e) {
			
		} catch (IOException e) {			
			e.printStackTrace();
		}finally {
			try {os.close(); fos.close();}catch (Exception e) {
			}
		}
	}
	
	public void loadPlayers() {
		
		
		ObjectInputStream is = null;
		FileInputStream fis = null;		
		int pos=0;
		try {
			fis = new FileInputStream("players.txt");			
			is = new ObjectInputStream(fis);			
			while (fis.available()>0) {				
				Player s = (Player)is.readObject();				
				this.players[pos++] = s;
			}
			
			
		} catch (FileNotFoundException e) {
		
		} catch (IOException e) {			
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			
		}finally {
			try {is.close(); fis.close();}catch (Exception e) {
			}
		}
	}
	
	
		
	
}