package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.person.Socials;


/**
 * Jackson-friendly version of {@link Socials}.
 */
class JsonAdaptedSocials{

    private final String socials;

    /**
     * Constructs a {@code JsonAdaptedSocials} with the given {@code socials}.
     */
    @JsonCreator
    public JsonAdaptedSocials(String socials) {
        this.socials = socials;
    }

    /**
     * Converts a given {@code socials} into this class for Jackson use.
     */
    public JsonAdaptedSocials(Socials source) {
        socials = source.fullHandle;
    }

    @JsonValue
    public String getSocials() {
        return socials;
    }

    /**
     * Converts this Jackson-friendly adapted socials object into the model's {@code Socials} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted socials.
     */
    public Socials toModelType() throws IllegalValueException {
        if (!Socials.isValidName(socials)) {
            throw new IllegalValueException(Socials.MESSAGE_CONSTRAINTS);
        }
        return ParserUtil.parseSocial(socials);
    }

}
