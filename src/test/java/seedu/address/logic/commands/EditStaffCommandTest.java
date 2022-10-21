package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.STAFF_DESC_ANDY;
import static seedu.address.logic.commands.CommandTestUtil.STAFF_DESC_JAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFCONTACT_ANDY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFDEPARTMENT_ANDY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFNAME_ANDY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PROJECT;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.EditStaffCommand.EditStaffDescriptor;
import seedu.address.model.AddressBook;
import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;
import seedu.address.model.project.ProjectName;
import seedu.address.model.staff.Staff;
import seedu.address.testutil.EditStaffDescriptorBuilder;
import seedu.address.testutil.ProjectBuilder;
import seedu.address.testutil.StaffBuilder;

/**
 * Contains integration tests (interaction with the Model)
 */
public class EditStaffCommandTest {


    @Test
    public void execute_allFieldsSpecifiedUnfilteredList_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Project project = new ProjectBuilder().build();
        Staff editedStaff = new StaffBuilder().build();
        EditStaffDescriptor descriptor = new EditStaffDescriptorBuilder(editedStaff).build();
        project.getStaffList().add(editedStaff);
        String expectedMessage = String.format(EditStaffCommand.MESSAGE_EDIT_STAFF_SUCCESS, editedStaff);
        EditStaffCommand editStaffCommand = new EditStaffCommand(
                project.getProjectName(), INDEX_FIRST_PROJECT, descriptor);
        model.setProject(model.getFilteredProjectList().get(0), project);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        assertCommandSuccess(editStaffCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_someFieldsSpecifiedUnfilteredList_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Project project = new ProjectBuilder().build();
        Staff editedStaff = new StaffBuilder().withStaffName(VALID_STAFFNAME_ANDY)
                .withStaffContact(VALID_STAFFCONTACT_ANDY).withStaffDepartment(VALID_STAFFDEPARTMENT_ANDY).build();
        EditStaffDescriptor descriptor = new EditStaffDescriptorBuilder().withName(VALID_STAFFNAME_ANDY)
                .withContact(VALID_STAFFCONTACT_ANDY).withDepartment(VALID_STAFFDEPARTMENT_ANDY).build();
        project.getStaffList().add(editedStaff);
        String expectedMessage = String.format(EditStaffCommand.MESSAGE_EDIT_STAFF_SUCCESS, editedStaff);
        EditStaffCommand editStaffCommand = new EditStaffCommand(
                project.getProjectName(), INDEX_FIRST_PROJECT, descriptor);
        model.setProject(model.getFilteredProjectList().get(0), project);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        assertCommandSuccess(editStaffCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecifiedUnfilteredList_success() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Project project = new ProjectBuilder().build();
        Staff staff = new StaffBuilder().build();
        project.getStaffList().add(staff);
        model.setProject(model.getFilteredProjectList().get(0), project);
        EditStaffCommand editStaffCommand = new EditStaffCommand(project.getProjectName(), INDEX_FIRST_PROJECT,
                new EditStaffDescriptor());
        String expectedMessage = String.format(EditStaffCommand.MESSAGE_EDIT_STAFF_SUCCESS, staff);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        assertCommandSuccess(editStaffCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_invalidProjectName_failure() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Project emptyProject = new ProjectBuilder().withName("DoesNotExist").build();
        EditStaffDescriptor editStaffDescriptor = new EditStaffDescriptorBuilder().build();
        EditStaffCommand editStaffCommand = new EditStaffCommand(emptyProject.getProjectName(), INDEX_FIRST_PROJECT,
                editStaffDescriptor);
        assertCommandFailure(editStaffCommand, expectedModel, String.format(Messages.MESSAGE_INVALID_PROJECT,
                emptyProject.getProjectName()));
    }

    @Test
    public void execute_invalidStaffIndex_failure() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Project blankProject = new ProjectBuilder().withName("Nothing").build();
        expectedModel.addProject(blankProject);

        Index outOfBoundIndex = INDEX_SECOND_PROJECT;
        EditStaffDescriptor editStaffDescriptor = new EditStaffDescriptorBuilder().build();
        assertTrue(outOfBoundIndex.getZeroBased() >= blankProject.getStaffList().size());
        EditStaffCommand editStaffCommand = new EditStaffCommand(blankProject.getProjectName(), outOfBoundIndex,
                editStaffDescriptor);
        assertCommandFailure(editStaffCommand, expectedModel, EditStaffCommand.MESSAGE_INVALID_STAFF_INDEX);
    }

    @Test
    public void equals() {
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

}
