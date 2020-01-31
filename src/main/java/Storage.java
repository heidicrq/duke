import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Deals with loading tasks from the file and saving tasks in the file
 */
public class Storage {
    TaskList taskList = new TaskList();
    private static File file;

    public Storage() {
        file = new File("./data/duke.txt");
    }

    public void loadData() throws IOException, DukeException {
        // load data from ./data/duke.txt
        file.createNewFile();

        BufferedReader br = new BufferedReader(new FileReader(file));
        String str;

        while ((str = br.readLine()) != null) {
            Task task;
            String[] data = str.split("\\|");
            if (data[0].equals("T")) {
                task = taskList.createAndAddTask("todo", "todo " + data[2]);
            } else if (data[0].equals("E")){
                task = taskList.createAndAddTask("event", "event " + data[2] + " /at " + data[3]);
            } else {
                task = taskList.createAndAddTask("deadline", "deadline " + data[2] + " /by " + data[3]);
            }

            if (data[1].equals("1")) {
                task.markAsDone();
            }
        }
    }

    public void saveData() throws IOException {
        // save data into ./data/duke.txt
        if (taskList.isListEmpty()) {
            Files.write(Paths.get("./data/duke.txt"), ("").getBytes());
        } else {
            FileOutputStream fos = new FileOutputStream(file);
            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(fos));
            for (int i = 0; i < Task.totalTasks; i++) {
                bw.write(taskList.getTask(i).saveString());
                bw.newLine();
            }
            bw.close();
        }
    }
}