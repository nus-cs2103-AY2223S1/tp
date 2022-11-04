package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.AssignTaskCommand.MESSAGE_NO_PREFIX_GROUP;
import static seedu.address.logic.commands.AssignTaskCommand.MESSAGE_NO_PREFIX_TASK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WORKLOAD;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.person.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AssignTaskAllCommand;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.assignment.Workload;

public class AssignTaskAllCommandParserTest {

    private AssignTaskAllCommandParser parser = new AssignTaskAllCommandParser();

    @Test
    public void parse_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_invalidInput_throwsParseException() {

        // no group prefix
        assertParseFailure(parser, " " + "Group" + " "
                        + PREFIX_TASK + "Task" + " " + PREFIX_WORKLOAD + "low",
                String.format(MESSAGE_NO_PREFIX_GROUP, AssignTaskAllCommand.MESSAGE_USAGE));

        // no task
        assertParseFailure(parser, " " + PREFIX_GROUP + "Group" + " "
                + PREFIX_WORKLOAD + "low",
                String.format(MESSAGE_NO_PREFIX_TASK, AssignTaskAllCommand.MESSAGE_USAGE));

        // invalid task
        assertParseFailure(parser, " " + PREFIX_GROUP + "Group" + " "
                + PREFIX_TASK + " " + PREFIX_WORKLOAD + "low",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, Assignment.MESSAGE_CONSTRAINTS));

        // invalid workload
        assertParseFailure(parser, " " + PREFIX_GROUP + "Group" + " "
                + PREFIX_TASK + "Task" + " " + PREFIX_WORKLOAD + "very high",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, Workload.MESSAGE_CONSTRAINTS));

        // invalid deadline
        assertParseFailure(parser, " " + PREFIX_GROUP + "Group" + " "
                        + PREFIX_TASK + "Task" + " " + PREFIX_WORKLOAD + "low" + " "
                        + PREFIX_DEADLINE + "2022-02-30",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignTaskAllCommand.MESSAGE_EMPTY_DEADLINE));
    }

    @Test
    public void parse_validInput_commandParseSuccess() {
        assertParseSuccess(parser, " " + PREFIX_GROUP + "Group" + " "
                + PREFIX_TASK + "Task" + " " + PREFIX_WORKLOAD + "low" + " "
                + PREFIX_DEADLINE + "2022-02-28",
                new AssignTaskAllCommand("Group", new Assignment("Task")));
    }
}
