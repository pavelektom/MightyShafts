package Hra;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

import Grafika.CustomButton;

public class Game {

    private JFrame frame;
    private Image obrazekPozadi1;

    public Game(){
        frame = new JFrame();
    }
    public void turnOn(){
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

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
        Delnik delnik1 = new Delnik(300, 430);
        Delnik delnik2 = new Delnik(300, 580);
        Vytah vytah = new Vytah(170, 310);
        Skladnik skladnik = new Skladnik(315, 235);

        frame.add(skladnik);
        frame.add(vytah);
        frame.add(delnik1);
        frame.add(delnik2);
        Timer cas = new Timer(15, e ->{
            delnik1.posun();
            delnik2.posun();
            vytah.posun();
            skladnik.posun();
        });

        cas.start();

        panel1.setLayout(null);

        frame.add(panel1);
        panel1.add(exit);

        frame.setVisible(true);
    }
}
