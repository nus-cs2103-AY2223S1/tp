package seedu.address.logic.parser.buyer;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.buyer.FindBuyersCommand;
import seedu.address.model.buyer.BuyerNameContainsSubstringPredicate;

public class FindBuyersCommandParserTest {

    private FindBuyersCommandParser parser = new FindBuyersCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindBuyersCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindBuyersCommand expectedFindBuyersCommand =
                new FindBuyersCommand(new BuyerNameContainsSubstringPredicate("Alice Bob"));
        assertParseSuccess(parser, "Alice Bob", expectedFindBuyersCommand);
    }
}
