package Hra;

import javax.swing.*;
import java.awt.*;


public class Delnik extends JLabel {

    private int rychlost = 3;
    private boolean doprava = true;

    public Delnik(int poziceX, int poziceY) {
        this.setBounds(poziceX, poziceY, 50, 50);
        this.setIcon(hotovej);
    }

    ImageIcon puvodni = new ImageIcon(getClass().getResource("/delnikDoprava.png"));
    Image obrazek1 = puvodni.getImage();
    Image zmensenyObrazek = obrazek1.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    ImageIcon hotovej = new ImageIcon(zmensenyObrazek);

    ImageIcon puvodni2 = new ImageIcon(getClass().getResource("/delnikDoleva.png"));
    Image obrazek2 = puvodni2.getImage();
    Image zmensenyObrazek2 = obrazek2.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    ImageIcon hotovej2 = new ImageIcon(zmensenyObrazek2);



    public void posun(){
        int x = this.getX();
        int y = this.getY();
        if (doprava){
            this.setLocation(x + rychlost, y);
            if (this.getX() >= 1120){
                this.setIcon(hotovej2);
                doprava = false;
            }
        } else {
            this.setLocation(x - rychlost, y);
            if (this.getX() <= 300){
                this.setIcon(hotovej);
                doprava = true;
            }
        }

    }




}
