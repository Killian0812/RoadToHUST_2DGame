package main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import entity.Player;

public class KeyHandler implements KeyListener {

    public boolean upPressed, downPressed, rightPressed, leftPressed;
    GamePanel gp;

    public KeyHandler(GamePanel gp) {
        this.gp = gp;
    }

    @Override
    public void keyPressed(KeyEvent e) {

        int code = e.getKeyCode();

        if (gp.gameState == gp.titleState) {

            if (gp.ui.titleScreenState == 0) {
                if (code == KeyEvent.VK_W) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum == -1)
                        gp.ui.commandNum = 2;
                }
                if (code == KeyEvent.VK_S) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum == 3)
                        gp.ui.commandNum = 0;
                }
                if (code == KeyEvent.VK_ENTER) {
                    if (gp.ui.commandNum == 0)
                        gp.ui.titleScreenState = 1;
                    if (gp.ui.commandNum == 2)
                        System.exit(0);
                }
            } else if (gp.ui.titleScreenState == 1) {
                if (code == KeyEvent.VK_W) {
                    gp.ui.commandNum--;
                    if (gp.ui.commandNum == -1)
                        gp.ui.commandNum = 2;
                }
                if (code == KeyEvent.VK_S) {
                    gp.ui.commandNum++;
                    if (gp.ui.commandNum == 3)
                        gp.ui.commandNum = 0;
                }
                if (code == KeyEvent.VK_ENTER) {
                    if (gp.ui.commandNum == 0)
                        gp.gameState = gp.playState;
                    if (gp.ui.commandNum == 1) {
                        gp.playerGender = 1;
                        gp.player = new Player(gp, this);
                        gp.gameState = gp.playState;
                    }
                    if (gp.ui.commandNum == 2)
                        gp.ui.titleScreenState = 0;
                }
            }
        }

        if (code == KeyEvent.VK_W) {
            upPressed = true;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = true;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = true;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = true;
        }
        if (code == KeyEvent.VK_P) {
            gp.gameState = gp.pauseState + gp.playState - gp.gameState;
        }
    }

    @Override
    public void keyReleased(KeyEvent e) {

        int code = e.getKeyCode();
        if (code == KeyEvent.VK_W) {
            upPressed = false;
        }
        if (code == KeyEvent.VK_D) {
            rightPressed = false;
        }
        if (code == KeyEvent.VK_S) {
            downPressed = false;
        }
        if (code == KeyEvent.VK_A) {
            leftPressed = false;
        }

    }

    @Override
    public void keyTyped(KeyEvent e) {
    }

}
