package it.uniroma3.diadia;


import java.util.Scanner;

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
	


	public DiaDia() {
		this.partita = new Partita();
	}

	public void gioca() {
		String istruzione; 
		Scanner scannerDiLinee;

		System.out.println(MESSAGGIO_BENVENUTO);
		scannerDiLinee = new Scanner(System.in);
		
		do		
			istruzione = scannerDiLinee.nextLine();
		while (!processaIstruzione(istruzione));
		
	 scannerDiLinee.close();	
	}   
		

	/**
	 * Processa una istruzione 
	 *
	 * @return true se l'istruzione e' eseguita e il gioco continua, false altrimenti
	 */
	private boolean processaIstruzione(String istruzione) {
		
		Comando comandoDaEseguire = new Comando(istruzione);
		
		if(comandoDaEseguire.getNome()==null) {
			System.out.println("comando non riconosciuto");
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
			System.out.println("Comando sconosciuto");
		}
		
		if (this.partita.vinta()) {
			System.out.println("Hai vinto!");
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
			System.out.print(elencoComandi[i]+" ");
		System.out.println();
		Scanner sc= new Scanner(System.in);
	
	}

	/**
	 * Cerca di andare in una direzione. Se c'e' una stanza ci entra 
	 * e ne stampa il nome, altrimenti stampa un messaggio di errore
	 */
	private void vai(String direzione) {
		if(direzione==null) {
			System.out.println("Dove vuoi andare ?");
			Scanner scannerdiLinee;
			scannerdiLinee= new Scanner(System.in);	
			direzione=scannerdiLinee.next();
		
		}
		Stanza prossimaStanza = null;
		prossimaStanza = this.partita.getStanzaCorrente().getStanzaAdiacente(direzione);
		if (prossimaStanza == null)
			System.out.println("Direzione inesistente");
		else {
			this.partita.setStanzaCorrente(prossimaStanza);
			int cfu = this.partita.getGiocatore().getCfu();
			this.partita.getGiocatore().setCfu(cfu--);
		}
		System.out.println(partita.getStanzaCorrente().getDescrizione());
		System.out.println(partita.getGiocatore().info());
		System.out.println(partita.getGiocatore().getBorsa().toString());
		
	
	}

	/**
	 * Comando "Fine".
	 */
	private void fine() {
		System.out.println("Grazie di aver giocato!");  // si desidera smettere
	}
	
	private void prendi(String attrezzo) {
		if(attrezzo==null) {
			System.out.println("Che cosa intendi prendere?");
			Scanner scannerdiLinee;
			scannerdiLinee= new Scanner(System.in);	
			attrezzo=scannerdiLinee.next();
		}
		
		Borsa borsa= partita.getGiocatore().getBorsa();
		Stanza stanzaCorrente= partita.getStanzaCorrente();
		
		if(stanzaCorrente.hasAttrezzo(attrezzo)) {
			Attrezzo cercato= stanzaCorrente.getAttrezzo(attrezzo);
			if(borsa.addAttrezzo(cercato))
			stanzaCorrente.removeAttrezzo(cercato);
		}
		else {
			System.out.println("attrezzo inesistente");
		}
		
		System.out.println(partita.getGiocatore().getBorsa().toString());
		System.out.println(partita.getStanzaCorrente().getDescrizione());
	}
	
	private void posa(String attrezzo) {
		if(attrezzo==null) {
			System.out.println("Che cosa intendi posare?");
			Scanner scannerdiLinee;
			scannerdiLinee= new Scanner(System.in);	
			attrezzo=scannerdiLinee.next();
		}
		Borsa borsa= partita.getGiocatore().getBorsa();
		Stanza stanzaCorrente= partita.getStanzaCorrente();
		if(borsa.hasAttrezzo(attrezzo)) {
			Attrezzo cercato= borsa.getAttrezzo(attrezzo);
				borsa.removeAttrezzo(attrezzo);
				stanzaCorrente.addAttrezzo(cercato);
			
			
		}
		else {
			System.out.println("attrezzo inesistente");
		}
		System.out.println(partita.getGiocatore().getBorsa().toString());
		System.out.println(partita.getStanzaCorrente().getDescrizione());
	}

	public static void main(String[] argc) {
		DiaDia gioco = new DiaDia();
		gioco.gioca();
	}
}