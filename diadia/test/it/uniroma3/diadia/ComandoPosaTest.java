package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoPosa;
import it.uniroma3.diadia.giocatore.Borsa;

class ComandoPosaTest {

	private Comando comando;
	private Stanza stanza;
	private Attrezzo attrezzo;
	private Borsa borsa;
	@BeforeEach
	void setUp() {
		this.comando= new ComandoPosa();
		this.stanza= new Stanza("stanza");
		this.attrezzo= new Attrezzo("penna",2);
		Partita partita= new Partita();
		partita.setStanzaCorrente(this.stanza);
		this.borsa= partita.getGiocatore().getBorsa();
		this.borsa.addAttrezzo(attrezzo);
		this.comando.setParametro(attrezzo.getNome());
		this.comando.esegui(partita);
	}
	@Test
	void testEsegui() {
		assertTrue(this.stanza.hasAttrezzo(this.attrezzo.getNome()));
	}
	@Test 
	void testSetParametro() {
		ComandoPosa that= new ComandoPosa();
		that=(ComandoPosa)comando;
		assertEquals(attrezzo.getNome(),that.getParametro());
	}
}
