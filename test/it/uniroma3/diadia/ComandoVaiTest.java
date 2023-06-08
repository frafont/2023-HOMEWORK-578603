package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Direzione;
import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.ComandoVai;

class ComandoVaiTest {
	private Comando comando;
	private Partita partita;
	@BeforeEach 
	void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		this.comando=new ComandoVai();
		Stanza stanzaCorrente= new Stanza("inizio");
		Labirinto l= Labirinto.newBuilder("labirinto1.txt").getLabirinto();
		this.partita= new Partita(l);
		this.partita.setStanzaCorrente(stanzaCorrente);
		Stanza stanzaFinale= new Stanza("finale");
		stanzaCorrente.impostaStanzaAdiacente(Direzione.nord, stanzaFinale);
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
