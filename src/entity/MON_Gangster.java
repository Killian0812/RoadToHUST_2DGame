package entity;

import java.util.Random;

import main.GamePanel;

public class MON_Gangster extends Entity {

    public MON_Gangster(GamePanel gp) {

        super(gp);

        name = "Gangster";
        speed = 2;
        defaultSpeed = speed;
        maxLife = 6;
        life = maxLife;

        solidArea.x = 3;
        solidArea.y = 18;
        solidArea.width = 42;
        solidArea.height = 30;
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;

        getImage();
        getAttackImage();

    }

    public void getImage() {
        up1 = setup("./res/npc/gangster/gangster_up_1", 48, 48);
        up2 = setup("./res/npc/gangster/gangster_up_2", 48, 48);
        down1 = setup("./res/npc/gangster/gangster_down_1", 48, 48);
        down2 = setup("./res/npc/gangster/gangster_down_2", 48, 48);
        left1 = setup("./res/npc/gangster/gangster_left_1", 48, 48);
        left2 = setup("./res/npc/gangster/gangster_left_2", 48, 48);
        right1 = setup("./res/npc/gangster/gangster_right_1", 48, 48);
        right2 = setup("./res/npc/gangster/gangster_right_2", 48, 48);
    }

    public void getAttackImage() {
        atkUp1 = setup("./res/npc/gangster/gangster_attack_up_1", 48, 96);
        atkUp2 = setup("./res/npc/gangster/gangster_attack_up_2", 48, 96);
        atkDown1 = setup("./res/npc/gangster/gangster_attack_down_1", 48, 96);
        atkDown2 = setup("./res/npc/gangster/gangster_attack_down_2", 48, 96);
        atkLeft1 = setup("./res/npc/gangster/gangster_attack_left_1", 96, 48);
        atkLeft2 = setup("./res/npc/gangster/gangster_attack_left_2", 96, 48);
        atkRight1 = setup("./res/npc/gangster/gangster_attack_right_1", 96, 48);
        atkRight2 = setup("./res/npc/gangster/gangster_attack_right_2", 96, 48);
    }

    public void update() {
        super.update();
        int xDistance = Math.abs(worldX - gp.player.worldX);
        int yDistance = Math.abs(worldY - gp.player.worldY);
        int tileDistance = (xDistance + yDistance) / gp.tileSize;

        // if (onPath == false && tileDistance <= 3) {
        // if (new Random().nextInt(100) > 50)
        // onPath = true;
        // }
        if (onPath == true && tileDistance > 10) {
            onPath = false;
        }
        if (attacking == true) {
            if (tileDistance <= 3)
                attack();
            else
                attacking = false;
        }
    }

    public void setAction() {

        super.setAction();

        if (onPath == true) {
            int goalCol = (gp.player.worldX + gp.player.solidArea.x) / gp.tileSize;
            int goalRow = (gp.player.worldY + gp.player.solidArea.y) / gp.tileSize;
            searchPath(goalRow, goalCol);
        } else {
            actionLockCounter++;
            // change direction after 2s
            if (actionLockCounter == 120) {
                Random random = new Random();
                int i = random.nextInt(100) + 1;
                if (i <= 25)
                    direction = "up";
                else if (i <= 50)
                    direction = "down";
                else if (i <= 75)
                    direction = "left";
                else
                    direction = "right";
                actionLockCounter = 0;
            }
        }
    }
}