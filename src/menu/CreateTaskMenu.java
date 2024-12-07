package menu;

import item.Priority;
import item.Task;
import item.Project;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class CreateTaskMenu implements Menu {
    private Menu prevMenu;
    private Task task;
    private Project project;
    private Task parentTask;

    public CreateTaskMenu(Menu prevMenu, Project project) {
        this.prevMenu = prevMenu;
        this.project = project;
        parentTask = null;
    }

    public CreateTaskMenu(Menu prevMenu, Task parentTask) {
        this.prevMenu = prevMenu;
        this.parentTask = parentTask;
    }

    public Menu display(){
        for(int i = 0; i < 50; ++i)
            System.out.print("=");
        System.out.print("\nCreate Task Menu:\n");
        for(int i = 0; i < 50; ++i)
            System.out.print("=");
        System.out.println();

        System.out.println("Enter Task Name or write 'EXIT' to exit':");

        Scanner s = new Scanner(System.in);
        String enteredString = s.nextLine();

        switch(enteredString) {
            case "EXIT":
                return new ExitMenu(this, prevMenu);
            default:
                task = new Task(enteredString);
                return displayEnterStartTime();
        }
    }

    public Menu displayEnterStartTime()
    {
        System.out.println("Enter Task start time in format 'DD-MM-YYYY HH:mm' or write 'EXIT' to exit':");

        Scanner s = new Scanner(System.in);
        String enteredString = s.nextLine();

        switch(enteredString) {
            case "EXIT":
                return new ExitMenu(this, prevMenu);
            default:
                try {
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                    LocalDateTime ldt = LocalDateTime.parse(enteredString, formatter);
                    if(parentTask != null)
                    {
                        if(parentTask.getStartTime().isBefore(ldt) || parentTask.getStartTime().equals(ldt)
                        && parentTask.getDeadline().isAfter(ldt))
                        {
                            task.setStartTime(ldt);
                        }
                        else
                            return this;
                    }
                    else {
                        if(project.getStartTime().isBefore(ldt) || project.getStartTime().equals(ldt)
                                && project.getDeadline().isAfter(ldt))
                        {
                            task.setStartTime(ldt);
                        }
                        else
                            return this;
                    }
                    return displayEnterDeadline();
                } catch (DateTimeParseException e) {
                    e.printStackTrace();
                }
                return this;
        }

/*        String str = "1986-04-08 12:30";
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.parse(str, formatter);*/

/*        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm");
        LocalDateTime dateTime = LocalDateTime.of(1986, Month.APRIL, 8, 12, 30);
        String formattedDateTime = dateTime.format(formatter); // "1986-04-08 12:30"*/
    }

    public Menu displayEnterDeadline()
    {
        System.out.println("Enter Task deadline in format 'DD-MM-YYYY HH:mm' or write 'EXIT' to exit':");

        Scanner s = new Scanner(System.in);
        String enteredString = s.nextLine();

        switch(enteredString) {
            case "EXIT":
                return new ExitMenu(this, prevMenu);
            default:
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                try {
                    LocalDateTime ldt = LocalDateTime.parse(enteredString, formatter);
                    if(parentTask != null)
                    {
                        if((parentTask.getStartTime().isBefore(ldt) || parentTask.getStartTime().equals(ldt))
                        && parentTask.getDeadline().isAfter(ldt))
                        {
                            if (task.setDeadline(ldt)) {
                                return displayEnterPriority();
                            } else {
                                System.out.println("\n\n" + Menu.SYSTEM_MSG + "Invalid deadline value!\n");
                                return displayEnterDeadline();
                            }
                        }
                        else
                        {
                            System.out.println("\n\n" + Menu.SYSTEM_MSG + "Invalid deadline value!\n");
                            return displayEnterDeadline();
                        }

                    }
                    else {
                        if((project.getStartTime().isBefore(ldt) || project.getStartTime().equals(ldt))
                                && project.getDeadline().isAfter(ldt))
                        {
                            if (task.setDeadline(ldt)) {
                                return displayEnterPriority();
                            } else {
                                System.out.println("\n\n" + Menu.SYSTEM_MSG + "Invalid deadline value!\n");
                                return displayEnterDeadline();
                            }
                        }
                        else
                        {
                            System.out.println("\n\n" + Menu.SYSTEM_MSG + "Invalid deadline value!\n");
                            return displayEnterDeadline();
                        }
                    }


                }
                catch (DateTimeParseException e) {
                    e.printStackTrace();
                }
                return this;
        }

    }

    public Menu displayEnterPriority()
    {
        System.out.println("Enter Task priority ('L' - Low, 'M' - Medium, 'H' - High) or write 'EXIT' to exit':");

        Scanner s = new Scanner(System.in);
        String enteredString = s.nextLine();

        switch(enteredString) {
            case "EXIT":
                return new ExitMenu(this, prevMenu);
            case "L":
                task.setPriority(Priority.Low);
                if(parentTask == null)
                    project.addTask(task);
                else
                    parentTask.addSubTask(task);
                return prevMenu;
            case "M":
                task.setPriority(Priority.Medium);
                if(parentTask == null)
                    project.addTask(task);
                else
                    parentTask.addSubTask(task);
                return prevMenu;
            case "H":
                task.setPriority(Priority.High);
                if(parentTask == null)
                    project.addTask(task);
                else
                    parentTask.addSubTask(task);
                return prevMenu;

            default:
                System.out.println("\n\n" + Menu.SYSTEM_MSG + "Invalid priority value\n");
                return displayEnterPriority();
        }

    }
}
