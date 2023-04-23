package it.uniroma3.diadia;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

class FabbricaDiComandiFisarmonicaTest {
	
	private FabbricaDiComandiFisarmonica factory;
	@BeforeEach
	void setUp() {
		this.factory= new FabbricaDiComandiFisarmonica();
		Comando comando=factory.costruisciComando("vai nord");
		
	}
	@Test 
	void testRiconoscimentoComandoNonValido() {
		Comando comando= factory.costruisciComando("");
		assertNull(comando.getNome());
		assertNull(comando.getParametro());
	}

	@Test
	void testRiconoscimentoComandoVai() {
		Comando comando=factory.costruisciComando("vai nord");
		assertEquals("vai",comando.getNome());
		assertEquals("nord", comando.getParametro());
	}
	@Test 
	void testRiconoscimentoComandoPrendi() {
		Comando comando=factory.costruisciComando("prendi osso");
		assertEquals("prendi",comando.getNome());
		assertEquals("osso", comando.getParametro());
	}
	@Test 
	void testRiconoscimentoComandoPosa() {
		Comando comando=factory.costruisciComando("posa penna");
		assertEquals("posa",comando.getNome());
		assertEquals("penna", comando.getParametro());
	}
	@Test 
	void testRiconoscimentoComandoGuarda() {
		Comando comando=factory.costruisciComando("guarda");
		assertEquals("guarda",comando.getNome());
		assertNull(comando.getParametro());
	}
	@Test 
	void testRiconoscimentoComandoFine() {
		Comando comando=factory.costruisciComando("fine");
		assertEquals("fine",comando.getNome());
		assertNull(comando.getParametro());
	}
	@Test 
	void testRiconoscimentoComandoAiuto() {
		Comando comando=factory.costruisciComando("aiuto");
		assertEquals("aiuto",comando.getNome());
		assertNull(comando.getParametro());
	}
	


}
