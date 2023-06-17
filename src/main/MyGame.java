package main;

import java.io.File;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.image.BufferedImage;

public class MyGame {

    public static JFrame window;

    public static void main(String[] args) {

        window = new JFrame();
        window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        window.setResizable(false);
        window.setTitle("Road to HUST");

        GamePanel gamePanel = new GamePanel();

        window.add(gamePanel);
        window.pack();

        window.setLocationRelativeTo(null);
        window.setVisible(true);

        try {
            File f = new File("./res/player/hust_boy/hust_boy_down_1.png");
            BufferedImage image = ImageIO.read(f);
            ImageIcon icon = new ImageIcon(image);
            window.setIconImage(icon.getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        gamePanel.setupGame();
        gamePanel.startGameThread();

    }
}
