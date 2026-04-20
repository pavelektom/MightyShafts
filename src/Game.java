import javax.swing.*;

public class Game {

    private JFrame frame;

    public Game(){
        frame = new JFrame();
    }
    public void turnOn(){
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }
}
