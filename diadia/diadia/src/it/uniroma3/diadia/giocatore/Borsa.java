package it.uniroma3.diadia.giocatore;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Borsa {
	public final static int DEFAULT_PESO_MAX_BORSA = 10;
	private Attrezzo[] attrezzi;
	private int numeroAttrezzi;
	private int pesoMax;
	private int dimensione=10; // speriamo bastino...
	private IOConsole console;
	public Borsa() {
	this(DEFAULT_PESO_MAX_BORSA);
	}
	public Borsa(int pesoMax) {
		this.pesoMax = pesoMax;
		this.attrezzi = new Attrezzo[this.dimensione]; 
		this.numeroAttrezzi = 0;
		this.console= new IOConsole();
	}
	public boolean addAttrezzo(Attrezzo attrezzo) {
		if (this.getPeso() + attrezzo.getPeso() > this.getPesoMax()) {
			this.console.mostraMessaggio("non c'è spazio nella borsa");
		return false;
		}
		if (this.numeroAttrezzi==this.dimensione)
		return false;
		for(int i=0; i<this.dimensione;i++) {
			if(attrezzi[i]==null) {
				attrezzi[i]=attrezzo;
				this.numeroAttrezzi++;
				return true;
			}
		}
		return false;
		
	}
	public int getPesoMax() {
		return pesoMax;
	}
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo a = null;
		for (int i= 0; i<this.dimensione; i++) {
			if(attrezzi[i]!=null)
				if (this.attrezzi[i].getNome().equals(nomeAttrezzo))
					a = attrezzi[i];
		}
		
	
		return a;
	}
	public int getPeso() {
		int peso = 0;
		for (int i= 0; i<this.dimensione; i++) {
			if(attrezzi[i]!=null) {
				peso += this.attrezzi[i].getPeso();
			}
		}
	return peso;
	}
	public boolean isEmpty() {
		return this.numeroAttrezzi == 0;
	}
	public boolean hasAttrezzo(String nomeAttrezzo) {
		return this.getAttrezzo(nomeAttrezzo)!=null;
	}
	public boolean removeAttrezzo(String nomeAttrezzo) {
		console.mostraMessaggio("rimozione in corso");
		if(this.getPeso()==0) {
			console.mostraMessaggio("borsa vuota, niente da rimuovere");
			return false;
		}

		for(int i=0; i<this.dimensione;i++) {
			if(attrezzi[i]!=null) {
			if(this.attrezzi[i].getNome().equals(nomeAttrezzo)) {
				this.attrezzi[i]=null;
				this.numeroAttrezzi--;
				console.mostraMessaggio("attrezzo rimosso dalla borsa");
				
				return true;
					}
			}
		}
				
			System.out.println("attrezzo da rimuovere non trovato");
			return false;
		}
	
	public String toString() {
		StringBuilder s = new StringBuilder();

		if (!this.isEmpty()) {
		s.append("Contenuto borsa ("+this.getPeso()+"kg/"+this.getPesoMax()+"kg): ");
		for (int i= 0; i<this.dimensione; i++) {
			if(attrezzi[i]!=null)
				s.append(attrezzi[i].toString()+" ");
			}
		}
		else
		s.append("Borsa vuota");
	return s.toString();
	}
}
		
		