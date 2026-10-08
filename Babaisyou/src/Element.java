public class Element {
	protected Etat e;
	
	Element() {
		e = Etat.FANTOME;
	}
	
	Element(Etat e) {
		this.e = e;
	}
	
	public Etat getEtat() {return e;}
	
	public void setEtat(Etat e) {this.e = e;}
		
}
