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

    /** @return the current number (for display) */
    public synchronized int getCurrentNumber() {
        return currentNumber;
    }

    /** Increment and return the next number */
    public synchronized int nextNumber() {
        currentNumber++;
        System.out.println(">>> Next queue number issued: " + currentNumber);
        return currentNumber;
    }

    /** Reset to any number (used by help-desk staff) */
    public synchronized void reset(int newNumber) {
        if (newNumber < 0) newNumber = 0;
        this.currentNumber = newNumber;
        System.out.println(">>> Queue RESET to: " + currentNumber);
    }
}