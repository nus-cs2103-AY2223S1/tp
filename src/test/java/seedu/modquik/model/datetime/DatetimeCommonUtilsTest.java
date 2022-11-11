package seedu.modquik.model.datetime;

import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.modquik.logic.parser.exceptions.ParseException;

public class DatetimeCommonUtilsTest {

    @Test
    public void invalidDatetime_throwsParseException() {
        //invalid datetime format
        assertThrows(ParseException.class, () -> DatetimeCommonUtils.assertDatetimeValid("2023-01- 08:00"));
    }
}
