package object;

import java.io.InputStream;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_BathroomDoor extends SuperObject {

    GamePanel gp;

    public OBJ_BathroomDoor(GamePanel gp) {
        this.gp = gp;
        name = "BathroomDoor";
        solidArea.height = 96;
        try {

            InputStream is = OBJ_BathroomDoor.class.getResourceAsStream("/objects/bathroomdoor.png");
            image = ImageIO.read(is);
            uTool.scaledImage(image, gp.tileSize, 2 * gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
