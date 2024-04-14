package model;

import java.util.Objects;

public class Task {
    private String name;
    private String description;
    private final int numberOfTask;
    private StatusOfTask status;

    public Task(String name, String description, int numberOfTask) {
        this.name = name;
        this.description = description;
        this.numberOfTask = numberOfTask;
    }
    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.numberOfTask = IdGenerator.generate();
        this.status = StatusOfTask.NEW;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getNumberOfTask() {
        return numberOfTask;
    }
    public StatusOfTask getStatus() {
        return status;
    }

    public void setStatus(String status) {
        switch (status) {
            case "NEW":
                this.status = StatusOfTask.NEW;
                break;
            case "IN_PROGRESS":
                this.status = StatusOfTask.IN_PROGRESS;
                break;
            case "DONE":
                this.status = StatusOfTask.DONE;
                break;
            default:
                System.out.println("Такого статуса нет");
        }
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return numberOfTask == task.numberOfTask && Objects.equals(name, task.name) && Objects.equals(description, task.description) && status == task.status;
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, description, numberOfTask, status);
    }

    @Override
    public String toString() {
        return "Задача{ " +
                "Название='" + name + '\'' +
                ", Описание='" + description + '\'' +
                ", ID=" + numberOfTask +
                ", Статус=" + status +
                '}';
    }
}
