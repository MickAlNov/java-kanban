public class SubTask extends Task {
    //поле связи с Эпиком
    private int epicID;
    //Конструктор
    public SubTask(String name, String description, int epicID) {
        super(name, description);
        this.epicID = epicID;
    }

    public SubTask(String name, String description, int numberOfTask, StatusOfTask status, int epicID) {
        super(name, description, numberOfTask, status);
        this.epicID = epicID;
    }
    //Геттер

    public int getEpicID() {
        return epicID;
    }

    @Override
    public void setStatus(StatusOfTask status) {
        super.setStatus(status);
    }
}
