package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.STAFF_DESC_ANDY;
import static seedu.address.logic.commands.CommandTestUtil.STAFF_DESC_JAY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFCONTACT_ANDY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFNAME_ANDY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFTITLE_ANDY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PROJECT;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PROJECT;

import java.util.ArrayList;

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

    private Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_invalidProjectName_failure() {
        cleanUpModel();
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Project project = model.getFilteredProjectList().get(0);
        Staff staff = new StaffBuilder().withStaffName(VALID_NAME_AMY).build();
        project.getStaffList().add(staff);
        model.setFilteredStaffList(project.getStaffList());
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        Project emptyProject = new ProjectBuilder().withName("DoesNotExist").build();
        EditStaffDescriptor editStaffDescriptor = new EditStaffDescriptorBuilder().build();
        EditStaffCommand editStaffCommand = new EditStaffCommand(emptyProject.getProjectName(), INDEX_FIRST_PROJECT,
                editStaffDescriptor);
        assertCommandFailure(editStaffCommand, expectedModel, String.format(Messages.MESSAGE_INVALID_PROJECT,
                emptyProject.getProjectName()));

        model.getFilteredProjectList().get(0).getStaffList().remove(staff);
    }

    @Test
    public void execute_invalidStaffIndex_failure() {
        cleanUpModel();
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Project project = model.getFilteredProjectList().get(0);
        Staff staff = new StaffBuilder().withStaffName(VALID_NAME_AMY).build();
        project.getStaffList().add(staff);
        model.setFilteredStaffList(project.getStaffList());
        Index outOfBoundIndex = Index.fromOneBased(project.getStaffList().size() + 1);
        ProjectName invalidProjectName = model.getFilteredProjectList().get(0).getProjectName();
        EditStaffDescriptor descriptor = new EditStaffDescriptorBuilder().withName(invalidProjectName.fullName)
                .build();
        EditStaffCommand command = new EditStaffCommand(invalidProjectName, outOfBoundIndex, descriptor);

        assertCommandFailure(command, model, Messages.MESSAGE_INVALID_STAFF_DISPLAYED_INDEX);

        model.getFilteredProjectList().get(0).getStaffList().remove(staff);
    }

    @Test
    public void execute_duplicateStaff_failure() {
        cleanUpModel();
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Staff dummyStaff = new StaffBuilder().withStaffName(VALID_STAFFNAME_ANDY).build();

        // add two more dummy staff to ensure the total number of staff in the project >= 2
        Staff dummyStaffTwo = new StaffBuilder().withStaffName("Marcus").build();
        Staff dummyStaffThree = new StaffBuilder().withStaffName("Beethoven Mozart").build();
        model.getFilteredProjectList().get(0).getStaffList().add(dummyStaff);
        model.getFilteredProjectList().get(0).getStaffList().add(dummyStaffTwo);
        model.getFilteredProjectList().get(0).getStaffList().add(dummyStaffThree);

        Index outOfBoundsIndex = INDEX_SECOND_PROJECT;
        assertTrue(outOfBoundsIndex.getZeroBased()
                < model.getFilteredProjectList().get(0).getStaffList().size());

        EditStaffCommand command = new EditStaffCommand(model.getFilteredProjectList().get(0).getProjectName(),
                outOfBoundsIndex,
                new EditStaffDescriptor(new EditStaffDescriptorBuilder().withName(VALID_STAFFNAME_ANDY).build()));

        model.setFilteredStaffList(model.getFilteredProjectList().get(0).getStaffList());
        assertCommandFailure(command, model, EditStaffCommand.MESSAGE_DUPLICATE_STAFF);

    }

    @Test
    public void execute_allFieldsSpecified_success() {
        cleanUpModel();
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Project validProject = new ProjectBuilder().build();

        Staff validStaff = new StaffBuilder().build();
        Staff validStaffTwo = new StaffBuilder().withStaffName("Carlos").build();
        EditStaffDescriptor descriptor = new EditStaffDescriptorBuilder(validStaffTwo).build();

        validProject.getStaffList().add(validStaff);
        model.addProject(validProject);

        EditStaffCommand command = new EditStaffCommand(validProject.getProjectName(),
                INDEX_FIRST_PROJECT, descriptor);

        String expectedMessage = String.format(EditStaffCommand.MESSAGE_EDIT_STAFF_SUCCESS,
                validStaffTwo, validProject.getProjectName().toString());

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Project expectedProject = new ProjectBuilder(validProject).build();
        expectedProject.getStaffList().setStaff(validStaff, validStaffTwo);
        expectedModel.addProject(expectedProject);
        model.setFilteredStaffList(validProject.getStaffList());
        expectedModel.setFilteredStaffList(expectedProject.getStaffList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_someFieldsSpecified_success() {
        cleanUpModel();
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Project validProject = new ProjectBuilder().build();

        Staff validStaff = new StaffBuilder().build();
        Staff validStaffTwo = new StaffBuilder().withStaffName("Carlos")
                .withStaffContact(VALID_STAFFCONTACT_ANDY).withStaffTitle(VALID_STAFFTITLE_ANDY).build();

        EditStaffDescriptor descriptor = new EditStaffDescriptorBuilder(validStaffTwo).build();

        validProject.getStaffList().add(validStaff);
        model.addProject(validProject);

        EditStaffCommand command = new EditStaffCommand(validProject.getProjectName(),
                INDEX_FIRST_PROJECT, descriptor);

        String expectedMessage = String.format(EditStaffCommand.MESSAGE_EDIT_STAFF_SUCCESS,
                validStaffTwo, validProject.getProjectName().toString());

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Project expectedProject = new ProjectBuilder(validProject).build();
        expectedProject.getStaffList().setStaff(validStaff, validStaffTwo);
        expectedModel.addProject(expectedProject);
        model.setFilteredStaffList(validProject.getStaffList());
        expectedModel.setFilteredStaffList(expectedProject.getStaffList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_noFieldSpecified_success() {
        cleanUpModel();
        model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Project validProject = new ProjectBuilder().build();

        Staff validStaff = new StaffBuilder().build();

        EditStaffDescriptor descriptor = new EditStaffDescriptorBuilder().build();

        validProject.getStaffList().add(validStaff);
        model.addProject(validProject);

        EditStaffCommand command = new EditStaffCommand(validProject.getProjectName(),
                INDEX_FIRST_PROJECT, descriptor);

        String expectedMessage = String.format(EditStaffCommand.MESSAGE_EDIT_STAFF_SUCCESS,
                validStaff, validProject.getProjectName().toString());

        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Project expectedProject = new ProjectBuilder(validProject).build();
        expectedProject.getStaffList().setStaff(validStaff, validStaff);
        model.setFilteredStaffList(validProject.getStaffList());
        expectedModel.addProject(expectedProject);
        expectedModel.setFilteredStaffList(expectedProject.getStaffList());
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
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

}
