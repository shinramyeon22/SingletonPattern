package PagIbig;
import java.util.Scanner;

public class Application {
    public static void main(String[] args) {
        QueueManager qm = QueueManager.getInstance();

        QueueDisplay monitor = new QueueDisplay();
        monitor.start();
        
        HelpDesk desk1 = new HelpDesk("Help Desk 1");
        HelpDesk desk2 = new HelpDesk("Help Desk 2");
        HelpDesk desk3 = new HelpDesk("Help Desk 3");

        Scanner clientScanner = new Scanner(System.in);

        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("        CLIENT TICKET MACHINE");
        System.out.println("╚══════════════════════════════════╝\n");
        while (true) {
            System.out.print("Press ENTER to get a queue number (or type 'q' to quit): ");
            String line = clientScanner.nextLine();
            if (line.equalsIgnoreCase("q")) break;
            qm.nextNumber();
        }



        System.out.println("\n╔══════════════════════════════════╗");
        System.out.println("            STAFF - DESK");
        System.out.println("╚══════════════════════════════════╝\n");

        Thread t1 = new Thread(() -> {
            Scanner sc = new Scanner(System.in);
            desk1.runMenu(sc);

        }, "Desk1-Thread");

        Thread t2 = new Thread(() -> {
            Scanner sc = new Scanner(System.in);
            desk2.runMenu(sc);
        }, "Desk2-Thread");

        Thread t3 = new Thread(() -> {
            Scanner sc = new Scanner(System.in);
            desk3.runMenu(sc);
        }, "Desk3-Thread");

        t1.start(); t2.start(); t3.start();

        try {
            t1.join();
            t2.join();
            t3.join();
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }

        monitor.stopMonitoring();
        System.out.println("System shutdown. Goodbye!");

        clientScanner.close(); 
    }

}
