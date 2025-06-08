import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class HistoryManagerTest {

    private static HistoryManager historyManager;
    private static TaskManager taskManager;
    @BeforeAll
    public static void taskManagerCreate(){

        taskManager = Managers.getDefault();
    }

    @Test
    void add() {
        Task task = new Task("Test addNewTask", "Test addNewTask description");
        taskManager.createTask(task);
        Task saveTask = taskManager.getTasksById(0);

        final ArrayList<Task> history = Managers.memoryHistoryManager.getHistory() ;
        assertNotNull(history, "После добавления задачи, история не должна быть пустой.");
        assertEquals(1, history.size(), "После добавления задачи, история не должна быть пустой.");
    }
    @Test
    void checkSaveTask(){
        Task task = new Task("Test addNewTask", "Test addNewTask description");
        taskManager.createTask(task);
        Task saveTask = taskManager.getTasksById(0);

        final ArrayList<Task> history = Managers.memoryHistoryManager.getHistory();

        assertEquals(saveTask, history.get(0), "После добавления задачи в историю, задача в ней недолжна измениться.");

        Task task2 = new Task("Test checkSaveTask", "Test checkSaveTask description");
        taskManager.changeTask(task2, 0);

        assertTrue(task2 != history.get(0) , "После изменения задачи, в истории сохранена предыдущая версия задачи (проверка с измененной задачаей)");
        assertEquals(saveTask, history.get(0), "После изменения задачи, в истории сохранена предыдущая версия задачи (проверка с предыдущей версией задачи)");
    }
}