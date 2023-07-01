package object;

import java.io.InputStream;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Bed extends SuperObject {

    GamePanel gp;

    public OBJ_Bed(GamePanel gp) {
        this.gp = gp;
        name = "Bed";
        solidArea.width = 60;
        solidArea.height = 96;
        try {

            InputStream is = OBJ_Bed.class.getResourceAsStream("/objects/bed.png");
            image = ImageIO.read(is);
            uTool.scaledImage(image, 60, 96);

        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
