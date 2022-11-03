package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPatients.getTypicalPatientsHealthContact;


import java.util.Comparator;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;


/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code SelectPatientCommand}.
 */
public class SortAppointmentCommandTest {

    private Model model = new ModelManager(getTypicalPatientsHealthContact(), new UserPrefs());

    @Test
    public void execute_validSortAppointmentName_success() {
        /*
        Patient patientToSelect = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());
        SelectPatientCommand selectPatientCommand = new SelectPatientCommand(INDEX_FIRST_PATIENT);

        String expectedMessage = String.format(SelectPatientCommand.MESSAGE_SUCCESS, patientToSelect);

        ModelManager expectedModel = new ModelManager(model.getHealthContact(), new UserPrefs());
        expectedModel.selectPatient(patientToSelect);

        assertCommandSuccess(selectPatientCommand, model, expectedMessage, expectedModel);
        */
        class NameComparator implements Comparator<Appointment> {
            @Override
            public int compare(Appointment first, Appointment second) {
                return first.getName().toString().compareTo(second.getName().toString());
            }
        }
        SortAppointmentCommand sortAppointmentCommand = new SortAppointmentCommand("name", true);
        String expectedMessage = String.format(SortAppointmentCommand.MESSAGE_SORT_SUCCESS, "name");
        ModelManager expectedModel = new ModelManager(model.getHealthContact(), new UserPrefs());
        expectedModel.sortAppointments(new NameComparator(), true);
        assertCommandSuccess(sortAppointmentCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_invalidSortAppointmentCriteria_throwCommandException() {
        /*
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        SelectPatientCommand selectPatientCommand = new SelectPatientCommand(outOfBoundIndex);

        assertCommandFailure(selectPatientCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
         */

        SortAppointmentCommand sortAppointmentCommand = new SortAppointmentCommand("invalid", true);
        assertCommandFailure(sortAppointmentCommand, model, SortAppointmentCommand.MESSAGE_USAGE);

    }

    @Test
    public void execute_validSortAppointmentTest_success() {
        class TestComparator implements Comparator<Appointment> {
            @Override
            public int compare(Appointment first, Appointment second) {
                return first.getMedicalTest().toString().compareTo(second.getMedicalTest().toString());
            }
        }

        SortAppointmentCommand sortAppointmentCommand = new SortAppointmentCommand("test", true);
        String expectedMessage = String.format(SortAppointmentCommand.MESSAGE_SORT_SUCCESS, "test");
        ModelManager expectedModel = new ModelManager(model.getHealthContact(), new UserPrefs());
        expectedModel.sortAppointments(new TestComparator(), true);
    }

    @Test
    public void execute_validSortAppointmentSlot_success() {
        class SlotComparator implements Comparator<Appointment> {
            @Override
            public int compare(Appointment first, Appointment second) {
                return first.getSlot().toString().compareTo(second.getSlot().toString());
            }
        }

        SortAppointmentCommand sortAppointmentCommand = new SortAppointmentCommand("slot", true);
        String expectedMessage = String.format(SortAppointmentCommand.MESSAGE_SORT_SUCCESS, "slot");
        ModelManager expectedModel = new ModelManager(model.getHealthContact(), new UserPrefs());
        expectedModel.sortAppointments(new SlotComparator(), true);
    }

    @Test
    public void execute_validSortAppointmentDoctor_success() {
        class DoctorComparator implements Comparator<Appointment> {
            @Override
            public int compare(Appointment first, Appointment second) {
                return first.getDoctor().toString().compareTo(second.getDoctor().toString());
            }
        }

        SortAppointmentCommand sortAppointmentCommand = new SortAppointmentCommand("doctor", true);
        String expectedMessage = String.format(SortAppointmentCommand.MESSAGE_SORT_SUCCESS, "doctor");
        ModelManager expectedModel = new ModelManager(model.getHealthContact(), new UserPrefs());
        expectedModel.sortAppointments(new DoctorComparator(), true);
    }

    @Test
    public void equals() {
        /*
        SelectPatientCommand selectFirstCommand = new SelectPatientCommand(INDEX_FIRST_PATIENT);
        SelectPatientCommand selectSecondCommand = new SelectPatientCommand(INDEX_SECOND_PATIENT);

        // same object -> returns true
        assertTrue(selectFirstCommand.equals(selectFirstCommand));

        // same values -> returns true
        SelectPatientCommand selectFirstCommandCopy = new SelectPatientCommand(INDEX_FIRST_PATIENT);
        assertTrue(selectFirstCommand.equals(selectFirstCommandCopy));

        // different types -> returns false
        assertFalse(selectFirstCommand.equals(1));

        // null -> returns false
        assertFalse(selectFirstCommand.equals(null));

        // different patient -> returns false
        assertFalse(selectFirstCommand.equals(selectSecondCommand));
         */

        SortAppointmentCommand sortAppointmentCommand = new SortAppointmentCommand("name", true);
        SortAppointmentCommand sortAppointmentCommandCopy = new SortAppointmentCommand("name", true);
        SortAppointmentCommand sortAppointmentCommandDifferent = new SortAppointmentCommand("test", true);
        SortAppointmentCommand sortAppointmentCommandDifferent2 = new SortAppointmentCommand("name", false);

        // same object -> returns true
        assertTrue(sortAppointmentCommand.equals(sortAppointmentCommand));

        // same values -> returns true
        assertTrue(sortAppointmentCommand.equals(sortAppointmentCommandCopy));

        // different types -> returns false
        assertFalse(sortAppointmentCommand.equals(1));

        // null -> returns false
        assertFalse(sortAppointmentCommand.equals(null));

        // different criteria -> returns false
        assertFalse(sortAppointmentCommand.equals(sortAppointmentCommandDifferent));

        // different order -> returns false

    }
}