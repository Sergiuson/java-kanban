

import java.util.Scanner;
import java.util.HashMap;
import java.util.ArrayList;

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
        Task NewTask = new Task(name,description);
        taskManager.createTask(NewTask);
        name = "TaskF";
        description = "sdlslffldsds";
        NewTask = new Task(name,description);
        taskManager.createTask(NewTask);
        System.out.println("Создание epics");
        name = "TaskB";
        description = "sdlslffldsdsdssdsd";
        ArrayList<Integer> subList = new ArrayList<>();
        Epic NewEpic = new Epic(name,description,subList);
        taskManager.createEpic(NewEpic);
        name = "TaskC";
        description = "sdlslffldsdssdsddssdsd";
        subList = new ArrayList<>();
        NewEpic = new Epic(name,description,subList);
        taskManager.createEpic(NewEpic);
        name = "TaskR";
        description = "rrrr";
        subList = new ArrayList<>();
        NewEpic = new Epic(name,description,subList);
        taskManager.createEpic(NewEpic);
        System.out.println("Создание subtasks");
        name = "Sub1";
        description = "sdlslffldsdssdsddssddd";
        SubTask NewSubTask = new SubTask(name, description, 2);
        taskManager.createSubtask(NewSubTask);
        name = "Sub2";
        description = "sdlslffld";
        NewSubTask = new SubTask(name, description, 2);
        taskManager.createSubtask(NewSubTask);
        name = "Sub3";
        description = "sdlslffldsdssdsddss";
        NewSubTask = new SubTask(name, description, 3);
        taskManager.createSubtask(NewSubTask);
        name = "Sub1";
        description = "fdsjdjsfd";
        NewSubTask = new SubTask(name, description, 4);
        taskManager.createSubtask(NewSubTask);
        name = "Sub2";
        description = "ddddjfjd";
        NewSubTask = new SubTask(name, description, 4);
        taskManager.createSubtask(NewSubTask);
        System.out.println("Показ списка tasks");
        System.out.println(taskManager.getTasks());
        System.out.println("Показ списка epics");
        System.out.println(taskManager.getEpics());
        System.out.println("Показ списка subtasks");
        System.out.println(taskManager.getSubtasks());
        System.out.println("Изменение  задачи Task");
        name = "";
        description = "BBB";
        NewTask = new Task(name,description);
        taskManager.changeTask(NewTask,0 );
        name = "TaskFFFF";
        description = "";
        NewTask = new Task(name,description);
        taskManager.changeTask(NewTask,1 );
        System.out.println(taskManager.getTasks());

        System.out.println("Изменение статусов задач");
        taskManager.changeStatusTask( 3,0);
        taskManager.changeStatusSubTask(3, 5);
        taskManager.changeStatusSubTask(3, 7);

        System.out.println(taskManager.getEpics());
        System.out.println(taskManager.getSubtasks());

        System.out.println("Показ конкретной задачи");
        System.out.println(taskManager.getEpicsById( 3));
        System.out.println("Удаление одной подзадачи");
        taskManager.deleteSubtasksById( 5);
        System.out.println("Удаление одного эпика");
        taskManager.deleteEpicsById( 3);
        System.out.println("Показ списка epics");
        System.out.println(taskManager.getEpics());
        System.out.println("Показ списка subtasks");
        System.out.println(taskManager.getSubtasks());
        System.out.println("Показ списка подзадач");
        System.out.println(taskManager.getListSubtasks(4));

    }
}