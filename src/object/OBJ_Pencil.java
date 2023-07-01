package object;

import java.io.InputStream;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Pencil extends SuperObject {

    GamePanel gp;

    public OBJ_Pencil(GamePanel gp) {
        this.gp = gp;
        name = "Pencil";
        try {

            InputStream is = OBJ_Pencil.class.getResourceAsStream("/objects/pencil.png");
            image = ImageIO.read(is);
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
