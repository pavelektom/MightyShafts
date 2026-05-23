package Hra;

import javax.swing.*;
import java.awt.*;

public class Skladnik extends JLabel {

    private int rychlost = 3;
    private boolean doprava = false;
    public Skladnik(int poziceX, int poziceY) {
        this.setBounds(poziceX, poziceY, 75, 75);
        this.setIcon(hotovej2);
    }

    ImageIcon puvodni = new ImageIcon(getClass().getResource("/skladnikDoprava.png"));
    Image obrazek1 = puvodni.getImage();
    Image zmensenyObrazek = obrazek1.getScaledInstance(85, 75, Image.SCALE_SMOOTH);
    ImageIcon hotovej = new ImageIcon(zmensenyObrazek);

    ImageIcon puvodni2 = new ImageIcon(getClass().getResource("/skladnikDoleva.png"));
    Image obrazek2 = puvodni2.getImage();
    Image zmensenyObrazek2 = obrazek2.getScaledInstance(85, 65, Image.SCALE_SMOOTH);
    ImageIcon hotovej2 = new ImageIcon(zmensenyObrazek2);


    public void posun() {
        int x = this.getX();
        int y = this.getY();
        if (doprava) {
            this.setLocation(x + rychlost, y);
            if (this.getX() >= 1160) {
                this.setIcon(hotovej2);
                doprava = false;
            }
        } else {
            this.setLocation(x - rychlost, y);
            if (this.getX() <= 315) {
                this.setIcon(hotovej);
                doprava = true;
            }
        }
    }
}
