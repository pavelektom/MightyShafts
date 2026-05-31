package Loading;

import javax.swing.*;
import java.awt.*;
/**
 * Třída loadingScreen je pro načítací bar po stisknutí tlačítka pokračovat v MainMenu
 * */
public class LoadingScreen {

    private JFrame frame;
    public LoadingScreen() {
        frame = new JFrame("Loadinggg");
    }

    /** V metodě initialize je všechna logika pro loading screen */
    public void initialize() {
        /** normální nastavování velikosti atd atd*/
        frame.setLayout(new BorderLayout());
        frame.setSize(400,100);
        frame.setLocationRelativeTo(null);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JLabel label = new JLabel("Pokrok");

        JProgressBar bar = new JProgressBar();
        bar.setStringPainted(true);

        JPanel panel = new JPanel();
        panel.setLayout(new BorderLayout());
        panel.add(bar,BorderLayout.CENTER);



        frame.add(label);
        frame.add(panel);
        frame.setVisible(true);

        /** Funguje to vlastně přes LoadingWorker*/
        LoadingWorker worker = new LoadingWorker(bar, label, frame);
        worker.execute();

    }
}
