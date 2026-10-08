
public class Objet extends Element{
	Type_obj obj;
	int id = 0;
	Objet(Etat e, int id, Type_obj obj) {
		super(e);
		this.id = id;
		this.obj = obj;
	}
}
