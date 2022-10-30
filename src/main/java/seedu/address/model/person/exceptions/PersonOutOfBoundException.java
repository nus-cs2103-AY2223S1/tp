package seedu.address.model.person.exceptions;

import seedu.address.logic.commands.exceptions.CommandException;

/**
 * Encapsulates an OutOfBoundException for Person
 */
public class PersonOutOfBoundException extends CommandException {

    public PersonOutOfBoundException(int length, int index) {
        super(String.format("Person out of bounds. Length is only %d yet index %d supplied.", length, index));
    }

}
