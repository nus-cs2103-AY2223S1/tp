package seedu.address.model.person.exceptions;

/**
 * Encapsulates an OutOfBoundException for Person
 */
public class PersonOutOfBoundException extends Exception {

    public PersonOutOfBoundException(int length, int index) {
        super(String.format("Person out of bounds. Length is only %d yet index %d supplied.", length, index));
    }

}
