package object;

import java.io.InputStream;

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

            InputStream is = OBJ_Shoes.class.getResourceAsStream("/objects/shoes/" + name + ".png");
            image = ImageIO.read(is);
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
