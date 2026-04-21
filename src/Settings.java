import javax.swing.*;

public class Settings {
    private JFrame frame;

    public Settings() {
        frame = new  JFrame(" Settings ");
    }

    public void zapnout(){
        frame.setSize(500,500);
        frame.setResizable(false);

        frame.setVisible(true);
    }
}
