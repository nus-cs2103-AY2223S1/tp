package seedu.rc4hdb.model.resident.fields;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

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

    List<String> LOWERCASE_FIELDS = FIELDS.stream()
            .map(String::toLowerCase)
            .collect(Collectors.toList());

    HashMap<String, String> LETTER_TO_FIELD_NAME_MAPPINGS = getHashMapOfLetterToFieldName();

    private static HashMap<String, String> getHashMapOfLetterToFieldName() {
        HashMap<String, String> letterToFieldNameMappings = new HashMap<>();
        for (String field : LOWERCASE_FIELDS) {
            letterToFieldNameMappings.put(String.valueOf(field.charAt(0)), field);
        }
        return letterToFieldNameMappings;
    }

}
