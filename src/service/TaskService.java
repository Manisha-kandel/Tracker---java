package service;

import java.util.List;

import model.Task;
import storage.TaskRepository;

public class TaskService {
    private final TaskRepository repo;

    public TaskService(TaskRepository repo) {
        this.repo = repo;
    }

    public Task add(String title) {
        List<Task> tasks = repo.load();

        int nextId = 1;
        for (Task t : tasks) {
            nextId = Math.max(nextId, t.id + 1);
        }

        Task newTask = new Task(nextId, title, false);
        tasks.add(newTask);
        repo.save(tasks);
        return newTask;
    }

    public List<Task> list() {
        return repo.load();
    }

    public boolean remove(int id) {
        List<Task> tasks = repo.load();
        boolean removed = tasks.removeIf(t -> t.id == id);
        if (removed) {
            repo.save(tasks);
        }
        return removed;
    }

    public boolean markDone(int id) {
        List<Task> tasks = repo.load();
        for (Task t : tasks) {
            if (t.id == id) {
                t.done = true;
                repo.save(tasks);
                return true;
            }
        }
        return false;
    }
}