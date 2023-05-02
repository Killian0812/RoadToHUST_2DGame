package tile;

import java.awt.Graphics2D;
import java.io.File;
import java.io.IOException;
import java.util.Scanner;

import javax.imageio.ImageIO;

import main.GamePanel;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {

        this.gp = gp;
        tile = new Tile[15];
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
                // System.out.println(line);
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
            File f0 = new File("./res/tiles/path.png");
            File f1 = new File("./res/tiles/pathv.png");
            File f2 = new File("./res/tiles/pathh.png");
            File f3 = new File("./res/tiles/wall.png");
            File f4 = new File("./res/tiles/tree.png");
            File f5 = new File("./res/tiles/wood.png");
            File f6 = new File("./res/tiles/grass.png");
            File f7 = new File("./res/tiles/water.png");
            File f8 = new File("./res/tiles/h.png");
            File f9 = new File("./res/tiles/u.png");
            File f10 = new File("./res/tiles/s.png");
            File f11 = new File("./res/tiles/t.png");

            tile[0] = new Tile();
            tile[0].image = ImageIO.read(f0);

            tile[1] = new Tile();
            tile[1].image = ImageIO.read(f1);

            tile[2] = new Tile();
            tile[2].image = ImageIO.read(f2);

            tile[3] = new Tile();
            tile[3].image = ImageIO.read(f3);
            tile[3].collison = true;

            tile[4] = new Tile();
            tile[4].image = ImageIO.read(f4);
            tile[4].collison = true;

            tile[5] = new Tile();
            tile[5].image = ImageIO.read(f5);

            tile[6] = new Tile();
            tile[6].image = ImageIO.read(f6);

            tile[7] = new Tile();
            tile[7].image = ImageIO.read(f7);
            tile[7].collison = true;

            tile[8] = new Tile();
            tile[8].image = ImageIO.read(f8);
            tile[8].isFinishLine = true;

            tile[9] = new Tile();
            tile[9].image = ImageIO.read(f9);
            tile[9].isFinishLine = true;

            tile[10] = new Tile();
            tile[10].image = ImageIO.read(f10);
            tile[10].isFinishLine = true;

            tile[11] = new Tile();
            tile[11].image = ImageIO.read(f11);
            tile[11].isFinishLine = true;

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
            // --> player.screenX + playerToTileX = position of tile on screen
            int screenX = gp.player.screenX + playerToTileX;
            int screenY = gp.player.screenY + playerToTileY;

            // only draw if tile is in screen range
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
