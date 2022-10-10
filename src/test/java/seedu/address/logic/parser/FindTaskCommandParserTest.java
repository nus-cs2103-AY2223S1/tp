package seedu.address.logic.parser;

import org.junit.jupiter.api.Test;
import seedu.address.logic.commands.FindTaskCommand;
import seedu.address.model.task.TaskContainsKeywordsPredicate;

import java.util.Arrays;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

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
        assertParseSuccess(parser, "Study Shower", expectedFindCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n Study \n \t Shower  \t", expectedFindCommand);
    }

}
