package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoPosa implements Comando {

	private String attrezzo;
	@Override
	public void esegui(Partita partita) {
		IO console=new IOConsole();
		if (this.attrezzo==null){
			console.mostraMessaggio("che cosa intendi posare?");
			this.attrezzo=(console.leggiRiga());
		}
			Borsa borsa= partita.getGiocatore().getBorsa();
			Stanza stanzaCorrente= partita.getStanzaCorrente();
			if(borsa.hasAttrezzo(attrezzo)) {
				Attrezzo cercato= borsa.getAttrezzo(attrezzo);
					borsa.removeAttrezzo(attrezzo);
					stanzaCorrente.addAttrezzo(cercato);
				
				
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
		return "posa";
	}

	@Override
	public String getParametro() {
		return this.attrezzo;
	}

}
