package test;

import manager.*;
import task.*;


import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Collection;

class EpicTest {
    private static TaskManager taskManager;
    private  static ArrayList<Integer> listSubTasks;
    @BeforeAll
    public static void taskManagerCreate(){
        taskManager = Managers.getDefault();
        listSubTasks = new ArrayList<>();
    }
    @Test
    void addNewEpic() {
        Epic epic = new Epic("Test addNewEpic", "Test addNewEpic description", StatusTask.NEW, listSubTasks);
        taskManager.createEpic(epic);

        final Epic savedEpic = taskManager.getEpicsById(0);

        Assertions.assertNotNull(savedEpic, "Задача не найдена.");
        Assertions.assertEquals(epic, savedEpic, "Задачи не совпадают.");
        //Дубликат задачи
        Epic epic2 = new Epic("Test addNewEpic", "Test addNewEpic description", StatusTask.NEW, listSubTasks);
        taskManager.createEpic(epic2);
        final Collection<Epic> epics = taskManager.getEpics();

        assertNotNull(epics, "Задачи не возвращаются.");
        assertEquals(1, epics.size(), "Неверное количество задач.");
        assertTrue( epics.contains(epic) , "Задачи не совпадают.");
    }


}