package seedu.address.model.ta.exceptions;

public class DuplicateTeachingAssistantException extends RuntimeException {
    public DuplicateTeachingAssistantException() {
        super("Operation would result in duplicate teaching assistants!");
    }
}
