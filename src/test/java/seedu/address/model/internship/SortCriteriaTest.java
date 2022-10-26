package seedu.address.model.internship;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class SortCriteriaTest {

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> SortCriteria.parse(null));
    }

    @Test
    public void constructor_invalidSortCriteria_throwsParseException() {
        String invalidSortCriteria = "";
        assertThrows(ParseException.class, () -> SortCriteria.parse(invalidSortCriteria));
    }

    @Test
    public void isValidSortCriteria() {
        // null sort criteria
        assertThrows(NullPointerException.class, () -> SortCriteria.isValidSortCriteria(null));

        // invalid sort criteria
        assertFalse(SortCriteria.isValidSortCriteria("")); // empty string
        assertFalse(SortCriteria.isValidSortCriteria(" ")); // spaces only
        assertFalse(SortCriteria.isValidSortCriteria("shortlisted")); // invalid status

        // valid sort criteria
        assertTrue(SortCriteria.isValidSortCriteria("applied"));
        assertTrue(SortCriteria.isValidSortCriteria("interview"));
    }
}
