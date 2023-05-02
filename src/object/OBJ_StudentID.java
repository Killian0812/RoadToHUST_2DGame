package object;

import java.io.File;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_StudentID extends SuperObject {

    GamePanel gp;

    public OBJ_StudentID(GamePanel gp) {
        this.gp = gp;
        name = "StudentID";
        try {

            File f = new File("./res/objects/studentID.png");
            image = ImageIO.read(f);
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
