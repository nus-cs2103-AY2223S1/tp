package seedu.address.model.module.exceptions;

public class DuplicateModuleException extends RuntimeException{
    public DuplicateModuleException() {
        super("This would result in duplicate modules being added.");
    }
}
