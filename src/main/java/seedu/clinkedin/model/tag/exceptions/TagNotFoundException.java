package seedu.clinkedin.model.tag.exceptions;

import seedu.clinkedin.model.tag.Tag;

/**
 * Signals that the operation is unable to find the specified tag name.
 */
public class TagNotFoundException extends RuntimeException {
    public TagNotFoundException() {
        super("Tag not found!");
    }
    public TagNotFoundException(Tag t) {
        super(String.format("'%s' Tag not found!", t.getTagName()));
    }
}
