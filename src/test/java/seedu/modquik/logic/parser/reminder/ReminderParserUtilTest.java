package seedu.modquik.logic.parser.reminder;

import static seedu.modquik.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.modquik.logic.parser.exceptions.ParseException;

public class ReminderParserUtilTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PRIORITY = "pork";

    @Test
    public void parseName_invalidValue_throwsParseException() {
        // invalid name
        assertThrows(ParseException.class, () -> ReminderParserUtil.parseReminderName(INVALID_NAME));

        //invalid priority
        assertThrows(ParseException.class, () -> ReminderParserUtil.parseReminderPriority(INVALID_PRIORITY));
    }

}
