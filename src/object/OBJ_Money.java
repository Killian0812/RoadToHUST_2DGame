package object;

import java.io.InputStream;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Money extends SuperObject {

    GamePanel gp;

    public OBJ_Money(GamePanel gp) {
        this.gp = gp;
        name = "Money";
        try {

            InputStream is = OBJ_Money.class.getResourceAsStream("/objects/money.png");
            image = ImageIO.read(is);
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
