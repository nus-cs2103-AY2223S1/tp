package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.commands.AssignTaskCommand.MESSAGE_EMPTY_DEADLINE;
import static seedu.address.logic.commands.AssignTaskCommand.MESSAGE_EMPTY_NAME;
import static seedu.address.logic.commands.AssignTaskCommand.MESSAGE_NO_PREFIX_GROUP;
import static seedu.address.logic.commands.AssignTaskCommand.MESSAGE_NO_PREFIX_TASK;
import static seedu.address.logic.commands.AssignTaskCommand.MESSAGE_NO_PREFIX_WORKLOAD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.address.logic.parser.CliSyntax.PREFIX_WORKLOAD;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.person.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AssignTaskCommand;
import seedu.address.model.assignment.Assignment;
import seedu.address.model.group.GroupName;
import seedu.address.model.person.Name;

public class AssignTaskCommandParserTest {

    private AssignTaskCommandParser parser = new AssignTaskCommandParser();

    @Test
    public void parse_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_invalidInput_throwsParseException() {

        // empty name
        assertParseFailure(parser, " " + PREFIX_GROUP + "Group" + " " + PREFIX_TASK + "Task" + " "
                + PREFIX_WORKLOAD + "low",
                String.format(MESSAGE_EMPTY_NAME, AssignTaskCommand.MESSAGE_USAGE));

        // no group
        assertParseFailure(parser, " " + "Alex Yeoh" + " " + PREFIX_TASK + "Task" + " "
                + PREFIX_WORKLOAD + "low",
                String.format(MESSAGE_NO_PREFIX_GROUP, AssignTaskCommand.MESSAGE_USAGE));

        // invalid group
        assertParseFailure(parser, " " + "Alex Yeoh" + " "
                        + PREFIX_GROUP + " " + " " + PREFIX_TASK + "Task" + " " + PREFIX_WORKLOAD + "low",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupName.MESSAGE_CONSTRAINTS));

        // no group prefix
        assertParseFailure(parser, " " + "Alex Yeoh" + " " + "Group" + " " + PREFIX_TASK + "Task" + " "
                        + PREFIX_WORKLOAD + "low",
                String.format(MESSAGE_NO_PREFIX_GROUP, AssignTaskCommand.MESSAGE_USAGE));

        // no task
        assertParseFailure(parser, " " + "Alex Yeoh" + " " + PREFIX_GROUP + "Group" + " "
                        + PREFIX_WORKLOAD + "low",
                String.format(MESSAGE_NO_PREFIX_TASK, AssignTaskCommand.MESSAGE_USAGE));

        // invalid task
        assertParseFailure(parser, " " + "Alex Yeoh" + " "
                        + PREFIX_GROUP + "Group" + " " + PREFIX_TASK + " " + PREFIX_WORKLOAD + "low",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, Assignment.MESSAGE_CONSTRAINTS));

        // no task prefix
        assertParseFailure(parser, " " + "Alex Yeoh" + " " + PREFIX_GROUP + "Group" + " " + "Task",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignTaskCommand.MESSAGE_USAGE));

        // no workload
        assertParseFailure(parser, " " + "Alex Yeoh" + " " + PREFIX_GROUP + "Group"
                        + PREFIX_TASK + "Task",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignTaskCommand.MESSAGE_USAGE));

        // no workload prefix
        assertParseFailure(parser, " " + "Alex Yeoh" + " " + PREFIX_GROUP + "Group" + " "
                        + PREFIX_TASK + "Task" + " " + "very high",
                String.format(MESSAGE_NO_PREFIX_WORKLOAD, AssignTaskCommand.MESSAGE_USAGE));

        // invalid deadline
        assertParseFailure(parser, " " + "Alex Yeoh" + " "
                        + PREFIX_GROUP + "Group" + " " + PREFIX_TASK + "Task" + " "
                        + PREFIX_WORKLOAD + "low" + " " + PREFIX_DEADLINE,
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_EMPTY_DEADLINE));
    }

    @Test
    public void parse_validInput_commandParseSuccess() {
        assertParseSuccess(parser, " " + "Alex Yeoh" + " "
                        + PREFIX_GROUP + "Group" + " " + PREFIX_TASK + "Task" + " "
                        + PREFIX_WORKLOAD + "low" + " " + PREFIX_DEADLINE + "2022-12-12 23:59",
                new AssignTaskCommand(new Name("Alex Yeoh"),
                        "Group",
                        new Assignment("Task")));
    }
}
