import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;


public interface TaskManager {
    //метод создания  задач с типом Task
    void createTask(Task newTask);

    //метод создания  задач с типом Epic
    void createEpic(Epic newEpic);

    //метод создания  задач с типом SubTask
    void createSubtask(SubTask newSubTask);

    //метод просмотра  всех задач с типом Task
    Collection<Task> getTasks();

    //метод просмотра  всех задач с типом Epic
    Collection<Epic> getEpics();

    //метод просмотра  всех задач c типом Subtask
    Collection<SubTask> getSubtasks();

    //метод удаления всех Task
    void deleteTasks();

    //метод удаления всех Epic
    void deleteEpics();

    //метод удаления всех SubTask
    void deleteSubtasks();

    //метод просмотра   задачи с типом Task по id
    Task getTasksById(Integer id);

    //метод просмотра   задачи с типом Epic по id
    Epic getEpicsById(Integer id);

    //метод просмотра   задачи с типом SubTask по id
    SubTask getSubtasksById(Integer id);

    //метод обновления  всех задач с типом Task
    void changeTask(Task newTask, int id);

    //метод обновления  всех задач с типом Epic
    void changeEpic(Epic newEpic, int id);

    //метод обновления  всех задач с типом SubTask
    void changeSubtask(SubTask newSubTask, int id);

    //метод обновления статуса  всех задач с типом Task
    void changeStatusTask(int statusCode, int id);

    //метод обновления статуса  всех задач с типом Epic
    void changeStatusEpic(HashSet<Integer> listId);

    //метод обновления статуса  всех задач с типом SubTask
    void changeStatusSubTask(int statusCode, int id);

    //метод удаления Task по ID
    void deleteTasksById(int id);

    //метод удаления Epic по ID
    void deleteEpicsById(int id);

    //метод удаления Subtask по ID
    void deleteSubtasksById(int id);

    //метод просмотра подзадач опредленного эпика
    ArrayList<SubTask> getListSubtasks(int id);

    //метод просмотра истории задач
    ArrayList<Task> getHistory();
}
