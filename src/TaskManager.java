import java.util.*;

public class TaskManager {


    //Объявление наборов задач
    HashMap<Integer, Task> tasks;
    HashMap<Integer, Epic> epics;
    HashMap<Integer, SubTask> subtasks;
    //Объявление начального идентификатора задач
    int id;


    TaskManager(HashMap<Integer, Task> tasks,HashMap<Integer, Epic> epics,HashMap<Integer, SubTask> subtasks){
        this.tasks = tasks;
        this.epics = epics;
        this.subtasks = subtasks;
        id = 0;
    }


    //метод создания  задач с типом Task
    public static Integer createTask(int id, String name,String description, HashMap<Integer, Task> tasks){
        int newId;
        Task newTask = new Task(name, description);
        if(!tasks.containsValue(newTask)){
            tasks.put(id, newTask );
            newId = id + 1;
        } else {
            newId = id;
        }

        return newId;
    }
    //метод создания  задач с типом Epic
    public static Integer createEpic(int id, String name,String description , HashMap<Integer, Epic> epics){
        int newId;
        ArrayList<Integer> subList = new ArrayList<>();
        Epic newEpic = new Epic(name, description,subList);

        if(!epics.containsValue(newEpic)){
            epics.put(id, newEpic );
            newId = id + 1;
        } else {
            newId = id;
        }

        return newId;
    }
    //метод создания  задач с типом SubTask
    public static Integer createSubtask(int id, String name,String description , HashMap<Integer, SubTask> subtasks, Integer parentId, HashMap<Integer, Epic> epics){
        int newId;
        SubTask newSubTask = new SubTask(name, description,  parentId);
        // Subtask создается только в том случае, если сущестует Epic c таким parentId и у него нет ещё такой подзадачи
        if(!subtasks.containsValue(newSubTask) && epics.containsKey(parentId)) {
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
            newId = id;
        }
        return newId;
    }

    //метод просмотра  всех задач с типом Task
    public static Collection<Task> getTasks(HashMap<Integer, Task> tasks){
       return tasks.values();
    }
    //метод просмотра  всех задач с типом Epic
    public static Collection<Epic> getEpics(HashMap<Integer, Epic> epics){
        return epics.values();
    }

    //метод просмотра  всех задач c типом Subtask
    public static Collection<SubTask> getSubtasks(HashMap<Integer, SubTask> subtasks){
        return subtasks.values();
    }

    //метод удаления всех Task
    public static void deleteTasks(HashMap<Integer, Task> tasks){
        tasks.clear();
    }
    //метод удаления всех Epic
    public static void deleteEpics(HashMap<Integer, Epic> epics){
        epics.clear();
    }
    //метод удаления всех SubTask
    public static void deleteSubtasks(HashMap<Integer, Epic> epics,HashMap<Integer, SubTask> subtasks){
        //Определяем список существующих эпиков
        HashSet<Integer> parentIdList = new HashSet<>();
        for( SubTask subtask: subtasks.values()) {
            parentIdList.add(subtask.parentId);
        }
        //Удаляем все подзадачи
        subtasks.clear();
        //Обновляем статусы всем эпикам
        changeStatusEpic(epics,subtasks,parentIdList);
    }
    //метод просмотра   задачи с типом Task по id
    public static Task getTasksById(HashMap<Integer, Task> tasks, Integer id){
        return tasks.get(id);
    }
    //метод просмотра   задачи с типом Epic по id
    public static Epic getEpicsById(HashMap<Integer, Epic> epics, Integer id){
        return epics.get(id);
    }
    //метод просмотра   задачи с типом SubTask по id
    public static SubTask getSubtasksById(HashMap<Integer, SubTask> subtasks, Integer id){
        return subtasks.get(id);
    }

    //метод обновления  всех задач с типом Task
    public static void changeTask(HashMap<Integer, Task> tasks,String name, String description, int id){
        //Вычитывание текущих поле задачи
        String newName = tasks.get(id).name;
        String newDescription = tasks.get(id).description;
        //Определение полей для изменения
        if(!name.isEmpty()){
            newName = name;
        }
        if(!description.isEmpty()){
            newDescription = description;
        }
        Task task = new Task(newName,newDescription);
        //Перед изменением задачи проверяется наличие аналогичной задачи
        if(!tasks.containsValue(task)){
            tasks.put(id, task );
        }
    }
    //метод обновления  всех задач с типом Epic
    public static void changeEpic(HashMap<Integer, Epic> epics,String name, String description, int id){
        //Вычитывание текущих поле задачи
        String newName = epics.get(id).name;
        String newDescription = epics.get(id).description;
        ArrayList<Integer> newListSubTasks = epics.get(id).listSubTasks;
        //Определение полей для изменения
        if(!name.isEmpty()){
            newName = name;
        }
        if(!description.isEmpty()){
            newDescription = description;
        }
        Epic epic = new Epic(newName,newDescription,newListSubTasks);
        //Перед изменением задачи проверяется наличие аналогичной задачи
        if(!epics.containsValue(epic)){
            epics.put(id, epic );
        }
    }
    //метод обновления  всех задач с типом SubTask
    public static void changeSubtask(HashMap<Integer, SubTask> subtasks,String name, String description, int id){
        //Вычитывание текущих поле задачи
        String newName = subtasks.get(id).name;
        String newDescription = subtasks.get(id).description;
        int newParentId = subtasks.get(id).parentId;
        //Определение полей для изменения
        if(!name.isEmpty()){
            newName = name;
        }
        if(!description.isEmpty()){
            newDescription = description;
        }
        SubTask subtask = new SubTask(newName,newDescription,newParentId);
        //Перед изменением задачи проверяется наличие аналогичной задачи
        if(!subtasks.containsValue(subtask)){
            subtasks.put(id, subtask );
        }
    }
    //метод обновления статуса  всех задач с типом Task
    public static void changeStatusTask(HashMap<Integer, Task> tasks, int statusCode, int id){
        //Вычитывание текущих поле задачи
        String newName = tasks.get(id).name;
        String newDescription = tasks.get(id).description;
        StatusTask newStatus = tasks.get(id).status;
        switch (statusCode) {
            case 1:
                newStatus = StatusTask.NEW;
                break;
            case 2:
                newStatus = StatusTask.IN_PROGRESS;
                break;
            case 3:
                newStatus = StatusTask.DONE;
                break;
            default:
                break;
        }

        Task task = new Task(newName,newDescription, newStatus);
        tasks.put(id, task);
    }
    //метод обновления статуса  всех задач с типом Epic
    public static void changeStatusEpic(HashMap<Integer, Epic> epics, HashMap<Integer, SubTask> subtasks, HashSet<Integer> listId){
        for(int id : listId){
            //Вычитывание текущих поле задачи
            String newName = epics.get(id).name;
            String newDescription = epics.get(id).description;
            ArrayList<Integer> listSubTasks = epics.get(id).listSubTasks;
            //Список статусов подзадач
            HashSet<StatusTask> statusSet = new HashSet<>();
            //Объявление списка коррекции подзадач
            ArrayList<Integer> removeListSubTask = new ArrayList<>();
            //Объявление нового статуса
            StatusTask newStatus;
            //Чтение статусов подзадач происхоит только в случае их существования
            for (int subId : listSubTasks) {
                if(subtasks.containsKey(subId)) {
                    StatusTask status = subtasks.get(subId).status;
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
                newStatus = StatusTask.NEW;
            } else if(statusSet.contains(StatusTask.NEW) || statusSet.contains(StatusTask.IN_PROGRESS)){
                newStatus = StatusTask.IN_PROGRESS;
            } else{
                newStatus = StatusTask.DONE;
            }
            //Запись обновленной задачи
            Epic epic = new Epic(newName,newDescription,newStatus,listSubTasks);
            epics.put(id,epic);
        }
    }
    //метод обновления статуса  всех задач с типом SubTask
    public static void changeStatusSubTask(HashMap<Integer, Epic> epics, HashMap<Integer, SubTask> subtasks, int statusCode, int id){
        //Вычитывание текущих поле задачи
        String newName = subtasks.get(id).name;
        String newDescription = subtasks.get(id).description;
        StatusTask newStatus = subtasks.get(id).status;
        HashSet<Integer> parentList = new HashSet<>();
        //Формирование списка родительского эпика, для обновления его статуса
        parentList.add(subtasks.get(id).parentId);

        switch (statusCode) {
            case 1:
                newStatus = StatusTask.NEW;
                break;
            case 2:
                newStatus = StatusTask.IN_PROGRESS;
                break;
            case 3:
                newStatus = StatusTask.DONE;
                break;
            default:
                break;
        }
        SubTask subtask = new SubTask(newName,newDescription, newStatus, subtasks.get(id).parentId);
        subtasks.put(id, subtask);
        //Обновление статуса эпика
        changeStatusEpic(epics,subtasks,parentList);
    }

    //метод удаления Task по ID
    public static void deleteTasksById(HashMap<Integer, Task> tasks, int id){
        tasks.remove(id);
    }
    //метод удаления Epic по ID
    public static void deleteEpicsById(HashMap<Integer, Epic> epics, int id){
        epics.remove(id);
    }
    //метод удаления Subtask по ID
    public static void deleteSubtasksById(HashMap<Integer, SubTask> subtasks,HashMap<Integer, Epic> epics, int id){
        //Определение родительского эпика
        HashSet<Integer> parentList = new HashSet<>();
        parentList.add(subtasks.get(id).parentId);
        //Удаление задачи
        subtasks.remove(id);
        //Обновление статуса эпика
        changeStatusEpic(epics,subtasks,parentList);
    }

    //метод просмотра подзадач опредленного эпика
    public static ArrayList<SubTask> getListSubtasks(HashMap<Integer, Epic> epics,HashMap<Integer, SubTask> subtasks, int id){
        ArrayList<SubTask> listSubtask = new ArrayList<>();
        if(epics.containsKey(id)){
            for(int sub : epics.get(id).listSubTasks){
                if(subtasks.containsKey(sub)){
                    listSubtask.add(subtasks.get(sub));
                }
            }
        }
        return listSubtask;
    }
}
