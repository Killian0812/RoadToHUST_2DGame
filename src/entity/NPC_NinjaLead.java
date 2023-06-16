package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class NPC_NinjaLead extends Entity {

    public NPC_NinjaLead(GamePanel gp) {
        super(gp);
        speed = 6;
        direction = "";
        solidArea = new Rectangle(8, 16, 72, gp.tileSize);
        isVehicle = true;
        getImage();
    }

    public void getImage() {

        left1 = setup("./res/npc/ninjalead/ninjalead_left_1", gp.tileSize * 2, gp.tileSize);
        left2 = setup("./res/npc/ninjalead/ninjalead_left_2", gp.tileSize * 2, gp.tileSize);
        right1 = setup("./res/npc/ninjalead/ninjalead_right_1", gp.tileSize * 2, gp.tileSize);
        right2 = setup("./res/npc/ninjalead/ninjalead_right_2", gp.tileSize * 2, gp.tileSize);

    }

    public void setAction() {

        actionLockCounter++;
        /// change direction after 2s
        if (actionLockCounter == 120) {

            Random random = new Random();
            int i = random.nextInt(100);
            if (i % 2 == 0)
                direction = "left";
            else
                direction = "right";

            actionLockCounter = 0;
        }
    }
}
