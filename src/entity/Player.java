package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public int keyCount = 0;

    public Player(GamePanel gp, KeyHandler keyH) {

        this.gp = gp;
        this.keyH = keyH;

        screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        screenY = gp.screenHeight / 2 - gp.tileSize / 2;

        solidArea = new Rectangle(8, 20, gp.tileSize / 2, gp.tileSize / 2);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        setDefaultValues();
        try {
            getPlayerImage();
        } catch (Exception e) {
            System.out.println("Can not get player image!");
        }
    }

    public void setDefaultValues() {
        worldX = gp.tileSize * 23;
        worldY = gp.tileSize * 21;
        speed = 4;
        direction = "down1";
    }

    public void getPlayerImage() {
        try {

            File f1 = new File("./res/player/hust_boy/hust_boy_up_1.png");
            File f2 = new File("./res/player/hust_boy/hust_boy_up_2.png");
            File f3 = new File("./res/player/hust_boy/hust_boy_down_1.png");
            File f4 = new File("./res/player/hust_boy/hust_boy_down_2.png");
            File f5 = new File("./res/player/hust_boy/hust_boy_left_1.png");
            File f6 = new File("./res/player/hust_boy/hust_boy_left_2.png");
            File f7 = new File("./res/player/hust_boy/hust_boy_right_1.png");
            File f8 = new File("./res/player/hust_boy/hust_boy_right_2.png");
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
            if (keyH.upPressed == true)
                direction = "up";
            if (keyH.downPressed == true)
                direction = "down";
            if (keyH.rightPressed == true)
                direction = "right";
            if (keyH.leftPressed == true)
                direction = "left";

            spriteCounter++;
            if (spriteCounter > 13) {
                spriteNum = 3 - spriteNum;
                spriteCounter = 0;
            }

            /// Check collision
            collisionOn = false;
            gp.cChecker.checkTile(this);

            int objIndex = gp.cChecker.checkObject(this, true);
            pickUpObject(objIndex);

            if (collisionOn == false)
                switch (direction) {
                    case "up":
                        worldY -= speed;
                        break;
                    case "down":
                        worldY += speed;
                        break;
                    case "left":
                        worldX -= speed;
                        break;
                    case "right":
                        worldX += speed;
                        break;
                }

        }
    }

    public void pickUpObject(int index) {
        if (index != 999) {
            String objectName = gp.obj[index].name;
            switch (objectName) {
                case "Key":
                    gp.obj[index] = null;
                    keyCount++;
                    gp.playSE(1);
                    gp.ui.showMsg("You've got a key!");
                    break;
                case "Door":
                    if (keyCount > 0) {
                        keyCount--;
                        gp.obj[index] = null;
                        gp.playSE(4);
                        gp.ui.showMsg("You've opened the door!");
                    } else {
                        gp.ui.showMsg("You need a key!");
                    }
                    break;
                case "Boots":
                    speed += 2;
                    gp.obj[index] = null;
                    gp.playSE(3);
                    gp.ui.showMsg("Speed up!");
                    break;
                case "Chest":
                    gp.ui.gameFinished = true;
                    gp.stopMusic();
                    gp.playSE(2);
            }
        }
    }

    public void draw(Graphics2D g2) {

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
        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

    }
}
