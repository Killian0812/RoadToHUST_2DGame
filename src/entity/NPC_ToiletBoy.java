package entity;

import java.awt.Rectangle;

import main.GamePanel;

public class NPC_ToiletBoy extends Entity {

    public NPC_ToiletBoy(GamePanel gp) {

        super(gp);
        name = "ToiletBoy";
        direction = "down";
        solidArea = new Rectangle(0, 40, 70, 48);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        isMoving = false;
        speed = 0;
        getImage();

    }

    public void getImage() {

        up1 = setup("./res/npc/toilethust", 2 * gp.tileSize, 2 * gp.tileSize);
        up2 = setup("./res/npc/toilethust", 2 * gp.tileSize, 2 * gp.tileSize);
        down1 = setup("./res/npc/toilethust", 2 * gp.tileSize, 2 * gp.tileSize);
        down2 = setup("./res/npc/toilethust", 2 * gp.tileSize, 2 * gp.tileSize);
        left1 = setup("./res/npc/toilethust", 2 * gp.tileSize, 2 * gp.tileSize);
        left2 = setup("./res/npc/toilethust", 2 * gp.tileSize, 2 * gp.tileSize);
        right1 = setup("./res/npc/toilethust", 2 * gp.tileSize, 2 * gp.tileSize);
        right2 = setup("./res/npc/toilethust", 2 * gp.tileSize, 2 * gp.tileSize);

    }

    public void setAction() {
        ///
    }
}