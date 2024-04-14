package model;

import java.util.ArrayList;
import java.util.Objects;

public class Epic extends Task {
    ArrayList<SubTask> subTasks;
    public Epic(String name, String description) {
        super(name, description);
        this.subTasks = new ArrayList<>();
    }

    public ArrayList<SubTask> getSubTasks() {
        if (subTasks == null)
            return null;
        else
            return subTasks;
    }

    public void setSubTasks(SubTask subTask) {
        subTasks.add(subTask);
    }

    @Override
    public StatusOfTask getStatus() {
        boolean statusDone = true;
        boolean statusNew = true;
        if (subTasks.isEmpty())
            return StatusOfTask.NEW;
        for (SubTask st : subTasks) {
                if (st.getStatus() != StatusOfTask.DONE) {
                    statusDone = false;
                }
                if (st.getStatus() != StatusOfTask.NEW) {
                    statusNew = false;
                }
        }
        if (statusDone) {
            return StatusOfTask.DONE;
        }
        if (statusNew) {
            return StatusOfTask.NEW;
        }
        return  StatusOfTask.IN_PROGRESS;
    }

    @Override
    public void setStatus(String status) {
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;
        Epic epic = (Epic) o;
        return Objects.equals(subTasks, epic.subTasks);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), subTasks);
    }

    @Override
    public String toString() {
        return "Эпик { " +
                "Название='" + this.getName() + '\'' +
                ", Описание='" + this.getDescription() + '\'' +
                ", ID=" + this.getNumberOfTask() +
                ", Статус=" + this.getStatus() +
                '}';
    }
}
