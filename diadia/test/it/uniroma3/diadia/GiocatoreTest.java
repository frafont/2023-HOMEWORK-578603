package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.giocatore.Giocatore;

class GiocatoreTest {
	private Giocatore giocatore;
	private int cfu=20;
	@BeforeEach
	void setUp() {
		this.giocatore=new Giocatore();
		this.giocatore.setCfu(cfu);
	}
	@Test
	void testGiocatoreNotNull() {
		assertNotNull(this.giocatore);
	}
	@Test 
	void testGetCfu() {
		assertEquals(cfu,this.giocatore.getCfu());
	}
	@Test 
	void testGetBorsa(){
		assertNotNull(this.giocatore.getBorsa());
	}
	@Test 
	void testInfoGiocatore() {
		assertEquals(20, this.giocatore.getCfu());
	}

}
