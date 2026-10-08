
public class Is extends Texte{
	static int id_libre = 0;
	int id;
	Is() {
		super();
		id = id_libre;
		id_libre++;
	}
}
