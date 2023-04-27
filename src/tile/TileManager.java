package tile;

import java.awt.Graphics2D;
// import java.io.BufferedReader;
import java.io.File;
// import java.io.FileInputStream;
import java.io.IOException;
// import java.io.InputStream;
// import java.io.InputStreamReader;
import java.util.Scanner;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

    GamePanel gp;
    Tile[] tile;
    int mapTileNum[][];

    public TileManager(GamePanel gp) {

        this.gp = gp;
        tile = new Tile[10];
        mapTileNum = new int[gp.maxScreenRow][gp.maxScreenCol];

        try {
            getTileImage();
        } catch (Exception e) {
            System.out.println("Can not get tile image!");
        }

        try {
            loadMap("./res/maps/map01.txt");
        } catch (Exception e) {
            System.out.println("Can not load map data!");
        }
    }

    public void loadMap(String filePath) {
        try {
            File f = new File(filePath);
            Scanner sc = new Scanner(f);

            int i = 0, j;
            while (sc.hasNextLine() && i < gp.maxScreenRow) {
                String line = sc.nextLine();
                System.out.println(line);
                String numbers[] = line.split(" ");
                for (j = 0; j < gp.maxScreenCol; j++) {
                    mapTileNum[i][j] = Integer.parseInt(numbers[j]);
                }
                i++;
            }
            sc.close();

        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void getTileImage() {
        try {
            File f1 = new File("./res/tiles/grass.png");
            File f2 = new File("./res/tiles/wall.png");
            File f3 = new File("./res/tiles/water.png");
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(f1);
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(f2);
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(f3);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        for (int i = 0; i < gp.maxScreenRow; i++)
            for (int j = 0; j < gp.maxScreenCol; j++)
                g2.drawImage(tile[mapTileNum[i][j]].image, j * gp.tileSize, i * gp.tileSize, gp.tileSize, gp.tileSize,
                        null);

    }
}
