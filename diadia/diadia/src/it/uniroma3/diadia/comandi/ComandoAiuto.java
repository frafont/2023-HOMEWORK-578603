package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto implements Comando {
	static final private String[] elencoComandi = {"vai", "aiuto", "fine","prendi","posa","guarda"};
	@Override
	public void esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length;i++)
			System.out.println(elencoComandi[i]+"");
	}

	@Override
	public void setParametro(String parametro) {
		return;

	}

	@Override
	public String getNome() {
		return "aiuto";
	}

	@Override
	public String getParametro() {
		return null;
	}

}
