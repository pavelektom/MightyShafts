package Hra;

import javax.swing.*;
import java.awt.*;

public class Vytah extends JLabel {

    private int rychlost = 2;
    private boolean jedeNahoru = false;

    ImageIcon obrazek = new ImageIcon(getClass().getResource("/vytah.png"));
    Image zmenseny = obrazek.getImage().getScaledInstance(110, 100, Image.SCALE_SMOOTH);

    public Vytah(int poziceX, int poziceY) {
        this.setBounds(poziceX, poziceY, 100, 100);
        this.setIcon(new ImageIcon(zmenseny));
    }

    public void posun(){
        int x =  this.getX();
        int y = this.getY();
        if (jedeNahoru){
            this.setLocation(x,y- rychlost);
            if (this.getY() <= 310){
                jedeNahoru = false;
            }
        } else {
            this.setLocation(x,y + rychlost);
            if (this.getY() >= 915){
                jedeNahoru = true;
            }
        }
    }





}
