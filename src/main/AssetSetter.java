package main;

import entity.*;
import object.*;

public class AssetSetter {

    GamePanel gp;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObj() {
        gp.obj[0] = new OBJ_Key(gp);
        gp.obj[0].worldX = gp.tileSize * 50;
        gp.obj[0].worldY = gp.tileSize * 18;

        gp.obj[1] = new OBJ_Key(gp);
        gp.obj[1].worldX = gp.tileSize * 91;
        gp.obj[1].worldY = gp.tileSize * 7;

        gp.obj[2] = new OBJ_StudentID(gp);
        gp.obj[2].worldX = gp.tileSize * 50;
        gp.obj[2].worldY = gp.tileSize * 11;

        gp.obj[3] = new OBJ_Backpack(gp);
        gp.obj[3].worldX = gp.tileSize * 59;
        gp.obj[3].worldY = gp.tileSize * 8;

        gp.obj[4] = new OBJ_Book(gp);
        gp.obj[4].worldX = gp.tileSize * 62;
        gp.obj[4].worldY = gp.tileSize * 8;

        gp.obj[5] = new OBJ_Pencil(gp);
        gp.obj[5].worldX = gp.tileSize * 65;
        gp.obj[5].worldY = gp.tileSize * 8;

        gp.obj[6] = new OBJ_Boots(gp);
        gp.obj[6].worldX = gp.tileSize * 61;
        gp.obj[6].worldY = gp.tileSize * 18;

        gp.obj[7] = new OBJ_Door(gp);
        gp.obj[7].worldX = gp.tileSize * 58;
        gp.obj[7].worldY = gp.tileSize * 17;

        gp.obj[8] = new OBJ_Door(gp);
        gp.obj[8].worldX = gp.tileSize * 97;
        gp.obj[8].worldY = gp.tileSize * 21;
    }

    public void setNPC() {
        gp.npc[0] = new NPC_OldMan(gp);
        gp.npc[0].worldX = gp.tileSize * 49;
        gp.npc[0].worldY = gp.tileSize * 8;

        gp.npc[1] = new NPC_HustBoy(gp);
        gp.npc[1].direction = "up";
        gp.npc[1].worldX = gp.tileSize * 58;
        gp.npc[1].worldY = gp.tileSize * 8;

        gp.npc[2] = new NPC_HustBoy(gp);
        gp.npc[2].direction = "left";
        gp.npc[2].worldX = gp.tileSize * 53;
        gp.npc[2].worldY = gp.tileSize * 8;

        gp.npc[3] = new NPC_HustGirl(gp);
        gp.npc[3].worldX = gp.tileSize * 36;
        gp.npc[3].worldY = gp.tileSize * 8;

        gp.npc[4] = new NPC_HustGirl(gp);
        gp.npc[4].worldX = gp.tileSize * 26;
        gp.npc[4].worldY = gp.tileSize * 30;
        
        gp.npc[5] = new NPC_HustGirl(gp);
        gp.npc[5].worldX = gp.tileSize * 63;
        gp.npc[5].worldY = gp.tileSize * 26;

        gp.npc[6] = new NPC_HustBoy(gp);
        gp.npc[6].direction = "left";
        gp.npc[6].worldX = gp.tileSize * 64;
        gp.npc[6].worldY = gp.tileSize * 25;
    }
}
