package seedu.address.logic.parser.task;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import java.util.Arrays;
import java.util.HashSet;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.task.AssignTaskCommand;

public class AssignTaskCommandParserTest {

    private AssignTaskCommandParser parser = new AssignTaskCommandParser();

    @Test
    public void parse_validArgs_returnsAssignTaskCommand() {
        assertParseSuccess(parser, "1", new AssignTaskCommand(INDEX_FIRST_TASK, new HashSet<>()));

        assertParseSuccess(parser, "1 c/1 c/2", new AssignTaskCommand(INDEX_FIRST_TASK,
                new HashSet<>(Arrays.asList(INDEX_FIRST_PERSON, INDEX_SECOND_PERSON))));
    }

    @Test
    public void parse_invalidArgs_throwsParseException() {

        // no task index specified
        assertParseFailure(parser, "", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AssignTaskCommand.MESSAGE_USAGE));

        // invalid task index
        assertParseFailure(parser, "a", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AssignTaskCommand.MESSAGE_USAGE));

        assertParseFailure(parser, "0", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AssignTaskCommand.MESSAGE_USAGE));

        // invalid person index
        assertParseFailure(parser, "1 c/0", String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                AssignTaskCommand.MESSAGE_USAGE));
    }

}
