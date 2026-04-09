import javax.imageio.ImageIO;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class Knife extends Thread{
    private int x;
    private int y;
    private int width;
    private int height;
    private int speed;
    private Image knife;

    public Knife() {
        this.speed = 5;
        this.width = 50;
        this.height = 50;
        this.x = 0;
        this.y = 0;
        try{
            this.knife = ImageIO.read(new File("src/Images/knife.png"));
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public void update() {
        
    }

    public void paint(Graphics g) {
        g.drawImage(knife, x, y, width, height, null);
    }

    @Override
    public void run() {
        super.run();
    }
}
