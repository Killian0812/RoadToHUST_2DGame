package entity;

import java.util.Random;

import main.GamePanel;

public class NPC_Guest1 extends Entity {

    public NPC_Guest1(GamePanel gp) {

        super(gp);
        direction = "left";
        name = "Guest";
        isMoving = false;
        speed = 1;
        getImage();

    }

    public void getImage() {

        up1 = setup("./res/npc/guest1/guest1_up_1", gp.tileSize, gp.tileSize);
        up2 = setup("./res/npc/guest1/guest1_up_2", gp.tileSize, gp.tileSize);
        down1 = setup("./res/npc/guest1/guest1_down_1", gp.tileSize, gp.tileSize);
        down2 = setup("./res/npc/guest1/guest1_down_2", gp.tileSize, gp.tileSize);
        left1 = setup("./res/npc/guest1/guest1_left_1", gp.tileSize, gp.tileSize);
        left2 = setup("./res/npc/guest1/guest1_left_2", gp.tileSize, gp.tileSize);
        right1 = setup("./res/npc/guest1/guest1_right_1", gp.tileSize, gp.tileSize);
        right2 = setup("./res/npc/guest1/guest1_right_2", gp.tileSize, gp.tileSize);

    }

    public void setAction() {

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