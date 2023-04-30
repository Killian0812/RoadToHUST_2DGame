package object;

import java.io.File;

import javax.imageio.ImageIO;

public class OBJ_Door extends SuperObject {

    public OBJ_Door() {

        name = "Door";
        try {

            File f = new File("./res/objects/door.png");
            image = ImageIO.read(f);

        } catch (Exception e) {
            e.printStackTrace();
        }
        collision = true;
        
    }
}
