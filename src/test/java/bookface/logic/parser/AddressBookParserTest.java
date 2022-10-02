package bookface.logic.parser;

import static bookface.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static bookface.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static bookface.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import bookface.logic.commands.AddUserCommand;
import bookface.logic.commands.ClearCommand;
import bookface.logic.commands.DeleteUserCommand;
import bookface.logic.commands.EditCommand;
import bookface.logic.commands.EditCommand.EditPersonDescriptor;
import bookface.logic.commands.ExitCommand;
import bookface.logic.commands.FindCommand;
import bookface.logic.commands.HelpCommand;
import bookface.logic.commands.ListUsersCommand;
import bookface.logic.parser.exceptions.ParseException;
import bookface.model.person.NameContainsKeywordsPredicate;
import bookface.model.person.Person;
import bookface.testutil.EditPersonDescriptorBuilder;
import bookface.testutil.PersonBuilder;
import bookface.testutil.PersonUtil;
import bookface.testutil.TypicalIndexes;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddUserCommand command = (AddUserCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddUserCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteUserCommand command = (DeleteUserCommand) parser.parseCommand(
                DeleteUserCommand.COMMAND_WORD + DeleteUserCommand.COMMAND_WORD_USER + " "
                        + TypicalIndexes.INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteUserCommand(TypicalIndexes.INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + TypicalIndexes.INDEX_FIRST_PERSON.getOneBased() + " "
                + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(TypicalIndexes.INDEX_FIRST_PERSON, descriptor), command);
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
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListUsersCommand.COMMAND_WORD
                + ListUsersCommand.COMMAND_WORD_USER) instanceof ListUsersCommand);
        assertTrue(parser.parseCommand(ListUsersCommand.COMMAND_WORD
                + ListUsersCommand.COMMAND_WORD_USER + " 3") instanceof ListUsersCommand);
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
