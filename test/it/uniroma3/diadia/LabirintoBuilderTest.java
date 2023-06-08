package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Labirinto.LabirintoBuilder;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class LabirintoBuilderTest {
	Labirinto.LabirintoBuilder labirintoBuilder;
	
	@BeforeEach
	public void setUp() throws FileNotFoundException, FormatoFileNonValidoException {
		labirintoBuilder= new LabirintoBuilder("labirinto1.txt");
	}
	@Test
	public void testGetLabirinto() {
		assertNotNull(labirintoBuilder.getLabirinto());
		assertEquals(Labirinto.class, labirintoBuilder.getLabirinto().getClass());
	}

	@Test
	public void testAddStanza() {
		labirintoBuilder.addStanza("stanzetta");
		Stanza expected = new Stanza("stanzetta");
		assertEquals(expected.getNome(), labirintoBuilder.getMappStanze().get("stanzetta").getNome());
	}

	@Test
	public void testAddAttrezzoSenzaUltimaStanzaAggiunta(){
		
		//lb.addAttrezzo("cacciavite", 3);
		//Attrezzo expected = new Attrezzo("cacciavite", 3);
		assertEquals(LabirintoBuilder.class, labirintoBuilder.addAttrezzo("cacciavite", 3).getClass());
	}
	
	@Test
	public void testAddAttrezzoConUltimaStanzaAggiunta(){
		labirintoBuilder.addStanzaIniziale("stanzetta").addAttrezzo("cacciavite", 3);
		Attrezzo expected = new Attrezzo("cacciavite", 3);
		assertEquals(expected, labirintoBuilder.getLabirinto().getStanzaIniziale().getAttrezzo("cacciavite"));		
	}

	@Test
    public void testAddAttrezzoConStanza() {
        labirintoBuilder.addStanza("stanzetta");
        labirintoBuilder.addAttrezzo("cacciavite", 3);
        assertTrue(labirintoBuilder.getMappStanze().get("stanzetta").hasAttrezzo("cacciavite"));
    }
		

}
