package paymelah.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static paymelah.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static paymelah.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static paymelah.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static paymelah.testutil.Assert.assertThrows;
import static paymelah.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import paymelah.logic.commands.AddCommand;
import paymelah.logic.commands.ClearCommand;
import paymelah.logic.commands.DeleteCommand;
import paymelah.logic.commands.EditCommand;
import paymelah.logic.commands.ExitCommand;
import paymelah.logic.commands.FindCommand;
import paymelah.logic.commands.HelpCommand;
import paymelah.logic.commands.ListCommand;
import paymelah.logic.parser.ParserUtil.PersonDescriptor;
import paymelah.logic.parser.exceptions.ParseException;
import paymelah.model.person.Person;
import paymelah.model.person.PersonMatchesDescriptorPredicate;
import paymelah.testutil.DebtsDescriptorBuilder;
import paymelah.testutil.PersonBuilder;
import paymelah.testutil.PersonDescriptorBuilder;
import paymelah.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
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
        Person person = new PersonBuilder().build();
        PersonDescriptor descriptor = new PersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
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
                FindCommand.COMMAND_WORD + " " + PREFIX_DESCRIPTION
                        + keywords.stream().collect(Collectors.joining(" " + PREFIX_DESCRIPTION)));
        assertEquals(new FindCommand(new PersonMatchesDescriptorPredicate(
                new DebtsDescriptorBuilder().withDescriptions(keywords.toArray(new String[0])).build())), command);
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
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }
}
