package it.uniroma3.diadia;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class IOSimulatorTest {
	private IOSimulator io;
	private List<String> comandiLetti;
	@BeforeEach
	void setUp() {
		this.comandiLetti=new ArrayList<String>();
	}
	@Test
	void testUnComando() {
		this.comandiLetti.add("fine");
		this.io=new IOSimulator(comandiLetti);
		assertEquals("fine",io.leggiRiga());
	}
	@Test
	void testDueComandi() {
		this.comandiLetti.add("vai sud");
		this.comandiLetti.add("guarda");
		this.io= new IOSimulator(comandiLetti);
		assertEquals("vai sud", io.leggiRiga());
		assertEquals("guarda", io.leggiRiga());
	}
	
	@Test
	void testMostraMessaggio() {
		io=new IOSimulator(comandiLetti);
		io.mostraMessaggio("grazie di aver giocato");
		
		assertEquals("grazie di aver giocato",io.nextMessaggio());
		assertFalse(io.hasNextMessaggio());
		
	}

}
