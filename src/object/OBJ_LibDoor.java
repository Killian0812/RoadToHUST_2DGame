package object;

import java.io.File;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_LibDoor extends SuperObject {

    GamePanel gp;

    public OBJ_LibDoor(GamePanel gp) {
        this.gp = gp;
        name = "Door";
        solidArea.width = 96;
        solidArea.height = 96;
        try {

            File f = new File("./res/objects/libdoor.png");
            image = ImageIO.read(f);
            uTool.scaledImage(image, 2 * gp.tileSize, 2 * gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
