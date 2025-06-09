package manager;

import task.Task;

import java.util.ArrayList;
import task.*;

public class InMemoryHistoryManager implements HistoryManager {

    //Объявление списка последних задач
    public ArrayList<Task> historyTaskList;

    InMemoryHistoryManager(){
        historyTaskList = new ArrayList<>();
    }


    @Override
    public ArrayList<Task> getHistory(){
        return historyTaskList;
    }

    @Override
    public void add(Task task) {
        if(historyTaskList.size() >= 10){
            historyTaskList.removeFirst();
        }
        historyTaskList.add(task);
    }
}
