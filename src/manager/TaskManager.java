package manager;

import task.Epic;
import task.SubTask;
import task.Task;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;


public interface TaskManager {
    //метод создания  задач с типом task.Task
    void createTask(Task newTask);

    //метод создания  задач с типом task.Epic
    void createEpic(Epic newEpic);

    //метод создания  задач с типом task.SubTask
    void createSubtask(SubTask newSubTask);

    //метод просмотра  всех задач с типом task.Task
    Collection<Task> getTasks();

    //метод просмотра  всех задач с типом task.Epic
    Collection<Epic> getEpics();

    //метод просмотра  всех задач c типом Subtask
    Collection<SubTask> getSubtasks();

    //метод удаления всех task.Task
    void deleteTasks();

    //метод удаления всех task.Epic
    void deleteEpics();

    //метод удаления всех task.SubTask
    void deleteSubtasks();

    //метод просмотра   задачи с типом task.Task по id
    Task getTasksById(Integer id);

    //метод просмотра   задачи с типом task.Epic по id
    Epic getEpicsById(Integer id);

    //метод просмотра   задачи с типом task.SubTask по id
    SubTask getSubtasksById(Integer id);

    //метод обновления  всех задач с типом task.Task
    void changeTask(Task newTask, int id);

    //метод обновления  всех задач с типом task.Epic
    void changeEpic(Epic newEpic, int id);

    //метод обновления  всех задач с типом task.SubTask
    void changeSubtask(SubTask newSubTask, int id);

    //метод обновления статуса  всех задач с типом task.Task
    void changeStatusTask(int statusCode, int id);

    //метод обновления статуса  всех задач с типом task.Epic
    void changeStatusEpic(HashSet<Integer> listId);

    //метод обновления статуса  всех задач с типом task.SubTask
    void changeStatusSubTask(int statusCode, int id);

    //метод удаления task.Task по ID
    void deleteTasksById(int id);

    //метод удаления task.Epic по ID
    void deleteEpicsById(int id);

    //метод удаления Subtask по ID
    void deleteSubtasksById(int id);

    //метод просмотра подзадач опредленного эпика
    ArrayList<SubTask> getListSubtasks(int id);


}
