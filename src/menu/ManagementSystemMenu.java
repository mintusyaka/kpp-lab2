package menu;

import item.ManagementSystem;
import java.util.Scanner;

public class ManagementSystemMenu implements Menu {
    static public ManagementSystem managementSystem;

    public ManagementSystemMenu(ManagementSystem managementSystem) {
        ManagementSystemMenu.managementSystem = managementSystem;
    }

    public Menu display() {
        for(int i = 0; i < 50; ++i)
            System.out.print("=");
        System.out.print("\nMenu:\n");
        for(int i = 0; i < 50; ++i)
            System.out.print("=");
        System.out.println();

        System.out.println("Choose option:");
        System.out.println("1. Create new project.");
        System.out.println("2. Work with existing project.");
        System.out.println("3. Save Project in File.");
        System.out.println("4. Exit.");
        System.out.println("\n\nAnswer: ");

        Scanner s = new Scanner(System.in);
        int choice = s.nextInt();

        switch(choice) {
            case 1:
                return new CreateProjectMenu(this);
            case 2:
                return new ChooseProjectMenu(this);
            case 3:
                return new ExitMenu(this);
            case 4:
                return new ExitMenu(this);
            default:
                System.out.println("\n\n" + Menu.SYSTEM_MSG + "Invalid choice\n");
                return this;
        }
    }
}

/*
public class CreateProjectMenu extends Menus.Menu
{

}*/
