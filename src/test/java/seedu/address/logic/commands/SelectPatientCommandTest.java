package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.logic.commands.CommandTestUtil.showPatientAtIndex;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PATIENT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PATIENT;
import static seedu.address.testutil.TypicalPatients.getTypicalPatientsHealthContact;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.patient.Patient;

/**
 * Contains integration tests (interaction with the Model) and unit tests for
 * {@code SelectPatientCommand}.
 */
public class SelectPatientCommandTest {

    private Model model = new ModelManager(getTypicalPatientsHealthContact(), new UserPrefs());

    @Test
    public void execute_validIndexUnfilteredList_success() {
        Patient patientToSelect = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());
        SelectPatientCommand selectPatientCommand = new SelectPatientCommand(INDEX_FIRST_PATIENT);

        String expectedMessage = String.format(SelectPatientCommand.MESSAGE_SUCCESS, patientToSelect);

        ModelManager expectedModel = new ModelManager(model.getHealthContact(), new UserPrefs());
        expectedModel.selectPatient(patientToSelect);

        assertCommandSuccess(selectPatientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexUnfilteredList_throwsCommandException() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        SelectPatientCommand selectPatientCommand = new SelectPatientCommand(outOfBoundIndex);

        assertCommandFailure(selectPatientCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    public void execute_validIndexFilteredList_success() {

        Patient patientToSelect = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());
        SelectPatientCommand selectPatientCommand = new SelectPatientCommand(INDEX_FIRST_PATIENT);

        String expectedMessage = String.format(SelectPatientCommand.MESSAGE_SUCCESS, patientToSelect);

        Model expectedModel = new ModelManager(model.getHealthContact(), new UserPrefs());
        expectedModel.selectPatient(patientToSelect);
        showNoAppointment(expectedModel);

        assertCommandSuccess(selectPatientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_invalidIndexFilteredList_throwsCommandException() {
        showPatientAtIndex(model, INDEX_FIRST_PATIENT);
        Index outOfBoundIndex = INDEX_SECOND_PATIENT;
        // ensures that outOfBoundIndex is still in bounds of HealthContact list
        assertTrue(outOfBoundIndex.getZeroBased() < model.getHealthContact().getPatientList().size());

        SelectPatientCommand selectPatientCommand = new SelectPatientCommand(outOfBoundIndex);

        assertCommandFailure(selectPatientCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
    }

    @Test
    public void equals() {
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
    }

    /**
     * Updates {@code model}'s filtered appointment list to show no one.
     */
    private void showNoAppointment(Model model) {
        model.updateFilteredAppointmentList(p -> false);

        assertTrue(model.getFilteredAppointmentList().isEmpty());
    }
}
