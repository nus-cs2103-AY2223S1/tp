package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalPersons.getTypicalAddressBook;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import seedu.address.model.Key;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;

/**
 * Contains integration tests (interaction with the Model) and unit tests for GroupAppointmentCommand.
 */
public class GroupAppointmentCommandTest {

    private Model model;
    private Model expectedModel1;
    private Model expectedModel2;
    private Model expectedModel3;

    @BeforeEach
    public void setUp() {
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        expectedModel1 = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel1.updateAppointmentComparator(Model.COMPARATOR_GROUP_TAG_APPOINTMENTS);
        expectedModel2 = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel2.updateAppointmentComparator(Model.COMPARATOR_GROUP_PATIENT_APPOINTMENTS);
        expectedModel3 = new ModelManager(model.getAddressBook(), new UserPrefs());
        expectedModel3.updateAppointmentComparator(Model.COMPARATOR_GROUP_MARK_APPOINTMENTS);
    }

    @Test
    public void execute_patientListSorted_showsSameList() {
        assertCommandSuccess(
                new GroupAppointmentCommand(Key.TAG), model,
                String.format(GroupAppointmentCommand.MESSAGE_SUCCESS_APPOINTMENTS, "tag"),
                expectedModel1);
        assertCommandSuccess(
                new GroupAppointmentCommand(Key.PATIENT), model,
                String.format(GroupAppointmentCommand.MESSAGE_SUCCESS_APPOINTMENTS, "patient"),
                expectedModel2);
        assertCommandSuccess(
                new GroupAppointmentCommand(Key.MARK), model,
                String.format(GroupAppointmentCommand.MESSAGE_SUCCESS_APPOINTMENTS, "mark"),
                expectedModel3);
    }

    @Test
    public void equals() {
        GroupAppointmentCommand ungroupAppointmentCommand = new GroupAppointmentCommand(Key.TAG);

        // same object -> returns true
        assertTrue(ungroupAppointmentCommand.equals(ungroupAppointmentCommand));

        // same values -> returns true
        GroupAppointmentCommand ungroupAppointmentCommandCopy = new GroupAppointmentCommand(Key.TAG);
        assertTrue(ungroupAppointmentCommand.equals(ungroupAppointmentCommandCopy));

        // different values -> returns false
        GroupAppointmentCommand ungroupAppointmentCommand2 = new GroupAppointmentCommand(Key.MARK);
        assertFalse(ungroupAppointmentCommand2.equals(ungroupAppointmentCommand));
    }
}
