package object;

import java.io.InputStream;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Coffee extends SuperObject {

    GamePanel gp;

    public OBJ_Coffee(GamePanel gp) {

        this.gp = gp;
        name = "Coffee";
        try {

            InputStream is = OBJ_Coffee.class.getResourceAsStream("/objects/coffee.png");
            image = ImageIO.read(is);
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
