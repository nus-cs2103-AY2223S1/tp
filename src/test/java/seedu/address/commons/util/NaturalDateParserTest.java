package seedu.address.commons.util;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.commons.util.NaturalDateParser.parse;
import static seedu.address.testutil.Assert.assertThrows;

import java.time.LocalDateTime;

import org.junit.jupiter.api.Test;

class NaturalDateParserTest {

    /** Some usual inputs */
    @Test
    void parse_smoketest() {
        assertEquals(
                parse("2 Jan 2006"),
                LocalDateTime.of(2006, 1, 2, 12, 0)
        );
        assertEquals(
                parse("Jan 2 2006 15:04:05"),
                LocalDateTime.of(2006, 1, 2, 15, 4, 5)
        );
    }

    /** null */
    @Test
    void parse_null_throwsException() {
        assertThrows(NullPointerException.class, () -> parse(null));
    }

    /** Not a date/time */
    @Test
    void parse_notADateTime_throwsException() {
        assertThrows(NaturalDateParser.DateTimeNotFoundException.class, () -> parse("Hello World :p"));
    }
}
