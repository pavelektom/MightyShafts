package Hra;

import javax.swing.*;
import java.awt.*;


public class Delnik extends JLabel {

    StavDelnika aktualniStav = StavDelnika.JDE_TEZIT;

    int vylozenaRuda =0;
    private int casTezby;
    private int kapacita;
    private int batoh = 0;
    public int level = 1;
    private int rychlost = 3;

    public boolean odemceno = false;

    public void odemkni() {
        this.odemceno = true;
        this.setVisible(true);
    }

    public int getCenaLevelUp() {
        return cenaLevelUp;
    }

    private int cenaLevelUp;
    public void levelUp(){
        if(Game.cash >= cenaLevelUp){
            level++;
            rychlost++;
            kapacita += 10;
            Game.cash -= cenaLevelUp;
            cenaLevelUp = cenaLevelUp *2;

        }
    }

    public Delnik(int poziceX, int poziceY, int cenaLevelUp, int kapacita) {
        this.setBounds(poziceX, poziceY, 50, 50);
        this.setIcon(hotovej);
        this.cenaLevelUp = cenaLevelUp;
        this.kapacita = kapacita;
    }

    ImageIcon puvodni = new ImageIcon(getClass().getResource("/delnikDoprava.png"));
    Image obrazek1 = puvodni.getImage();
    Image zmensenyObrazek = obrazek1.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    ImageIcon hotovej = new ImageIcon(zmensenyObrazek);

    ImageIcon puvodni2 = new ImageIcon(getClass().getResource("/delnikDoleva.png"));
    Image obrazek2 = puvodni2.getImage();
    Image zmensenyObrazek2 = obrazek2.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    ImageIcon hotovej2 = new ImageIcon(zmensenyObrazek2);

    public void posun() {
        int x = this.getX();
        int y = this.getY();

        if (aktualniStav == StavDelnika.JDE_TEZIT) {
            this.setLocation(x + rychlost, y);

            if (this.getX() >= 1120) {
                aktualniStav = StavDelnika.TEZI;
            }
        }
        else if (aktualniStav == StavDelnika.TEZI) {
            casTezby++;
            if (casTezby >= 30) {
                batoh += level*5;
                casTezby = 0;
            }
            if (batoh >= kapacita) {
                aktualniStav = StavDelnika.JDE_K_VYTAHU;
                this.setIcon(hotovej2);

            }
        }
        else if (aktualniStav == StavDelnika.JDE_K_VYTAHU) {
            this.setLocation(x - rychlost, y);

            if (this.getX() <= 300) {
                aktualniStav = StavDelnika.VYKLADA;
                this.setIcon(hotovej);
            }
        }
        else if (aktualniStav == StavDelnika.VYKLADA) {
            vylozenaRuda += batoh;
            batoh = 0;
            aktualniStav = StavDelnika.JDE_TEZIT;
        }
    }



}
