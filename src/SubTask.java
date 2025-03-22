import java.util.ArrayList;
import java.util.Objects;

public class SubTask extends Epic {
    public static  String type = "Subtask";
    public Integer parentId;

    SubTask(String name, String description, int id, ArrayList<Integer> listSubTasks, Integer parentId){
        super(name, description, id,listSubTasks);
        this.parentId = parentId;
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
}
