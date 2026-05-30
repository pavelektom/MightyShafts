package Hra;

import javax.swing.*;
import java.awt.*;
/**
 * Třída Skladnik je pro našeho skladníka
 * Je to jakoby taková podtřída pro Game, protože je to JLabel, který pak vkládáme na Game
 * */
public class Skladnik extends JLabel {

    /**
     * Tady si vytvořím všechny nutné proměnné
     * level je momentální úroveň skladníka
     * casCekani používáme na nabírání rudy
     * kapacita je kolik toho může unést najednou
     * naklad je kolik toho má momentálně u sebe
     * rychlost je kolik pixelů se posune za jeden "timer" neboli 15 ms
     * */
    public int level = 1;
    private int rychlost = 3;
    private int casCekani = 0;
    private int kapacita = 50;
    private int naklad = 0;
    StavSkladnika aktualniStav = StavSkladnika.CEKA;

    private int cenaLevelUp = 500;
    /**
     * metodu levelUp pouzivame na zvyseni úrovně skladníka
     * zvýšíme level, kapacitu, rychlost
     * také zvýšíme cenu
     * */
    public void levelUp(){
        if(Game.cash >= cenaLevelUp){
            level++;
            rychlost++;
            kapacita = kapacita *2;
            Game.cash -= cenaLevelUp;
            cenaLevelUp = cenaLevelUp *2;
        }
    }

    /**
     * getCenaLevelUp je pro zjištení momentální ceny, pomocí této metody můžeme používat levelup jbuttony v třídě Game
     * */
    public int getCenaLevelUp() {
        return cenaLevelUp;
    }

    /**
     * Konstruktor skladníka, kde si určíme jeho polohu
     * také mu nastavíme vzhled
     * */
    public Skladnik(int poziceX, int poziceY) {
        this.setBounds(poziceX, poziceY, 75, 75);
        this.setIcon(hotovej2);
    }

    /**
     * Udělání vzhledu skladníka
     * zmenšíme si ho a poté nastavíme v metodě posun zdali jde doprava nebo doleva
     * */
    ImageIcon puvodni = new ImageIcon(getClass().getResource("/skladnikDoprava.png"));
    Image obrazek1 = puvodni.getImage();
    Image zmensenyObrazek = obrazek1.getScaledInstance(85, 65, Image.SCALE_SMOOTH);
    ImageIcon hotovej = new ImageIcon(zmensenyObrazek);

    ImageIcon puvodni2 = new ImageIcon(getClass().getResource("/skladnikDoleva.png"));
    Image obrazek2 = puvodni2.getImage();
    Image zmensenyObrazek2 = obrazek2.getScaledInstance(85, 65, Image.SCALE_SMOOTH);
    ImageIcon hotovej2 = new ImageIcon(zmensenyObrazek2);


    /**
     * Metoda posun
     * Metoda určena pro posun skladníka po mapě, pomocí ENUM má jiné stavy, kontrolujeme si na jakých je pixelech
     * kontrolujeme si pomocí ENUM co právě dělá a timpádem mu můžeme udělat jiné funkčností
     * */
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
                int volneMisto = kapacita - naklad;
                if (Vytah.truhlaNahore >= volneMisto) {
                    naklad += volneMisto;
                    Vytah.truhlaNahore -= volneMisto;
                } else {
                    naklad += Vytah.truhlaNahore;
                    Vytah.truhlaNahore = 0;
                }
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
