package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.SortedSet;
import java.util.TreeSet;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private  Set<Attrezzo> attrezzi;
	private int pesoMax;
	private IOConsole console;
	public Borsa() {
	this(DEFAULT_PESO_MAX_BORSA);
	}
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new HashSet<Attrezzo>(); 
		this.console= new IOConsole();
	}
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if(this.getPeso()+attrezzo.getPeso()> this.getPesoMax())
			return false;
	return this.attrezzi.add(attrezzo);
		
	}
	public int getPesoMax() {
		return pesoMax;
	}
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		Iterator<Attrezzo> iteratore= this.attrezzi.iterator();
		while(iteratore.hasNext()){ 
			a=iteratore.next();
				if (a.getNome().equals(nomeAttrezzo))
					return a;
		}
	return null;
	}
	public int getPeso() {
		int pesoTotale = 0;
		for(Attrezzo a: this.attrezzi) {
			pesoTotale+=a.getPeso();
		}
	return pesoTotale;
	}
	public boolean isEmpty() {
		return this.attrezzi.isEmpty();
	}
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	public boolean removeAttrezzo(String nomeAttrezzo) {
		console.mostraMessaggio("rimozione in corso");
		
		if(this.attrezzi.toString().contains(nomeAttrezzo)) {
			Attrezzo a=this.getAttrezzo(nomeAttrezzo);
			this.attrezzi.remove(a);
			return true;
		}
				
		/*Attrezzo a=null;
		Iterator<Attrezzo> it= this.attrezzi.iterator();
		while(it.hasNext()) {
			a=it.next();
			if(a.getNome().equals(nomeAttrezzo)) {
				it.remove();
				return true;
			}
			
		}*/
		System.out.println("attrezzo da rimuovere non trovato");
		return false;
}
	
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
		s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
		for (Attrezzo a: this.attrezzi) {
				s.append(a.toString()+" ");
			}
		}
		else
		s.append("Borsa vuota");
	return s.toString();
	}
	public  List<Attrezzo> getContenutoOrdinatoPerPeso(){
		ComparatoreperPeso comparatore= new ComparatoreperPeso();
		List<Attrezzo> lista= new ArrayList<Attrezzo>();
		lista.addAll(this.attrezzi);
		Collections.sort(lista,comparatore);
		return lista;
	}
	public SortedSet<Attrezzo> getContenutoOrdinatoPerNome(){
		SortedSet<Attrezzo> InsiemeOrdinatoPerNome= new TreeSet<Attrezzo>(this.attrezzi);
		return InsiemeOrdinatoPerNome;
	}
	public SortedSet<Attrezzo> getSortedSetOrdinatoPerPeso(){
		ComparatoreperPeso comp= new ComparatoreperPeso();
		SortedSet<Attrezzo> InsiemeOrdinato= new TreeSet<Attrezzo>(comp);
		InsiemeOrdinato.addAll(this.attrezzi);
		return InsiemeOrdinato;
	}
	public Map<Integer,Set<Attrezzo>> getContenutoRaggruppatoPerPeso(){
		Map<Integer,Set<Attrezzo>> mappa= new HashMap <Integer,Set<Attrezzo>>();
		Set<Attrezzo> insiemeAttrezzi= new HashSet<Attrezzo>();
		
		for(Attrezzo a: this.attrezzi) {
			insiemeAttrezzi= mappa.get(a.getPeso());
			if(insiemeAttrezzi==null) {
				insiemeAttrezzi= new HashSet<Attrezzo>();
			}
			insiemeAttrezzi.add(a);
			mappa.put(a.getPeso(), insiemeAttrezzi);
		}
		return mappa;
	}
	
	public Set<Attrezzo> getAttrezzi(){
		return this.attrezzi;
	}
}
		
		