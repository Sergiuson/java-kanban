package test;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import manager.*;
import task.*;
import java.util.ArrayList;

class ManagersTest {


    private static TaskManager taskManager;
    @BeforeAll
    public static void taskManagerCreate(){
        taskManager = Managers.getDefault();
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

        Assertions.assertNotNull(saveTask, "Задача не возвращается.");
        Assertions.assertNotNull(saveEpic, "Задача не возвращается.");

        taskManager.deleteTasksById(0);

        Assertions.assertNull(taskManager.getTasksById(0), "Задача  возвращается.");

        final ArrayList<Task> history = Managers.memoryHistoryManager.getHistory();

        Assertions.assertEquals(saveTask, history.getFirst(), "Задача не зафиксирована в истории ");
        Assertions.assertEquals(saveEpic, history.getLast(), "Задача не зафиксирована в истории ");
    }

}