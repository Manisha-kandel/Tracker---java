# Tracker App (Java CLI Task Tracker)

A simple command-line task tracker built in Java, designed for tracking
study sessions and daily practice tasks.  
Tasks are stored locally in `data/tasks.json` using Gson.

---

## Features
- Add tasks from the command line
- List all tasks with completion status
- Mark tasks as done
- Remove tasks
- Persistent storage using JSON (no database required)

---

## Project Structure

```

Tracker app/
lib/                # external jars (Gson)
data/               # runtime data file
tasks.json
src/
app/              # entry point (CLI)
Main.java
model/            # data models
Task.java
Priority.java
Status.java
service/          # business logic
TaskService.java
storage/          # persistence layer (JSON)
TaskRepository.java
bin/                # compiled .class output (generated)

````

---

## Requirements
- Java JDK 21 (JDK 17+ should also work)
- Gson JAR in `lib/` (example: `lib/gson-2.13.2.jar`)

---

## Build (Windows – Git Bash)

From the project root:

```bash
rm -rf bin
mkdir -p bin
javac -cp "lib/*" -d bin $(find src -name "*.java")
````

---

## Run

Windows uses `;` as the classpath separator:

```bash
java -cp "bin;lib\gson-2.13.2.jar" app.Main <command> [args...]
```

---

## Sample Usage

### Add tasks

```bash
java -cp "bin;lib\gson-2.13.2.jar" app.Main add "1 hour Spanish practice"
java -cp "bin;lib\gson-2.13.2.jar" app.Main add "30 minutes Ukulele practice"
java -cp "bin;lib\gson-2.13.2.jar" app.Main add "30 minutes knitting"
java -cp "bin;lib\gson-2.13.2.jar" app.Main add "3 hours DSA practice"
```

---

### List tasks

```bash
java -cp "bin;lib\gson-2.13.2.jar" app.Main list
```

Example output:

```txt
[ ] 1 - 1 hour Spanish practice
[ ] 2 - 30 minutes Ukulele practice
[ ] 3 - 30 minutes knitting
[ ] 4 - 3 hours DSA practice
```

---

### Mark a task as done

```bash
java -cp "bin;lib\gson-2.13.2.jar" app.Main done 1
```

Listing again:

```txt
[x] 1 - 1 hour Spanish practice
[ ] 2 - 30 minutes Ukulele practice
[ ] 3 - 30 minutes knitting
[ ] 4 - 3 hours DSA practice
```

---

### Remove a task

```bash
java -cp "bin;lib\gson-2.13.2.jar" app.Main remove 3
```

---

## Data File

Tasks are persisted in:

```
data/tasks.json
```

Example contents:

```json
[
  {
    "id": 1,
    "title": "1 hour Spanish practice",
    "done": true
  },
  {
    "id": 2,
    "title": "30 minutes Ukulele practice",
    "done": false
  }
]
```

---

## Notes

* `bin/` contains compiled files and can be safely deleted anytime.
* The application is intentionally framework-free (no Spring, no database).
* Designed as a learning project to practice Java CLI apps, packages,
  services, repositories, and JSON persistence.

---

## Future Improvements

* Interactive (menu-driven) mode
* Filters (done / pending)
* Sorting by time or priority
* Better use of `Priority` and `Status`

```
