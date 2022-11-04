package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFNAME_ANDY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PROJECT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
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
    public void execute_validIndexValidStaff_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Project validProject = new ProjectBuilder().build();
        model.addProject(validProject);
        Staff validStaff = new StaffBuilder().build();
        int len = model.getAddressBook().getProjectList().size() - 1;
        AddStaffCommand addStaffCommand = new AddStaffCommand(validStaff, Index.fromZeroBased(len));

        String expectedMessage = String.format(
                AddStaffCommand.MESSAGE_ADD_STAFF_SUCCESS, validStaff, validProject.getProjectName().toString());

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Project expectedProject = new ProjectBuilder().build();
        expectedProject.getStaffList().add(validStaff);
        expectedModel.addProject(expectedProject);
        expectedModel.setFilteredStaffList(expectedProject.getStaffList());

        assertCommandSuccess(addStaffCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexInvalidStaff_throwsException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Staff validStaff = new StaffBuilder().build();
        Project validProject = new ProjectBuilder().withStaff(validStaff).build();
        model.addProject(validProject);
        int len = model.getAddressBook().getProjectList().size() - 1;
        AddStaffCommand addStaffCommand = new AddStaffCommand(validStaff, Index.fromZeroBased(len));

        String expectedMessage = String.format(AddStaffCommand.MESSAGE_DUPLICATE_STAFF,
                validProject.getProjectName().toString());

        assertCommandFailure(addStaffCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndexValidStaff_throwsException() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Staff validStaff = new StaffBuilder().build();
        Index len = Index.fromZeroBased(model.getAddressBook().getProjectList().size() + 1);

        AddStaffCommand addStaffCommand = new AddStaffCommand(validStaff, len);

        String errorMessage = Messages.MESSAGE_INVALID_PROJECT_DISPLAYED_INDEX;

        assertCommandFailure(addStaffCommand, model, errorMessage);
    }

    @Test
    public void equals() {
        final Staff staff = new StaffBuilder().build();
        final ProjectName projectName = new ProjectName(VALID_NAME_AMY);
        final AddStaffCommand addStaffCommand = new AddStaffCommand(staff, INDEX_FIRST_PROJECT);

        // same object -> returns true
        assertTrue(addStaffCommand.equals(addStaffCommand));

        // same values -> returns true
        assertTrue(addStaffCommand.equals(new AddStaffCommand(staff, INDEX_FIRST_PROJECT)));

        // null -> returns false
        assertFalse(addStaffCommand.equals(null));

        // different types -> returns false
        assertFalse(addStaffCommand.equals(new ClearCommand()));

        // different project name -> returns false
        assertFalse(addStaffCommand.equals(new AddStaffCommand(staff, INDEX_SECOND_PROJECT)));

        // different staff -> returns false
        assertFalse(addStaffCommand.equals(new AddStaffCommand(
                new StaffBuilder().withStaffName(VALID_STAFFNAME_ANDY).build(), INDEX_FIRST_PROJECT)));
    }
}
