package seedu.clinkedin.model.tag.exceptions;

import seedu.clinkedin.model.tag.TagType;

/**
 * Signals that the operation is unable to find the specified tag type.
 */
public class TagTypeNotFoundException extends RuntimeException {
    public TagTypeNotFoundException() {
        super("Tag type not found!");
    }
    public TagTypeNotFoundException(TagType t) {
        super(String.format("'%s' Tag type not found!", t.getTagTypeName()));
    }
    public TagTypeNotFoundException(String tagType) {
        super(String.format("'%s' Tag type not found!", tagType));
    }
}
