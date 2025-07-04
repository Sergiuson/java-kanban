package task;

import java.util.Objects;

public class Task {
    public String name;
    public String description;
    public TypeTask type = TypeTask.TASK;
    public StatusTask status = StatusTask.NEW;

    public Task(String name, String description){
        this.name = name;
        this.description = description;

    }

    public Task(String name, String description, StatusTask status){
        this.name = name;
        this.description = description;
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return
                     Objects.equals(name, task.name) &&
                     Objects.equals(description, task.description) &&
                     Objects.equals(type, task.type);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, status);
    }

    @Override
    public String toString() {
        return
                "task.Task{name = " + name +  ", type = " + type + ", status = " + status  + ", description = '" + description +"'" + '}';
    }
}
