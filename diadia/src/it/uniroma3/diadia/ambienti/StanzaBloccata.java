package it.uniroma3.diadia.ambienti;

public class StanzaBloccata extends Stanza{
	
	private Direzione direzioneBloccata;
	private String attrezzoSbloccante;
	
	public StanzaBloccata(String nome, Direzione direzione, String attrezzo){
		super(nome);
		this.attrezzoSbloccante=attrezzo;
		this.direzioneBloccata=direzione;
	}
	
	@Override
	public Stanza getStanzaAdiacente(Direzione direzione) {
		if(this.direzioneBloccata.equals(direzione) && !this.hasAttrezzo(this.attrezzoSbloccante)) 
			return this;
		return super.getStanzaAdiacente(direzione);
	}
	@Override 
	public String getDescrizione() {
		if(!this.hasAttrezzo(attrezzoSbloccante)) {
			return super.getDescrizione()+" "+ this.infoDirezione()+" " +this.infoAttrezzo();
		}
		return super.getDescrizione();
	}
		
	public String infoDirezione() {
		return "direzione bloccata: "+this.direzioneBloccata;
	}
	public String infoAttrezzo() {
		return "attrezzo sbloccante: "+this.attrezzoSbloccante;
	}
	public void setDirezioneBloccata(Direzione direzione) {
		this.direzioneBloccata=direzione;
	}
	public void setAttrezzoSbloccante(String attrezzo) {
		this.attrezzoSbloccante=attrezzo;
	}
	
}
