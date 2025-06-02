import java.util.ArrayList;

public interface HistoryManager {
    //метод просмотра истории задач
    ArrayList<Task> getHistory();
    //метод помечает задачи как просмотренные
    void add(Task task);

    }
