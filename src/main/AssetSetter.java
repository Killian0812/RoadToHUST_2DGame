package main;

import entity.*;
import object.*;

public class AssetSetter {

    GamePanel gp;

    public int objNum = 20;

    public AssetSetter(GamePanel gp) {
        this.gp = gp;
    }

    public void setObj() {  

        // NET
        gp.obj[0] = new OBJ_NetDoor(gp);
        gp.obj[0].worldX = gp.tileSize * 26;
        gp.obj[0].worldY = gp.tileSize * 37;

        gp.obj[1] = new OBJ_StudentID(gp);
        gp.obj[1].worldX = gp.tileSize * 29;
        gp.obj[1].worldY = gp.tileSize * 13;

        // CAFE
        gp.obj[11] = new OBJ_CafeDoor(gp);
        gp.obj[11].worldX = gp.tileSize * 173;
        gp.obj[11].worldY = gp.tileSize * 32;

        gp.obj[12] = new OBJ_Coffee(gp);
        gp.obj[12].worldX = gp.tileSize * 188;
        gp.obj[12].worldY = gp.tileSize * 14;

        gp.obj[13] = new OBJ_Coffee(gp);
        gp.obj[13].worldX = gp.tileSize * 188;
        gp.obj[13].worldY = gp.tileSize * 15;

        gp.obj[14] = new OBJ_Coffee(gp);
        gp.obj[14].worldX = gp.tileSize * 189;
        gp.obj[14].worldY = gp.tileSize * 17;

        // LIB
        gp.obj[21] = new OBJ_LibDoor(gp);
        gp.obj[21].worldX = gp.tileSize * 132;
        gp.obj[21].worldY = gp.tileSize * 62;

        // HOUSE
        gp.obj[31] = new OBJ_HouseDoor(gp);
        gp.obj[31].worldX = gp.tileSize * 100;
        gp.obj[31].worldY = gp.tileSize * 33;

        gp.obj[32] = new OBJ_Key(gp);
        gp.obj[32].worldX = gp.tileSize * 100;
        gp.obj[32].worldY = gp.tileSize * 14;

        gp.obj[33] = new OBJ_Key(gp);
        gp.obj[33].worldX = gp.tileSize * 90;
        gp.obj[33].worldY = gp.tileSize * 30;

        gp.obj[34] = new OBJ_BathroomDoor(gp);
        gp.obj[34].worldX = gp.tileSize * 80;
        gp.obj[34].worldY = gp.tileSize * 25;

        gp.obj[35] = new OBJ_HouseDoor(gp);
        gp.obj[35].worldX = gp.tileSize * 75;
        gp.obj[35].worldY = gp.tileSize * 25;

        gp.obj[36] = new OBJ_Shoes(gp, 1);
        gp.obj[36].worldX = gp.tileSize * 67;
        gp.obj[36].worldY = gp.tileSize * 28;

        gp.obj[37] = new OBJ_Shoes(gp, 2);
        gp.obj[37].worldX = gp.tileSize * 67;
        gp.obj[37].worldY = gp.tileSize * 30;

        gp.obj[38] = new OBJ_Shoes(gp, 3);
        gp.obj[38].worldX = gp.tileSize * 67;
        gp.obj[38].worldY = gp.tileSize * 32;

        gp.obj[39] = new OBJ_Bed(gp);
        gp.obj[39].worldX = gp.tileSize * 66;
        gp.obj[39].worldY = gp.tileSize * 12;

        gp.obj[40] = new OBJ_Backpack(gp);
        gp.obj[40].worldX = gp.tileSize * 78;
        gp.obj[40].worldY = gp.tileSize * 13;

        gp.obj[41] = new OBJ_Pencil(gp);
        gp.obj[41].worldX = gp.tileSize * 73;
        gp.obj[41].worldY = gp.tileSize * 13;

        // TRASH

        gp.obj[42] = new OBJ_Trashbag(gp);
        gp.obj[42].worldX = gp.tileSize * 103;
        gp.obj[42].worldY = gp.tileSize * 35;

        gp.obj[43] = new OBJ_Trashbag(gp);
        gp.obj[43].worldX = gp.tileSize * 104;
        gp.obj[43].worldY = gp.tileSize * 35;

        gp.obj[44] = new OBJ_Trashbag(gp);
        gp.obj[44].worldX = gp.tileSize * 35;
        gp.obj[44].worldY = gp.tileSize * 37;

        gp.obj[45] = new OBJ_Trashbag(gp);
        gp.obj[45].worldX = gp.tileSize * 35;
        gp.obj[45].worldY = gp.tileSize * 38;

        gp.obj[46] = new OBJ_Trashbag(gp);
        gp.obj[46].worldX = gp.tileSize * 105;
        gp.obj[46].worldY = gp.tileSize * 60;

        gp.obj[47] = new OBJ_Trashcan(gp);
        gp.obj[47].worldX = gp.tileSize * 103;
        gp.obj[47].worldY = gp.tileSize * 60;
    }

    public void setNPC() {

        // NET
        gp.npc[0] = new NPC_Guest2(gp);
        gp.npc[0].worldX = gp.tileSize * 31;
        gp.npc[0].worldY = gp.tileSize * 33;

        gp.npc[1] = new NPC_Car(gp);
        gp.npc[1].direction = "right";
        gp.npc[1].worldX = gp.tileSize * 6;
        gp.npc[1].worldY = gp.tileSize * 47;

        gp.npc[2] = new NPC_NinjaLead(gp);
        gp.npc[2].direction = "right";
        gp.npc[2].worldX = gp.tileSize * 98;
        gp.npc[2].worldY = gp.tileSize * 51;

        gp.npc[3] = new NPC_Car(gp);
        gp.npc[3].direction = "left";
        gp.npc[3].worldX = gp.tileSize * 41;
        gp.npc[3].worldY = gp.tileSize * 49;

        gp.npc[4] = new NPC_NinjaLead(gp);
        gp.npc[4].direction = "left";
        gp.npc[4].worldX = gp.tileSize * 95;
        gp.npc[4].worldY = gp.tileSize * 52;

        // CAFE
        gp.npc[11] = new NPC_Guest1(gp);
        gp.npc[11].worldX = gp.tileSize * 188;
        gp.npc[11].worldY = gp.tileSize * 19;
        gp.npc[11].direction = "up";

        gp.npc[12] = new NPC_Guest2(gp);
        gp.npc[12].worldX = gp.tileSize * 188;
        gp.npc[12].worldY = gp.tileSize * 20;
        gp.npc[12].direction = "up";

        gp.npc[13] = new NPC_Guest3(gp);
        gp.npc[13].worldX = gp.tileSize * 187;
        gp.npc[13].worldY = gp.tileSize * 21;
        gp.npc[13].direction = "up";

        String text14[] = { "Anh đang bận tí, vào bê cafe giúp anh với!" };
        gp.npc[14] = new NPC_PeeingGuy(gp);
        gp.npc[14].worldX = gp.tileSize * 183;
        gp.npc[14].worldY = gp.tileSize * 36;
        gp.npc[14].setDialogue(text14, text14);

        // LIB
        gp.npc[22] = new NPC_BreadSeller(gp);
        gp.npc[22].worldX = gp.tileSize * 161;
        gp.npc[22].worldY = gp.tileSize * 48;

        gp.npc[23] = new NPC_BookSeller(gp);
        gp.npc[23].worldX = gp.tileSize * 133;
        gp.npc[23].worldY = gp.tileSize * 66;

        // HOUSE
        String text31[] = { "Mày nhìn cái gì?" };
        gp.npc[31] = new NPC_ToiletBoy(gp);
        gp.npc[31].worldX = gp.tileSize * 79;
        gp.npc[31].worldY = gp.tileSize * 31;
        gp.npc[31].setDialogue(text31, text31);

        String text32[] = { "Mày làm bài tập Giải Tích 3 chưa?", "Thế đã code xong bài OOP chưa?",
                "Thôi đi học làm gì" };
        gp.npc[32] = new NPC_HustBoy(gp);
        gp.npc[32].direction = "left";
        gp.npc[32].worldX = gp.tileSize * 81;
        gp.npc[32].worldY = gp.tileSize * 14;
        gp.npc[32].setDialogue(text32, text32);

        String text33[] = { "Hôm nay OOP không điểm danh đâu", "Đi net đê?" };
        gp.npc[33] = new NPC_HustBoy(gp);
        gp.npc[33].direction = "up";
        gp.npc[33].worldX = gp.tileSize * 93;
        gp.npc[33].worldY = gp.tileSize * 16;
        gp.npc[33].setDialogue(text33, text33);

        String text34[] = { "Bây giờ mới dậy à cháu?", "Tiền nhà cháu nợ đến giờ là 15 triệu rồi",
                "Thu xếp trả tiền cho chú", "Không thì tháng sau ra khỏi nhà này nhá" };
        String text34_[] = { "Em gái dậy rồi à!", "Thu xếp trả tiền nhà cho anh nhá"
        };
        gp.npc[34] = new NPC_Landlord(gp);
        gp.npc[34].worldX = gp.tileSize * 101;
        gp.npc[34].worldY = gp.tileSize * 31;
        gp.npc[34].setDialogue(text34, text34_);

        // SCHOOL
        String text41[] = { "Không có giáo trình OOP à?",
                "Mua cho tao cái bánh mì đi tao cho mượn" };
        gp.npc[41] = new NPC_HustBoy(gp);
        gp.npc[41].worldX = gp.tileSize * 17;
        gp.npc[41].worldY = gp.tileSize * 68;
        gp.npc[41].setDialogue(text41, text41);
        gp.npc[41].name = "Helper";
        gp.npc[41].isMoving = false;

        String text42[] = { "Cậu đi theo tôi đấy à?", "Tránh xa tôi ra!" };
        String text42_[] = { "Mày đấy à?", "Hôm nay makeup xinh dữ ha" };
        gp.npc[42] = new NPC_HustGirl(gp);
        gp.npc[42].worldX = gp.tileSize * 18;
        gp.npc[42].worldY = gp.tileSize * 82;
        gp.npc[42].setDialogue(text42, text42_);

        String text43[] = { "Mày như nào đấy? /nBiết bố mày là ai không?", "Cút ra chỗ khác!" };
        String text43_[] = { "Cậu đang đi đâu đấy?", "Thôi đi học làm gì /nđi chơi với tớ đi" };
        gp.npc[43] = new NPC_HustBoy(gp);
        gp.npc[43].worldX = gp.tileSize * 32;
        gp.npc[43].worldY = gp.tileSize * 72;
        gp.npc[43].setDialogue(text43, text43_);
    }

    public void setAggroNPC() {
        String text0[] = { "Đi đứng cẩn thận vào!", "Mày thích như nào đấy?" };
        gp.aggroNPC[0] = new MON_Gangster(gp);
        gp.aggroNPC[0].direction = "up";
        gp.aggroNPC[0].worldX = gp.tileSize * 163;
        gp.aggroNPC[0].worldY = gp.tileSize * 78;
        gp.aggroNPC[0].setDialogue(text0, text0);
        gp.aggroNPC[0].isCarrying = true;
        gp.aggroNPC[0].objCarry = "Money";

        gp.aggroNPC[1] = new MON_Gangster(gp);
        gp.aggroNPC[1].direction = "down";
        gp.aggroNPC[1].worldX = gp.tileSize * 141;
        gp.aggroNPC[1].worldY = gp.tileSize * 18;
        gp.aggroNPC[1].setDialogue(text0, text0);
    }

    public void setMonster() {

        // System.out.println("Monsters generated");
        gp.monster[0] = new MON_Slime(gp, 0);
        gp.monster[0].worldX = gp.tileSize * 32;
        gp.monster[0].worldY = gp.tileSize * 116;
        gp.monster[0].direction = "left";
        gp.monster[0].isCarrying = true;
        gp.monster[0].objCarry = "Heart";

        gp.monster[1] = new MON_Orc(gp);
        gp.monster[1].worldX = gp.tileSize * 30;
        gp.monster[1].worldY = gp.tileSize * 110;
        gp.monster[1].direction = "left";

        gp.monster[2] = new MON_Orc(gp);
        gp.monster[2].worldX = gp.tileSize * 26;
        gp.monster[2].worldY = gp.tileSize * 113;
        gp.monster[2].direction = "right";

        gp.monster[3] = new MON_Slime(gp, 1);
        gp.monster[3].worldX = gp.tileSize * 18;
        gp.monster[3].worldY = gp.tileSize * 113;
        gp.monster[3].direction = "left";

    }
}
