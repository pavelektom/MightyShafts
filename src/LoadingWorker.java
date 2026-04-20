import javax.swing.*;
import java.util.List;
import java.util.Random;

public class LoadingWorker extends SwingWorker<Void, int[]> {

    private JProgressBar bar;
    private JLabel status;
    private JFrame frame;


    private String[] progress = {"step 1", "step 2", "step 3", "step 4",  "step 5", "step 6", "step 7", "step 8"
            , "step 9", "step 10", "step 11", "step 12", "step 13",  "step 14", "step 15", "step 16",
            "step 17", "step 18", "step 19",  "step 20"};

    public LoadingWorker(JProgressBar bar, JLabel status, JFrame frame) {
        this.bar = bar;
        this.status = status;
        this.frame = frame;
    }

    @Override
    protected Void doInBackground() throws Exception {
        for (int i = 0; i < progress.length; i++) {
            Random rd = new Random();
            int rdnum = rd.nextInt(100, 500);
            Thread.sleep(rdnum);
            publish(new int[]{(i+1)*5, i});
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
