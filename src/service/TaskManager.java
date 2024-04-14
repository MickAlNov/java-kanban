package service;

import model.Epic;
import model.SubTask;
import model.Task;

import java.util.ArrayList;
import java.util.HashMap;

public class TaskManager {

    public HashMap<Integer, Task> taskStorage = new HashMap<>();
    public HashMap<Integer, Epic> epicStorage = new HashMap<>();
    public HashMap<Integer, SubTask> subTaskStorage = new HashMap<>();


    public void addTask(Task task) {
        taskStorage.put(task.getNumberOfTask(), task);
    }
    public void addEpic(Epic epic) {
        epic.getSubTasks().clear();
        epicStorage.put(epic.getNumberOfTask(), epic);
    }
    public void addSubTask(SubTask subTask) {
        Epic ep = subTask.getEpic();
        ep.setSubTasks(subTask);
        subTaskStorage.put(subTask.getNumberOfTask(), subTask);
    }
    public void updateTask(Task task) {
        taskStorage.put(task.getNumberOfTask(), task);
    }
    //Пользователь не может менять  статус Эпика. Метод меняет только описание и имя Эпика
    public void updateEpic(Epic epic) {
        epicStorage.put(epic.getNumberOfTask(), epic);
    }
    public void updateSubTask(SubTask subTask) {
        ArrayList<SubTask> taskList = new ArrayList<>();
        subTaskStorage.put(subTask.getNumberOfTask(), subTask);
        for(Integer st : subTaskStorage.keySet()) {
                if (subTaskStorage.get(st).getEpic().equals(subTask.getEpic()))
                    taskList.add(subTaskStorage.get(st));
        }
        subTask.getEpic().getSubTasks().clear();
        subTask.getEpic().getSubTasks().addAll(taskList);
    }
    public void deleteTask(int id) {
        taskStorage.remove(id);
    }
    public void deleteEpic(int id) {
        epicStorage.get(id).getSubTasks().clear();
        for (Integer i : subTaskStorage.keySet()) {
            if (subTaskStorage.get(i).getEpic().equals(epicStorage.get(id)))
                subTaskStorage.remove(i);
        }
        epicStorage.remove(id);
    }
    public void deleteSubTask(int id) {
        ArrayList<SubTask> taskList = new ArrayList<>();
        Epic epic = subTaskStorage.get(id).getEpic();
        subTaskStorage.remove(id);
        for(Integer st : subTaskStorage.keySet()) {
                if (subTaskStorage.get(st).getEpic().equals(epic))
                    taskList.add(subTaskStorage.get(st));
        }
        epic.getSubTasks().clear();
        epic.getSubTasks().addAll(taskList);
    }
    public ArrayList<Task> getAllTasks() {
        ArrayList<Task> taskList = new ArrayList<>();
        if(taskStorage.isEmpty() && epicStorage.isEmpty())
            return null;
        for(Integer id : taskStorage.keySet()) {
            taskList.add(taskStorage.get(id));
        }
        for(Integer id : epicStorage.keySet()) {
            taskList.add(epicStorage.get(id));
            if (epicStorage.get(id).getSubTasks() != null) {
                taskList.addAll(epicStorage.get(id).getSubTasks());
            }
        }
        return taskList;
    }
    //получение задачи по идентификатору
    public Task getTask(int id) {
        if (taskStorage.containsKey(id)) {
            return taskStorage.get(id);
        } else
        if (epicStorage.containsKey(id)) {
            return epicStorage.get(id);
        } else if (subTaskStorage.containsKey(id)){
            return subTaskStorage.get(id);
        } else return null;
    }
    //Удаление всех задач
    public void deleteAllTasks() {
        taskStorage.clear();
        epicStorage.clear();
        subTaskStorage.clear();
    }
}
