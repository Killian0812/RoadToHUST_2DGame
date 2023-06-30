package object;

import java.io.File;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Trashcan extends SuperObject {

    GamePanel gp;

    public OBJ_Trashcan(GamePanel gp) {
        this.gp = gp;
        name = "Trashcan";
        try {

            File f = new File("./res/objects/trashcan.png");
            image = ImageIO.read(f);
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
