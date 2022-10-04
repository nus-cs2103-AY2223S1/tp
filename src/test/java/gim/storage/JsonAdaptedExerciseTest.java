package gim.storage;

import static gim.storage.JsonAdaptedExercise.MISSING_FIELD_MESSAGE_FORMAT;
import static gim.testutil.Assert.assertThrows;
import static gim.testutil.TypicalExercises.BENSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import gim.commons.exceptions.IllegalValueException;
import gim.model.exercise.Address;
import gim.model.exercise.Email;
import gim.model.exercise.Name;
import gim.model.exercise.Weight;



public class JsonAdaptedExerciseTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_WEIGHT = "+651234";
    private static final String INVALID_ADDRESS = " ";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_WEIGHT = BENSON.getWeight().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_ADDRESS = BENSON.getAddress().toString();
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validExerciseDetails_returnsExercise() throws Exception {
        JsonAdaptedExercise exercise = new JsonAdaptedExercise(BENSON);
        assertEquals(BENSON, exercise.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedExercise exercise =
                new JsonAdaptedExercise(INVALID_NAME, VALID_WEIGHT, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedExercise exercise = new JsonAdaptedExercise(null, VALID_WEIGHT, VALID_EMAIL,
                VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    }

    @Test
    public void toModelType_invalidWeight_throwsIllegalValueException() {
        JsonAdaptedExercise exercise =
                new JsonAdaptedExercise(VALID_NAME, INVALID_WEIGHT, VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Weight.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    }

    @Test
    public void toModelType_nullWeight_throwsIllegalValueException() {
        JsonAdaptedExercise exercise = new JsonAdaptedExercise(VALID_NAME, null,
                VALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Weight.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedExercise exercise =
                new JsonAdaptedExercise(VALID_NAME, VALID_WEIGHT, INVALID_EMAIL, VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedExercise exercise = new JsonAdaptedExercise(VALID_NAME, VALID_WEIGHT, null,
                VALID_ADDRESS, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    }

    @Test
    public void toModelType_invalidAddress_throwsIllegalValueException() {
        JsonAdaptedExercise exercise =
                new JsonAdaptedExercise(VALID_NAME, VALID_WEIGHT, VALID_EMAIL, INVALID_ADDRESS, VALID_TAGS);
        String expectedMessage = Address.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    }

    @Test
    public void toModelType_nullAddress_throwsIllegalValueException() {
        JsonAdaptedExercise exercise = new JsonAdaptedExercise(VALID_NAME, VALID_WEIGHT, VALID_EMAIL, null, VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedExercise exercise =
                new JsonAdaptedExercise(VALID_NAME, VALID_WEIGHT, VALID_EMAIL, VALID_ADDRESS, invalidTags);
        assertThrows(IllegalValueException.class, exercise::toModelType);
    }

}
