package object;

import java.io.File;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Bread extends SuperObject {

    GamePanel gp;

    public OBJ_Bread(GamePanel gp) {
        this.gp = gp;
        name = "Bread";
        try {

            File f = new File("./res/objects/bread.png");
            image = ImageIO.read(f);
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
