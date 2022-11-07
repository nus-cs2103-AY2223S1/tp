package foodwhere.storage;

import static foodwhere.storage.JsonAdaptedStall.MISSING_FIELD_MESSAGE_FORMAT;
import static foodwhere.testutil.Assert.assertThrows;
import static foodwhere.testutil.TypicalStalls.BENSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import foodwhere.commons.exceptions.IllegalValueException;
import foodwhere.model.commons.Address;
import foodwhere.model.commons.Name;
import foodwhere.model.stall.Stall;
import foodwhere.model.stall.StallBuilder;
import foodwhere.testutil.TypicalReviews;

public class JsonAdaptedStallTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String VALID_NAME_EXTRA_SPACE = "Chicken    Rice";
    private static final String VALID_NAME_EXTRA_SPACE_PARSED = "Chicken Rice";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validStallTags_returnsStall() throws Exception {
        JsonAdaptedStall stall = new JsonAdaptedStall(BENSON);
        assertEquals(BENSON, stall.toModelType());

        Stall testStall = new StallBuilder(BENSON)
                .withName(VALID_NAME_EXTRA_SPACE).build();
        Stall testStallResult = new StallBuilder(BENSON)
                .withName(VALID_NAME_EXTRA_SPACE_PARSED).build();
        JsonAdaptedStall stall2 = new JsonAdaptedStall(testStall);
        assertEquals(testStallResult, stall2.toModelType());
    }

    @Test
    public void toModelType_withReview_returnsStall() throws Exception {
        Stall testStall = new StallBuilder(BENSON).addReview(TypicalReviews.BENSON).build();
        JsonAdaptedStall stall = new JsonAdaptedStall(testStall);
        assertEquals(testStall, stall.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedStall stall = new JsonAdaptedStall(INVALID_NAME, VALID_ADDRESS,
                VALID_TAGS, new ArrayList<>());
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, stall::toModelType);
    }

    @Test
    public void getModelReviews_invalidName_throwsIllegalValueException() {
        JsonAdaptedStall stall = new JsonAdaptedStall(INVALID_NAME, VALID_ADDRESS,
                VALID_TAGS, new ArrayList<>());
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, stall::getModelReviews);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedStall stall = new JsonAdaptedStall(null, VALID_ADDRESS,
                VALID_TAGS, new ArrayList<>());
        String expectedMessage =
                String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, stall::toModelType);
    }

    @Test
    public void getModelReviews_nullName_throwsIllegalValueException() {
        JsonAdaptedStall stall = new JsonAdaptedStall(null, VALID_ADDRESS,
                VALID_TAGS, new ArrayList<>());
        String expectedMessage =
                String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, stall::getModelReviews);
    }

    @Test
    public void getModelReviews_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedStall stall = new JsonAdaptedStall(VALID_NAME, INVALID_ADDRESS,
                VALID_TAGS, new ArrayList<>());
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, stall::getModelReviews);
    }

    @Test
    public void getModelReviews_nullAddress_throwsIllegalValueException() {
        JsonAdaptedStall stall = new JsonAdaptedStall(VALID_NAME, null,
                VALID_TAGS, new ArrayList<>());
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, stall::getModelReviews);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedStall stall = new JsonAdaptedStall(VALID_NAME, INVALID_ADDRESS,
                VALID_TAGS, new ArrayList<>());
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, stall::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedStall stall = new JsonAdaptedStall(VALID_NAME, null,
                VALID_TAGS, new ArrayList<>());
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT,
                Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, stall::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedStall stall = new JsonAdaptedStall(VALID_NAME, VALID_ADDRESS,
                invalidTags, new ArrayList<>());
        assertThrows(IllegalValueException.class, stall::toModelType);
    }

}
