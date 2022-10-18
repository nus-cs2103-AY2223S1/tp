package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Name;
import seedu.address.model.team.Link;
import seedu.address.model.team.Url;


/**
 * Jackson-friendly version of {@link Link}.
 */
public class JsonAdaptedLink {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Link's %s field is missing!";
    private final String displayedName;
    private final String url;

    /**
     * Constructs a {@code JsonAdaptedLink} with the given {@code linkName} and {@code url}.
     */
    @JsonCreator
    public JsonAdaptedLink(@JsonProperty("displayedName") String displayedName, @JsonProperty("url") String url) {
        this.displayedName = displayedName;
        this.url = url;
    }

    /**
     * Converts a given {@code Link} into this class for Jackson use.
     */
    public JsonAdaptedLink(Link source) {
        displayedName = source.getDisplayedName().fullName;
        url = source.getUrl().value;
    }

    /**
     * Converts this Jackson-friendly adopted link object into the model's {@ccode Link} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */

    public Link toModelType() throws IllegalValueException {
        if (displayedName == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }

        if (!Name.isValidName(displayedName)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }

        final Name modelName = new Name(displayedName);

        if (url == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Url.class.getSimpleName()));
        }

        if (!Url.isValidUrl(url)) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Url.class.getSimpleName()));
        }

        final Url modelUrl = new Url(url);

        return new Link(modelName, modelUrl);
    }

}
