package seedu.rc4hdb.model.resident.fields;

import java.util.List;

/**
 * Represents the fields for a resident.
 */
public interface ResidentField {

    String INDEX_IDENTIFIER = "Index";

    List<String> FIELDS = List.of(
            INDEX_IDENTIFIER, Email.IDENTIFIER, Gender.IDENTIFIER,
            House.IDENTIFIER, MatricNumber.IDENTIFIER, Name.IDENTIFIER,
            Phone.IDENTIFIER, Room.IDENTIFIER, Tag.IDENTIFIER
    );

}
