package seedu.clinkedin.model.tag.exceptions;

/**
 * Signals that the operation is unable to find the given tag type with the specified prefix.
 */
public class TagTypePrefixPairNotFoundException extends RuntimeException {
    public TagTypePrefixPairNotFoundException() {
        super("Tag type with given prefix not found!");
    }
}
