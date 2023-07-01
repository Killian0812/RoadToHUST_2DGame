package object;

import java.io.InputStream;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_HouseDoor extends SuperObject {

    GamePanel gp;

    public OBJ_HouseDoor(GamePanel gp) {
        this.gp = gp;
        name = "HouseDoor";
        solidArea.height = 96;
        needKey = true;
        try {

            InputStream is = OBJ_HouseDoor.class.getResourceAsStream("/objects/door.png");
            image = ImageIO.read(is);
            uTool.scaledImage(image, gp.tileSize, 2 * gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
