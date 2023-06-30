package object;

import java.io.File;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Money extends SuperObject {

    GamePanel gp;

    public OBJ_Money(GamePanel gp) {
        this.gp = gp;
        name = "Money";
        try {

            File f = new File("./res/objects/money.png");
            image = ImageIO.read(f);
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
