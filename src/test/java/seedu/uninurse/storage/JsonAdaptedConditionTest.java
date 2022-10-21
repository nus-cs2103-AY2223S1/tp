package seedu.uninurse.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalConditions.CONDITION_DIABETES;
import static seedu.uninurse.testutil.TypicalConditions.TYPICAL_CONDITION_DIABETES;

import org.junit.jupiter.api.Test;

import seedu.uninurse.commons.exceptions.IllegalValueException;
import seedu.uninurse.model.condition.Condition;

public class JsonAdaptedConditionTest {

    JsonAdaptedCondition condition = new JsonAdaptedCondition(TYPICAL_CONDITION_DIABETES);
    private static final String INVALID_CONDITION = " ";

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new JsonAdaptedCondition((String) null));
        assertThrows(NullPointerException.class, () -> new JsonAdaptedCondition((Condition) null));
    }

    @Test
    public void getCondition() {
        assertEquals(condition.getCondition(), TYPICAL_CONDITION_DIABETES);
    }

    @Test
    public void toModelType_invalidCondition_throwsIllegalValueException() {
        JsonAdaptedCondition condition = new JsonAdaptedCondition(INVALID_CONDITION);
        String expectedMessage = Condition.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, condition::toModelType);
    }

    @Test
    public void toModelType_validCondition_success() throws IllegalValueException {
        JsonAdaptedCondition condition = new JsonAdaptedCondition(TYPICAL_CONDITION_DIABETES);
        assertEquals(CONDITION_DIABETES, condition.toModelType());
    }
}
