package seedu.codeconnect.logic.parser;

import static seedu.codeconnect.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.codeconnect.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.codeconnect.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.codeconnect.logic.commands.FindTaskCommand;
import seedu.codeconnect.model.task.TaskContainsKeywordsPredicate;

public class FindTaskCommandParserTest {

    private FindTaskCommandParser parser = new FindTaskCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindCommand() {
        // no leading and trailing whitespaces
        FindTaskCommand expectedFindCommand =
                new FindTaskCommand(new TaskContainsKeywordsPredicate(Arrays.asList("Study", "Shower")));
        assertParseSuccess(parser, " n/Study Shower", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " n/ \n Study \n \t Shower  \t", expectedFindCommand);
    }

}
