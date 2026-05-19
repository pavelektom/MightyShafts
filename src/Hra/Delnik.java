package Hra;

import javax.swing.*;
import java.awt.*;


public class Delnik extends JLabel {

    private int rychlost = 3;
    private boolean doprava = true;

    public Delnik(int poziceX, int poziceY) {
        this.setOpaque(false);
        this.setBounds(poziceX, poziceY, 50, 50);
        this.setIcon(hotovaIkona);
    }

    ImageIcon puvodniIkona = new ImageIcon(getClass().getResource("/delnikDoprava.png"));
    Image surovyObrazek = puvodniIkona.getImage();
    Image zmensenyObrazek = surovyObrazek.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    ImageIcon hotovaIkona = new ImageIcon(zmensenyObrazek);

    ImageIcon puvodniIkona2 = new ImageIcon(getClass().getResource("/delnikDoleva.png"));
    Image surovyObrazek2 = puvodniIkona2.getImage();
    Image zmensenyObrazek2 = surovyObrazek2.getScaledInstance(50, 50, Image.SCALE_SMOOTH);
    ImageIcon hotovaIkona2 = new ImageIcon(zmensenyObrazek2);



    public void posun(){
        int x = this.getX();
        int y = this.getY();
        if (doprava){
            this.setLocation(x + rychlost, y);
            if (this.getX() >= 1120){
                this.setIcon(hotovaIkona2);
                doprava = false;
            }
        } else {
            this.setLocation(x - rychlost, y);
            if (this.getX() <= 300){
                this.setIcon(hotovaIkona);
                doprava = true;
            }
        }

    }




}
