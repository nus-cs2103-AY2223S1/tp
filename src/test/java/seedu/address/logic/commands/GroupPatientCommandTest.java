package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for GroupPatientCommand.
 */
public class GroupPatientCommandTest {

    private Model model;
    private Model expectedModel;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel.updatePersonComparator(Model.COMPARATOR_GROUP_PATIENTS);
    }

    @Test
    public void execute_patientListSorted_showsSameList() {
        assertCommandSuccess(
                new GroupPatientCommand(), model, GroupPatientCommand.MESSAGE_SUCCESS_PATIENTS, expectedModel);
    }

    @Test
    public void equals() {
        GroupPatientCommand groupPatientCommand = new GroupPatientCommand();

        // same object -> returns true
        assertTrue(groupPatientCommand.equals(groupPatientCommand));
    }
}
