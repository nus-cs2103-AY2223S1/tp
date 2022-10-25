package seedu.address.model.entry;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_AMT_INVESTMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATE_INVESTMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DESC_INVESTMENT;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_INVESTMENT;
import static seedu.address.testutil.TypicalEntry.ALLOWANCE;
import static seedu.address.testutil.TypicalEntry.INVESTMENT;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.IncomeBuilder;

public class IncomeTest {
    @Test
    public void isSameIncome() {
        // same object -> returns True
        assertTrue(ALLOWANCE.isSameEntry(ALLOWANCE));

        // null -> returns false
        assertFalse(ALLOWANCE.isSameEntry(null));
    }

    @Test
    public void equals() {

        //same values -> returns true
        Entry allowanceCopy = new IncomeBuilder(ALLOWANCE).build();
        assertTrue(ALLOWANCE.equals(allowanceCopy));

        // same object -> returns true
        assertTrue(ALLOWANCE.equals(ALLOWANCE));

        // null -> returns false
        assertFalse(ALLOWANCE.equals(null));

        // different type -> returns false
        assertFalse(ALLOWANCE.equals(5));

        // different entry -> returns false
        assertFalse(ALLOWANCE.equals(INVESTMENT));

        // different description -> returns false
        Entry editedEntry = new IncomeBuilder(ALLOWANCE).withDescription(VALID_DESC_INVESTMENT).build();
        assertFalse(ALLOWANCE.equals(editedEntry));

        // different amount -> return false
        editedEntry = new IncomeBuilder(ALLOWANCE).withAmount(VALID_AMT_INVESTMENT).build();
        assertFalse(ALLOWANCE.equals(editedEntry));

        // different date -> return false
        editedEntry = new IncomeBuilder(ALLOWANCE).withDate(VALID_DATE_INVESTMENT).build();
        assertFalse(ALLOWANCE.equals(editedEntry));

        // different tags -> return false
        editedEntry = new IncomeBuilder(ALLOWANCE).withTag(VALID_TAG_INVESTMENT).build();
        assertFalse(ALLOWANCE.equals(editedEntry));


    }
}
