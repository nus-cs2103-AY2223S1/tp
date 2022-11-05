package seedu.address.model.task;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

public class DeadlineTest {

    @Test
    public void constructor_invalidDateString_throwsIllegalArgumentException() {
        assertThrows(IllegalArgumentException.class, () -> Deadline.of(" "));
        assertThrows(IllegalArgumentException.class, () -> Deadline.of("2022-10-10"));
        assertThrows(IllegalArgumentException.class, () -> Deadline.of("10/10/2022"));
    }

    @Test
    public void formatForUi_withinCurrentYear_shouldNotDisplayYear() {
        LocalDate withinYear = LocalDate.now();
        assertEquals(
                Deadline.of(withinYear).formatForUi(),
                withinYear.format(Deadline.READABLE_FORMATTER_WITHOUT_YEAR)
        );
    }
    @Test
    public void formatForUi_beforeCurrentYear_shouldDisplayYear() {
        LocalDate beforeYear = LocalDate.now().minusYears(1);
        assertEquals(Deadline.of(beforeYear).formatForUi(), beforeYear.format(Deadline.READABLE_FORMATTER_WITH_YEAR));
    }
    @Test
    public void formatForUi_afterCurrentYear_shouldDisplayYear() {
        LocalDate afterYear = LocalDate.now().plusYears(1);
        assertEquals(Deadline.of(afterYear).formatForUi(), afterYear.format(Deadline.READABLE_FORMATTER_WITH_YEAR));
    }
}
