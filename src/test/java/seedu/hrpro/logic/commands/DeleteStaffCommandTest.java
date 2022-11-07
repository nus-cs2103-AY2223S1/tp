package seedu.hrpro.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hrpro.commons.core.Messages.MESSAGE_INVALID_PROJECT;
import static seedu.hrpro.commons.core.Messages.MESSAGE_INVALID_STAFF;
import static seedu.hrpro.commons.core.Messages.MESSAGE_INVALID_STAFF_DISPLAYED_INDEX;
import static seedu.hrpro.commons.core.Messages.MESSAGE_NO_STAFF_DISPLAYED;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.hrpro.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.hrpro.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.hrpro.testutil.TypicalHrPro.getTypicalHrPro;
import static seedu.hrpro.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.hrpro.testutil.TypicalIndexes.INDEX_FIRST_STAFF;
import static seedu.hrpro.testutil.TypicalIndexes.INDEX_SECOND_STAFF;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.hrpro.commons.core.index.Index;
import seedu.hrpro.model.Model;
import seedu.hrpro.model.ModelManager;
import seedu.hrpro.model.UserPrefs;
import seedu.hrpro.model.project.Project;
import seedu.hrpro.model.project.ProjectName;
import seedu.hrpro.model.staff.Staff;
import seedu.hrpro.model.staff.StaffName;
import seedu.hrpro.testutil.ProjectBuilder;
import seedu.hrpro.testutil.StaffBuilder;

public class DeleteStaffCommandTest {

    private Model model = new ModelManager(getTypicalHrPro(), new UserPrefs());

    @Test
    public void execute_validInput_success() {
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        ProjectName projectName = project.getProjectName();
        Staff staff = model.getFilteredStaffList().get(INDEX_FIRST_STAFF.getZeroBased());
        StaffName staffName = staff.getStaffName();

        String expectedMessage = String.format(DeleteStaffCommand.MESSAGE_DELETE_STAFF_SUCCESS,
                staffName, projectName);

        Index index = Index.fromOneBased(1);

        DeleteStaffCommand deleteStaffCommand = new DeleteStaffCommand(index, projectName);

        // Simulating expected behaviour
        Model expectedModel = new ModelManager(getTypicalHrPro(), model.getUserPrefs());
        Project tempProject = new ProjectBuilder(project).build();
        tempProject.getStaffList().remove(staff);
        expectedModel.setProject(project, tempProject);
        expectedModel.setFilteredStaffList(tempProject.getStaffList());
        expectedModel.updateFilteredStaffList(Model.PREDICATE_SHOW_ALL_STAFF);

        assertCommandSuccess(deleteStaffCommand, model, expectedMessage, expectedModel);

        // Reset model
        model = new ModelManager(getTypicalHrPro(), new UserPrefs());
    }


    @Test
    public void execute_invalidProject_throwCommandException() {
        // Trying to delete from a project that does not exist
        ProjectName projectName = new ProjectName("WRONG PROJECT NAME");
        DeleteStaffCommand deleteStaffCommand = new DeleteStaffCommand(INDEX_FIRST_STAFF, projectName);

        String expectedMessage = String.format(MESSAGE_INVALID_PROJECT, projectName);

        assertCommandFailure(deleteStaffCommand, model, expectedMessage);

        // Trying to delete a staff from a project that does not contain this staff
        Project targetProject = model.getFilteredProjectList().get(1);
        DeleteStaffCommand deleteStaffCommand2 = new DeleteStaffCommand(INDEX_FIRST_STAFF,
                targetProject.getProjectName());
        Staff staffToDelete = model.getFilteredStaffList().get(0);

        String expectedMessage2 = String.format(MESSAGE_INVALID_STAFF,
                staffToDelete.getStaffName());

        assertCommandFailure(deleteStaffCommand2, model, expectedMessage2);
    }

    @Test
    public void execute_invalidIndex_throwCommandException() {
        cleanUpModel();
        Project project = model.getHrPro().getProjectList().get(0);
        Staff staff = new StaffBuilder().withStaffName(VALID_NAME_AMY).build();
        project.getStaffList().add(staff);
        ProjectName projectName = project.getProjectName();
        model.setFilteredStaffList(project.getStaffList());
        int len = model.getFilteredStaffList().size();
        Index index = Index.fromZeroBased(len + 1);
        DeleteStaffCommand deleteStaffCommand = new DeleteStaffCommand(index, projectName);

        String expectedMessage = MESSAGE_INVALID_STAFF_DISPLAYED_INDEX;

        assertCommandFailure(deleteStaffCommand, model, expectedMessage);
        model.getFilteredProjectList().get(0).getStaffList().remove(staff);
    }

    @Test
    public void execute_noDisplayedStaff_throwCommandException() {
        Project project = model.getHrPro().getProjectList().get(0);
        ProjectName projectName = project.getProjectName();

        DeleteStaffCommand deleteStaffCommand = new DeleteStaffCommand(INDEX_FIRST_STAFF, projectName);
        String expectedMessage = String.format(MESSAGE_NO_STAFF_DISPLAYED, "delstaff command");

        clearFilteredStaff();
        assertCommandFailure(deleteStaffCommand, model, expectedMessage);

        // Reset model
        model = new ModelManager(getTypicalHrPro(), new UserPrefs());
    }

    @Test
    public void execute_noDisplayedProject_throwCommandException() {
        Project targetProject = model.getFilteredProjectList().get(0);
        ProjectName projectName = targetProject.getProjectName();

        DeleteStaffCommand deleteStaffCommand = new DeleteStaffCommand(INDEX_FIRST_STAFF, projectName);
        String expectedMessage = String.format(MESSAGE_INVALID_PROJECT, projectName);

        clearFilteredProjects();
        assertCommandFailure(deleteStaffCommand, model, expectedMessage);

        // Reset model
        model = new ModelManager(getTypicalHrPro(), new UserPrefs());
    }

    @Test
    public void equals() {
        cleanUpModel();
        ProjectName projectNameFirst = new ProjectName(VALID_NAME_AMY);
        ProjectName projectNameSecond = new ProjectName(VALID_NAME_BOB);
        DeleteStaffCommand deleteFirstCommand = new DeleteStaffCommand(INDEX_FIRST_STAFF, projectNameFirst);
        DeleteStaffCommand deleteSecondCommand = new DeleteStaffCommand(INDEX_SECOND_STAFF, projectNameSecond);

        // same object -> returns true
        assertTrue(deleteFirstCommand.equals(deleteFirstCommand));

        // same values -> returns true
        DeleteStaffCommand deleteFirstCommandCopy = new DeleteStaffCommand(INDEX_FIRST_STAFF, projectNameFirst);
        assertTrue(deleteFirstCommand.equals(deleteFirstCommandCopy));

        // different types -> returns false
        assertFalse(deleteFirstCommand.equals(1));

        // null -> returns false
        assertFalse(deleteFirstCommand.equals(null));

        // different project -> returns false
        assertFalse(deleteFirstCommand.equals(deleteSecondCommand));
    }

    private void cleanUpModel() {
        Project project = model.getFilteredProjectList().get(INDEX_FIRST_PROJECT.getZeroBased());
        project.getStaffList().setStaffs(new ArrayList<>());
    }

    private void clearFilteredProjects() {
        //Clears the displayed project list
        model.updateFilteredProjectList((a) -> false);
    }

    private void clearFilteredStaff() {
        //Clears the displayed project list
        model.updateFilteredStaffList((a) -> false);
    }
}
