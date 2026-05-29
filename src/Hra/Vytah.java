package Hra;

import javax.swing.*;
import java.awt.*;

public class Vytah extends JLabel {

    public int level = 1;
    private int rychlost = 3;
    private int casNakladu = 0;
    private int naklad = 0;
    static int truhlaNahore = 0;
    private int kapacita = 30;
    private Delnik[] vsichniDelnici;
    private Delnik uKohoJeVytah;
    StavVytahu aktualniStav = StavVytahu.CEKA;

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

    ImageIcon obrazek = new ImageIcon(getClass().getResource("/vytah.png"));
    Image zmenseny = obrazek.getImage().getScaledInstance(110, 100, Image.SCALE_SMOOTH);

    public Vytah(int poziceX, int poziceY, Delnik[] delnici) {
        this.setBounds(poziceX, poziceY, 100, 100);
        this.setIcon(new ImageIcon(zmenseny));
        this.vsichniDelnici = delnici;
    }

    public void posun() {
        int x = this.getX();
        int y = this.getY();

        if (aktualniStav == StavVytahu.CEKA) {
            for (int i = 0; i < vsichniDelnici.length; i++) {
                Delnik d = vsichniDelnici[i];
                if (d != null && d.vylozenaRuda > 0) {
                    aktualniStav = StavVytahu.JEDE_DOLU;
                    break;
                }
            }
        }
        else if (aktualniStav == StavVytahu.JEDE_DOLU) {
            this.setLocation(x, y + rychlost);
            boolean jeRudaDole = false;

            for (int i = 0; i < vsichniDelnici.length; i++) {
                Delnik d = vsichniDelnici[i];
                if (d != null && d.vylozenaRuda > 0) {
                    int ciloveY = d.getY() - 30;
                    if (this.getY() <= ciloveY && this.getY() + rychlost >= ciloveY) {
                        this.setLocation(x, ciloveY);
                        uKohoJeVytah = d;
                        aktualniStav = StavVytahu.NAKLADA;
                        return;
                    }
                    if (d.getY() > this.getY()) {
                        jeRudaDole = true;
                    }
                }
            }
            if (!jeRudaDole || this.getY() >= 930) {
                aktualniStav = StavVytahu.JEDE_NAHORU;
            }
        }
        else if (aktualniStav == StavVytahu.JEDE_NAHORU) {
            this.setLocation(x, y - rychlost);
            if (this.getY() <= 310) {
                truhlaNahore += naklad;
                naklad = 0;
                aktualniStav = StavVytahu.CEKA;
            }
        }
        else if (aktualniStav == StavVytahu.NAKLADA) {
            casNakladu++;
            if (casNakladu >= 50) {
                int volneMisto = kapacita - naklad;
                if (uKohoJeVytah.vylozenaRuda >= volneMisto) {
                    naklad += volneMisto;
                    uKohoJeVytah.vylozenaRuda -= volneMisto;
                } else {
                    naklad += uKohoJeVytah.vylozenaRuda;
                    uKohoJeVytah.vylozenaRuda = 0;
                }
                casNakladu = 0;
                uKohoJeVytah = null;
                if (naklad >= kapacita) {
                    aktualniStav = StavVytahu.JEDE_NAHORU;
                } else {
                    aktualniStav = StavVytahu.JEDE_DOLU;
                }
            }
        }
    }
    }

