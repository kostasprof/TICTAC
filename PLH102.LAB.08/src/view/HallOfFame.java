package view;

import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GraphicsEnvironment;
import java.awt.Toolkit;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;

import javax.swing.JPanel;

import control.GameController;

public class HallOfFame extends GamePanel{
private GameController gc;
	
	public HallOfFame(GameController gc) {
		super(gc);
		this.gc=gc;
	}
	
	@Override
	public void paintComponent(Graphics g)   {  
	      super.paintComponent(g);
	      int x = this.getWidth()/2-50;
			int y = 10;		
			g.drawString("House Of Fame", x, y);
			gc.getModel().getPlayerCatalogue().sortPlayers();
			x=this.getWidth()/2 -200;
			
			for(int i=0;i<10;i++) {
				if(gc.getModel().getPlayerCatalogue().getPlayers()[i]==null) {
					return;
				}
				for(int j=0;j<10;j++) {
					y+=5;
				}
				
				g.drawString(i+1+". "+gc.getModel().getPlayerCatalogue().getPlayers()[i].getName()+"                         "+gc.getModel().getPlayerCatalogue().getPlayers()[i].getScore(),x,y);
				
			}
			
	      
	}
	
	

}
