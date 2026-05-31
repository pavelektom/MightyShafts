package Hra;

import javax.swing.*;
import java.awt.*;
/**
 * Třída Vytah je pro náš výtah
 * Je to jakoby taková podtřída pro Game, protože je to JLabel, který pak vkládáme na Game
 * */
public class Vytah extends JLabel {

    /**
     * Tady si vytvořím všechny nutné proměnné
     * level je momentální úroveň skladníka
     * casNakladu používáme na nabírání rudy
     * kapacita je kolik toho může unést najednou
     * naklad je kolik toho má momentálně u sebe
     * rychlost je kolik pixelů se posune za jeden "timer" neboli 15 ms
     * uKohoJeVytah je pro metodu posun důležité
     * truhlaNahore je pro ukazovani kolik mame momentalne v truhle u výtahu rudy
     * pole vsichniDelnici je pole delniku pro to aby vytah mohl lokalizovat ke komu jit
     * */
    public int level = 1;
    public int rychlost = 3;
    private int casNakladu = 0;
    private int naklad = 0;
    static int truhlaNahore = 0;
    public int kapacita = 30;
    private Delnik[] vsichniDelnici;
    private Delnik uKohoJeVytah;
    StavVytahu aktualniStav = StavVytahu.CEKA;


    public int cenaLevelUp = 500;
    /**
     * metodu levelUp pouzivame na zvyseni úrovně výtahu
     * zvýšíme level, kapacitu, rychlost
     * také zvýšíme cenu
     */
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
     * Udělání vzhledu výtahu
     * zmenšíme si ho a pak si ho v konstruktoru načteme a nastavime
     * */
    ImageIcon obrazek = new ImageIcon(getClass().getResource("/vytah.png"));
    Image zmenseny = obrazek.getImage().getScaledInstance(110, 100, Image.SCALE_SMOOTH);

    /**
     * Konstruktor, pomoci ktereho se urci pole delniku ke komu vse bude jezdit vytah
     * urceni pozice x a y
     * take nastaveni vzhledu vytahu
     * */
    public Vytah(int poziceX, int poziceY, Delnik[] delnici) {
        this.setBounds(poziceX, poziceY, 100, 100);
        this.setIcon(new ImageIcon(zmenseny));
        this.vsichniDelnici = delnici;
    }
    /**
     * Hlavní metoda pro pohyb výtahu.
     * CEKA: Výtah stojí nahoře a kontroluje, zda má některý z dělníků v patrech vyloženou rudu
     * JEDE_DOLU: Posunuje se směrem k patru, kde je připravená ruda
     * NAKLADA: Vezme vyloženou rudu od dělníka - pokud má místo
     * JEDE_NAHORU: Vrací se zpět na povrch
     * VYKLADA: Přesype svůj náklad do společné proměnné truhlaNahore,
     * vynuluje svůj náklad a přejde zpět do stavu CEKA.
     */
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

