package seedu.address.model.task;

import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.Iterator;

public class TaskList {
    private static ArrayList<Task> tempStorage = new ArrayList<>();

    // Commented out parts of code related to saving into storage.
    // TaskUi is temporary until we update Ui.
    // private Storage saveFile;

    public String addTask(Task task) {

        tempStorage.add(task);
        assert tempStorage.contains(task) : "Task failed to be added to temporary storage";

        return TaskUi.addText(tempStorage.get(tempStorage.size() - 1).toString(), tempStorage.size());
    }

    /**
     *  Removes the specified Task from storage if it exists.
     *
     *  If the specified Task does not exist, a statement telling the user that
     *  the specified does not exist is printed.
     *
     * @param index The index of the Task to be removed from storage.
     * @return A response to be displayed to the user.
     */
    public String delete(int index) {
        assert index >= 0 : "Index does not exist.";
        assert tempStorage != null : "No temporary storage created.";
        // assert saveFile != null : "No save file provided.";

        try {
            Task toRemove = tempStorage.remove(index);
            // saveFile.reload(tempStorage);

            return TaskUi.deleteText(toRemove.toString(), tempStorage.size());
        } catch (IndexOutOfBoundsException e) {
            return TaskUi.taskNotFoundText();
        }

    }

    /**
     * Lists all the tasks currently being stored.
     *
     * @return A response to be displayed to the user.
     */
    public String list() {
        String list = "Here are the tasks in your list:\n";

        int index = 1;
        Iterator<Task> iterator = tempStorage.iterator();
        while (iterator.hasNext()) {
            String task = index + ". " + iterator.next().toString() + "\n";
            list = list + task;

            index++;
        }

        return list;
    }

}
