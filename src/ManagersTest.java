import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

class ManagersTest {

    private static HistoryManager historyManager;
    private static TaskManager taskManager;
    @BeforeAll
    public static void taskManagerCreate(){
        taskManager = Managers.getDefault();
        historyManager = Managers.getDefaultHistory();
    }
    @Test
    void taskManagerTest(){
        ArrayList<Integer> listSubTasks = new ArrayList<>();
        Task task = new Task("Test InMemoryTaskManagerTest", "Test InMemoryTaskManagerTest description");
        taskManager.createTask(task);
        Epic epic = new Epic("Test InMemoryTaskManagerTest", "Test InMemoryTaskManagerTest description", listSubTasks);
        taskManager.createEpic(epic);

        final Task saveTask = taskManager.getTasksById(0);
        final Task saveEpic = taskManager.getEpicsById(1);

        assertNotNull(saveTask, "Задача не возвращается.");

        taskManager.deleteTasksById(0);

        assertNull(taskManager.getTasksById(0), "Задача  возвращается.");

        final ArrayList<Task> history = historyManager.getHistory();

        assertEquals(saveTask, history.get(0), "Задача не зафиксирована в истории ");
        assertEquals(saveEpic, history.get(1), "Задача не зафиксирована в истории ");
    }

}