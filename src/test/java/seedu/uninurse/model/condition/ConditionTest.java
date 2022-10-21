package seedu.uninurse.model.condition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

public class ConditionTest {
    // may be moved to a utility class if sample conditions are needed in other tests
    private final Condition diabetesCondition = new Condition("Type II Diabetes");
    private final Condition osteoporosisCondition = new Condition("Osteoporosis");
    private final String diabetesConditionName = "Type II Diabetes";


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
        // numbers in condition -> returns true
        assertTrue(Condition.isValidCondition("123"));

        // special characters in condition -> returns true
        assertTrue(Condition.isValidCondition("@!#$%^&*()-=+_[];.,`~:<>?/"));

        // valid condition -> returns true
        assertTrue(Condition.isValidCondition("Check vitals"));
    }

    @Test
    public void isValidCondition_invalidCondition_returnsFalse() {
        // empty condition -> returns false
        assertFalse(Condition.isValidCondition(""));

        // blank condition -> returns false
        assertFalse(Condition.isValidCondition("  "));
    }

    @Test
    public void testToString() {
        assertEquals(diabetesCondition.toString(), diabetesConditionName);
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertEquals(diabetesCondition, diabetesCondition);

        // same values -> returns true
        Condition diabetesConditionCopy = new Condition(diabetesConditionName);
        assertEquals(diabetesCondition, diabetesConditionCopy);

        // different types -> returns false
        assertNotEquals(1, diabetesCondition);

        // null -> returns false
        assertNotEquals(null, diabetesCondition);

        // different description -> returns false
        assertNotEquals(diabetesCondition, osteoporosisCondition);
    }
}
