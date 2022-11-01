package seedu.address.logic.parser.addcommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_MISSING_INDEX;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.addcommands.AddOrderCommand;

public class AddOrderCommandParserTest {
    private AddOrderCommandParser parser = new AddOrderCommandParser();

    @Test
    public void parse_emptyString_parseFailure() {
        String expected = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MESSAGE_MISSING_INDEX + AddOrderCommand.MESSAGE_USAGE_EXISTING_BUYER);
        String input = "";
        assertParseFailure(parser, input, expected);
    }

    @Test
    public void parse_spaces_parseFailure() {
        String expected = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MESSAGE_MISSING_INDEX + AddOrderCommand.MESSAGE_USAGE_EXISTING_BUYER);
        String input = "          \n";
        assertParseFailure(parser, input, expected);
    }

    @Test
    public void parse_invalidArgs_parseFailure() {
        String expected = String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                MESSAGE_MISSING_INDEX + AddOrderCommand.MESSAGE_USAGE_EXISTING_BUYER);
        String input = "this is an invalid argument";
        assertParseFailure(parser, input, expected);
    }

    //TODO: add more test cases
}
