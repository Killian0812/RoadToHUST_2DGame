package object;

import java.io.InputStream;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Book extends SuperObject {

    GamePanel gp;

    public OBJ_Book(GamePanel gp) {
        this.gp = gp;
        name = "Book";
        try {

            InputStream is = OBJ_Book.class.getResourceAsStream("/objects/book.png");
            image = ImageIO.read(is);
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
