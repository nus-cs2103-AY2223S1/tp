package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_STAFFCONTACT_ANDY;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandFailure;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;

import java.util.Arrays;
import java.util.List;

import org.junit.jupiter.api.Test;

import seedu.address.model.AddressBook;
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
    public void execute_zeroKeywords_noStaffFound() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Project project = new ProjectBuilder().withName(VALID_NAME_AMY).build();

        Staff staffAmy = new StaffBuilder().withStaffName(VALID_NAME_AMY).build();
        Staff staffAmyBob = new StaffBuilder().withStaffName(VALID_NAME_AMY + VALID_NAME_BOB)
                .withStaffContact(VALID_STAFFCONTACT_ANDY).build();
        Staff staffBob = new StaffBuilder().withStaffName(VALID_NAME_BOB).build();

        project.getStaffList().add(staffAmy);
        project.getStaffList().add(staffAmyBob);
        project.getStaffList().add(staffBob);

        model.addProject(project);

        String expectedMessage = String.format(FindStaffCommand.MESSAGE_STAFF_NOT_FOUND);
        FindStaffCommand command = new FindStaffCommand(model.getFilteredProjectList().get(0).getProjectName(),
                nameKeywordGenerator(" "));
        assertCommandFailure(command, model, expectedMessage);
    }

    @Test
    public void execute_multipleKeywords_multipleStaffsFound() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Project project = new ProjectBuilder().withName(VALID_NAME_AMY).build();

        Staff staffAmy = new StaffBuilder().withStaffName(VALID_NAME_AMY).build();
        Staff staffAmyBob = new StaffBuilder().withStaffName(VALID_NAME_AMY + VALID_NAME_BOB)
                .withStaffContact(VALID_STAFFCONTACT_ANDY).build();
        Staff staffBob = new StaffBuilder().withStaffName(VALID_NAME_BOB).build();

        project.getStaffList().add(staffAmy);
        project.getStaffList().add(staffAmyBob);
        project.getStaffList().add(staffBob);

        model.addProject(project);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        List<StaffName> staffNameList = Arrays.asList(new StaffName[] {staffAmy.getStaffName(),
            staffAmyBob.getStaffName(), staffBob.getStaffName()});

        String expectedMessage = String.format(FindStaffCommand.MESSAGE_FIND_STAFF_SUCCESS, staffNameList);

        FindStaffCommand findStaffCommand = new FindStaffCommand(project.getProjectName(),
                nameKeywordGenerator("Amy Bob"));

        assertCommandSuccess(findStaffCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_singleKeyword_multipleStaffsFound() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Project project = new ProjectBuilder().withName(VALID_NAME_AMY).build();

        Staff staffAmy = new StaffBuilder().withStaffName(VALID_NAME_AMY).build();
        Staff staffAmyBob = new StaffBuilder().withStaffName(VALID_NAME_AMY + VALID_NAME_BOB)
                .withStaffContact(VALID_STAFFCONTACT_ANDY).build();
        Staff staffBob = new StaffBuilder().withStaffName(VALID_NAME_BOB).build();

        project.getStaffList().add(staffAmy);
        project.getStaffList().add(staffAmyBob);
        project.getStaffList().add(staffBob);

        model.addProject(project);
        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());

        List<StaffName> staffNameList = Arrays.asList(new StaffName[] {staffAmy.getStaffName(),
                staffAmyBob.getStaffName()});

        String expectedMessage = String.format(FindStaffCommand.MESSAGE_FIND_STAFF_SUCCESS, staffNameList);

        FindStaffCommand findStaffCommand = new FindStaffCommand(project.getProjectName(),
                nameKeywordGenerator("Amy"));

        assertCommandSuccess(findStaffCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        ProjectName projectNameOne = new ProjectName(VALID_NAME_AMY);
        ProjectName projectNameTwo = new ProjectName(VALID_NAME_BOB);

        FindStaffCommand findStaffCommand = new FindStaffCommand(projectNameOne, nameKeywordGenerator("Amy"));

        // same object -> returns true
        assertTrue(findStaffCommand.equals(findStaffCommand));

        // same values -> returns true
        FindStaffCommand findStaffCommandCopy = new FindStaffCommand(projectNameOne,
                nameKeywordGenerator("Amy"));
        assertTrue(findStaffCommand.equals(findStaffCommandCopy));

        // different types -> returns false
        assertFalse(findStaffCommand.equals("Good day"));

        // null -> returns false
        assertFalse(findStaffCommand.equals(null));

        // different project name -> returns false
        FindStaffCommand findStaffCommandTwo = new FindStaffCommand(projectNameTwo, nameKeywordGenerator("Amy"));
        assertFalse(findStaffCommand.equals(findStaffCommandTwo));

        // different keywords -> returns false
        FindStaffCommand findStaffCommandThree = new FindStaffCommand(projectNameOne, nameKeywordGenerator("Bb"));
        assertFalse(findStaffCommand.equals(findStaffCommandThree));
    }

    /**
     * Parses {@code input} into a {@code List<String>} to use as
     * list of keywords for {@code FindStaffCommand}.
     */
    private List<String> nameKeywordGenerator(String input) {
        return Arrays.asList(input.split("\\s+"));
    }
}
