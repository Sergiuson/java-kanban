
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;
import java.util.HashSet;

public class Main {

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);

        HashMap<Integer, Task> tasks = new HashMap<>();
        HashMap<Integer, Epic> epics = new HashMap<>();
        HashMap<Integer, SubTask> subtasks = new HashMap<>();

        TaskManager taskManager = new TaskManager(tasks,epics,subtasks);
        //Ниже идут тесты из TaskManager
        System.out.println("Создание epics");
        taskManager.id = taskManager.createAllTask(taskManager.id,console, tasks, epics, subtasks);
        taskManager.id = taskManager.createAllTask(taskManager.id,console, tasks, epics, subtasks);
        System.out.println("Создание subtasks");
        taskManager.id = taskManager.createAllTask(taskManager.id,console, tasks, epics, subtasks);
        taskManager.id = taskManager.createAllTask(taskManager.id,console, tasks, epics, subtasks);
        taskManager.id = taskManager.createAllTask(taskManager.id,console, tasks, epics, subtasks);
        System.out.println("Показ списка epics");
        taskManager.seeEpicsList(epics);
        System.out.println("Показ списка subtasks");
        taskManager.seeSubtasksList(subtasks);
        System.out.println("Изменение статусов задач");
        taskManager.changeAllStatus(tasks, epics, subtasks, console);
        taskManager.changeAllStatus(tasks, epics, subtasks, console);
        taskManager.changeAllStatus(tasks, epics, subtasks, console);
        System.out.println("Показ конкретной задачи");
        taskManager.searchByIdTask(tasks, epics, subtasks, console);
        taskManager.searchByIdTask(tasks, epics, subtasks, console);
        taskManager.searchByIdTask(tasks, epics, subtasks, console);
        System.out.println("Удаление одной подзадачи");
        taskManager.deleteIdTasks(tasks, epics, subtasks, console);
        System.out.println("Удаление одного эпика");
        taskManager.deleteIdTasks(tasks, epics, subtasks, console);
        System.out.println("Показ конкретной задачи");
        taskManager.searchByIdTask(tasks, epics, subtasks, console);
        taskManager.searchByIdTask(tasks, epics, subtasks, console);







    }
}