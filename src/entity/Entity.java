package entity;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class Entity {

    GamePanel gp;

    // Coordinates
    public int worldX, worldY;

    // Character basic info
    public int speed;
    public String name;
    public int defaultSpeed;

    // Animation
    public BufferedImage up1, up2, down1, down2, left1, left2, right1, right2, dead1, dead2;
    public BufferedImage atkUp1, atkUp2, atkDown1, atkDown2, atkLeft1, atkLeft2, atkRight1, atkRight2;

    // Moving
    public String direction;
    public int spriteCounter = 0;
    public int spriteNum = 1;
    public int actionLockCounter = 0;
    public boolean collisionOn = false;

    // Character collision area
    public Rectangle solidArea;
    public int solidAreaDefaultX, solidAreaDefaultY;

    // Attack and vulnerable status
    public boolean attacking = false;
    public int atkCounter = 0;
    public Rectangle atkArea = new Rectangle(0, 0, 36, 36);
    public boolean isInvicible = false;
    public int invincibleCounter = 0;
    public boolean knockBack = false;
    public int knockBackCounter = 0;

    // Character status
    public int maxLife;
    public int life;
    public boolean isDead = false;
    public boolean onPath = false;

    // Character speaking
    public String dialogues0[] = new String[20];
    public String dialogues1[] = new String[20];
    public int dialogueIndex = 0;

    // Extra
    public boolean isVehicle = false;
    public boolean hitPlayer = false;
    public boolean displayHP = false;
    public int displayHPCounter = 0;
    public int verticalScale = 1;
    public int horizontalScale = 1;
    public boolean isCarrying = false;
    public String objCarry;

    public Entity(GamePanel gp) {
        this.gp = gp;
        this.solidArea = new Rectangle(8, 24, gp.tileSize / 2, gp.tileSize / 2);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        this.name = "";
    }

    public BufferedImage setup(String imageName, int width, int height) {
        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {
            File f = new File(imageName + ".png");
            image = ImageIO.read(f);
            image = uTool.scaledImage(image, width, height);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
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

    public void setDialogue(String textForBoy[], String textForGirl[]) {
        dialogues0 = textForBoy;
        dialogues1 = textForGirl;
    }

    public void checkCollision() {
        collisionOn = false;
        gp.cChecker.checkTile(this, false);
        gp.cChecker.checkObject(this, false);
        gp.cChecker.checkEntity(this, gp.npc);
        gp.cChecker.checkEntity(this, gp.aggroNPC);
        gp.cChecker.checkEntity(this, gp.monster);
        hitPlayer = gp.cChecker.checkPlayer(this);
    }

    public void setAction() {
        //
    }

    public void update() {

        if (knockBack == true) {

            checkCollision();
            if (collisionOn == true) {
                knockBackCounter = 0;
                knockBack = false;
                speed = defaultSpeed;
            } else {
                switch (gp.player.direction) {
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
            knockBackCounter++;
            if (knockBackCounter == 5) {
                knockBack = false;
                knockBackCounter = 0;
                speed = defaultSpeed;
            }
        } else {

            setAction();
            checkCollision();

            if (hitPlayer == true) {
                if (name.equals("Guider1") && gp.player.hasID == true) {
                    String text44[] = { "Nhanh đi học đi" };
                    setDialogue(text44, text44);
                    dialogueIndex = 0;
                }
                if (name.equals("Car") || name.equals("NinjaLead")) {
                    if (direction == "left") {
                        gp.player.worldX -= gp.tileSize / 2;
                        gp.player.life = 0;
                        gp.player.deadScene = 1;
                    }
                    if (direction == "right") {
                        gp.player.worldX += gp.tileSize / 2;
                        gp.player.life = 0;
                        gp.player.deadScene = 2;
                    }
                    gp.gameState = gp.gameOverState;
                    gp.ui.isDead = true;
                    gp.playSE(7);
                }
                if (name.equals("Slime") || name.equals("Orc")) {
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
        }

        spriteCounter++;
        if (attacking == true) {

        } else if (spriteCounter > 13) {
            spriteNum = 3 - spriteNum;
            spriteCounter = 0;
        }

        if (isInvicible == true) {
            invincibleCounter++;
            if (invincibleCounter > 30) {
                isInvicible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void searchPath(int goalRow, int goalCol) {
        int startRow = (worldY + solidArea.y) / gp.tileSize;
        int startCol = (worldX + solidArea.x) / gp.tileSize;

        gp.pFinder.setNodes(startRow, startCol, goalRow, goalCol);
        if (gp.pFinder.search() == true) {

            // next worldX, worldY
            int nextX = gp.pFinder.pathList.get(0).col * gp.tileSize;
            int nextY = gp.pFinder.pathList.get(0).row * gp.tileSize;

            // entity's solidArea position
            int enLeftX = worldX + solidArea.x;
            int enRightX = enLeftX + solidArea.width;
            int enTopY = worldY + solidArea.y;
            int enBottomY = enTopY + solidArea.height;

            if (enTopY > nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "up";
            } else if (enTopY < nextY && enLeftX >= nextX && enRightX < nextX + gp.tileSize) {
                direction = "down";
            } else if (enTopY >= nextY && enBottomY < nextY + gp.tileSize) {
                if (enLeftX > nextX)
                    direction = "left";
                if (enLeftX < nextX)
                    direction = "right";
            } else if (enTopY > nextY && enLeftX > nextX) {
                direction = "up";
                checkCollision();
                if (collisionOn == true)
                    direction = "left";
            } else if (enTopY > nextY && enLeftX < nextX) {
                direction = "up";
                checkCollision();
                if (collisionOn == true)
                    direction = "right";
            } else if (enTopY < nextY && enLeftX > nextX) {
                direction = "down";
                checkCollision();
                if (collisionOn == true)
                    direction = "left";
            } else if (enTopY < nextY && enLeftX < nextX) {
                direction = "down";
                checkCollision();
                if (collisionOn == true)
                    direction = "right";
            }

            // int nextCol = gp.pFinder.pathList.get(0).col;
            // int nextRow = gp.pFinder.pathList.get(0).row;
            // if (nextCol == goalCol && nextRow == goalRow)
            // onPath = false;

        }
    }

    public void dyingAnimation() {
        //
    }

    public void attack() {
        spriteCounter++;
        if (spriteCounter <= 15)
            spriteNum = 1;
        else if (spriteCounter <= 60)
            spriteNum = 2;
        else
            spriteCounter = 0;

        // Save current values for later reset
        int currentWorldX = worldX;
        int currentWorldY = worldY;

        // Adjust worldX/Y for attack
        switch (direction) {
            case "up": {
                worldY -= atkArea.height;
                break;
            }
            case "down": {
                worldY += atkArea.height;
                break;
            }
            case "left": {
                worldX -= atkArea.width;
                break;
            }
            case "right": {
                worldX += atkArea.width;
                break;
            }
        }
        solidArea.width = atkArea.width;
        solidArea.height = atkArea.height;

        checkCollision();
        if (hitPlayer == true) {
            damagePlayer();
        }

        // Reset
        worldX = currentWorldX;
        worldY = currentWorldY;
        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
    }

    public boolean checkInRange(int vertical, int horizontal) {
        boolean targetInRange = false;
        int xDistance = Math.abs((gp.player.worldX - worldX) / gp.tileSize);
        int yDistance = Math.abs((gp.player.worldY - worldY) / gp.tileSize);
        switch (direction) {
            case "up":
                if (gp.player.worldY < worldY && yDistance < vertical && xDistance < horizontal)
                    targetInRange = true;
                break;
            case "down":
                if (gp.player.worldY > worldY && yDistance < vertical && xDistance < horizontal)
                    targetInRange = true;
                break;
            case "left":
                if (gp.player.worldX < worldX && yDistance < vertical && xDistance < horizontal)
                    targetInRange = true;
                break;
            case "right":
                if (gp.player.worldX > worldX && yDistance < vertical && xDistance < horizontal)
                    targetInRange = true;
                break;
        }
        return targetInRange;
    }

    public void damagePlayer() {
        if (gp.player.isInvicible == false) {
            gp.player.life--;
            gp.playSE(7);
            gp.player.isInvicible = true;
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = null;

        int playerToNPCX = worldX - gp.player.worldX;
        int playerToNPCY = worldY - gp.player.worldY;

        int screenX = gp.player.screenX + playerToNPCX;
        int screenY = gp.player.screenY + playerToNPCY;

        int tempScreenX = screenX;
        int tempScreenY = screenY;

        if (Math.abs(playerToNPCX) < gp.player.screenX + gp.tileSize &&
                Math.abs(playerToNPCY) < gp.player.screenY + gp.tileSize)
            switch (direction) {
                case "up": {
                    if (attacking == true) {
                        tempScreenY = screenY - gp.tileSize;
                        if (spriteNum == 1)
                            image = atkUp1;
                        else
                            image = atkUp2;
                    } else {
                        if (spriteNum == 1)
                            image = up1;
                        if (spriteNum == 2)
                            image = up2;
                    }
                    break;
                }
                case "down": {
                    if (attacking == true) {
                        if (spriteNum == 1)
                            image = atkDown1;
                        else
                            image = atkDown2;
                    } else {
                        if (spriteNum == 1)
                            image = down1;
                        if (spriteNum == 2)
                            image = down2;
                    }
                    break;
                }
                case "right": {
                    if (attacking == true) {
                        if (spriteNum == 1)
                            image = atkRight1;
                        else
                            image = atkRight2;
                    } else {
                        if (spriteNum == 1)
                            image = right1;
                        if (spriteNum == 2)
                            image = right2;
                    }
                    break;
                }
                case "left": {
                    if (attacking == true) {
                        tempScreenX = screenX - gp.tileSize;
                        if (spriteNum == 1)
                            image = atkLeft1;
                        else
                            image = atkLeft2;
                    } else {
                        if (spriteNum == 1)
                            image = left1;
                        if (spriteNum == 2)
                            image = left2;
                    }
                    break;
                }
            }

        // Display HP bar
        if (displayHP == true) {

            double oneUnit = (double) gp.tileSize / maxLife;
            double hpBarLength = oneUnit * life;

            if (name.equals("Slime") || name.equals("Gangster") || name.equals("Orc")) {
                g2.setColor(new Color(0, 0, 0, 150));
                g2.fillRect(screenX - 1, screenY - 11, gp.tileSize + 2, 7);
                g2.setColor(new Color(255, 0, 30));
                g2.fillRect(screenX, screenY - 10, (int) hpBarLength, 5);
            }

            displayHPCounter++;
            if (displayHPCounter > 400) {
                displayHP = false;
                displayHPCounter = 0;
            }
        }

        if (isInvicible == true) {
            displayHP = true;
            displayHPCounter = 0;
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f));
        }
        if (isDead == true) {
            dyingAnimation();
        }
        if (isVehicle == false) {
            verticalScale = 1;
            horizontalScale = 1;
            if (attacking == true) {
                if (direction.equals("down") || direction.equals("up"))
                    verticalScale = 2;
                else
                    horizontalScale = 2;
                g2.drawImage(image, tempScreenX, tempScreenY, gp.tileSize * horizontalScale,
                        gp.tileSize * verticalScale, null);
            } else
                g2.drawImage(image, tempScreenX, tempScreenY, gp.tileSize, gp.tileSize, null);
        } else
            g2.drawImage(image, screenX, screenY, gp.tileSize * 2, gp.tileSize + 20, null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));

    }

}
