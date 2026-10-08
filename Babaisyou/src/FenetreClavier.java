import java.awt.event.KeyListener;
import java.awt.event.KeyEvent;

public class FenetreClavier implements KeyListener {

    private Jeu jeu;

    public FenetreClavier(Jeu jeu) {
        this.jeu = jeu;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        char direction;

        switch (e.getKeyCode()) {
            case KeyEvent.VK_UP:    direction = 'N'; break;
            case KeyEvent.VK_DOWN:  direction = 'S'; break;
            case KeyEvent.VK_LEFT:  direction = 'O'; break;
            case KeyEvent.VK_RIGHT: direction = 'E'; break;
            default: return;
        }

        jeu.traiterAction(direction);
    }

    @Override
    public void keyReleased(KeyEvent e) {}
    @Override
    public void keyTyped(KeyEvent e) {}
}