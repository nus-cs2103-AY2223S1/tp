package seedu.address.logic.parser;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import seedu.address.logic.parser.exceptions.ParseException;

public class ViewMeetingCommandParserTest {

    private ViewMeetingCommandParser vmcp = new ViewMeetingCommandParser();

    @Test
    public void parse_negativeIndexGiven_throwsParseException() {
        String args = "viewMeeting i/-1";
        Assertions.assertThrows(ParseException.class, () -> vmcp.parse(args));
    }

}
