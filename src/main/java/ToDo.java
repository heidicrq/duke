public class ToDo extends Task{
    public ToDo(String description) {
        super(description);
        totalTasks++;
    }

    public String getType() {
        return "[T]";
    }

    @Override
    public void taskSummary() {
        System.out.println(getType() + getStatusIcon() + " " + description);
    }

    public String saveString() {
        return "T|" + (isDone? "1|" : "0|") + description;
    }


}