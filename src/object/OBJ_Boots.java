package object;

import java.io.File;

import javax.imageio.ImageIO;

public class OBJ_Boots extends SuperObject {

    public OBJ_Boots() {

        name = "Boots";
        try {

            File f = new File("./res/objects/boots.png");
            image = ImageIO.read(f);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
