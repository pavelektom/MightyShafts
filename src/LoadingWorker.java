import javax.swing.*;
import java.util.List;
import java.util.Random;

public class LoadingWorker extends SwingWorker<Void, int[]> {

    private JProgressBar bar;
    private JLabel status;
    private JFrame frame;


    private String[] progress = {"step 1", "step 2", "step 3", "step 4",  "step 5", "step 6", "step 7", "step 8"
            , "step 9", "step 10", "step 11", "step 12", "step 13",  "step 14", "step 15", "step 16",
            "step 17", "step 18", "step 19",  "step 20",  "step 21", "step 22", "step 23", "step 24",
            "step 25", "step 26", "step 27", "step 28", "step 29", "step 30", "step 31", "step 32", "step 33",
            "step 34", "step 35", "step 36", "step 37",  "step 38", "step 39", "step 40", "step 41",  "step 42",
            "step 43", "step 44", "step 45", "step 46", "step 47", "step 48", "step 49", "step 50"};

    public LoadingWorker(JProgressBar bar, JLabel status, JFrame frame) {
        this.bar = bar;
        this.status = status;
        this.frame = frame;
    }

    @Override
    protected Void doInBackground() throws Exception {
        for (int i = 0; i < progress.length; i++) {
            Random rd = new Random();
            int rdnum = rd.nextInt(50, 100);
            Thread.sleep(rdnum);
            publish(new int[]{(i+1)*2, i});
        }
        return null;
    }

    @Override
    protected void process(List<int[]> data) {
        int[] last = data.get(data.size()-1);
        bar.setValue(last[0]);
        status.setText(progress[1]);
    }

    @Override
    protected void done() {
        frame.dispose();
        new Game().turnOn();
    }
}
