package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_ITEM;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddPersonCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteBuyerCommand;
import seedu.address.logic.commands.DeletePropertyCommand;
import seedu.address.logic.commands.EditBuyerCommand;
import seedu.address.logic.commands.EditBuyerCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListBuyersCommand;
import seedu.address.logic.commands.ListPropertiesCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class PersonBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

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

    @Test
    public void parseCommand_deletebuyer() throws Exception {
        DeleteBuyerCommand command = (DeleteBuyerCommand) parser.parseCommand(
                DeleteBuyerCommand.COMMAND_WORD + " " + INDEX_FIRST_ITEM.getOneBased());
        assertEquals(new DeleteBuyerCommand(INDEX_FIRST_ITEM), command);
    }

    @Test
    public void parseCommand_deleteprop() throws Exception {
        DeletePropertyCommand command = (DeletePropertyCommand) parser.parseCommand(
                DeletePropertyCommand.COMMAND_WORD + " " + INDEX_FIRST_ITEM.getOneBased());
        assertEquals(new DeletePropertyCommand(INDEX_FIRST_ITEM), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditBuyerCommand command = (EditBuyerCommand) parser.parseCommand(EditBuyerCommand.COMMAND_WORD + " "
                + INDEX_FIRST_ITEM.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditBuyerCommand(INDEX_FIRST_ITEM, descriptor), command);
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
    public void parseCommand_listbuyers() throws Exception {
        assertTrue(parser.parseCommand(ListBuyersCommand.COMMAND_WORD) instanceof ListBuyersCommand);
        assertTrue(parser.parseCommand(ListBuyersCommand.COMMAND_WORD + " 3") instanceof ListBuyersCommand);
    }

    @Test
    public void parseCommand_listprops() throws Exception {
        assertTrue(parser.parseCommand(ListPropertiesCommand.COMMAND_WORD) instanceof ListPropertiesCommand);
        assertTrue(parser.parseCommand(ListPropertiesCommand.COMMAND_WORD + " 3") instanceof ListPropertiesCommand);
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
