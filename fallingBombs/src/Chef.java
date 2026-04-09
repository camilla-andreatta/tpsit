import javax.imageio.ImageIO;
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.IOException;


public class Chef implements KeyListener {
    private int x;
    private int y;
    private int width;
    private int height;
    private int speed;
    private Image chef;

    public Chef(){
        this.speed = 5;
        this.width = 170;
        this.height = 200;
        this.x = 200;
        this.y = 590;
        try{
            this.chef = ImageIO.read(new File("src/Images/chef.png"));
        }catch (IOException e){
            e.printStackTrace();
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {

    }

    @Override
    public void keyPressed(KeyEvent e) {
        if(e.getKeyCode() == KeyEvent.VK_LEFT){
            moveLeft();
        }
        if(e.getKeyCode() == KeyEvent.VK_RIGHT){
            moveRight();
        }
        if(e.getKeyCode() == KeyEvent.VK_SPACE){
            throwKnife();
        }
    }

    public void update() {

    }

    @Override
    public void keyReleased(KeyEvent e) {

    }

    public void moveLeft() {
        //evitiamo che esca dalla finestra a sinistra
        x = Math.max(0, x - speed);
    }

    public void moveRight() {
        //evitiamo che esca dalla finestra a destra
        x = Math.min(550 - width, x + speed);
    }

    public void throwKnife() {

    }

    public void paint(Graphics g) {
        g.drawImage(chef, x, y, width, height, null);
    }
}
