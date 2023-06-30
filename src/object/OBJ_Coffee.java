package object;

import java.io.File;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Coffee extends SuperObject {

    GamePanel gp;

    public OBJ_Coffee(GamePanel gp) {

        this.gp = gp;
        name = "Coffee";
        try {

            File f = new File("./res/objects/coffee.png");
            image = ImageIO.read(f);
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
