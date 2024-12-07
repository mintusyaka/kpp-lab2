package item;

import java.time.*;
import java.util.ArrayList;
import java.util.List;

public class Task {
    private String name;
    private ArrayList<Task> subtasks;
    private LocalDateTime created;
    private LocalDateTime startTime;
    private LocalDateTime deadline;
    private Priority priority;
    private Status status;
    private Task dependOn;

    public Task(String name) {
        this.name = name;
        subtasks = new ArrayList<Task>();
        created = LocalDateTime.now();
        startTime = created;
        deadline = LocalDateTime.now().plusDays(1);
        priority = Priority.Medium;
        status = Status.ToDo;
        dependOn = null;
    }
    public Task(String name, LocalDateTime startTime, LocalDateTime deadline, Priority priority) {
        this.name = name;
        this.startTime = startTime;
        this.deadline = deadline;
        this.priority = priority;
        subtasks = new ArrayList<>();
        created = LocalDateTime.now();
        status = Status.ToDo;
        dependOn = null;
    }

    // STATUS
    public void setStatus(Status status) {
        if(!status.equals(Status.Completed) || canBeCompleted())
        {
            try {
                if(dependOn != null)
                    if (status.equals(Status.InProgress) && !dependOn.getStatus().equals("Completed")) ;
                    else
                        this.status = status;
                else
                    this.status = status;

            } catch (Exception e) {
                e.printStackTrace();
            }

        }
    }
    public String getStatus() throws Exception {
        switch (status) {
            case ToDo:
                return "To Do";
            case InProgress:
                return "In Progress";
            case Completed:
                return "Completed";
            case Failed:
                return "Failed";
        }

        throw new Exception("BAD TASK STATUS");
    }

    //PRIORITY
    public void setPriority(Priority priority) { this.priority = priority; }
    public String getPriority() throws Exception {
        switch (priority) {
            case Low:
                return "Low";
            case Medium:
                return "Medium";
            case High:
                return "High";
        }

        throw new Exception("BAD TASK PRIORITY");
    }

    //START TIME
    public void setStartTime(LocalDateTime startTime) { this.startTime = startTime; }
    public LocalDateTime getStartTime() { return startTime; }

    //DEADLINES
    public boolean setDeadline(LocalDateTime deadline) {
        if(deadline.isAfter(startTime))
        {
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

    //SUBTASKS
    public void addSubTask(Task subtask) {
        subtasks.add(subtask);
    }
    public void removeSubTask(Task subtask) {
        subtasks.remove(subtask);
    }
    public ArrayList<Task> getSubTasks() {
        return subtasks;
    }

    //NAME
    public void setName(String name) {
        this.name = name;
    }
    public String getName() {
        return name;
    }

    //DEPEND ON TASK
    public void setDependOn(Task dependOn) {
        if(dependOn != this)
            this.dependOn = dependOn;
    }
    public Task getDependOn() {
        return dependOn;
    }

    private boolean canBeCompleted()
    {
        for(Task subtask : subtasks)
        {
            try{
                if(subtask.getStatus() != "Completed")
                    return false;
            } catch (Exception e)
            {
                e.printStackTrace();
                return false;
            }
        }
        return true;
    }
}
