package main;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.text.DecimalFormat;

import javax.imageio.ImageIO;

import object.*;

public class UI {

    public double playTime;
    public double VRWorldCoolDown;
    DecimalFormat dFormat = new DecimalFormat("#0.00");

    Graphics2D g2;
    GamePanel gp;

    Font arial_25, arial_80B, cambria_80;
    Font joystixMonospace;

    BufferedImage keyImage, moneyImage;
    BufferedImage studentIDImage, bookImage, pencilImage;
    BufferedImage breadImage;
    BufferedImage heart_full, heart_half, heart_blank;
    public boolean messageOn = false;
    public String message = "";
    int msgCount = 0;

    public boolean gameFinished = false;
    public boolean isDead = false;

    public String currentDialogue = "";

    public int commandNum = 0;
    public int titleScreenState = 0;

    public UI(GamePanel gp) {
        this.gp = gp;

        arial_25 = new Font("Arial", Font.PLAIN, 25);
        arial_80B = new Font("Arial", Font.BOLD, 80);
        cambria_80 = new Font("Cambria", Font.BOLD, 80);
        try {
            File f = new File("./res/font/joystix monospace.otf");
            joystixMonospace = Font.createFont(Font.TRUETYPE_FONT, f);
        } catch (FontFormatException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
        // HUD
        OBJ_Money money = new OBJ_Money(gp);
        moneyImage = money.image;

        OBJ_Key key = new OBJ_Key(gp);
        keyImage = key.image;

        OBJ_StudentID studentID = new OBJ_StudentID(gp);
        studentIDImage = studentID.image;
        OBJ_Book book = new OBJ_Book(gp);
        bookImage = book.image;
        OBJ_Pencil pencil = new OBJ_Pencil(gp);
        pencilImage = pencil.image;

        OBJ_Bread bread = new OBJ_Bread(gp);
        breadImage = bread.image;

        SuperObject heart = new OBJ_Heart(gp);
        heart_full = heart.image;
        heart_half = heart.image2;
        heart_blank = heart.image3;

    }

    public void showMsg(String text) {
        message = text;
        messageOn = true;
    }

    public void draw(Graphics2D g2) {

        this.g2 = g2;

        if (gp.gameState == gp.playState) {
            drawPlayerLife();
            drawPlayScreen();
        }
        if (gp.gameState == gp.pauseState) {
            drawPlayerLife();
            drawPauseScreen();
        }

        if (gp.gameState == gp.titleState) {
            drawTitleScreen();
        }

        if (gp.gameState == gp.gameOverState) {
            drawPlayerLife();
            drawGameOverScreen();
        }

        if (gp.gameState == gp.dialogueState) {
            drawPlayerLife();
            drawDialogue();
        }
    }

    public void drawPlayScreen() {

        // display items
        g2.setFont(arial_25);
        g2.setColor(Color.green);
        g2.drawImage(moneyImage, 20, 15, 30, 30, null);
        g2.drawString(" x " + gp.player.moneyCount, 45, 38);

        g2.setFont(arial_25);
        g2.setColor(Color.white);
        g2.drawImage(keyImage, 20, 50, 30, 30, null);
        g2.drawString(" x " + gp.player.keyCount, 45, 75);

        if (gp.player.hasPencil == true)
            g2.drawImage(pencilImage, 20, 90, 20, 25, null);

        if (gp.player.hasBook == true)
            g2.drawImage(bookImage, 60, 85, 30, 30, null);

        if (gp.player.hasID == true)
            g2.drawImage(studentIDImage, 20, 120, 30, 30, null);

        if (gp.player.hasBread == true)
            g2.drawImage(breadImage, 20, 155, 30, 30, null);

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
        VRWorldCoolDown += (double) 1 / 60;
        g2.drawString("Time: " + dFormat.format(playTime), gp.tileSize * 16, 80);

    }

    public void drawPlayerLife() {

        // gp.player.life = 0;

        int x = 16 * gp.tileSize;
        int y = gp.tileSize - 32;
        int i = 0;
        while (i < gp.player.maxLife / 2) {
            g2.drawImage(heart_blank, null, x, y);
            i++;
            x += gp.tileSize - 16;
        }
        x = 16 * gp.tileSize;
        for (i = 1; i <= gp.player.life - 2; i += 2) {
            g2.drawImage(heart_full, null, x, y);
            x += gp.tileSize - 16;
        }
        if (gp.player.life % 2 == 1)
            g2.drawImage(heart_half, null, x, y);
        else if (gp.player.life > 0)
            g2.drawImage(heart_full, null, x, y);
    }

    public void drawDialogue() {

        if (currentDialogue == null)
            return;
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
                g2.drawImage(image00, 40, 400, gp.tileSize * 5, gp.tileSize * 5, null);
                g2.drawImage(image10, 140, 450, gp.tileSize * 5, gp.tileSize * 5, null);
            } else {
                g2.drawImage(image01, 40, 400, gp.tileSize * 5, gp.tileSize * 5, null);
                g2.drawImage(image11, 140, 450, gp.tileSize * 5, gp.tileSize * 5, null);
                if (gp.tCount == 40)
                    gp.tCount = 0;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        g2.setFont(joystixMonospace);
        g2.setColor(new Color(204,0,0));
        g2.setFont(g2.getFont().deriveFont(40f));

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

            g2.setFont(joystixMonospace);
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

            g2.setFont(joystixMonospace);
            g2.setFont(g2.getFont().deriveFont(35f));
            x = gp.screenWidth / 2;
            y = gp.screenHeight / 2;

            text = "WASD - Movement";
            g2.drawString(text, x, y);

            y += gp.tileSize;
            text = "ENTER - Interact";
            g2.drawString(text, x, y);

            y += gp.tileSize;
            text = "G - Drop item";
            g2.drawString(text, x, y);

            y += gp.tileSize;
            text = "SPACE - Attack";
            g2.drawString(text, x, y);

            y += gp.tileSize;
            text = "P - Pause";
            g2.drawString(text, x, y);

            y += gp.tileSize * 2;
            x = gp.screenWidth / 2 + gp.tileSize;
            text = "BACK";
            g2.drawString(text, x, y);
            g2.drawImage(gp.player.right1, x - gp.tileSize - 10, y - gp.tileSize + 20, gp.tileSize - 10,
                    gp.tileSize - 15, null);
        }
    }

    public void drawPauseScreen() {

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

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
        text = "QUIT TO MENU";
        x = getXforCenteredText(text);
        y = gp.screenHeight / 2 + gp.tileSize;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize - 5, y);
        }
    }

    public void drawGameOverScreen() {

        g2.setColor(new Color(0, 0, 0, 150));
        g2.fillRect(0, 0, gp.screenWidth, gp.screenHeight);

        String text;
        int x, y, txtLength;
        if (isDead == false) {
            g2.setFont(arial_25);
            g2.setFont(g2.getFont().deriveFont(40f));
            g2.setColor(Color.white);
            text = "You are at HUST!";
            txtLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - txtLength / 2;
            y = gp.screenHeight / 2 - gp.tileSize * 2;
            g2.drawString(text, x, y);

            text = "Your time is: " + dFormat.format(playTime);
            txtLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - txtLength / 2;
            y = gp.screenHeight / 2 - gp.tileSize;
            g2.drawString(text, x, y);

            g2.setFont(arial_80B);
            g2.setColor(Color.yellow);
            text = "Congratulations!";
            txtLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
            x = gp.screenWidth / 2 - txtLength / 2;
            y = gp.screenHeight / 2 - gp.tileSize * 3;
            g2.drawString(text, x, y);

            g2.setFont(g2.getFont().deriveFont(40f));
            g2.setColor(Color.white);

            text = "RESTART";
            x = getXforCenteredText(text);
            y = gp.screenHeight / 2 + gp.tileSize * 2;
            g2.drawString(text, x, y);
            if (commandNum == 0) {
                g2.drawString(">", x - gp.tileSize - 5, y);
            }
            text = "QUIT TO MENU";
            x = getXforCenteredText(text);
            y = gp.screenHeight / 2 + gp.tileSize * 3;
            g2.drawString(text, x, y);
            if (commandNum == 1) {
                g2.drawString(">", x - gp.tileSize - 5, y);
            }

            gp.stopMusic();
            // gp.gameThread = null;
            return;
        }

        g2.setFont(arial_80B);
        g2.setColor(Color.red);
        text = "You're dead!";
        txtLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        x = gp.screenWidth / 2 - txtLength / 2;
        y = gp.screenHeight / 2 - gp.tileSize * 3;
        g2.drawString(text, x, y);

        g2.setFont(g2.getFont().deriveFont(40f));
        g2.setColor(Color.white);

        text = "RESTART";
        x = getXforCenteredText(text);
        y = gp.screenHeight / 2 + gp.tileSize * 2;
        g2.drawString(text, x, y);
        if (commandNum == 0) {
            g2.drawString(">", x - gp.tileSize - 5, y);
        }
        text = "QUIT TO MENU";
        x = getXforCenteredText(text);
        y = gp.screenHeight / 2 + gp.tileSize * 3;
        g2.drawString(text, x, y);
        if (commandNum == 1) {
            g2.drawString(">", x - gp.tileSize - 5, y);
        }
        gp.stopMusic();
    }

    public int getXforCenteredText(String text) {
        int txtLength = (int) g2.getFontMetrics().getStringBounds(text, g2).getWidth();
        return gp.screenWidth / 2 - txtLength / 2;
    }
}