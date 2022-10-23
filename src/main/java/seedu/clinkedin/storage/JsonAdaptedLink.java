package seedu.clinkedin.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.clinkedin.commons.exceptions.IllegalValueException;
import seedu.clinkedin.model.link.Link;

/**
 * Jackson-friendly version of {@link Link}.
 */
class JsonAdaptedLink {

    private final String link;

    /**
     * Constructs a {@code JsonAdaptedLink} with the given {@code link}.
     */
    @JsonCreator
    public JsonAdaptedLink(String link) {
        this.link = link;
    }

    /**
     * Converts a given {@code Link} into this class for Jackson use.
     */
    public JsonAdaptedLink(Link source) {
        link = source.link;
    }

    @JsonValue
    public String getLink() {
        return link;
    }

    /**
     * Converts this Jackson-friendly adapted tag object into the model's {@code Tag} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted tag.
     */
    public Link toModelType() throws IllegalValueException {
        if (!Link.isValidLink(link)) {
            throw new IllegalValueException(Link.MESSAGE_CONSTRAINTS);
        }
        return new Link(link);
    }

}