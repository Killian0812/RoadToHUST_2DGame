package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.UtilityTool;;

public class SuperObject {

    public BufferedImage image, image2, image3;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;
    public int type;
    public boolean needKey = false;

    public int useCount = 0;

    UtilityTool uTool = new UtilityTool();

    public void draw(Graphics2D g2, GamePanel gp) {

        int playerToObjX = worldX - gp.player.worldX;
        int playerToObjY = worldY - gp.player.worldY;

        int screenX = gp.player.screenX + playerToObjX;
        int screenY = gp.player.screenY + playerToObjY;

        int drawSize = gp.tileSize / 2;
        if (name == "Pencil")
            drawSize -= 10;
        if (name == "Chest" || name == "Trashcan" || name == "Trashbag")
            drawSize *= 2;
        if (name == "Door")
            drawSize *= 4;
        if (name == "Backpack")
            drawSize += 15;

        if (Math.abs(playerToObjX) < gp.player.screenX + gp.tileSize &&
                Math.abs(playerToObjY) < gp.player.screenY + gp.tileSize)
            if (name == "HouseDoor" || name == "BathroomDoor")
                g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize * 2, null);
            else if (name == "Bed")
                g2.drawImage(image, screenX, screenY, 60, 96, null);
            else if (name == "Key")
                g2.drawImage(image, screenX, screenY, drawSize - 5, drawSize, null);
            else
                g2.drawImage(image, screenX, screenY, drawSize, drawSize, null);
    }

}
