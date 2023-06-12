package object;

import java.io.File;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Heart extends SuperObject {

    GamePanel gp;

    public OBJ_Heart(GamePanel gp) {
        this.gp = gp;
        name = "Heart";
        try {

            File f1 = new File("./res/objects/heart_full.png");
            File f2 = new File("./res/objects/heart_half.png");
            File f3 = new File("./res/objects/heart_blank.png");
            image = ImageIO.read(f1);
            image2 = ImageIO.read(f2);
            image3 = ImageIO.read(f3);
            image = uTool.scaledImage(image, 32 , 32);
            image2 = uTool.scaledImage(image2, 32, 32);
            image3 = uTool.scaledImage(image3, 32, 32);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
