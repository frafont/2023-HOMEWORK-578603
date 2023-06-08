package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IO;
import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;

public class ComandoNonValido extends AbstractComando  {

	@Override
	public void esegui(Partita partita) {
		IO console= new IOConsole();
		console.mostraMessaggio("comando sconosciuto");
		return;

	}
	@Override
	public String getNome() {
		return null;
	}

}
