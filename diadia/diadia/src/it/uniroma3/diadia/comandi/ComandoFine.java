package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoFine implements Comando {

	@Override
	public void esegui(Partita partita) {
		IO console= new IOConsole();
		console.mostraMessaggio("Grazie di aver giocato!");

	}

	@Override
	public void setParametro(String parametro) {
		return;

	}

	@Override
	public String getNome() {
		return "fine";
	}

	@Override
	public String getParametro() {
		return null;
	}

}
