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
import seedu.address.model.patient.Patient;

public class SortPatientCommandTest {
    private Model model = new ModelManager(getTypicalPatientsHealthContact(), new UserPrefs());

    @Test
    public void execute_validSortPatientName_success() {
        /*
        Patient patientToSelect = model.getFilteredPatientList().get(INDEX_FIRST_PATIENT.getZeroBased());
        SelectPatientCommand selectPatientCommand = new SelectPatientCommand(INDEX_FIRST_PATIENT);

        String expectedMessage = String.format(SelectPatientCommand.MESSAGE_SUCCESS, patientToSelect);

        ModelManager expectedModel = new ModelManager(model.getHealthContact(), new UserPrefs());
        expectedModel.selectPatient(patientToSelect);

        assertCommandSuccess(selectPatientCommand, model, expectedMessage, expectedModel);
        */
        class NameComparator implements Comparator<Patient> {
            @Override
            public int compare(Patient first, Patient second) {
                return first.getName().toString().compareToIgnoreCase(second.getName().toString());
            }
        }
        SortPatientCommand SortPatientCommand = new SortPatientCommand("name", true);
        String expectedMessage = String.format(SortPatientCommand.MESSAGE_SORT_SUCCESS, "name");
        ModelManager expectedModel = new ModelManager(model.getHealthContact(), new UserPrefs());
        expectedModel.sortPatients(new NameComparator(), true);
        assertCommandSuccess(SortPatientCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_invalidSortPatientCriteria_throwCommandException() {
        /*
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredPatientList().size() + 1);
        SelectPatientCommand selectPatientCommand = new SelectPatientCommand(outOfBoundIndex);

        assertCommandFailure(selectPatientCommand, model, Messages.MESSAGE_INVALID_PATIENT_DISPLAYED_INDEX);
         */

        SortPatientCommand SortPatientCommand = new SortPatientCommand("invalid", true);
        assertCommandFailure(SortPatientCommand, model, SortPatientCommand.MESSAGE_USAGE);

    }

    @Test
    public void execute_validSortPatientPhone_success() {
        class PhoneComparator implements Comparator<Patient> {
            @Override
            public int compare(Patient first, Patient second) {
                return first.getPhone().toString().compareTo(second.getPhone().toString());
            }
        }

        SortPatientCommand SortPatientCommand = new SortPatientCommand("phone", true);
        String expectedMessage = String.format(SortPatientCommand.MESSAGE_SORT_SUCCESS, "phone");
        ModelManager expectedModel = new ModelManager(model.getHealthContact(), new UserPrefs());
        expectedModel.sortPatients(new PhoneComparator(), true);
    }

    @Test
    public void execute_validSortPatientEmail_success() {
        class EmailComparator implements Comparator<Patient> {
            @Override
            public int compare(Patient first, Patient second) {
                return first.getEmail().toString().compareToIgnoreCase(second.getEmail().toString());
            }
        }

        SortPatientCommand SortPatientCommand = new SortPatientCommand("email", true);
        String expectedMessage = String.format(SortPatientCommand.MESSAGE_SORT_SUCCESS, "email");
        ModelManager expectedModel = new ModelManager(model.getHealthContact(), new UserPrefs());
        expectedModel.sortPatients(new EmailComparator(), true);
    }

    @Test
    public void execute_validSortPatientAddress_success() {
        class AddressComparator implements Comparator<Patient> {
            @Override
            public int compare(Patient first, Patient second) {
                return first.getAddress().toString().compareToIgnoreCase(second.getAddress().toString());
            }
        }

        SortPatientCommand SortPatientCommand = new SortPatientCommand("address", true);
        String expectedMessage = String.format(SortPatientCommand.MESSAGE_SORT_SUCCESS, "doctor");
        ModelManager expectedModel = new ModelManager(model.getHealthContact(), new UserPrefs());
        expectedModel.sortPatients(new AddressComparator(), true);
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

        SortPatientCommand SortPatientCommand = new SortPatientCommand("name", true);
        SortPatientCommand SortPatientCommandCopy = new SortPatientCommand("name", true);
        SortPatientCommand SortPatientCommandDifferent = new SortPatientCommand("address", true);
        SortPatientCommand SortPatientCommandDifferent2 = new SortPatientCommand("name", false);

        // same object -> returns true
        assertTrue(SortPatientCommand.equals(SortPatientCommand));

        // same values -> returns true
        assertTrue(SortPatientCommand.equals(SortPatientCommandCopy));

        // different types -> returns false
        assertFalse(SortPatientCommand.equals(1));

        // null -> returns false
        assertFalse(SortPatientCommand.equals(null));

        // different criteria -> returns false
        assertFalse(SortPatientCommand.equals(SortPatientCommandDifferent));

        // different order -> returns false

    }
}
