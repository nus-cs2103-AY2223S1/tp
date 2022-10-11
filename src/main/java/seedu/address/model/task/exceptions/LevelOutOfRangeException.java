package seedu.address.model.task.exceptions;

public class LevelOutOfRangeException extends RuntimeException{
    public LevelOutOfRangeException() {
        super("Level out of acceptable range");
    }
}
