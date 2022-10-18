package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;

import java.time.LocalDate;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.task.DeadlineTaskCommand;
import seedu.address.model.task.Deadline;

public class DeadlineTaskCommandParserTest {
    private DeadlineTaskCommandParser parser = new DeadlineTaskCommandParser();

    @Test
    public void parse_validArgs_returnsAssignTaskCommand() {
        LocalDate date = LocalDate.of(2022, 9, 19);
        assertParseSuccess(
                parser,
                "1 by/ 19-09-2022",
                new DeadlineTaskCommand(INDEX_FIRST_TASK, Deadline.of(date))
        );

        assertParseSuccess(
                parser,
                "1 by/ ?",
                new DeadlineTaskCommand(INDEX_FIRST_TASK, Deadline.UNSPECIFIED)
        );
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {

        // no task index specified
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeadlineTaskCommand.MESSAGE_USAGE));

        // invalid task index
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeadlineTaskCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "0", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                DeadlineTaskCommand.MESSAGE_USAGE));

    }
}
