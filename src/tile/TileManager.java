package tile;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
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
        mapTileNum = new int[gp.maxWorldRow][gp.maxWorldCol];

        try {
            getTileImage();
        } catch (Exception e) {
            System.out.println("Can not get tile image!");
        }

        try {
            loadMap("./res/maps/world01.txt");
        } catch (Exception e) {
            System.out.println("Can not load map data!");
        }
    }

    public void loadMap(String filePath) {
        try {
            File f = new File(filePath);
            Scanner sc = new Scanner(f);

            int i = 0, j;
            while (sc.hasNextLine() && i < gp.maxWorldRow) {
                String line = sc.nextLine();
                System.out.println(line);
                String numbers[] = line.split(" ");
                for (j = 0; j < gp.maxWorldCol; j++) {
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
            File f0 = new File("./res/tiles/grass.png");
            File f1 = new File("./res/tiles/wall.png");
            File f2 = new File("./res/tiles/water.png");
            File f3 = new File("./res/tiles/earth.png");
            File f4 = new File("./res/tiles/tree.png");
            File f5 = new File("./res/tiles/sand.png");
            tile[0] = new Tile();
            tile[0].image = ImageIO.read(f0);
            tile[1] = new Tile();
            tile[1].image = ImageIO.read(f1);
            tile[2] = new Tile();
            tile[2].image = ImageIO.read(f2);
            tile[3] = new Tile();
            tile[3].image = ImageIO.read(f3);
            tile[4] = new Tile();
            tile[4].image = ImageIO.read(f4);
            tile[5] = new Tile();
            tile[5].image = ImageIO.read(f5);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void draw(Graphics2D g2) {

        int worldCol = 0;
        int worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileType = mapTileNum[worldRow][worldCol];

            int worldX = worldCol * gp.tileSize;
            int worldY = worldRow * gp.tileSize;

            int playerToTileX = worldX - gp.player.worldX;
            int playerToTileY = worldY - gp.player.worldY;
            // playerToTileX = distance from player to tile
            // --> player.screenX + playerToTileX = tile position on screen
            int screenX = gp.player.screenX + playerToTileX;
            int screenY = gp.player.screenY + playerToTileY;

            // only draw if tile is in the screen
            if (Math.abs(playerToTileX) < gp.player.screenX + gp.tileSize &&
                Math.abs(playerToTileY) < gp.player.screenY + gp.tileSize)
                g2.drawImage(tile[tileType].image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }

    }
}
