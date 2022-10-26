package seedu.address.model.module.exceptions;

/**
 * DuplicateModuleException class represents an exception that there
 * are duplicate modules being added.
 */
public class DuplicateModuleException extends RuntimeException {
    public DuplicateModuleException() {
        super("This would result in duplicate modules being added.");
    }
}
