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
                new GetHospitalWingCommand(new HospitalWingContainsKeywordsPredicate(Arrays.asList("south")));
        assertParseSuccess(parser, "south", expectedGetHospitalWingCommand);
        // mixed cases
        expectedGetHospitalWingCommand =
                new GetHospitalWingCommand(new HospitalWingContainsKeywordsPredicate(Arrays.asList("souTh")));
        assertParseSuccess(parser, "south", expectedGetHospitalWingCommand);
    }

}
