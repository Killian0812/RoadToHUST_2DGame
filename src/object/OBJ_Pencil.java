package object;

import java.io.File;

import javax.imageio.ImageIO;

public class OBJ_Pencil extends SuperObject {

    public OBJ_Pencil() {
    
            name = "Pencil";
            try {
    
                File f = new File("./res/objects/pencil.png");
                image = ImageIO.read(f);
    
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

}
