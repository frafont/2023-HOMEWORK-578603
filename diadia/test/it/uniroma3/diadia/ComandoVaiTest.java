package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoVai;

class ComandoVaiTest {
	private Comando comando;
	private Partita partita;
	@BeforeEach 
	void setUp() {
		this.comando=new ComandoVai();
		Stanza stanzaCorrente= new Stanza("inizio");
		this.partita= new Partita();
		this.partita.setStanzaCorrente(stanzaCorrente);
		Stanza stanzaFinale= new Stanza("finale");
		stanzaCorrente.impostaStanzaAdiacente("nord", stanzaFinale);
		this.comando.setParametro("nord");
		this.comando.esegui(partita);
		
	}
	
	@Test
	void testComandoNotNull(){
		assertNotNull(this.comando);
	}
	@Test
	void testGetNome() {
		assertEquals("vai",this.comando.getNome());
	}
	@Test 
	void testEsegui() {
		assertEquals("finale",this.partita.getStanzaCorrente().getNome());
	}
	@Test 
	void testGetParametro() {
		assertEquals("nord",this.comando.getParametro());
	}

}
