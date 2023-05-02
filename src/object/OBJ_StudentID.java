package object;

import java.io.File;

import javax.imageio.ImageIO;

public class OBJ_StudentID extends SuperObject {

    public OBJ_StudentID() {
    
            name = "StudentID";
            try {
    
                File f = new File("./res/objects/studentID.png");
                image = ImageIO.read(f);
    
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

}
