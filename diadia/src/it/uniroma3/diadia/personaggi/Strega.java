package it.uniroma3.diadia.personaggi;

import java.util.List;

import it.uniroma3.diadia.Partita;
import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Strega extends AbstractPersonaggio {
	
	private static final String MESSAGGIO_DONO= "poichè mi hai salutato, ora ti trasferirò"+
			"nella stanza adiacente con più attrezzi";
	
	
	private static final String MESSAGGIO_MALEDUCATO= "come ti sei permesso a non salutarmi!"+
			"ora ti trasferirò nella stanza adiacente con meno attrezzi!";
	
	private static final String MESSAGGIO_RINGRAZIAMENTO= "ahahaahha grazie per il tuo dono"+
	"ora lo terrò per me";
	
	
	
	public Strega(String nome, String presentaz) {
		super(nome, presentaz);
	}

	@Override
	public String agisci(Partita partita) {
		List<Stanza> listaStanzaAdiacenti= partita.getStanzaCorrente().getStanzeAdiacenti();
		Stanza nuovaStanzaCorr=partita.getStanzaCorrente();
		String msg;
		
		if(this.haSalutato()) {
			int maxNumAttr=0;
			for(Stanza s: listaStanzaAdiacenti) {
				if(s.getAttrezzi().size()>maxNumAttr) {
					nuovaStanzaCorr=s;
				}
			}
			partita.setStanzaCorrente(nuovaStanzaCorr);
			msg=MESSAGGIO_DONO;
			return msg;
		}
		int min=10;
		for(Stanza s: listaStanzaAdiacenti) {
			if(s.getAttrezzi().size()<min) {
				nuovaStanzaCorr=s;
				}
		}
			partita.setStanzaCorrente(nuovaStanzaCorr);
			msg= MESSAGGIO_MALEDUCATO;
			return msg;
		
	}

	@Override
	public String riceviRegalo(Attrezzo attrezzo, Partita partita) {
		return MESSAGGIO_RINGRAZIAMENTO;
	}

}
