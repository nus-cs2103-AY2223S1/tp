package tracko.logic.parser;

import static tracko.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tracko.logic.parser.CommandParserTestUtil.assertParseFailure;
import static tracko.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import tracko.logic.commands.order.FindOrderCommand;
import tracko.logic.parser.order.FindOrderCommandParser;
import tracko.model.person.NameContainsKeywordsPredicate;

public class FindOrderCommandParserTest {

    private FindOrderCommandParser parser = new FindOrderCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindOrderCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindOrderCommand expectedFindOrderCommand =
                new FindOrderCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindOrderCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindOrderCommand);
    }

}
