package Hra;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Delnik extends JLabel {

    private int rychlost;
    private boolean smer;

    public Delnik(int poziceX, int poziceY) {
        this.setOpaque(true);
        this.setBounds(poziceX, poziceY, 50, 50);
    }



    public void posun(){
        int x = this.getX();
        int y = this.getY();
        if (smer){
            this.setLocation(x + rychlost, y);
        }

    }




}
