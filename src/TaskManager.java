import java.util.ArrayList;
import java.util.HashMap;


public class TaskManager {

    private static int ID;
    HashMap<Integer, Task> taskStorage = new HashMap<>();
    HashMap<Integer, SubTask> epicStorage = new HashMap<>();

    //Конструктор

    //Геттер
    public static int getID() {
        return ++ID;
    }

    //Методы

    //Создание. Сам объект должен передаваться в качестве параметра.
    void addTask(Task newTask) {
        taskStorage.put(newTask.getNumberOfTask(), newTask);
        System.out.println("Эпик ---- " + newTask.label);
    }
    //Создать Эпик
    void addSubTask(Task newTask, SubTask newSubTask) {
        //если такого эпика еще нет
        if (taskStorage.isEmpty() || !taskStorage.containsKey(newTask.getNumberOfTask())) {
            addTask(newTask);
        } else {
            epicStorage.put(newSubTask.getNumberOfTask(), newSubTask);
        }
    }
    //Удаление всех задач
    void deleteAllTasks() {
        taskStorage.clear();
        epicStorage.clear();
    }

    //получение списка всех подзадач для определенного Эпика
    ArrayList<Task> getAllTasksByEpic(Task newTask) {
        ArrayList<Task> subTaskList = new ArrayList<>();
        for(Integer id : epicStorage.keySet()) {
                if (epicStorage.get(id).getEpicID() == newTask.getNumberOfTask()) {
                    subTaskList.add(epicStorage.get(id));
                }
        }
        return subTaskList;
    }
    ArrayList<ArrayList<Task>> getAllTasks() {
        ArrayList<ArrayList<Task>> taskList = new ArrayList<>();

        for(Integer id : taskStorage.keySet()) {
            ArrayList nestedList = new ArrayList();
            Task task = taskStorage.get(id);
            nestedList.add(task);
            for (Integer es : epicStorage.keySet()) {
                if(epicStorage.get(es).getEpicID() == task.getNumberOfTask()) {
                    nestedList.add(epicStorage.get(es));
                }
            }
            taskList.add(nestedList);
        }
        return taskList;
    }
    // Получение по идентификатору.
    Task getTask(int id) {
        if (taskStorage.containsKey(id)) {
            return taskStorage.get(id);
        } else
        if (epicStorage.containsKey(id)) {
            return epicStorage.get(id);
        } else {
            return null;
        }
    }
    //удаление задачи по идентификатору
    void deleteTask(int id) {
        if (taskStorage.containsKey(id)) {
            if (!taskStorage.get(id).label) {
                taskStorage.remove(id);
            } else {
                for (SubTask i : epicStorage.values()) {
                    if (i.getEpicID() == id) {
                        epicStorage.remove(i.getNumberOfTask());
                    }
                }
                taskStorage.remove(id);
            }
        }
        if (epicStorage.containsKey(id))
            epicStorage.remove(id);
    }
    //Обновление
    void updateTask(Task newTask) {
        if (taskStorage.containsKey(newTask.getNumberOfTask())) {
            taskStorage.put(newTask.getNumberOfTask(), newTask);
        }  else if (epicStorage.containsKey(newTask.getNumberOfTask())) {
            SubTask subTask = new SubTask(newTask.getName(), newTask.getDescription(),
                    newTask.getNumberOfTask(), newTask.getStatus(), epicStorage.get(newTask.getNumberOfTask()).getEpicID());
            epicStorage.put(newTask.getNumberOfTask(), subTask);
            updateEpic(subTask.getEpicID());
        } else {
            System.out.println("Задач с таким ID не существует");
            return;
        }
    }
    //Метод для update Статуса в Эпике
    void updateEpic(int id) {
        boolean statusDone = true;
        boolean statusNew = true;
        for (Integer i : epicStorage.keySet()) {
            if (epicStorage.get(i).getEpicID() == id) {
                if (epicStorage.get(i).getStatus() != StatusOfTask.DONE) {
                    statusDone = false;
                }
                if (epicStorage.get(i).getStatus() != StatusOfTask.NEW) {
                        statusNew = false;
                }
            }
        }
        if (statusDone) {
            taskStorage.get(id).setStatus(StatusOfTask.DONE);
        } else
        if (statusNew) {
            taskStorage.get(id).setStatus(StatusOfTask.NEW);
        } else {
            taskStorage.get(id).setStatus(StatusOfTask.IN_PROGRESS);
        }
    }
}

