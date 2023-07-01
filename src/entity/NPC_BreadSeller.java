package entity;

import java.awt.Rectangle;

import main.GamePanel;

public class NPC_BreadSeller extends Entity {

    public NPC_BreadSeller(GamePanel gp) {

        super(gp);
        name = "BreadSeller";
        direction = "down";
        solidArea = new Rectangle(0, 40, 96, 48);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        isMoving = false;
        speed = 0;
        getImage();

    }

    public void getImage() {

        up1 = setup("/npc/breadseller", 2 * gp.tileSize, 2 * gp.tileSize);
        up2 = setup("/npc/breadseller", 2 * gp.tileSize, 2 * gp.tileSize);
        down1 = setup("/npc/breadseller", 2 * gp.tileSize, 2 * gp.tileSize);
        down2 = setup("/npc/breadseller", 2 * gp.tileSize, 2 * gp.tileSize);
        left1 = setup("/npc/breadseller", 2 * gp.tileSize, 2 * gp.tileSize);
        left2 = setup("/npc/breadseller", 2 * gp.tileSize, 2 * gp.tileSize);
        right1 = setup("/npc/breadseller", 2 * gp.tileSize, 2 * gp.tileSize);
        right2 = setup("/npc/breadseller", 2 * gp.tileSize, 2 * gp.tileSize);

    }

    public void setAction() {
        ///
    }
}