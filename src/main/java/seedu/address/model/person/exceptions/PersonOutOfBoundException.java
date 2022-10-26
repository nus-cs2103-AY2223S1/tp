package seedu.address.model.person.exceptions;

public class PersonOutOfBoundException extends Exception {

    public PersonOutOfBoundException(int length, int index) {
        super(String.format("Person out of bounds. Length is only %d yet index %d supplied.", length, index));
    }

}
