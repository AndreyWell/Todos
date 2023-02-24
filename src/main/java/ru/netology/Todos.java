package ru.netology;

import java.util.*;

public class Todos {
    private Set<Task> taskSet;
    private Deque<Task> taskDeque;
    private int selector = 0;

    public Todos() {
        taskSet = new TreeSet<>(Comparator.comparing(Task::getTask));
        taskDeque = new ArrayDeque<>();
    }

    public Set<Task> getTaskSet() {
        return taskSet;
    }

    public Deque<Task> getTaskDeque() {
        return taskDeque;
    }

    public void addTask(Task taskAdd) {
        if (taskSet.size() < 7) {
            if (selector == 0) {
                taskDeque.offer(taskAdd);
            }
            taskSet.add(taskAdd);
        }
    }

    public void removeTask(Task taskRemove) {
        if (taskSet.size() >= 1) {
            if (selector == 0) {
                taskDeque.offer(taskRemove);
            }
            taskSet.remove(taskRemove);
        }
    }

    public String getAllTasks() {
        StringBuilder sb = new StringBuilder();
        taskSet.forEach(taskGet -> sb.append(taskGet.getTask() + " "));
        return sb.toString();
    }

    public void restoreTask() {
        if (!taskDeque.isEmpty()) {
            selector++;
            Task restoreTask = taskDeque.pollLast();

            if (restoreTask.getType().equals("ADD")) {
                removeTask(restoreTask);
            }

            if (restoreTask.getType().equals("REMOVE")) {
                addTask(restoreTask);
            }
            selector--;
        }
    }

    @Override
    public String toString() {
        return "Todos{" +
                "stringSet=" + taskSet +
                '}';
    }
}
