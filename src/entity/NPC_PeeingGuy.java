package entity;

import java.awt.Rectangle;

import main.GamePanel;

public class NPC_PeeingGuy extends Entity {

    public NPC_PeeingGuy(GamePanel gp) {

        super(gp);
        direction = "down";
        name = "PeeingGuy";
        solidArea = new Rectangle(0, 10, 48, 36);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        isMoving = false;
        speed = 0;
        getImage();

    }

    public void getImage() {

        up1 = setup("./res/npc/peeing", gp.tileSize, gp.tileSize);
        up2 = setup("./res/npc/peeing", gp.tileSize, gp.tileSize);
        down1 = setup("./res/npc/peeing", gp.tileSize, gp.tileSize);
        down2 = setup("./res/npc/peeing", gp.tileSize, gp.tileSize);
        left1 = setup("./res/npc/peeing", gp.tileSize, gp.tileSize);
        left2 = setup("./res/npc/peeing", gp.tileSize, gp.tileSize);
        right1 = setup("./res/npc/peeing", gp.tileSize, gp.tileSize);
        right2 = setup("./res/npc/peeing", gp.tileSize, gp.tileSize);

    }

    public void setAction() {
        ///
    }
}
