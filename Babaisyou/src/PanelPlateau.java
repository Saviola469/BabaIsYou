import javax.swing.JPanel;
import java.awt.Graphics;
import java.awt.Color;
import java.awt.Dimension;

public class PanelPlateau extends JPanel {

    private Plateau plateau;
    private static final int TAILLE_CASE = 50;

    public PanelPlateau(Plateau plateau) {
        this.plateau = plateau;
        setFocusable(true);
    
        int largeur = plateau.colonnes * TAILLE_CASE;
        int hauteur = plateau.lignes * TAILLE_CASE;
        setPreferredSize(new Dimension(largeur, hauteur));
        
    }


    public void setPlateau(Plateau nouveauPlateau) {
        this.plateau = nouveauPlateau;
        repaint();
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        for (int i = 0; i < plateau.lignes; i++) {
            for (int j = 0; j < plateau.colonnes; j++) {

                // Fond
                g.drawImage(Images.tile,
                        j * TAILLE_CASE,
                        i * TAILLE_CASE,
                        TAILLE_CASE,
                        TAILLE_CASE,
                        null);

                Element e = plateau.getCase(i, j);
                dessinerElement(g, e, i, j);
            }
        }
    }

    private void dessinerElement(Graphics g, Element e, int i, int j) {

        if (e instanceof Objet) {
            Objet o = (Objet) e;

            switch (o.obj) {
                case Baba:
                    g.drawImage(Images.baba, j * TAILLE_CASE, i * TAILLE_CASE, null);
                    break;
                case Roche:
                    g.drawImage(Images.rock, j * TAILLE_CASE, i * TAILLE_CASE, null);
                    break;
                case Mur:
                case Barrier:
                    g.drawImage(Images.wall, j * TAILLE_CASE, i * TAILLE_CASE, null);
                    break;
                case Flag:
                    g.drawImage(Images.flag, j * TAILLE_CASE, i * TAILLE_CASE, null);
                    break;
            }
        }

        if (e instanceof Is) {
            g.drawImage(Images.is, j * TAILLE_CASE, i * TAILLE_CASE, null);
        }

        if (e instanceof Sujet) {
            Sujet s = (Sujet) e;
            switch (s.obj) {
                case Baba:  g.drawImage(Images.text_baba, j * TAILLE_CASE, i * TAILLE_CASE, null); break;
                case Roche: g.drawImage(Images.text_rock, j * TAILLE_CASE, i * TAILLE_CASE, null); break;
                case Mur:   g.drawImage(Images.text_wall, j * TAILLE_CASE, i * TAILLE_CASE, null); break;
                case Flag:  g.drawImage(Images.text_win,  j * TAILLE_CASE, i * TAILLE_CASE, null); break;
            }
        }

        if (e instanceof Action) {
            Action a = (Action) e;
            switch (a.action) {
                case POUSSABLE: g.drawImage(Images.text_push, j * TAILLE_CASE, i * TAILLE_CASE, null); break;
                case BLOQUE:    g.drawImage(Images.text_stop, j * TAILLE_CASE, i * TAILLE_CASE, null); break;
                case JOUER:     g.drawImage(Images.text_you,  j * TAILLE_CASE, i * TAILLE_CASE, null); break;
                case VICTOIRE:  g.drawImage(Images.text_win,  j * TAILLE_CASE, i * TAILLE_CASE, null); break;
            }
        }
    }
}