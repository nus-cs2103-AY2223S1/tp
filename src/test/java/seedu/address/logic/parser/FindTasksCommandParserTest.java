package seedu.address.logic.parser;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;

import java.util.Arrays;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.FindTasksCommand;
import seedu.address.model.task.DescriptionContainsKeywordsPredicate;

public class FindTasksCommandParserTest {

    private FindTaskCommandParser parser = new FindTaskCommandParser();
    @Test
    public void parse_emptyArg_throwsParseException() {
        assertParseFailure(parser, "     ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, FindTasksCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validArgs_returnsFindTasksCommand() {
        // no leading and trailing whitespaces
        FindTasksCommand expectedFindTasksCommand =
                new FindTasksCommand(new DescriptionContainsKeywordsPredicate(Arrays.asList("ask, on")));
        assertParseSuccess(parser, "ask, on", expectedFindTasksCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \t \n ask, on\n \t \t", expectedFindTasksCommand);
    }

    @Test
    public void parse_validAllCapsArgsPassToPredicateAsLowerCase_returnsFindTasksCommand() {
        // no leading and trailing whitespaces
        FindTasksCommand expectedFindTasksCommand =
                new FindTasksCommand(new DescriptionContainsKeywordsPredicate(Arrays.asList("ask, on")));
        assertParseSuccess(parser, "ASK, ON", expectedFindTasksCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \t \n ASK, ON\n \t \t", expectedFindTasksCommand);
    }

    @Test
    public void parse_validMixCaseArgsPassToPredicateAsLowerCase_returnsFindTasksCommand() {
        // no leading and trailing whitespaces
        FindTasksCommand expectedFindTasksCommand =
                new FindTasksCommand(new DescriptionContainsKeywordsPredicate(Arrays.asList("ask, on")));
        assertParseSuccess(parser, "AsK, On", expectedFindTasksCommand);

        // multiple whitespaces between keywords
        assertParseSuccess(parser, " \t \n AsK, On\n \t \t", expectedFindTasksCommand);
    }

}
