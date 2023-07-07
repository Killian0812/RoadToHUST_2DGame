package main;

import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JFrame;

import java.awt.image.BufferedImage;

public class MyGame {

    public static JFrame window;

    public static BufferedImage image;

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
            InputStream is = MyGame.class.getResourceAsStream("/player/hust_boy/hust_boy_down_1.png");
            image = ImageIO.read(is);
            ImageIcon icon = new ImageIcon(image);
            window.setIconImage(icon.getImage());
        } catch (Exception e) {
            e.printStackTrace();
        }

        gamePanel.setupGame();
        gamePanel.startGameThread();

    }
}
