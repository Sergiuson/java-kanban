import javax.script.ScriptEngine;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);
        HashMap<Integer, Task> tasks = new HashMap<>();
        int id = -1;

        System.out.println("Добро пожаловать в приложение Трекер задач!");

        //цикл обработки запроса пользователя через меню программы
        while (true) {
            printMenu();
            String command = console.nextLine();

            switch (command) {
                case "1":
                    id++;
                    crateAllTask(id, console, tasks);
                    break;
                case "2":
                    seeAllTasks(tasks);
                    break;
                case "3":
                    return;
                default:
                    System.out.println("Неизвестная команда!");
                    break;
            }
        }
    }

    //метод вызова меню программы
    public static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Создать задачу");
        System.out.println("2 - Получение списка всех задач");
        System.out.println("3 - Выход");
    }

    public static void crateAllTask (int id, Scanner console, HashMap<Integer, Task> tasks) {
        System.out.println("Укажите тип задачи (Task, Epic, Subtask):");
        String taskType = console.nextLine();
        switch (taskType) {
            case "Task":
                createTask(id, console, tasks);
                break;
            case "Epic":
                createEpic(id, console, tasks);
                break;
            case "Subtask":
                createSubtask(id, console, tasks);
                break;
            default:
                System.out.println("Неизвестный тип задачи!");
                break;

        }
    }

    public static void seeAllTasks(HashMap<Integer, Task> tasks){
        if(!tasks.isEmpty()) {
            for (Integer key : tasks.keySet()) {
                Task t = tasks.get(key);
                System.out.println("Идентификатор задачи: " + t.id + ", Название задачи: " + t.name + ", Тип задачи: " + t.type);
            }
        }
    }

    public static void createTask(int id, Scanner console, HashMap<Integer, Task> tasks){
        System.out.println("Введите название задачи:");
        String name = console.nextLine();
        System.out.println("Опишите задачу:");
        String description = console.nextLine();
        tasks.put(id, new Task(name, description, id));
    }

    public static void createEpic(int id, Scanner console, HashMap<Integer, Task> tasks){
        System.out.println("Введите название задачи:");
        String name = console.nextLine();
        System.out.println("Опишите задачу:");
        String description = console.nextLine();
    }

    public static void createSubtask(int id, Scanner console, HashMap<Integer, Task> tasks){
        System.out.println("Введите название задачи:");
        String name = console.nextLine();
        System.out.println("Опишите задачу:");
        String description = console.nextLine();
    }


}