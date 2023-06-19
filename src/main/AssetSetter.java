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
        gp.obj[2].worldX = gp.tileSize * 82;
        gp.obj[2].worldY = gp.tileSize * 44;

        gp.obj[3] = new OBJ_Backpack(gp);
        gp.obj[3].worldX = gp.tileSize * 65;
        gp.obj[3].worldY = gp.tileSize * 8;

        gp.obj[4] = new OBJ_Book(gp);
        gp.obj[4].worldX = gp.tileSize * 62;
        gp.obj[4].worldY = gp.tileSize * 8;

        gp.obj[5] = new OBJ_Pencil(gp);
        gp.obj[5].worldX = gp.tileSize * 59;
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

        String text0[] = { "Bây giờ mới dậy à cháu?", "Tiền nhà cháu nợ tính đến tháng /n này là 15 triệu rồi",
                "Thu xếp trả tiền cho chú", "Không thanh toán trong tuần này /n là mày ra khỏi nhà nhá!" };
        String text00[] = { "Em gái dậy rồi à!", "Thu xếp trả tiền nhà cho anh nhá" };
        gp.npc[0] = new NPC_Landlord(gp);
        gp.npc[0].worldX = gp.tileSize * 88;
        gp.npc[0].worldY = gp.tileSize * 17;
        gp.npc[0].setDialogue(text0, text00);

        String text1[] = { "Hôm nay OOP không điểm danh đâu", "Đi net không cu?" };
        String text11[] = { "Uây cậu xinh thế!", "Cho tớ xin info được không?" };
        gp.npc[1] = new NPC_HustBoy(gp);
        gp.npc[1].direction = "up";
        gp.npc[1].worldX = gp.tileSize * 58;
        gp.npc[1].worldY = gp.tileSize * 8;
        gp.npc[1].setDialogue(text1, text11);

        String text2[] = { "Mày làm bài tập Giải Tích 3 chưa?", "Thế đã code xong bài OOP chưa?",
                "Thôi đi học làm gì" };
        String text22[] = { "HUST cũng có sắn ngon như này á?", "Cậu có người yêu chưa?" };
        gp.npc[2] = new NPC_HustBoy(gp);
        gp.npc[2].direction = "left";
        gp.npc[2].worldX = gp.tileSize * 53;
        gp.npc[2].worldY = gp.tileSize * 8;
        gp.npc[2].setDialogue(text2, text22);

        String text3[] = { "Tớ có người yêu rồi!" };
        String text33[] = { "Ui cậu dễ thương thế!" };
        gp.npc[3] = new NPC_HustGirl(gp);
        gp.npc[3].worldX = gp.tileSize * 36;
        gp.npc[3].worldY = gp.tileSize * 8;
        gp.npc[3].setDialogue(text3, text33);

        String text4[] = { "Cậu bị rơi thẻ sinh viên à?",
                "Hình như có một cái rơi ở hồ nước /n Cậu ra đó thử xem" };
        gp.npc[4] = new NPC_HustBoy(gp);
        gp.npc[4].worldX = gp.tileSize * 25;
        gp.npc[4].worldY = gp.tileSize * 29;
        gp.npc[4].name = "Guider1";
        // gp.npc[4] = new NPC_HustGirl(gp);
        // gp.npc[4].worldX = gp.tileSize * 25;
        // gp.npc[4].worldY = gp.tileSize * 29;
        gp.npc[4].setDialogue(text4, text4);

        String text5[] = { "Cậu đi theo tôi đấy à?", "Tránh xa tôi ra!" };
        String text55[] = { "Mày đấy à?", "Hôm nay makeup xinh dữ ha" };
        gp.npc[5] = new NPC_HustGirl(gp);
        gp.npc[5].worldX = gp.tileSize * 63;
        gp.npc[5].worldY = gp.tileSize * 26;
        gp.npc[5].setDialogue(text5, text55);

        String text6[] = { "Mày như nào đấy? /n Biết bố mày là ai không?", "Cút ra chỗ khác!" };
        String text66[] = { "Cậu đang đi đâu đấy?", "Thôi đi học làm gì /n đi chơi với tớ đi" };
        gp.npc[6] = new NPC_HustBoy(gp);
        gp.npc[6].direction = "left";
        gp.npc[6].worldX = gp.tileSize * 64;
        gp.npc[6].worldY = gp.tileSize * 25;
        gp.npc[6].setDialogue(text6, text66);

        gp.npc[7] = new NPC_Car(gp);
        gp.npc[7].direction = "left";
        gp.npc[7].worldX = gp.tileSize * 72;
        gp.npc[7].worldY = gp.tileSize * 22;
        gp.npc[7].name = "Car";

        gp.npc[8] = new NPC_NinjaLead(gp);
        gp.npc[8].direction = "right";
        gp.npc[8].worldX = gp.tileSize * 72;
        gp.npc[8].worldY = gp.tileSize * 24;
        gp.npc[8].name = "NinjaLead";

    }

    public void setMonster() {

        gp.monster[0] = new MON_Slime(gp);
        gp.monster[0].worldX = gp.tileSize * 52;
        gp.monster[0].worldY = gp.tileSize * 10;
        gp.monster[0].direction = "left";

        gp.monster[1] = new MON_Orc(gp);
        gp.monster[1].worldX = gp.tileSize * 55;
        gp.monster[1].worldY = gp.tileSize * 10;
        gp.monster[1].direction = "left";

        gp.monster[2] = new MON_Orc(gp);
        gp.monster[2].worldX = gp.tileSize * 58;
        gp.monster[2].worldY = gp.tileSize * 10;
        gp.monster[2].direction = "right";

        gp.monster[3] = new MON_Orc(gp);
        gp.monster[3].worldX = gp.tileSize * 61;
        gp.monster[3].worldY = gp.tileSize * 10;
        gp.monster[3].direction = "right";
    }
}
