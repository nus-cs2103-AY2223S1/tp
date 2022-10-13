package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;
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
        // no name
        assertParseFailure(parser, "" + PREFIX_GROUP + "Group" + " " + PREFIX_TASK + "Task",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignTaskCommand.MESSAGE_USAGE));

        // empty name
        assertParseFailure(parser, " " + PREFIX_GROUP + "Group" + " " + PREFIX_TASK + "Task",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignTaskCommand.MESSAGE_USAGE));

        // no group
        assertParseFailure(parser, " " + "Alex Yeoh" + " " + PREFIX_TASK + "Task",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignTaskCommand.MESSAGE_USAGE));

        // invalid group
        assertParseFailure(parser, " " + "Alex Yeoh" + " "
                        + PREFIX_GROUP + " " + " " + PREFIX_TASK + "Task",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, GroupName.MESSAGE_CONSTRAINTS));

        // no group prefix
        assertParseFailure(parser, " " + "Alex Yeoh" + " " + "Group" + " " + PREFIX_TASK + "Task",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignTaskCommand.MESSAGE_USAGE));

        // no task
        assertParseFailure(parser, " " + "Alex Yeoh" + " " + PREFIX_GROUP + "Group",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignTaskCommand.MESSAGE_USAGE));

        // invalid task
        assertParseFailure(parser, " " + "Alex Yeoh" + " "
                        + PREFIX_GROUP + "Group" + " " + PREFIX_TASK + " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, Assignment.MESSAGE_CONSTRAINTS));

        // no task prefix
        assertParseFailure(parser, " " + "Alex Yeoh" + " " + PREFIX_GROUP + "Group" + " " + "Task",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, AssignTaskCommand.MESSAGE_USAGE));
    }

    @Test
    public void parse_validInput_commandParseSuccess() {
        assertParseSuccess(parser, " " + "Alex Yeoh" + " "
                        + PREFIX_GROUP + "Group" + " " + PREFIX_TASK + "Task",
                new AssignTaskCommand(new Name("Alex Yeoh"), "Group", new Assignment("Task")));
    }
}
