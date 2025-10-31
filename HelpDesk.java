package PagIbig;

import java.util.Scanner;

public class HelpDesk {
    private final String name;
    private final QueueManager qm = QueueManager.getInstance();

    public HelpDesk(String name) {
        this.name = name;
    }

    public void serveNext() {
        int serving = qm.getCurrentNumber();
        System.out.println(name + " is now serving queue # " + serving);
    }

    public void resetQueue(int newNumber) {
        qm.reset(newNumber);
    }

    public void runMenu(Scanner sc) {
        while (true) {
            System.out.println("\n--- " + name + " ---");
            System.out.println("1) Serve next client");
            System.out.println("2) Reset queue number");
            System.out.println("3) Exit this desk");
            System.out.print("Choose: ");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    serveNext();
                    break;
                case "2":
                    System.out.print("Enter new queue number: ");
                    try {
                        int n = Integer.parseInt(sc.nextLine());
                        resetQueue(n);
                    } catch (NumberFormatException e) {
                        System.out.println("Invalid number!");
                    }
                    break;
                case "3":
                    System.out.println(name + " shutting down.");
                    return;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}