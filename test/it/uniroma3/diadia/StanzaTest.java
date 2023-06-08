package it.uniroma3.diadia;
import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class StanzaTest {
	
	private Stanza stanza;
	private Attrezzo attrezzo;
	
	
	@BeforeEach 
	void setUp() {
		this.stanza= new Stanza("atrio");
		Stanza adiacente= new Stanza("biblioteca");
		this.stanza.impostaStanzaAdiacente(Direzione.valueOf("nord"), adiacente);
		
		this.attrezzo = new Attrezzo("spada", 8);
		this.stanza.addAttrezzo(this.attrezzo);
		
	}
	@Test 
	public void testStanzaNotNull() {
		assertNotNull(this.stanza);
	}

	@Test
	public void testGetStanzaAdiacente() {
		assertEquals("biblioteca", this.stanza.getStanzaAdiacente(Direzione.valueOf("nord")).getNome());
		}
	@Test
	public void testAddAttrezzo() {
		assertFalse(this.stanza.addAttrezzo(this.attrezzo));
		}
	
	@Test 
	public void testRemoveAttrezzo() {
		assertTrue(this.stanza.removeAttrezzo(this.attrezzo));
	}
	@Test 
	public void testHasAttrezzo() {
		assertTrue(this.stanza.hasAttrezzo("spada"));
	}
	@Test
	public void testAttrezzoDuplicato() {
		Attrezzo duplicato= new Attrezzo("spada",7);
		assertFalse(this.stanza.addAttrezzo(duplicato));
	}

	
}



