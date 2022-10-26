package seedu.address.model.task.exceptions;

public class TaskOutOfBoundException extends Exception {

    public TaskOutOfBoundException(int length, int index) {
        super(String.format("Task out of bounds. Length is only %d yet index %d supplied.", length, index));
    }

}
