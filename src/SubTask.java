import java.util.Objects;

public class SubTask extends Task {

    public Integer parentId;


    SubTask(String name, String description, int id,  Integer parentId){
        super(name, description, id);
        this.parentId = parentId;
        this.type = "Subtask";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SubTask subTask = (SubTask) o;
        return Objects.equals(parentId, subTask.parentId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), parentId);
    }

    @Override
    public String toString() {
        return
                "Задача: '" + name + '\'' + ", Идентификатор задачи: " + id + ", Тип задачи: " + type + ", Статус: " + status
                        + ", Описание: '" + description + '\'' + ", Идентификатор Epic: " + parentId;
    }
}
