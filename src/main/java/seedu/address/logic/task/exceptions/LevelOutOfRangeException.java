package seedu.address.logic.task.exceptions;

/**
 * Represents an exception when the level of a task is beyond the range of 1 to 5
 */
public class LevelOutOfRangeException extends RuntimeException {
    public LevelOutOfRangeException() {
        super("Level out of acceptable range");
    }
}
