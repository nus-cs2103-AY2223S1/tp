package seedu.taassist.model.uniquelist.exceptions;

/**
 * Signals that the operation is unable to find the specified element.
 */
public class ElementNotFoundException extends RuntimeException {

    public ElementNotFoundException() {
        super("A matching element was not found");
    }
}
