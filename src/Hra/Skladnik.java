package Hra;

import javax.swing.*;
import java.awt.*;

public class Skladnik extends JLabel {

    public int level = 1;
    private int rychlost = 3;
    private int casCekani = 0;
    private int kapacita = 50;
    private int naklad = 0;
    StavSkladnika aktualniStav = StavSkladnika.CEKA;

    private int cenaLevelUp = 500;
    public void levelUp(){
        if(Game.cash >= cenaLevelUp){
            level++;
            rychlost++;
            kapacita = kapacita *2;
            Game.cash -= cenaLevelUp;
            cenaLevelUp = cenaLevelUp *2;
        }
    }

    public int getCenaLevelUp() {
        return cenaLevelUp;
    }

    public Skladnik(int poziceX, int poziceY) {
        this.setBounds(poziceX, poziceY, 75, 75);
        this.setIcon(hotovej2);
    }
    
    ImageIcon puvodni = new ImageIcon(getClass().getResource("/skladnikDoprava.png"));
    Image obrazek1 = puvodni.getImage();
    Image zmensenyObrazek = obrazek1.getScaledInstance(85, 65, Image.SCALE_SMOOTH);
    ImageIcon hotovej = new ImageIcon(zmensenyObrazek);

    ImageIcon puvodni2 = new ImageIcon(getClass().getResource("/skladnikDoleva.png"));
    Image obrazek2 = puvodni2.getImage();
    Image zmensenyObrazek2 = obrazek2.getScaledInstance(85, 65, Image.SCALE_SMOOTH);
    ImageIcon hotovej2 = new ImageIcon(zmensenyObrazek2);

    public void posun() {
        int x = this.getX();
        int y = this.getY();

        if (aktualniStav == StavSkladnika.CEKA) {
            this.setIcon(hotovej2);

            if (Game.vytah != null && Vytah.truhlaNahore > 0) {
                aktualniStav = StavSkladnika.JDE_NAKLADAT;
            }
        }
        else if (aktualniStav == StavSkladnika.JDE_NAKLADAT) {
            this.setLocation(x - rychlost, y);

            if (this.getX() <= 315) {
                aktualniStav = StavSkladnika.NAKLADA;
            }
        }
        else if (aktualniStav == StavSkladnika.NAKLADA) {
            casCekani++;

            if (casCekani >= 40) {
                naklad = Vytah.truhlaNahore;
                Vytah.truhlaNahore = 0;

                casCekani = 0;
                this.setIcon(hotovej);
                aktualniStav = StavSkladnika.JDE_VYKLADAT;
            }
        }
        else if (aktualniStav == StavSkladnika.JDE_VYKLADAT) {
            this.setLocation(x + rychlost, y);

            if (this.getX() >= 1160) {
                aktualniStav = StavSkladnika.VYKLADA;
            }
        }
        else if (aktualniStav == StavSkladnika.VYKLADA) {
            casCekani++;

            if (casCekani >= 40) {
                Game.cash += (naklad * 5);
                naklad = 0;

                casCekani = 0;
                this.setIcon(hotovej2);
                aktualniStav = StavSkladnika.CEKA;
            }
        }
    }




}
