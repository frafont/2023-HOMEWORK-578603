package it.uniroma3.diadia;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.attrezzi.Attrezzo;

class LabirintoBuilderTest {
	private LabirintoBuilder labirintoBuilder;
	private String stanzaIniziale= "atrio";
	private String stanzaVincente= "biblioteca";
	
	@BeforeEach
	public void setUp() {
		labirintoBuilder= new LabirintoBuilder();
	}
	@Test
	void testLabirintoMonolocale() {
		Labirinto monolocale= labirintoBuilder
							.addStanzaIniziale(stanzaIniziale)
							.addStanzaVincente(stanzaIniziale)
							.getLabirinto();
		assertEquals(this.stanzaIniziale,monolocale.getStanzaCorrente().getNome());
		assertEquals(this.stanzaIniziale,monolocale.getStanzaVincente().getNome());	
	}
	
	@Test
	void testLabirintoMonolocaleConAttrezzoDuplicato() {
		Labirinto monolocale= labirintoBuilder
								.addStanzaIniziale(stanzaIniziale)
								.addAttrezzo("spada", 8)
								.addStanzaVincente(stanzaIniziale)
								.addAttrezzo("spada", 8)
								.getLabirinto();
		int numeroAttrezzi= monolocale.getStanzaCorrente().getAttrezzi().size();
		assertEquals(1,numeroAttrezzi);
	}
	
	@Test
	void testLabirintoBilocale() {
		Labirinto bilocale= labirintoBuilder
							.addStanzaIniziale(stanzaIniziale)
							.addStanzaVincente(stanzaVincente)
							.addAdiacenza(stanzaIniziale, stanzaVincente, "nord")
							.getLabirinto();
		assertEquals(stanzaIniziale,bilocale.getStanzaCorrente().getNome());
		assertEquals(stanzaVincente,bilocale.getStanzaVincente().getNome());
		assertEquals(stanzaVincente, bilocale.getStanzaCorrente().getStanzaAdiacente("nord").getNome());
	}
	
	@Test
	void testLabirintoBilocaleConAttrezziDuplicati() {
		Labirinto bilocale= labirintoBuilder
							.addStanzaIniziale(stanzaIniziale)
							.addAttrezzo("lanterna", 5)
							.addAdiacenza(stanzaIniziale, stanzaVincente, "sud")
							.addStanzaVincente(stanzaVincente)
							.addAttrezzo("lanterna", 5)
							.getLabirinto();
		assertEquals("[lanterna]",bilocale.getStanzaCorrente().getAttrezzi().toString());
	}
	@Test
	public void testTrilocale(){
		Labirinto trilocale = labirintoBuilder
				.addStanzaIniziale(stanzaIniziale).addAttrezzo("osso", 1)
				.addStanza("biblioteca")
				.addAdiacenza(stanzaIniziale, "biblioteca", "sud")
				.addAdiacenza("biblioteca", stanzaIniziale, "nord")
				.addAttrezzo("libro", 10)
				.addStanzaVincente(stanzaVincente)
				.addAdiacenza("biblioteca", stanzaVincente, "est")
				.addAdiacenza(stanzaVincente,"biblioteca" , "ovest")
				.getLabirinto();	
		assertEquals(stanzaIniziale, trilocale.getStanzaCorrente().getNome());
		assertEquals(stanzaVincente, trilocale.getStanzaVincente().getNome());
		assertEquals("biblioteca",trilocale.getStanzaCorrente().getStanzaAdiacente("sud").getNome());
	}
	
	@Test
	public void testTrilocaleConStanzaDuplicata() {
				labirintoBuilder
				.addStanzaIniziale(stanzaIniziale)
				.addStanza("bagno")
				.addStanza("bagno")
				.addAdiacenza(stanzaIniziale, "bagno", "nord")
				.getLabirinto();
		assertTrue(labirintoBuilder.getMappStanze().size()<=2);
	}
	@Test
	public void testCambioStanzaIniziale() {
		Labirinto modificabile= labirintoBuilder
								.addStanzaIniziale(stanzaIniziale)
								.addAttrezzo("libro", 4)
								.addStanza("laboratorio")
								.addAdiacenza(stanzaIniziale, "laboratorio", "ovest")
								.addStanzaIniziale("mensa")
								.addStanzaVincente(stanzaVincente)
								.getLabirinto();
		assertFalse(modificabile.getStanzaCorrente().getAttrezzi().size()==2);
		assertEquals("mensa", modificabile.getStanzaCorrente().getNome());

	}
	
	@Test
	public void testStanzaConPiù4Uscite(){
		Labirinto bizzarro= labirintoBuilder
							.addStanzaIniziale(stanzaIniziale)
							.addStanza("salotto")
							.addStanza("cucina")
							.addStanza("bagno")
							.addStanza("cantina")
							.addStanzaVincente(stanzaVincente)
							.addAdiacenza(stanzaIniziale, stanzaVincente, "nord")
							.addAdiacenza(stanzaIniziale, "cucina", "sud")
							.addAdiacenza(stanzaIniziale, "salotto", "est")
							.addAdiacenza(stanzaIniziale, "bagno", "ovest")
							.addAdiacenza(stanzaIniziale, "cantina", "nord")
							.getLabirinto();
		assertEquals(stanzaVincente, bizzarro.getStanzaCorrente().getStanzaAdiacente("nord").getNome());
		
	}
	@Test
	public void testLabirintoConStanzaMagica() {
		Labirinto magico= labirintoBuilder
							.addStanzaIniziale(stanzaIniziale)
							.addStanzaMagica("stanza magica",0)
							.addAttrezzo("spada",8)
							.addAdiacenza(stanzaIniziale, "stanza magica", "nord")
							.addStanzaVincente(stanzaVincente)
							.getLabirinto();
		assertEquals("[adaps]", magico.getStanzaCorrente().getStanzaAdiacente("nord").getAttrezzi().toString());
		assertEquals(16, magico.getStanzaCorrente().getStanzaAdiacente("nord").getAttrezzo("adaps").getPeso());
	}
	
	@Test
	public void testLabirintoConStanzaBuia() {
		Labirinto buio= labirintoBuilder
						.addStanzaIniziale(stanzaIniziale)
						.addStanzaBuia("stanza buia", "lanterna")
						.addAdiacenza(stanzaIniziale, "stanza buia", "sud")
						.addStanzaVincente(stanzaVincente)
						.addAdiacenza(stanzaIniziale, stanzaVincente, "nord")
						.getLabirinto();
		
		assertEquals("qui c'è buio pesto" , buio.getStanzaCorrente().getStanzaAdiacente("sud").getDescrizione());
	}
	
	
	@Test
	public void testLabirintoConStanzaBloccata(){
		Labirinto banca= labirintoBuilder
						.addStanzaIniziale(stanzaIniziale)
						.addStanza("stanza del bottino")
						.addStanzaBloccata("caveau", "nord", "codice")
						.addAdiacenza("caveau", "stanza del bottino", "nord")
						.addAdiacenza(stanzaIniziale, "caveau", "est")
						.getLabirinto();
		assertFalse("stanza del bottino"==labirintoBuilder.getUltimaStanza().getStanzaAdiacente("nord").getNome());
		Attrezzo codice= new Attrezzo("codice",3);
		labirintoBuilder.getUltimaStanza().addAttrezzo(codice);
		assertTrue(banca.getStanzaCorrente().getStanzaAdiacente("est").getAttrezzi().contains(codice));
		assertTrue("stanza del bottino"==labirintoBuilder.getUltimaStanza().getStanzaAdiacente("nord").getNome());
	}
		

}
