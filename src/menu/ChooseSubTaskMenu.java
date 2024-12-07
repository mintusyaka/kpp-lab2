package menu;

import item.Project;
import item.Task;

import java.util.Scanner;

public class ChooseSubTaskMenu implements Menu{
    public Menu prevMenu;
    public Task parentTask;

    ChooseSubTaskMenu(Menu prevMenu, Task parentTask) {
        this.prevMenu = prevMenu;
        this.parentTask = parentTask;
    }

    public Menu display() {
        for(int i = 0; i < 50; ++i)
            System.out.print("=");
        System.out.print("\nAll Tasks:\n");
        for(int i = 0; i < 50; ++i)
            System.out.print("=");
        System.out.println();

        if(parentTask.getSubTasks().size() == 0) {
            System.out.println(ManagementSystemMenu.SYSTEM_MSG + "There are no tasks in the Project!");
            return prevMenu;
        }

        int i = 1;
        for(Task task: parentTask.getSubTasks())
        {
            try {
                System.out.println(i++ + ". " + task.getName()
                        + "\n\tPriority: " + task.getPriority()
                        + "\n\tStatus: " + task.getStatus()
                        + "\n\tStarted: " + task.getStartTime().toString()
                        + "\n\tDeadline: " + task.getDeadline().toString());
            } catch (Exception e) {
                e.printStackTrace();
            }
            System.out.println();
        }

        System.out.println();
        System.out.println("Choose subtask number: ");


        Scanner s = new Scanner(System.in);
        int choice = s.nextInt();

        if(choice <= parentTask.getSubTasks().size() && choice > 0)
        {
            return new EditTaskMenu(prevMenu, parentTask, parentTask.getSubTasks().get(choice - 1));
        }
        else {
            System.out.println("\n\n" + Menu.SYSTEM_MSG + "Invalid task number!\n");
        }
        return this;
    }
}
