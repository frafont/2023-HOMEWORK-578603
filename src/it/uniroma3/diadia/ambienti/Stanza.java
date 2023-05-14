package it.uniroma3.diadia.ambienti;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import it.uniroma3.diadia.attrezzi.Attrezzo;

/**
 * Classe Stanza - una stanza in un gioco di ruolo.
 * Una stanza e' un luogo fisico nel gioco.
 * E' collegata ad altre stanze attraverso delle uscite.
 * Ogni uscita e' associata ad una direzione.
 * 
 * @author docente di POO 
 * @see Attrezzo
 * @version base
*/

public class Stanza {
	
	static final private int NUMERO_MASSIMO_DIREZIONI = 4;
	static final private int NUMERO_MASSIMO_ATTREZZI = 10;
	
	private String nome;
    private Set<Attrezzo> attrezzi;
    private List <Stanza> stanzeAdiacenti;
	private List <String> direzioni;
    
    /**
     * Crea una stanza. Non ci sono stanze adiacenti, non ci sono attrezzi.
     * @param nome il nome della stanza
     */
    public Stanza(String nome) {
        this.nome = nome;
       
        this.direzioni = new ArrayList<String>();
        this.stanzeAdiacenti = new ArrayList<Stanza>();
        this.attrezzi = new HashSet<Attrezzo>();
    }

    /**
     * Imposta una stanza adiacente.
     *
     * @param direzione direzione in cui sara' posta la stanza adiacente.
     * @param stanza stanza adiacente nella direzione indicata dal primo parametro.
     */
    public void impostaStanzaAdiacente(String direzione, Stanza stanza) {
        boolean aggiornato = false;
    	if(this.direzioni.contains(direzione)&& !this.getStanzeAdiacenti().contains(stanza)) {
    		int i= this.direzioni.indexOf(direzione);
    		this.stanzeAdiacenti.set(i, stanza);
    		aggiornato = true;
    	}
    	
    	if (!aggiornato)
    		if (this.direzioni.size() < NUMERO_MASSIMO_DIREZIONI) {
    			this.direzioni.add(direzione);
    			this.stanzeAdiacenti.add(stanza);
    		}
    }

    /**
     * Restituisce la stanza adiacente nella direzione specificata
     * @param direzione
     */
	public Stanza getStanzaAdiacente(String direzione) {
		Stanza stanza=null;
	
	 if(this.direzioni.contains(direzione)) {
		 	int i=this.direzioni.indexOf(direzione);
		 	stanza =this.stanzeAdiacenti.get(i);
	 }
	 return stanza;
	}
    /**
     * Restituisce la nome della stanza.
     * @return il nome della stanza
     */
    public String getNome() {
        return this.nome;
    }

    /**
     * Restituisce la descrizione della stanza.
     * @return la descrizione della stanza
     */
    public String getDescrizione() {
        return this.toString();
    }

    /**
     * Restituisce la collezione di attrezzi presenti nella stanza.
     * @return la collezione di attrezzi nella stanza.
     */
    public Set<Attrezzo> getAttrezzi() {
    	return this.attrezzi;
    }

    /**
     * Mette un attrezzo nella stanza.
     * @param attrezzo l'attrezzo da mettere nella stanza.
     * @return true se riesce ad aggiungere l'attrezzo, false atrimenti.
     */
    public boolean addAttrezzo(Attrezzo attrezzo) {
        if (this.attrezzi.size() > NUMERO_MASSIMO_ATTREZZI) {
        		return false;
        	}
    return this.attrezzi.add(attrezzo);
    
    }
   /**
	* Restituisce una rappresentazione stringa di questa stanza,
	* stampadone la descrizione, le uscite e gli eventuali attrezzi contenuti
	* @return la rappresentazione stringa
	*/
    public String toString() {
    	StringBuilder risultato = new StringBuilder();
    	risultato.append(this.nome);
    	risultato.append("\nUscite: ");
    	for (String direzione : this.direzioni)
    			risultato.append(" " + direzione);
    	risultato.append("\nAttrezzi nella stanza: ");
    	for (Attrezzo a: this.attrezzi) {
    		risultato.append(a.toString()+" ");
    		
    	}
    	return risultato.toString();
    }

    /**
	* Controlla se un attrezzo esiste nella stanza (uguaglianza sul nome).
	* @return true se l'attrezzo esiste nella stanza, false altrimenti.
	*/
	public boolean hasAttrezzo(String nomeAttrezzo) {
		boolean trovato;
		trovato = false;
		if(this.attrezzi.toString().contains(nomeAttrezzo))
			trovato=true;
		/*for (Attrezzo a: this.attrezzi) {
			if (a.getNome().equals(nomeAttrezzo))
				trovato = true;
			
		}*/
		return trovato;
	}

	/**
     * Restituisce l'attrezzo nomeAttrezzo se presente nella stanza.
	 * @param nomeAttrezzo
	 * @return l'attrezzo presente nella stanza.
     * 		   null se l'attrezzo non e' presente.
	 */
	public Attrezzo getAttrezzo(String nomeAttrezzo) {
		Attrezzo attrezzoCercato;
		attrezzoCercato = null;
		for (Attrezzo a: this.attrezzi) {
			if (a.getNome().equals(nomeAttrezzo))
				attrezzoCercato = a;
			
		}
		return attrezzoCercato;	
	}

	/**
	 * Rimuove un attrezzo dalla stanza (ricerca in base al nome).
	 * @param nomeAttrezzo
	 * @return true se l'attrezzo e' stato rimosso, false altrimenti
	 */
	public boolean removeAttrezzo(Attrezzo attrezzo) {
		if(this.attrezzi.contains(attrezzo)) {
			this.attrezzi.remove(attrezzo);
			return true;
		}
		return false;
	
	}


	public List<String> getDirezioni() {
		return this.direzioni;
    }
	
	public List<Stanza> getStanzeAdiacenti(){
		return this.stanzeAdiacenti;
	}
	

}