package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.commons.core.Messages.MESSAGE_INVALID_GET_PREFIX;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.GetCommand;
import seedu.address.logic.commands.getcommands.GetAppointmentByDateCommand;
import seedu.address.logic.commands.getcommands.GetFloorNumberCommand;
import seedu.address.logic.commands.getcommands.GetHospitalWingCommand;
import seedu.address.logic.commands.getcommands.GetInpatientCommand;
import seedu.address.logic.commands.getcommands.GetMedicationCommand;
import seedu.address.logic.commands.getcommands.GetNameCommand;
import seedu.address.logic.commands.getcommands.GetNextOfKinCommand;
import seedu.address.logic.commands.getcommands.GetOutpatientCommand;
import seedu.address.logic.commands.getcommands.GetPastAppointmentCommand;
import seedu.address.logic.commands.getcommands.GetWardNumberCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.AppointmentByDatePredicate;
import seedu.address.model.person.FloorNumberPredicate;
import seedu.address.model.person.HospitalWingContainsKeywordsPredicate;
import seedu.address.model.person.MedicationContainsKeywordsPredicate;
import seedu.address.model.person.NameContainsKeywordsPredicate;
import seedu.address.model.person.NextOfKinPredicate;
import seedu.address.model.person.WardNumberPredicate;

public class GetCommandParserTest {

    private final GetCommandParser parser = new GetCommandParser();

    @Test
    public void parseCommand_getOutpatient() throws Exception {
        assertTrue(parser.parse(GetOutpatientCommand.OUTPATIENT_PREFIX) instanceof GetOutpatientCommand);
        assertTrue(parser.parse(GetOutpatientCommand.OUTPATIENT_PREFIX + " 1") instanceof GetOutpatientCommand);
    }

    @Test
    public void parseCommand_getInpatient() throws Exception {
        assertTrue(parser.parse(GetInpatientCommand.INPATIENT_PREFIX) instanceof GetInpatientCommand);
        assertTrue(parser.parse(GetInpatientCommand.INPATIENT_PREFIX + " 1") instanceof GetInpatientCommand);
    }

    @Test
    public void parseCommand_getFloorNumber() throws Exception {
        List<Integer> floors = Arrays.asList(1, 2, 3);
        GetFloorNumberCommand command = new GetFloorNumberCommand(new FloorNumberPredicate(floors));
        assertEquals(parser.parse(GetFloorNumberCommand.FLOOR_NUMBER_PREFIX + " "
                + floors.stream().map(Object::toString).collect(Collectors.joining(" "))), command);
    }

    @Test
    public void parseCommand_getHospitalWing() throws Exception {
        List<String> wings = Arrays.asList("south");
        GetHospitalWingCommand command = new GetHospitalWingCommand(new HospitalWingContainsKeywordsPredicate(wings));
        assertEquals(parser.parse(GetHospitalWingCommand.HOSPITAL_WING_PREFIX + " "
                + wings.stream().collect(Collectors.joining(" "))), command);
    }

    @Test
    public void parseCommand_getWardNumber() throws Exception {
        List<String> wards = Arrays.asList("G690", "B420", "A001");
        GetWardNumberCommand command = new GetWardNumberCommand(new WardNumberPredicate(wards));
        assertEquals(parser.parse(GetWardNumberCommand.WARD_NUMBER_PREFIX + " "
                + wards.stream().collect(Collectors.joining(" "))), command);
    }

    @Test
    public void parseCommand_getName() throws Exception {
        List<String> names = Arrays.asList("kartik", "zhehao", "ryan", "peiyee");
        GetNameCommand command = new GetNameCommand(new NameContainsKeywordsPredicate(names));
        assertEquals(parser.parse(GetNameCommand.NAME_PREFIX + " "
                + names.stream().collect(Collectors.joining(" "))), command);
    }

    @Test
    public void parseCommand_getNextOfKin() throws Exception {
        List<String> names = Arrays.asList("foo", "bar", "baz");
        GetNextOfKinCommand command = new GetNextOfKinCommand(new NextOfKinPredicate(names));
        assertEquals(parser.parse(GetNextOfKinCommand.NEXT_OF_KIN_PREFIX + " "
                + names.stream().collect(Collectors.joining(" "))), command);
    }

    @Test
    public void parseCommand_getPastAppointment() throws Exception {
        Index idx = INDEX_FIRST_PERSON;
        GetPastAppointmentCommand command = new GetPastAppointmentCommand(idx);
        assertEquals(parser.parse(GetPastAppointmentCommand.PAST_APPOINTMENT_PREFIX + " "
                + idx.getOneBased()), command);
    }

    @Test
    public void parseCommand_getMedication() throws Exception {
        List<String> meds = Arrays.asList("panadol", "xanax");
        GetMedicationCommand command = new GetMedicationCommand(new MedicationContainsKeywordsPredicate(meds));
        assertEquals(parser.parse(GetMedicationCommand.MEDICATION_PREFIX + " "
                + meds.stream().collect(Collectors.joining(" "))), command);
    }

    @Test
    public void parseCommand_getAppointmentByDate() throws Exception {
        LocalDate date1 = LocalDate.of(2022, 4, 10);
        LocalDate date2 = LocalDate.of(2021, 11, 25);
        List<LocalDate> apptDates = Arrays.asList(date1, date2);
        GetAppointmentByDateCommand command = new GetAppointmentByDateCommand(
                new AppointmentByDatePredicate(apptDates));
        assertEquals(parser.parse(GetAppointmentByDateCommand.APPOINTMENT_BY_DATE_PREFIX + " "
                + "10-04-2022 25-11-2021"), command);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, GetCommand.MESSAGE_USAGE), ()
                -> parser.parse(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_GET_PREFIX,
                GetCommand.MESSAGE_USAGE), () -> parser.parse("unknownCommand"));
    }
}
