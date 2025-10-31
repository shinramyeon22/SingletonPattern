package PagIbig;
public class QueueDisplay extends Thread {
    private final QueueManager qm = QueueManager.getInstance();
    private volatile boolean running = true;

    @Override
    public void run() {
        System.out.println("*** Online Queue Monitor STARTED ***");
        int last = -1;
        while (running) {
            int cur = qm.getCurrentNumber();
            if (cur != last) {
                System.out.println("\n[REMOTE DISPLAY] Current queue: " + cur);
                last = cur;
            }
            try { Thread.sleep(800); } catch (InterruptedException ignored) {}
        }
    }

    public void stopMonitoring() {
        running = false;
        interrupt();
    }
}