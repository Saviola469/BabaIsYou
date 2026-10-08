import java.util.ArrayList;
import java.util.List;

public class GestionnaireNiveaux {

    private List<Niveau> niveaux = new ArrayList<>();
    private int niveauCourant = 0;

    public GestionnaireNiveaux() {
        niveaux.add(new Niveau1());
        niveaux.add(new Niveau2());
        niveaux.add(new Niveau3());
    }

    public Plateau chargerNiveauCourant() {
        return niveaux.get(niveauCourant).creerPlateau();
    }

    public boolean niveauSuivant() {
        if (niveauCourant + 1 < niveaux.size()) {
            niveauCourant++;
            return true;
        }
        return false;
    }
}