package app;

import java.util.List;

import service.TaskService;
// import model.Priority;
// import model.Status;
import model.Task;
import storage.TaskRepository;


public class Main {
    public static void main(String[] args) {

        TaskService service = new TaskService(new TaskRepository());

        if (args.length == 0) 
            {
            System.out.println("No arguments provided");
            printHelp();
            return;
            }
        
        String cmd = args[0];
        
        switch (cmd) {
            case "add": {
                if (args.length < 2) {
                    System.out.println("Missing title.");
                    System.out.println("Usage: add \"Buy Milk\"");
                    return;
                }
                Task t = service.add(args[1]);
                System.out.println("Added: [" + t.id + "] " + t.title);
                return;
            }

            case "list": {
                List<Task> tasks = service.list();
                if (tasks.isEmpty()) {
                    System.out.println("(no tasks yet)");
                    return;
                }
                for (Task t : tasks) {
                    String box = t.done ? "[x]" : "[ ]";
                    System.out.println(box + " " + t.id + " - " + t.title);
                }
                return;
            }

            case "remove": {
                if (args.length < 2) {
                    System.out.println("Missing task ID.");
                    System.out.println("Usage: remove 1");
                    return;
                }
                try {
                    int id = Integer.parseInt(args[1]);
                    boolean ok = service.remove(id);
                    System.out.println(ok ? ("Removed task " + id) : ("No task found with id " + id));
                } catch (NumberFormatException e) {
                    System.out.println("Task ID must be an integer.");
                }
                return;
            }

            case "done": {
                if (args.length < 2) {
                    System.out.println("Missing task ID.");
                    System.out.println("Usage: done 1");
                    return;
                }
                try {
                    int id = Integer.parseInt(args[1]);
                    boolean ok = service.markDone(id);
                    System.out.println(ok ? ("Marked done: " + id) : ("No task found with id " + id));
                } catch (NumberFormatException e) {
                    System.out.println("Task ID must be an integer.");
                }
                return;
            }

            default:
                System.out.println("Unknown command: " + cmd);
                printHelp();
        }
    }


    private static void printHelp() {
            System.out.println("Commands are as follows:");
            System.out.println(" add \"title here\"");
            System.out.println(" remove \"id of task here\"");
            System.out.println(" list");
            System.out.println(" done \"id of the task here\">");
            }

    }
