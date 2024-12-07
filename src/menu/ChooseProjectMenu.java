package menu;

import item.ManagementSystem;
import item.Project;
import item.Task;

import java.util.Scanner;

public class ChooseProjectMenu implements Menu {
    private Menu prevMenu;

    public ChooseProjectMenu(Menu prevMenu) {
        this.prevMenu = prevMenu;
    }

    public Menu display() {
        for(int i = 0; i < 50; ++i)
            System.out.print("=");
        System.out.print("\nAll Projects:\n");
        for(int i = 0; i < 50; ++i)
            System.out.print("=");
        System.out.println();

        if(ManagementSystemMenu.managementSystem.getProjects().size() == 0) {
            System.out.println(ManagementSystemMenu.SYSTEM_MSG + "There are no projects in the system!");
            return prevMenu;
        }

        int i = 1;
        for(Project project: ManagementSystemMenu.managementSystem.getProjects())
        {
            try {
                System.out.println(i++ + ". " + project.getName()
                        + "\n\tStarted: " + project.getStartTime().toString()
                        + "\n\tDeadline: " + project.getDeadline().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("Choose project number: ");


        Scanner s = new Scanner(System.in);
        int choice = s.nextInt();

        if(choice <= ManagementSystemMenu.managementSystem.getProjects().size() && choice > 0)
        {
            return new ManagementProjectMenu(
                    prevMenu,
                    ManagementSystemMenu.managementSystem.getProjects().get(choice-1)
            );
        }
        else {
            System.out.println("\n\n" + Menu.SYSTEM_MSG + "Invalid task number!\n");
        }
        return this;
    }
}
