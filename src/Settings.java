import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Settings {
     JFrame frame;
    private Image pozadi;
    private MainMenu mainMenu;
    private String hudba = Music.ahoj() ? "Music: ON" : "Music: OFF";



    public Settings() {
        frame = new  JFrame(" Settings ");
    }

    public void zapnout(){
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JButton hudbicka = new CustomButton("");
        hudbicka.setFont(new Font("Arial", Font.BOLD, 30));
        if (Music.ahoj()) {
            hudbicka.setText("Music:  ON");
            hudbicka.setBackground(new Color(22, 204, 75, 220));
        }else {
            hudbicka.setText("Music: OFF");
            hudbicka.setBackground(new Color(253, 41, 45,125));
        }
        hudbicka.setForeground(Color.BLACK);
        hudbicka.setFocusPainted(false);
        hudbicka.setPreferredSize(new Dimension(250, 60));
        hudbicka.setMaximumSize(new Dimension(250, 60));
        hudbicka.setAlignmentX(Component.CENTER_ALIGNMENT);
        hudbicka.addActionListener(e -> {
            Music.zapnoutVypnout();
            if (Music.ahoj()) {
                hudbicka.setText("Music:  ON");
                hudbicka.setBackground(new Color(22, 204, 75, 220));
            }else {
                hudbicka.setText("Music: OFF");
                hudbicka.setBackground(new Color(253, 41, 45,125));

            }
        });

        JButton zpet = new CustomButton("Go Back");
        zpet.setFont(new Font("Arial", Font.BOLD, 30));
        zpet.setBackground(new Color(253, 41, 45,125));
        zpet.setForeground(Color.BLACK);
        zpet.setFocusPainted(false);
        zpet.setPreferredSize(new Dimension(250, 60));
        zpet.setMaximumSize(new Dimension(250, 60));
        zpet.setAlignmentX(Component.CENTER_ALIGNMENT);

        zpet.addActionListener(e -> {
            frame.dispose();

        });


        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (pozadi != null) {
                    g.drawImage(pozadi, 0, 0, getWidth(), getHeight(), this);
                    g.setColor(new Color(0, 0, 0, 10));
                    g.fillRect(0, 0, getWidth(), getHeight());
                } else {
                    g.setColor(Color.BLACK);
                    System.out.println("nevidim fotku");
                }
            }
        };
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);

        try {
            pozadi = ImageIO.read(getClass().getResource("/nastaveni_fixed.jpg"));
        } catch (IOException e) {
            System.out.println("Nepodarilo se načíst obrázek");
        }

        panel.add(Box.createVerticalStrut(615));
        panel.add(hudbicka);
        panel.add(Box.createVerticalStrut(30));
        panel.add(zpet);
        frame.add(panel);


        frame.setVisible(true);
    }
}