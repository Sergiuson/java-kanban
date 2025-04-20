import java.util.Objects;

public class SubTask extends Task {

    public Integer epicId;


    SubTask(String name, String description,   Integer epicId){
        super(name, description);
        this.epicId = epicId;
        this.type = TypeTask.SUBTASK;
    }

    SubTask(String name, String description, StatusTask status,   Integer epicId){
        super(name, description, status);
        this.epicId = epicId;
        this.type = TypeTask.SUBTASK;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SubTask subTask = (SubTask) o;
        return
                Objects.equals(epicId, subTask.epicId);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), epicId);
    }

    @Override
    public String toString() {
        return
                "SubTask{name = " + name +  ", type = " + type + ", status = " + status  + ", description = '" + description +  ", epicId = " + epicId + '}';
    }
}
