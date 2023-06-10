package entity;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public int keyCount = 0;
    public boolean hasPencil = false;
    public boolean hasID = true;
    public boolean hasBook = false;
    public boolean hasBackpack = false;

    public int isDead = 0;

    public Player(GamePanel gp, KeyHandler keyH) {

        super(gp);

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

        // worldX = gp.tileSize * 50;
        // worldY = gp.tileSize * 8;
        worldX = gp.tileSize * 50;
        worldY = gp.tileSize * 26;
        // worldX = gp.tileSize * 25;
        // worldY = gp.tileSize * 25;
        speed = 4;
        direction = "down1";
        keyCount = 0;
        hasPencil = false;
        hasID = false;
        hasBook = false;
        hasBackpack = false;
    }

    public void getPlayerImage() {

        up1 = setup("up_1");
        up2 = setup("up_2");
        down1 = setup("down_1");
        down2 = setup("down_2");
        left1 = setup("left_1");
        left2 = setup("left_2");
        right1 = setup("right_1");
        right2 = setup("right_2");
        dead1 = setup("dead_1");
        dead2 = setup("dead_2");

    }

    public BufferedImage setup(String imageName) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {

            File f = null;
            f = new File("./res/player/hust_boy/hust_boy_" + imageName + ".png");
            if (gp.playerGender == 1)
                f = new File("./res/player/hust_girl/hust_girl_" + imageName + ".png");
            image = ImageIO.read(f);
            image = uTool.scaledImage(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    public void update() {

        if (keyH.downPressed == true || keyH.upPressed == true || keyH.leftPressed == true
                || keyH.rightPressed == true || keyH.enterPressed == true) {

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

            /// Check tile collision
            collisionOn = false;
            gp.cChecker.checkTile(this, true);

            /// Check object collision & interact
            int objIndex = gp.cChecker.checkObject(this, true);
            objectInteraction(objIndex);

            /// Check npc collision & interact
            int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
            npcInteraction(npcIndex);

            if (collisionOn == false && keyH.enterPressed == false)
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

    public void objectInteraction(int index) {
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
                case "Backpack":
                    gp.obj[index] = null;
                    gp.playSE(1);
                    gp.ui.showMsg("You've got a backpack!");
                    hasBackpack = true;
                    break;
                case "Book":
                    if (hasBackpack == true) {
                        gp.obj[index] = null;
                        gp.playSE(1);
                        gp.ui.showMsg("You've got a book!");
                        hasBook = true;
                    } else
                        gp.ui.showMsg("You need a backpack!");
                    break;
                case "StudentID":
                    if (hasBackpack == true) {
                        gp.obj[index] = null;
                        gp.playSE(1);
                        gp.ui.showMsg("You've got a student ID!");
                        hasID = true;
                    } else
                        gp.ui.showMsg("You need a backpack!");
                    break;
                case "Pencil":
                    if (hasBackpack == true) {
                        gp.obj[index] = null;
                        gp.playSE(1);
                        gp.ui.showMsg("You've got a pencil!");
                        hasPencil = true;
                    } else
                        gp.ui.showMsg("You need a backpack!");
                    break;
            }
        }
    }

    public void npcInteraction(int index) {

        if (index != 999) {
            if (gp.keyH.enterPressed == true) {
                gp.gameState = gp.dialogueState;
                gp.npc[index].speak();
            }
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = down1;

        if (isDead != 0) {
            if (isDead == 1)
                image = dead1;
            else
                image = dead2;
        }
        else switch (direction) {
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
