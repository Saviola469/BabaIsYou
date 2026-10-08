public class Niveau2 extends Niveau {

    @Override
    public Plateau creerPlateau() {

        Plateau plateau = new Plateau(13, 13);

        plateau.setCase(6, 6, new Objet(Etat.JOUER, 0, Type_obj.Baba));
        plateau.setCase(6, 7, new Objet(Etat.FANTOME, 1, Type_obj.Roche));
        plateau.setCase(6, 8, new Objet(Etat.FANTOME, 2, Type_obj.Roche));
        plateau.setCase(2, 6, new Objet(Etat.VICTOIRE, 3, Type_obj.Flag));

        plateau.setCase(1, 1, new Sujet(Type_obj.Baba));
        plateau.setCase(1, 2, new Is());
        plateau.setCase(1, 3, new Action(Etat.JOUER));

        plateau.setCase(2, 1, new Sujet(Type_obj.Roche));
        plateau.setCase(2, 2, new Is());
        plateau.setCase(2, 3, new Action(Etat.POUSSABLE));

        plateau.setCase(3, 1, new Sujet(Type_obj.Flag));
        plateau.setCase(3, 2, new Is());
        plateau.setCase(3, 3, new Action(Etat.VICTOIRE));

        return plateau;
    }
}