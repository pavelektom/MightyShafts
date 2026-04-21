import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Settings {
    private JFrame frame;
    private Image pozadi;
    private MainMenu mainMenu;

    public Settings() {
        frame = new  JFrame(" Settings ");
    }

    public void zapnout(){
        frame.setSize(800,800);
        frame.setResizable(false);
        frame.setLocationRelativeTo(null);
        frame.setUndecorated(true);



        JButton zpet = new CustomButton("Go Back");
        zpet.setFont(new Font("Arial", Font.BOLD, 30));
        zpet.setBackground(new Color(179, 9, 15));
        zpet.setForeground(Color.BLACK);
        zpet.setFocusPainted(false);
        zpet.setPreferredSize(new Dimension(250, 60));
        zpet.setMaximumSize(new Dimension(250, 60));
        zpet.setAlignmentX(Component.CENTER_ALIGNMENT);

        zpet.addActionListener(e -> {
            mainMenu = new MainMenu();
            mainMenu.settings.setEnabled(true);
            frame.dispose();
            mainMenu.zapnout();
        });


        JPanel panel = new JPanel() {
            protected void paintComponent(Graphics g) {
                super.paintComponent(g);
                if (pozadi != null) {
                    g.drawImage(pozadi, 0, 0, getWidth(), getHeight(), this);
                    g.setColor(new Color(0, 0, 0, 150));
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
            pozadi = ImageIO.read(getClass().getResource("/ikonaNastaveni.jpg"));
        } catch (IOException e) {
            System.out.println("Nepodarilo se načíst obrázek");
        }

        panel.add(Box.createVerticalGlue());
        panel.add(zpet);
        frame.add(panel);



        frame.setVisible(true);
    }
}
