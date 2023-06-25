package object;

import java.io.File;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Rubbish extends SuperObject {

    GamePanel gp;

    public OBJ_Rubbish(GamePanel gp) {
        this.gp = gp;
        name = "Rubbish";
        try {

            File f = new File("./res/objects/rubbish.png");
            image = ImageIO.read(f);
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
