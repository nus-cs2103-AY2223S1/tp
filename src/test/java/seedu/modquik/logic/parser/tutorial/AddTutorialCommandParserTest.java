package seedu.modquik.logic.parser.tutorial;

import static seedu.modquik.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.modquik.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.modquik.logic.commands.tutorial.AddTutorialCommand;
import seedu.modquik.logic.parser.ModQuikParser;
import seedu.modquik.model.datetime.DatetimeCommonUtils;
import seedu.modquik.model.tutorial.Tutorial;
import seedu.modquik.testutil.TutorialBuilder;

/**
 * Test cases for adding tutorials
 */
public class AddTutorialCommandParserTest {

    private ModQuikParser parser = new ModQuikParser();

    @Test
    public void parse_validArgs_returnsAddTutorialCommand() {
        Tutorial tutorial = new TutorialBuilder()
                .withName("W1").withModule("CS1101").withVenue("COM1")
                .withTimeslot("08:00", "09:00").withDay("1")
                .build();
        String userInput = "add tutorial n/W1 m/CS1101 v/COM1 T/08:00-09:00 D/1";
        assertParseSuccess(parser, userInput, new AddTutorialCommand(tutorial));
    }


    @Test
    public void parse_invalidTimeFormat_throwsParseException() {
        String userInput;
        userInput = "add tutorial n/W1 m/CS1101 v/COM1 T/hello D/1";
        assertParseFailure(parser, userInput, DatetimeCommonUtils.TIMERANGE_MESSAGE_CONSTRAINTS);
        userInput = "add tutorial n/W1 m/CS1101 v/COM1 T/hi there D/1";
        assertParseFailure(parser, userInput, DatetimeCommonUtils.TIMERANGE_MESSAGE_CONSTRAINTS);
        userInput = "add tutorial n/W1 m/CS1101 v/COM1 T/- D/1";
        assertParseFailure(parser, userInput, DatetimeCommonUtils.TIMERANGE_MESSAGE_CONSTRAINTS);
        userInput = "add tutorial n/W1 m/CS1101 v/COM1 T/08:00- D/1";
        assertParseFailure(parser, userInput, DatetimeCommonUtils.TIMERANGE_MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidDayFormat_throwsParseException() {
        String userInput;
        userInput = "add tutorial n/W1 m/CS1101 v/COM1 T/08:00-09:00 D/hello";
        assertParseFailure(parser, userInput, DatetimeCommonUtils.DAY_MESSAGE_CONSTRAINTS);
        userInput = "add tutorial n/W1 m/CS1101 v/COM1 T/08:00-09:00 D/hi there";
        assertParseFailure(parser, userInput, DatetimeCommonUtils.DAY_MESSAGE_CONSTRAINTS);
    }

    @Test
    public void parse_invalidTime_throwsParseException() {
        String userInput;
        userInput = "add tutorial n/W1 m/CS1101 v/COM1 T/08:00-99:99 D/1";
        assertParseFailure(parser, userInput, DatetimeCommonUtils.TIMERANGE_MESSAGE_CONSTRAINTS_UNPARSABLE);
    }

    @Test
    public void parse_invalidTimeStartEnd_throwsParseException() {
        String userInput;
        userInput = "add tutorial n/W1 m/CS1101 v/COM1 T/08:00-00:00 D/1";
        assertParseFailure(parser, userInput, DatetimeCommonUtils.TIMERANGE_MESSAGE_CONSTRAINTS_START_END);
    }
}
