package object;

import java.io.InputStream;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Backpack extends SuperObject {

    GamePanel gp;

    public OBJ_Backpack(GamePanel gp) {

        this.gp = gp;
        name = "Backpack";
        try {

            InputStream is = OBJ_Backpack.class.getResourceAsStream("/objects/backpack.png");
            image = ImageIO.read(is);
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
