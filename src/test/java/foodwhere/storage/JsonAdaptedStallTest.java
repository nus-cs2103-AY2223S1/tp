package foodwhere.storage;

import static foodwhere.testutil.Assert.assertThrows;
import static foodwhere.testutil.TypicalStalls.BENSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import foodwhere.commons.exceptions.IllegalValueException;
import foodwhere.model.stall.Address;
import foodwhere.model.stall.Name;

public class JsonAdaptedStallTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final List<JsonAdaptedDetail> VALID_TAGS = BENSON.getDetails().stream()
            .map(JsonAdaptedDetail::new)
            .collect(Collectors.toList());

    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedStall stall = new JsonAdaptedStall(BENSON);
        assertEquals(BENSON, stall.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStall stall = new JsonAdaptedStall(INVALID_NAME, VALID_PHONE,
                VALID_ADDRESS, VALID_TAGS, new ArrayList<JsonAdaptedStall>());
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, stall::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStall stall = new JsonAdaptedStall(null, VALID_PHONE,
                VALID_ADDRESS, VALID_TAGS, new ArrayList<JsonAdaptedStall>());
        String expectedMessage =
                String.format(JsonAdaptedStall.MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, stall::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedStall stall = new JsonAdaptedStall(VALID_NAME, VALID_PHONE,
                INVALID_ADDRESS, VALID_TAGS, new ArrayList<JsonAdaptedStall>());
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, stall::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedStall stall = new JsonAdaptedStall(VALID_NAME, VALID_PHONE,
                null, VALID_TAGS, new ArrayList<JsonAdaptedStall>());
        String expectedMessage = String.format(JsonAdaptedStall.MISSING_FIELD_MESSAGE_FORMAT,
                Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, stall::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedDetail> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedDetail(INVALID_TAG));
        JsonAdaptedStall stall = new JsonAdaptedStall(VALID_NAME, VALID_PHONE,
                VALID_ADDRESS, invalidTags, new ArrayList<JsonAdaptedStall>());
        assertThrows(IllegalValueException.class, stall::toModelType);
    }

}
