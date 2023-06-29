package object;

import java.io.File;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Trashbag extends SuperObject {

    GamePanel gp;

    public OBJ_Trashbag(GamePanel gp) {
        this.gp = gp;
        name = "Trashbag";
        try {

            File f = new File("./res/objects/trashbag.png");
            image = ImageIO.read(f);
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }

}
