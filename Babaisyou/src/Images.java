import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import java.io.IOException;

public class Images {

    public static BufferedImage baba;
    public static BufferedImage rock;
    public static BufferedImage wall;
    public static BufferedImage flag;
    public static BufferedImage is;

    public static BufferedImage text_baba;
    public static BufferedImage text_rock;
    public static BufferedImage text_wall;
    public static BufferedImage text_push;
    public static BufferedImage text_stop;
    public static BufferedImage text_win;
    public static BufferedImage text_you;

    public static BufferedImage tile;

    public static void charger() {
        try {
            baba = ImageIO.read(Images.class.getResource("/ressources/baba.png"));
            rock = ImageIO.read(Images.class.getResource("/ressources/rock.png"));
            wall = ImageIO.read(Images.class.getResource("/ressources/wall.png"));
            flag = ImageIO.read(Images.class.getResource("/ressources/flag.png"));
            is   = ImageIO.read(Images.class.getResource("/ressources/is.png"));

            text_baba = ImageIO.read(Images.class.getResource("/ressources/text_baba.png"));
            text_rock = ImageIO.read(Images.class.getResource("/ressources/text_rock.png"));
            text_wall = ImageIO.read(Images.class.getResource("/ressources/text_wall.png"));
            text_push = ImageIO.read(Images.class.getResource("/ressources/text_push.png"));
            text_stop = ImageIO.read(Images.class.getResource("/ressources/text_stop.png"));
            text_win  = ImageIO.read(Images.class.getResource("/ressources/text_win.png"));
            text_you  = ImageIO.read(Images.class.getResource("/ressources/text_you.png"));

            tile = ImageIO.read(Images.class.getResource("/ressources/tile.png"));

        } catch (IOException e) {
            System.err.println("Erreur chargement images");
            e.printStackTrace();
        }
    }
}