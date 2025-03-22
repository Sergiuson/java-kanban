import java.util.ArrayList;
import java.util.Objects;

public class Epic extends Task {

    ArrayList<Integer> listSubTasks;
    public static  String type = "Epic";


    public Epic(String name, String description, int id, ArrayList<Integer> listSubTasks){
       super(name, description, id);
        this.listSubTasks = listSubTasks;

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



}
