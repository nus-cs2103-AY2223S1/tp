package seedu.address.model.person;

import static seedu.address.logic.commands.CommandTestUtil.FIRST_VALID_LOCAL_DATE_TIME;
import static seedu.address.logic.commands.CommandTestUtil.SECOND_VALID_LOCAL_DATE_TIME;
import static seedu.address.logic.parser.DateTimeParserTest.FIRST_VALID_DATE_TIME;
import static seedu.address.logic.parser.DateTimeParserTest.SECOND_VALID_DATE_TIME;
import static seedu.address.logic.parser.DateTimeParserTest.THIRD_VALID_DATE_TIME;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.DateTimeParser;
import seedu.address.logic.parser.ParserUtil;

public class DateTimeTest {


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
