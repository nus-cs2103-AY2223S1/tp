package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_APPOINTMENTS_LISTED_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_1;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_2;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_3;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_4;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_5;
import static seedu.address.testutil.TypicalAppointments.APPOINTMENT_6;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentsHealthContact;

import java.util.Arrays;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Predicate;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Doctor;
import seedu.address.model.appointment.MedicalTest;
import seedu.address.model.appointment.Slot;
import seedu.address.model.patient.Name;

public class FindAppointmentCommandTest {
    private Model model = new ModelManager(getTypicalAppointmentsHealthContact(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalAppointmentsHealthContact(), new UserPrefs());
    @Test
    public void equals() {
        Optional<Predicate<Name>> firstNamePredicate = Optional.of(name -> name.fullName.toLowerCase()
                .contains("MEIER".toLowerCase()));
        Optional<Predicate<MedicalTest>> firstTestPredicate = Optional.of(test -> test.toString().toLowerCase()
                .contains("TEST".toLowerCase()));
        Optional<Predicate<Slot>> firstSlotPredicate = Optional.of(slot -> slot.toString()
                .contains("2022-".toLowerCase()));
        Optional<Predicate<Doctor>> firstDoctorPredicate = Optional.of(doctor -> doctor.toString().toLowerCase()
                        .contains("lamBERT".toLowerCase()));

        Optional<Predicate<Name>> secondNamePredicate = Optional.of(name -> name.fullName.toLowerCase()
                        .contains("pauLINE".toLowerCase()));
        Optional<Predicate<MedicalTest>> secondTestPredicate = Optional.of(test -> test.toString().toLowerCase()
                .contains("funcTION".toLowerCase()));
        Optional<Predicate<Slot>> secondSlotPredicate = Optional.of(slot -> slot.toString()
                .contains(":30".toLowerCase()));
        Optional<Predicate<Doctor>> secondDoctorPredicate = Optional.of(doctor -> doctor.toString().toLowerCase()
                .contains("gibbONS".toLowerCase()));

        FindAppointmentCommand firstCommand = new FindAppointmentCommand(firstNamePredicate, firstTestPredicate,
                firstSlotPredicate, firstDoctorPredicate);
        FindAppointmentCommand secondCommand = new FindAppointmentCommand(secondNamePredicate, secondTestPredicate,
                secondSlotPredicate, secondDoctorPredicate);

        // same object -> returns true
        assertTrue(firstCommand.equals(firstCommand));


        // same values -> returns true
        FindAppointmentCommand firstCommandCopy = new FindAppointmentCommand(firstNamePredicate, firstTestPredicate,
                firstSlotPredicate, firstDoctorPredicate);
        assertTrue(firstCommand.equals(firstCommandCopy));

        // different types -> returns false
        assertFalse(firstCommand.equals(1));

        // null -> returns false
        assertFalse(firstCommand.equals(null));

        // different predicate -> returns false
        assertFalse(firstCommand.equals(secondCommand));
    }


    @Test
    public void execute_findByName_noPersonFound() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 0);
        Optional<Predicate<Name>> namePredicate = Optional.of(name -> name.fullName.toLowerCase()
                .contains("bernice".toLowerCase()));
        FindAppointmentCommand command = new FindAppointmentCommand(namePredicate, Optional.empty(), Optional.empty(),
                Optional.empty());
        expectedModel.updateFilteredAppointmentList(command.getPredicate());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_findByName_found() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 1);
        Optional<Predicate<Name>> namePredicate = Optional.of(name -> name.fullName.toLowerCase()
                .contains("pauline".toLowerCase()));
        FindAppointmentCommand command = new FindAppointmentCommand(namePredicate, Optional.empty(), Optional.empty(),
                Optional.empty());
        expectedModel.updateFilteredAppointmentList(command.getPredicate());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(APPOINTMENT_1), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_findByMedicalTest_found() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 3);
        Optional<Predicate<MedicalTest>> testPredicate = Optional.of(test -> test.toString().toLowerCase()
                .contains("TEST".toLowerCase()));
        FindAppointmentCommand command = new FindAppointmentCommand(Optional.empty(), testPredicate, Optional.empty(),
                Optional.empty());
        expectedModel.updateFilteredAppointmentList(command.getPredicate());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(APPOINTMENT_2, APPOINTMENT_3, APPOINTMENT_5), model.getFilteredAppointmentList());
    }

    @Test
    public void execute_findBySlot_found() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 5);
        Optional<Predicate<Slot>> slotPredicate = Optional.of(slot -> slot.toString()
                .contains("2022-".toLowerCase()));
        FindAppointmentCommand command = new FindAppointmentCommand(Optional.empty(), Optional.empty(), slotPredicate,
                Optional.empty());
        expectedModel.updateFilteredAppointmentList(command.getPredicate());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(APPOINTMENT_1, APPOINTMENT_3, APPOINTMENT_4, APPOINTMENT_5, APPOINTMENT_6),
                model.getFilteredAppointmentList());
    }

    @Test
    public void execute_findByDoctor_found() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 1);
        Optional<Predicate<Doctor>> doctorPredicate = Optional.of(doctor -> doctor.toString().toLowerCase()
                .contains("lamBERT".toLowerCase()));
        FindAppointmentCommand command = new FindAppointmentCommand(Optional.empty(),
                Optional.empty(), Optional.empty(), doctorPredicate);
        expectedModel.updateFilteredAppointmentList(command.getPredicate());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(APPOINTMENT_3),
                model.getFilteredAppointmentList());
    }

    @Test
    public void execute_multipleFields_found() {
        String expectedMessage = String.format(MESSAGE_APPOINTMENTS_LISTED_OVERVIEW, 1);
        Optional<Predicate<Name>> namePredicate = Optional.of(name -> name.fullName.toLowerCase()
                .contains("MEIER".toLowerCase()));
        Optional<Predicate<MedicalTest>> testPredicate = Optional.of(test -> test.toString().toLowerCase()
                .contains("TEST".toLowerCase()));
        Optional<Predicate<Slot>> slotPredicate = Optional.of(slot -> slot.toString()
                .contains("2023-".toLowerCase()));
        Optional<Predicate<Doctor>> doctorPredicate = Optional.of(doctor -> doctor.toString().toLowerCase()
                .contains("everett".toLowerCase()));
        FindAppointmentCommand command = new FindAppointmentCommand(namePredicate, testPredicate, slotPredicate,
                doctorPredicate);
        expectedModel.updateFilteredAppointmentList(command.getPredicate());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(APPOINTMENT_2), model.getFilteredAppointmentList());
    }
}
