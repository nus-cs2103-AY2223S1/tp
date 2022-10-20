package seedu.address.model.person;

import static seedu.address.logic.parser.DateTimeParserTest.FIRST_VALID_DATE_TIME;
import static seedu.address.logic.parser.DateTimeParserTest.SECOND_VALID_DATE_TIME;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.DateTimeParser;

public class DateTimeTest {
    private static final LocalDateTime FIRST_VALID_LOCAL_DATE_TIME = DateTimeParser
            .parseLocalDateTimeFromString(FIRST_VALID_DATE_TIME);
    private static final LocalDateTime SECOND_VALID_LOCAL_DATE_TIME = DateTimeParser
            .parseLocalDateTimeFromString(SECOND_VALID_DATE_TIME);

    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTime(null));
    }

    @Test
    public void equals_success() {
        DateTime firstDateTime = new DateTime(FIRST_VALID_LOCAL_DATE_TIME);
        DateTime secondDateTime = new DateTime(SECOND_VALID_LOCAL_DATE_TIME);

        Assertions.assertEquals(firstDateTime, firstDateTime);
        Assertions.assertNotEquals(firstDateTime, null);
        Assertions.assertNotEquals(firstDateTime, secondDateTime);
    }
}
