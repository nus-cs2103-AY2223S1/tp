package bookface.logic.parser.find;

import static bookface.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static bookface.logic.parser.CommandParserTestUtil.assertParseFailure;
import static bookface.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static bookface.testutil.TestUtil.preparePredicateToCheckPersonForPartialWordIgnoreCase;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import bookface.logic.commands.find.FindUserCommand;

public class FindUserArgumentsParserTest {

    private final FindUserArgumentsParser parser = new FindUserArgumentsParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindUserCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindUserCommand expectedFindCommand =
                new FindUserCommand(
                        preparePredicateToCheckPersonForPartialWordIgnoreCase(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

}
