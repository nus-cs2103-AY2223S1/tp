package bookface.logic.parser;

import static bookface.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static bookface.testutil.Assert.assertThrows;
import static bookface.testutil.TestUtil.preparePredicateToCheckPersonForPartialWordIgnoreCase;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import bookface.commons.core.Messages;
import bookface.logic.commands.ClearCommand;
import bookface.logic.commands.Command;
import bookface.logic.commands.ExitCommand;
import bookface.logic.commands.HelpCommand;
import bookface.logic.commands.add.AddCommand;
import bookface.logic.commands.add.AddUserCommand;
import bookface.logic.commands.delete.DeleteCommand;
import bookface.logic.commands.delete.DeleteUserCommand;
import bookface.logic.commands.edit.EditCommand;
import bookface.logic.commands.edit.EditUserCommand;
import bookface.logic.commands.edit.EditUserCommand.EditPersonDescriptor;
import bookface.logic.commands.find.FindCommand;
import bookface.logic.commands.find.FindUserCommand;
import bookface.logic.commands.list.ListBooksCommand;
import bookface.logic.commands.list.ListCommand;
import bookface.logic.commands.list.ListUsersCommand;
import bookface.logic.parser.exceptions.ParseException;
import bookface.logic.parser.primary.PrimaryParser;
import bookface.model.person.Person;
import bookface.testutil.EditPersonDescriptorBuilder;
import bookface.testutil.PersonBuilder;
import bookface.testutil.PersonUtil;
import bookface.testutil.TypicalIndexes;

public class PrimaryParserTest {

    private final PrimaryParser parser = new PrimaryParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parse(PersonUtil.getAddCommand(person));
        assertEquals(new AddUserCommand(person), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parse(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteUserCommand command = (DeleteUserCommand) parser.parse(
                DeleteCommand.COMMAND_WORD + " " + DeleteUserCommand.COMMAND_WORD + " "
                        + TypicalIndexes.INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteUserCommand(TypicalIndexes.INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditUserCommand command =
                (EditUserCommand) parser.parse(EditCommand.COMMAND_WORD + " " + EditUserCommand.COMMAND_WORD + " "
                + TypicalIndexes.INDEX_FIRST_PERSON.getOneBased() + " "
                + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditUserCommand(TypicalIndexes.INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parse(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parse(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindUserCommand) parser.parse(
                FindCommand.COMMAND_WORD + " " + FindUserCommand.COMMAND_WORD + " "
                        + String.join(" ", keywords));
        assertEquals(new FindUserCommand(preparePredicateToCheckPersonForPartialWordIgnoreCase(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parse(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parse(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parse(ListCommand.COMMAND_WORD + " "
                + ListUsersCommand.COMMAND_WORD) instanceof ListUsersCommand);
        assertTrue(parser.parse(ListCommand.COMMAND_WORD + " "
                + ListUsersCommand.COMMAND_WORD + " 3") instanceof ListUsersCommand);
        assertTrue(parser.parse(ListCommand.COMMAND_WORD + " "
                + ListBooksCommand.COMMAND_WORD) instanceof ListBooksCommand);
        assertTrue(parser.parse(ListCommand.COMMAND_WORD + " "
                + ListBooksCommand.COMMAND_WORD + " 3") instanceof ListBooksCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, Command.MESSAGE_USAGE), ()
            -> parser.parse(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, String.format(Messages.MESSAGE_UNKNOWN_COMMAND,
                Command.MESSAGE_USAGE), () -> parser.parse("unknownCommand"));
    }
}
