package model;

public class Task {
    public int id;
    public String title;
    public boolean done;

    public Task(int id, String title, boolean done) {
        this.id = id;
        this.title = title;
        this.done = done;
    }
}