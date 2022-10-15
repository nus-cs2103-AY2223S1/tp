package seedu.address.model.person;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_21_JAN_2023;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_22_JAN_2023;
import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import seedu.address.logic.parser.ParserUtil;

public class DateTimeTest {


    @Test
    public void constructor_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new DateTime(null));
    }

    @Test
    public void equals_success() {
        DateTime firstDateTime = ParserUtil.parseDateTime(VALID_DATETIME_21_JAN_2023);
        DateTime secondDateTime = ParserUtil.parseDateTime(VALID_DATETIME_22_JAN_2023);

        Assertions.assertEquals(firstDateTime, firstDateTime);
        Assertions.assertNotEquals(firstDateTime, null);
        Assertions.assertNotEquals(firstDateTime, secondDateTime);
    }
}
