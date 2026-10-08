public class Plateau {
    private Element[][] matrice;
    int lignes;
    int colonnes;

    // Le constructeur initialise la structure
    public Plateau(int lignes, int colonnes) {
        this.lignes = lignes;
        this.colonnes = colonnes;
		this.matrice = new Element[lignes][colonnes];
        
        // On remplit par défaut pour éviter les NullPointerException
        initialiserPlateau();
    }
    //Initialisation du plateau en remplissant le contour de barrière et 
    //l'intérieur du plateau d'élément fantôme
    private void initialiserPlateau() {
		for (int i = 0; i < lignes; i++) {
			for (int j = 0; j < colonnes; j++) {
				if (i == 0 || i == lignes - 1 || j == 0 || j == colonnes - 1) {
					matrice[i][j] = new Objet(Etat.BLOQUE, 0, Type_obj.Barrier);
				}
				else {
					matrice[i][j] = new Element(Etat.FANTOME);
				}
			}
		}
	}

    // Méthode pour modifier une case précise
    public void setCase(int l, int c, Element nouvelleElement) {
        if (estDansLesLimites(l, c)) {
            this.matrice[l][c] = nouvelleElement;
        }
    }

    // Méthode pour récupérer une case
    public Element getCase(int l, int c) {
        if (estDansLesLimites(l, c)) {
            return this.matrice[l][c];
        }
        return null; 
    }

    // Sécurité pour éviter de sortir du tableau (IndexOutOfBounds)
    private boolean estDansLesLimites(int l, int c) {
        return l >= 0 && l < lignes && c >= 0 && c < colonnes;
    }
    
    /* Observe si un is à la synthaxe nécessaire pour appliquer une règle et l'applique si la synthaxe est bonne
     * Renvoie -1 si la case observer n'est pas un is
     * Renvoie 0 si le is ne répond à aucune synthaxe,
     * Renvoie 1 ou 2 si le is possède une ou deux synthaxes valides
     */
    public int synthaxeRegle(int l, int c) {
    	Element cursor = getCase(l,c);
    	if (cursor instanceof Is) {
    		Element precedent;
    		Element prochain;
    		int compte = 0;
    		if(estDansLesLimites(l-1,c) && estDansLesLimites(l+1,c)) {
    			precedent = getCase(l - 1, c);
    			prochain = getCase(l + 1, c);
    		}
    		else {
    			precedent = new Element();
    			prochain = new Element();
    		}
    		Sujet s;
    		Action a;
    		if(precedent instanceof Sujet && prochain instanceof Action) {
    			s = (Sujet)precedent;
    			a = (Action)prochain;
    			this.updateEtat(s,a);
    			compte++;
    		}
    		if(estDansLesLimites(l, c-1) && estDansLesLimites(l, c+1)){
    			precedent = getCase(l, c - 1);
    			prochain = getCase(l, c + 1);
    		}
    		else {
    			precedent = new Element();
    			prochain = new Element();
    		}
        	if(precedent instanceof Sujet && prochain instanceof Action) {
        		s = (Sujet)precedent;
        		a = (Action)prochain;
        		this.updateEtat(s,a);
        		compte++;
    		}
        	return compte;
    	}
    	return -1;
    }
    
	// Change l'état de tout les objets concernés par le sujet en l'état stocké dans action
	public void updateEtat(Sujet s,Action a) {
		for(int i = 1; i < lignes - 1; i++) {
			for(int j = 1; j < colonnes - 1; j++ ) {
				Element cursor = getCase(i,j);
				if(cursor instanceof Objet && ((Objet) cursor).obj == s.obj) {
					cursor.setEtat(a.action);
				}
			}
		}
	}

	// Regarde si le déplacemet est possible retourne la première occurence d'un emplacement libre fantome et -1 sinon
	int deplacementOK(int i, int j, char direction) {
		Etat etat_p;

		switch(direction) {

			case 'N':
				for (int k = i - 1; k >= 0; k--) {
					etat_p = getCase(k, j).getEtat();
					if (k == i - 1 && etat_p == Etat.VICTOIRE) return k;
					else if (etat_p == Etat.BLOQUE || etat_p == Etat.VICTOIRE) return -1;
					else if (etat_p == Etat.FANTOME) return k;
				}
				break;

			case 'O':
				for (int k = j - 1; k >= 0; k--) {
					etat_p = getCase(i, k).getEtat();
					if (k == j - 1 && etat_p == Etat.VICTOIRE) return k;
					else if (etat_p == Etat.BLOQUE || etat_p == Etat.VICTOIRE) return -1;
					else if (etat_p == Etat.FANTOME) return k;
				}
				break;

			case 'S':
				for (int k = i + 1; k < lignes; k++) {
					etat_p = getCase(k, j).getEtat();
					if (k == i + 1 && etat_p == Etat.VICTOIRE) return k;
					else if (etat_p == Etat.BLOQUE || etat_p == Etat.VICTOIRE) return -1;
					else if (etat_p == Etat.FANTOME) return k;
				}
				break;
				/*{
					int k = i + 1;

					if (!estDansLesLimites(k, j)) return -1;

					etat_p = getCase(k, j).getEtat();

					if (etat_p == Etat.VICTOIRE) return k;
					if (etat_p == Etat.FANTOME) return k;

					return -1;
				}*/

			case 'E':
				for (int k = j + 1; k < colonnes; k++) {
					etat_p = getCase(i, k).getEtat();
					if (k == j + 1 && etat_p == Etat.VICTOIRE) return k;
					else if (etat_p == Etat.BLOQUE || etat_p == Etat.VICTOIRE) return -1;
					else if (etat_p == Etat.FANTOME) return k;
				}
				break;
		}

		return -1;
	}
	
	// Effectue le déplacement dans la matrice
	public int deplacement(int i, int j, char direction) {
		Element cursor = getCase(i, j);
		int index = deplacementOK(i, j, direction);

		if (index < 0) return index;

		switch(direction) {

			case 'N':
				if (getCase(i - 1, j).getEtat() == Etat.VICTOIRE) return 1000;
				for (int k = index; k < i; k++) {
					setCase(k, j, getCase(k + 1, j));
				}
				setCase(i, j, new Element(Etat.FANTOME));
				break;

			case 'S':
				if (getCase(i + 1, j).getEtat() == Etat.VICTOIRE) return 1000;
				for (int k = index; k > i; k--) {
					setCase(k, j, getCase(k - 1, j));
				}
				setCase(i, j, new Element(Etat.FANTOME));
				break;

			case 'O':
				if (getCase(i, j - 1).getEtat() == Etat.VICTOIRE) return 1000;
				for (int k = index; k < j; k++) {
					setCase(i, k, getCase(i, k + 1));
				}
				setCase(i, j, new Element(Etat.FANTOME));
				break;

			case 'E':
				if (getCase(i, j + 1).getEtat() == Etat.VICTOIRE) return 1000;
				for (int k = index; k > j; k--) {
					setCase(i, k, getCase(i, k - 1));
				}
				setCase(i, j, new Element(Etat.FANTOME));
				break;
		}

		return index;
	}


	public void reinitialiserEtats() {
		for (int i = 0; i < lignes; i++) {
			for (int j = 0; j < colonnes; j++) {

				Element e = getCase(i, j);

				if (e instanceof Objet) {
					Objet o = (Objet) e;

					// État par défaut selon le type d’objet
					switch (o.obj) {
						case Baba:
						case Roche:
						case Flag:
							o.setEtat(Etat.FANTOME);
							break;

						case Mur:
						case Barrier:
							o.setEtat(Etat.BLOQUE);
							break;
					}
				}
			}
		}
	}

//    Cette partie servira à debbuguer en cas de pb
//    public void afficher() {
//        for (int i = 0; i < lignes; i++) {
//            for (int j = 0; j < colonnes; j++) {
//                System.out.print(matrice[i][j] + " ");
//            }
//            System.out.println();
//        }
//    }
}