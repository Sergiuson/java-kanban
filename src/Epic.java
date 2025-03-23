import java.util.ArrayList;
import java.util.Objects;

public class Epic extends Task {

    ArrayList<Integer> listSubTasks;



    public Epic(String name, String description, int id, ArrayList<Integer> listSubTasks){
        super(name, description, id);
        this.name = name;
        this.description = description;
        this.id = id;
        this.listSubTasks = listSubTasks;
        this.type = "Epic";

    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Epic epic = (Epic) o;
        return Objects.equals(listSubTasks, epic.listSubTasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), listSubTasks);
    }

    @Override
    public String toString() {
        return
                "Задача: '" + name + '\'' + ", Идентификатор задачи: " + id + ", Тип задачи: " + type + ", Статус: " + status
                        + ", Описание: '" + description + '\'' + ", Список идентификаторов Subtasks: " + listSubTasks;
    }

}
