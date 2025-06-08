import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;


import java.util.ArrayList;
import java.util.Collection;

class SubTaskTest {
    private static TaskManager taskManager;
    private Integer epicId;
    private static ArrayList<Integer> listSubTasks;
    @BeforeAll
    public static void taskManagerCreate(){
        taskManager = Managers.getDefault();
        listSubTasks =new ArrayList<>();
    }
    @Test
    void addNewSubtask() {

        Epic epic = new Epic("Test addNewEpic", "Test addNewEpic description",  listSubTasks);
        taskManager.createEpic(epic);
        SubTask subTask = new SubTask("Test addNewSubtask", "Test addNewSubtask description", 0);
        taskManager.createSubtask(subTask);

        final SubTask savedSubTask = taskManager.getSubtasksById(1);

        assertNotNull(savedSubTask, "Задача не найдена.");
        assertEquals(subTask, savedSubTask, "Задачи не совпадают.");
        //Дубликат задачи
        SubTask subTask2 = new SubTask("Test addNewSubtask", "Test addNewSubtask description", 0);
        taskManager.createSubtask(subTask2);
        final Collection<SubTask> subTasks = taskManager.getSubtasks();

        assertNotNull(subTasks, "Задачи не возвращаются.");
        assertEquals(1, subTasks.size(), "Неверное количество задач.");
        assertTrue( subTasks.contains(subTask) , "Задачи не совпадают.");
    }


}