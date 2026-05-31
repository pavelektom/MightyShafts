package Hudba;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import java.io.File;
/**
 * Třída Music slouží k přehrávání hudby na pozadí hry.
 * Implementuje rozhraní Runnable, což znamená, že hudba může běžet
 * ve vlastním vlákně a nezasekává tak zbytek hry.
 */
public class Music implements  Runnable {

    private static Clip clip;
    private String c;
    private static boolean hraje = false;

    public Music(String c) {
        this.c = c;
    }
    /**
     * Metoda run() se spustí při odstartování vlákna.
     * Bezpečnostní pojistka: Pokud už nějaký klip existuje a je otevřený tak ho zavře
     * Vytvoří zvukový stream a nahraje ho do nového klipu
     * Nastaví neustálé opakování spustí přehrávání a změní hraje na true.
     */
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

    /**
     * Metoda zapnoutVypnout() je k pauzování a opětovnému spuštění
     * Pokud hudba právě hraje, zastaví ji a změní boolean na false.
     * Pokud hudba nehraje, znovu ji spustí a změní na true.
     */
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





