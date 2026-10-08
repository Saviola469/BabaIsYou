import javax.swing.JFrame;

public class FenetreJeu extends JFrame {

    private PanelPlateau panel;

    public FenetreJeu(Jeu jeu, PanelPlateau panel) {
        this.panel = panel;

        add(panel);
        panel.addKeyListener(new FenetreClavier(jeu));

        pack();
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        panel.requestFocusInWindow();
    }

    public void rafraichirAffichage() {
        pack();
        revalidate();
        repaint();
    }
}
