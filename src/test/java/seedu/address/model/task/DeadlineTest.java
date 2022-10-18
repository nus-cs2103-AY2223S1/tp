package seedu.address.model.task;

import static seedu.address.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class DeadlineTest {

    @Test
    public void constructor_invalidDateString_throwsParseException() {
        assertThrows(ParseException.class, () -> Deadline.of(""));
        assertThrows(ParseException.class, () -> Deadline.of("2022-10-10"));
        assertThrows(ParseException.class, () -> Deadline.of("10/10/2022"));
    }

}
