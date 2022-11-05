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
import seedu.address.model.patient.Patient;

public class SortPatientCommandTest {
    private Model model = new ModelManager(getTypicalPatientsHealthContact(), new UserPrefs());

    @Test
    public void execute_validSortPatientName_success() {
        class NameComparator implements Comparator<Patient> {
            @Override
            public int compare(Patient first, Patient second) {
                return first.getName().toString().compareToIgnoreCase(second.getName().toString());
            }
        }
        SortPatientCommand sortPatientCommand = new SortPatientCommand("name", true);
        String expectedMessage = String.format(SortPatientCommand.MESSAGE_SORT_SUCCESS, "name");
        ModelManager expectedModel = new ModelManager(model.getHealthContact(), new UserPrefs());
        expectedModel.sortPatients(new NameComparator(), true);
        assertCommandSuccess(sortPatientCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_invalidSortPatientCriteria_throwCommandException() {
        SortPatientCommand sortPatientCommand = new SortPatientCommand("invalid", true);
        assertCommandFailure(sortPatientCommand, model, SortPatientCommand.MESSAGE_USAGE);

    }

    @Test
    public void execute_validSortPatientPhone_success() {
        class PhoneComparator implements Comparator<Patient> {
            @Override
            public int compare(Patient first, Patient second) {
                return first.getPhone().toString().compareTo(second.getPhone().toString());
            }
        }

        SortPatientCommand sortPatientCommand = new SortPatientCommand("phone", true);
        String expectedMessage = String.format(SortPatientCommand.MESSAGE_SORT_SUCCESS, "phone");
        ModelManager expectedModel = new ModelManager(model.getHealthContact(), new UserPrefs());
        expectedModel.sortPatients(new PhoneComparator(), true);
        assertCommandSuccess(sortPatientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validSortPatientEmail_success() {
        class EmailComparator implements Comparator<Patient> {
            @Override
            public int compare(Patient first, Patient second) {
                return first.getEmail().toString().compareToIgnoreCase(second.getEmail().toString());
            }
        }

        SortPatientCommand sortPatientCommand = new SortPatientCommand("email", true);
        String expectedMessage = String.format(SortPatientCommand.MESSAGE_SORT_SUCCESS, "email");
        ModelManager expectedModel = new ModelManager(model.getHealthContact(), new UserPrefs());
        expectedModel.sortPatients(new EmailComparator(), true);
        assertCommandSuccess(sortPatientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validSortPatientAddress_success() {
        class AddressComparator implements Comparator<Patient> {
            @Override
            public int compare(Patient first, Patient second) {
                return first.getAddress().toString().compareToIgnoreCase(second.getAddress().toString());
            }
        }

        SortPatientCommand sortPatientCommand = new SortPatientCommand("address", true);
        String expectedMessage = String.format(SortPatientCommand.MESSAGE_SORT_SUCCESS, "address");
        ModelManager expectedModel = new ModelManager(model.getHealthContact(), new UserPrefs());
        expectedModel.sortPatients(new AddressComparator(), true);
        assertCommandSuccess(sortPatientCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        SortPatientCommand sortPatientCommand = new SortPatientCommand("name", true);
        SortPatientCommand sortPatientCommandCopy = new SortPatientCommand("name", true);
        SortPatientCommand sortPatientCommandDifferent = new SortPatientCommand("address", true);
        SortPatientCommand sortPatientCommandDifferent2 = new SortPatientCommand("name", false);

        // same object -> returns true
        assertTrue(sortPatientCommand.equals(sortPatientCommand));

        // same values -> returns true
        assertTrue(sortPatientCommand.equals(sortPatientCommandCopy));

        // different types -> returns false
        assertFalse(sortPatientCommand.equals(1));

        // null -> returns false
        assertFalse(sortPatientCommand.equals(null));

        // different criteria -> returns false
        assertFalse(sortPatientCommand.equals(sortPatientCommandDifferent));

        // different order -> returns false
        assertFalse(sortPatientCommand.equals(sortPatientCommandDifferent2));

    }
}
