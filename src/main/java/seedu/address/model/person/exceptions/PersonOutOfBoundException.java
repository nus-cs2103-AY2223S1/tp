package seedu.address.model.person.exceptions;

import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Encapsulates an OutOfBoundException for Person
 */
public class PersonOutOfBoundException extends CommandException {
    public static final String ERR_MSG = "Person out of bounds. Length is only %d yet index %d supplied.";

    public PersonOutOfBoundException(int length, int index) {
        super(String.format(ERR_MSG, length, index));
    }

}
