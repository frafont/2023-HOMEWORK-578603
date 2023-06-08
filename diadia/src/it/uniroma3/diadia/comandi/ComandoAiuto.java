package it.uniroma3.diadia.comandi;

import it.uniroma3.diadia.Partita;

public class ComandoAiuto extends AbstractComando implements Comando{
	static final private String[] elencoComandi = {"vai", "aiuto", "fine","prendi","posa","guarda","interagisci","regala", "saluta"};
	
	@Override
	public void esegui(Partita partita) {
		for(int i=0; i< elencoComandi.length;i++)
			System.out.println(elencoComandi[i]+"");
	}


	@Override
	public String getNome() {
		return "aiuto";
	}


	@Override
	public void setParametro(String parametro) {
		return;
		
	}





	

	

}
