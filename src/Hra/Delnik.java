package Hra;

import javax.swing.*;
import java.awt.*;


public class Delnik extends JLabel {
    /**
     * Tady si vytvořím všechny nutné proměnné
     * level je momentální úroveň dělníka
     * casTezby používáme na odpočítávání těžení
     * kapacita je kolik toho může unést najednou ve svém batohu
     * batoh je kolik toho má momentálně u sebe
     * rychlost je kolik pixelů se posune za jeden "timer" neboli 15 ms
     * vylozenaRuda slouží k uchování vysypané rudy u výtahu
     * odemceno určuje, jestli už si hráč toto patro koupil
     * aktualniStav je pro metodu posun důležité a říká nám, co dělník zrovna dělá
     * */
    StavDelnika aktualniStav = StavDelnika.JDE_TEZIT;
    int vylozenaRuda =0;
    private int casTezby;
    public int kapacita;
    private int batoh = 0;
    public int level = 1;
    public int rychlost = 3;
    public boolean odemceno = false;

    /**
     * metodu odemkni pouzivame k tomu, aby se dělník po zaplacení zviditelnil a odemkl
     * nastavime proměnnou odemceno na true a zobrazíme dělníka
     * */
    public void odemkni() {
        this.odemceno = true;
        this.setVisible(true);
    }
     /** metodu levelUp pouzivame na zvyseni úrovně dělníka
     * zvýšíme level, kapacitu, rychlost a  cenu
     * */
    public int cenaLevelUp;
    public void levelUp(){
        if(Game.cash >= cenaLevelUp){
            level++;
            rychlost++;
            kapacita += kapacita/2 ;
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

    public Delnik(int poziceX, int poziceY, int cenaLevelUp, int kapacita) {
        this.setBounds(poziceX, poziceY, 50, 50);
        this.setIcon(hotovej);
        this.cenaLevelUp = cenaLevelUp;
        this.kapacita = kapacita;
    }

    /**
     * Udělání vzhledu dělníka
     * zmenšíme si ho a poté nastavíme v metodě posun zdali jde doprava nebo doleva
     * */
    ImageIcon puvodni = new ImageIcon(getClass().getResource("/delnikDoprava.png"));
    Image obrazek1 = puvodni.getImage();
    Image zmensenyObrazek = obrazek1.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    ImageIcon hotovej = new ImageIcon(zmensenyObrazek);
    ImageIcon puvodni2 = new ImageIcon(getClass().getResource("/delnikDoleva.png"));
    Image obrazek2 = puvodni2.getImage();
    Image zmensenyObrazek2 = obrazek2.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    ImageIcon hotovej2 = new ImageIcon(zmensenyObrazek2);

    /**
     * Metoda posun určena pro pohyb dělníka po mapě a řízení jeho činnosti.
     * 1. Zjistí momentální pozici X a Y dělníka na obrazovce.
     * 2. Stav JDE_TEZIT: Posunuje dělníka doprava. Na pixelu 1120 přepne stav na TEZI.
     * 3. Stav TEZI: Přičítá čas těžby a překresluje progress bar.
     * Po dosažení 100 tiků naplní batoh na maximum (batoh = kapacita),
     * vynuluje čas a přepne stav na JDE_K_VYTAHU (včetně otočení obrázku doleva).
     * 4. Stav JDE_K_VYTAHU: Posunuje dělníka doleva. Na pixelu 300 přepne stav na VYKLADA.
     * 5. Stav VYKLADA: Přesype rudu z batohu do společné vyložené rudy,
     * vyprázdní batoh a pošle dělníka znovu do stavu JDE_TEZIT.
     * */
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
            this.repaint();
            if (casTezby >= 30) {
                batoh += kapacita;
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


    /**
     * Metoda paintComponent vykresluje progress bar
     * Kreslí se jen ve chvíli, kdy dělník zrovna těží, a ukazuje stav naplnění batohu
     * */
    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (aktualniStav == StavDelnika.TEZI) {
            Graphics2D g2 = (Graphics2D) g;
            g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
            g2.setColor(Color.BLACK);
            g2.fillRect(5, 0, 40, 6);
            double procento = casTezby / 100.0;
            if (procento > 1.0) {
                procento = 1.0;
            }
            int sirkaVyplne = (int) (procento * 38);
            g2.setColor(Color.GREEN);
            g2.fillRect(6, 1, sirkaVyplne, 4);
        }
    }


}
