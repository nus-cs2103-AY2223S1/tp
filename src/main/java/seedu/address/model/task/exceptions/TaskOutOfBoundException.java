package seedu.address.model.task.exceptions;

/**
 * Encapsulates an OutOfBoundException for Task
 */
public class TaskOutOfBoundException extends Exception {

    public TaskOutOfBoundException(int length, int index) {
        super(String.format("Task out of bounds. Length is only %d yet index %d supplied.", length, index));
    }

}
