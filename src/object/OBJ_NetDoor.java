package object;

import java.io.InputStream;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_NetDoor extends SuperObject {

    GamePanel gp;

    public OBJ_NetDoor(GamePanel gp) {
        this.gp = gp;
        name = "Door";
        solidArea.width = 96;
        solidArea.height = 96;
        try {

            InputStream is = OBJ_NetDoor.class.getResourceAsStream("/objects/netdoor.png");
            image = ImageIO.read(is);
            uTool.scaledImage(image, 2 * gp.tileSize, 2 * gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
