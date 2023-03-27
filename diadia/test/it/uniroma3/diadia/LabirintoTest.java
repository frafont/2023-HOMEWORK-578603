package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

class LabirintoTest {
	
	private Labirinto labirinto;
	
	@BeforeEach 
	public void setUp() {
		this.labirinto= new Labirinto();
		Stanza vincente=new Stanza("vincente");
		this.labirinto.setStanzaFinal(vincente);
		Stanza iniziale= new Stanza("inizio");
		this.labirinto.setStanzaIniz(iniziale);
	}
	
	@Test
	void testGetStanzaVincente(){
		assertEquals("vincente", this.labirinto.getStanzaFinal().getNome());
	}
	@Test 
	void testGetStanzaIniz() {
		assertEquals("inizio", this.labirinto.getStanzaIniz().getNome());
	}
	@Test 
	void testLabirintoNoNull() {
		assertNotNull(this.labirinto);
	}
	
	  
}


