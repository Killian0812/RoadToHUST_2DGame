package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

import object.*;

public class UI {

    double playTime;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    Graphics2D g2;
    GamePanel gp;

    Font arial_25, arial_80B, cambria_80;

    BufferedImage keyImage, studentIDImage, bookImage, pencilImage;
    public boolean messageOn = false;
    public String message = "";
    int msgCount = 0;

    public boolean gameFinished = false;

    public String currentDialogue = "";

    public int commandNum = 0;
    public int titleScreenState = 0;

    public UI(GamePanel gp) {
        this.gp = gp;
        arial_25 = new Font("Arial", Font.PLAIN, 25);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        cambria_80 = new Font("Cambria", Font.BOLD, 80);
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

        this.g2 = g2;

        if (gp.gameState == gp.playState) {
            if (gameFinished == true) {

                commandNum = 0;
                g2.setFont(arial_25);
                g2.setFont(g2.getFont().deriveFont(40f));
                g2.setColor(Color.white);
                String text = "You are at HUST!";
                int txtLength = (int) g2.getFontMetrics().getStringBounds(text,
                        g2).getWidth();
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

        if (gp.gameState == gp.pauseState) {
            drawPauseScreen();
        }

        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        if (gp.gameState == gp.dialogueState) {
            drawDialogue();
        }
    }

    public void drawDialogue() {

        int x = gp.tileSize * 2;
        int y = gp.tileSize / 2;
        int width = gp.screenWidth - gp.tileSize * 4;
        int height = gp.tileSize * 4;
        drawSubWindow(x, y, width, height);

        g2.setFont(cambria_80);
        g2.setFont(g2.getFont().deriveFont(30f));
        x += gp.tileSize;
        y += gp.tileSize;
        for (String line : currentDialogue.split("/n")) {
            g2.drawString(line, x, y);
            y += 40;
        }
    }

    public void drawSubWindow(int x, int y, int width, int height) {
        g2.setColor(new Color(0, 0, 0, 200));
        g2.fillRoundRect(x, y, width, height, 35, 35);
        g2.setColor(Color.white);
        g2.setStroke(new BasicStroke(5));
        g2.drawRoundRect(x, y, width, height, 35, 35);
    }

    public void drawTitleScreen() {

        g2.setColor(Color.white);
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        try {
            File f = new File("./res/title/title.png");
            BufferedImage image = null;
            image = ImageIO.read(f);
            g2.drawImage(image, 0, 0, gp.screenWidth, gp.screenHeight / 2 - 50, null);
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            File f00 = new File("./res/player/hust_boy/hust_boy_down_1.png");
            BufferedImage image00 = ImageIO.read(f00);

            File f01 = new File("./res/player/hust_boy/hust_boy_down_2.png");
            BufferedImage image01 = ImageIO.read(f01);

            File f10 = new File("./res/player/hust_girl/hust_girl_down_1.png");
            BufferedImage image10 = ImageIO.read(f10);

            File f11 = new File("./res/player/hust_girl/hust_girl_down_2.png");
            BufferedImage image11 = ImageIO.read(f11);

            if (gp.tCount <= 20) {
                g2.drawImage(image00, 30, 300, gp.tileSize * 4, gp.tileSize * 4, null);
                g2.drawImage(image10, 100, 350, gp.tileSize * 4, gp.tileSize * 4, null);
            } else {
                g2.drawImage(image01, 30, 300, gp.tileSize * 4, gp.tileSize * 4, null);
                g2.drawImage(image11, 100, 350, gp.tileSize * 4, gp.tileSize * 4, null);
                if (gp.tCount == 40)
                    gp.tCount = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        g2.setFont(cambria_80);
        g2.setColor(Color.red);
        g2.setFont(g2.getFont().deriveFont(48f));

        String text;
        int x, y;

        if (titleScreenState == 0) {

            text = "NEW GAME";
            x = gp.screenWidth / 2;
            y = gp.screenHeight / 2 + gp.tileSize;
            g2.drawString(text, x, y);

            if (commandNum == 0) {
                g2.drawImage(gp.player.right1, x - gp.tileSize - 10, y - gp.tileSize + 10, gp.tileSize,
                        gp.tileSize - 10,
                        null);
            }

            text = "INSTRUCTIONS";
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawImage(gp.player.right1, x - gp.tileSize - 10, y - gp.tileSize + 10, gp.tileSize,
                        gp.tileSize - 10,
                        null);
            }

            text = "QUIT";
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawImage(gp.player.right1, x - gp.tileSize - 10, y - gp.tileSize + 10, gp.tileSize,
                        gp.tileSize - 10,
                        null);
            }
        } else if (titleScreenState == 1) {

            g2.setFont(cambria_80);
            g2.setFont(g2.getFont().deriveFont(30f));
            text = "CHOOSE YOUR GENDER: ";
            x = gp.screenWidth / 2 - gp.tileSize / 2;
            y = gp.screenHeight / 2 + gp.tileSize;
            g2.drawString(text, x, y);

            text = "MALE";
            x = gp.screenWidth / 2 + gp.tileSize;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawImage(gp.player.right1, x - gp.tileSize - 10, y - gp.tileSize + 15, gp.tileSize - 10,
                        gp.tileSize - 15, null);
            }

            text = "FEMALE";
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawImage(gp.player.right1, x - gp.tileSize - 10, y - gp.tileSize + 15, gp.tileSize - 10,
                        gp.tileSize - 15, null);
            }

            text = "BACK";
            x = gp.screenWidth / 2 + gp.tileSize / 2;
            y += gp.tileSize;
            g2.drawString(text, x, y);
            if (commandNum == 2) {
                g2.drawImage(gp.player.right1, x - gp.tileSize - 10, y - gp.tileSize + 15, gp.tileSize - 10,
                        gp.tileSize - 15, null);
            }
        } else if (titleScreenState == 2) {

            g2.setFont(cambria_80);
            g2.setFont(g2.getFont().deriveFont(35f));
            text = "WASD - Movement";
            x = gp.screenWidth / 2;
            y = gp.screenHeight / 2 + gp.tileSize;
            g2.drawString(text, x, y);
            text = "ENTER - Interact";
            y += gp.tileSize;
            g2.drawString(text, x, y);

            text = "BACK";
            x = gp.screenWidth / 2 + gp.tileSize;
            y += gp.tileSize * 2;
            g2.drawString(text, x, y);
            g2.drawImage(gp.player.right1, x - gp.tileSize - 10, y - gp.tileSize + 20, gp.tileSize - 10,
                    gp.tileSize - 15, null);
        }
    }

    public void drawPauseScreen() {

        g2.setFont(arial_80B);
        g2.setColor(Color.white);
        String text = "PAUSED";
        int x = getXforCenteredText(text);
        int y = gp.screenHeight / 2 - 50;
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(40f));
        g2.setColor(Color.white);

        text = "CONTINUE";
        x = getXforCenteredText(text);
        y = gp.screenHeight / 2;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize - 5, y);
        }
        text = "QUIT";
        x = getXforCenteredText(text);
        y = gp.screenHeight / 2 + gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize - 5, y);
        }
    }

    public int getXforCenteredText(String text) {
        int txtLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - txtLength / 2;
    }
}
