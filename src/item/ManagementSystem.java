package item;

import java.util.ArrayList;
import java.util.TimeZone;

public class ManagementSystem {
    private ArrayList<Project> projects;

    public ManagementSystem() {
        projects = new ArrayList<Project>();
    }

    public void addProject(Project project) {
        projects.add(project);
    }
    public void removeProject(Project project) {
        projects.remove(project);
    }
    public ArrayList<Project> getProjects() {
        return projects;
    }
}