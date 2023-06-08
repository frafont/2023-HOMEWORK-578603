package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.giocatore.Borsa;

public class ComandoGuarda extends AbstractComando  {

	@Override
	public void esegui(Partita partita) {
		IO console= new IOConsole();
		console.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		console.mostraMessaggio(partita.getGiocatore().info());
		Borsa borsa= partita.getGiocatore().getBorsa();
		console.mostraMessaggio("contenuto borsa ordinato per peso:"+ borsa.getContenutoOrdinatoPerPeso().toString());
		console.mostraMessaggio("contenuto borsa ordinato per nome" +borsa.getContenutoOrdinatoPerNome().toString());
		//console.mostraMessaggio(borsa.getSortedSetOrdinatoPerPeso().toString());
		console.mostraMessaggio("resoconto del contenuto della borsa:"+borsa.getContenutoRaggruppatoPerPeso().toString());
	}

	

	@Override
	public String getNome() {
		return "guarda";
	}

	

}
