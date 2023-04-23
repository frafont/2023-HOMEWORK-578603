package it.uniroma3.diadia.ambienti;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaBloccataTest {
	private StanzaBloccata stanzaIniziale;
	private Attrezzo chiave;
	private Stanza bloccata;
	@BeforeEach
	void setUp() {
		this.stanzaIniziale= new StanzaBloccata("banca","nord","codice");
		this.chiave=new Attrezzo("codice",15);
		this.bloccata= new Stanza("caveau");
		this.stanzaIniziale.impostaStanzaAdiacente("nord",this.bloccata);
		this.stanzaIniziale.setDirezioneBloccata("nord");
		
		
	}
	@Test
	void testGetStanzaAdiacenteBloccata() {
		assertEquals(this.stanzaIniziale,this.stanzaIniziale.getStanzaAdiacente("nord"));
	}
	@Test
	void testGetStanzaAdiacenteSbloccata() {
		this.stanzaIniziale.addAttrezzo(this.chiave);
		assertEquals(this.bloccata,this.stanzaIniziale.getStanzaAdiacente("nord"));
	}
	@Test 
	void testGetStanzaAdiacenteBloccata2() {
		Attrezzo spada= new Attrezzo("spada",8);
		this.stanzaIniziale.addAttrezzo(spada);
		assertEquals(this.stanzaIniziale, this.stanzaIniziale.getStanzaAdiacente("nord"));
	}
	@Test
	void testinfoDescrizioneinfoAttrezzo() {
		assertEquals("direzione bloccata: nord",this.stanzaIniziale.infoDirezione());
		assertEquals("attrezzo sbloccante: codice",this.stanzaIniziale.infoAttrezzo());
	}
	@Test 
	void testGetDescrizioneStanzaNonBloccata() {
		Stanza stanzaTest= new Stanza("banca");
		stanzaTest.addAttrezzo(chiave);
		stanzaTest.impostaStanzaAdiacente("nord", this.bloccata);
		this.stanzaIniziale.addAttrezzo(chiave);
		assertEquals(stanzaTest.getDescrizione(),this.stanzaIniziale.getDescrizione());
	}
	@Test 
	void testGetDescrizioneStanzaBloccata(){
		StanzaBloccata stanzaTest= new StanzaBloccata("banca", null, null);
		stanzaTest.setAttrezzoSbloccante("codice");
		stanzaTest.setDirezioneBloccata("nord");
		stanzaTest.impostaStanzaAdiacente("nord", this.bloccata);
		assertEquals(stanzaTest.getDescrizione(),this.stanzaIniziale.getDescrizione());
		
	}

}
