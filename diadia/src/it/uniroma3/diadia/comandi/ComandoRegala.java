package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.giocatore.Borsa;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoRegala implements Comando {
	
	private String attrDaRegalare;
	private String messaggio;
	
	
	@Override
	public void esegui(Partita partita) {
		IO console=new IOConsole();
		if(this.attrDaRegalare==null) {
			console.mostraMessaggio("che cosa intendi regalare?");
			this.attrDaRegalare=console.leggiRiga();
		}
		if(partita.getStanzaCorrente().getPersonaggio()==null) {
			console.mostraMessaggio("qui non c'è nessuno a cui puoi fare un regalo");
			return;
		}
		
		Borsa borsa= partita.getGiocatore().getBorsa();
		AbstractPersonaggio personaggio= partita.getStanzaCorrente().getPersonaggio();
		if(borsa.hasAttrezzo(attrDaRegalare)) {
			this.messaggio=personaggio.riceviRegalo(borsa.getAttrezzo(attrDaRegalare),partita);
			borsa.getAttrezzi().remove(borsa.getAttrezzo(attrDaRegalare));
			console.mostraMessaggio(this.messaggio);
		}
		console.mostraMessaggio("non puoi regalare un attrezzo che non hai!");
	}

	@Override
	public void setParametro(String parametro) {
		this.attrDaRegalare= parametro;
		
	}

	@Override
	public String getNome() {
		return "regala";
	}

	@Override
	public String getParametro() {
		return this.attrDaRegalare;
	}
	

}
