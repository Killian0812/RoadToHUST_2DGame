package object;

import java.io.File;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Backpack extends SuperObject {

    GamePanel gp;

    public OBJ_Backpack(GamePanel gp) {

        this.gp = gp;
        name = "Backpack";
        try {

            File f = new File("./res/objects/backpack.png");
            image = ImageIO.read(f);
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
