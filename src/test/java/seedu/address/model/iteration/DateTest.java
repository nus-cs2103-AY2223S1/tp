package seedu.address.model.iteration;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DateTest {
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new Date(null));
    }

    @Test
    public void constructor_anyDate_success() {
        LocalDate anyDate = LocalDate.now();
        assertDoesNotThrow(() -> new Date(anyDate));
    }

    @Test
    public void equals() {
        LocalDate someLocalDate = LocalDate.of(2022, 10, 18);
        LocalDate anotherLocalDate = LocalDate.of(2022, 10, 19);
        Date someDate = new Date(someLocalDate);
        Date anotherDate = new Date(anotherLocalDate);

        // same object -> returns true
        assertTrue(someDate.equals(someDate));

        // different type -> returns false
        assertFalse(someDate.equals(1));

        // null -> returns false
        assertFalse(someDate.equals(null));

        // same values (copy) -> returns true
        assertTrue(someDate.equals(new Date(someLocalDate)));

        // different feedback -> returns false
        assertFalse(someDate.equals(anotherDate));
    }
}
