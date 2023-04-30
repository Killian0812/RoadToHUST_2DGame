package object;

import java.awt.Graphics2D;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

import main.GamePanel;;

public class SuperObject {

    public BufferedImage image;
    public String name;
    public boolean collision = false;
    public int worldX, worldY;
    public Rectangle solidArea = new Rectangle(0, 0, 48, 48);
    public int solidAreaDefaultX = 0;
    public int solidAreaDefaultY = 0;

    public void draw(Graphics2D g2, GamePanel gp) {

        int playerToObjX = worldX - gp.player.worldX;
        int playerToObjY = worldY - gp.player.worldY;

        int screenX = gp.player.screenX + playerToObjX;
        int screenY = gp.player.screenY + playerToObjY;

        if (Math.abs(playerToObjX) < gp.player.screenX + gp.tileSize &&
                Math.abs(playerToObjY) < gp.player.screenY + gp.tileSize)
            g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
    }

}
