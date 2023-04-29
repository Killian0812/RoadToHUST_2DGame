package object;

import java.io.File;

import javax.imageio.ImageIO;

public class OBJ_Chest extends SuperObject {

    public OBJ_Chest() {

        name = "Chest";
        try {

            File f = new File("./res/objects/chest.png");
            image = ImageIO.read(f);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
