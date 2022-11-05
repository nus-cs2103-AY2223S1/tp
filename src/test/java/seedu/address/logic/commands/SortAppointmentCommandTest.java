package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPatients.getTypicalPatientsHealthContact;

import java.util.Comparator;

import org.junit.jupiter.api.Test;

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
        assertCommandSuccess(sortAppointmentCommand, model, expectedMessage, expectedModel);
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
        assertCommandSuccess(sortAppointmentCommand, model, expectedMessage, expectedModel);
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
        assertCommandSuccess(sortAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
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
        assertFalse(sortAppointmentCommand.equals(sortAppointmentCommandDifferent2));

    }
}
