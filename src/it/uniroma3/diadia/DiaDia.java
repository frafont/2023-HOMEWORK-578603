package it.uniroma3.diadia;




import it.uniroma3.diadia.ambienti.Labirinto;
import it.uniroma3.diadia.ambienti.LabirintoBuilder;
import it.uniroma3.diadia.comandi.Comando;
import it.uniroma3.diadia.comandi.FabbricaDiComandi;
import it.uniroma3.diadia.comandi.FabbricaDiComandiFisarmonica;

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
	
	private Partita partita;
	
	private IO console;

	public DiaDia(Labirinto labirinto, IO io) {
		this.partita = new Partita(labirinto);
		this.console=io;
		
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
		
			Comando comandoDaEseguire;
			FabbricaDiComandi factory = new FabbricaDiComandiFisarmonica();
			comandoDaEseguire = factory.costruisciComando(istruzione);
			comandoDaEseguire.esegui(this.partita);
			if (this.partita.vinta())

			System.out.println("Hai vinto!");
			if (!this.partita.giocatoreIsVivo())

			System.out.println("Hai esaurito i CFU...");

			return this.partita.isFinita();
			
	}   

	public static void main(String[] argc) {
		IO console = new IOConsole();
		Labirinto labirinto = new LabirintoBuilder()
										.addStanzaIniziale("atrio")
										.addAttrezzo("osso", 1)
										.addStanza("laboratorio")
										.addAttrezzo("spada", 8)
										.addAdiacenza("atrio", "laboratorio", "est")
										.addStanza("N11")
										.addAttrezzo("libro", 10)
										.addAdiacenza("atrio", "N11", "sud")
										.addAdiacenza("N11", "laboratorio", "ovest")
										.addStanzaVincente("Biblioteca")
										.addAdiacenza("atrio", "Biblioteca", "nord")
										.getLabirinto();
		DiaDia gioco = new DiaDia(labirinto, console);
		gioco.gioca();
	}
		
	
}