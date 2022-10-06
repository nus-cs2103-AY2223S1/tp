package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindByAddressCommand;
import seedu.address.model.person.AddressContainsKeywordsPredicate;

public class FindAddressCommandParserTest {

    private final FindAddressCommandParser parser = new FindAddressCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, FindByAddressCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindByAddressCommand expectedFindCommand =
                new FindByAddressCommand(new AddressContainsKeywordsPredicate(Arrays.asList("Kaki", "Bukit")));
        assertParseSuccess(parser, "Kaki Bukit", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Kaki \n \t Bukit  \t", expectedFindCommand);
    }
}
