package gim.storage;

import static gim.storage.JsonAdaptedExercise.MISSING_FIELD_MESSAGE_FORMAT;
import static gim.testutil.Assert.assertThrows;
import static gim.testutil.TypicalExercises.BENSON;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

import gim.commons.exceptions.IllegalValueException;
import gim.model.exercise.Name;
import gim.model.exercise.Reps;
import gim.model.exercise.Sets;
import gim.model.exercise.Weight;



public class JsonAdaptedExerciseTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_WEIGHT = "+651234";
    private static final String INVALID_REP = " ";
    private static final String INVALID_SETS = " ";
    private static final String INVALID_DATE = "11/111/2020";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_WEIGHT = BENSON.getWeight().toString();
    private static final String VALID_SETS = BENSON.getSets().toString();

    private static final String VALID_REP = BENSON.getReps().toString();
    private static final JsonAdaptedTag VALID_DATE = new JsonAdaptedTag(BENSON.getDate());

    @Test
    public void toModelType_validExerciseDetails_returnsExercise() throws Exception {
        JsonAdaptedExercise exercise = new JsonAdaptedExercise(BENSON);
        assertEquals(BENSON, exercise.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedExercise exercise =
                new JsonAdaptedExercise(INVALID_NAME, VALID_WEIGHT, VALID_SETS, VALID_REP, VALID_DATE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedExercise exercise = new JsonAdaptedExercise(null, VALID_WEIGHT, VALID_SETS,
                VALID_REP, VALID_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    }

    @Test
    public void toModelType_invalidWeight_throwsIllegalValueException() {
        JsonAdaptedExercise exercise =
                new JsonAdaptedExercise(VALID_NAME, INVALID_WEIGHT, VALID_SETS, VALID_REP, VALID_DATE);
        String expectedMessage = Weight.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    }

    @Test
    public void toModelType_nullWeight_throwsIllegalValueException() {
        JsonAdaptedExercise exercise = new JsonAdaptedExercise(VALID_NAME, null,
                VALID_SETS, VALID_REP, VALID_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Weight.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    }

    @Test
    public void toModelType_invalidSets_throwsIllegalValueException() {
        JsonAdaptedExercise exercise =
                new JsonAdaptedExercise(VALID_NAME, VALID_WEIGHT, INVALID_SETS, VALID_REP, VALID_DATE);
        String expectedMessage = Sets.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    }

    @Test
    public void toModelType_nullSets_throwsIllegalValueException() {
        JsonAdaptedExercise exercise = new JsonAdaptedExercise(VALID_NAME, VALID_WEIGHT, null,
                VALID_REP, VALID_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Sets.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    }

    @Test
    public void toModelType_invalidRep_throwsIllegalValueException() {
        JsonAdaptedExercise exercise =
                new JsonAdaptedExercise(VALID_NAME, VALID_WEIGHT, VALID_SETS, INVALID_REP, VALID_DATE);
        String expectedMessage = Reps.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    }

    @Test
    public void toModelType_nullRep_throwsIllegalValueException() {
        JsonAdaptedExercise exercise = new JsonAdaptedExercise(VALID_NAME, VALID_WEIGHT, VALID_SETS, null, VALID_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Reps.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, exercise::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        JsonAdaptedTag invalidDate = new JsonAdaptedTag(INVALID_DATE);
        JsonAdaptedExercise exercise =
                new JsonAdaptedExercise(VALID_NAME, VALID_WEIGHT, VALID_SETS, VALID_REP, invalidDate);
        assertThrows(IllegalValueException.class, exercise::toModelType);
    }

}
