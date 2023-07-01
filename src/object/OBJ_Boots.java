package object;

import java.io.InputStream;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Boots extends SuperObject {

    GamePanel gp;

    public OBJ_Boots(GamePanel gp) {
        this.gp = gp;
        name = "Boots";
        try {

            InputStream is = OBJ_Boots.class.getResourceAsStream("/objects/boots.png");
            image = ImageIO.read(is);
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
