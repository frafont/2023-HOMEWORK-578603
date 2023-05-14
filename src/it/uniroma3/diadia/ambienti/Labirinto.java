package it.uniroma3.diadia.ambienti;

public class Labirinto {
	private Stanza stanzaCorrente;
	private Stanza stanzaFinal;
	
	public LabirintoBuilder newBuilder() {
		return new LabirintoBuilder();
		
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
	public Stanza getStanzaCorrente() {
		return this.stanzaCorrente;
	}
	public void setStanzaCorrente(Stanza stanzaIniz) {
		this.stanzaCorrente = stanzaIniz;
	}

}
