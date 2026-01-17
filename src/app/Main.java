package app;
import service.TaskService;
import model.Priority;
import model.Status;
import model.Task;
import storage.TaskRepository;


public class Main {
    public static void main(String[] args) {
        if (args.length == 0) 
            {
            System.out.println("No arguments provided");
            printHelp();
            return;
            }
        
        String cmd = args[0];

        if (cmd.equals("add")){ 
            if (args.length <2) {
                System.out.println("Missing title.");
                System.out.println("Usage example: java Main add \"Buy Milk\"");
                return;
            }
            System.out.println("ADD: " + args[1]);
            return;
            }

        if (cmd.equals("list")){
            try {
                java.nio.file.Path dir = java.nio.file.Paths.get("data");
                java.nio.file.Path file = dir.resolve("tasks.json");

                if (!java.nio.file.Files.exists(dir)) {
                    java.nio.file.Files.createDirectories(dir);
                }

                if (!java.nio.file.Files.exists(file)) {
                    java.nio.file.Files.writeString(file, "[]");
                }

                String json = java.nio.file.Files.readString(file);
                System.out.println("tasks.json content:");
                System.out.println(json);

            } catch (Exception e) {
                System.out.println("Error reading tasks.json: " + e.getMessage());
            }
            return;

            }

        if (cmd.equals("remove")){
            if (args.length <2) {
                System.out.println("Missing task ID.");
                System.out.println("Usage example: java Main remove 1");
                return;
            }
            System.out.println("REMOVE task ID: " + args[1]);
            printHelp();
            }
    

        } 

    private static void printHelp() {
            System.out.println("Commands are as follows:");
            System.out.println(" add \"title here\"");
            System.out.println(" remove \"id of task here\"");
            System.out.println(" list");
            }

    }
