package object;

import java.io.File;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_Book extends SuperObject {

    GamePanel gp;

    public OBJ_Book(GamePanel gp) {
        this.gp = gp;
            name = "Book";
            try {
    
                File f = new File("./res/objects/book.png");
                image = ImageIO.read(f);
                uTool.scaledImage(image, gp.tileSize, gp.tileSize);
    
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

}
