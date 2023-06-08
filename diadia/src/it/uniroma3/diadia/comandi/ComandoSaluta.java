package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.IOConsole;
import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.personaggi.AbstractPersonaggio;

public class ComandoSaluta extends AbstractComando {

	@Override
	public void esegui(Partita partita) {
		AbstractPersonaggio personaggio;
		personaggio= partita.getStanzaCorrente().getPersonaggio();
		if(personaggio!=null)
			personaggio.saluta();
		else {
			IOConsole io= new IOConsole();
			io.mostraMessaggio("qui non c'è nessuno");
		}
	}

	@Override
	public String getNome() {
		
		return "saluta";
	}

}
