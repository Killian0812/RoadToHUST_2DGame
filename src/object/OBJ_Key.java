package object;

import java.io.File;

import javax.imageio.ImageIO;

public class OBJ_Key extends SuperObject {

    public OBJ_Key() {

        name = "Key";
        try {

            File f = new File("./res/objects/key.png");
            image = ImageIO.read(f);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
