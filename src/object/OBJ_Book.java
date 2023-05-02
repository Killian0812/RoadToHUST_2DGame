package object;

import java.io.File;

import javax.imageio.ImageIO;

public class OBJ_Book extends SuperObject {

    public OBJ_Book() {
    
            name = "Book";
            try {
    
                File f = new File("./res/objects/book.png");
                image = ImageIO.read(f);
    
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

}
