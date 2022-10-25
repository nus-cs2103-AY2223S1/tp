package tracko.logic.parser;

import static tracko.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import tracko.logic.commands.order.FindOrderCommand;
import tracko.logic.parser.order.FindOrderCommandParser;
import tracko.model.order.OrderContainsKeywordsPredicate;

public class FindOrderCommandParserTest {
    // TODO: Update test cases according to new implementations

    private FindOrderCommandParser parser = new FindOrderCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "     ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindOrderCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindOrderCommand expectedFindOrderCommand =
                new FindOrderCommand(new OrderContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        CommandParserTestUtil.assertParseSuccess(parser, "Alice Bob", expectedFindOrderCommand);

        // multiple whitespaces between keywords
        CommandParserTestUtil.assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindOrderCommand);
    }

}
