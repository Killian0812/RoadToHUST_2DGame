package object;

import java.io.InputStream;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Trashbag extends SuperObject {

    GamePanel gp;

    public OBJ_Trashbag(GamePanel gp) {
        this.gp = gp;
        name = "Trashbag";
        try {

            InputStream is = OBJ_Trashbag.class.getResourceAsStream("/objects/trashbag.png");
            image = ImageIO.read(is);
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }

}
