package object;

import java.io.InputStream;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Bread extends SuperObject {

    GamePanel gp;

    public OBJ_Bread(GamePanel gp) {
        this.gp = gp;
        name = "Bread";
        try {

            InputStream is = OBJ_Bread.class.getResourceAsStream("/objects/bread.png");
            image = ImageIO.read(is);
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
