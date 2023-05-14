package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBuiaTest {
	private Stanza stanza;
	@BeforeEach
	void setUp() {
		Stanza cantina= new StanzaBuia("cantina","torcia");
		this.stanza=cantina;
		Attrezzo torcia= new Attrezzo("torcia",6);
		this.stanza.addAttrezzo(torcia);
		
		
	}
	@Test
	void testStanzaBuiaNoNull() {
		assertNotNull(this.stanza);
	}
	@Test 
	void testGetDescrizione() {
		Stanza stanza= new Stanza("cantina");
		Attrezzo torcia= new Attrezzo("torcia",6);
		stanza.addAttrezzo(torcia);
		assertEquals(stanza.getDescrizione(),this.stanza.getDescrizione());
		this.stanza.removeAttrezzo(torcia);
		assertEquals("qui c'è buio pesto", this.stanza.getDescrizione());
	}

}
