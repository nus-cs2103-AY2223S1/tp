package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFNAME_ANDY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFNAME_JAY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.model.staff.Staff;
import seedu.address.model.staff.StaffName;
import seedu.address.testutil.ProjectBuilder;
import seedu.address.testutil.StaffBuilder;

public class FindStaffCommandTest {

    @Test
    public void constructor_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new FindStaffCommand(null, null));
    }

    @Test
    public void execute_validProjectValidStaff_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Project validProject = new ProjectBuilder().build();
        Staff validStaff = new StaffBuilder().build();
        validProject.getStaffList().add(validStaff);
        model.addProject(validProject);
        FindStaffCommand findStaffCommand = new FindStaffCommand(validProject.getProjectName(),
                validStaff.getStaffName());
        String expectedMessage = String.format(FindStaffCommand.MESSAGE_FIND_STAFF_SUCCESS, validStaff);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Project expectedProject = new ProjectBuilder().build();
        expectedProject.getStaffList().add(validStaff);
        expectedModel.addProject(expectedProject);

        assertCommandSuccess(findStaffCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validProjectInvalidStaff_throwsException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Project validProject = new ProjectBuilder().build();
        Staff validStaff = new StaffBuilder().build();
        model.addProject(validProject);
        FindStaffCommand findStaffCommand = new FindStaffCommand(validProject.getProjectName(),
                validStaff.getStaffName());
        assertCommandFailure(findStaffCommand, model, FindStaffCommand.MESSAGE_STAFF_NOT_FOUND);
    }

    @Test
    public void execute_invalidProjectValidStaff_throwsException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Project invalidProject = new ProjectBuilder().build();
        Staff validStaff = new StaffBuilder().build();

        FindStaffCommand findStaffCommand = new FindStaffCommand(invalidProject.getProjectName(),
                validStaff.getStaffName());

        String expectedMessage = String.format(Messages.MESSAGE_INVALID_PROJECT, invalidProject.getProjectName());

        assertCommandFailure(findStaffCommand, model, expectedMessage);
    }

    @Test
    public void equals() {
        final StaffName staffName = new StaffName(VALID_STAFFNAME_ANDY);
        final ProjectName projectName = new ProjectName(VALID_NAME_AMY);
        final FindStaffCommand findStaffCommand = new FindStaffCommand(projectName, staffName);

        // same object -> returns true
        assertTrue(findStaffCommand.equals(findStaffCommand));

        // same values -> returns true
        assertTrue(findStaffCommand.equals(new FindStaffCommand(projectName, staffName)));

        // null -> returns false
        assertFalse(findStaffCommand.equals(null));

        // different types -> return false
        assertFalse(findStaffCommand.equals(new ClearCommand()));

        // different project name -> returns false
        assertFalse(findStaffCommand.equals(new FindStaffCommand(new ProjectName(VALID_NAME_BOB), staffName)));

        // different staff name -> returns false
        assertFalse(findStaffCommand.equals(new FindStaffCommand(projectName, new StaffName(VALID_STAFFNAME_JAY))));
    }
}
