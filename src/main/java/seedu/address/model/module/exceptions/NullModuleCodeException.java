package seedu.address.model.module.exceptions;

/**
 * Signals that a module object was instantiated without a module code field.
 */
public class NullModuleCodeException extends RuntimeException {
    public NullModuleCodeException() {
        super("A module should have been instantiated with a ModuleCode field.");
    }
}
