import javax.swing.*;

public class MainMenu {

    private JFrame frame;
    private JPanel panel;

    public MainMenu() {
        frame = new JFrame();
    }

    public void zapnout(){
        frame.setLocationRelativeTo(null);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

        JButton button = new JButton("Continue");
        button.setSize(200,300);


        frame.add(button);
        button.addActionListener(e ->{
            frame.dispose();
            new LoadingScreen().initialize();
        });

        frame.setVisible(true);
    }

}
