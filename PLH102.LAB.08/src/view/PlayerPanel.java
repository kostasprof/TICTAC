package view;
import model.Player;
import model.PlayersCatalogue;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Insets;
import java.util.Arrays;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import control.GameController;

public class PlayerPanel extends GamePanel{
	
	JButton selectPlayerBtn;
	int pos;
	String currentPlayer;
	JTextField plName;
	JLabel plMark;
	JTextArea plStats;
	int numOfCurrentPlayers;
	
	public PlayerPanel(GameController c, int pos) {
		super(c);
		this.pos=pos;		
		this.setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		this.setPreferredSize(new Dimension(MainWindow.PLAYER_WIDTH, MainWindow.HEIGHT-MainWindow.TOP_HEIGHT));
		this.setBorder(new LineBorder(Color.GRAY,1,true));
		this.setAlignmentX(CENTER_ALIGNMENT);
		selectPlayerBtn = new JButton("Choose Player");
		selectPlayerBtn.setPreferredSize(new Dimension(150,40));
		selectPlayerBtn.setAlignmentX(CENTER_ALIGNMENT);
		selectPlayerBtn.addActionListener((e)->{changePlayer();});
		this.add(selectPlayerBtn);
		numOfCurrentPlayers=0;
		
		
		JButton AddPlayerBtn = new JButton("Choose Player");
		AddPlayerBtn.setPreferredSize(new Dimension(150,40));
		AddPlayerBtn.setAlignmentX(CENTER_ALIGNMENT);
		AddPlayerBtn.addActionListener((e)->{changePlayer();});
		this.add(selectPlayerBtn);
		
		plName = new JTextField();
		plName.setPreferredSize(new Dimension(MainWindow.PLAYER_WIDTH,40));
		plName.setMaximumSize(plName.getPreferredSize() );
		plName.setAlignmentX(CENTER_ALIGNMENT);
		plName.setHorizontalAlignment(JTextField.CENTER);
		plName.setEnabled(false);
		
		plMark = new JLabel(pos==0? "X" : "O");
		plMark.setPreferredSize(new Dimension(MainWindow.PLAYER_WIDTH,80));
		plMark.setAlignmentX(CENTER_ALIGNMENT);
		plMark.setHorizontalAlignment(JTextField.CENTER);
		plMark.setEnabled(false);
		Font markf = new Font("SansSerif", Font.BOLD,90);
		plMark.setFont(markf);
		
		plStats = new JTextArea(10,100);		
		plStats.setPreferredSize(new Dimension(MainWindow.PLAYER_WIDTH,400));
		plStats.setAlignmentX(CENTER_ALIGNMENT);
		Font statsf = new Font("SansSerif", Font.BOLD,20);
		plStats.setFont(statsf);
		plStats.setEnabled(false);		
		plStats.setMargin(new Insets(10, 10, 10, 10));
		this.add(plMark);
		this.add(plName);		
		this.add(plStats);
	}

	public void changePlayer() {
		String[] allPlayers = new String[100];
		for(int i=0;i<gc.getModel().getPlayerCatalogue().getNumofplayers();i++) {
			if(gc.getModel().getPlayerCatalogue().getPlayerName(i)!=null) {
			allPlayers[i]=gc.getModel().getPlayerCatalogue().getPlayerName(i);
			System.out.println(allPlayers[i]);
			}
		}
		//Arrays.sort(allPlayers);
        
		String selPlayer = (String) JOptionPane.showInputDialog(this, 
				"Choose a Player...",
				"Player selection",
				JOptionPane.PLAIN_MESSAGE,
				null,
				allPlayers,
				currentPlayer
				);
				
		if(selPlayer != null) {
			if (selPlayer.equals(gc.getModel().getGamePlayers()[pos==0?1:0])) {
				JOptionPane.showMessageDialog(gc.getView(), 						
						"Player already selected",
						"Ooops...",
						JOptionPane.ERROR_MESSAGE);
				return;
			}
			Player p= gc.getModel().getPlayerCatalogue().findPlayerByName(selPlayer);
			
			gc.getModel().getPlayerCatalogue().setCurrentPlayers(gc.getModel().getPlayerCatalogue().getNumOfCurrentPlayers(),p);
            numOfCurrentPlayers++;
			this.currentPlayer=selPlayer;			
			gc.selectPlayer(selPlayer,pos);
			this.plName.setText(currentPlayer);
			this.setPlayerStats(gc.getModel().getPlayerStats(currentPlayer));
			this.repaint();
		}
	}	

	public int getPos() {
		return pos;
	}

	public void setPos(int pos) {
		this.pos = pos;
	}

	public JTextField getPlName() {
		return plName;
	}
	
	public JTextArea getPlStats() {
		return plStats;
	}
	
	public void setPlayerStats(String stats) {
		this.plStats.setText(stats);
	}

	public JButton getSelectPlayerBtn() {
		return selectPlayerBtn;
	}

	
	
}
