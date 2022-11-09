package seedu.nutrigoals.logic.parser;

import static seedu.nutrigoals.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.nutrigoals.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.nutrigoals.logic.parser.CommandParserTestUtil.assertParseSuccess;

import org.junit.jupiter.api.Test;

import seedu.nutrigoals.logic.commands.ListCommand;
import seedu.nutrigoals.model.meal.DateTime;
import seedu.nutrigoals.model.meal.IsFoodAddedOnThisDatePredicate;

public class ListCommandParserTest {

    private static final String MESSAGE_INVALID_COMMAND =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, ListCommand.MESSAGE_USAGE);
    private ListCommandParser parser = new ListCommandParser();

    @Test
    public void parse_invalidDate_throwsParseException() {
        assertParseFailure(parser, "2022-02-29", MESSAGE_INVALID_COMMAND);
        assertParseFailure(parser, "0000-06-18", MESSAGE_INVALID_COMMAND);
    }

    @Test
    public void parse_invalidDateFormat_throwsParseException() {
        assertParseFailure(parser, "2022", MESSAGE_INVALID_COMMAND);
        assertParseFailure(parser, "today", MESSAGE_INVALID_COMMAND);
    }

    @Test
    public void parse_noArg_returnsListCommand() {
        IsFoodAddedOnThisDatePredicate predicate = new IsFoodAddedOnThisDatePredicate(new DateTime());
        ListCommand expectedListCommand = new ListCommand(predicate);
        assertParseSuccess(parser, "", expectedListCommand);
    }

    @Test
    public void parse_validArgs_returnsListCommand() {
        // no leading and trailing whitespaces
        IsFoodAddedOnThisDatePredicate predicate = new IsFoodAddedOnThisDatePredicate(new DateTime("2022-11-03T00:00"));
        ListCommand expectedListCommand = new ListCommand(predicate);
        assertParseSuccess(parser, "2022-11-03", expectedListCommand);

        // whitespaces
        assertParseSuccess(parser, " \n 2022-11-03 \t", expectedListCommand);
    }
}
