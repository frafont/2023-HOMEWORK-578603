package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;

class LabirintoTest {
	
	private Labirinto labirinto;
	
	@BeforeEach 
	public void setUp() {
		this.labirinto= new Labirinto();
		LabirintoBuilder costruttore= this.labirinto.newBuilder();
		costruttore.addStanzaIniziale("salotto");
		costruttore.addStanzaVincente("cucina");
		this.labirinto=costruttore.getLabirinto();
	}
	
	@Test
	void testGetStanzaVincente(){
		assertEquals("cucina", this.labirinto.getStanzaVincente().getNome());
	}
	@Test 
	void testGetStanzaIniz() {
		assertEquals("salotto", this.labirinto.getStanzaCorrente().getNome());
	}
	@Test 
	void testLabirintoNoNull() {
		assertNotNull(this.labirinto);
	}
	
	  
}


