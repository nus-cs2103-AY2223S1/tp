package seedu.address.logic.parser.getparsers;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.getcommands.GetNextOfKinCommand;
import seedu.address.model.person.NextOfKinPredicate;

public class GetNextOfKinCommandParserTest {

    private GetNextOfKinCommandParser parser = new GetNextOfKinCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetNextOfKinCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsGetNextOfKinCommand() {
        // no leading and trailing whitespaces
        GetNextOfKinCommand expectedGetNextOfKinCommand =
                new GetNextOfKinCommand(new NextOfKinPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedGetNextOfKinCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedGetNextOfKinCommand);
    }

}
