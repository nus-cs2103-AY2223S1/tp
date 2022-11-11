package seedu.pennywise.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static seedu.pennywise.logic.commands.CommandTestUtil.EXPENDITURE_BY_CATEGORY;
import static seedu.pennywise.logic.commands.CommandTestUtil.EXPENDITURE_BY_MONTH;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_MONTH_APRIL;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_TYPE_INCOME;

import org.junit.jupiter.api.Test;

import seedu.pennywise.logic.commands.ViewCommand.ViewEntriesDescriptor;
import seedu.pennywise.testutil.ViewEntriesDescriptorBuilder;

public class ViewEntriesDescriptorTest {

    @Test
    public void equals() {
        // same values -> returns true
        ViewEntriesDescriptor descriptorWithSameValues = new ViewEntriesDescriptor(EXPENDITURE_BY_CATEGORY);
        assertEquals(EXPENDITURE_BY_CATEGORY, descriptorWithSameValues);

        // same object -> returns true
        assertEquals(EXPENDITURE_BY_CATEGORY, EXPENDITURE_BY_CATEGORY);

        // null -> returns false
        assertNotEquals(null, EXPENDITURE_BY_CATEGORY);

        // different types -> returns false
        assertNotEquals(5, EXPENDITURE_BY_CATEGORY);

        // different values -> returns false
        assertNotEquals(EXPENDITURE_BY_CATEGORY, EXPENDITURE_BY_MONTH);

        // different month -> returns false
        ViewEntriesDescriptor editedExpenditureByMonth = new ViewEntriesDescriptorBuilder(EXPENDITURE_BY_MONTH)
                .withMonth(VALID_MONTH_APRIL).build();
        assertNotEquals(EXPENDITURE_BY_MONTH, editedExpenditureByMonth);

        // different entry type -> returns false
        editedExpenditureByMonth = new ViewEntriesDescriptorBuilder(EXPENDITURE_BY_MONTH)
                .withEntryType(VALID_TYPE_INCOME)
                .build();
        assertNotEquals(EXPENDITURE_BY_MONTH, editedExpenditureByMonth);
    }
}
