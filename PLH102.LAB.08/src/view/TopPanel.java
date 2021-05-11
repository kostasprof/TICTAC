package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.io.FileNotFoundException;

import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import control.GameController;
import java.util.Scanner;
public class TopPanel extends GamePanel{	
	JButton quitBtn;
	JButton startGameBtn;
	JButton doneBtn;
	JButton addPlayerBtn;
	Scanner input = new Scanner(System.in);
	String name; 
	
	
	public TopPanel(GameController gc) {
		super(gc);
		this.setLayout(new FlowLayout(FlowLayout.CENTER));
		this.setPreferredSize(new Dimension(MainWindow.WIDTH,MainWindow.TOP_HEIGHT));
		this.setBorder(new LineBorder(Color.GRAY,1,true));
		quitBtn = new JButton("Quit App");	
		quitBtn.setPreferredSize(new Dimension(100, 40));
		quitBtn.addActionListener((e)->{this.gc.quit();});		
		
		startGameBtn = new JButton("Start Game");
		startGameBtn.setPreferredSize(new Dimension(100, 40));
		startGameBtn.setEnabled(false);
		startGameBtn.addActionListener((e)->{this.gc.startGame();});
		
		
		addPlayerBtn = new JButton("Add Player");
		addPlayerBtn.setPreferredSize(new Dimension(100, 40));
		addPlayerBtn.addActionListener((e)->{this.gc.addPlayer(gc);});
		
		doneBtn = new JButton("Done");		
		doneBtn.setPreferredSize(new Dimension(100, 40));		
		doneBtn.setEnabled(true);
		doneBtn.addActionListener((e)->{this.gc.doneButton();});
		
		add(startGameBtn);
		add(doneBtn);
		add(quitBtn);
		add(addPlayerBtn);
	}

	public JButton getAddPlayerBtn() {
		return addPlayerBtn;
	}

	public JButton getQuitBtn() {
		return quitBtn;
	}

	public JButton getStartBtn() {
		return startGameBtn;
	}
	

	public JButton getDoneBtn() {
		return doneBtn;
	}	
	
}
