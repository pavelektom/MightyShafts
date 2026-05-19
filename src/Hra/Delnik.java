package Hra;

import javax.swing.*;
import java.awt.*;


public class Delnik extends JLabel {

    private int rychlost = 3;
    private boolean doprava = true;

    public Delnik(int poziceX, int poziceY) {
        this.setOpaque(true);
        this.setBackground(Color.YELLOW);
        this.setBounds(poziceX, poziceY, 50, 50);
    }

    public void posun(){
        int x = this.getX();
        int y = this.getY();
        if (doprava){
            this.setLocation(x + rychlost, y);
            if (this.getX() >= 1100){
                doprava = false;
            }
        } else {
            this.setLocation(x - rychlost, y);
            if (this.getX() <= 300){
                doprava = true;
            }
        }

    }




}
