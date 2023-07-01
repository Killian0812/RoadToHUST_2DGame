package main;

import java.io.File;
import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class Sound {

    Clip clip;
    File soundFile[] = new File[30];
    URL soundURL[] = new URL[30];

    public Sound() {

        // soundFile[0] = new File("./res/sound/bgmusic.wav");
        // soundFile[1] = new File("./res/sound/coin.wav");
        // soundFile[2] = new File("./res/sound/fanfare.wav");
        // soundFile[3] = new File("./res/sound/powerup.wav");
        // soundFile[4] = new File("./res/sound/unlock.wav");
        // soundFile[5] = new File("./res/sound/won.wav");
        // soundFile[6] = new File("./res/sound/dead.wav");
        // soundFile[7] = new File("./res/sound/hurt.wav");
        // soundFile[8] = new File("./res/sound/punching.wav");

        soundURL[0] = Sound.class.getResource("/sound/bgmusic.wav");
        soundURL[1] = Sound.class.getResource("/sound/coin.wav");
        soundURL[2] = Sound.class.getResource("/sound/fanfare.wav");
        soundURL[3] = Sound.class.getResource("/sound/powerup.wav");
        soundURL[4] = Sound.class.getResource("/sound/unlock.wav");
        soundURL[5] = Sound.class.getResource("/sound/won.wav");
        soundURL[6] = Sound.class.getResource("/sound/dead.wav");
        soundURL[7] = Sound.class.getResource("/sound/hurt.wav");
        soundURL[8] = Sound.class.getResource("/sound/punching.wav");

    }

    public void setFile(int i) {
        try {
            AudioInputStream ais = AudioSystem.getAudioInputStream(soundURL[i]);
            clip = AudioSystem.getClip();
            clip.open(ais);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public void play() {
        clip.start();
    }

    public void loop() {
        clip.loop(Clip.LOOP_CONTINUOUSLY);
    }

    public void stop() {
        clip.stop();
    }
}