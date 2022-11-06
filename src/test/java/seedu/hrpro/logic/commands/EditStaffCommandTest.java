package seedu.hrpro.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hrpro.commons.core.Messages.MESSAGE_NO_STAFF_DISPLAYED;
import static seedu.hrpro.logic.commands.CommandTestUtil.STAFF_DESC_ANDY;
import static seedu.hrpro.logic.commands.CommandTestUtil.STAFF_DESC_JAY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.hrpro.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.hrpro.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.hrpro.logic.commands.EditStaffCommand.MESSAGE_DUPLICATE_STAFF;
import static seedu.hrpro.logic.commands.EditStaffCommand.MESSAGE_EDIT_STAFF_SUCCESS;
import static seedu.hrpro.testutil.TypicalHrPro.getTypicalHrPro;
import static seedu.hrpro.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.hrpro.testutil.TypicalIndexes.INDEX_FIRST_STAFF;
import static seedu.hrpro.testutil.TypicalIndexes.INDEX_SECOND_PROJECT;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;

import seedu.hrpro.commons.core.Messages;
import seedu.hrpro.commons.core.index.Index;
import seedu.hrpro.logic.commands.EditStaffCommand.EditStaffDescriptor;
import seedu.hrpro.model.Model;
import seedu.hrpro.model.ModelManager;
import seedu.hrpro.model.UserPrefs;
import seedu.hrpro.model.project.Project;
import seedu.hrpro.model.project.ProjectName;
import seedu.hrpro.model.staff.Staff;
import seedu.hrpro.testutil.EditStaffDescriptorBuilder;
import seedu.hrpro.testutil.ProjectBuilder;
import seedu.hrpro.testutil.StaffBuilder;

/**
 * Contains integration tests (interaction with the Model)
 */
public class EditStaffCommandTest {

    private Model model = new ModelManager(getTypicalHrPro(), new UserPrefs());

    @Test
    public void execute_invalidProjectName_failure() {
        // Trying to edit a staff from a project that does not exist
        EditStaffDescriptor editStaffDescriptor = new EditStaffDescriptorBuilder().build();
        EditStaffCommand editStaffCommand = new EditStaffCommand(new ProjectName("Does not exist"),
                INDEX_FIRST_STAFF, editStaffDescriptor);
        assertCommandFailure(editStaffCommand, model, String.format(Messages.MESSAGE_INVALID_PROJECT,
                "Does not exist"));

        // Trying to edit a staff from a project that does not contain this staff
        Project wrongProject = model.getFilteredProjectList().get(1);
        EditStaffCommand editStaffCommand2 = new EditStaffCommand(wrongProject.getProjectName(),
                INDEX_FIRST_STAFF, editStaffDescriptor);

        Staff targetStaff = model.getFilteredStaffList().get(0);
        assertCommandFailure(editStaffCommand2, model, String.format(Messages.MESSAGE_INVALID_STAFF,
                targetStaff.getStaffName()));

        // Trying to edit a staff when the displayed project list is of size 0
        Project projectNotFound = model.getFilteredProjectList().get(0);
        EditStaffCommand editStaffCommand3 = new EditStaffCommand(projectNotFound.getProjectName(),
                INDEX_FIRST_STAFF, editStaffDescriptor);

        clearFilteredProjectList();
        assertCommandFailure(editStaffCommand3, model, String.format(Messages.MESSAGE_INVALID_PROJECT,
                projectNotFound.getProjectName()));
    }

    @Test
    public void execute_invalidStaffIndex_failure() {
        // Index out of bounds
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredProjectList().size() + 1);
        Project targetProject = model.getFilteredProjectList().get(0);

        EditStaffDescriptor descriptor = new EditStaffDescriptorBuilder().build();
        EditStaffCommand command = new EditStaffCommand(targetProject.getProjectName(), outOfBoundIndex, descriptor);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_STAFF_DISPLAYED_INDEX);

        // Displayed staff list is empty
        EditStaffCommand command2 = new EditStaffCommand(targetProject.getProjectName(),
                INDEX_FIRST_STAFF, descriptor);
        clearFilteredStaffList();

        assertCommandFailure(command2, model, String.format(MESSAGE_NO_STAFF_DISPLAYED, "editstaff command"));

        // Reset model
        model = new ModelManager(getTypicalHrPro(), new UserPrefs());
    }

    @Test
    public void execute_duplicateStaff_failure() {
        Project targetProject = model.getFilteredProjectList().get(0);
        Staff targetStaff = model.getFilteredStaffList().get(1);
        EditStaffDescriptor duplicateDescriptor = new EditStaffDescriptorBuilder(targetStaff).build();

        EditStaffCommand editStaffCommand = new EditStaffCommand(targetProject.getProjectName(),
                INDEX_FIRST_STAFF, duplicateDescriptor);

        assertCommandFailure(editStaffCommand, model, MESSAGE_DUPLICATE_STAFF);

        // Reset model
        model = new ModelManager(getTypicalHrPro(), new UserPrefs());
    }

    @Test
    public void execute_allFieldsSpecified_success() {
        Project targetProject = model.getFilteredProjectList().get(0);
        Staff editedStaff = new StaffBuilder().build();
        EditStaffDescriptor descriptor = new EditStaffDescriptorBuilder(editedStaff).build();

        EditStaffCommand command = new EditStaffCommand(targetProject.getProjectName(),
                INDEX_FIRST_STAFF, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_STAFF_SUCCESS,
                editedStaff, targetProject.getProjectName());

        // Simulate expected outcome
        Model expectedModel = new ModelManager(getTypicalHrPro(), new UserPrefs());
        Project expectedTargetProject = new ProjectBuilder(targetProject).build();
        Staff expectedTargetStaff = expectedModel.getFilteredStaffList().get(0);
        expectedTargetProject.getStaffList().setStaff(expectedTargetStaff, editedStaff);
        expectedModel.setProject(targetProject, expectedTargetProject);
        expectedModel.setFilteredStaffList(expectedTargetProject.getStaffList());

        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        // Reset model
        model = new ModelManager(getTypicalHrPro(), new UserPrefs());
    }

    @Test
    public void execute_someFieldsSpecified_success() {
        Project targetProject = model.getFilteredProjectList().get(0);
        Staff targetStaff = model.getFilteredStaffList().get(0);
        Staff editedStaff = new StaffBuilder(targetStaff)
                .withStaffName("Name")
                .withStaffDepartment("Department")
                .build();
        EditStaffDescriptor descriptor = new EditStaffDescriptorBuilder(editedStaff).build();

        EditStaffCommand command = new EditStaffCommand(targetProject.getProjectName(),
                INDEX_FIRST_STAFF, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_STAFF_SUCCESS,
                editedStaff, targetProject.getProjectName());

        // Simulate expected outcome
        Model expectedModel = new ModelManager(getTypicalHrPro(), new UserPrefs());
        Project expectedTargetProject = new ProjectBuilder(targetProject).build();
        Staff expectedTargetStaff = expectedModel.getFilteredStaffList().get(0);
        expectedTargetProject.getStaffList().setStaff(expectedTargetStaff, editedStaff);
        expectedModel.setProject(targetProject, expectedTargetProject);
        expectedModel.setFilteredStaffList(expectedTargetProject.getStaffList());

        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        // Reset model
        model = new ModelManager(getTypicalHrPro(), new UserPrefs());
    }

    @Test
    public void execute_noFieldSpecified_success() {
        Project targetProject = model.getFilteredProjectList().get(0);
        Staff targetStaff = model.getFilteredStaffList().get(0);
        EditStaffDescriptor descriptor = new EditStaffDescriptorBuilder(targetStaff).build();

        EditStaffCommand command = new EditStaffCommand(targetProject.getProjectName(),
                INDEX_FIRST_STAFF, descriptor);

        String expectedMessage = String.format(MESSAGE_EDIT_STAFF_SUCCESS,
                targetStaff, targetProject.getProjectName());

        // Simulate expected outcome
        Model expectedModel = new ModelManager(getTypicalHrPro(), new UserPrefs());
        Project expectedTargetProject = new ProjectBuilder(targetProject).build();
        Staff expectedTargetStaff = expectedModel.getFilteredStaffList().get(0);
        expectedTargetProject.getStaffList().setStaff(expectedTargetStaff, targetStaff);
        expectedModel.setProject(targetProject, expectedTargetProject);
        expectedModel.setFilteredStaffList(expectedTargetProject.getStaffList());

        assertCommandSuccess(command, model, expectedMessage, expectedModel);

        // Reset model
        model = new ModelManager(getTypicalHrPro(), new UserPrefs());
    }


    @Test
    public void equals() {
        cleanUpModel();
        final ProjectName standardProjectName = new ProjectName(VALID_NAME_AMY);
        final EditStaffCommand standardCommand = new EditStaffCommand(standardProjectName, INDEX_FIRST_PROJECT,
                STAFF_DESC_ANDY);

        // same values -> returns true
        EditStaffDescriptor copyDescriptor = new EditStaffDescriptor(STAFF_DESC_ANDY);
        EditStaffCommand commandWithSameValues = new EditStaffCommand(standardProjectName, INDEX_FIRST_PROJECT,
                copyDescriptor);
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(standardCommand.equals(new EditStaffCommand(standardProjectName, INDEX_SECOND_PROJECT,
                STAFF_DESC_ANDY)));

        // different descriptor -> returns false
        assertFalse(standardCommand.equals(new EditStaffCommand(standardProjectName, INDEX_FIRST_PROJECT,
                STAFF_DESC_JAY)));
    }

    private void cleanUpModel() {
        Project project = new ProjectBuilder().build();
        model.getFilteredProjectList().remove(project);

        Project projectTwo = model.getFilteredProjectList().get(0);
        projectTwo.getStaffList().setStaffs(new ArrayList<>());
    }

    private void clearFilteredProjectList() {
        model.updateFilteredProjectList((a) -> false);
    }

    private void clearFilteredStaffList() {
        model.updateFilteredStaffList((a) -> false);
    }

}
