import javax.swing.JOptionPane;

public class Jeu {

    private Plateau plateau;
    private PanelPlateau panel;
    private FenetreJeu fenetre;
    private GestionnaireNiveaux gestionnaire;

    public Jeu(GestionnaireNiveaux gestionnaire) {
        this.gestionnaire = gestionnaire;
        this.plateau = gestionnaire.chargerNiveauCourant();
    }

    public Plateau getPlateau() {
        return plateau;
    }

    public void setPanel(PanelPlateau panel) {
        this.panel = panel;
    }

    public void setFenetre(FenetreJeu fenetre) {
        this.fenetre = fenetre;
    }

    public void traiterAction(char direction) {

        plateau.reinitialiserEtats();

        for (int i = 0; i < plateau.lignes; i++) {
            for (int j = 0; j < plateau.colonnes; j++) {
                plateau.synthaxeRegle(i, j);
            }
        }

        for (int i = 0; i < plateau.lignes; i++) {
            for (int j = 0; j < plateau.colonnes; j++) {

                Element el = plateau.getCase(i, j);

                if (el instanceof Objet && el.getEtat() == Etat.JOUER) {
                    int resultat = plateau.deplacement(i, j, direction);

                    if (resultat == 1000) {

                        if (gestionnaire.niveauSuivant()) {
                            plateau = gestionnaire.chargerNiveauCourant();
                            panel.setPlateau(plateau);
                            fenetre.rafraichirAffichage();
                        } else {
                            JOptionPane.showMessageDialog(
                                    fenetre,
                                    "Victoire ! Tous les niveaux sont terminés.",
                                    "Fin du jeu",
                                    JOptionPane.INFORMATION_MESSAGE
                            );
                        }
                        return;
                    }

                }
            }
        }

        panel.repaint();
    }
}
