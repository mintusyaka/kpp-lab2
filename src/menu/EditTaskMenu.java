package menu;

import item.Priority;
import item.Project;
import item.Status;
import item.Task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class EditTaskMenu implements Menu {
    private Menu prevMenu;
    private Project project;
    private Task task;
    private Task parentTask;

    public EditTaskMenu(Menu prevMenu, Project project, Task task) {
        this.prevMenu = prevMenu;
        this.project = project;
        this.task = task;
        this.parentTask = null;
    }

    public EditTaskMenu(Menu prevMenu, Task parentTask, Task task) {
        this.prevMenu = prevMenu;
        this.project = null;
        this.task = task;
        this.parentTask = parentTask;
    }

    public Menu display() {
        for(int i = 0; i < 50; ++i)
            System.out.print("=");
        System.out.print("\nEdit Task Menu:\n");
        for(int i = 0; i < 50; ++i)
            System.out.print("=");
        System.out.println();

        System.out.println("Choose option:");
        System.out.println("1. Change name.");
        System.out.println("2. Change priority.");
        System.out.println("3. Change status.");
        System.out.println("4. Change deadline.");
        System.out.println("5. Add subtask.");
        System.out.println("6. Edit subtask.");
        System.out.println("7. Add dependency on task.");
        System.out.println("8. Exit.");
        System.out.println("\n\nAnswer: ");

        Scanner s = new Scanner(System.in);
        int choice = s.nextInt();

        switch(choice) {
            case 1:

                System.out.println("Enter Task Name: ");

                Scanner s2 = new Scanner(System.in);
                String enteredString = s2.nextLine();

                task.setName(enteredString);
                return this;

            case 2:
                System.out.println("Enter Priority (0 - Lowest, 2 - Highest): ");

                Scanner s3 = new Scanner(System.in);
                int priority = s3.nextInt();

                if(priority < 0 || priority > 2)
                {
                    System.out.println("\n\n" + Menu.SYSTEM_MSG + "Invalid priority value\n");
                }
                else {
                    if(priority == 0)
                        task.setPriority(Priority.Low);
                    else if(priority == 1)
                        task.setPriority(Priority.Medium);
                    else
                        task.setPriority(Priority.High);
                }
                return this;

            case 3:
                System.out.println("Enter Status (0 - To Do, 1 - In Progress, 2 - Completed, 3 - Failed): ");

                Scanner s4 = new Scanner(System.in);
                int status = s4.nextInt();

                if(status < 0 || status > 3)
                {
                    System.out.println("\n\n" + Menu.SYSTEM_MSG + "Invalid status value\n");
                }
                else {
                    if(status == 0)
                        task.setStatus(Status.ToDo);
                    else if(status == 1)
                        task.setStatus(Status.InProgress);
                    else if(status == 2)
                        task.setStatus(Status.Completed);
                    else
                        task.setStatus(Status.Failed);
                }
                return this;
            case 4:
                System.out.println("Enter Task deadline in format 'DD-MM-YYYY HH:mm': ");

                Scanner s5 = new Scanner(System.in);
                String deadlineString = s5.nextLine();


                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                try {
                    LocalDateTime ldt = LocalDateTime.parse(deadlineString, formatter);
                    if(parentTask != null) {
                        if(parentTask.getStartTime().isBefore(ldt) || parentTask.getStartTime().equals(ldt)
                                && parentTask.getDeadline().isAfter(ldt))
                        {
                            if (!task.setDeadline(ldt)) {
                                System.out.println("\n\n" + Menu.SYSTEM_MSG + "Invalid deadline value!\n");
                            }
                        }
                        else
                            return this;
                    }
                    else {
                        if(project.getStartTime().isBefore(ldt) || project.getStartTime().equals(ldt)
                                && project.getDeadline().isAfter(ldt))
                        {
                            if (!task.setDeadline(ldt)) {
                                System.out.println("\n\n" + Menu.SYSTEM_MSG + "Invalid deadline value!\n");
                            }
                        }
                        else
                            return this;
                    }

                } catch (DateTimeParseException e) {
                    e.printStackTrace();
                }
                return this;

            case 5:
                return new CreateTaskMenu(this, task);

            case 6:
                return new ChooseSubTaskMenu(this, task);

            case 7:
                System.out.println("Enter Task number to create dependency on: ");

                Scanner s6 = new Scanner(System.in);
                int number = s.nextInt();

                if(parentTask == null) {
                    if (number <= project.getTasks().size() && number > 0) {
                        task.setDependOn(project.getTasks().get(number - 1));
                    } else {
                        System.out.println("\n\n" + Menu.SYSTEM_MSG + "Invalid task number!\n");
                    }
                }
                else {
                    if (number <= parentTask.getSubTasks().size() && number > 0) {
                        task.setDependOn(parentTask.getSubTasks().get(number - 1));
                    } else {
                        System.out.println("\n\n" + Menu.SYSTEM_MSG + "Invalid task number!\n");
                    }
                }
                return this;

            case 8:
                return new ExitMenu(this, prevMenu);

            default:
                System.out.println("\n\n" + Menu.SYSTEM_MSG + "Invalid choice\n");
                return this;
        }
    }
}
