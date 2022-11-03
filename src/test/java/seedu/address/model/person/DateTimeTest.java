package seedu.address.model.person;

import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_21_JAN_2023;
import static seedu.address.logic.commands.CommandTestUtil.VALID_DATETIME_22_JAN_2023;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.ParserUtil;
import seedu.address.model.appointment.DateTime;

public class DateTimeTest {

    private class ValidDateTimeStub extends DateTime {
        ValidDateTimeStub() {
            super(LocalDateTime.of(2023, 1, 21, 9, 00));
        }
        @Override
        public String toString() {
            return "21-Jan-2023 09:00 AM";
        }
    }

    @Test
    public void getYear_sameYear_success() {
        Assertions.assertEquals(2023, new ValidDateTimeStub().getYear());
        Assertions.assertNotEquals(2022, new ValidDateTimeStub().getYear());
    }


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
