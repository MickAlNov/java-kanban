import java.util.Objects;

public class Task {
    private String name;
    private String description;
    private final Integer numberOfTask;
    private StatusOfTask status;
    //метка для определения является класс Эпиком или Задачей (нужно для вывода) в классе Main
    protected boolean label;

    public Task(String name, String description) {
        this.name = name;
        this.description = description;
        this.numberOfTask = TaskManager.getID();
        this.status = StatusOfTask.NEW;
        this.label = false;
    }

    public Task(String name, String description, int numberOfTask, StatusOfTask status) {
        this.name = name;
        this.description = description;
        this.numberOfTask = numberOfTask;
        this.status = status;
        this.label = false;
    }

    //Геттеры
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
    //Сеттеры

    public void setName(String name) {
        this.name = name;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(StatusOfTask status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "numberOfTask = " + numberOfTask +
                ", name = '" + name + '\'' +
                ", description = '" + description + '\'' +
                ", status = " + status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Task task = (Task) o;
        return numberOfTask == task.numberOfTask &&
                Objects.equals(name, task.name) &&
                Objects.equals(description, task.description) &&
                status == task.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(name, description, numberOfTask, status);
    }
}
