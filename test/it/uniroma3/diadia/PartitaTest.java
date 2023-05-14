package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;

class PartitaTest {
	
	private Partita partita;
	
		
	@BeforeEach 
	public void setup() {
		Labirinto  l= new Labirinto();
		this.partita=new Partita(l);
		Stanza finale= new Stanza("fianle");
		this.partita.setStanzaCorrente(finale);
		this.partita.getLab().setStanzaFinal(finale);
	}
	@Test
	void testPartitaNotNull() {
		assertNotNull(this.partita);
	}
	@Test 
	void testPartitaVinta() {
		assertTrue(this.partita.vinta());
	}
	@Test 
	void testIsFinita() {
		assertTrue(this.partita.vinta());
	}
}
