package it.uniroma3.diadia.ambienti;

import it.uniroma3.diadia.attrezzi.Attrezzo;

public class Labirinto {
	private Stanza stanzaIniz;
	private Stanza stanzaFinal;
	
	public Labirinto() {
		creaLabirinto();
	}
	private void creaLabirinto() {
		
		/* crea gli attrezzi */
    	Attrezzo lanterna = new Attrezzo("lanterna",3);
		Attrezzo osso = new Attrezzo("osso",1);
    	Attrezzo libro= new Attrezzo("libro", 10);
    	Attrezzo spada= new Attrezzo("spada", 8);
    	
		/* crea stanze del labirinto */
		Stanza atrio = new Stanza("Atrio");
		Stanza aulaN11 = new Stanza("Aula N11");
		Stanza aulaN10 = new Stanza("Aula N10");
		Stanza laboratorio = new Stanza("Laboratorio Campus");
		Stanza biblioteca = new Stanza("Biblioteca");
		
		/* collega le stanze */
		atrio.impostaStanzaAdiacente("nord", biblioteca);
		atrio.impostaStanzaAdiacente("est", aulaN11);
		atrio.impostaStanzaAdiacente("sud", aulaN10);
		atrio.impostaStanzaAdiacente("ovest", laboratorio);
		aulaN11.impostaStanzaAdiacente("est", laboratorio);
		aulaN11.impostaStanzaAdiacente("ovest", atrio);
		aulaN10.impostaStanzaAdiacente("nord", atrio);
		aulaN10.impostaStanzaAdiacente("est", aulaN11);
		aulaN10.impostaStanzaAdiacente("ovest", laboratorio);
		laboratorio.impostaStanzaAdiacente("est", atrio);
		laboratorio.impostaStanzaAdiacente("ovest", aulaN11);
		biblioteca.impostaStanzaAdiacente("sud", atrio);

        /* pone gli attrezzi nelle stanze */
		aulaN10.addAttrezzo(lanterna);
		aulaN10.addAttrezzo(libro);
		aulaN11.addAttrezzo(spada);
		atrio.addAttrezzo(osso);
		
	
		setStanzaIniz(atrio);
		setStanzaFinal(biblioteca);
	}

	public Stanza getStanzaVincente() {
		return this.getStanzaFinal();
	}
	public Stanza getStanzaFinal() {
		return stanzaFinal;
	}
	public void setStanzaFinal(Stanza stanzaFinal) {
		this.stanzaFinal = stanzaFinal;
	}
	public Stanza getStanzaIniz() {
		return this.stanzaIniz;
	}
	public void setStanzaIniz(Stanza stanzaIniz) {
		this.stanzaIniz = stanzaIniz;
	}

}
