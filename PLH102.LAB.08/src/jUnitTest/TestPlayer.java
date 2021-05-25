package jUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import model.Game;
import model.Player;
import model.PlayersCatalogue;

import java.time.LocalDateTime;
class TestPlayer {
Player p1;
Player p2;
Player p3;
Game g1;
Game g2;
Game g3;
Game g4;
PlayersCatalogue pc;
	@BeforeEach
    void setup() {
         p1=new Player("Kostaki",10,5,5,0);
         p2=new Player("Chris",10,10,0,0);
         p3=new Player("Nikos",10,0,10,0);
         g1=new Game(p1,p2,1,LocalDateTime.now());
         g2=new Game(p1,p3,1,LocalDateTime.now());
         g3=new Game(p1,p2,-1,LocalDateTime.now());
         g4=new Game(p1,p2,-1,LocalDateTime.now().minusHours(1));
         p1.addBestGame(g3);
         p1.addBestGame(g4);
         p1.addBestGame(g1);
         p1.addBestGame(g2);
    }
   
	@Test
    void testCalcScore() {
    	assertEquals(p1.getScore(),50);
    	assertEquals(p2.getScore(),100);
    	assertEquals(p3.getScore(),0);
    	
    }
	
	@Test 
	void testCompareGames() {
		assertEquals(p1.compareGames(g1, g2),1);
		assertEquals(p1.compareGames(g3, g2),2);
		assertEquals(p1.compareGames(g3, g4),1);
	}

	@Test
	void addAndSortGames() {
		assertEquals(p1.getBestGames()[0],g1);
		assertEquals(p1.getBestGames()[1],g2);
		assertEquals(p1.getBestGames()[2],g3);
		assertEquals(p1.getBestGames()[3],g4);
	}
}
