package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCustomerCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommissionCommand;
import seedu.address.logic.commands.DeleteCustomerCommand;
import seedu.address.logic.commands.EditCustomerCommand;
import seedu.address.logic.commands.EditCustomerCommand.EditCustomerDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.OpenCustomerCommand;
import seedu.address.logic.commands.SortCustomerCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.commission.CompositeCustomerPredicate;
import seedu.address.model.customer.Customer;
import seedu.address.model.tag.Tag;
import seedu.address.testutil.CustomerBuilder;
import seedu.address.testutil.CustomerUtil;
import seedu.address.testutil.EditCustomerDescriptorBuilder;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Customer customer = new CustomerBuilder().build();
        AddCustomerCommand command = (AddCustomerCommand) parser.parseCommand(
            CustomerUtil.getAddCustomerCommand(customer));
        assertEquals(new AddCustomerCommand(customer), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_deleteCustomer() throws Exception {
        DeleteCustomerCommand command = (DeleteCustomerCommand) parser.parseCommand(
                DeleteCustomerCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteCustomerCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_openCustomer() throws Exception {
        OpenCustomerCommand command = (OpenCustomerCommand) parser.parseCommand(
                OpenCustomerCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new OpenCustomerCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Customer customer = new CustomerBuilder().build();
        EditCustomerDescriptor descriptor = new EditCustomerDescriptorBuilder(customer).build();
        EditCustomerCommand command = (EditCustomerCommand) parser.parseCommand(
                EditCustomerCommand.COMMAND_WORD + " "
                        + INDEX_FIRST.getOneBased() + " " + CustomerUtil.getEditCustomerDescriptorDetails(descriptor));
        assertEquals(new EditCustomerCommand(INDEX_FIRST, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    // TODO: Update test case for FindCommand
    @Test
    public void parseCommand_find() throws Exception {
        Set<String> keywords = new HashSet<>(Arrays.asList("foo", "bar", "baz"));
        Set<Tag> mustTags = new HashSet<>(Arrays.asList(new Tag("tag1"),
                new Tag("tag2"), new Tag("tag3")));
        Set<Tag> anyTags = new HashSet<>(Arrays.asList(new Tag("tag4"),
                new Tag("tag5"), new Tag("tag6")));
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " k/" + String.join(" k/", keywords) + " -all t/tag1 "
                + "t/tag2 t/tag3 -any t/tag4 t/tag5 t/tag6");
        assertEquals(new FindCommand(new CompositeCustomerPredicate(keywords, mustTags, anyTags)), command);
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
    public void parseCommand_deleteCommission() throws Exception {
        DeleteCommissionCommand command = (DeleteCommissionCommand) parser.parseCommand(
            DeleteCommissionCommand.COMMAND_WORD + " " + INDEX_FIRST.getOneBased());
        assertEquals(new DeleteCommissionCommand(INDEX_FIRST), command);
    }

    @Test
    public void parseCommand_sortCustomer() throws Exception {
        assertTrue(parser.parseCommand(SortCustomerCommand.COMMAND_WORD + " n/+") instanceof SortCustomerCommand);
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
