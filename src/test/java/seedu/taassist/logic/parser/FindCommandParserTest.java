package seedu.taassist.logic.parser;

import static seedu.taassist.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.taassist.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.taassist.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.taassist.logic.commands.FindCommand;
import seedu.taassist.model.student.predicate.NameContainsKeywordsPredicate;

public class FindCommandParserTest {

    private static final String MESSAGE_INVALID_FORMAT =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindCommand.COMMAND_WORD, FindCommand.MESSAGE_USAGE);
    private FindCommandParser parser = new FindCommandParser();


    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", MESSAGE_INVALID_FORMAT);
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindCommand expectedFindCommand =
                new FindCommand(new NameContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindCommand);
    }

}
