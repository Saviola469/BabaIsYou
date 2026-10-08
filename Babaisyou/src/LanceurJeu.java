public class LanceurJeu {

    public static void main(String[] args) {

        Images.charger();

        GestionnaireNiveaux gestionnaire = new GestionnaireNiveaux();
        Jeu jeu = new Jeu(gestionnaire);

        PanelPlateau panel = new PanelPlateau(jeu.getPlateau());
        jeu.setPanel(panel);

        FenetreJeu fenetre = new FenetreJeu(jeu, panel);
        jeu.setFenetre(fenetre);

        new FenetreJeu(jeu, panel);
    }
}