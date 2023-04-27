package entity;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public Player(GamePanel gp, KeyHandler keyH) {
        this.gp = gp;
        this.keyH = keyH;
        setDefaultValues();
        try {
            getPlayerImage();
        } catch (Exception e) {
            System.out.println("Can not get player image!");
        }
    }

    public void setDefaultValues() {
        x = 100;
        y = 100;
        speed = 4;
        direction = "down1";
    }

    public void getPlayerImage() {
        try {

            File f1 = new File("./res/player/boy_up_1.png");
            File f2 = new File("./res/player/boy_up_2.png");
            File f3 = new File("./res/player/boy_down_1.png");
            File f4 = new File("./res/player/boy_down_2.png");
            File f5 = new File("./res/player/boy_left_1.png");
            File f6 = new File("./res/player/boy_left_2.png");
            File f7 = new File("./res/player/boy_right_1.png");
            File f8 = new File("./res/player/boy_right_2.png");
            up1 = ImageIO.read(f1);
            up2 = ImageIO.read(f2);
            down1 = ImageIO.read(f3);
            down2 = ImageIO.read(f4);
            left1 = ImageIO.read(f5);
            left2 = ImageIO.read(f6);
            right1 = ImageIO.read(f7);
            right2 = ImageIO.read(f8);

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    public void update() {

        if (keyH.downPressed == true || keyH.upPressed == true || keyH.leftPressed == true
                || keyH.rightPressed == true) {
            if (keyH.upPressed == true) {
                direction = "up";
                y -= speed;
            }
            if (keyH.downPressed == true) {
                direction = "down";
                y += speed;
            }
            if (keyH.rightPressed == true) {
                direction = "right";
                x += speed;
            }
            if (keyH.leftPressed == true) {
                direction = "left";
                x -= speed;
            }
            spriteCounter++;
            if (spriteCounter > 12) {
                spriteNum = 3 - spriteNum;
                spriteCounter = 0;
            }
        }
    }

    public void draw(Graphics2D g2) {

        // g2.setColor(Color.white);
        // g2.fillRect(x, y, gp.tileSize, gp.tileSize);
        BufferedImage image = down1;
        switch (direction) {
            case "up":
                if (spriteNum == 1)
                    image = up1;
                if (spriteNum == 2)
                    image = up2;
                break;
            case "down":
                if (spriteNum == 1)
                    image = down1;
                if (spriteNum == 2)
                    image = down2;
                break;
            case "right":
                if (spriteNum == 1)
                    image = right1;
                if (spriteNum == 2)
                    image = right2;
                break;
            case "left":
                if (spriteNum == 1)
                    image = left1;
                if (spriteNum == 2)
                    image = left2;
        }
        g2.drawImage(image, x, y, gp.tileSize, gp.tileSize, null);

    }
}
