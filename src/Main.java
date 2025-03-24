
import java.util.ArrayList;
import java.util.Scanner;
import java.util.HashMap;

public class Main {

    public static void main(String[] args) {

        Scanner console = new Scanner(System.in);

        HashMap<Integer, Task> tasks = new HashMap<>();
        HashMap<Integer, Epic> epics = new HashMap<>();
        HashMap<Integer, SubTask> subtasks = new HashMap<>();

        int id = 0;
        int newId;

        System.out.println("Добро пожаловать в приложение Трекер задач!");

        //цикл обработки запроса пользователя через меню программы
        while (true) {
            printMenu();
            String command = console.nextLine();

            switch (command) {
                case "1":
                    newId = crateAllTask(id, console, tasks, epics, subtasks);
                    id = newId;
                    break;
                case "2":
                    seeAllTasksList(tasks, epics, subtasks, console);
                    break;
                case "3":
                    return;
                case "4":
                    deleteAllTasks(tasks, epics, subtasks, console);
                    break;
                case "5":
                    searchByIdTask(tasks, epics, subtasks, console);
                    break;
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
        System.out.println("2 - Получение списка задач");
        System.out.println("3 - Выход");
        System.out.println("4 - Удаление задач");
        System.out.println("5 - Получение по идентификатору задачи");
    }

    public static Integer crateAllTask (int id, Scanner console, HashMap<Integer, Task> tasks,HashMap<Integer, Epic> epics,HashMap<Integer, SubTask> subtasks) {
        System.out.println("Укажите тип задачи (Task, Epic, Subtask):");
        String taskType = console.nextLine();
        int newId;
        switch (taskType) {
            case "Task":
                newId = createTask(id, console, tasks);
                //System.out.println("Task newId: " + newId);
                id = newId;
                break;
            case "Epic":
                newId = createEpic(id, console, epics);
                //System.out.println("Epic newId: " + newId);
                id = newId;
                break;
            case "Subtask":
                System.out.println("Укажите идентификатор родительской задачи (Epic):");
                Integer parentId = console.nextInt();
                console.nextLine();
                if(epics.containsKey(parentId)) {
                    newId = createSubtask(id, console, subtasks, parentId);
                    //Добаление id новой subtask к списку subtasks родительского Epic
                    epics.get(parentId).listSubTasks.add(id);
                    //System.out.println("Subtask newId: " + newId);
                    id = newId;

                } else{
                    System.out.println("Задачи с идентификатором " + parentId + " не существует");
                }
                break;
            default:
                System.out.println("Неизвестный тип задачи!");
                break;

        }
        return id;
    }

    public static Integer createTask(int id, Scanner console, HashMap<Integer, Task> tasks){
        int newId;
        System.out.println("Введите название задачи:");
        String name = console.nextLine();
        System.out.println("Опишите задачу:");
        String description = console.nextLine();
        Task newTask = new Task(name, description);
        if(!tasks.containsValue(newTask)){
            tasks.put(id, newTask );
            newId = id + 1;
        } else {
            System.out.println("Такая задача уже существует!");
            newId = id;
        }

        return newId;
    }

    public static Integer createEpic(int id, Scanner console, HashMap<Integer, Epic> epics){
        int newId;
        ArrayList<Integer> subList = new ArrayList<>();

        System.out.println("Введите название задачи:");
        String name = console.nextLine();
        System.out.println("Опишите задачу:");
        String description = console.nextLine();


        Epic newEpic = new Epic(name, description,subList);

        if(!epics.containsValue(newEpic)){
            epics.put(id, newEpic );
            newId = id + 1;
        } else {
            System.out.println("Такая задача уже существует!");
            newId = id;
        }

        return newId;
    }

    public static Integer createSubtask(int id, Scanner console, HashMap<Integer, SubTask> subtasks, Integer parentId){
        int newId;
        System.out.println("Введите название задачи:");
        String name = console.nextLine();
        System.out.println("Опишите задачу:");
        String description = console.nextLine();
        SubTask newSubTask = new SubTask(name, description,  parentId);
        if(!subtasks.containsValue(newSubTask)) {
            subtasks.put(id, new SubTask(name, description, parentId));
            newId = id + 1;
        }else{
            System.out.println("Такая задача уже существует!");
            newId = id;
        }
        return newId;
    }

    public static void seeAllTasksList(HashMap<Integer, Task> tasks,HashMap<Integer, Epic> epics,HashMap<Integer, SubTask> subtasks,Scanner console){

        System.out.println("Какой список задач вы хотите полчить : Tasks/Epics/Subtasks/All");
        String answer = console.nextLine();
        switch (answer){
            case "Tasks":
                seeTasksList(tasks);
                break;
            case "Epics":
                seeEpicsList(epics);
                break;
            case "Subtasks":
                seeSubtasksList(subtasks);
                break;
            case "All":
                seeTasksList(tasks);
                seeEpicsList(epics);
                seeSubtasksList(subtasks);
                break;
            default:
                System.out.println("Неизвестный тип задачи!");
                break;

        }
    }

    public static void seeTasksList(HashMap<Integer, Task> tasks){
        if(!tasks.isEmpty()) {
            for (Integer key : tasks.keySet()) {
                Task t = tasks.get(key);
                System.out.println("Идентификатор задачи: " + key+ ", Название задачи: " + t.name + ", Тип задачи: " + t.type);
            }
        } else{
            System.out.println("Список Tasks пуст ");
        }
    }
    public static void seeEpicsList(HashMap<Integer, Epic> epics){
        if(!epics.isEmpty()) {
            for (Integer key : epics.keySet()) {
                Task t = epics.get(key);
                System.out.println("Идентификатор задачи: " + key + ", Название задачи: " + t.name + ", Тип задачи: " + t.type);
            }
        } else{
            System.out.println("Список Epics пуст ");
        }
    }
    public static void seeSubtasksList(HashMap<Integer, SubTask> subtasks){
        if(!subtasks.isEmpty()) {
            for (Integer key : subtasks.keySet()) {
                Task t = subtasks.get(key);
                System.out.println("Идентификатор задачи: " + key + ", Название задачи: " + t.name + ", Тип задачи: " + t.type);
            }
        } else{
            System.out.println("Список SubTasks пуст ");
        }
    }


    public static void deleteAllTasks(HashMap<Integer, Task> tasks,HashMap<Integer, Epic> epics,HashMap<Integer, SubTask> subtasks,Scanner console){

        System.out.println("Какой список задач вы хотите удалить : Tasks/Epics/Subtasks/All");
        String answer = console.nextLine();
        switch (answer){
            case "Tasks":
                tasks.clear();
                break;
            case "Epics":
                epics.clear();
                break;
            case "Subtasks":
                subtasks.clear();
                break;
            case "All":
                tasks.clear();
                epics.clear();
                subtasks.clear();
                break;
            default:
                System.out.println("Неизвестный тип задачи!");
                break;

        }
    }

    public static void searchByIdTask(HashMap<Integer, Task> tasks,HashMap<Integer, Epic> epics,HashMap<Integer, SubTask> subtasks,Scanner console){

        System.out.println("Введите индентификатор задачи:");
        int id = console.nextInt();
        console.nextLine();

        if(tasks.containsKey(id)){
            System.out.println(tasks.get(id));
        } else if(epics.containsKey(id)){
            System.out.println(epics.get(id));

        } else if(subtasks.containsKey(id)){
            System.out.println(subtasks.get(id));
        } else{
            System.out.println("Неизвестный индентификатор задачи!");
        }

    }




}