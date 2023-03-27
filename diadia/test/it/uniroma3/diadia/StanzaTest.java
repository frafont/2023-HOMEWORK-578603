package it.uniroma3.diadia;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;






public class StanzaTest {
	
	private Stanza stanza;
	private Attrezzo spada;
	
	
	@BeforeEach 
	void setUp() {
		this.stanza= new Stanza("atrio");
		Stanza adiacente= new Stanza("biblioteca");
		this.stanza.impostaStanzaAdiacente("nord", adiacente);
		
		spada = new Attrezzo("spada", 8);
		this.stanza.addAttrezzo(spada);
		
	}
	@Test 
	public void testStanzaNotNull() {
		assertNotNull(this.stanza);
	}

	@Test
	public void testGetStanzaAdiacente() {
		assertEquals("biblioteca", this.stanza.getStanzaAdiacente("nord").getNome());
		}
	@Test
	public void testAddAttrezzo() {
		assertTrue(this.stanza.addAttrezzo(spada));
		}
	
	@Test 
	public void testRemoveAttrezzo() {
		assertTrue(this.stanza.removeAttrezzo(spada));
	}
	@Test 
	public void testHasAttrezzo() {
		assertTrue(this.stanza.hasAttrezzo("spada"));
	}

	
}



