package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {

    GamePanel gp;

    public int worldX, worldY;

    public int speed;
    public String name;

    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, dead1, dead2;
    public String direction;

    public int spriteCounter = 0;
    public int spriteNum = 1;

    public boolean collisionOn = false;

    public boolean isCar = false;

    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;

    public int actionLockCounter = 0;

    public boolean isInvicible = false;
    public int invincibleCounter = 0;

    public String dialogues0[] = new String[20];
    public String dialogues1[] = new String[20];
    public int dialogueIndex = 0;

    public boolean hitPlayer = false;

    // Character status
    public int maxLife;
    public int life;

    public Entity(GamePanel gp) {
        this.gp = gp;
        this.solidArea = new Rectangle(0, 0, gp.tileSize, gp.tileSize);
        this.name = "";
    }

    public void speak() {

        if (gp.player.gender == 0) {
            gp.ui.currentDialogue = dialogues0[dialogueIndex];
            if (dialogueIndex + 1 < dialogues0.length)
                dialogueIndex++;
        } else {
            gp.ui.currentDialogue = dialogues1[dialogueIndex];
            if (dialogueIndex + 1 < dialogues1.length)
                dialogueIndex++;
        }

        switch (gp.player.direction) {
            case "up":
                direction = "down";
                break;
            case "down":
                direction = "up";
                break;
            case "left":
                direction = "right";
                break;
            case "right":
                direction = "left";
        }
    }

    public void setAction() {
        ///
    }

    public void setDialogue(String textForBoy[], String textForGirl[]) {
        dialogues0 = textForBoy;
        dialogues1 = textForGirl;
    }

    public void update() {

        setAction();

        collisionOn = false;
        gp.cChecker.checkTile(this, false);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.monster);
        hitPlayer = gp.cChecker.checkPlayer(this);

        if (hitPlayer == true) {
            if (name.equals("Guider1") && gp.player.hasID == true) {
                String text44[] = { "Nhanh đi học đi" };
                setDialogue(text44, text44);
                dialogueIndex = 0;
            }
            if (name.equals("Car")) {
                if (direction == "left") {
                    gp.player.worldX -= gp.tileSize / 2;
                    gp.player.isDead = 1;
                }
                if (direction == "right") {
                    gp.player.worldX += gp.tileSize / 2;
                    gp.player.isDead = 2;
                }
                gp.gameState = gp.gameOverState;
                gp.ui.isDead = true;
                gp.playSE(6);
            }
            if (name.equals("Slime")) {
                if (gp.player.isInvicible == false) {
                    gp.player.life -= 1;
                    gp.player.isInvicible = true;
                }
            }
        }

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

        spriteCounter++;
        if (spriteCounter > 13) {
            spriteNum = 3 - spriteNum;
            spriteCounter = 0;
        }

    }

    public BufferedImage setup(String imageName) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            File f = new File(imageName + ".png");
            image = ImageIO.read(f);
            image = uTool.scaledImage(image, gp.tileSize, gp.tileSize);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        int playerToNPCX = worldX - gp.player.worldX;
        int playerToNPCY = worldY - gp.player.worldY;

        int screenX = gp.player.screenX + playerToNPCX;
        int screenY = gp.player.screenY + playerToNPCY;

        if (Math.abs(playerToNPCX) < gp.player.screenX + gp.tileSize &&
                Math.abs(playerToNPCY) < gp.player.screenY + gp.tileSize)
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

        if (isInvicible == true)
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));

        if (isCar == false)
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
        else
            g2.drawImage(image, screenX, screenY, gp.tileSize * 2, gp.tileSize + 20, null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

    }

}
