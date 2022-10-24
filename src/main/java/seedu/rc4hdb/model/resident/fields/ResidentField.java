package seedu.rc4hdb.model.resident.fields;

import java.util.List;
import java.util.stream.Collectors;

import seedu.rc4hdb.model.Field;

/**
 * Represents the fields for a residents.
 */
public abstract class ResidentField extends Field {

    public static final String INDEX_IDENTIFIER = "Index";

    public static final List<String> FIELDS = List.of(
            INDEX_IDENTIFIER, Email.IDENTIFIER, Gender.IDENTIFIER,
            House.IDENTIFIER, MatricNumber.IDENTIFIER, Name.IDENTIFIER,
            Phone.IDENTIFIER, Room.IDENTIFIER, Tag.IDENTIFIER
    );

    public static final List<String> LOWERCASE_FIELDS = FIELDS.stream()
            .map(String::toLowerCase)
            .collect(Collectors.toList());

    protected ResidentField(String value) {
        super(value);
    }

}
