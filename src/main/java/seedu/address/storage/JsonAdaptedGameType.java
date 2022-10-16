package seedu.address.storage;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonValue;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.person.GameType;


/**
 * Jackson-friendly version of {@link seedu.address.model.person.GameType}.
 */
class JsonAdaptedGameType {

    private final String gameTypes;

    /**
     * Constructs a {@code JsonAdaptedGameType} with the given {@code gameTypes}.
     */
    @JsonCreator
    public JsonAdaptedGameType(String gameTypes) {
        this.gameTypes = gameTypes;
    }

    /**
     * Converts a given {@code gameTypes} into this class for Jackson use.
     */
    public JsonAdaptedGameType(GameType source) {
        gameTypes = source.toString();
    }

    @JsonValue
    public String getGameTypes() {
        return gameTypes;
    }

    /**
     * Converts this Jackson-friendly adapted socials object into the model's {@code GameType} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted game types.
     */
    public GameType toModelType() throws IllegalValueException {
        if (!GameType.isValidGameType(gameTypes)) {
            throw new IllegalValueException(GameType.MESSAGE_CONSTRAINTS);
        }
        return ParserUtil.parseGameType(gameTypes);
    }

}