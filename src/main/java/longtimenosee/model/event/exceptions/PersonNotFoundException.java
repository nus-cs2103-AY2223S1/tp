package longtimenosee.model.event.exceptions;

/**
 * Thrown when the user attempts to add an event
 * And the specified person doesn't exist.
 */
public class PersonNotFoundException extends RuntimeException {
    public PersonNotFoundException() {
        super("The person you specified doesn't exist.");
    }

}
