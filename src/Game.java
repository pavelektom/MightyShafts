import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class Game {

    private JFrame frame;
    private Image pozadi;

    public Game(){
        frame = new JFrame();
    }
    public void turnOn(){
        frame.setExtendedState(JFrame.MAXIMIZED_BOTH);
        frame.setSize(1000,1000);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        frame.setVisible(true);
    }
}
