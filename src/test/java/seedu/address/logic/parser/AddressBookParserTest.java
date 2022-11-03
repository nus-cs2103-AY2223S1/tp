package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DATE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_RECORD;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.FIRST_INDEX;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddAppointmentCommand;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AddRecordCommand;
import seedu.address.logic.commands.ClearAppointmentCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.ClearRecordCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.DeleteRecordCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.EditRecordCommand;
import seedu.address.logic.commands.EditRecordCommand.EditRecordDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.FindRecordCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.ListRecordCommand;
import seedu.address.logic.commands.ShowAllCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.Person;
import seedu.address.model.record.Record;
import seedu.address.model.record.RecordContainsKeywordsPredicate;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.EditRecordDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;
import seedu.address.testutil.RecordBuilder;
import seedu.address.testutil.RecordUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }

    @Test
    public void parseCommand_radd() throws Exception {
        Record record = new RecordBuilder().build();
        AddRecordCommand command = (AddRecordCommand) parser.parseCommand(RecordUtil.getAddRecordCommand(record));
        assertEquals(new AddRecordCommand(record), command);
    }

    @Test
    public void parseCommand_appt() throws Exception {
        String apptDateTime = "10-10-2000 1430";
        AddAppointmentCommand command = (AddAppointmentCommand) parser.parseCommand(
                AddAppointmentCommand.COMMAND_WORD
                        + " " + FIRST_INDEX.getOneBased()
                        + " " + PREFIX_DATE + apptDateTime);
        assertEquals(new AddAppointmentCommand(FIRST_INDEX, apptDateTime), command);
    }

    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_rclear() throws Exception {
        assertTrue(parser.parseCommand(ClearRecordCommand.COMMAND_WORD) instanceof ClearRecordCommand);
        assertTrue(parser.parseCommand(ClearRecordCommand.COMMAND_WORD + " 3") instanceof ClearRecordCommand);
    }

    @Test
    public void parseCommand_apptcl() throws Exception {
        ClearAppointmentCommand command = (ClearAppointmentCommand) parser.parseCommand(
                ClearAppointmentCommand.COMMAND_WORD + " " + FIRST_INDEX.getOneBased());
        assertEquals(new ClearAppointmentCommand(FIRST_INDEX), command);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + FIRST_INDEX.getOneBased());
        assertEquals(new DeleteCommand(FIRST_INDEX), command);
    }

    @Test
    public void parseCommand_rdelete() throws Exception {
        DeleteRecordCommand command = (DeleteRecordCommand) parser.parseCommand(
                DeleteRecordCommand.COMMAND_WORD + " " + FIRST_INDEX.getOneBased());
        assertEquals(new DeleteRecordCommand(FIRST_INDEX), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + FIRST_INDEX.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(FIRST_INDEX, descriptor), command);
    }

    @Test
    public void parseCommand_redit() throws Exception {
        Record record = new RecordBuilder().build();
        EditRecordDescriptor descriptor = new EditRecordDescriptorBuilder(record).build();
        EditRecordCommand command = (EditRecordCommand) parser.parseCommand(EditRecordCommand.COMMAND_WORD + " "
                + FIRST_INDEX.getOneBased() + " " + RecordUtil.getEditRecordDescriptorDetails(descriptor));
        assertEquals(new EditRecordCommand(FIRST_INDEX, descriptor), command);
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
    public void parseCommand_rfind() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindRecordCommand command = (FindRecordCommand) parser.parseCommand(
                FindRecordCommand.COMMAND_WORD
                        + " " + PREFIX_RECORD
                        + keywords.stream().collect(Collectors.joining(" ")));

        assertEquals(new FindRecordCommand(
                new RecordContainsKeywordsPredicate(keywords, Arrays.asList(), "")), command);
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
    public void parseCommand_rlist() throws Exception {
        ListRecordCommand command = (ListRecordCommand) parser.parseCommand(
                ListRecordCommand.COMMAND_WORD + " " + FIRST_INDEX.getOneBased());
        assertEquals(new ListRecordCommand(FIRST_INDEX), command);
    }

    @Test
    public void parseCommand_showaLl() throws Exception {
        assertTrue(parser.parseCommand(ShowAllCommand.COMMAND_WORD) instanceof ShowAllCommand);
        assertTrue(parser.parseCommand(ShowAllCommand.COMMAND_WORD) instanceof ShowAllCommand);
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
