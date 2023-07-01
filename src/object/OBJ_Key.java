package object;

import java.io.InputStream;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Key extends SuperObject {

    GamePanel gp;

    public OBJ_Key(GamePanel gp) {
        this.gp = gp;
        name = "Key";
        try {

            InputStream is = OBJ_Key.class.getResourceAsStream("/objects/key.png");
            image = ImageIO.read(is);
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
