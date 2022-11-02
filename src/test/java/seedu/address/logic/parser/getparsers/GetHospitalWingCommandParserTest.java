package seedu.address.logic.parser.getparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.getcommands.GetHospitalWingCommand;
import seedu.address.model.person.HospitalWingContainsKeywordsPredicate;

public class GetHospitalWingCommandParserTest {
    private GetHospitalWingCommandParser parser = new GetHospitalWingCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetHospitalWingCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsGetHospitalWingCommand() {
        // no leading and trailing whitespaces
        GetHospitalWingCommand expectedGetHospitalWingCommand =
                new GetHospitalWingCommand(new HospitalWingContainsKeywordsPredicate(Arrays.asList("South", "East")));
        assertParseSuccess(parser, "South East", expectedGetHospitalWingCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "\n South \n \t East  \t", expectedGetHospitalWingCommand);
    }

}
