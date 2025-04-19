

import java.util.Scanner;
import java.util.HashMap;


public class Main {

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);

        HashMap<Integer, Task> tasks = new HashMap<>();
        HashMap<Integer, Epic> epics = new HashMap<>();
        HashMap<Integer, SubTask> subtasks = new HashMap<>();

        TaskManager taskManager = new TaskManager(tasks,epics,subtasks);

        String name;
        String description;
        //Ниже идут тесты из TaskManager
        System.out.println("Создание tasks");
        name = "TaskA";
        description = "AAAA";
        taskManager.id = taskManager.createTask(taskManager.id,name,description,tasks);
        name = "TaskF";
        description = "sdlslffldsds";
        taskManager.id = taskManager.createTask(taskManager.id,name,description,tasks);
        System.out.println("Создание epics");
        name = "TaskB";
        description = "sdlslffldsdsdssdsd";
        taskManager.id = taskManager.createEpic(taskManager.id,name,description, epics);
        name = "TaskC";
        description = "sdlslffldsdssdsddssdsd";
        taskManager.id = taskManager.createEpic(taskManager.id,name,description, epics);
        name = "TaskR";
        description = "rrrr";
        taskManager.id = taskManager.createEpic(taskManager.id,name,description, epics);
        System.out.println("Создание subtasks");
        name = "Sub1";
        description = "sdlslffldsdssdsddssddd";
        taskManager.id = taskManager.createSubtask(taskManager.id,name,description, subtasks, 2,epics);
        name = "Sub2";
        description = "sdlslffld";
        taskManager.id = taskManager.createSubtask(taskManager.id,name,description, subtasks, 2,epics);
        name = "Sub3";
        description = "sdlslffldsdssdsddss";
        taskManager.id = taskManager.createSubtask(taskManager.id,name,description, subtasks, 3,epics);
        name = "Sub1";
        description = "fdsjdjsfd";
        taskManager.id = taskManager.createSubtask(taskManager.id,name,description, subtasks, 4,epics);
        name = "Sub2";
        description = "ddddjfjd";
        taskManager.id = taskManager.createSubtask(taskManager.id,name,description, subtasks, 4,epics);
        System.out.println("Показ списка tasks");
        System.out.println(taskManager.getTasks(tasks));
        System.out.println("Показ списка epics");
        System.out.println(taskManager.getEpics(epics));
        System.out.println("Показ списка subtasks");
        System.out.println(taskManager.getSubtasks(subtasks));
        System.out.println("Изменение  задачи Task");
        name = "";
        description = "BBB";
        taskManager.changeTask(tasks,name,description,0 );
        name = "TaskFFFF";
        description = "";
        taskManager.changeTask(tasks,name,description,1 );
        System.out.println(taskManager.getTasks(tasks));

        System.out.println("Изменение статусов задач");
        taskManager.changeStatusTask(tasks, 3,0);
        taskManager.changeStatusSubTask(epics,subtasks,3, 5);
        taskManager.changeStatusSubTask(epics,subtasks,3, 7);

        System.out.println(taskManager.getEpics(epics));
        System.out.println(taskManager.getSubtasks(subtasks));

        System.out.println("Показ конкретной задачи");
        System.out.println(taskManager.getEpicsById(epics, 3));
        System.out.println("Удаление одной подзадачи");
        taskManager.deleteSubtasksById(subtasks,epics, 5);
        System.out.println("Удаление одного эпика");
        taskManager.deleteEpicsById(epics, 3);
        System.out.println("Показ списка epics");
        System.out.println(taskManager.getEpics(epics));
        System.out.println("Показ списка subtasks");
        System.out.println(taskManager.getSubtasks(subtasks));
        System.out.println("Показ списка подзадач");
        System.out.println(taskManager.getListSubtasks(epics,subtasks,4));

    }
}