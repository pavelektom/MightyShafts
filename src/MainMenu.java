import javax.swing.*;
import java.awt.*;

public class MainMenu {

    private JFrame frame;


    public MainMenu() {
        frame = new JFrame();
    }

    public void zapnout(){

        JPanel panel = new JPanel();
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setSize(800,800);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setForeground(Color.BLACK);
        frame.setBackground(Color.BLACK);

        JButton ukoncit = new JButton(" Exit ");   /* Tady mame tlacitko ukoncit, pokud bude stisknuto, hra se vypne. */
        ukoncit.setBackground(Color.RED);
        ukoncit.setForeground(Color.BLACK);
        ukoncit.setFont(new Font("Arial", Font.BOLD, 50));
        ukoncit.setFocusPainted(false);
        ukoncit.setPreferredSize(new Dimension(350, 80));
        ukoncit.setMaximumSize(new Dimension(350, 80));
        ukoncit.setAlignmentX(Component.CENTER_ALIGNMENT);

        ukoncit.addActionListener(e->{
            frame.dispose();
            System.exit(0);
        });

        JButton playBtn = new JButton(" Continue ");
        playBtn.setFont(new Font("Arial", Font.BOLD, 50));
        playBtn.setBackground(new Color(55, 152, 48));
        playBtn.setForeground(Color.BLACK);
        playBtn.setFocusPainted(false);
        playBtn.setPreferredSize(new Dimension(350, 80));
        playBtn.setMaximumSize(new Dimension(350, 80));
        playBtn.setAlignmentX(Component.CENTER_ALIGNMENT);
        playBtn.addActionListener(e -> {
            frame.dispose();
            new LoadingScreen().initialize();
        });

        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        JLabel title = new JLabel("Mighty shafts");
        title.setFont(new Font("Times new roman", Font.BOLD, 80));
        title.setForeground(new Color(255, 215, 0));
        title.setAlignmentX(Component.CENTER_ALIGNMENT);

        panel.setBackground(new Color(62, 23, 23, 163));

        panel.add(Box.createVerticalStrut(300));
        panel.add(title);
        panel.add(Box.createVerticalStrut(50));
        panel.add(playBtn);
        panel.add(Box.createVerticalStrut(50));
        panel.add(ukoncit);
        frame.add(panel);


        frame.setVisible(true);
    }

}
