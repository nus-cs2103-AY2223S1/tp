package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddTaskCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteItemCommand;
import seedu.address.logic.commands.DeleteTaskCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.EditStockCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListAllCommand;
import seedu.address.logic.commands.ListInventoryCommand;
import seedu.address.logic.commands.ListSupplierCommand;
import seedu.address.logic.commands.ListTaskCommand;
import seedu.address.logic.commands.MarkTaskCommand;
import seedu.address.logic.commands.UnMarkTaskCommand;
import seedu.address.logic.commands.UpdateTaskCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

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
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
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
                FindCommand.COMMAND_WORD + " n/" + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new NameContainsKeywordsPredicate(keywords)), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_listSuppliers() throws Exception {
        assertTrue(parser.parseCommand(ListSupplierCommand.COMMAND_WORD) instanceof ListSupplierCommand);
        assertTrue(parser.parseCommand(ListSupplierCommand.COMMAND_WORD + " 3") instanceof ListSupplierCommand);
    }

    @Test
    public void parseCommand_mark() throws Exception {
        assertTrue(parser.parseCommand(MarkTaskCommand.COMMAND_WORD + " 1") instanceof MarkTaskCommand);
    }

    @Test
    public void parseCommand_unmark() throws Exception {
        assertTrue(parser.parseCommand(UnMarkTaskCommand.COMMAND_WORD + " 1") instanceof UnMarkTaskCommand);
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
    public void parseCommand_addTask() throws Exception {
        assertTrue(parser.parseCommand(AddTaskCommand.COMMAND_WORD + " d/"
                + "Buy fish dl/2029-12-12 t/food") instanceof AddTaskCommand);
    }

    @Test
    public void parseCommand_deleteTask() throws Exception {
        assertTrue(parser.parseCommand(DeleteTaskCommand.COMMAND_WORD + " 1") instanceof DeleteTaskCommand);
    }

    @Test
    public void parseCommand_updateTask() throws Exception {
        assertTrue(parser.parseCommand(UpdateTaskCommand.COMMAND_WORD + " 1 d/Purchase oil dl/2029-10-10"
                + " t/Important t/Urgent") instanceof UpdateTaskCommand);
    }

    @Test
    public void parseCommand_editStock() throws Exception {
        assertTrue(parser.parseCommand(EditStockCommand.COMMAND_WORD + " 1 c/50")
                instanceof EditStockCommand);
    }

    @Test
    public void parseCommand_deleteItem() throws Exception {
        assertTrue(parser.parseCommand(DeleteItemCommand.COMMAND_WORD + " 1") instanceof DeleteItemCommand);
    }

    @Test
    public void parseCommand_listAll() throws ParseException {
        assertTrue(parser.parseCommand(ListAllCommand.COMMAND_WORD) instanceof ListAllCommand);
    }

    @Test
    public void parseCommand_listTasks() throws ParseException {
        assertTrue(parser.parseCommand(ListTaskCommand.COMMAND_WORD) instanceof ListTaskCommand);
    }

    @Test
    public void parseCommand_listInventory() throws ParseException {
        assertTrue(parser.parseCommand(ListInventoryCommand.COMMAND_WORD) instanceof ListInventoryCommand);
    }
}
