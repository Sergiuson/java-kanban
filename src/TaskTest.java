import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.util.Collection;


class TaskTest {
    private static TaskManager taskManager;
    @BeforeAll
    public static void taskManagerCreate(){
        taskManager = Managers.getDefault();
    }
    @Test
    void addNewTask() {
        Task task = new Task("Test addNewTask", "Test addNewTask description", StatusTask.NEW);
        taskManager.createTask(task);

        final Task savedTask = taskManager.getTasksById(0);

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task, savedTask, "Задачи не совпадают.");
        //Дубликат задачи
        Task task2 = new Task("Test addNewTask", "Test addNewTask description", StatusTask.NEW);
        taskManager.createTask(task2);
        final Collection<Task> tasks = taskManager.getTasks();

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertTrue( tasks.contains(task) , "Задачи не совпадают.");
    }

}