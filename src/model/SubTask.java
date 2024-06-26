package model;

import java.util.Objects;

public class SubTask extends Task {
    Epic epic;
    public SubTask(String name, String description, Epic epic) {
        super(name, description);
        this.epic = epic;
    }
    public SubTask(String name, String description, int id, Epic epic) {
        super(name, description, id);
        this.epic = epic;
    }
    public Epic getEpic() {
        return epic;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        SubTask subTask = (SubTask) o;
        return Objects.equals(epic, subTask.epic);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), epic);
    }

    @Override
    public String toString() {
        return "\tПодзадача{ " +
                "Название='" + this.getName() + '\'' +
                ", Описание='" + this.getDescription() + '\'' +
                ", ID=" + this.getId() +
                ", Статус=" + this.getStatus() +
                '}';
    }
}
