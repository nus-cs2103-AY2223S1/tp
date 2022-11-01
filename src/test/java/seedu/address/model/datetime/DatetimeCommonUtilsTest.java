package seedu.address.model.datetime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;
import seedu.address.logic.parser.exceptions.ParseException;


public class DatetimeCommonUtilsTest {

    @Test
    public void validDatetime_success() {

    }

    @Test
    public void invalidDatetime_throwsParseException() {
        //invalid datetime format
        assertThrows(ParseException.class, () -> DatetimeCommonUtils.assertDatetimeValid("2023-01- 08:00"));

        //datetime before current
        assertThrows(ParseException.class, () -> DatetimeCommonUtils.assertDatetimeValid("2021-01-01 08:00"));
    }
}
