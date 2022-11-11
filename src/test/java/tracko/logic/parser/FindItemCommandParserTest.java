package tracko.logic.parser;

import static tracko.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static tracko.logic.commands.item.FindItemCommand.MESSAGE_USAGE;
import static tracko.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import tracko.logic.commands.item.FindItemCommand;
import tracko.logic.parser.item.FindItemCommandParser;
import tracko.model.item.ItemContainsKeywordsPredicate;

public class FindItemCommandParserTest {
    private FindItemCommandParser parser = new FindItemCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        CommandParserTestUtil.assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindItemCommand() {
        // no leading and trailing whitespaces
        FindItemCommand expectedFindItemCommand =
                new FindItemCommand(new ItemContainsKeywordsPredicate(Arrays.asList("Chair", "Keychain")));
        assertParseSuccess(parser, "Chair Keychain", expectedFindItemCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Chair \n \t Keychain  \t", expectedFindItemCommand);
    }

}
