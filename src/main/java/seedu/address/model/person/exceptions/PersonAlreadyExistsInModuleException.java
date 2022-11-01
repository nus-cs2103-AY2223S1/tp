package seedu.address.model.person.exceptions;

/**
 * Signals that the person has already been added to the module, hence cannot be added to the
 * module again.
 */
public class PersonAlreadyExistsInModuleException extends RuntimeException {
    public PersonAlreadyExistsInModuleException() {
        super("Person already exists in module");
    }
}
