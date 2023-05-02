package object;

import java.io.File;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Chest extends SuperObject {

    GamePanel gp;

    public OBJ_Chest(GamePanel gp) {
        this.gp = gp;
        name = "Chest";
        try {

            File f = new File("./res/objects/chest.png");
            image = ImageIO.read(f);
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
