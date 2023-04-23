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
	private Stanza stanzaCorrente;
	private boolean finita;
	private Giocatore player;
	private Labirinto lab;
	
	public Partita(){
		this.player = new Giocatore();
		this.setLab(new Labirinto());
		this.stanzaCorrente= getLab().getStanzaIniz();
		this.finita = false;
	
	}

	public void setStanzaCorrente(Stanza stanzaCorrente) {
		this.stanzaCorrente = stanzaCorrente;
	}

	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	
	/**
	 * Restituisce vero se e solo se la partita e' stata vinta
	 * @return vero se partita vinta
	 */
	public boolean vinta() {
		return this.getStanzaCorrente()== getLab().getStanzaFinal();
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
		return lab;
	}

	public void setLab(Labirinto lab) {
		this.lab = lab;
	}

	public boolean giocatoreIsVivo() {
		if(this.player.getCfu()!=0)
			return true;
		else 
		return false;
	}
}
