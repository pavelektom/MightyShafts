package Loading;

import javax.swing.*;
import java.awt.*;

public class LoadingScreen {

    private JFrame frame;
    public LoadingScreen() {
        frame = new JFrame("Loadinggg");
    }
    public void initialize() {
        frame.setLayout(new BorderLayout());
        frame.setSize(400,100);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);

        JLabel label = new JLabel("Pokrok");

        JProgressBar bar = new JProgressBar();
        bar.setStringPainted(true);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(bar,BorderLayout.CENTER);



        frame.add(label);
        frame.add(panel);
        frame.setVisible(true);

        LoadingWorker worker = new LoadingWorker(bar, label, frame);
        worker.execute();

    }
}
