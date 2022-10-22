package seedu.address.model.module.exceptions;

public class NullModuleCodeException extends RuntimeException {
    public NullModuleCodeException() {
        super("A module should have been instantiated with a ModuleCode field.");
    }
}
