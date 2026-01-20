package storage;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import model.Task;

public class TaskRepository {
    private final Path dataDir = Paths.get("data");
    private final Path filePath = dataDir.resolve("tasks.json");

    private final Gson gson = new GsonBuilder().setPrettyPrinting().create();
    private final Type listType = new TypeToken<List<Task>>() {}.getType();

    private void ensureFile() throws IOException {
        if (!Files.exists(dataDir)) {
            Files.createDirectories(dataDir);
        }
        if (!Files.exists(filePath)) {
            try (BufferedWriter w = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)) {
                w.write("[]");
            }
        }
    }
    public List<Task> load() {
        try {
            ensureFile();
            try (BufferedReader r = Files.newBufferedReader(filePath, StandardCharsets.UTF_8)) {
                List<Task> tasks = gson.fromJson(r, listType);
                return (tasks == null) ? new ArrayList<>() : tasks;
            }
        } catch (IOException e) {
            System.out.println("Error reading tasks.json: " + e.getMessage());
            return new ArrayList<>();
        }
    }
    
    public void save(List<Task> tasks) {
        try {
            ensureFile();
            try (BufferedWriter w = Files.newBufferedWriter(filePath, StandardCharsets.UTF_8)) {
                gson.toJson(tasks, listType, w);
            }
        } catch (IOException e) {
            System.out.println("Error saving tasks.json: " + e.getMessage());
        }
    }
}


    
