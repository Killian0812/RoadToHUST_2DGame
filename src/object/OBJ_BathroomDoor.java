package object;

import java.io.File;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_BathroomDoor extends SuperObject {

    GamePanel gp;

    public OBJ_BathroomDoor(GamePanel gp) {
        this.gp = gp;
        name = "BathroomDoor";
        solidArea.height = 96;
        try {

            File f = new File("./res/objects/bathroomdoor.png");
            image = ImageIO.read(f);
            uTool.scaledImage(image, gp.tileSize, 2 * gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
