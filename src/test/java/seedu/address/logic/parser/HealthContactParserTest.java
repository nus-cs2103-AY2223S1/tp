package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddPatientCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeletePatientCommand;
import seedu.address.logic.commands.EditPatientCommand;
import seedu.address.logic.commands.EditPatientCommand.EditPatientDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.SelectAppointmentCommand;
import seedu.address.logic.commands.SelectPatientCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.patient.Patient;
import seedu.address.testutil.EditPatientDescriptorBuilder;
import seedu.address.testutil.PatientBuilder;
import seedu.address.testutil.PatientUtil;

public class HealthContactParserTest {
    private final HealthContactParser parser = new HealthContactParser();
    @Test
    public void parseCommand_addPatient() throws Exception {
        Patient patient = new PatientBuilder().build();
        AddPatientCommand command = (AddPatientCommand) parser.parseCommand(PatientUtil.getAddCommand(patient));
        assertEquals(new AddPatientCommand(patient), command);
    }
    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD.toString()) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }
    @Test
    public void parseCommand_deletePatient() throws Exception {
        DeletePatientCommand command = (DeletePatientCommand) parser.parseCommand(
                DeletePatientCommand.COMMAND_WORD + " " + INDEX_FIRST_PATIENT.getOneBased());
        assertEquals(new DeletePatientCommand(INDEX_FIRST_PATIENT), command);
    }
    @Test
    public void parseCommand_editPatient() throws Exception {
        Patient patient = new PatientBuilder().build();
        EditPatientDescriptor descriptor = new EditPatientDescriptorBuilder(patient).build();
        EditPatientCommand command =
                (EditPatientCommand) parser.parseCommand(EditPatientCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PATIENT.getOneBased() + " " + PatientUtil.getEditPatientDescriptorDetails(descriptor));
        assertEquals(new EditPatientCommand(INDEX_FIRST_PATIENT, descriptor), command);
    }
    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD.toString()) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
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
    public void parseCommand_selectPatient() throws Exception {
        SelectPatientCommand command = (SelectPatientCommand) parser.parseCommand(
                SelectPatientCommand.COMMAND_WORD + " " + INDEX_FIRST_PATIENT.getOneBased());
        assertEquals(new SelectPatientCommand(INDEX_FIRST_PATIENT), command);
    }
    @Test
    public void parseCommand_selectAppointment() throws Exception {
        SelectAppointmentCommand command = (SelectAppointmentCommand) parser.parseCommand(
                SelectAppointmentCommand.COMMAND_WORD + " " + INDEX_FIRST_PATIENT.getOneBased());
        assertEquals(new SelectAppointmentCommand(INDEX_FIRST_PATIENT), command);
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
