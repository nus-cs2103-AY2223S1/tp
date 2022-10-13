package taskbook.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static taskbook.testutil.Assert.assertThrows;
import static taskbook.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import taskbook.commons.core.Messages;
import taskbook.logic.commands.Command;
import taskbook.logic.commands.HelpCommand;
import taskbook.logic.commands.categoryless.ExitCommand;
import taskbook.logic.commands.contacts.ContactAddCommand;
import taskbook.logic.commands.contacts.ContactDeleteCommand;
import taskbook.logic.commands.contacts.ContactListCommand;
import taskbook.logic.commands.tasks.TaskDeleteCommand;
import taskbook.logic.commands.tasks.TaskListCommand;
import taskbook.logic.commands.tasks.TaskTodoCommand;
import taskbook.logic.parser.contacts.ContactCategoryParser;
import taskbook.logic.parser.exceptions.ParseException;
import taskbook.logic.parser.tasks.TaskCategoryParser;
import taskbook.model.person.Person;
import taskbook.testutil.PersonBuilder;
import taskbook.testutil.PersonUtil;

public class TaskBookParserTest {

    private final TaskBookParser parser = new TaskBookParser();

    @Test
    public void parseCommand_contact_add() throws Exception {
        Person person = new PersonBuilder().build();
        ContactAddCommand command = (ContactAddCommand) parseContactCommand(PersonUtil.getAddCommand(person));
        assertEquals(new ContactAddCommand(person), command);
    }

    @Test
    public void parseCommand_contact_delete() throws Exception {
        ContactDeleteCommand command = (ContactDeleteCommand) parseContactCommand(PersonUtil.getDeleteCommand(
            INDEX_FIRST_PERSON.getOneBased()));
        assertEquals(new ContactDeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_contact_list() throws Exception {
        assertTrue(parseContactCommand(ContactListCommand.COMMAND_WORD) instanceof ContactListCommand);
        assertTrue(parseContactCommand(ContactListCommand.COMMAND_WORD + " 3") instanceof ContactListCommand);
    }

    @Test
    public void parseCommand_task_todo() throws Exception {
        TaskTodoCommand command = (TaskTodoCommand) parseTaskCommand("todo m/John d/Finish user guide");
        assertNotNull(command);
    }

    @Test
    public void parseCommand_task_delete() throws Exception {
        TaskDeleteCommand command = (TaskDeleteCommand) parseTaskCommand("delete i/1");
        assertNotNull(command);
    }

    @Test
    public void parseCommand_task_list() throws Exception {
        assertTrue(parseTaskCommand(TaskListCommand.COMMAND_WORD) instanceof TaskListCommand);
        assertTrue(parseTaskCommand(TaskListCommand.COMMAND_WORD + " 3") instanceof TaskListCommand);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(Messages.MESSAGE_INVALID_COMMAND_FORMAT,
                HelpCommand.MESSAGE_USAGE), ()
                -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, Messages.MESSAGE_UNKNOWN_COMMAND, ()
            -> parser.parseCommand("unknownCommand"));
    }

    private Command parseCommandWithCategory(String category, String commandWithoutCategory) throws ParseException {
        return parser.parseCommand(category + " " + commandWithoutCategory);
    }

    private Command parseContactCommand(String command) throws ParseException {
        return parseCommandWithCategory(ContactCategoryParser.CATEGORY_WORD, command);
    }

    private Command parseTaskCommand(String command) throws ParseException {
        return parseCommandWithCategory(TaskCategoryParser.CATEGORY_WORD, command);
    }
}
