package entity;

import java.awt.Rectangle;
import java.util.Random;

import main.GamePanel;

public class NPC_Car extends Entity {

    public NPC_Car(GamePanel gp) {
        super(gp);
        speed = 6;
        direction = "";
        name = "Car";
        solidArea = new Rectangle(0, 10, gp.tileSize * 2, gp.tileSize / 2 + 8);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        isVehicle = true;
        getImage();
    }

    public void getImage() {

        // down1 = setup("./res/npc/car/car_down_1");
        // down2 = setup("./res/npc/car/car_down_2");
        // up1 = setup("./res/npc/car/car_up_1");
        // up2 = setup("./res/npc/car/car_up_2");
        left1 = setup("./res/npc/car/car_left_1", gp.tileSize * 2, gp.tileSize);
        left2 = setup("./res/npc/car/car_left_2", gp.tileSize * 2, gp.tileSize);
        right1 = setup("./res/npc/car/car_right_1", gp.tileSize * 2, gp.tileSize);
        right2 = setup("./res/npc/car/car_right_2", gp.tileSize * 2, gp.tileSize);

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
