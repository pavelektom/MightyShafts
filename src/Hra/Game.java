package Hra;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Game {

    private JFrame frame;
    private Image obrazekPozadi1;


    static int cash = 0;
    private JLabel penizeText;

    public static Vytah vytah;

    public Game(){
        frame = new JFrame();

    }
    public void turnOn(){

        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        penizeText = new JLabel("Cash: " + "$");
        penizeText.setBounds(1050, 5, 650, 30);
        penizeText.setFont(new Font("Arial", Font.BOLD, 40));
        penizeText.setForeground(Color.GREEN);

        JLabel textTruhly = new JLabel("");
        textTruhly.setBounds(200, 60, 6200, 40);
        textTruhly.setFont(new Font("Arial", Font.BOLD, 35));
        textTruhly.setForeground(Color.ORANGE);
        frame.add(textTruhly);

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



        JButton exit = new JButton("↩");
        exit.setBackground(new Color(140, 20, 24));
        exit.setFont(new Font("Segoe UI emoji", Font.BOLD, 30));
        exit.setPreferredSize(new Dimension(135, 40));
        exit.setMaximumSize(new Dimension(135, 40));
        exit.setFocusPainted(false);
        exit.setBounds(1530,5 , 95, 40);
        exit.addActionListener(e->{
            frame.dispose();
            new MainMenu().zapnout();
        });


        try {
            obrazekPozadi1 = ImageIO.read(getClass().getResource("/pozadiHry.png"));
        } catch (IOException e) {
            System.out.println("Nepodarilo se načíst obrázek");
        }

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
        Delnik delnik1 = new Delnik(300, 433);
        Delnik delnik2 = new Delnik(300, 580);
        Delnik delnik3 = new Delnik(300, 695);
        Delnik delnik4 = new Delnik(300, 806);
        Delnik delnik5 = new Delnik(300, 960);
        Delnik[] poledelniku = {delnik1, delnik2, delnik3, delnik4, delnik5};

        vytah = new Vytah(170, 310, poledelniku);
        Skladnik skladnik = new Skladnik(1160, 235);

        frame.add(skladnik);
        frame.add(vytah);
        frame.add(delnik1);
        frame.add(delnik2);
        frame.add(delnik3);
        frame.add(delnik4);
        frame.add(delnik5);

            Timer cas = new Timer(15, e ->{
                delnik1.posun();
                delnik2.posun();
                delnik3.posun();
                delnik4.posun();
                delnik5.posun();
                vytah.posun();
                skladnik.posun();

                penizeText.setText("Peníze: " + cash + " $ ");

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

        cas.start();

        panel1.setLayout(null);
        frame.add(penizeText);
        frame.add(panel1);
        panel1.add(exit);

        frame.setVisible(true);
    }
}
