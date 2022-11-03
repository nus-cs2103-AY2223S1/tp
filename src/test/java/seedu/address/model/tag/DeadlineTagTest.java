package seedu.address.model.tag;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.DeadlineTagBuilder;

/**
 * Unit testing Deadline tag.
 */
public class DeadlineTagTest {

    private final DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MM-uuuu");
    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DeadlineTag(null));
    }

    @Test
    public void testCheckDateFormat() {
        //Normal valid input for date
        assertTrue(DeadlineTag.checkDateFormat("31-12-2022"));

        //Invalid length for deadline(Length is less than 10 characters long)
        assertFalse(DeadlineTag.checkDateFormat("31-12-22"));

        //Invalid length for deadline(Length is more than 10 character long)
        assertFalse(DeadlineTag.checkDateFormat("031-012-2022"));

        //Using invalid character(alphabets)
        assertFalse(DeadlineTag.checkDateFormat("th-no-2022"));

        //Using invalid number for date for deadline(In this case there is no month with 32nd day)
        assertFalse(DeadlineTag.checkDateFormat("32-01-2022"));

        //Using invalid number for month for deadline
        assertFalse(DeadlineTag.checkDateFormat("10-13-2022"));

    }

    @Test
    public void testIsValidDeadline() {

        //Normal valid input for date
        assertTrue(DeadlineTag.isValidDeadline(LocalDate.parse("12-12-2022", dateTimeFormatter)));

        //Null value for date
        assertFalse(DeadlineTag.isValidDeadline(null));

        //Invalid date (Date in the past)
        assertFalse(DeadlineTag.isValidDeadline(LocalDate.parse("01-01-2022", dateTimeFormatter)));
    }

    @Test
    public void testEquals() {
        DeadlineTag decemberDeadlineTag = new DeadlineTagBuilder().withDeadline(LocalDate.parse("13-12-2022",
                dateTimeFormatter)).build();
        DeadlineTag januaryDeadlineTag = new DeadlineTagBuilder().withDeadline(LocalDate
                .parse("01-01-2023", dateTimeFormatter)).build();

        //Creates a copy of the deadline tag
        DeadlineTag decemberDeadlineTagCopy = new DeadlineTagBuilder(decemberDeadlineTag).build();

        //Checks whether the deadline tag is same as itself
        assertTrue(decemberDeadlineTag.equals(decemberDeadlineTag));

        //Checks whether the deadline tag is same as another deadline tag object with same dates
        assertTrue(decemberDeadlineTag.equals(decemberDeadlineTagCopy));

        //Checks whether the deadline tag is different as null
        assertFalse(decemberDeadlineTag.equals(null));

        //Checks whether the deadline tag is different from deadline tag with different date
        assertFalse(decemberDeadlineTag.equals(januaryDeadlineTag));

        //Checks whether deadline tag is equal with different object type
        assertFalse(decemberDeadlineTag.equals(15));
    }

    @Test
    public void testToString() {
        DeadlineTag deadlineTag = new DeadlineTag(LocalDate.parse("16-12-2022", dateTimeFormatter));
        assertEquals("16-12-2022", deadlineTag.toString());
    }

    @Test
    public void testCompareTo() {
        DeadlineTag novemberDeadlineTag = new DeadlineTagBuilder()
                .withDeadline(LocalDate.parse("16-11-2022", dateTimeFormatter)).build();
        DeadlineTag novemberDeadlineTagCopy = new DeadlineTagBuilder(novemberDeadlineTag).build();
        DeadlineTag decemberDeadlineTag = new DeadlineTagBuilder()
                .withDeadline(LocalDate.parse("16-12-2022", dateTimeFormatter)).build();
        DeadlineTag octoberDeadlineTag = new DeadlineTagBuilder()
                .withDeadline(LocalDate.parse("16-10-2022", dateTimeFormatter)).build();

        //Same date
        assertEquals(0, novemberDeadlineTag.compareTo(novemberDeadlineTagCopy));

        //Date in the future
        assertEquals(-1, novemberDeadlineTag.compareTo(decemberDeadlineTag));

        //Date in the past
        assertEquals(1, novemberDeadlineTag.compareTo(octoberDeadlineTag));

    }
}
