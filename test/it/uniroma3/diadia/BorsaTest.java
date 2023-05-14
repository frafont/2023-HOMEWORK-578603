package it.uniroma3.diadia;

import static org.junit.Assert.assertEquals;
import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.giocatore.ComparatoreperPeso;

class BorsaTest {
	private Borsa borsa;
	private Attrezzo attrezzo;
	private List<Attrezzo> attrezzi; 
	
	@BeforeEach 
	public void setUp() {
		this.borsa=new Borsa(30);
		this.attrezzo= new Attrezzo("attrezzo",21);
		this.attrezzi= new ArrayList<Attrezzo>();
		
		Attrezzo rimuovere= new Attrezzo("rimuovere", 5);
		this.borsa.addAttrezzo(rimuovere);
		
		Attrezzo aggiunto= new Attrezzo("aggiunto", 8);
		this.borsa.addAttrezzo(aggiunto);
		
		Attrezzo trovato= new Attrezzo("trovato", 2);
		this.borsa.addAttrezzo(trovato);
		
		Attrezzo stessoPeso= new Attrezzo("duplicato",8);
		this.borsa.addAttrezzo(stessoPeso);
		
		this.attrezzi.add(trovato);
		this.attrezzi.add(rimuovere);
		this.attrezzi.add(aggiunto);
		this.attrezzi.add(stessoPeso);
	}
	@Test
	void testBorsaNotNull() {
		assertNotNull(this.borsa);
	}
	@Test 
	void testAddAttrezzo() {
		assertFalse(this.borsa.addAttrezzo(attrezzo));
	}
	@Test 
	void testRemoveAttrezzo() {
		assertTrue(this.borsa.removeAttrezzo("rimuovere"));
	}
	@Test 
	void testGetPeso() {
		assertEquals(23,this.borsa.getPeso());
	}
	@Test 
	void testHasAttrezzo() {
		assertTrue(this.borsa.hasAttrezzo("trovato"));
	}
	@Test
	void testComparatorePerPesoConLista() {
		this.borsa.getContenutoOrdinatoPerPeso();
		assertEquals(this.attrezzi,this.borsa.getContenutoOrdinatoPerPeso());
	}
	@Test 
	void testAttrezzoDuplicato() {
		Attrezzo duplicato= new Attrezzo("aggiunto",4);
		assertFalse(this.borsa.addAttrezzo(duplicato));
		
	}
	@Test
	void testComparatorePerNome() {
		Set<Attrezzo> lista=new  TreeSet<Attrezzo>(this.attrezzi);
		assertEquals(lista, this.borsa.getContenutoOrdinatoPerNome());
	}
	@Test
	void tesdtComparatorePerPesoeNome() {
		ComparatoreperPeso comparatore = new ComparatoreperPeso();
		Set<Attrezzo> insieme= new TreeSet<Attrezzo>(comparatore);
		insieme.addAll(this.attrezzi);
		assertEquals(insieme, this.borsa.getSortedSetOrdinatoPerPeso());
	}
	@Test 
	void testMappaRaggruppataperPeso() {
		Map<Integer, Set<Attrezzo>> mappa= new HashMap<Integer,Set<Attrezzo>>();
		Set<Attrezzo> listaAttrezzi= new HashSet<Attrezzo>();
		
		for(Attrezzo a: this.attrezzi) {
			listaAttrezzi= mappa.get(a.getPeso());
			if(listaAttrezzi==null) {
				listaAttrezzi= new HashSet<Attrezzo>();
			}
			listaAttrezzi.add(a);
			mappa.put(a.getPeso(), listaAttrezzi);
		}
		assertEquals(mappa,this.borsa.getContenutoRaggruppatoPerPeso());
	}
	
	/*INIZIO BATTERIA TEST CASE MINIMALI*/
	
	@Test
	void batteriaTestBorsaOrdinataPerPeso() {
		Borsa borsa= new Borsa();
		List<Attrezzo> listaAttrezzi= new ArrayList<Attrezzo>();
		Attrezzo attr1= new Attrezzo("attr1", 1);
		listaAttrezzi.add(attr1);
		borsa.addAttrezzo(attr1);
		assertNotNull(borsa);
		assertNotNull(listaAttrezzi);
		assertEquals(1,listaAttrezzi.size());
		Attrezzo duplicato= new Attrezzo("attr1",5);
		assertFalse(borsa.addAttrezzo(duplicato));
		Attrezzo attr2= new Attrezzo("attr2",3);
		borsa.addAttrezzo(attr2);
		listaAttrezzi.add(attr2);
		Attrezzo attr3= new Attrezzo("attr3",2);
		borsa.addAttrezzo(attr3);
		listaAttrezzi.add(attr3);
		ComparatoreperPeso comp= new ComparatoreperPeso();
		Collections.sort(listaAttrezzi,comp);
		assertEquals(listaAttrezzi, borsa.getContenutoOrdinatoPerPeso());
		Attrezzo attr4= new Attrezzo("attr4",25);
		assertFalse(borsa.addAttrezzo(attr4));
		assertTrue(listaAttrezzi.add(attr4));
		
	}
	@Test
	void batteriaTestBorsaOrdinataPerNome() {
		Borsa borsa= new Borsa(20);
		Set<Attrezzo> insiemeAttrezzi= new TreeSet<Attrezzo>();
		Attrezzo attr1= new Attrezzo("c",5);
		assertTrue(borsa.addAttrezzo(attr1));
		assertTrue(insiemeAttrezzi.add(attr1));
		assertFalse(borsa.addAttrezzo(new Attrezzo("c",2)));
		assertFalse(insiemeAttrezzi.add(new Attrezzo("c",2)));
		Attrezzo attr2= new Attrezzo("a",2);
		borsa.addAttrezzo(attr2);
		insiemeAttrezzi.add(attr2);
		assertEquals(insiemeAttrezzi,borsa.getContenutoOrdinatoPerNome());
		Attrezzo attr3= new Attrezzo("b",4);
		borsa.addAttrezzo(attr3);
		insiemeAttrezzi.add(attr3);
		assertEquals(insiemeAttrezzi,borsa.getContenutoOrdinatoPerNome());
	}


}
