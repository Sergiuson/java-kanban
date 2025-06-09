package manager;

import task.Epic;
import task.SubTask;
import task.Task;

import java.util.HashMap;

public class Managers {

    static public HashMap<Integer, Task> tasks = new HashMap<>();
    static public HashMap<Integer, Epic> epics = new HashMap<>();
    static public HashMap<Integer, SubTask> subtasks = new HashMap<>();
    static public TaskManager taskManager ;
    static public InMemoryHistoryManager memoryHistoryManager ;

    static public TaskManager getDefault(){
        taskManager = new InMemoryTaskManager(tasks,epics,subtasks);
        return taskManager;
    }

    static public HistoryManager getDefaultHistory(){
        memoryHistoryManager = new InMemoryHistoryManager();
        return memoryHistoryManager;
    }
}
