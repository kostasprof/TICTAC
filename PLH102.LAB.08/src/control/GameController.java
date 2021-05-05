package control;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;

import model.GameModel;
import model.PlayersCatalogue;
import view.MainAreaPanel;
import view.MainWindow;
import model.Player;
public class GameController extends WindowAdapter {
	MainWindow view;
	GameModel model;
	
	public GameController() {		
		
	}
	
	@Override
	public void windowClosing(WindowEvent event) {
		quit();
	}
	
	
	public void start() {
		this.view= new MainWindow(this);
		this.model = new GameModel(this);
		this.view.addWindowListener(this);
		this.view.setVisible(true);	
		/*this.getModel().getPlayerCatalogue().loadPlayers();*/
	
	}
	
	
	public void quit() {		
		System.out.println("bye bye...");		
		System.exit(0);
	}
	
	
	public void selectPlayer(String p, int pos) {
		this.model.selectPlayer(p, pos);	
		System.out.println("Player " + pos + " set to " + p);
		this.view.getTopPanel().getStartBtn().setEnabled(model.ready());		
	}
	
	public void startGame() {
		this.model.setGameBoard(new String[3][3]);
		this.view.getTopPanel().getStartBtn().setEnabled(false);
		this.view.getTopPanel().getAddPlayerBtn();
		this.view.getMainPanel().showCard(MainAreaPanel.BOARD);
		this.view.getLeftPanel().getSelectPlayerBtn().setEnabled(model.NoPlay());
		this.view.getRightPanel().getSelectPlayerBtn().setEnabled(model.NoPlay());
	}
	
	public GameModel getModel() {
		return model;
	}
	
	public MainWindow getView() {
		return view;
	}
	
	public void addPlayer(GameController gc) {
		
		String name = JOptionPane.showInputDialog(gc, "What's your name?");
		String games = JOptionPane.showInputDialog(gc, "How many games have you played?");
		int g=Integer.parseInt(games);
		String wins = JOptionPane.showInputDialog(gc, "How many wins do you have ?");
		int w=Integer.parseInt(wins);
		String losses = JOptionPane.showInputDialog(gc, "How many losses do you have?");
		int l=Integer.parseInt(losses);
		String draws = JOptionPane.showInputDialog(gc, "How many draws do you have?");
		int d=Integer.parseInt(draws);
		this.getModel().getPlayerCatalogue().addPlayer(new Player(name,g,w,l,d));
	}
	
}
