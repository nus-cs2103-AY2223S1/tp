package seedu.address.model.task.exceptions;

import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Encapsulates an OutOfBoundException for Task
 */
public class TaskOutOfBoundException extends CommandException {

    public TaskOutOfBoundException(int length, int index) {
        super(String.format("Task out of bounds. Length is only %d yet index %d supplied.", length, index));
    }

}
