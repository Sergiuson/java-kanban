import java.util.Objects;

public class Task {
    public String name;
    public String description;
    public static  String type = "Task";
    public int id;
    public String status = "NEW";

    public Task(String name, String description, int id){
        this.name = name;
        this.description = description;
        this.id = id;

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id &&
                     Objects.equals(name, task.name) &&
                     Objects.equals(description, task.description) &&
                     Objects.equals(status, task.status);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, id, status);
    }

    @Override
    public String toString() {
        return
                "Задача: '" + name + '\'' + ", Идентификатор задачи: " + id + ", Статус: '" + status + '\'' + ", Описание: '" + description + '\'';
    }
}
