package it.uniroma3.diadia.ambienti;

import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class LabirintoBuilder {
	private Labirinto labirinto;
	private Stanza ultimaStanza;
	private Map<String,Stanza> mappaStanze;
	
	public LabirintoBuilder() {
		this.labirinto=new Labirinto();
		this.mappaStanze= new HashMap<String,Stanza>();
	}

	

	public Labirinto getLabirinto() {
		return this.labirinto;
	}
	public Map<String,Stanza> getMappStanze(){
		return this.mappaStanze;
	}



	public Stanza getUltimaStanza() {
		return this.ultimaStanza;
	}
	
	public LabirintoBuilder addStanzaIniziale(String nomeStanza) {
		Stanza stanzaIniziale= new Stanza(nomeStanza);
		this.mappaStanze.put(nomeStanza, stanzaIniziale);
		this.ultimaStanza=stanzaIniziale;
		this.labirinto.setStanzaCorrente(stanzaIniziale);
		
		return this;
	}

	public LabirintoBuilder addStanzaVincente(String nome) {
		Stanza stanzaVincente= new Stanza(nome);
		this.ultimaStanza=stanzaVincente;
		this.mappaStanze.put(nome, stanzaVincente);
		this.labirinto.setStanzaFinal(stanzaVincente);
		return this;
	}
	
	public LabirintoBuilder addStanza( String nomeStanza) {
		Stanza stanza= new Stanza(nomeStanza);
		if(this.mappaStanze.containsKey(nomeStanza)) 
				return this;
			
			this.ultimaStanza=stanza;
			this.mappaStanze.put(nomeStanza, stanza);
			return this;
		
	}
	public LabirintoBuilder addStanzaMagica(String nomeStanza, int soglia) {
		Stanza magica= new StanzaMagica(nomeStanza, soglia);
		if(this.mappaStanze.containsKey(nomeStanza))
			return this;
		
		this.ultimaStanza=magica;
		this.mappaStanze.put(nomeStanza, magica);
		return this;
	}
	
	public LabirintoBuilder addStanzaBloccata(String nomeStanza,String direzione, String attrezzoBloccante) {
		Stanza bloccata= new StanzaBloccata(nomeStanza, direzione, attrezzoBloccante);
		if(this.mappaStanze.containsKey(nomeStanza))
			return this;
		this.ultimaStanza= bloccata;
		this.mappaStanze.put(nomeStanza, bloccata);
		
		return this;
	}
	
	public LabirintoBuilder addStanzaBuia( String nomeStanza, String attrezzoLuce) {
		Stanza buia= new StanzaBuia(nomeStanza, attrezzoLuce);
		if(this.mappaStanze.containsKey(nomeStanza))
			return this;
		this.ultimaStanza= buia;
		this.mappaStanze.put(nomeStanza, buia);
		return this;
	}
	
	public LabirintoBuilder addAttrezzo(String nomeAttr, int peso) {
		if(this.getUltimaStanza()==null) {
			return this;
		}
		Attrezzo attrezzo= new Attrezzo(nomeAttr,peso);
		this.getUltimaStanza().addAttrezzo(attrezzo);
		return this;
	}
	
	public LabirintoBuilder addAdiacenza(String stanza1,String stanza2, String direzione) {
		Stanza st1= this.mappaStanze.get(stanza1);
		Stanza st2= this.mappaStanze.get(stanza2);
		if(!(st1.getDirezioni().size()>=4) && !st1.getStanzeAdiacenti().contains(st2))
			st1.impostaStanzaAdiacente(direzione, st2);
			
		return this;
	}
	
	
}
