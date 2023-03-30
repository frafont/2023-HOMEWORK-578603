package it.uniroma3.diadia;




import it.uniroma3.diadia.ambienti.Stanza;
import it.uniroma3.diadia.attrezzi.Attrezzo;
import it.uniroma3.diadia.giocatore.Borsa;

/**
 * Classe principale di diadia, un semplice gioco di ruolo ambientato al dia.
 * Per giocare crea un'istanza di questa classe e invoca il metodo gioca
 *
 * Questa e' la classe principale crea e istanzia tutte le altre
 *
 * @author  docente di POO 
 *         (da un'idea di Michael Kolling and David J. Barnes) 
 *          
 * @version base
 */

public class DiaDia {



	static final private String MESSAGGIO_BENVENUTO = ""+
			"Ti trovi nell'Universita', ma oggi e' diversa dal solito...\n" +
			"Meglio andare al piu' presto in biblioteca a studiare. Ma dov'e'?\n"+
			"I locali sono popolati da strani personaggi, " +
			"alcuni amici, altri... chissa!\n"+
			"Ci sono attrezzi che potrebbero servirti nell'impresa:\n"+
			"puoi raccoglierli, usarli, posarli quando ti sembrano inutili\n" +
			"o regalarli se pensi che possano ingraziarti qualcuno.\n\n"+
			"Per conoscere le istruzioni usa il comando 'aiuto'.";
	
	static final private String[] elencoComandi = {"vai", "aiuto", "fine","prendi","posa"};

	private Partita partita;
	
	private IOConsole console;

	public DiaDia() {
		this.partita = new Partita();
		
	}

	public void gioca() {
		String istruzione; 
		

		console.mostraMessaggio(MESSAGGIO_BENVENUTO);
		
		do	{ 	
			istruzione=null;
			istruzione = console.leggiRiga();
			
		}
		while (!processaIstruzione(istruzione));
		
	 	
	}   
		

	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		
		Comando comandoDaEseguire = new Comando(istruzione);
		
		if(comandoDaEseguire.getNome()==null) {
			console.mostraMessaggio("comando non riconosciuto");
		}
		else { 

		if (comandoDaEseguire.getNome().equals("fine")) {
			this.fine();
			return true;
		}
		else if (comandoDaEseguire.getNome().equals("vai")) 
			this.vai(comandoDaEseguire.getParametro());
		else if (comandoDaEseguire.getNome().equals("aiuto"))
			this.aiuto();
		else if (comandoDaEseguire.getNome().equals("prendi"))
			this.prendi(comandoDaEseguire.getParametro());
		else if(comandoDaEseguire.getNome().equals("posa"))
			this.posa(comandoDaEseguire.getParametro());
		else
			console.mostraMessaggio("comando sconosciuto");
		}
		
		if (this.partita.vinta()) {
			console.mostraMessaggio("Hai vinto!");
			return true;
		} else
			return false;
	
	}   

	// implementazioni dei comandi dell'utente:

	/**
	 * Stampa informazioni di aiuto.
	 */
	private void aiuto() {
		for(int i=0; i< elencoComandi.length; i++) 
			console.mostraMessaggio(elencoComandi[i]+" ");
		 console.mostraMessaggio("\n");
	
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null) {
			console.mostraMessaggio("Dove vuoi andare ?");

			direzione=console.leggiRiga();	
		
		
		}
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			console.mostraMessaggio("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu--);
		}
		console.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
		console.mostraMessaggio(partita.getGiocatore().info());
		console.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
		
	
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		console.mostraMessaggio("Grazie di aver giocato!");  // si desidera smettere
	}
	
	private void prendi(String attrezzo) {
		if(attrezzo==null) {
			console.mostraMessaggio("Che cosa intendi prendere?");			
			attrezzo= console.leggiRiga();
		}
		
		Borsa borsa= partita.getGiocatore().getBorsa();
		Stanza stanzaCorrente= partita.getStanzaCorrente();
		
		if(stanzaCorrente.hasAttrezzo(attrezzo)) {
			Attrezzo cercato= stanzaCorrente.getAttrezzo(attrezzo);
			if(borsa.addAttrezzo(cercato))
			stanzaCorrente.removeAttrezzo(cercato);
		}
		else {
			console.mostraMessaggio("attrezzo inesistente");
		}
		
		console.mostraMessaggio(partita.getGiocatore().getBorsa().toString());
		console.mostraMessaggio(partita.getStanzaCorrente().getDescrizione());
	}
	
	private void posa(String attrezzo) {
		if(attrezzo==null) {
			console.mostraMessaggio("Che cosa intendi posare?");
			attrezzo=console.leggiRiga();		}
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

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.console= new IOConsole();
		gioco.gioca();
		
	}
}