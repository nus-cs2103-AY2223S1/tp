package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.list.ListAllCommand;
import seedu.address.logic.commands.list.ListCommand;
import seedu.address.logic.commands.list.ListModuleCommand;
import seedu.address.logic.commands.list.ListUnmarkedCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.task.ModuleContainsKeywordsPredicate;
import seedu.address.model.task.NameOrTagContainsKeywordsPredicate;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskIsDonePredicate;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class TaskListParserTest {

    private final TaskListParser parser = new TaskListParser();

    @Test
    public void parseCommand_add() throws Exception {
        Task task = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(task));
        assertEquals(new AddCommand(task), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Task task = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(task).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));

        EditCommand expected = new EditCommand(INDEX_FIRST_PERSON, descriptor);
        assertEquals(expected, command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameOrTagContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_listAll() throws Exception {
        String str = ListCommand.COMMAND_WORD + " " + ListAllCommand.COMMAND_WORD;
        assertTrue(parser.parseCommand(str) instanceof ListCommand);
        assertTrue(parser.parseCommand(str + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_listUnmarked() throws Exception {
        List<String> isDone = List.of("false");
        String str = ListCommand.COMMAND_WORD + " " + ListUnmarkedCommand.COMMAND_WORD;
        ListCommand command = (ListCommand) parser.parseCommand(str + " " + isDone
                .stream()
                .collect(Collectors.joining(" ")));
        List<Predicate<Task>> predicates = new ArrayList<>();
        predicates.add(new TaskIsDonePredicate(isDone));
        assertEquals(new ListCommand(predicates), command);
    }

    @Test
    public void parseCommand_listModule() throws Exception {
        List<String> module = List.of("CS2100");
        String str = ListCommand.COMMAND_WORD + " " + ListModuleCommand.COMMAND_WORD;
        ListCommand command = (ListCommand) parser.parseCommand(str + " " + module
                .stream()
                .collect(Collectors.joining(" ")));
        List<Predicate<Task>> predicates = new ArrayList<>();
        predicates.add(new ModuleContainsKeywordsPredicate(module));
        assertEquals(new ListCommand(predicates), command);
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
}
