package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.persons.AddPersonCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.group.Group;
import seedu.address.model.item.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.task.Task;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

/** Test to test the singleton address book parser */
public class AddressBookParserTest {

    private final AddressBookParser parser = AddressBookParser.get();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddPersonCommand command = (AddPersonCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddPersonCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void parsePersonCommand_delete() throws Exception {
        DeleteCommand<Person> command = (DeleteCommand<Person>) parser.parseCommand(
            CmdBuilder.P_DELETE + " " + INDEX_FIRST.getOneBased());
        assertEquals(CmdBuilder.makeDelPerson(INDEX_FIRST), command);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void parseTaskCommand_delete() throws Exception {
        DeleteCommand<Task> command = (DeleteCommand<Task>) parser.parseCommand(
            CmdBuilder.T_DELETE + " "
                + INDEX_FIRST.getOneBased());
        assertEquals(CmdBuilder.makeDelTask(INDEX_FIRST), command);
    }

    @SuppressWarnings("unchecked")
    @Test
    public void parseTeamCommand_delete() throws Exception {
        DeleteCommand<Group> command = (DeleteCommand<Group>) parser.parseCommand(
            CmdBuilder.G_DELETE + " "
                + INDEX_FIRST.getOneBased());
        assertEquals(CmdBuilder.makeDelGrp(INDEX_FIRST), command);
    }


    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand<?> command = (FindCommand<?>) parser.parseCommand("person find "
            + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(CmdBuilder.makeFindPerson(new NameContainsKeywordsPredicate<Person>(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class,
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), () -> {
                parser.parseCommand("");
            });
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }


}
