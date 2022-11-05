package seedu.address.logic.parser.findcommandparser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.findcommands.FindCommand;
import seedu.address.logic.commands.findcommands.FindDelivererCommand;
import seedu.address.model.person.predicates.NameContainsKeywordsPredicate;

public class FindDelivererCommandParserTest {
    private FindDelivererCommandParser parser = new FindDelivererCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_emptyArg_throwsParseException2() {
        assertParseFailure(parser, "b/", String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommandDeliverer() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindDelivererCommand(new NameContainsKeywordsPredicate<>(Arrays.asList("Charlie", "Bob")));
        assertParseSuccess(parser, "n/Charlie Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, "n/ \n Charlie Bob  \t", expectedFindCommand);
    }
}
