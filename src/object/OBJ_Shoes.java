package object;

import java.io.File;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Shoes extends SuperObject {

    GamePanel gp;

    public OBJ_Shoes(GamePanel gp, int type) {
        this.gp = gp;
        this.type = type;
        switch (type) {
            case 1:
                name = "Converse";
                break;
            case 2:
                name = "Jordan";
                break;
            case 3:
                name = "Adidas";
        }
        try {

            File f = new File("./res/objects/shoes/" + name + ".png");
            image = ImageIO.read(f);
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
