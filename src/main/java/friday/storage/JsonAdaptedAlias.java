package friday.storage;

import java.util.Map;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import friday.commons.exceptions.IllegalValueException;
import friday.model.alias.Alias;
import friday.model.alias.ReservedKeyword;

/**
 * Jackson-friendly version of {@link Alias} and {@link ReservedKeyword}.
 */
class JsonAdaptedAlias {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Alias %s field is missing!";

    private final String alias;
    private final String keyword;

    /**
     * Constructs a {@code JsonAdaptedAlias} with the given alias details.
     */
    @JsonCreator

    public JsonAdaptedAlias(@JsonProperty("alias") String alias, @JsonProperty("reservedKeyword") String keyword) {

        this.alias = alias;
        this.keyword = keyword;
    }

    /**
     * Converts a given {@code Map.Entry} into this class for Jackson use.
     */
    public JsonAdaptedAlias(Map.Entry<String, String> source) {
        alias = source.getKey();
        keyword = source.getValue();
    }

    /**
     * Converts this Jackson-friendly adapted alias object into the model's {@code Alias} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted alias.
     */
    public Alias toAliasModelType() throws IllegalValueException {
        if (alias == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    Alias.class.getSimpleName()));
        }
        return new Alias(alias);
    }

    /**
     * Converts this Jackson-friendly adapted alias object into the model's {@code ReservedKeyword} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted reserved keyword.
     */
    public ReservedKeyword toReservedKeywordModelType() throws IllegalValueException {
        if (keyword == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT,
                    ReservedKeyword.class.getSimpleName()));
        }
        if (!ReservedKeyword.isValidReservedKeyword(keyword)) {
            throw new IllegalValueException(ReservedKeyword.MESSAGE_CONSTRAINTS);
        }
        return new ReservedKeyword(keyword);
    }

}

