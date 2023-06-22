package entity;

import java.awt.AlphaComposite;
import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.KeyHandler;
import main.UtilityTool;
import object.OBJ_Money;

public class Player extends Entity {

    GamePanel gp;
    KeyHandler keyH;

    public final int screenX;
    public final int screenY;

    public int moneyCount = 0;
    public int keyCount = 0;
    public boolean hasPencil = false;
    public boolean hasID = false;
    public boolean hasBook = false;
    public boolean hasBackpack = false;

    final public Rectangle tmp;

    public int gender = 0;
    public int deadScene = 0;

    public Player(GamePanel gp, KeyHandler keyH, int gender) {

        super(gp);

        this.gp = gp;
        this.keyH = keyH;

        this.gender = gender;

        screenX = gp.screenWidth / 2 - gp.tileSize / 2;
        screenY = gp.screenHeight / 2 - gp.tileSize / 2;

        solidArea = new Rectangle(8, 20, gp.tileSize / 2, gp.tileSize / 2);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        atkArea.width = 36;
        atkArea.height = 36;

        tmp = new Rectangle(8, 20, gp.tileSize / 2, gp.tileSize / 2);

        setDefaultValues();
        try {
            getPlayerImage();
            getPlayerAttackImage();
        } catch (Exception e) {
            System.out.println("Can not get player image!");
        }
    }

    public void setDefaultValues() {

        worldX = gp.tileSize * 50;
        worldY = gp.tileSize * 8;
        // worldX = gp.tileSize * 50;
        // worldY = gp.tileSize * 26;
        // worldX = gp.tileSize * 25;
        // worldY = gp.tileSize * 25;
        // worldX = gp.tileSize * 33;
        // worldY = gp.tileSize * 61;

        deadScene = 0;
        isDead = false;
        speed = 4;
        defaultSpeed = speed;
        direction = "down1";

        keyCount = 0;
        hasPencil = false;
        hasID = false;
        hasBook = false;
        hasBackpack = false;

        maxLife = 6;
        life = maxLife;
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

    public void getPlayerAttackImage() {

        atkUp1 = setup("attack_up_1");
        atkUp2 = setup("attack_up_2");
        atkDown1 = setup("attack_down_1");
        atkDown2 = setup("attack_down_2");
        atkLeft1 = setup("attack_left_1");
        atkLeft2 = setup("attack_left_2");
        atkRight1 = setup("attack_right_1");
        atkRight2 = setup("attack_right_2");

    }

    public BufferedImage setup(String imageName) {

        UtilityTool uTool = new UtilityTool();
        BufferedImage image = null;

        try {

            File f = null;
            f = new File("./res/player/hust_boy/hust_boy_" + imageName + ".png");
            if (gender == 1)
                f = new File("./res/player/hust_girl/hust_girl_" + imageName + ".png");
            image = ImageIO.read(f);
            image = uTool.scaledImage(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return image;
    }

    public void update() {

        if (gp.keyH.spaceTyped == true) {
            attacking = true;
            // System.out.println("Attacking");
            attack();
        } else
            attacking = false;

        if (life <= 0)
            dead();

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

            if (attacking == false) {
                spriteCounter++;
                if (spriteCounter > 13) {
                    spriteNum = 3 - spriteNum;
                    spriteCounter = 0;
                }
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

            /// Check aggro npc collision & interact
            int aggroNPCIndex = gp.cChecker.checkEntity(this, gp.aggroNPC);
            aggroNPCInteraction(aggroNPCIndex);

            /// Check monster collision
            int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
            contactMonster(monsterIndex);

            gp.eHandler.checkEvent();

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

        if (isInvicible == true) {
            invincibleCounter++;
            if (invincibleCounter > 60) {
                isInvicible = false;
                invincibleCounter = 0;
            }
        }
    }

    public void objectInteraction(int index) {
        if (index != 999) {
            String objectName = gp.obj[index].name;
            switch (objectName) {
                case "Money":
                    gp.obj[index] = null;
                    moneyCount++;
                    gp.playSE(1);
                    gp.ui.showMsg("You've got a dollar!");
                    break;
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

    public void aggroNPCInteraction(int index) {
        if (index != 999) {
            if (gp.keyH.enterPressed == true) {
                gp.gameState = gp.dialogueState;
                gp.aggroNPC[index].speak();
            }
        }
    }

    public void contactMonster(int index) {
        if (index != 999) {
            if (isInvicible == false) {
                life -= 1;
                if (life <= 0)
                    dead();
                else
                    isInvicible = true;
            }
        }
    }

    public void dead() {
        if (direction == "up" || direction == "right")
            deadScene = 1;
        else
            deadScene = 2;
        gp.gameState = gp.gameOverState;
        gp.ui.isDead = true;
        gp.playSE(7);
    }

    public void attack() {
        spriteCounter++;
        if (spriteCounter > 20) {
            spriteNum = 3 - spriteNum;
            spriteCounter = 0;
        }
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

        // Check monster collison with adjusted worldX,Y and solidArea
        int monsterIndex = gp.cChecker.checkEntity(this, gp.monster);
        damageMonster(monsterIndex);

        // Check aggro npc collison with adjusted worldX,Y and solidArea
        int aggroNPCIndex = gp.cChecker.checkEntity(this, gp.aggroNPC);
        damageAggroNPC(aggroNPCIndex);

        // Reset
        worldX = currentWorldX;
        worldY = currentWorldY;
        solidArea.x = 8;
        solidArea.y = 20;
        solidArea.width = gp.tileSize / 2;
        solidArea.height = gp.tileSize / 2;
    }

    public void damageMonster(int index) {
        if (index != 999) {
            // System.out.println("Hit");
            if (gp.monster[index].isInvicible == false) {
                gp.monster[index].isInvicible = true;
                gp.monster[index].life -= 1;
                if (gp.monster[index].name == "Orc")
                    gp.monster[index].attacking = true;
                gp.playSE(8);
                gp.monster[index].onPath = true;
                knockbackMonster(gp.monster[index]);
                if (gp.monster[index].life <= 0) {
                    gp.monster[index].isDead = true;
                    
                    // Monster item drop
                    if (gp.monster[index].isCarrying == true) {
                        if (gp.monster[index].objCarry == "Money") {
                            gp.obj[++gp.aSetter.objNum] = new OBJ_Money(gp);
                            gp.obj[gp.aSetter.objNum].worldX = gp.monster[index].worldX;
                            gp.obj[gp.aSetter.objNum].worldY = gp.monster[index].worldY;
                        }
                    }
                    gp.monster[index] = null;
                }
            }
        } else {
            // System.out.println("Miss");
        }
    }

    public void damageAggroNPC(int index) {
        if (index != 999) {
            // System.out.println("Hit");
            if (gp.aggroNPC[index].isInvicible == false) {
                gp.aggroNPC[index].isInvicible = true;
                gp.aggroNPC[index].life -= 1;
                if (gp.aggroNPC[index].name == "Gangster")
                    gp.aggroNPC[index].attacking = true;
                gp.playSE(8);
                gp.aggroNPC[index].onPath = true;
                knockbackMonster(gp.aggroNPC[index]);
                if (gp.aggroNPC[index].life <= 0) {

                    gp.aggroNPC[index].isDead = true;
                    // NPC item drop
                    if (gp.aggroNPC[index].isCarrying == true) {
                        if (gp.aggroNPC[index].objCarry == "Money") {
                            gp.obj[++gp.aSetter.objNum] = new OBJ_Money(gp);
                            gp.obj[gp.aSetter.objNum].worldX = gp.aggroNPC[index].worldX;
                            gp.obj[gp.aSetter.objNum].worldY = gp.aggroNPC[index].worldY;
                        }
                        gp.aggroNPC[index].isCarrying = false;
                    }
                    // gp.aggroNPC[index] = null;
                }
            }
        } else {
            // System.out.println("Miss");
        }
    }

    public void knockbackMonster(Entity entity) {
        if (entity.knockBack == false) {
            entity.direction = direction;
            entity.speed += 5;
            entity.knockBack = true;
        } else {
            if (entity.direction.equals(direction) == false) {
                if (direction.equals("up"))
                    entity.direction = "down";
                if (direction.equals("down"))
                    entity.direction = "up";
                if (direction.equals("left"))
                    entity.direction = "right";
                if (direction.equals("right"))
                    entity.direction = "up";
            }
            entity.speed += 5;
        }
    }

    public void draw(Graphics2D g2) {

        BufferedImage image = down1;

        if (deadScene != 0) {
            if (deadScene == 1)
                image = dead1;
            else
                image = dead2;
        } else
            switch (direction) {
                case "up": {
                    if (attacking == true) {
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

        if (isInvicible == true && isDead == false)
            g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.7f));

        g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);

        g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 1f));
    }
}
