import javax.swing.*;

public class Main {
    public static void main(String[] args) {
        JFrame frame = new JFrame("Falling Bombs: make your best performance!");
        frame.setSize(550, 800);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        GUI gui = new GUI();
        frame.add(gui);

        frame.setVisible(true);
    }
}