package tracko.logic.parser;

import static tracko.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import tracko.logic.commands.order.FindOrderCommand;
import tracko.logic.parser.order.FindOrderCommandParser;
import tracko.model.order.OrderMatchesFlagsAndPrefixPredicate;

public class FindOrderCommandParserTest {
    private FindOrderCommandParser parser = new FindOrderCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "     ",
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindOrderCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        List<String> nameList = Arrays.asList("Alice", "Bob");
        List<String> addressList = Arrays.asList("Clementi", "Geylang");
        List<String> itemList = Arrays.asList("Keychain", "Pillow");
        FindOrderCommand expectedFindOrderCommand =
                new FindOrderCommand(
                        new OrderMatchesFlagsAndPrefixPredicate(nameList, addressList,
                                 itemList, false, true, false, true));
        CommandParserTestUtil.assertParseSuccess(parser,
                " -d n/Alice Bob a/Clementi Geylang i/Keychain Pillow", expectedFindOrderCommand);

        // multiple whitespaces between keywords
        CommandParserTestUtil.assertParseSuccess(parser,
                " -d \n n/ \n Alice \n \t Bob  \t a/ \n Clementi \n \t Geylang  \t i/ \n Keychain \n \t Pillow  \t",
                expectedFindOrderCommand);
    }

}
