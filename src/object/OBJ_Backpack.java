package object;

import java.io.File;

import javax.imageio.ImageIO;

public class OBJ_Backpack extends SuperObject {

    public OBJ_Backpack() {
    
            name = "Backpack";
            try {
    
                File f = new File("./res/objects/backpack.png");
                image = ImageIO.read(f);
    
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

}
