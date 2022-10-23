package modtrekt.logic.parser;

import static modtrekt.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static modtrekt.logic.commands.utils.AddCommandMessages.MESSAGE_ADD_COMMAND_PREFIXES;
import static modtrekt.testutil.Assert.assertThrows;
import static modtrekt.testutil.TypicalIndexes.INDEX_FIRST_TASK;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import modtrekt.commons.core.Messages;
import modtrekt.logic.commands.AddCommand;
import modtrekt.logic.commands.AddTaskCommand;
import modtrekt.logic.commands.ExitCommand;
import modtrekt.logic.commands.HelpCommand;
import modtrekt.logic.commands.RemoveTaskCommand;
import modtrekt.logic.parser.exceptions.ParseException;
import modtrekt.model.task.Task;
import modtrekt.testutil.AddTaskCommandBuilder;
import modtrekt.testutil.TaskBuilder;
import modtrekt.testutil.TaskUtil;

public class ModtrektParserTest {

    private final ModtrektParser parser = new ModtrektParser();

    @Test
    public void parseCommand_add() throws Exception {
        Task t = new TaskBuilder().build();
        AddTaskCommand command = (AddTaskCommand) parser.parseCommand(TaskUtil.getAddCommand(t));
        assertEquals(new AddTaskCommand(t.getDescription(), t.getModule(), null), command);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        RemoveTaskCommand command = (RemoveTaskCommand) parser.parseCommand(
                RemoveTaskCommand.COMMAND_WORD + " -t " + INDEX_FIRST_TASK.getOneBased());
        assertEquals(new RemoveTaskCommand(INDEX_FIRST_TASK), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, Messages.MESSAGE_MISSING_COMMAND, ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, () -> parser.parseCommand("unknownCommand"));
    }

    @Test
    public void parseCommand_addCommandNoFlags_throwsParseException() {
        assertThrows(ParseException.class, String.format(
                MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_ADD_COMMAND_PREFIXES), ()
                -> parser.parseCommand(AddCommand.COMMAND_WORD));
    }

    @Test
    public void parseCommand_addCommandMFlag_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, AddCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand(String.format("%s %s", AddCommand.COMMAND_WORD, CliSyntax.PREFIX_MODULE)));
    }

    @Test
    public void parseCommand_addTaskCommandNoDeadline_throws() {
        assertThrows(Exception.class, () -> parser.parseCommand("add -t -c CS2102"));
        assertThrows(Exception.class, () -> parser.parseCommand("add -t -d 2022-09-19 -c CS2102"));
    }

    @Test
    public void parseCommand_addTaskCommandNoModCode_returnsTrue() throws Exception {
        AddTaskCommand cmd = AddTaskCommandBuilder.build("desc", null, null);
        assertEquals(parser.parseCommand("add -t desc"), cmd);
    }

    @Test
    public void parseCommand_addTaskCommandInvalidDate_throws() {
        assertThrows(ParseException.class, "Invalid date, date must be in YYYY-MM-DD format", ()
                -> parser.parseCommand("add -t desc -d 4"));
    }


}
