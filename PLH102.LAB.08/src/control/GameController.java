package control;

import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

import javax.swing.JOptionPane;
import javax.swing.Timer;

import model.GameModel;
import model.PlayersCatalogue;
import view.MainAreaPanel;
import view.MainWindow;
import model.Player;
public class GameController extends WindowAdapter {
	MainWindow view;
	GameModel model;
	Timer timerbean;
	Timer timerhal;
	
	public GameController() {		
		this.timerbean =new Timer(1000,(event)->{
			if(this.getModel().inPlay()) {
				this.getModel().mrBean();
				//this.timerhal.restart();
			}
		});
		timerbean.setRepeats(false);
		this.timerhal= new Timer(1000,(event)->{
			if(this.getModel().inPlay()) {
				this.getModel().hal();
				//this.timerbean.restart();
			}
		});
		timerhal.setRepeats(false);
	}
	
	@Override
	public void windowClosing(WindowEvent event) {
		quit();
	}
	
	
	public void setView(MainWindow view) {
		this.view = view;
	}

	public void setModel(GameModel model) {
		this.model = model;
	}

	public Timer getTimerbean() {
		return timerbean;
	}

	public void setTimerbean(Timer timerbean) {
		this.timerbean = timerbean;
	}

	public Timer getTimerhal() {
		return timerhal;
	}

	public void setTimerhal(Timer timerhal) {
		this.timerhal = timerhal;
	}

	public void start() {
		
		this.view= new MainWindow(this);
		this.model = new GameModel(this);
		/*this.getModel().getPlayerCatalogue().loadPlayers();*/
		this.view.addWindowListener(this);
		this.view.setVisible(true);	
		
	
	}
	
	
	public void quit() {		
		System.out.println("GAME FINISHED");		
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
		this.getModel().setMover(false);
		if(this.getModel().getPlayerCatalogue().getCurrentPlayers()[1].getName().equals("Mr Bean")==true && this.getModel().getPlayerCatalogue().getCurrentPlayers()[0].getName().equals("Hal")) {
			this.timerbean.restart();
		    return;
		}
		if(this.getModel().getPlayerCatalogue().getCurrentPlayers()[0].getName().equals("Mr Bean")==true && this.getModel().getPlayerCatalogue().getCurrentPlayers()[1].getName().equals("Hal")) {
			this.timerhal.restart();
			return;
		}
		if(this.getModel().getPlayerCatalogue().getCurrentPlayers()[1].getName().equals("Mr Bean")==true) 
			this.getModel().mrBean();
		if(this.getModel().getPlayerCatalogue().getCurrentPlayers()[1].getName().equals("Hal")==true) 
			this.getModel().hal();	
		
		
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
		this.getModel().getPlayerCatalogue().storePlayers();
	}
	
	public void doneButton() {
		this.getModel().getPlayerCatalogue().getCurrentPlayers()[0]=null;
		this.getModel().getPlayerCatalogue().getCurrentPlayers()[1]=null;
		this.getModel().getPlayerCatalogue().setNumOfCurrentPlayers(0);
		
		this.getView().getLeftPanel().getSelectPlayerBtn().setEnabled(this.getModel().ready());
		this.getView().getRightPanel().getSelectPlayerBtn().setEnabled(this.getModel().ready());
		this.getView().getMainPanel().showCard(MainAreaPanel.HOF);
	}
	
}
