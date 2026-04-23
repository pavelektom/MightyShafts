import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;

public class Music implements  Runnable {

    private Clip clip;
    private String c;

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
           }
       } catch (Exception e) {
           throw new RuntimeException(e);
       }
       }

    public void stop(){
        if (clip != null && clip.isOpen()) {
            clip.close();
        }
    }

    }





