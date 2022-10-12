package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.link.Link;

/**
 * Jackson-friendly version of {@link Link}.
 */
public class JsonAdaptedLink {
    private final String linkName;

    /**
     * Constructs a {@code JsonAdaptedLink} with the given {@code linkName}.
     */
    @JsonCreator
    public JsonAdaptedLink(String linkName) {
        this.linkName = linkName;
    }

    /**
     * Converts a given {@code Link} into this class for Jackson use.
     */
    public JsonAdaptedLink(Link source) {
        linkName = source.linkName;
    }

    @JsonValue
    public String getLinkName() {
        return linkName;
    }

    /**
     * Converts this Jackson-friendly adapted link object into the model's {@code Link} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted Link.
     */
    public Link toModelType() throws IllegalValueException {
        if (!Link.isValidLinkName(linkName)) {
            throw new IllegalValueException(Link.MESSAGE_CONSTRAINTS);
        }
        return new Link(linkName);
    }
}
