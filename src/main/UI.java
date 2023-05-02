package main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.text.DecimalFormat;

import object.OBJ_Book;
import object.OBJ_Key;
import object.OBJ_Pencil;
import object.OBJ_StudentID;

public class UI {

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    GamePanel gp;
    Font arial_25, arial_80B;

    BufferedImage keyImage, studentIDImage, bookImage, pencilImage;
    public boolean messageOn = false;
    public String message = "";
    int msgCount = 0;
    public boolean gameFinished = false;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_25 = new Font("Arial", Font.PLAIN, 25);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;
        OBJ_StudentID studentID = new OBJ_StudentID(gp);
        studentIDImage = studentID.image;
        OBJ_Book book = new OBJ_Book(gp);
        bookImage = book.image;
        OBJ_Pencil pencil = new OBJ_Pencil(gp);
        pencilImage = pencil.image;
    }

    public void showMsg(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        if (gameFinished == true) {

            g2.setFont(arial_25);
            g2.setFont(g2.getFont().deriveFont(40f));
            g2.setColor(Color.white);
            String text = "You are at HUST!";
            int txtLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            int x = gp.screenWidth / 2 - txtLength / 2;
            int y = gp.screenHeight / 2 - gp.tileSize * 3;
            g2.drawString(text, x, y);

            text = "Your time is: " + dFormat.format(playTime);
            txtLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - txtLength / 2;
            y = gp.screenHeight / 2 + gp.tileSize * 4;
            g2.drawString(text, x, y);

            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);
            text = "Congratulations!";
            txtLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - txtLength / 2;
            y = gp.screenHeight / 2 + gp.tileSize * 2;
            g2.drawString(text, x, y);

            gp.stopMusic();
            gp.gameThread = null;
            return;
        }

        // display items
        g2.setFont(arial_25);
        g2.setColor(Color.white);
        g2.drawImage(keyImage, 20, 15, 30, 30, null);
        g2.drawString(" x " + gp.player.keyCount, 45, 40);

        if (gp.player.hasBook == true)
            g2.drawImage(bookImage, 20, 55, 30, 30, null);

        if (gp.player.hasPencil == true)
            g2.drawImage(pencilImage, 60, 55, 20, 30, null);

        if (gp.player.hasID == true)
            g2.drawImage(studentIDImage, 20, 90, 30, 30, null);

        // message
        if (messageOn == true) {

            g2.drawString(message, gp.tileSize / 2, gp.tileSize * 5);
            msgCount++;

            // disappear after 2s
            if (msgCount > 120) {
                msgCount = 0;
                messageOn = false;
            }
        }

        // playtime
        playTime += (double) 1 / 60;
        g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize * 12, 65);

    }
}
