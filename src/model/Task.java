package model;

import java.util.Objects;

public class Task {
    private String name;
    private String description;
    private final int id;
    private StatusOfTask status;

    public Task(String name, String description, int id) {
        this.name = name;
        this.description = description;
        this.id = id;
    }
    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.id = IdGenerator.generate();
        this.status = StatusOfTask.NEW;
    }

    public String getName() {
        return name;
    }
    public String getDescription() {
        return description;
    }
    public int getId() {
        return id;
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

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return id == task.id && Objects.equals(name, task.name) && Objects.equals(description, task.description) && status == task.status;
    }
    @Override
    public int hashCode() {
        return Objects.hash(name, description, id, status);
    }

    @Override
    public String toString() {
        return "Задача{ " +
                "Название='" + name + '\'' +
                ", Описание='" + description + '\'' +
                ", ID=" + id +
                ", Статус=" + status +
                '}';
    }
}
