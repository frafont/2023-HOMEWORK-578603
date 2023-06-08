package it.uniroma3.diadia.ambienti;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.Map;

import it.uniroma3.diadia.CaricatoreLabirinto;
import it.uniroma3.diadia.FormatoFileNonValidoException;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.personaggi.Cane;
import it.uniroma3.diadia.personaggi.Mago;
import it.uniroma3.diadia.personaggi.Strega;

public class Labirinto {
	private Stanza stanzaIniziale;
	private Stanza stanzaFinale;
	
	private Labirinto(String nomeFile) throws FileNotFoundException, FormatoFileNonValidoException {
		CaricatoreLabirinto c =
				new CaricatoreLabirinto(nomeFile);
		c.carica();
		this.stanzaIniziale = c.getStanzaIniziale();
		this.stanzaFinale = c.getStanzaVincente();
	}

	public Stanza getStanzaVincente() {
		return this.getStanzaFinale();
	}
	public Stanza getStanzaFinale() {
		return stanzaFinale;
	}
	public void setStanzaFinale(Stanza stanzaFinal) {
		this.stanzaFinale = stanzaFinal;
	}
	public Stanza getStanzaIniziale() {
		return this.stanzaIniziale;
	}
	public void setStanzaIniziale(Stanza stanzaIniz) {
		this.stanzaIniziale = stanzaIniz;
	}
	public static LabirintoBuilder newBuilder(String labirinto) throws FileNotFoundException, FormatoFileNonValidoException {
		return new LabirintoBuilder(labirinto);
	}
	
	public static class LabirintoBuilder {
		
		public static  class Pippo {
			//another useless nested class
		}
		
		private Labirinto labirinto;
		private Stanza ultimaStanza;
		private Map<String, Stanza> mappaStanze;

		public LabirintoBuilder(String labirinto) throws FileNotFoundException, FormatoFileNonValidoException {
			this.labirinto = new Labirinto(labirinto);
			this.mappaStanze = new HashMap<>();
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
			this.labirinto.setStanzaIniziale(stanzaIniziale);
			
			return this;
		}

		public LabirintoBuilder addStanzaVincente(String nome) {
			Stanza stanzaVincente= new Stanza(nome);
			this.ultimaStanza=stanzaVincente;
			this.mappaStanze.put(nome, stanzaVincente);
			this.labirinto.setStanzaFinale(stanzaVincente);
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
			Stanza bloccata= new StanzaBloccata(nomeStanza, Direzione.valueOf(direzione), attrezzoBloccante);
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
		public LabirintoBuilder addMago(String nome,String presentazione, Attrezzo attrezzo) {
			Mago mago= new Mago(nome,presentazione,attrezzo);
			if(this.ultimaStanza==null)
				return this;
			this.ultimaStanza.setPersonaggio(mago);
			return this;
		}
		public LabirintoBuilder addStrega(String nome,String presentazione) {
			Strega strega= new Strega(nome,presentazione);
			if(this.ultimaStanza==null)
				return this;
			this.ultimaStanza.setPersonaggio(strega);
			return this;
		}
		public LabirintoBuilder addCane(String nome,String presentazione) {
			Cane cane= new Cane(nome,presentazione);
			if(this.ultimaStanza==null)
				return this;
			this.ultimaStanza.setPersonaggio(cane);
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
				st1.impostaStanzaAdiacente(Direzione.valueOf(direzione), st2);
				
			return this;
		}
	}
}
