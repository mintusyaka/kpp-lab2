package item;

import java.time.*;
import java.util.ArrayList;

public class Project {
    private String name;
    private ArrayList<Task> tasks;
    private LocalDateTime created;
    private LocalDateTime startTime;
    private LocalDateTime deadline;

    public Project(String name) {
        this.name = name;
        this.tasks = new ArrayList<Task>();
        this.created = LocalDateTime.now();
        this.startTime = created;
        this.deadline = created.plusDays(1);
    }

    //TASKS
    public void addTask(Task task) {
        if(task != null)
            tasks.add(task);
    }
    public void removeTask(Task task) {
        tasks.remove(task);
    }
    public ArrayList<Task> getTasks() {
        return tasks;
    }

    //START TIME
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public LocalDateTime getStartTime() { return startTime; }

    //DEADLINES
    public boolean setDeadline(LocalDateTime deadline) {
        if(deadline.isAfter(created)) {
            this.deadline = deadline;
            return true;
        }
        return false;
    }
    public LocalDateTime getDeadline() { return deadline; }

    //CREATED TIME
    public LocalDateTime getCreated() {
        return created;
    }

    //NAME
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

}
