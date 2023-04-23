package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPrendi implements Comando {

	private String attrezzo;
	@Override
	public void esegui(Partita partita) {
		IO console=new IOConsole();
		if(this.attrezzo==null) {
			console.mostraMessaggio("che cosa intendi prendere?");
			this.attrezzo=console.leggiRiga();
		}
		Borsa borsa= partita.getGiocatore().getBorsa();
		Stanza stanzaCorrente= partita.getStanzaCorrente();
		
		if(stanzaCorrente.hasAttrezzo(attrezzo)) {
			Attrezzo cercato= stanzaCorrente.getAttrezzo(attrezzo);
			if(borsa.addAttrezzo(cercato))
			stanzaCorrente.removeAttrezzo(cercato);
		}
		else {
			console.mostraMessaggio("attrezzo inesistente");
		}
		
		console.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
		console.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
				

	}

	@Override
	public void setParametro(String parametro) {
		this.attrezzo=parametro;

	}

	@Override
	public String getNome() {
		return "prendi";
	}

	@Override
	public String getParametro() {
		return this.attrezzo;
		
	}

	

}
