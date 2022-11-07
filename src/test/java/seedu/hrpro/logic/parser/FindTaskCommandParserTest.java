package seedu.hrpro.logic.parser;

import static seedu.hrpro.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.hrpro.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.hrpro.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.hrpro.logic.commands.FindTaskCommand;
import seedu.hrpro.model.task.TaskDescriptionContainsKeywordsPredicate;

/**
 * Contains test cases for FindTaskCommandParser.
 */
public class FindTaskCommandParserTest {

    private FindTaskCommandParser parser = new FindTaskCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                           String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindTaskCommand() {
        // no leading and trailing whitespaces
        FindTaskCommand expectedFindTaskCommand =
                new FindTaskCommand(new TaskDescriptionContainsKeywordsPredicate(Arrays.asList("Alice", "Bob")));
        assertParseSuccess(parser, "Alice Bob", expectedFindTaskCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Alice \n \t Bob  \t", expectedFindTaskCommand);
    }

}
