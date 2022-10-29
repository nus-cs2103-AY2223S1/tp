package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showAppointmentAtIndex;
import static seedu.address.testutil.TypicalAppointments.getTypicalAppointmentsHealthContact;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PATIENT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.appointment.Appointment;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code SelectAppointmentCommand}.
 */
public class SelectAppointmentCommandTest {

    private Model model = new ModelManager(getTypicalAppointmentsHealthContact(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Appointment appointmentToSelect = model.getFilteredAppointmentList().get(INDEX_FIRST_PATIENT.getZeroBased());
        SelectAppointmentCommand selectAppointmentCommand = new SelectAppointmentCommand(INDEX_FIRST_PATIENT);

        String expectedMessage = String.format(SelectAppointmentCommand.MESSAGE_SUCCESS, appointmentToSelect);

        ModelManager expectedModel = new ModelManager(model.getHealthContact(), new UserPrefs());
        expectedModel.selectAppointment(appointmentToSelect);

        assertCommandSuccess(selectAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredAppointmentList().size() + 1);
        SelectAppointmentCommand selectAppointmentCommand = new SelectAppointmentCommand(outOfBoundIndex);

        assertCommandFailure(selectAppointmentCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {

        Appointment appointmentToSelect = model.getFilteredAppointmentList().get(INDEX_FIRST_PATIENT.getZeroBased());
        SelectAppointmentCommand selectAppointmentCommand = new SelectAppointmentCommand(INDEX_FIRST_PATIENT);

        String expectedMessage = String.format(SelectAppointmentCommand.MESSAGE_SUCCESS, appointmentToSelect);

        Model expectedModel = new ModelManager(model.getHealthContact(), new UserPrefs());
        expectedModel.selectAppointment(appointmentToSelect);
        showNoAppointment(expectedModel);

        assertCommandSuccess(selectAppointmentCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showAppointmentAtIndex(model, INDEX_FIRST_PATIENT);
        Index outOfBoundIndex = INDEX_SECOND_PATIENT;
        // ensures that outOfBoundIndex is still in bounds of HealthContact list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getHealthContact().getAppointmentList().size());

        SelectAppointmentCommand selectAppointmentCommand = new SelectAppointmentCommand(outOfBoundIndex);

        assertCommandFailure(selectAppointmentCommand, model, Messages.MESSAGE_INVALID_APPOINTMENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
        SelectAppointmentCommand selectFirstCommand = new SelectAppointmentCommand(INDEX_FIRST_PATIENT);
        SelectAppointmentCommand selectSecondCommand = new SelectAppointmentCommand(INDEX_SECOND_PATIENT);

        // same object -> returns true
        assertTrue(selectFirstCommand.equals(selectFirstCommand));

        // same values -> returns true
        SelectAppointmentCommand selectFirstCommandCopy = new SelectAppointmentCommand(INDEX_FIRST_PATIENT);
        assertTrue(selectFirstCommand.equals(selectFirstCommandCopy));

        // different types -> returns false
        assertFalse(selectFirstCommand.equals(1));

        // null -> returns false
        assertFalse(selectFirstCommand.equals(null));

        // different appointment -> returns false
        assertFalse(selectFirstCommand.equals(selectSecondCommand));
    }

    /**
     * Updates {@code model}'s filtered appointment list to show no one.
     */
    private void showNoAppointment(Model model) {
        model.updateFilteredAppointmentList(p -> false);

        assertTrue(model.getFilteredAppointmentList().isEmpty());
    }
}
