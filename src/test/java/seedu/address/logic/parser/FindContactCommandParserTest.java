package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindContactCommand;
import seedu.address.model.person.NameContainsKeywordsPredicate;

public class FindContactCommandParserTest {

    private FindContactCommandParser parser = new FindContactCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindContactCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {
        // multiple search prefixes (e.g. both n/ and m/)
        assertParseFailure(parser, " n/Alice m/CS1101S",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindContactCommand.MESSAGE_USAGE));

        // invalid prefixes
        assertParseFailure(parser, "s/foo",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindContactCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "s/foo g/bar",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindContactCommand.MESSAGE_USAGE));

        // no prefixes
        assertParseFailure(parser, "jeremy",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindContactCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindContactCommand expectedFindContactCommand =
                new FindContactCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));

        assertParseSuccess(parser, " n/Alice n/Bob", expectedFindContactCommand);
        assertParseSuccess(parser, " n/Alice Bob", expectedFindContactCommand);
        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/\n Alice \n \t Bob  \t", expectedFindContactCommand);
    }

}
