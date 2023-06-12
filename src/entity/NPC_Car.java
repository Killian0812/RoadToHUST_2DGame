package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class NPC_Car extends Entity {

    public NPC_Car(GamePanel gp) {
        super(gp);
        speed = 6;
        direction = "";
        solidArea = new Rectangle(0, 0, gp.tileSize * 2, gp.tileSize);
        isCar = true;
        getImage();
    }

    public void getImage() {

        // down1 = setup("./res/npc/car/car_down_1");
        // down2 = setup("./res/npc/car/car_down_2");
        // up1 = setup("./res/npc/car/car_up_1");
        // up2 = setup("./res/npc/car/car_up_2");
        left1 = setup("./res/npc/car/sprite_0");
        left2 = setup("./res/npc/car/sprite_1");
        right1 = setup("./res/npc/car/sprite_2");
        right2 = setup("./res/npc/car/sprite_3");

    }

    public void setAction() {

        actionLockCounter++;
        /// change direction after 4s
        if (actionLockCounter == 240) {

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
