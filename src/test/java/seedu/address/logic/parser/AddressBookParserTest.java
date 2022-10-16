package seedu.address.logic.parser;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_REMARK;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddPatientCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeletePatientCommand;
import seedu.address.logic.commands.EditPersonCommand;
import seedu.address.logic.commands.EditPersonCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FilterNameCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.RemarkCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.NameContainsKeywordsPredicatePatient;
import seedu.address.model.person.Patient;
import seedu.address.model.person.Remark;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;
public class AddressBookParserTest {
    private final AddressBookParser parser = new AddressBookParser();
    @Test
    public void parseCommand_add() throws Exception {
        Patient patient = new PersonBuilder().build();
        AddPatientCommand command = (AddPatientCommand) parser.parseCommand(PersonUtil.getAddCommand(patient));
        assertEquals(new AddPatientCommand(patient), command);
    }
    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD.toString()) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }
    @Test
    public void parseCommand_delete() throws Exception {
        DeletePatientCommand command = (DeletePatientCommand) parser.parseCommand(
                DeletePatientCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeletePatientCommand(INDEX_FIRST_PERSON), command);
    }
    @Test
    public void parseCommand_edit() throws Exception {
        Patient patient = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(patient).build();
        EditPersonCommand command = (EditPersonCommand) parser.parseCommand(EditPersonCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditPersonCommand(INDEX_FIRST_PERSON, descriptor), command);
    }
    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD.toString()) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }
    @Test
    public void parseCommand_find() throws Exception {
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FilterNameCommand command = (FilterNameCommand) parser.parseCommand(
                FilterNameCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FilterNameCommand(new NameContainsKeywordsPredicatePatient(keywords)), command);
    }
    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD.toString()) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }
    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD.toString()) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_remark() throws Exception {
        final Remark remark = new Remark("Some remark.");
        RemarkCommand command = (RemarkCommand) parser.parseCommand(RemarkCommand.COMMAND_WORD + " "
            + INDEX_FIRST_PERSON.getOneBased() + " " + PREFIX_REMARK + remark.value);
        assertEquals(new RemarkCommand(INDEX_FIRST_PERSON, remark), command);
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
