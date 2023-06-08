package it.uniroma3.diadia;

import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.giocatore.Giocatore;

/**
 * Questa classe modella una partita del gioco
 *
 * @author  docente di POO
 * @see Stanza
 * @version base
 */

public class Partita {
	private boolean finita;
	private Giocatore player;
	private Labirinto labirinto;
	
	public Partita(Labirinto labirinto){
		this.player = new Giocatore();
		this.labirinto=labirinto;
		this.finita = false;
	
	}
	
	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.labirinto.setStanzaIniziale(stanzaCorrente);
	}
	
	public Stanza getStanzaCorrente() {
		return this.labirinto.getStanzaIniziale();
	}

	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.labirinto.getStanzaIniziale()== this.labirinto.getStanzaFinale();
	}

	/**
	 * Restituisce vero se e solo se la partita e' finita
	 * @return vero se partita finita
	 */
	public boolean isFinita() {
		return finita || vinta() || (player.getCfu()==0);
	}

	/**
	 * Imposta la partita come finita
	 *
	 */
	public void setFinita() {
		this.finita = true;
	}
	
	
	public Giocatore getGiocatore() {
		return this.player;
	}

	public Labirinto getLab() {
		return this.labirinto;
	}

	public void setLabirinto(Labirinto lab) {
		this.labirinto = lab;
	}

	public boolean giocatoreIsVivo() {
		if(this.player.getCfu()!=0)
			return true;
		else 
		return false;
	}
}
