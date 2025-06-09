package test;

import manager.*;
import task.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;


class InMemoryTaskManagerTest {
    private static TaskManager taskManager;
    private  static ArrayList<Integer> listSubTasks;
    @BeforeAll
    public static void taskManagerCreate(){
        taskManager = Managers.getDefault();
        listSubTasks = new ArrayList<>();
    }

    @Test
    void checkworkTaskManager() {
        Task task = new Task("Test test.InMemoryTaskManagerTest", "Test test.InMemoryTaskManagerTest description");
        taskManager.createTask(task);
        Epic epic = new Epic("Test test.InMemoryTaskManagerTest", "Test test.InMemoryTaskManagerTest description", StatusTask.NEW, listSubTasks);
        taskManager.createEpic(epic);
        SubTask subTask = new SubTask("Test test.InMemoryTaskManagerTest", "Test test.InMemoryTaskManagerTest description", 1);
        taskManager.createSubtask(subTask);

        final Task saveTask = taskManager.getTasksById(0);

        Assertions.assertNotNull(saveTask, "Задача не возвращается.");

        final SubTask saveSubtask = taskManager.getSubtasksById(2);

        Assertions.assertNotNull(saveSubtask, "Подзадача не возвращается.");

        final Epic saveEpic = taskManager.getEpicsById(1);

        Assertions.assertNotNull(saveEpic, "Эпик не возвращается.");
    }


}