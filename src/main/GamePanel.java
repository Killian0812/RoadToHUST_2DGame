package main;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import entity.Entity;
import entity.Player;
import object.SuperObject;

import tile.TileManager;

public class GamePanel extends JPanel implements Runnable {

    /// FPS
    int FPS = 60;

    /// SCREEN SETTINGS
    public final int tileSize = 16 * 3;

    public final int maxScreenCol = 16;
    public final int maxScreenRow = 12;

    public final int screenWidth = maxScreenCol * tileSize;
    public final int screenHeight = maxScreenRow * tileSize;

    /// WORLD SETTINGS
    public final int maxWorldCol = 113;
    public final int maxWorldRow = 56;
    public final int worldWidth = maxWorldCol * tileSize;
    public final int worldHeight = maxWorldRow * tileSize;

    /// CORE
    public KeyHandler keyH = new KeyHandler(this);
    public TileManager tileM = new TileManager(this);
    public CollisionChecker cChecker = new CollisionChecker(this);
    public AssetSetter aSetter = new AssetSetter(this);
    Sound music = new Sound();
    Sound se = new Sound();
    public UI ui = new UI(this);

    /// ENTITY AND OBJECT
    public SuperObject obj[] = new SuperObject[10];
    public Player player = new Player(this, keyH);
    public int playerGender = 0;
    public Entity npc[] = new Entity[10];

    /// GAME STATE
    public int gameState;
    public final int titleState = 0;
    public final int playState = 1;
    public final int pauseState = 2;
    public final int dialogueState = 3;
    public final int gameOverState = 4;
    public int tCount = 0;

    Thread gameThread;

    public GamePanel() {

        this.setPreferredSize(new Dimension(screenWidth, screenHeight));
        this.setBackground(Color.black);
        this.setDoubleBuffered(true);
        this.addKeyListener(keyH);
        this.setFocusable(true);

    }

    public void setupGame() {

        aSetter.setObj();
        aSetter.setNPC();
        ui.isDead = false;
        player.isDead = 0;
        ui.gameFinished = false;
        // playMusic(0);
        gameState = titleState;

    }

    public void startGameThread() {
        gameThread = new Thread(this);
        gameThread.start();
    }

    @Override
    public void run() {

        double drawInterval = 1e9 / FPS;
        double delta = 0;
        long lastTime = System.nanoTime();
        long currentTime;

        while (gameThread != null) {

            currentTime = System.nanoTime();
            delta += (currentTime - lastTime) / drawInterval;
            lastTime = currentTime;
            if (delta >= 1) {
                update();
                repaint();
                delta--;
            }
        }
    }

    public void update() {

        if (gameState == playState) {

            if (ui.gameFinished == false)
                player.update();

            for (int i = 0; i < npc.length; i++) {
                if (npc[i] != null) {
                    if (i == 4 && player.hasID == true) {
                        String text44[] = { "Nhanh đi học đi" };
                        npc[i].setDialogue(text44, text44);
                        npc[i].dialogueIndex = 0;
                    }
                    npc[i].update();
                    if (i == 7 && npc[i].hitPlayer == true) {
                        if (npc[i].direction == "left") {
                            player.worldX -= tileSize / 2;
                            player.isDead = 1;
                        }
                        if (npc[i].direction == "right") {
                            player.worldX += tileSize / 2;
                            player.isDead = 2;
                        }
                        gameState = gameOverState;
                        ui.isDead = true;
                        playSE(6);
                    }
                }
            }
            if (gameState == pauseState) {

            }
        }
    }

    public void paintComponent(Graphics g) {

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        tCount++;

        if (gameState == titleState) {
            ui.draw(g2);
        } else {

            /// TILE
            tileM.draw(g2);

            /// OBJECT
            for (int i = 0; i < obj.length; i++) {
                if (obj[i] == null)
                    continue;
                obj[i].draw(g2, this);
            }

            /// NPC
            for (int i = 0; i < npc.length; i++) {
                if (npc[i] == null)
                    continue;
                npc[i].draw(g2);
            }

            /// PLAYER
            player.draw(g2);

            /// UI
            ui.draw(g2);
        }

        g2.dispose();
    }

    public void playMusic(int i) {
        music.setFile(i);
        music.play();
        music.loop();
    }

    public void stopMusic() {
        music.stop();
    }

    public void playSE(int i) {
        se.setFile(i);
        se.play();
    }
}