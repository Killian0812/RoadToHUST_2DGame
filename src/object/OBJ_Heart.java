package object;

import java.io.InputStream;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Heart extends SuperObject {

    GamePanel gp;

    public OBJ_Heart(GamePanel gp) {
        this.gp = gp;
        name = "Heart";
        try {

            InputStream is = OBJ_Heart.class.getResourceAsStream("/objects/heart_full.png");
            image = ImageIO.read(is);
            is = OBJ_Heart.class.getResourceAsStream("/objects/heart_half.png");
            image2 = ImageIO.read(is);
            is = OBJ_Heart.class.getResourceAsStream("/objects/heart_blank.png");
            image3 = ImageIO.read(is);
            image = uTool.scaledImage(image, 32, 32);
            image2 = uTool.scaledImage(image2, 32, 32);
            image3 = uTool.scaledImage(image3, 32, 32);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
