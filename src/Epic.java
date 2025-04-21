import java.util.ArrayList;
import java.util.Objects;

public class Epic extends Task {

    ArrayList<Integer> listSubTasks;



    public Epic(String name, String description, ArrayList<Integer> listSubTasks){
        super(name, description);
        this.name = name;
        this.description = description;
        this.listSubTasks = listSubTasks;
        this.type = TypeTask.EPIC;

    }

    public Epic(String name, String description, StatusTask status, ArrayList<Integer> listSubTasks){
        super(name, description, status);
        this.name = name;
        this.description = description;
        this.listSubTasks = listSubTasks;
        this.type = TypeTask.EPIC;

    }



    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), listSubTasks);
    }

    @Override
    public String toString() {
        return
                "Epic{name = " + name +  ", type = " + type + ", status = " + status  + ", description = '" + description +  "', listSubTasks = " + listSubTasks + '}';
    }

}
