import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;


public class GUI extends JPanel {

    private Image sfondo;
    private Chef chef;
    private Timer timer;

    public GUI() {
        try {
            sfondo = ImageIO.read(new File("src/Images/kitchen_background.png"));
            chef = new Chef();
        } catch (IOException e) {
            e.printStackTrace();
        }

        setFocusable(true);
        requestFocusInWindow();
        addKeyListener(chef);
        timer = new Timer(10, e -> {
            update();
            repaint();
        });
        timer.start();
    }

    public void update() {
        // aggiorna lo chef (coltelli ecc.)
        if (chef != null) {
            chef.update();
        }
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);

        // disegna lo sfondo
        if (sfondo != null) {
            g.drawImage(sfondo, 0, 0, getWidth(), getHeight(), this);
        }

        // disegna lo chef statica
        if (chef != null) {
            chef.paint(g);
        }
    }
}