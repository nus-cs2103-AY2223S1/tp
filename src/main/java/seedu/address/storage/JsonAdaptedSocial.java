package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.person.Social;


/**
 * Jackson-friendly version of {@link Social}.
 */
class JsonAdaptedSocial{

    private final String social;

    /**
     * Constructs a {@code JsonAdaptedSocials} with the given {@code socials}.
     */
    @JsonCreator
    public JsonAdaptedSocial(String social) {
        this.social = social;
    }

    /**
     * Converts a given {@code socials} into this class for Jackson use.
     */
    public JsonAdaptedSocial(Social source) {
        social = source.fullHandle;
    }

    @JsonValue
    public String getSocial() {
        return social;
    }

    /**
     * Converts this Jackson-friendly adapted socials object into the model's {@code Socials} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted socials.
     */
    public Social toModelType() throws IllegalValueException {
        if (!Social.isValidName(social)) {
            throw new IllegalValueException(Social.MESSAGE_CONSTRAINTS);
        }
        return ParserUtil.parseSocial(social);
    }

}
