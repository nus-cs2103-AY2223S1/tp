package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFNAME_ANDY;
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
import seedu.address.testutil.ProjectBuilder;
import seedu.address.testutil.StaffBuilder;

public class AddStaffCommandTest {

    @Test
    public void constructor_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddStaffCommand(null, null));
    }

    @Test
    public void execute_validProjectValidStaff_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Project validProject = new ProjectBuilder().build();
        model.addProject(validProject);
        Staff validStaff = new StaffBuilder().build();
        AddStaffCommand addStaffCommand = new AddStaffCommand(validStaff, validProject.getProjectName());

        String expectedMessaage = String.format(AddStaffCommand.MESSAGE_ADD_STAFF_SUCCESS, validStaff);

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Project expectedProject = new ProjectBuilder().build();
        expectedProject.getStaffList().add(validStaff);
        expectedModel.addProject(expectedProject);

        assertCommandSuccess(addStaffCommand, model, expectedMessaage, expectedModel);
    }

    @Test
    public void execute_validProjectInvalidStaff_throwsException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Staff validStaff = new StaffBuilder().build();
        Project validProject = new ProjectBuilder().withStaff(validStaff).build();
        model.addProject(validProject);
        AddStaffCommand addStaffCommand = new AddStaffCommand(validStaff, validProject.getProjectName());

        assertCommandFailure(addStaffCommand, model, AddStaffCommand.MESSAGE_DUPLICATE_STAFF);
    }

    @Test
    public void execute_invalidProjectValidStaff_throwsException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Staff validStaff = new StaffBuilder().build();
        Project invalidProject = new ProjectBuilder().build();
        AddStaffCommand addStaffCommand = new AddStaffCommand(validStaff, invalidProject.getProjectName());

        String errorMessage = String.format(Messages.MESSAGE_INVALID_PROJECT, invalidProject.getProjectName());

        assertCommandFailure(addStaffCommand, model, errorMessage);
    }

    @Test
    public void equals() {
        final Staff staff = new StaffBuilder().build();
        final ProjectName projectName = new ProjectName(VALID_NAME_AMY);
        final AddStaffCommand addStaffCommand = new AddStaffCommand(staff, projectName);

        // same object -> returns true
        assertTrue(addStaffCommand.equals(addStaffCommand));

        // same values -> returns true
        assertTrue(addStaffCommand.equals(new AddStaffCommand(staff, new ProjectName(VALID_NAME_AMY))));

        // null -> returns false
        assertFalse(addStaffCommand.equals(null));

        // different types -> returns false
        assertFalse(addStaffCommand.equals(new ClearCommand()));

        // different project name -> returns false
        assertFalse(addStaffCommand.equals(new AddStaffCommand(staff, new ProjectName(VALID_NAME_BOB))));

        // different staff -> returns false
        assertFalse(addStaffCommand.equals(new AddStaffCommand(
                new StaffBuilder().withStaffName(VALID_STAFFNAME_ANDY).build(), projectName)));
    }
}
