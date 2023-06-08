package it.uniroma3.diadia.personaggi;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Giocatore;

public class Cane extends AbstractPersonaggio{

	private static final String MESSAGGIO_CANE="wouf wouf..... il cane ti ha morso!"+
			"togliendoti 5 CFU!";
	private static final String CIBO_PREFERITO= "croccantini";
	private static final Attrezzo ATTREZZO_BUTTATO= new Attrezzo("osso speciale",3);
	private static final String MESSAGGIO_DONO= "il cane ti ringrazia per i croccantini!"+
	"ora nella stanza troverai un attrezzo speciale";
	private static final String MESSAGGIO_REGALO_NON_GRADITO="il cane non ha apprezzato e ti ha morso"+
	"togliendoti 1 CFU!";
	public Cane(String nome, String presentaz) {
		super(nome, presentaz);
	}

	@Override
	public String agisci(Partita partita) {
		Giocatore giocatore= partita.getGiocatore();
		
		giocatore.setCfu(giocatore.getCfu()-5);
		
		return MESSAGGIO_CANE;
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		if(attrezzo.getNome().equals(CIBO_PREFERITO)) {
			partita.getStanzaCorrente().addAttrezzo(ATTREZZO_BUTTATO);
			return MESSAGGIO_DONO;
		}
		else {
			Giocatore protagonista= partita.getGiocatore();
			protagonista.setCfu(protagonista.getCfu()-1);
			return MESSAGGIO_REGALO_NON_GRADITO; 
		}
		
	}

}
