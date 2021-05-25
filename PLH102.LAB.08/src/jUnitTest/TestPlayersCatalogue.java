package jUnitTest;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

import model.GameModel;
import model.Player;
import model.PlayersCatalogue;

import org.junit.jupiter.api.BeforeEach;


class TestPlayersCatalogue {
Player p1;
Player p2;
Player p3;
Player p4;
Player p5;
Player p6;
PlayersCatalogue pc;
	@BeforeEach
	void setup() {
		pc=new PlayersCatalogue();
		 p1=new Player("Kostaki",10,5,5,0);
         p2=new Player("Chris",10,10,0,0);
         p3=new Player("Nikos",10,0,10,0);
         p4=new Player("Haris",10,5,4,1);
         p5=new Player("Marika",10,7,3,0);
         p6=new Player("Mixalis",10,6,3,1);
         pc.addPlayer(p6);
         pc.addPlayer(p5);
         pc.addPlayer(p3);
         pc.addPlayer(p4);
         pc.addPlayer(p1);
         pc.addPlayer(p2);
         
		
	}

	@Test
	void testSortPlayers(){
		pc.sortPlayers();
		assertEquals(pc.getPlayers()[0],p2);
		assertEquals(pc.getPlayers()[1],p5);
		assertEquals(pc.getPlayers()[2],p6);
		assertEquals(pc.getPlayers()[3],p4);
		assertEquals(pc.getPlayers()[4],p1);
		assertEquals(pc.getPlayers()[5],p3);
	}
}
