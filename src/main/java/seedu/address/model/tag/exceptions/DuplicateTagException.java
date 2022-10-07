package seedu.address.model.tag.exceptions;

public class DuplicateTagException extends RuntimeException {
    public DuplicateTagException() {
        super("Operation would result in duplicate tag names");
    }
}
