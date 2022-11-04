package seedu.boba.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.boba.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.boba.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.boba.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.boba.testutil.Assert.assertThrows;
import static seedu.boba.testutil.TypicalEmails.EMAIL_FIRST_PERSON;
import static seedu.boba.testutil.TypicalPhones.PHONE_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.boba.logic.commands.AddCommand;
import seedu.boba.logic.commands.ClearCommand;
import seedu.boba.logic.commands.DecreaseCommand;
import seedu.boba.logic.commands.DeleteCommand;
import seedu.boba.logic.commands.EditCommand;
import seedu.boba.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.boba.logic.commands.ExitCommand;
import seedu.boba.logic.commands.FindCommand;
import seedu.boba.logic.commands.HelpCommand;
import seedu.boba.logic.commands.IncreaseCommand;
import seedu.boba.logic.commands.ListCommand;
import seedu.boba.logic.commands.RedoCommand;
import seedu.boba.logic.commands.UndoCommand;
import seedu.boba.logic.parser.exceptions.ParseException;
import seedu.boba.model.customer.Customer;
import seedu.boba.model.customer.Email;
import seedu.boba.model.customer.MultiSearchPredicate;
import seedu.boba.model.customer.Phone;
import seedu.boba.testutil.CustomerBuilder;
import seedu.boba.testutil.CustomerUtil;
import seedu.boba.testutil.EditCustomerDescriptorBuilder;

public class BobaBotParserTest {

    private final BobaBotParser parser = new BobaBotParser();

    @Test
    public void parseCommand_add() throws Exception {
        Customer customer = new CustomerBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(CustomerUtil.getAddCommand(customer));
        assertEquals(new AddCommand(customer), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand.DeletePersonDescriptor deletePersonDescriptor = new DeleteCommand.DeletePersonDescriptor();
        deletePersonDescriptor.setPhone(new Phone("12345678"));
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " p/12345678");

        DeleteCommand.DeletePersonDescriptor deletePersonDescriptor2 = new DeleteCommand.DeletePersonDescriptor();
        deletePersonDescriptor2.setEmail(new Email("testing@test.com"));
        DeleteCommand command2 = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " e/testing@test.com");

        assertEquals(new DeleteCommand(deletePersonDescriptor), command);
        assertEquals(new DeleteCommand(deletePersonDescriptor2), command2);
    }


    @Test
    public void parseCommand_phone_edit() throws Exception {
        Customer customer = new CustomerBuilder().build();
        EditPersonDescriptor descriptor = new EditCustomerDescriptorBuilder(customer).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + PREFIX_PHONE + PHONE_FIRST_PERSON + " " + CustomerUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(PHONE_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_pemail_edit() throws Exception {
        Customer customer = new CustomerBuilder().build();
        EditPersonDescriptor descriptor = new EditCustomerDescriptorBuilder(customer).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + PREFIX_EMAIL + EMAIL_FIRST_PERSON + " " + CustomerUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(EMAIL_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_phone_increase() throws Exception {
        IncreaseCommand command = (IncreaseCommand) parser.parseCommand(IncreaseCommand.COMMAND_WORD
                + " 10 " + PREFIX_PHONE + PHONE_FIRST_PERSON);
        assertEquals(new IncreaseCommand(PHONE_FIRST_PERSON, "10"), command);
    }

    @Test
    public void parseCommand_phone_decrease() throws Exception {
        DecreaseCommand command = (DecreaseCommand) parser.parseCommand(DecreaseCommand.COMMAND_WORD
                + " 10 " + PREFIX_PHONE + PHONE_FIRST_PERSON);
        assertEquals(new DecreaseCommand(PHONE_FIRST_PERSON, "10"), command);
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
        assertEquals(new FindCommand(new MultiSearchPredicate(keywords)), command);
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
    public void parseCommand_undo() throws ParseException {
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD) instanceof UndoCommand);
        assertTrue(parser.parseCommand(UndoCommand.COMMAND_WORD + "     ") instanceof UndoCommand);
    }

    @Test
    public void parseCommand_redo() throws ParseException {
        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD) instanceof RedoCommand);
        assertTrue(parser.parseCommand(RedoCommand.COMMAND_WORD + "     ") instanceof RedoCommand);
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
