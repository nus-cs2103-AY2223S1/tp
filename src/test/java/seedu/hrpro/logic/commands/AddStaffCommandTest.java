package seedu.hrpro.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_STAFFNAME_ANDY;
import static seedu.hrpro.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.hrpro.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.hrpro.testutil.Assert.assertThrows;
import static seedu.hrpro.testutil.TypicalHRPro.getTypicalHRPro;
import static seedu.hrpro.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.hrpro.testutil.TypicalIndexes.INDEX_SECOND_PROJECT;

import org.junit.jupiter.api.Test;

import seedu.hrpro.commons.core.Messages;
import seedu.hrpro.commons.core.index.Index;
import seedu.hrpro.model.Model;
import seedu.hrpro.model.ModelManager;
import seedu.hrpro.model.UserPrefs;
import seedu.hrpro.model.project.Project;
import seedu.hrpro.model.project.ProjectName;
import seedu.hrpro.model.staff.Staff;
import seedu.hrpro.testutil.ProjectBuilder;
import seedu.hrpro.testutil.StaffBuilder;

public class AddStaffCommandTest {

    @Test
    public void constructor_nullProject_throwsNullPointerException() {
        assertThrows(NullPointerException.class, () -> new AddStaffCommand(null, null));
    }

    @Test
    public void execute_validIndexValidStaff_success() {
        Model model = new ModelManager(getTypicalHRPro(), new UserPrefs());
        Project validProject = new ProjectBuilder().build();
        model.addProject(validProject);
        Staff validStaff = new StaffBuilder().build();
        int len = model.getHRPro().getProjectList().size() - 1;
        AddStaffCommand addStaffCommand = new AddStaffCommand(validStaff, Index.fromZeroBased(len));

        String expectedMessage = String.format(
                AddStaffCommand.MESSAGE_ADD_STAFF_SUCCESS, validStaff, validProject.getProjectName().toString());

        Model expectedModel = new ModelManager(getTypicalHRPro(), new UserPrefs());
        Project expectedProject = new ProjectBuilder().build();
        expectedProject.getStaffList().add(validStaff);
        expectedModel.addProject(expectedProject);
        expectedModel.setFilteredStaffList(expectedProject.getStaffList());

        assertCommandSuccess(addStaffCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_validIndexInvalidStaff_throwsException() {
        Model model = new ModelManager(getTypicalHRPro(), new UserPrefs());
        Staff validStaff = new StaffBuilder().build();
        Project validProject = new ProjectBuilder().withStaff(validStaff).build();
        model.addProject(validProject);
        int len = model.getHRPro().getProjectList().size() - 1;
        AddStaffCommand addStaffCommand = new AddStaffCommand(validStaff, Index.fromZeroBased(len));

        String expectedMessage = String.format(AddStaffCommand.MESSAGE_DUPLICATE_STAFF,
                validProject.getProjectName().toString());

        assertCommandFailure(addStaffCommand, model, expectedMessage);
    }

    @Test
    public void execute_invalidIndexValidStaff_throwsException() {
        Model model = new ModelManager(getTypicalHRPro(), new UserPrefs());
        Staff validStaff = new StaffBuilder().build();
        Index len = Index.fromZeroBased(model.getHRPro().getProjectList().size() + 1);

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
