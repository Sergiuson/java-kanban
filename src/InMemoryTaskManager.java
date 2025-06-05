import java.util.*;

public class InMemoryTaskManager implements TaskManager {


    //Объявление наборов задач
    HashMap<Integer, Task> tasks;
    HashMap<Integer, Epic> epics;
    HashMap<Integer, SubTask> subtasks;
    //Объявление списка последних задач
    HistoryManager historyManager = Managers.getDefaultHistory();
    //Объявление начального идентификатора задач
    int id;


    InMemoryTaskManager(HashMap<Integer, Task> tasks, HashMap<Integer, Epic> epics, HashMap<Integer, SubTask> subtasks){
        this.tasks = tasks;
        this.epics = epics;
        this.subtasks = subtasks;
        id = 0;
    }


    //метод создания  задач с типом Task
    @Override
    public  void createTask(Task newTask){
        if(!tasks.containsValue(newTask)){
            tasks.put(id, newTask );
            id++;
        }
    }
    //метод создания  задач с типом Epic
    @Override
    public  void createEpic(Epic newEpic){

        if(!epics.containsValue(newEpic)){
            epics.put(id, newEpic );
            id++;
        }
    }
    //метод создания  задач с типом SubTask
    @Override
    public  void createSubtask(SubTask newSubTask){


        // Subtask создается только в том случае, если сущестует Epic c таким epicId и у него нет ещё такой подзадачи
        if(!subtasks.containsValue(newSubTask) && epics.containsKey(newSubTask.epicId)) {
            subtasks.put(id, newSubTask);
            //Обновление родительского эпика
            String EpicName = epics.get(newSubTask.epicId).name;
            String EpicDesrip = epics.get(newSubTask.epicId).description;
            ArrayList<Integer> EpicSubTasks = epics.get(newSubTask.epicId).listSubTasks;
            EpicSubTasks.add(id);
            epics.put(newSubTask.epicId, new Epic(EpicName,EpicDesrip,EpicSubTasks));
            //Вызов метода epic, изменяющего статус задачи согласно новой подзадачи
            HashSet<Integer> epicTask = new HashSet<>();
            epicTask.add(newSubTask.epicId);
            changeStatusEpic(epicTask);
            id++;
        }
    }

    //метод просмотра  всех задач с типом Task
    @Override
    public  Collection<Task> getTasks(){
        Collection<Task> tasksList = new ArrayList<>();
        for (Integer i : tasks.keySet()){
            tasksList.add(getTasksById(i));
        }
       return tasksList;
    }
    //метод просмотра  всех задач с типом Epic
    @Override
    public  Collection<Epic> getEpics(){
        Collection<Epic> epicsList = new ArrayList<>();
        for (Integer i : epics.keySet()){
            epicsList.add(getEpicsById(i));
        }
        return epicsList;
    }

    //метод просмотра  всех задач c типом Subtask
    @Override
    public  Collection<SubTask> getSubtasks(){
        Collection<SubTask> subtasksList = new ArrayList<>();
        for (Integer i : subtasks.keySet()){
            subtasksList.add(getSubtasksById(i));
        }
        return subtasksList;
    }

    //метод удаления всех Task
    @Override
    public  void deleteTasks(){
        tasks.clear();
    }
    //метод удаления всех Epic
    @Override
    public  void deleteEpics(){
        epics.clear();
    }
    //метод удаления всех SubTask
    @Override
    public  void deleteSubtasks(){
        //Определяем список существующих эпиков
        HashSet<Integer> EpicIdList = new HashSet<>();
        for( SubTask subtask: subtasks.values()) {
            EpicIdList.add(subtask.epicId);
        }
        //Удаляем все подзадачи
        subtasks.clear();
        //Обновляем статусы всем эпикам
        changeStatusEpic(EpicIdList);
    }
    //метод просмотра   задачи с типом Task по id
    @Override
    public  Task getTasksById(Integer id){
        if (tasks.containsKey(id)){
            historyManager.add(tasks.get(id));
        }
        return tasks.get(id);
    }
    //метод просмотра   задачи с типом Epic по id
    @Override
    public  Epic getEpicsById(Integer id){
        if (epics.containsKey(id)){
            historyManager.add(epics.get(id));
        }
        return epics.get(id);
    }
    //метод просмотра   задачи с типом SubTask по id
    @Override
    public  SubTask getSubtasksById(Integer id){
        if (subtasks.containsKey(id)){
            historyManager.add(subtasks.get(id));
        }
        return subtasks.get(id);
    }

    //метод обновления  всех задач с типом Task
    @Override
    public  void changeTask(Task newTask, int id){
        if (tasks.containsKey(id)) {
            //Вычитывание текущих поле задачи
            String newName = tasks.get(id).name;
            String newDescription = tasks.get(id).description;
            //Определение полей для изменения
            if (!newTask.name.isEmpty()) {
                newName = newTask.name;
            }
            if (!newTask.description.isEmpty()) {
                newDescription = newTask.description;
            }
            Task task = new Task(newName, newDescription);
            //Перед изменением задачи проверяется наличие аналогичной задачи
            if (!tasks.containsValue(task)) {
                tasks.put(id, task);
            }
        }
    }
    //метод обновления  всех задач с типом Epic
    @Override
    public  void changeEpic(Epic newEpic, int id){
        if (epics.containsKey(id)) {
            //Вычитывание текущих поле задачи
            String newName = epics.get(id).name;
            String newDescription = epics.get(id).description;
            ArrayList<Integer> newListSubTasks = epics.get(id).listSubTasks;
            //Определение полей для изменения
            if (!newEpic.name.isEmpty()) {
                newName = newEpic.name;
            }
            if (!newEpic.description.isEmpty()) {
                newDescription = newEpic.description;
            }
            Epic epic = new Epic(newName, newDescription, newListSubTasks);
            //Перед изменением задачи проверяется наличие аналогичной задачи
            if (!epics.containsValue(epic)) {
                epics.put(id, epic);
            }
        }
    }
    //метод обновления  всех задач с типом SubTask
    @Override
    public  void changeSubtask(SubTask newSubTask, int id){
        if (subtasks.containsKey(id)) {
            //Вычитывание текущих поле задачи
            String newName = subtasks.get(id).name;
            String newDescription = subtasks.get(id).description;
            int newEpicId = subtasks.get(id).epicId;
            //Определение полей для изменения
            if (!newSubTask.name.isEmpty()) {
                newName = newSubTask.name;
            }
            if (!newSubTask.description.isEmpty()) {
                newDescription = newSubTask.description;
            }
            SubTask subtask = new SubTask(newName, newDescription, newEpicId);
            //Перед изменением задачи проверяется наличие аналогичной задачи
            if (!subtasks.containsValue(subtask)) {
                subtasks.put(id, subtask);
            }
        }
    }
    //метод обновления статуса  всех задач с типом Task
    @Override
    public  void changeStatusTask(int statusCode, int id){
        if (tasks.containsKey(id)) {
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

            Task task = new Task(newName, newDescription, newStatus);
            tasks.put(id, task);
        }
    }
    //метод обновления статуса  всех задач с типом Epic
    @Override
    public  void changeStatusEpic(HashSet<Integer> listId){
        for(int id : listId) {
            if (epics.containsKey(id)) {
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
                //Чтение статусов подзадач происходит только в случае их существования
                for (int subId : listSubTasks) {
                    if (subtasks.containsKey(subId)) {
                        StatusTask status = subtasks.get(subId).status;
                        statusSet.add(status);
                    } else {
                        //Определяем несуществующие подзадачи, которые необходимо убрать из списка
                        removeListSubTask.add(subId);
                    }
                }
                //Удаление несуществующие подзадачи
                for (int removeSubId : removeListSubTask) {
                    listSubTasks.remove(Integer.valueOf(removeSubId));
                }
                if (statusSet.isEmpty()) {
                    newStatus = StatusTask.NEW;
                } else if (statusSet.contains(StatusTask.NEW) || statusSet.contains(StatusTask.IN_PROGRESS)) {
                    newStatus = StatusTask.IN_PROGRESS;
                } else {
                    newStatus = StatusTask.DONE;
                }
                //Запись обновленной задачи
                Epic epic = new Epic(newName, newDescription, newStatus, listSubTasks);
                epics.put(id, epic);
            }
        }
    }
    //метод обновления статуса  всех задач с типом SubTask
    @Override
    public  void changeStatusSubTask(int statusCode, int id){
        if (subtasks.containsKey(id)) {
            //Вычитывание текущих поле задачи
            String newName = subtasks.get(id).name;
            String newDescription = subtasks.get(id).description;
            StatusTask newStatus = subtasks.get(id).status;
            HashSet<Integer> EpicList = new HashSet<>();
            //Формирование списка родительского эпика, для обновления его статуса
            EpicList.add(subtasks.get(id).epicId);

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
            SubTask subtask = new SubTask(newName, newDescription, newStatus, subtasks.get(id).epicId);
            subtasks.put(id, subtask);
            //Обновление статуса эпика
            changeStatusEpic(EpicList);
        }
    }

    //метод удаления Task по ID
    @Override
    public  void deleteTasksById(int id){
        tasks.remove(id);
    }
    //метод удаления Epic по ID
    @Override
    public  void deleteEpicsById(int id){
        epics.remove(id);
    }
    //метод удаления Subtask по ID
    @Override
    public  void deleteSubtasksById(int id){
        //Определение родительского эпика
        HashSet<Integer> EpicList = new HashSet<>();
        EpicList.add(subtasks.get(id).epicId);
        //Удаление задачи
        subtasks.remove(id);
        //Обновление статуса эпика
        changeStatusEpic(EpicList);
    }

    //метод просмотра подзадач опредленного эпика
    @Override
    public  ArrayList<SubTask> getListSubtasks(int id){
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
