package object;

import java.io.InputStream;

import javax.imageio.ImageIO;

import main.GamePanel;

public class OBJ_StudentID extends SuperObject {

    GamePanel gp;

    public OBJ_StudentID(GamePanel gp) {
        this.gp = gp;
        name = "StudentID";
        try {

            InputStream is = OBJ_StudentID.class.getResourceAsStream("/objects/studentID.png");
            image = ImageIO.read(is);
            uTool.scaledImage(image, gp.tileSize, gp.tileSize);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

}
