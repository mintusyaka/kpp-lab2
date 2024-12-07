package menu;

import java.util.Scanner;

public class ExitMenu implements Menu {
    private Menu prevMenu;
    private Menu whereToExit;

    public ExitMenu(Menu prevMenu) {
        this.prevMenu = prevMenu;
        whereToExit = null;
    }

    public ExitMenu(Menu prevMenu, Menu whereToExit) {
        this.prevMenu = prevMenu;
        this.whereToExit = whereToExit;
    }

    public Menu display(){
        for(int i = 0; i < 50; ++i)
            System.out.print("=");
        System.out.print("\nExit Menu:\n");
        for (int i = 0; i < 50; ++i)
            System.out.print("=");
        System.out.println();

        System.out.println("Are you sure?");
        System.out.println("1. Yes.");
        System.out.println("2. No.");
        System.out.println("\n\nAnswer: ");

        Scanner s = new Scanner(System.in);
        int choice = s.nextInt();

        switch (choice) {
            case 1:
                return whereToExit;
            case 2:
                return prevMenu;
            default:
                System.out.println("\n\n" + Menu.SYSTEM_MSG + "Invalid choice\n");
                return this;
        }

    }
}
