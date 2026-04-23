public class Main {
    public static void main(String[] args) {

        Music m = new Music("resources/Below_the_Bedrock.wav");
        Thread abc = new Thread(m);
        abc.setPriority(Thread.MIN_PRIORITY);
        abc.start();
        m.run();
        new MainMenu().zapnout();
    }
}
