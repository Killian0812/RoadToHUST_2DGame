package object;

import java.io.InputStream;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Trashcan extends SuperObject {

    GamePanel gp;

    public OBJ_Trashcan(GamePanel gp) {
        this.gp = gp;
        name = "Trashcan";
        try {

            InputStream is = OBJ_Trashcan.class.getResourceAsStream("/objects/trashcan.png");
            image = ImageIO.read(is);
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
