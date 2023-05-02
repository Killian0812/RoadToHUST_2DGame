package main;

import object.*;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObj() {
        gp.obj[0] = new OBJ_Key();
        gp.obj[0].worldX = gp.tileSize * 50;
        gp.obj[0].worldY = gp.tileSize * 18;

        gp.obj[1] = new OBJ_Key();
        gp.obj[1].worldX = gp.tileSize * 91;
        gp.obj[1].worldY = gp.tileSize * 7;
 
        gp.obj[2] = new OBJ_StudentID();
        gp.obj[2].worldX = gp.tileSize * 50;
        gp.obj[2].worldY = gp.tileSize * 11;

        gp.obj[3] = new OBJ_Backpack();
        gp.obj[3].worldX = gp.tileSize * 59;
        gp.obj[3].worldY = gp.tileSize * 8;

        gp.obj[4] = new OBJ_Book();
        gp.obj[4].worldX = gp.tileSize * 62;
        gp.obj[4].worldY = gp.tileSize * 8;

        gp.obj[5] = new OBJ_Pencil();
        gp.obj[5].worldX = gp.tileSize * 65;
        gp.obj[5].worldY = gp.tileSize * 8;

        gp.obj[6] = new OBJ_Boots();
        gp.obj[6].worldX = gp.tileSize * 61;
        gp.obj[6].worldY = gp.tileSize * 18;

        gp.obj[7] = new OBJ_Door();
        gp.obj[7].worldX = gp.tileSize * 58;
        gp.obj[7].worldY = gp.tileSize * 17;

        gp.obj[8] = new OBJ_Door();
        gp.obj[8].worldX = gp.tileSize * 97;
        gp.obj[8].worldY = gp.tileSize * 21;

        gp.obj[9] = new OBJ_Chest();
        gp.obj[9].worldX = gp.tileSize * 24;
        gp.obj[9].worldY = gp.tileSize * 48;
    }
}
