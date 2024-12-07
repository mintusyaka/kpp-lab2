package menu;

import item.Project;
import item.Task;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Scanner;

public class ManagementProjectMenu implements Menu {
    private Menu prevMenu;
    private Project project;

    public ManagementProjectMenu(Menu prevMenu, Project project) {
        this.prevMenu = prevMenu;
        this.project = project;
    }

    public Menu display() {
        for(int i = 0; i < 50; ++i)
            System.out.print("=");
        System.out.print("\nProject Menu:\n");
        for(int i = 0; i < 50; ++i)
            System.out.print("=");
        System.out.println();

        System.out.println("Choose option:");
        System.out.println("1. Create new task.");
        System.out.println("2. Change existing task.");
        System.out.println("3. Get tasks with closest deadlines.");
        System.out.println("4. Remove project.");
        System.out.println("5. Exit.");
        System.out.println("\n\nAnswer: ");

        Scanner s = new Scanner(System.in);
        int choice = s.nextInt();

        switch(choice) {
            case 1:
                return new CreateTaskMenu(this, project);
            case 2:
                return new ChooseTaskMenu(this, project);
            case 3:
                getNearestDeadlineTasks();
                return this;

            case 4:
                ManagementSystemMenu.managementSystem.getProjects().remove(project);
                return prevMenu;
            case 5:
                return new ExitMenu(this, prevMenu);
            default:
                System.out.println("\n\n" + Menu.SYSTEM_MSG + "Invalid choice\n");
                return this;
        }
    }

    private void getNearestDeadlineTasks()
    {
        int i = 0;
        System.out.println("\nNearest Deadline:");
        for(Task task : project.getTasks())
        {
            i++;
            try {
                if(Duration.between(LocalDateTime.now(), task.getDeadline() ).toDays() <= 5)
                {
                    System.out.println(i++ + ". " + task.getName()
                            + "\n\tPriority: " + task.getPriority()
                            + "\n\tStatus: " + task.getStatus()
                            + "\n\tStarted: " + task.getStartTime().toString()
                            + "\n\tDeadline: " + task.getDeadline().toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        i = 0;
        System.out.println("\nFailed Tasks:");
        for(Task task : project.getTasks())
        {
            i++;
            try {
                if(LocalDateTime.now().isAfter(task.getDeadline()))
                {
                    System.out.println(i++ + ". " + task.getName()
                            + "\n\tPriority: " + task.getPriority()
                            + "\n\tStatus: " + task.getStatus()
                            + "\n\tStarted: " + task.getStartTime().toString()
                            + "\n\tDeadline: " + task.getDeadline().toString());
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
