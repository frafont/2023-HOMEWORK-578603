package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoPrendi;

class ComandoPrendiTest{
	
	private Comando comando;
	private Stanza stanza;
	private Attrezzo attrezzo;
	@BeforeEach
	void setUp() {
		this.comando= new ComandoPrendi();
		this.stanza= new Stanza("stanza");
		this.attrezzo= new Attrezzo("penna",2);
		this.stanza.addAttrezzo(this.attrezzo);
		Labirinto l= new Labirinto();
		Partita partita= new Partita(l);
		partita.setStanzaCorrente(this.stanza);
		this.comando.setParametro(attrezzo.getNome());
		this.comando.esegui(partita);
	}
	@Test
	void testEsegui() {
		assertFalse(this.stanza.hasAttrezzo(this.attrezzo.getNome()));
	}
	@Test 
	void testSetParametro() {
		ComandoPrendi that= new ComandoPrendi();
		that=(ComandoPrendi)comando;
		assertEquals(attrezzo.getNome(),that.getParametro());
	}

}
