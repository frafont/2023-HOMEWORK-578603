package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

class BorsaTest {
	private Borsa borsa;
	private Attrezzo attrezzo;
	
	@BeforeEach 
	public void setUp() {
		this.borsa=new Borsa(20);
		this.attrezzo= new Attrezzo("attrezzo",21);
		
		Attrezzo rimuovere= new Attrezzo("rimuovere", 5);
		this.borsa.addAttrezzo(rimuovere);
		
		Attrezzo aggiunto= new Attrezzo("aggiunto", 8);
		this.borsa.addAttrezzo(aggiunto);
		
		Attrezzo trovato= new Attrezzo("trovato", 2);
		this.borsa.addAttrezzo(trovato);
	}
	@Test
	void testBorsaNotNull() {
		assertNotNull(this.borsa);
	}
	@Test 
	void testAddAttrezzo() {
		assertFalse(this.borsa.addAttrezzo(attrezzo));
	}
	@Test 
	void testRemoveAttrezzo() {
		assertTrue(this.borsa.removeAttrezzo("rimuovere"));
	}
	@Test 
	void testGetPeso() {
		assertEquals(15,this.borsa.getPeso());
	}
	@Test 
	void testHasAttrezzo() {
		assertTrue(this.borsa.hasAttrezzo("trovato"));
	}

}
