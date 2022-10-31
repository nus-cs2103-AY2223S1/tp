package seedu.address.model.task.exceptions;

import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Encapsulates an OutOfBoundException for Task
 */
public class TaskOutOfBoundException extends CommandException {

    public static final String ERR_MSG = "Task out of bounds. Length is only %d yet index %d supplied.";

    public TaskOutOfBoundException(int length, int index) {
        super(String.format(ERR_MSG, length, index));
    }

}
