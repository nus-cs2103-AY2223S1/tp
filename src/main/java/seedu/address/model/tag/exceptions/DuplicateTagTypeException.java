package seedu.address.model.tag.exceptions;

public class DuplicateTagTypeException extends RuntimeException {
    public DuplicateTagTypeException() {
        super("Operation would result in duplicate tag types");
    }
}

