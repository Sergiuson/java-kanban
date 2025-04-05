
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

        //Обьявление начального идентификатора задач
        int id = 0;
        //Обьявление следующего идентификатора задач
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
                case "6":
                    changeAllTasks(tasks, epics, subtasks, console);
                    break;
                case "7":
                    changeAllStatus(tasks, epics, subtasks, console);
                    break;
                case "8":
                    deleteIdTasks(tasks, epics, subtasks, console);
                    break;
                case "9":
                    seeListSubtasks(epics, subtasks, console);
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
        System.out.println("5 - Получение задачи по её идентификатору");
        System.out.println("6 - Обновление задачи");
        System.out.println("7 - Изменение статуса задачи");
        System.out.println("8 - Удаление задачи по идентификатору");
        System.out.println("9 - Список подзадач эпика");

    }
    //метод создания всех задач
    public static Integer crateAllTask (int id, Scanner console, HashMap<Integer, Task> tasks,HashMap<Integer, Epic> epics,HashMap<Integer, SubTask> subtasks) {
        System.out.println("Укажите тип задачи (Task, Epic, Subtask):");
        String taskType = console.nextLine();
        int newId;
        switch (taskType) {
            case "Task":
                newId = createTask(id, console, tasks);
                id = newId;
                break;
            case "Epic":
                newId = createEpic(id, console, epics);
                id = newId;
                break;
            case "Subtask":
                System.out.println("Укажите идентификатор родительской задачи (Epic):");
                Integer parentId = console.nextInt();
                console.nextLine();
                if(epics.containsKey(parentId)) {
                    newId = createSubtask(id, console, subtasks, parentId, epics);
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
    //метод создания  задач с типом Task
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
    //метод создания  задач с типом Epic
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
    //метод создания  задач с типом SubTask
    public static Integer createSubtask(int id, Scanner console, HashMap<Integer, SubTask> subtasks, Integer parentId, HashMap<Integer, Epic> epics){
        int newId;
        System.out.println("Введите название задачи:");
        String name = console.nextLine();
        System.out.println("Опишите задачу:");
        String description = console.nextLine();
        SubTask newSubTask = new SubTask(name, description,  parentId);
        if(!subtasks.containsValue(newSubTask)) {
            subtasks.put(id, new SubTask(name, description, parentId));
            newId = id + 1;
            //Обновление родительского эпика
            String parentName = epics.get(parentId).name;
            String parentDesrip = epics.get(parentId).description;
            ArrayList<Integer> parentSubTasks = epics.get(parentId).listSubTasks;
            parentSubTasks.add(id);
            epics.put(parentId, new Epic(parentName,parentDesrip,parentSubTasks));
            //Вызов метода epic, изменяющего статус задачи согласно новой подзадачи
            HashSet<Integer> epicTask = new HashSet<>();
            epicTask.add(parentId);
            changeStatusEpic(epics,subtasks,epicTask);
        }else{
            System.out.println("Такая задача уже существует!");
            newId = id;
        }
        return newId;
    }
    //метод просмотра  всех задач
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
    //метод просмотра  всех задач с типом Task
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
    //метод просмотра  всех задач с типом Epic
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

    //метод просмотра  всех задач c типом Subtask
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

    //метод удаления  всех задач
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
                //Определяем список существующих эпиков
                HashSet<Integer> parentIdList = new HashSet<>();
                for( SubTask subtask: subtasks.values()) {
                    parentIdList.add(subtask.parentId);
                }
                //Удаляем все подзадачи
                subtasks.clear();
                //Обновляем статусы всем эпикам
                changeStatusEpic(epics,subtasks,parentIdList);
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

    //метод поиска по идентификатору задачи
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
    //метод обновления  всех задач
    public static void changeAllTasks(HashMap<Integer, Task> tasks,HashMap<Integer, Epic> epics,HashMap<Integer, SubTask> subtasks,Scanner console){
        System.out.println("Введите идентификатор задачи:");
        int id = console.nextInt();
        console.nextLine();
        if(tasks.containsKey(id)){
            changeTask(tasks, console, id);
        } else if(epics.containsKey(id)){
            changeEpic(epics,console,id);
        } else if(subtasks.containsKey(id)){
            changeSubtask(subtasks,console,id);
        } else{
            System.out.println("Неизвестный индентификатор задачи!");
        }
    }
    //метод обновления  всех задач с типом Task
    public static void changeTask(HashMap<Integer, Task> tasks,Scanner console, int id){
        //Вычитывание текущих поле задачи
        String newName = tasks.get(id).name;
        String newDescription = tasks.get(id).description;
        //Определение полей для изменения
        System.out.println("Вы хотите изменить название задачи ? yes/no");
        String answer = console.nextLine();
        switch (answer) {
            case "yes":
                System.out.println("Ввелите новое название задачи :");
                newName = console.nextLine();
                break;
            case "no":
                break;
            default:
                System.out.println("Неизвестный ответ");
                break;
        }
        System.out.println("Вы хотите изменить описание задачи ? yes/no");
        answer = console.nextLine();
        switch (answer) {
            case "yes":
                System.out.println("Ввелите новое описание задачи :");
                newDescription = console.nextLine();
                break;
            case "no":
                break;
            default:
                System.out.println("Неизвестный ответ");
                break;

        }
        Task task = new Task(newName,newDescription);
        //Перед изменением задачи проверяется наличие аналогичной задачи
        if(!tasks.containsValue(task)){
            tasks.put(id, task );
        } else {
            System.out.println("Такая задача уже существует!");
        }



    }
    //метод обновления  всех задач с типом Epic
    public static void changeEpic(HashMap<Integer, Epic> epics,Scanner console, int id){
        //Вычитывание текущих поле задачи
        String newName = epics.get(id).name;
        String newDescription = epics.get(id).description;
        ArrayList<Integer> newListSubTasks = epics.get(id).listSubTasks;
        //Определение полей для изменения
        System.out.println("Вы хотите изменить название задачи ? yes/no");
        String answer = console.nextLine();
        switch (answer) {
            case "yes":
                System.out.println("Ввелите новое название задачи :");
                newName = console.nextLine();
                break;
            case "no":
                break;
            default:
                System.out.println("Неизвестный ответ");
                break;
        }
        System.out.println("Вы хотите изменить описание задачи ? yes/no");
        answer = console.nextLine();
        switch (answer) {
            case "yes":
                System.out.println("Ввелите новое описание задачи :");
                newDescription = console.nextLine();
                break;
            case "no":
                break;
            default:
                System.out.println("Неизвестный ответ");
                break;

        }
        Epic epic = new Epic(newName,newDescription,newListSubTasks);
        //Перед изменением задачи проверяется наличие аналогичной задачи
        if(!epics.containsValue(epic)){
            epics.put(id, epic );
        } else {
            System.out.println("Такая задача уже существует!");
        }
    }
    //метод обновления  всех задач с типом SubTask
    public static void changeSubtask(HashMap<Integer, SubTask> subtasks,Scanner console, int id){
        //Вычитывание текущих поле задачи
        String newName = subtasks.get(id).name;
        String newDescription = subtasks.get(id).description;
        int newParentId = subtasks.get(id).parentId;
        //Определение полей для изменения
        System.out.println("Вы хотите изменить название задачи ? yes/no");
        String answer = console.nextLine();
        switch (answer) {
            case "yes":
                System.out.println("Ввелите новое название задачи :");
                newName = console.nextLine();
                break;
            case "no":
                break;
            default:
                System.out.println("Неизвестный ответ");
                break;
        }
        System.out.println("Вы хотите изменить описание задачи ? yes/no");
        answer = console.nextLine();
        switch (answer) {
            case "yes":
                System.out.println("Ввелите новое описание задачи :");
                newDescription = console.nextLine();
                break;
            case "no":
                break;
            default:
                System.out.println("Неизвестный ответ");
                break;

        }
        SubTask subtask = new SubTask(newName,newDescription,newParentId);
        //Перед изменением задачи проверяется наличие аналогичной задачи
        if(!subtasks.containsValue(subtask)){
            subtasks.put(id, subtask );
        } else {
            System.out.println("Такая задача уже существует!");
        }
    }
    //метод обновления статуса  всех задач с типом
    public static void changeAllStatus(HashMap<Integer, Task> tasks,HashMap<Integer, Epic> epics,HashMap<Integer, SubTask> subtasks,Scanner console){
        System.out.println("Введите идентификатор задачи:");
        int id = console.nextInt();
        console.nextLine();
        if(tasks.containsKey(id)){
            changeStatusTask(tasks, console, id);
        } else if(epics.containsKey(id)){
            System.out.println("Статус задачи типа Epic определяется статусами подзадач");
        } else if(subtasks.containsKey(id)){
            changeStatusSubTask(epics, subtasks, console, id);
        } else{
            System.out.println("Неизвестный индентификатор задачи!");
        }
    }
    //метод обновления статуса  всех задач с типом Task
    public static void changeStatusTask(HashMap<Integer, Task> tasks, Scanner console, int id){
        //Вычитывание текущих поле задачи
        String newName = tasks.get(id).name;
        String newDescription = tasks.get(id).description;
        String newStatus = tasks.get(id).status;
        System.out.println("Какой статус установит : 1 - NEW; 2 - IN_PROGRESS; 3 - DONE");
        int answer = console.nextInt();
        switch (answer) {
            case 1:
                newStatus = "NEW";
                break;
            case 2:
                newStatus = "IN_PROGRESS";
                break;
            case 3:
                newStatus = "DONE";
            default:
                System.out.println("Неизвестный ответ");
                break;
        }

        Task task = new Task(newName,newDescription, newStatus);
        tasks.put(id, task);
    }
    //метод обновления статуса  всех задач с типом SubTask
    public static void changeStatusEpic(HashMap<Integer, Epic> epics, HashMap<Integer, SubTask> subtasks, HashSet<Integer> listId){
       for(int id : listId){
         //Вычитывание текущих поле задачи
         String newName = epics.get(id).name;
         String newDescription = epics.get(id).description;
         ArrayList<Integer> listSubTasks = epics.get(id).listSubTasks;
         //Список статусов подзадач
         HashSet<String> statusSet = new HashSet<>();
         //Объявление списка коррекции подзадач
           ArrayList<Integer> removeListSubTask = new ArrayList<>();
         //Объявление нового статуса
         String newStatus;
         //Чтение статусов подзадач происхоит только в случае их существования
          for (int subId : listSubTasks) {
              if(subtasks.containsKey(subId)) {
                  String status = subtasks.get(subId).status;
                  statusSet.add(status);
              } else {
                  //Определяем несуществующие подзадачи, которые необходимо убрать из списка
                  removeListSubTask.add(subId);
              }
          }
          //Удаление несуществующие подзадачи
          for (int removeSubId : removeListSubTask){
              listSubTasks.remove(Integer.valueOf(removeSubId));
          }

         if(statusSet.isEmpty()){
             newStatus = "NEW";
         } else if(statusSet.contains("NEW") || statusSet.contains("IN_PROGRESS")){
             newStatus = "IN_PROGRESS";
         } else{
             newStatus = "DONE";
         }
         //Запись обновленной задачи
         Epic epic = new Epic(newName,newDescription,newStatus,listSubTasks);
         epics.put(id,epic);
       }
    }
    //метод обновления статуса  всех задач с типом SubTask
    public static void changeStatusSubTask(HashMap<Integer, Epic> epics, HashMap<Integer, SubTask> subtasks, Scanner console, int id){
        //Вычитывание текущих поле задачи
        String newName = subtasks.get(id).name;
        String newDescription = subtasks.get(id).description;
        String newStatus = subtasks.get(id).status;
        HashSet<Integer> parentList = new HashSet<>();
        //Формирование списка родительского эпика, для обновления его статуса
        parentList.add(subtasks.get(id).parentId);

        System.out.println("Какой статус установит : 1 - NEW; 2 - IN_PROGRESS; 3 - DONE");
        int answer = console.nextInt();
        switch (answer) {
            case 1:
                newStatus = "NEW";
                break;
            case 2:
                newStatus = "IN_PROGRESS";
                break;
            case 3:
                newStatus = "DONE";
            default:
                System.out.println("Неизвестный ответ");
                break;
        }

        SubTask subtask = new SubTask(newName,newDescription, newStatus, subtasks.get(id).parentId);
        subtasks.put(id, subtask);
        //Обновление статуса эпика
        changeStatusEpic(epics,subtasks,parentList);
    }
    //метод удаления задачи по идентификатору
    public static void deleteIdTasks(HashMap<Integer, Task> tasks,HashMap<Integer, Epic> epics,HashMap<Integer, SubTask> subtasks,Scanner console){
        System.out.println("Введите идентификатор задачи:");
        int id = console.nextInt();
        console.nextLine();
        if(tasks.containsKey(id)){
            tasks.remove(id);
        } else if(epics.containsKey(id)){
            epics.remove(id);
        } else if(subtasks.containsKey(id)){
            //Определение родительского эпика
            HashSet<Integer> parentList = new HashSet<>();
            parentList.add(subtasks.get(id).parentId);
            //Удаление задачи
            subtasks.remove(id);
            //Обновление статуса эпика
            changeStatusEpic(epics,subtasks,parentList);
        } else{
            System.out.println("Неизвестный индентификатор задачи!");
        }
    }
    //метод просмотра подзадач опредленного эпика
    public static void seeListSubtasks(HashMap<Integer, Epic> epics,HashMap<Integer, SubTask> subtasks,Scanner console){
        System.out.println("Введите идентификатор epic:");
        int id = console.nextInt();
        console.nextLine();
        if(epics.containsKey(id)){
            System.out.println("Список подздач epic:");
            for(int sub : epics.get(id).listSubTasks){
                if(subtasks.containsKey(sub)){
                    System.out.println("Идентификатор задачи: " + sub +
                            ", Название задачи: " + subtasks.get(sub).name +
                            ", Описание задачи: " + subtasks.get(sub).description +
                            ", Статус задачи: " + subtasks.get(sub).status);
                }
            }
        } else{
            System.out.println("Неизвестный индентификатор задачи!");
        }
    }
}