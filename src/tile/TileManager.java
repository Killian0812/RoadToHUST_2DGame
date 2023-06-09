package tile;

import java.awt.Graphics2D;
import java.io.File;
import java.util.Scanner;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][];

    public TileManager(GamePanel gp) {

        this.gp = gp;
        tile = new Tile[20];
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

        setup(0, "path", false, false);
        setup(1, "pathv", false, false);
        setup(2, "pathh", false, false);
        setup(3, "wall", true, false);
        setup(4, "tree new", true, false);
        setup(5, "wood", false, false);
        setup(6, "grass new", false, false);
        setup(7, "water new 10", true, false);
        setup(8, "h new", false, true);
        setup(9, "u new", false, true);
        setup(10, "s new", false, true);
        setup(11, "t new", false, true);
        setup(12, "water new 2", true, false);
        setup(13, "water new 3", true, false);
        setup(14, "water new 4", true, false);
        setup(15, "water new 9", true, false);
        setup(16, "water new 5", true, false);
        setup(17, "water new 8", true, false);
        setup(18, "water new 6", true, false);
        setup(19, "water new 7", true, false);

    }

    public void setup(int index, String imagePath, boolean collision, boolean isFinishLine) {

        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            File f = new File("./res/tiles/" + imagePath + ".png");
            tile[index].image = ImageIO.read(f);
            tile[index].image = uTool.scaledImage(tile[index].image, gp.tileSize, gp.tileSize);
            tile[index].collision = collision;
            tile[index].isFinishLine = isFinishLine;
        } catch (Exception e) {
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
