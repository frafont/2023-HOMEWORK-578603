package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.StanzaMagica;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class StanzaMagicaTest {
	private StanzaMagica stanzaMagica;
	@BeforeEach
	void setUp() {
		stanzaMagica=new StanzaMagica("magica",2);
		Attrezzo attr1= new Attrezzo("lanterna",5);
		Attrezzo attr2= new Attrezzo("spada",10);
		Attrezzo attr3= new Attrezzo("libro",8);
		stanzaMagica.addAttrezzo(attr1);
		stanzaMagica.addAttrezzo(attr2);
		stanzaMagica.addAttrezzo(attr3);
		
		
		
	}
	@Test
	void testComportamentoMagico() {
		assertTrue(stanzaMagica.hasAttrezzo("orbil"));
		assertFalse(stanzaMagica.hasAttrezzo("adaps"));
	}

}
