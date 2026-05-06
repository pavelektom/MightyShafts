package Hra;

import javax.swing.*;
import java.awt.*;
import Grafika.CustomButton;

public class Game {

    private JFrame frame;
    private Image pozadi;

    public Game(){
        frame = new JFrame();
    }
    public void turnOn(){
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setUndecorated(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 35, 20));
        panel.setBackground(new Color(163, 157, 108));

        panel.setPreferredSize(new Dimension(0, 140));

        JButton exit = new CustomButton("Back ↩");
        exit.setBackground(new Color(179, 9, 15, 220));
        exit.setFont(new Font("Segoe UI emoji", Font.BOLD, 30));
        exit.setPreferredSize(new Dimension(175, 70));
        exit.setMaximumSize(new Dimension(175, 70));
        exit.addActionListener(e->{
            frame.dispose();
            new MainMenu().zapnout();
        });
        panel.add(exit);
        frame.add(panel, BorderLayout.NORTH);

        frame.setVisible(true);
    }
}
