package menu;

import item.Project;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class CreateProjectMenu implements Menu {
    private Menu prevMenu;
    private Project project;

    public CreateProjectMenu(Menu prevMenu) {
        this.prevMenu = prevMenu;
    }

    public Menu display(){
        for(int i = 0; i < 50; ++i)
            System.out.print("=");
        System.out.print("\nCreate Project Menu:\n");
        for(int i = 0; i < 50; ++i)
            System.out.print("=");
        System.out.println();

        System.out.println("Enter Project Name or write 'EXIT' to exit':");

        Scanner s = new Scanner(System.in);
        String enteredString = s.nextLine();

        switch(enteredString) {
            case "EXIT":
                return new ExitMenu(this, prevMenu);
            default:
                project = new Project(enteredString);
                return displayEnterStartTime();
        }
    }

    public Menu displayEnterStartTime()
    {
        System.out.println("Enter Project start time in format 'DD-MM-YYYY HH:mm' or write 'EXIT' to exit':");

        Scanner s = new Scanner(System.in);
        String enteredString = s.nextLine();

        switch(enteredString) {
            case "EXIT":
                return new ExitMenu(this, prevMenu);
            default:
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
              try
              {
                  project.setStartTime(LocalDateTime.parse(enteredString, formatter));
                  return displayEnterDeadline();
              } catch (DateTimeParseException e)
              {
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
        System.out.println("Enter Project deadline in format 'DD-MM-YYYY HH:mm' or write 'EXIT' to exit':");

        Scanner s = new Scanner(System.in);
        String enteredString = s.nextLine();

        switch(enteredString) {
            case "EXIT":
                return new ExitMenu(this, prevMenu);
            default:
                DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
                try {
                    if (project.setDeadline(LocalDateTime.parse(enteredString, formatter))) {
                        ManagementSystemMenu.managementSystem.addProject(project);
                        return new ManagementProjectMenu(prevMenu, project);
                    } else {
                        System.out.println("\n\n" + Menu.SYSTEM_MSG + "Invalid deadline value!\n");
                        return displayEnterDeadline();
                    }
                } catch (DateTimeParseException e) {
                    e.printStackTrace();
                }
                return this;
        }

    }
}
