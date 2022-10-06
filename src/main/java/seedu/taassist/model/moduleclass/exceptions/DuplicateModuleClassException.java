package seedu.taassist.model.moduleclass.exceptions;

/**
 * Signals that the operation will result in duplicate ModuleClasses (ModuleClasses are considered duplicates if they
 * have the same identity).
 */
public class DuplicateModuleClassException extends RuntimeException {
    public DuplicateModuleClassException() {
        super("Operation would result in duplicate classes");
    }
}
