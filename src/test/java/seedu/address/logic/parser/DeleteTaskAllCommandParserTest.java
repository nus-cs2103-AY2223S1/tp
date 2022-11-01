package seedu.address.logic.parser;

import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GROUP;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.model.person.testutil.Assert.assertThrows;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.DeleteTaskAllCommand;
import seedu.address.model.assignment.Assignment;

public class DeleteTaskAllCommandParserTest {

    private DeleteTaskAllCommandParser parser = new DeleteTaskAllCommandParser();

    @Test
    public void parse_null_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> parser.parse(null));
    }

    @Test
    public void parse_invalidInput_throwsParseException() {
        // no group
        assertParseFailure(parser, " " + PREFIX_TASK + "Task" + " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskAllCommand.MESSAGE_USAGE));

        // no task
        assertParseFailure(parser, " " + PREFIX_GROUP + "Group" + " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, DeleteTaskAllCommand.MESSAGE_USAGE));

        // invalid task
        assertParseFailure(parser, " " + PREFIX_GROUP + "Group" + " " + PREFIX_TASK + " ",
                String.format(MESSAGE_INVALID_COMMAND_FORMAT, Assignment.MESSAGE_CONSTRAINTS));
    }

    @Test
    public void parse_validInput_commandParseSuccess() {
        assertParseSuccess(parser, " " + PREFIX_GROUP + "Group" + " " + PREFIX_TASK + "Task",
                new DeleteTaskAllCommand("Group", new Assignment("Task")));
    }
}
