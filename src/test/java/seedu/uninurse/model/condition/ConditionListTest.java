package seedu.uninurse.model.condition;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.uninurse.testutil.Assert.assertThrows;
import static seedu.uninurse.testutil.TypicalConditions.CONDITION_DIABETES;
import static seedu.uninurse.testutil.TypicalConditions.CONDITION_OSTEOPOROSIS;
import static seedu.uninurse.testutil.TypicalConditions.TYPICAL_CONDITION_DIABETES;
import static seedu.uninurse.testutil.TypicalConditions.TYPICAL_CONDITION_OSTEOPOROSIS;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.uninurse.model.condition.exceptions.ConditionNotFoundException;
import seedu.uninurse.model.condition.exceptions.DuplicateConditionException;

public class ConditionListTest {
    private final ConditionList emptyConditionList = new ConditionList();
    private final ConditionList conditionListDiabetes = new ConditionList(List.of(CONDITION_DIABETES));
    private final ConditionList conditionListOsteoporosis = new ConditionList(List.of(CONDITION_OSTEOPOROSIS));
    private final ConditionList conditionList = new ConditionList(
            Arrays.asList(CONDITION_DIABETES, CONDITION_OSTEOPOROSIS));

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new ConditionList(null));
    }

    @Test
    public void add_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> emptyConditionList.add(null));
    }

    @Test
    public void add_duplicateCondition_throwsDuplicateConditionException() {
        assertThrows(DuplicateConditionException.class, () -> conditionListDiabetes.add(CONDITION_DIABETES));
    }

    @Test
    public void add_validCondition_success() {
        ConditionList updatedConditionList = emptyConditionList.add(CONDITION_DIABETES);
        assertEquals(updatedConditionList, conditionListDiabetes);
    }

    @Test
    public void delete_emptyList_throwsConditionNotFoundException() {
        assertThrows(ConditionNotFoundException.class, () -> emptyConditionList.delete(0));
    }

    @Test
    public void delete_invalidIndex_throwsConditionNotFoundException() {
        assertThrows(ConditionNotFoundException.class, () -> emptyConditionList.delete(-1));
    }

    @Test
    public void delete_validIndex_success() {
        ConditionList updatedConditionList = conditionListDiabetes.delete(0);
        assertEquals(updatedConditionList, emptyConditionList);
    }

    @Test
    public void delete_indexOutOfBounds_throwsConditionNotFoundException() {
        assertThrows(ConditionNotFoundException.class, () -> conditionListDiabetes.delete(1));
    }

    @Test
    public void edit_nullCondition_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> emptyConditionList.add(null));
    }

    @Test
    public void edit_emptyList_throwsConditionNotFoundException() {
        assertThrows(ConditionNotFoundException.class, () -> emptyConditionList.edit(0, CONDITION_DIABETES));
    }

    @Test
    public void edit_invalidIndex_throwsConditionNotFoundException() {
        assertThrows(ConditionNotFoundException.class, () -> emptyConditionList.edit(-1, CONDITION_DIABETES));
    }

    @Test
    public void edit_indexOutOfBounds_throwsConditionNotFoundException() {
        assertThrows(ConditionNotFoundException.class, () ->
                conditionListDiabetes.edit(1, CONDITION_OSTEOPOROSIS));
    }

    @Test
    public void edit_duplicateCondition_throwsDuplicateConditionException() {
        assertThrows(DuplicateConditionException.class, () -> conditionListDiabetes.edit(0, CONDITION_DIABETES));
    }

    @Test
    public void edit_validArgs_success() {
        ConditionList updatedConditionList = conditionListDiabetes.edit(0, CONDITION_OSTEOPOROSIS);
        assertEquals(updatedConditionList, conditionListOsteoporosis);
    }

    @Test
    public void get_emptyList_throwsConditionNotFoundException() {
        assertThrows(ConditionNotFoundException.class, () -> emptyConditionList.get(0));
    }

    @Test
    public void get_invalidIndex_throwsConditionNotFoundException() {
        assertThrows(ConditionNotFoundException.class, () -> emptyConditionList.get(-1));
    }

    @Test
    public void get_indexOutOfBounds_throwsConditionNotFoundException() {
        assertThrows(ConditionNotFoundException.class, () -> conditionListDiabetes.get(1));
    }

    @Test
    public void get_validIndex_success() {
        assertEquals(conditionListDiabetes.get(0), CONDITION_DIABETES);
    }

    @Test
    public void size_emptyList_returnsZero() {
        assertEquals(emptyConditionList.size(), 0);
    }

    @Test
    public void size_nonEmptyList_returnsNonZero() {
        assertNotEquals(conditionList.size(), 0);
    }

    @Test
    public void isEmpty_emptyList_returnsTrue() {
        assertTrue(emptyConditionList.isEmpty());
    }

    @Test
    public void isEmpty_nonEmptyList_returnsFalse() {
        assertFalse(conditionList.isEmpty());
    }

    @Test
    public void getInternalList_modifyList_throwsUnsupportedOperationException() {
        assertThrows(UnsupportedOperationException.class, ()
                -> conditionList.getInternalList().remove(0));
    }

    @Test
    public void toString_emptyList_returnsEmptyString() {
        assertEquals(emptyConditionList.toString(), "");
    }

    @Test
    public void toString_nonEmptyList_returnsIndexedStrings() {
        String expectedString = "1. " + TYPICAL_CONDITION_DIABETES + "\n2. " + TYPICAL_CONDITION_OSTEOPOROSIS;
        assertEquals(conditionList.toString(), expectedString);
    }

    @Test
    public void equals() {
        // same object -> returns true
        assertEquals(conditionListDiabetes, conditionListDiabetes);

        // same internal list -> returns true
        ConditionList conditionListDiabetesCopy = new ConditionList(List.of(CONDITION_DIABETES));
        assertEquals(conditionListDiabetes, conditionListDiabetesCopy);

        // different types -> returns false
        assertNotEquals(1, conditionListDiabetes);

        // null -> returns false
        assertNotEquals(null, conditionListDiabetes);

        // different internal list -> returns false
        assertNotEquals(conditionListDiabetes, conditionList);
    }
}
