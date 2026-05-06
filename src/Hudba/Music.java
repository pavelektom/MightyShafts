package Hudba;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Music implements  Runnable {

    private static Clip clip;
    private String c;
    private static boolean hraje = false;

    public Music(String c) {
        this.c = c;
    }

    public void run(){

       try {
           File song = new File(c);
           if (song.exists()){
               if (clip != null && clip.isOpen()) {
                   clip.close();
               }
               AudioInputStream a = AudioSystem.getAudioInputStream(song);
               clip = AudioSystem.getClip();
               clip.open(a);
               clip.loop(Clip.LOOP_CONTINUOUSLY);
               clip.start();
               hraje = true;
           }
       } catch (Exception e) {
           throw new RuntimeException(e);
            }
       }

       public static boolean ahoj(){
            return clip.isRunning();
       }

    public static void zapnoutVypnout() {
        if (clip == null) {
            return;
        }
        if (hraje) {
            clip.stop();
            hraje = false;
        } else {
            clip.start();
            clip.loop(Clip.LOOP_CONTINUOUSLY);
            hraje = true;
        }
    }

    }





