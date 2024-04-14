package main;

import model.Epic;
import model.SubTask;
import model.Task;
import service.TaskManager;

import java.util.ArrayList;
import java.util.Scanner;
/*В этом классе я постарался учесть всевозможные вызовы для проверки
работоспособности методов из класса TaskManager*/
public class Main {

    static TaskManager tm;
    static Scanner scanner;

    public static void main(String[] args) {
        tm = new TaskManager();
        scanner = new Scanner(System.in);

        while (true) {
            printMenu();
            String command = scanner.nextLine();
            switch (command) {
                case "1":
                    while(true) {
                        printTaskMenu();
                        String commandTask = scanner.nextLine();
                        if (commandTask.equals("1")) {
                            createTask();
                            break;
                        } else if (commandTask.equals("2")) {
                            createEpic();
                            break;
                        } else {
                            System.out.println("Команда не распознана! Введите 1 или 2");
                        }
                    }
                    break;
                case "2":
                    update();
                    break;
                case "3":
                    printListOfTasks();
                    break;
                case "4":
                    getTaskByID();
                    break;
                case "5":
                    getAndPrintTasksByEpic();
                    break;
                case "6":
                    deleteTasks();
                    break;
                case "7":
                    deleteTaskByID();
                    break;
                case "8":
                    return;
                default:
                    System.out.println("Команда не распознана! Введите 1, 2, 3, 4 ,5, 6, 7 или 8");
                    break;
            }
        }

    }
    private static void printMenu() {
        System.out.println("Выберите команду:");
        System.out.println("1 - Создать задачу");
        System.out.println("2 - Обновление");
        System.out.println("3 - Распечатать список задач");
        System.out.println("4 - Получить задачу по идентификатору");
        System.out.println("5 - Получить список всех подзадач для Эпика");
        System.out.println("6 - Удаление всех задач");
        System.out.println("7 - Удаление задачи(эпика, подзадачи) по идентификатору");

        System.out.println("8 - Выход");
    }
    private static void printTaskMenu() {
        System.out.println("Создать Задачу или Эпик?:");
        System.out.println("1 - Создать задачу");
        System.out.println("2 - Создать Эпик");
    }
    private static void printSubTaskMenu() {
        System.out.println("Ввести подзадачу? (1 - ввести новуб подзадачу, 2 - прекратить ввод подзадач):");
        System.out.println("1 - Ввести подзадачу");
        System.out.println("2 - Выйти");
    }
    private static void createTask() {
        System.out.println("Введите название задачи:");
        String taskName = scanner.nextLine();
        System.out.println("Введите описание задачи:");
        String taskDescription = scanner.nextLine();
        Task newTask = new Task(taskName, taskDescription);
        tm.addTask(newTask);
    }
    private static void createEpic() {
        System.out.println("Введите название Эпика:");
        String epicName = scanner.nextLine();
        System.out.println("Введите описание Эпика:");
        String epicDescription = scanner.nextLine();
        Epic epic = new Epic(epicName, epicDescription);
        tm.addEpic(epic);
        System.out.println("А теперь введите подзадачи");
        createSubTask(epic);
    }
    private static void createSubTask(Epic epic) {
        while (true) {
            printSubTaskMenu();
            String commandSubTask = scanner.nextLine();
            switch(commandSubTask) {
                case "1":
                    System.out.println("Введите название Подзадачи:");
                    String subTaskName = scanner.nextLine();
                    System.out.println("Введите описание Подзадачи:");
                    String subTaskDescription = scanner.nextLine();
                    SubTask subTask = new SubTask(subTaskName, subTaskDescription, epic);
                    tm.addSubTask(subTask);
                    break;
                case "2":
                    return;
                default:
                    System.out.println("Команда не распознана! Введите 1 или 2");
                    break;
            }
        }
    }
    private static void deleteTasks() {
        tm.deleteAllTasks();
        System.out.println("Таблица задач полностью очищена!");
    }
    private static void printListOfTasks() {
        ArrayList<Task> taskList = tm.getAllTasks();
        if (taskList != null) {
            System.out.println("Список всех задач:");
            for (Task task : taskList) {
                System.out.println(task);
            }
        } else {
            System.out.println("Список задач пуст!");
        }
    }
    private static void getAndPrintTasksByEpic() {
        System.out.println("Введите ID Эпика:");
        int IDTask = scanner.nextInt();
        scanner.nextLine();
        if (!tm.epicStorage.containsKey(IDTask)) {
            System.out.println("Эпика с таким ID не сущкствует!");
        } else {
            System.out.println("Список всех подзадач Эпика с ID = " + IDTask + ":");
            for (Task subTask : tm.epicStorage.get(IDTask).getSubTasks()) {
                System.out.println(subTask);
            }
        }
    }
    private static void getTaskByID() {
        System.out.println("Введите ID задачи:");
        int IDTask = scanner.nextInt();
        scanner.nextLine();
        if (tm.getTask(IDTask) != null) {
            System.out.println(tm.getTask(IDTask));
        } else {
            System.out.println("Задачи с таким ID не существует!");
        }
    }

    private static void deleteTaskByID() {
        System.out.println("Введите ID задачи:");
        int IDTask = scanner.nextInt();
        scanner.nextLine();
        if (tm.getTask(IDTask) == null) {
            System.out.println("Задачи с таким ID не существует!");
        } else if(tm.taskStorage.containsKey(IDTask)) {
            tm.deleteTask(IDTask);
            System.out.println("Задача удалена!");
        } else if (tm.epicStorage.containsKey(IDTask)) {
            tm.deleteEpic(IDTask);
            System.out.println("Эпик удален!");
        } else if (tm.subTaskStorage.containsKey(IDTask)) {
            tm.deleteSubTask(IDTask);
            System.out.println("Подзадача удалена!");
        }
    }
    private  static void update() {
        System.out.println("Введите идентификатор задачи для обновления:");
        int taskID;
        do {
            taskID = scanner.nextInt();
            scanner.nextLine();
            if (tm.epicStorage.containsKey(taskID)) {
                System.out.println("Пользователь не должен менять статус Эпика самостоятельно");
                System.out.println("Введите ID для смены статуса Задачи или Подзадачи:");
            } else if (tm.taskStorage.containsKey(taskID) || tm.subTaskStorage.containsKey(taskID))
                break;
            else {
                System.out.println("Задачи с таким ID не существует. Введите другой ID Задачи или Подзадачи");
            }
        } while(true);
        System.out.println("Введите статус (NEW, IN_PROGRESS, DONE):");
        String taskStatus = scanner.nextLine().toUpperCase();

        if (tm.taskStorage.containsKey(taskID)) {
            Task task = new Task(tm.taskStorage.get(taskID).getName(),  tm.taskStorage.get(taskID).getDescription(),
                    tm.taskStorage.get(taskID).getNumberOfTask());
            task.setStatus(taskStatus);
            tm.updateTask(task);
        } else if (tm.subTaskStorage.containsKey(taskID)) {
            SubTask subTask = new SubTask(tm.subTaskStorage.get(taskID).getName(),
                    tm.subTaskStorage.get(taskID).getDescription(), tm.subTaskStorage.get(taskID).getNumberOfTask(),
                    tm.subTaskStorage.get(taskID).getEpic());
            subTask.setStatus(taskStatus);
            tm.updateSubTask(subTask);
        }

    }
}

