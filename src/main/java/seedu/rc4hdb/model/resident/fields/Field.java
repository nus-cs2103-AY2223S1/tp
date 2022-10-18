package seedu.rc4hdb.model.resident.fields;

import static java.util.Objects.requireNonNull;

import java.util.List;

/**
 * Represents the fields for a residents.
 */
public abstract class Field {

    public static final String INDEX_IDENTIFIER = "Index";

    public static final List<String> FIELDS = List.of(
            INDEX_IDENTIFIER, Email.IDENTIFIER, Gender.IDENTIFIER,
            House.IDENTIFIER, MatricNumber.IDENTIFIER, Name.IDENTIFIER,
            Phone.IDENTIFIER, Room.IDENTIFIER, Tag.IDENTIFIER
    );

    public final String value;

    protected Field(String value) {
        requireNonNull(value);
        this.value = value;
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
