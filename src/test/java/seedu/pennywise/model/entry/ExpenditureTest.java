package seedu.pennywise.model.entry;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_AMT_DINNER;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_DATE_DINNER;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_DESC_DINNER;
import static seedu.pennywise.logic.commands.CommandTestUtil.VALID_TAG_MOVIE;
import static seedu.pennywise.testutil.TypicalEntry.DINNER;
import static seedu.pennywise.testutil.TypicalEntry.LUNCH;

import org.junit.jupiter.api.Test;

import seedu.pennywise.testutil.ExpenditureBuilder;


public class ExpenditureTest {
    @Test
    public void isSameExpenditure() {
        // same object -> returns True
        assertTrue(LUNCH.isSameEntry(LUNCH));

        // null -> returns false
        assertFalse(LUNCH.isSameEntry(null));
    }

    @Test
    public void equals() {

        //same values -> returns true
        Entry lunchCopy = new ExpenditureBuilder(LUNCH).build();
        assertTrue(LUNCH.equals(lunchCopy));

        // same object -> returns true
        assertTrue(LUNCH.equals(LUNCH));

        // null -> returns false
        assertFalse(LUNCH.equals(null));

        // different type -> returns false
        assertFalse(LUNCH.equals(5));

        // different entry -> returns false
        assertFalse(LUNCH.equals(DINNER));

        // different description -> returns false
        Entry editedEntry = new ExpenditureBuilder(LUNCH).withDescription(VALID_DESC_DINNER).build();
        assertFalse(LUNCH.equals(editedEntry));

        // different amount -> return false
        editedEntry = new ExpenditureBuilder(LUNCH).withAmount(VALID_AMT_DINNER).build();
        assertFalse(LUNCH.equals(editedEntry));

        // different date -> return false
        editedEntry = new ExpenditureBuilder(LUNCH).withDate(VALID_DATE_DINNER).build();
        assertFalse(LUNCH.equals(editedEntry));

        // different tag -> return false
        editedEntry = new ExpenditureBuilder(LUNCH).withTag(VALID_TAG_MOVIE).build();
        assertFalse(LUNCH.equals(editedEntry));


    }
}
