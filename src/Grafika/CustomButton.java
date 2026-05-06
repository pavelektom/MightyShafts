package Grafika;

import javax.swing.*;
import java.awt.*;

public class CustomButton extends JButton {


    public CustomButton(String text) {
        super(text);
        this.setContentAreaFilled(false);
        this.setBorderPainted(false);
        this.setFocusPainted(false);
        this.setContentAreaFilled(false);
        this.setOpaque(false);

    }

    protected void paintComponent(Graphics g) {
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setColor(getBackground());
        g2.fillRoundRect(0, 0, getWidth(), getHeight(), 50, 50);
        super.paintComponent(g);
    }


}