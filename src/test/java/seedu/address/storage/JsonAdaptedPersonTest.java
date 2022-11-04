package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.attribute.Name;
import seedu.address.model.person.Person;
import seedu.address.model.tag.Tag;


public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final List<JsonAdaptedTag> INVALID_TAGS = List.of(
            new JsonAdaptedTag("#tag"),
            new JsonAdaptedTag("%)*^"));
    private static final String INVALID_UID = "1384750";
    private static final List<JsonAdaptedAbstractAttribute> INVALID_ATTRIBUTES =
            List.of(new JsonAdaptedAbstractAttribute(Map.of("type", "Check", "content",
                    "Paperwork is not done.", "display_format", "31", "style_format", "288")));

    private static final String VALID_NAME = BENSON.getName().fullName;
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
        .map(JsonAdaptedTag::new)
        .collect(Collectors.toList());
    private static final String VALID_UID = UUID.nameUUIDFromBytes(("Person: " + VALID_NAME)
        .getBytes(StandardCharsets.UTF_8)).toString();
    private static final List<JsonAdaptedAbstractAttribute> VALID_ATTRIBUTES = BENSON.getAttributes()
        .stream()
        .map(JsonAdaptedAbstractAttribute::new)
        .collect(Collectors.toList());

     @Test
     public void toModelType_validPersonDetails_returnsPerson() throws Exception {

         JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
         assertEquals(BENSON, person.toModelType());
     }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {

        JsonAdaptedPerson person = new JsonAdaptedPerson(INVALID_NAME, VALID_UID, VALID_TAGS,
            VALID_ATTRIBUTES);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(null, VALID_UID, VALID_TAGS,
            VALID_ATTRIBUTES);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTag_throwsIllegalValueException() {
         JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_UID, INVALID_TAGS,
                 VALID_ATTRIBUTES);
         String expectedMessage = Tag.MESSAGE_CONSTRAINTS;
         assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullTag_returnsPerson() throws Exception {
         JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_UID, null, VALID_ATTRIBUTES);
        assertTrue(person.toModelType().getTags().size() == 0);
    }

    @Test
    public void toModelType_invalidUid() {
         JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, INVALID_UID, VALID_TAGS, VALID_ATTRIBUTES);
         assertThrows
    }

    @Test
    public void toModelType_invalidAttributes_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME, VALID_UID, VALID_TAGS, INVALID_ATTRIBUTES);
        String expectedMessage = "";
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }
}
