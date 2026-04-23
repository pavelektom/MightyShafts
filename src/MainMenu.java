import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class MainMenu {

    JFrame frame;
    private Image obrazekPozadi;
    Settings s = new Settings();
    JButton settings = new CustomButton(" Settings ");


    public MainMenu() {
        frame = new JFrame();
    }

    public void zapnout(){

        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(800,800);
        frame.setUndecorated(true);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setForeground(Color.BLACK);
        frame.setBackground(Color.BLACK);

        try {
            obrazekPozadi = ImageIO.read(getClass().getResource("/pozadi1.jpg"));
        } catch (IOException e) {
            System.out.println("Nepodarilo se načíst obrázek");
        }

        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (obrazekPozadi != null) {
                    g.drawImage(obrazekPozadi, 0, 0, getWidth(), getHeight(), this);
                    g.setColor(new Color(0, 0, 0,15));
                    g.fillRect(0, 0, getWidth(), getHeight());
                } else {
                    g.setColor(Color.BLACK);
                    System.out.println("nevidim fotku");
                }
            }
        };
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
        panel.setOpaque(false);


        JButton playBtn = new CustomButton(" Continue ");
        playBtn.setFont(new Font("Arial", Font.BOLD, 50));
        playBtn.setBackground(new Color(22, 204, 75, 220));
        playBtn.setForeground(Color.BLACK);
        playBtn.setFocusPainted(false);
        playBtn.setPreferredSize(new Dimension(350, 80));
        playBtn.setMaximumSize(new Dimension(350, 80));
        playBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        playBtn.addActionListener(e -> {
            frame.dispose();
            new LoadingScreen().initialize();
        });

        JButton ukoncit = new CustomButton(" Exit ");
        ukoncit.setBackground(new Color(179, 9, 15, 220));
        ukoncit.setForeground(Color.BLACK);
        ukoncit.setFont(new Font("Arial", Font.BOLD, 50));
        ukoncit.setFocusPainted(false);
        ukoncit.setPreferredSize(new Dimension(350, 80));
        ukoncit.setMaximumSize(new Dimension(350, 80));
        ukoncit.setAlignmentX(Component.CENTER_ALIGNMENT);
        ukoncit.addActionListener(e->{
            frame.dispose();
        });


        settings.setFont(new Font("Arial", Font.BOLD, 50));
        settings.setBackground(new Color(143, 145, 145, 220));
        settings.setForeground(Color.BLACK);
        settings.setFocusPainted(false);
        settings.setPreferredSize(new Dimension(350, 80));
        settings.setMaximumSize(new Dimension(350, 80));
        settings.setAlignmentX(Component.CENTER_ALIGNMENT);
        settings.addActionListener(e -> {
                new Settings().zapnout();
        });


        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Mighty shafts");
        title.setFont(new Font("Times new roman", Font.BOLD, 100));
        title.setForeground(new Color(255, 215, 0));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.setBackground(new Color(62, 23, 23, 163));

        panel.add(Box.createVerticalStrut(300));
        panel.add(title);
        panel.add(Box.createVerticalStrut(50));
        panel.add(playBtn);
        panel.add(Box.createVerticalStrut(50));
        panel.add(settings);
        panel.add(Box.createVerticalStrut(50));
        panel.add(ukoncit);
        frame.add(panel);


        frame.setVisible(true);
    }

}
