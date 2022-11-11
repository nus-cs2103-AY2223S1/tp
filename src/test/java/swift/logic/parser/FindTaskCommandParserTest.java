package swift.logic.parser;

import static swift.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static swift.logic.parser.CommandParserTestUtil.assertParseFailure;
import static swift.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import swift.logic.commands.FindTaskCommand;
import swift.model.task.TaskNameContainsKeywordsPredicate;

public class FindTaskCommandParserTest {

    private FindTaskCommandParser parser = new FindTaskCommandParser();

    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                FindTaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindTaskCommand() {
        // no leading and trailing whitespaces
        FindTaskCommand expectedFindTaskCommand =
                new FindTaskCommand(new TaskNameContainsKeywordsPredicate(Arrays.asList("discuss", "meeting")));
        assertParseSuccess(parser, "discuss meeting", expectedFindTaskCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \n discuss \n \t meeting  \t", expectedFindTaskCommand);
    }

}
