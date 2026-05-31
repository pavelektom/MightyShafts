package Hra;

import Grafika.CustomButton;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

/**
 * Tato třída slouží jako hlavní panel hry, je tu hlavní metoda turnOn, ve které se odehrává celá hra
 */

public class Game {

    private JFrame frame;
    private Image obrazekPozadi1;
    /** obrázek pro pozadí */

    /** cash jsou peníze, které hráč má a vidí je ve hře
     *  */
    static int cash = 0;
    private JLabel penizeText;

    public static Vytah vytah;

    public Game() {
        frame = new JFrame();
    }

    public void turnOn() {
        /**
         * Hlavní metoda hry, je tu úplně vše možné, na začátku metody jsou nastavení jen na JFrame
         * */

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());


        /**
         * Text kolik hráč má momentálně peněz
         * */
        penizeText = new JLabel("Cash: " + "$");
        penizeText.setBounds(1050, 5, 650, 30);
        penizeText.setFont(new Font("Arial", Font.BOLD, 40));
        penizeText.setForeground(Color.GREEN);

        /**
         * Text kolik je momentálně rudy v truhle u výtahu
         *  */
        JLabel textTruhly = new JLabel("");
        textTruhly.setBounds(200, 60, 6200, 40);
        textTruhly.setFont(new Font("Arial", Font.BOLD, 35));
        textTruhly.setForeground(Color.ORANGE);
        frame.add(textTruhly);

        /**
         * text kolik je momentálně rudy v šachtě, každý další JLabel rudaX je úplně stejná jako tato metoda
         *  */
        JLabel ruda1 = new JLabel("");
        ruda1.setBounds(300, 440, 100, 30);
        ruda1.setFont(new Font("Arial", Font.BOLD, 22));
        ruda1.setForeground(Color.YELLOW);
        frame.add(ruda1);

        JLabel ruda2 = new JLabel("");
        ruda2.setBounds(300, 590, 100, 30);
        ruda2.setFont(new Font("Arial", Font.BOLD, 22));
        ruda2.setForeground(Color.YELLOW);
        frame.add(ruda2);

        JLabel ruda3 = new JLabel("");
        ruda3.setBounds(300, 705, 100, 30);
        ruda3.setFont(new Font("Arial", Font.BOLD, 22));
        ruda3.setForeground(Color.YELLOW);
        frame.add(ruda3);

        JLabel ruda4 = new JLabel("");
        ruda4.setBounds(300, 815, 100, 30);
        ruda4.setFont(new Font("Arial", Font.BOLD, 22));
        ruda4.setForeground(Color.YELLOW);
        frame.add(ruda4);

        JLabel ruda5 = new JLabel("");
        ruda5.setBounds(300, 970, 100, 30);
        ruda5.setFont(new Font("Arial", Font.BOLD, 22));
        ruda5.setForeground(Color.YELLOW);
        frame.add(ruda5);


        /**
         * tlačítko na vrácení se zpět do main menu
         * */
        JButton exit = new JButton("↩");
        exit.setBackground(new Color(140, 20, 24));
        exit.setFont(new Font("Segoe UI emoji", Font.BOLD, 30));
        exit.setPreferredSize(new Dimension(135, 40));
        exit.setMaximumSize(new Dimension(135, 40));
        exit.setFocusPainted(false);
        exit.setBounds(1530, 5, 95, 40);
        exit.addActionListener(e -> {
            frame.dispose();
            new MainMenu().zapnout();
        });

        /**
         * Načítání obrázku pozadí do hry
         * Toto jsem udělal pomocí youtube
         * */
        try {
            obrazekPozadi1 = ImageIO.read(getClass().getResource("/pozadiHry.png"));
        } catch (IOException e) {
            System.out.println("Nepodarilo se načíst obrázek");
        }
        /**
         * Pomocí paintComponent si "Vykreslíme" obrázek do pozadí, pokud to fotku nedokáže vykreslit, nastaví černé pozadí
         * */
        JPanel panel1 = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (obrazekPozadi1 != null) {
                    g.drawImage(obrazekPozadi1, 0, 0, getWidth(), getHeight(), this);
                    g.setColor(new Color(0, 0, 0, 15));
                    g.fillRect(0, 0, getWidth(), getHeight());
                } else {
                    g.setColor(Color.BLACK);
                    System.out.println("nevidim fotku");
                }
            }
        };
        /**
         * Zde si vytváříme delníky do každé šachty, poté je všechny pridáme do pole
         * */
        Delnik delnik1 = new Delnik(300, 433, 200, 10);
        Delnik delnik2 = new Delnik(300, 580, 500, 20);
        Delnik delnik3 = new Delnik(300, 695, 1000, 35);
        Delnik delnik4 = new Delnik(300, 806, 2500, 60);
        Delnik delnik5 = new Delnik(300, 960, 5000, 100);
        Delnik[] poledelniku = {delnik1, delnik2, delnik3, delnik4, delnik5};

        /**
         * Tady si vytváříme výtah a skladníka
         * */
        vytah = new Vytah(170, 310, poledelniku);
        Skladnik skladnik = new Skladnik(1160, 235);

        /**
         * Tlačítko level up pro dělníky
         * nastavíme si barvy, pozice
         * poté pomocí actionlisteneru zavoláme metodu .levelUp(); z tridy Delnik
         * Změníme text, protože cena se zvětšila
         * */
        JButton levelUp1 = new JButton("Level Up : 200$");
        levelUp1.setBackground(new Color(99, 131, 243));
        levelUp1.setBounds(1200, 433, 180, 30);
        levelUp1.addActionListener(e -> {
            delnik1.levelUp();
            levelUp1.setText("Level Up : " + delnik1.getCenaLevelUp() + "$");

        });
        JButton levelUp2 = new JButton("Level Up : 500$");
        levelUp2.setBackground(new Color(99, 131, 243));
        levelUp2.setBounds(1200, 590, 180, 30);
        levelUp2.setVisible(false);
        levelUp2.addActionListener(e -> {
            if (delnik2.odemceno) {
                delnik2.levelUp();
                levelUp2.setText("Level Up : " + delnik2.getCenaLevelUp() + "$");
            }
        });
        JButton levelUp3 = new JButton("Level Up : 1000$");
        levelUp3.setBackground(new Color(99, 131, 243));
        levelUp3.setBounds(1200, 695, 180, 30);
        levelUp3.setVisible(false);
        levelUp3.addActionListener(e -> {
            if (delnik3.odemceno) {
                delnik3.levelUp();
                levelUp3.setText("Level Up : " + delnik3.getCenaLevelUp() + "$");
            }
        });
        JButton levelUp4 = new JButton("Level Up : 2500$");
        levelUp4.setBackground(new Color(99, 131, 243));
        levelUp4.setBounds(1200, 806, 180, 30);
        levelUp4.setVisible(false);
        levelUp4.addActionListener(e -> {
            if (delnik4.odemceno) {
                delnik4.levelUp();
                levelUp4.setText("Level Up : " + delnik4.getCenaLevelUp() + "$");
            }
        });
        JButton levelUp5 = new JButton("Level Up : 5000$");
        levelUp5.setBackground(new Color(99, 131, 243));
        levelUp5.setBounds(1200, 950, 180, 30);
        levelUp5.setVisible(false);
        levelUp5.addActionListener(e -> {
            if (delnik5.odemceno) {
                delnik5.levelUp();
                levelUp5.setText("Level Up : " + delnik5.getCenaLevelUp() + "$");
            }
        });
        /**
         * Level up pro skladníka, funguje stejně jako level up pro všechny delníky, jen má trošku jiné maličkosti v metodě .levelUp();
         * */
        JButton levelUpSkladnik = new JButton("Level Up : 500$");
        levelUpSkladnik.setBackground(new Color(99, 131, 243));
        levelUpSkladnik.setBounds(1400, 265, 160, 30);
        levelUpSkladnik.addActionListener(e -> {
            skladnik.levelUp();
            levelUpSkladnik.setText("Level up : " + skladnik.getCenaLevelUp() + "$");
        });
        /**
         * Level up pro výtah, stejné jako pro delníky, jen jiné specifikace v metodě .levelUp();
         * */
        JButton levelUpVytah = new JButton("Level Up : 500$");
        levelUpVytah.setBackground(new Color(99, 131, 243));
        levelUpVytah.setBounds(5, 265, 160, 30);
        levelUpVytah.addActionListener(e -> {
            vytah.levelUp();
            levelUpVytah.setText("Level up : " + vytah.getCenaLevelUp() + "$");
        });

        /**
         * Odemknutí pater
         * Vždy uděláme tlačítko a takový plášť přes ty šachty, udělal jsem pro každou třídu samostatné
         * Po koupení patra zmizí plášť i tlačítko, objeví se dělník a tlačítko pro level up
         * */
        JButton kupPatro2 = new JButton("Odemknout 2. patro (2500 $)");
        kupPatro2.setBackground(new Color(253, 213, 47));
        kupPatro2.setBounds(550, 545, 300, 30);
        JPanel zamknuti = new JPanel();
        zamknuti.setBackground(new Color(255, 0, 0, 150));
        zamknuti.setBounds(295, 520, 880, 120);
        delnik2.setVisible(false);
        kupPatro2.addActionListener(e -> {
            if (Game.cash >= 2500 && !delnik2.odemceno) {
                Game.cash -= 2500;
                levelUp2.setVisible(true);
                delnik2.odemkni();
                zamknuti.setVisible(false);
                kupPatro2.setVisible(false);
            }
        });

        delnik3.setVisible(false);
        JPanel zamek3 = new JPanel();
        zamek3.setBackground(new Color(255, 0, 0, 150));
        zamek3.setBounds(295, 640, 880, 120);

        JButton kupPatro3 = new JButton("Odemknout 3. patro (10000 $)");
        kupPatro3.setBackground(new Color(253, 213, 47));
        kupPatro3.setBounds(550, 660, 300, 30);

        kupPatro3.addActionListener(e -> {
            if (Game.cash >= 10000 && !delnik3.odemceno && delnik2.odemceno) {
                Game.cash -= 10000;
                levelUp3.setVisible(true);
                delnik3.odemkni();
                zamek3.setVisible(false);
                kupPatro3.setVisible(false);
            }
        });

        delnik4.setVisible(false);
        JPanel zamek4 = new JPanel();
        zamek4.setBackground(new Color(255, 0, 0, 150));
        zamek4.setBounds(295, 760, 880, 120);

        JButton kupPatro4 = new JButton("Odemknout 4. patro (50000 $)");
        kupPatro4.setBackground(new Color(253, 213, 47));
        kupPatro4.setBounds(550, 780, 300, 30);

        kupPatro4.addActionListener(e -> {
            if (Game.cash >= 50000 && !delnik4.odemceno && delnik3.odemceno) {
                Game.cash -= 50000;
                levelUp4.setVisible(true);
                delnik4.odemkni();
                zamek4.setVisible(false);
                kupPatro4.setVisible(false);
            }
        });

        delnik5.setVisible(false);
        JPanel zamek5 = new JPanel();
        zamek5.setBackground(new Color(255, 0, 0, 150));
        zamek5.setBounds(295, 900, 880, 120);

        JButton kupPatro5 = new JButton("Odemknout 5. patro (250000 $)");
        kupPatro5.setBackground(new Color(253, 213, 47));
        kupPatro5.setBounds(550, 930, 300, 30);

        kupPatro5.addActionListener(e -> {
            if (Game.cash >= 250000 && !delnik5.odemceno && delnik4.odemceno) {
                Game.cash -= 250000;
                levelUp5.setVisible(true);
                delnik5.odemkni();
                zamek5.setVisible(false);
                kupPatro5.setVisible(false);
            }
        });

        /**
         * Tady přidáváme všechno do framu
         * */
        frame.add(levelUpSkladnik);
        frame.add(levelUpVytah);
        frame.add(levelUp5);
        frame.add(levelUp4);
        frame.add(levelUp3);
        frame.add(levelUp2);
        frame.add(levelUp1);
        frame.add(kupPatro2);
        frame.add(zamknuti);
        frame.add(kupPatro3);
        frame.add(zamek3);
        frame.add(kupPatro4);
        frame.add(zamek4);
        frame.add(kupPatro5);
        frame.add(zamek5);
        frame.add(skladnik);
        frame.add(vytah);
        frame.add(delnik1);
        frame.add(delnik2);
        frame.add(delnik3);
        frame.add(delnik4);
        frame.add(delnik5);


        /**
         * Hlavní část hry Timer
         * pomocí timeru mi ve hře můžou běhat dělníci
         * Ze začátku běhá jen první, protože jiní jsou zamknutí
         * */
        Timer cas = new Timer(15, e -> {
            delnik1.posun();
            if (delnik2.odemceno) { /** Tady můžeme vidět jak jsou zamčení, pokud hráč koupí šachtu, přidá se nám tam dělník a rovnou se začne posouvat */
                delnik2.posun();
            }
            if (delnik3.odemceno) {
                delnik3.posun();
            }
            if (delnik4.odemceno) {
                delnik4.posun();
            }
            if (delnik5.odemceno) {
                delnik5.posun();
            }
            /**
             * Stejně uděláme posun pro výtah a skladníka
             * */
            vytah.posun();
            skladnik.posun();

            /**
             * Nastavím peníze
             * */
            penizeText.setText("Peníze: " + cash + " $ ");

            /**
             * Tady zas máme vizuál, abychom viděli kdo má kde kolik momentálně rudy
             * */
            if (Vytah.truhlaNahore > 0) {
                textTruhly.setText(String.valueOf(Vytah.truhlaNahore));
            } else {
                textTruhly.setText("");
            }
            if (delnik1.vylozenaRuda > 0) {
                ruda1.setText(String.valueOf(delnik1.vylozenaRuda));
            } else {
                ruda1.setText("");
            }
            if (delnik2.vylozenaRuda > 0) {
                ruda2.setText(String.valueOf(delnik2.vylozenaRuda));
            } else {
                ruda2.setText("");
            }
            if (delnik3.vylozenaRuda > 0) {
                ruda3.setText(String.valueOf(delnik3.vylozenaRuda));
            } else {
                ruda3.setText("");
            }
            if (delnik4.vylozenaRuda > 0) {
                ruda4.setText(String.valueOf(delnik4.vylozenaRuda));
            } else {
                ruda4.setText("");
            }
            if (delnik5.vylozenaRuda > 0) {
                ruda5.setText(String.valueOf(delnik5.vylozenaRuda));
            } else {
                ruda5.setText("");
            }
        });
        /**
         *  TADY MÁME TLAČÍTKA ULOŽIT A NAČÍST
         *  na začátku si jen uděláme normální jbutton a umístíme na mapu
         *  */
        JButton tlacitkoUlozit = new JButton("Uložit");
        tlacitkoUlozit.setBackground(new Color(100, 200, 100));
        tlacitkoUlozit.setFont(new Font("Arial", Font.BOLD, 15));
        tlacitkoUlozit.setBounds(10, 5, 100, 35);
        tlacitkoUlozit.setFocusPainted(false);

        tlacitkoUlozit.addActionListener(e -> {
            try {
                java.io.PrintWriter zapisovac = new java.io.PrintWriter("save.txt");

                /**
                 *  Uložíme peníze
                 *  */
                zapisovac.println(Game.cash);

                /**
                 * Uložíme , která patra jsou odemčená
                 * */
                zapisovac.println(delnik2.odemceno);
                zapisovac.println(delnik3.odemceno);
                zapisovac.println(delnik4.odemceno);
                zapisovac.println(delnik5.odemceno);

                /**
                 *  Uložíme staty všech 5 dělníků
                 *  */
                Delnik[] vsichni = {delnik1, delnik2, delnik3, delnik4, delnik5};
                for (int i = 0; i < vsichni.length; i++) {
                    zapisovac.println(vsichni[i].level);
                    zapisovac.println(vsichni[i].kapacita);
                    zapisovac.println(vsichni[i].rychlost);
                    zapisovac.println(vsichni[i].cenaLevelUp);
                }
                /**
                 * Uložíme staty výtahu */
                zapisovac.println(vytah.level);
                zapisovac.println(vytah.kapacita);
                zapisovac.println(vytah.rychlost);
                zapisovac.println(vytah.cenaLevelUp);
                /** Uložíme staty skladníka
                 *  */
                zapisovac.println(skladnik.level);
                zapisovac.println(skladnik.kapacita);
                zapisovac.println(skladnik.rychlost);
                zapisovac.println(skladnik.cenaLevelUp);

                zapisovac.close();
                JOptionPane.showMessageDialog(frame, "Hra byla úspěšně uložena!");

            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Chyba při ukládání hry!");
            }
        });

        JButton tlacitkoNacist = new JButton("Načíst");
        tlacitkoNacist.setBackground(new Color(100, 150, 255));
        tlacitkoNacist.setFont(new Font("Arial", Font.BOLD, 15));
        tlacitkoNacist.setBounds(120, 5, 100, 35);
        tlacitkoNacist.setFocusPainted(false);

        tlacitkoNacist.addActionListener(e -> {
            try {
                java.util.Scanner ctenar = new java.util.Scanner(new java.io.File("save.txt"));
                /**
                 *  Načteme peníze
                 *  */
                Game.cash = ctenar.nextInt();
                /** Načteme informace o patrech do proměnných
                 * */
                boolean p2 = ctenar.nextBoolean();
                boolean p3 = ctenar.nextBoolean();
                boolean p4 = ctenar.nextBoolean();
                boolean p5 = ctenar.nextBoolean();

                if (p2) {
                    delnik2.odemkni();
                    zamknuti.setVisible(false);
                    kupPatro2.setVisible(false);
                    levelUp2.setVisible(true);
                }
                if (p3) {
                    delnik3.odemkni();
                    zamek3.setVisible(false);
                    kupPatro3.setVisible(false);
                    levelUp3.setVisible(true);
                }
                if (p4) {
                    delnik4.odemkni();
                    zamek4.setVisible(false);
                    kupPatro4.setVisible(false);
                    levelUp4.setVisible(true);
                }
                if (p5) {
                    delnik5.odemkni();
                    zamek5.setVisible(false);
                    kupPatro5.setVisible(false);
                    levelUp5.setVisible(true);
                }

                /** Načteme udaje 5 dělníků a přepíšu ceny na tlačítkách
                 *  */
                Delnik[] vsichni = {delnik1, delnik2, delnik3, delnik4, delnik5};
                JButton[] tlacitkaLevelu = {levelUp1, levelUp2, levelUp3, levelUp4, levelUp5};

                for (int i = 0; i < vsichni.length; i++) {
                    vsichni[i].level = ctenar.nextInt();
                    vsichni[i].kapacita = ctenar.nextInt();
                    vsichni[i].rychlost = ctenar.nextInt();
                    vsichni[i].cenaLevelUp = ctenar.nextInt();
                    tlacitkaLevelu[i].setText("Level Up : " + vsichni[i].cenaLevelUp + "$");
                }

                /**
                 * Načteme výtah a zaktualizujem jeho tlačítko level up
                 *  */
                vytah.level = ctenar.nextInt();
                vytah.kapacita = ctenar.nextInt();
                vytah.rychlost = ctenar.nextInt();
                vytah.cenaLevelUp = ctenar.nextInt();
                levelUpVytah.setText("Level up : " + vytah.cenaLevelUp + "$");

                /**
                 * Načteme skladníka a zaktualizujem jeho tlačítko  level up
                 * */
                skladnik.level = ctenar.nextInt();
                skladnik.kapacita = ctenar.nextInt();
                skladnik.rychlost = ctenar.nextInt();
                skladnik.cenaLevelUp = ctenar.nextInt();
                levelUpSkladnik.setText("Level up : " + skladnik.cenaLevelUp + "$");
                ctenar.close();
                JOptionPane.showMessageDialog(frame, "Hra byla úspěšně načtena");
            } catch (Exception ex) {
                JOptionPane.showMessageDialog(frame, "Soubor save.txt nebyl nalezen");
            }
        });

        frame.add(tlacitkoNacist);
        frame.add(tlacitkoUlozit);
        /** Zapneme timer */
        cas.start();

        /** vypneme layout pro panel abychom se mohli koordinovat podle pixelů */
        panel1.setLayout(null);
        frame.add(penizeText);
        frame.add(panel1);
        panel1.add(exit);
        /** dame viditelny frame aby po zapnutí hry z mainmenu se zobrazil frame */
        frame.setVisible(true);
    }
}
