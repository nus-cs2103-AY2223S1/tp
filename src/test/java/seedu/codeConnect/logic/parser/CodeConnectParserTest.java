package seedu.codeConnect.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.codeConnect.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.codeConnect.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.codeConnect.testutil.Assert.assertThrows;
import static seedu.codeConnect.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.codeConnect.logic.commands.AddContactCommand;
import seedu.codeConnect.logic.commands.ClearCommand;
import seedu.codeConnect.logic.commands.DeleteContactCommand;
import seedu.codeConnect.logic.commands.EditContactCommand;
import seedu.codeConnect.logic.commands.EditContactCommand.EditPersonDescriptor;
import seedu.codeConnect.logic.commands.EditTaskCommand;
import seedu.codeConnect.logic.commands.EditTaskCommand.EditTaskDescriptor;
import seedu.codeConnect.logic.commands.ExitCommand;
import seedu.codeConnect.logic.commands.FindContactCommand;
import seedu.codeConnect.logic.commands.FindTaskCommand;
import seedu.codeConnect.logic.commands.HelpCommand;
import seedu.codeConnect.logic.commands.ListContactCommand;
import seedu.codeConnect.logic.parser.exceptions.ParseException;
import seedu.codeConnect.model.person.CanHelpWithTaskPredicate;
import seedu.codeConnect.model.person.ModuleTakenPredicate;
import seedu.codeConnect.model.person.NameContainsKeywordsPredicate;
import seedu.codeConnect.model.person.Person;
import seedu.codeConnect.model.task.Task;
import seedu.codeConnect.model.task.TaskContainsKeywordsPredicate;
import seedu.codeConnect.model.task.TaskContainsModulesPredicate;
import seedu.codeConnect.testutil.EditPersonDescriptorBuilder;
import seedu.codeConnect.testutil.EditTaskDescriptorBuilder;
import seedu.codeConnect.testutil.PersonBuilder;
import seedu.codeConnect.testutil.PersonUtil;
import seedu.codeConnect.testutil.TaskBuilder;
import seedu.codeConnect.testutil.TaskUtil;

public class CodeConnectParserTest {

    private final CodeConnectParser parser = new CodeConnectParser();

    @Test
    public void parseCommand_addc() throws Exception {
        Person person = new PersonBuilder().build();
        AddContactCommand command = (AddContactCommand) parser.parseCommand(PersonUtil.getAddContactCommand(person));
        assertEquals(new AddContactCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteContactCommand command = (DeleteContactCommand) parser.parseCommand(
                DeleteContactCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteContactCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_editc() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditContactCommand command = (EditContactCommand) parser.parseCommand(EditContactCommand.COMMAND_WORD + " "
                + INDEX_FIRST.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditContactCommand(INDEX_FIRST, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_findContactByName() throws Exception {
        List<String> keywords = Arrays.asList("Jacob", "Alex", "Alice");
        FindContactCommand command = (FindContactCommand) parser.parseCommand(
                FindContactCommand.COMMAND_WORD + " n/ "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindContactCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findContactByModule() throws Exception {
        List<String> moduleNames = Arrays.asList("CS1101S", "MA1521", "IS1103");
        FindContactCommand command = (FindContactCommand) parser.parseCommand(
                FindContactCommand.COMMAND_WORD + " m/ "
                        + moduleNames.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindContactCommand(new ModuleTakenPredicate(moduleNames)), command);
    }

    @Test
    public void parseCommand_findContactByTask() throws Exception {
        FindContactCommand command = (FindContactCommand) parser.parseCommand(
                FindContactCommand.COMMAND_WORD + " ts/ 3");
        assertEquals(new FindContactCommand(new CanHelpWithTaskPredicate(3)), command);
    }

    @Test
    public void parseCommand_findTaskByName() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindTaskCommand command = (FindTaskCommand) parser.parseCommand(
                FindTaskCommand.COMMAND_WORD + " n/ "
                        + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindTaskCommand(new TaskContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_findTaskByModule() throws Exception {
        ArrayList<String> keywords = new ArrayList<>();
        keywords.add("foo1231");
        keywords.add("bar1101s");
        keywords.add("baz2100");
        FindTaskCommand command = (FindTaskCommand) parser.parseCommand(
                FindTaskCommand.COMMAND_WORD + " m/ " + "foo1231 bar1101s baz2100");
        assertEquals(new FindTaskCommand(new TaskContainsModulesPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_listc() throws Exception {
        assertTrue(parser.parseCommand(ListContactCommand.COMMAND_WORD) instanceof ListContactCommand);
        assertTrue(parser.parseCommand(ListContactCommand.COMMAND_WORD + " 3") instanceof ListContactCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Task task = new TaskBuilder().build();
        EditTaskDescriptor descriptor = new EditTaskDescriptorBuilder(task).build();
        EditTaskCommand command = (EditTaskCommand) parser.parseCommand(EditTaskCommand.COMMAND_WORD + " "
                + INDEX_FIRST.getOneBased() + " " + TaskUtil.getEditTaskDescriptorDetails(descriptor));
        assertEquals(new EditTaskCommand(INDEX_FIRST, descriptor), command);
    }
}
