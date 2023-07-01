package tile;

import java.awt.Graphics2D;
import java.io.InputStream;
import java.util.Scanner;

import javax.imageio.ImageIO;

import main.GamePanel;
import main.UtilityTool;
import object.OBJ_Trashcan;

public class TileManager {

    GamePanel gp;
    public Tile[] tile;
    public int mapTileNum[][][];

    public TileManager(GamePanel gp) {

        this.gp = gp;
        tile = new Tile[200];
        mapTileNum = new int[2][gp.maxWorldRow][gp.maxWorldCol];

        try {
            getTileImage();
        } catch (Exception e) {
            System.out.println("Can not get tile image!");
        }

        try {
            loadMap(0, "/maps/world01.txt");
            loadMap(1, "/maps/world01_2.txt");
        } catch (Exception e) {
            System.out.println("Can not load map data!");
        }
    }

    public void loadMap(int id, String filePath) {
        try {
            InputStream is = OBJ_Trashcan.class.getResourceAsStream(filePath);
            Scanner sc = new Scanner(is);
            int i = 0, j;
            while (sc.hasNextLine() && i < gp.maxWorldRow) {
                String line = sc.nextLine();
                // System.out.println(line);
                String numbers[] = line.split(" ");
                for (j = 0; j < gp.maxWorldCol; j++) {
                    mapTileNum[id][i][j] = Integer.parseInt(numbers[j]);
                }
                i++;

            }
            sc.close();

        } catch (Exception e) {
            e.getStackTrace();
        }
    }

    public void getTileImage() {

        setup(0, "blank", false, false);
        setup(1, "caffetile", false, false);
        setup(2, "cfchair1", true, false);
        setup(3, "cfchair2", true, false);
        setup(4, "cflight", true, false);
        setup(5, "crosspathdown", false, false);
        setup(6, "crosspathdownleft", false, false);
        setup(7, "crosspathright", false, false);
        setup(8, "crosspathrightup", false, false);
        setup(9, "grass new", false, false);
        setup(10, "h", false, true);
        setup(11, "librarywall", true, false);
        setup(12, "libtile", false, false);
        setup(13, "midcolroad", false, false);
        setup(14, "midrowroad", false, false);
        setup(15, "path", false, false);
        setup(16, "s", false, true);
        setup(17, "t new", false, true);
        setup(18, "tree new", true, false);
        setup(19, "tree", true, false);
        setup(20, "u new", false, true);
        setup(21, "viahe", false, false);
        setup(22, "wall new", true, false);
        setup(23, "wall", true, false);
        setup(24, "water new 2", true, false);
        setup(25, "water new 3", true, false);
        setup(26, "water new 4", true, false);
        setup(27, "water new 5", true, false);
        setup(28, "water new 6", true, false);
        setup(29, "water new 7", true, false);
        setup(30, "water new 8", true, false);
        setup(31, "water new 9", true, false);
        setup(32, "water new 10", true, false);
        setup(33, "wood new", true, false);
        setup(34, "wood", false, false);
        setup(35, "blue", true, false);
        setup(36, "brown", true, false);
        setup(37, "cast", true, false);
        setup(38, "red", true, false);
        setup(39, "white", true, false);
        setup(40, "superred", true, false);
        setup(41, "libchair", true, false);
        setup(42, "netfloornew/netfloor", false, false);
        setup(43, "netwallnew/netwall", true, false);
        setup(44, "bin", true, false);
        setup(45, "computerplayerfront/computerplayer1", true, false);
        setup(46, "computerplayerfront/computerplayer2", true, false);
        setup(47, "computerplayerfront/computerplayer3", true, false);
        setup(48, "computerplayerfront/computerplayer4", true, false);
        setup(49, "computerplayerfront/computerplayer5", true, false);
        setup(50, "computerplayerfront/computerplayer6", true, false);
        setup(51, "computerplayerleft/computerplayerleft1", true, false);
        setup(52, "computerplayerleft/computerplayerleft2", true, false);
        setup(53, "computerplayerleft/computerplayerleft3", true, false);
        setup(54, "computerplayerleft/computerplayerleft4", true, false);
        setup(55, "computerplayerleft/computerplayerleft5", true, false);
        setup(56, "computerplayerleft/computerplayerleft6", true, false);
        setup(57, "computerplayerright/computerplayerright1", true, false);
        setup(58, "computerplayerright/computerplayerright2", true, false);
        setup(59, "computerplayerright/computerplayerright3", true, false);
        setup(60, "computerplayerright/computerplayerright4", true, false);
        setup(61, "computerplayerright/computerplayerright5", true, false);
        setup(62, "computerplayerright/computerplayerright6", true, false);
        setup(63, "bookstoresign/bookstoresign1", true, false);
        setup(64, "bookstoresign/bookstoresign2", true, false);
        setup(65, "coffeesign/coffeesign1", true, false);
        setup(66, "coffeesign/coffeesign2", true, false);
        setup(67, "netsign/netsign1", true, false);
        setup(68, "netsign/netsign2", true, false);
        setup(69, "vendingmachine/vendinmachine1", true, false);
        setup(70, "vendingmachine/vendinmachine2", true, false);
        setup(71, "vendingmachine/vendinmachine3", true, false);
        setup(72, "vendingmachine/vendinmachine4", true, false);
        setup(73, "vendingmachine/vendinmachine5", true, false);
        setup(74, "vendingmachine/vendinmachine6", true, false);
        setup(75, "big tree new/big tree new-1.png", true, false);
        setup(76, "big tree new/big tree new-2", true, false);
        setup(77, "big tree new/big tree new-3", true, false);
        setup(78, "big tree new/big tree new-4", true, false);
        setup(79, "big tree new/big tree new-5", true, false);
        setup(80, "big tree new/big tree new-6", true, false);
        setup(81, "bookshelf/bookshelf-1", true, false);
        setup(82, "bookshelf/bookshelf-2", true, false);
        setup(83, "bookshelf/bookshelf-3", true, false);
        setup(84, "bookshelf/bookshelf-4", true, false);
        setup(85, "bookshelf/bookshelf-5", true, false);
        setup(86, "bookshelf/bookshelf-6", true, false);
        setup(87, "bookshelf/bookshelf-7", true, false);
        setup(88, "bookshelf/bookshelf-8", true, false);
        setup(89, "bookshelf/bookshelf-9", true, false);
        setup(90, "cashcounter/img-1", true, false);
        setup(91, "cashcounter/img-2", true, false);
        setup(92, "cashcounter/img-3", true, false);
        setup(93, "cashcounter/img-4", true, false);
        setup(94, "cashcounter/img-5", true, false);
        setup(95, "cashcounter/img-6", true, false);
        setup(96, "cashcounter/img-7", true, false);
        setup(97, "cashcounter/img-8", true, false);
        setup(98, "cashcounter/img-9", true, false);
        setup(99, "cfbar/img-1", true, false);
        setup(100, "cfbar/img-2", true, false);
        setup(101, "cfbar/img-3", true, false);
        setup(102, "cfbar/img-4", true, false);
        setup(103, "cfbar/img-5", true, false);
        setup(104, "cfbar/img-6", true, false);
        setup(105, "cfbar/img-7", true, false);
        setup(106, "cfbar/img-8", true, false);
        setup(107, "cfbar/img-9", true, false);
        setup(108, "cfbar/img-10", true, false);
        setup(109, "cfbar/img-11", true, false);
        setup(110, "cfbar/img-12", true, false);
        setup(111, "cfbep/img-1", true, false);
        setup(112, "cfbep/img-2", true, false);
        setup(113, "cfbep/img-3", true, false);
        setup(114, "cfbep/img-4", true, false);
        setup(115, "cftable/img-1", true, false);
        setup(116, "cftable/img-2", true, false);
        setup(117, "cftable/img-3", true, false);
        setup(118, "cftable/img-4", true, false);
        setup(119, "cftree/cftree-1", true, false);
        setup(120, "cftree/cftree-2", true, false);
        setup(121, "cfwindow/img-1", true, false);
        setup(122, "cfwindow/img-2", true, false);
        setup(123, "cfwindow/img-3", true, false);
        setup(124, "cfwindow/img-4", true, false);
        setup(125, "cua 1 canh/cua 1 canh 1", false, false);
        setup(126, "cua 1 canh/cua 1 canh 2", false, false);
        setup(127, "cua 2 canh/img-1", false, false);
        setup(128, "cua 2 canh/img-2", false, false);
        setup(129, "cua 2 canh/img-3", false, false);
        setup(130, "cua 2 canh/img-4", false, false);
        setup(131, "left_lib_door/left_lib_door-1", false, false);
        setup(132, "left_lib_door/left_lib_door-2", false, false);
        setup(133, "libtable/img-1", true, false);
        setup(134, "libtable/img-2", true, false);
        setup(135, "libtable/img-3", true, false);
        setup(136, "libtable/img-4", true, false);
        setup(137, "libtable/img-6", true, false);
        setup(138, "libtable/img-7", true, false);
        setup(139, "libtable/img-8", true, false);
        setup(140, "libtable/img-9", true, false);
        setup(141, "netdoor/netdoor1", false, false);
        setup(142, "netdoor/netdoor2", false, false);
        setup(143, "netdoor/netdoor3", false, false);
        setup(144, "netdoor/netdoor4", false, false);
        setup(145, "right_lib_door/right_lib_door-1", false, false);
        setup(146, "right_lib_door/right_lib_door-2", false, false);
        setup(147, "v", true, false);
        setup(148, "r", true, false);
        setup(149, "o", true, false);
        setup(150, "m", true, false);

    }

    public void setup(int index, String imagePath, boolean collision, boolean isFinishLine) {

        UtilityTool uTool = new UtilityTool();

        try {
            tile[index] = new Tile();
            InputStream is = TileManager.class.getResourceAsStream("/tiles/" + imagePath + ".png");
            tile[index].image = ImageIO.read(is);
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

            int tileType = mapTileNum[0][worldRow][worldCol];

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
                if (tileType != 0)
                    g2.drawImage(tile[tileType].image, screenX, screenY, gp.tileSize, gp.tileSize, null);

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }

        worldCol = 0;
        worldRow = 0;

        while (worldCol < gp.maxWorldCol && worldRow < gp.maxWorldRow) {

            int tileType = mapTileNum[1][worldRow][worldCol];

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
                if (tileType != 0)
                    g2.drawImage(tile[tileType].image, screenX, screenY, gp.tileSize,
                            gp.tileSize, null);

            worldCol++;

            if (worldCol == gp.maxWorldCol) {
                worldCol = 0;
                worldRow++;
            }
        }

    }
}
