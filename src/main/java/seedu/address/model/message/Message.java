package seedu.address.model.message;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

import seedu.address.model.person.Person;

/**
 * Represents a message template in the address book.
 * Used to generate personalised messages.
 * Guarantees: immutable
 */
public class Message {

    public static final String MESSAGE_CONSTRAINTS = "Addresses can take any values, and it should not be blank";

    /*
     * A message should not be an empty string.
     */
    public static final String VALIDATION_REGEX = "^(?!\\s*$).+";
    public static final String NAME_PLACEHOLDER = "{name}";

    private String message;

    /**
     * Constructs a Message object
     * @param message
     */
    public Message(String message) {
        requireNonNull(message);
        checkArgument(isValidMessage(message), MESSAGE_CONSTRAINTS);
        this.message = message;
    }

    /**
     * Returns true if a given string is a valid message.
     */
    public static boolean isValidMessage(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    /**
     * Generate a personalised message for the person, using the person's `fullName`.
     * @param person to generate for
     * @return the personalised message
     */
    public String generate(Person person) {
        return message.replace(NAME_PLACEHOLDER, person.getName().fullName);
    }

    /**
     * Get the message template.
     */
    public String getMessage() {
        return this.message;
    }

    @Override
    public String toString() {
        return this.getMessage();
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof Message // instanceof handles nulls
                && message.equals(((Message) other).message)); // state check
    }
}
