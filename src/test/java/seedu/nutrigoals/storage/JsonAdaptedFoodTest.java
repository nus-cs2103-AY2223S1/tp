package seedu.nutrigoals.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.nutrigoals.storage.JsonAdaptedFood.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.nutrigoals.testutil.Assert.assertThrows;
import static seedu.nutrigoals.testutil.TypicalFoods.BREAD;

import org.junit.jupiter.api.Test;

import seedu.nutrigoals.commons.exceptions.IllegalValueException;
import seedu.nutrigoals.model.meal.Name;

public class JsonAdaptedFoodTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BREAD.getName().toString();
    private static final String VALID_TAG = BREAD.getTag().tagName;
    private static final String VALID_CALORIE = "200";
    private static final String VALID_DATE = "2022-10-05T05:38:22.902190";

    @Test
    public void toModelType_validFoodDetails_returnsFood() throws Exception {
        JsonAdaptedFood food = new JsonAdaptedFood(BREAD);
        assertEquals(BREAD, food.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedFood food =
                new JsonAdaptedFood(INVALID_NAME, VALID_CALORIE, VALID_TAG, VALID_DATE);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedFood food = new JsonAdaptedFood(null, VALID_CALORIE, VALID_TAG, VALID_DATE);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, food::toModelType);
    }

    @Test
    public void toModelType_invalidTag_throwsIllegalValueException() {
        // List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        // invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedFood food =
                new JsonAdaptedFood(VALID_NAME, VALID_CALORIE, INVALID_TAG, VALID_DATE);
        assertThrows(IllegalValueException.class, food::toModelType);
    }

}
