package PagIbig;
public class QueueManager {
    private static QueueManager instance;
    private int currentNumber = 0;   // starts at 0, first call returns 1

    private QueueManager() {}

    public static synchronized QueueManager getInstance() {
        if (instance == null) {
            instance = new QueueManager();
        }
        return instance;
    }

    public synchronized int getCurrentNumber() {
        return currentNumber;
    }

    public synchronized int nextNumber() {
        currentNumber++;
        System.out.println(">>> Next queue number issued: " + currentNumber);
        return currentNumber;
    }

    public synchronized void reset(int newNumber) {
        if (newNumber < 0) newNumber = 0;
        this.currentNumber = newNumber;
        System.out.println(">>> Queue RESET to: " + currentNumber);
    }
}