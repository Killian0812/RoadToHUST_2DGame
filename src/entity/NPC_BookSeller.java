package entity;

import java.awt.Rectangle;

import main.GamePanel;

public class NPC_BookSeller extends Entity {

    public NPC_BookSeller(GamePanel gp) {

        super(gp);
        name = "BookSeller";
        direction = "down";
        solidArea = new Rectangle(0, 0, gp.tileSize * 3, gp.tileSize * 3);
        solidAreaDefaultX = solidArea.x;
        solidAreaDefaultY = solidArea.y;
        isMoving = false;
        speed = 0;
        getImage();

    }

    public void getImage() {

        up1 = setup("./res/npc/bookseller", 3 * gp.tileSize, 3 * gp.tileSize);
        up2 = setup("./res/npc/bookseller", 3 * gp.tileSize, 3 * gp.tileSize);
        down1 = setup("./res/npc/bookseller", 3 * gp.tileSize, 3 * gp.tileSize);
        down2 = setup("./res/npc/bookseller", 3 * gp.tileSize, 3 * gp.tileSize);
        left1 = setup("./res/npc/bookseller", 3 * gp.tileSize, 3 * gp.tileSize);
        left2 = setup("./res/npc/bookseller", 3 * gp.tileSize, 3 * gp.tileSize);
        right1 = setup("./res/npc/bookseller", 3 * gp.tileSize, 3 * gp.tileSize);
        right2 = setup("./res/npc/bookseller", 3 * gp.tileSize, 3 * gp.tileSize);

    }

    public void setAction() {
        ///
    }
}