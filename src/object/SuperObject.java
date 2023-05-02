package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;
import main.UtilityTool;;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    UtilityTool uTool = new UtilityTool();

    public void draw(Graphics2D g2, GamePanel gp) {

        int playerToObjX = worldX - gp.player.worldX;
        int playerToObjY = worldY - gp.player.worldY;

        int screenX = gp.player.screenX + playerToObjX;
        int screenY = gp.player.screenY + playerToObjY;

        int drawSize = gp.tileSize / 2;
        if (name == "Door" || name == "Chest" || name == "Backpack")
            drawSize *= 2;

        if (Math.abs(playerToObjX) < gp.player.screenX + gp.tileSize &&
                Math.abs(playerToObjY) < gp.player.screenY + gp.tileSize)
            g2.drawImage(image, screenX, screenY, drawSize, drawSize, null);
    }

}
