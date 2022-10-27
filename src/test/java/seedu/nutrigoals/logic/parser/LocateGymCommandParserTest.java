package seedu.nutrigoals.logic.parser;

import static seedu.nutrigoals.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.nutrigoals.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.nutrigoals.logic.commands.LocateGymCommand;


public class LocateGymCommandParserTest {
    private LocateGymCommandParser parser = new LocateGymCommandParser();

    @Test
    public void parseFailure() {
        String invalidInput = "@121asfb";
        String expected = String.format(MESSAGE_INVALID_COMMAND_FORMAT, LocateGymCommand.MESSAGE_USAGE);
        assertParseFailure(parser, invalidInput, expected);
    }

}
