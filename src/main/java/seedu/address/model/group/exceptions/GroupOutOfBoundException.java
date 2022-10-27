package seedu.address.model.group.exceptions;

/**
 * Encapsulates an OutOfBoundException for a Group
 */
public class GroupOutOfBoundException extends Exception {

    public GroupOutOfBoundException(int length, int index) {
        super(String.format("Group out of bounds. Length is only %d yet index %d supplied.", length, index));
    }

}
