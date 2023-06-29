package object;

import java.io.File;

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

            File f = new File("./res/objects/bed.png");
            image = ImageIO.read(f);
            uTool.scaledImage(image, 60, 96);

        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
    }
}
