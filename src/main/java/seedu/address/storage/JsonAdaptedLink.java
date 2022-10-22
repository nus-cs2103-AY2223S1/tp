package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.AddLinkCommandParser;
import seedu.address.model.module.link.Link;

/**
 * Jackson-friendly version of {@link Link}.
 */
public class JsonAdaptedLink {
    private final String linkAliasWithUrl;

    /**
     * Constructs a {@code JsonAdaptedLink} with the given {@code linkAliasWithUrl}.
     */
    @JsonCreator
    public JsonAdaptedLink(String linkAliasWithUrl) {
        this.linkAliasWithUrl = linkAliasWithUrl;
    }

    /**
     * Converts a given {@code Link} into this class for Jackson use.
     */
    public JsonAdaptedLink(Link source) {
        linkAliasWithUrl = source.linkAlias + ";" + source.linkUrl;
    }

    @JsonValue
    public String getLinkAliasWithUrl() {
        return linkAliasWithUrl;
    }

    /**
     * Converts this Jackson-friendly adapted link object into the model's {@code Link} object.
     * ";" is used as delimiter it is not accepted by link url / alias regex check
     * @throws IllegalValueException if there were any data constraints violated in the adapted Link.
     */
    public Link toModelType() throws IllegalValueException {
        String[] linkFields = linkAliasWithUrl.split(";");
        if (linkFields.length != 2) {
            throw new IllegalValueException(AddLinkCommandParser.MESSAGE_CONSTRAINTS);
        }
        String linkAlias = linkFields[0].trim();
        if (!Link.isValidLinkAlias(linkAlias)) {
            throw new IllegalValueException(Link.MESSAGE_CONSTRAINTS_ALIAS);
        }
        String linkUrl = linkFields[1].trim();
        if (!Link.isValidLinkUrl(linkUrl)) {
            throw new IllegalValueException(Link.MESSAGE_CONSTRAINTS_URL);
        }
        return new Link(linkAlias, linkUrl);
    }
}
