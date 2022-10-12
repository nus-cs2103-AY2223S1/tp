package seedu.address.logic.task.exceptions;

public class LevelOutOfRangeException extends RuntimeException{
    public LevelOutOfRangeException() {
        super("Level out of acceptable range");
    }
}
