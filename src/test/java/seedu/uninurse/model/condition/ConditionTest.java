package seedu.uninurse.model.condition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalConditions.CONDITION_DIABETES;
import static seedu.uninurse.testutil.TypicalConditions.CONDITION_OSTEOPOROSIS;
import static seedu.uninurse.testutil.TypicalConditions.TYPICAL_CONDITION_DIABETES;

import org.junit.jupiter.api.Test;

public class ConditionTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Condition(null));
    }

    @Test
    public void constructor_invalidCondition_throwsIllegalArgumentException() {
        String invalidCondition = "";
        assertThrows(IllegalArgumentException.class, () -> new Condition(invalidCondition));
    }

    @Test
    public void isValidCondition_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> Condition.isValidCondition(null));
    }

    @Test
    public void isValidCondition_validCondition_returnsTrue() {
        // numbers in task description -> returns true
        assertTrue(Condition.isValidCondition("123"));

        // special characters in task description -> returns true
        assertTrue(Condition.isValidCondition("@!#$%^&*()-=+_[];.,`~:<>?/"));

        // valid task description -> returns true
        assertTrue(Condition.isValidCondition("Check vitals"));
    }

    @Test
    public void isValidCondition_invalidCondition_returnsFalse() {
        // empty task description -> returns false
        assertFalse(Condition.isValidCondition(""));

        // blank task description -> returns false
        assertFalse(Condition.isValidCondition("  "));
    }

    @Test
    public void testToString() {
        assertEquals(CONDITION_DIABETES.toString(), TYPICAL_CONDITION_DIABETES);
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertEquals(CONDITION_DIABETES, CONDITION_DIABETES);

        // same values -> returns true
        Condition diabetesConditionCopy = new Condition(TYPICAL_CONDITION_DIABETES);
        assertEquals(CONDITION_DIABETES, diabetesConditionCopy);

        // different types -> returns false
        assertNotEquals(1, CONDITION_DIABETES);

        // null -> returns false
        assertNotEquals(null, CONDITION_DIABETES);

        // different description -> returns false
        assertNotEquals(CONDITION_DIABETES, CONDITION_OSTEOPOROSIS);
    }
}
