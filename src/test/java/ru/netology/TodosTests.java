package ru.netology;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.*;
import java.util.stream.Stream;

public class TodosTests {
    private Set<Task> taskSet;
    private Deque<Task> taskDeque;
    private Todos todos;

    @BeforeEach
    void initSet() {
        taskSet = new TreeSet<>(Comparator.comparing(Task::getTask));
        taskDeque = new ArrayDeque<>();
        todos = new Todos();
    }

    @ParameterizedTest
    @MethodSource("getArgumentOne")
    void addTaskOneTest(Task taskAdd) {
        taskSet.add(new Task("ADD", "Задание 01"));
        Set<Task> expected = taskSet;

        todos.addTask(taskAdd);

        Set<Task> actual = todos.getTaskSet();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void addTaskThreeTest() {
        taskSet.add(new Task("ADD", "Задание 02"));
        taskSet.add(new Task("ADD", "Задание 01"));
        taskSet.add(new Task("ADD", "Задание 03"));
        Set<Task> expected = taskSet;

        todos.addTask(new Task("ADD", "Задание 01"));
        todos.addTask(new Task("ADD", "Задание 03"));
        todos.addTask(new Task("ADD", "Задание 02"));

        Set<Task> actual = todos.getTaskSet();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void addTaskSevenTest() {
        taskSet.add(new Task("ADD", "Задание 02"));
        taskSet.add(new Task("ADD", "Задание 01"));
        taskSet.add(new Task("ADD", "Задание 03"));
        taskSet.add(new Task("ADD", "Задание 06"));
        taskSet.add(new Task("ADD", "Задание 05"));
        taskSet.add(new Task("ADD", "Задание 04"));
        taskSet.add(new Task("ADD", "Задание 07"));
        Set<Task> expected = taskSet;

        todos.addTask(new Task("ADD", "Задание 01"));
        todos.addTask(new Task("ADD", "Задание 03"));
        todos.addTask(new Task("ADD", "Задание 02"));
        todos.addTask(new Task("ADD", "Задание 06"));
        todos.addTask(new Task("ADD", "Задание 05"));
        todos.addTask(new Task("ADD", "Задание 04"));
        todos.addTask(new Task("ADD", "Задание 07"));
        todos.addTask(new Task("ADD", "Задание 08"));

        Set<Task> actual = todos.getTaskSet();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void removeTaskTest() {
        taskSet.add(new Task("ADD", "Задание 01"));
        taskSet.add(new Task("ADD", "Задание 03"));
        Set<Task> expected = taskSet;
        todos.addTask(new Task("ADD", "Задание 01"));
        todos.addTask(new Task("ADD", "Задание 03"));
        todos.addTask(new Task("ADD", "Задание 02"));

        todos.removeTask(new Task("ADD", "Задание 02"));

        Set<Task> actual = todos.getTaskSet();
        Assertions.assertEquals(expected, actual);
    }

    @Test
    void getAllTasksTest() {
        String expected = "Акробатика Пробежка Учёба";
        todos.addTask(new Task("ADD", "Пробежка"));
        todos.addTask(new Task("ADD", "Акробатика"));
        todos.addTask(new Task("ADD", "Учёба"));

        String actual = todos.getAllTasks();

        Assertions.assertEquals(expected, actual);
    }

    @Test
    void restoreTaskTest() {
        String expected = "Пробежка Учёба";
        todos.addTask(new Task("ADD", "Пробежка"));
        todos.addTask(new Task("ADD", "Акробатика"));
        todos.addTask(new Task("ADD", "Учёба"));
        todos.removeTask(new Task("REMOVE", "Акробатика"));
        todos.addTask(new Task("ADD", "Футбол"));

        todos.restoreTask();

        String actual = todos.getAllTasks();
        Assertions.assertEquals(expected, actual);
    }

    private static Stream<Arguments> getArgumentOne() {
        return Stream.of(
                Arguments.of(new Task("ADD", "Задание 01"))
        );
    }
}
