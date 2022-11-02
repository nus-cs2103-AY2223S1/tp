package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.commons.core.Messages.MESSAGE_STAFFS_LISTED_PLURAL_OVERVIEW;
import static seedu.address.commons.core.Messages.MESSAGE_STAFFS_LISTED_SINGULAR_OVERVIEW;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.address.testutil.TypicalAddressBook.getTypicalAddressBook;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.address.model.Model;
import seedu.address.model.ModelManager;
import seedu.address.model.UserPrefs;
import seedu.address.model.project.Project;
import seedu.address.model.staff.Staff;
import seedu.address.model.staff.StaffNameContainsKeywordsPredicate;
import seedu.address.testutil.ProjectBuilder;
import seedu.address.testutil.StaffBuilder;

public class FindStaffCommandTest {
    @Test
    public void execute_zeroKeywords_noStaffFound() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        String expectedMessage = String.format(MESSAGE_STAFFS_LISTED_PLURAL_OVERVIEW, 0);
        StaffNameContainsKeywordsPredicate predicate = preparePredicate("Alice");
        FindStaffCommand findStaffCommand = new FindStaffCommand(predicate);
        expectedModel.updateFilteredStaffList(predicate);
        assertCommandSuccess(findStaffCommand, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStaffList());
    }

    @Test
    public void execute_staffNameWithSpaces_staffFound() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Staff dummyStaff = new StaffBuilder().withStaffName("Alice Lau").build();
        Project dummyProject = new ProjectBuilder().withName(VALID_NAME_AMY).build();
        dummyProject.getStaffList().add(dummyStaff);

        // set active project staff list to dummy project's staff list
        model.setFilteredStaffList(dummyProject);
        expectedModel.setFilteredStaffList(dummyProject);

        StaffNameContainsKeywordsPredicate predicate = preparePredicate("Alice Lau");
        FindStaffCommand findStaffCommand =
                new FindStaffCommand(predicate);

        expectedModel.updateFilteredStaffList(predicate);
        String expectedMessage = String.format(MESSAGE_STAFFS_LISTED_SINGULAR_OVERVIEW, 1);
        assertCommandSuccess(findStaffCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_staffsWithSameFirstName_staffFound() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Staff dummyStaff = new StaffBuilder().withStaffName("Alice Lau").build();
        Staff dummyStaffTwo = new StaffBuilder().withStaffName("Alice Ng").build();
        Project dummyProject = new ProjectBuilder().withName(VALID_NAME_AMY).build();
        dummyProject.getStaffList().add(dummyStaff);
        dummyProject.getStaffList().add(dummyStaffTwo);

        // set active project staff list to dummy project's staff list
        model.setFilteredStaffList(dummyProject);
        expectedModel.setFilteredStaffList(dummyProject);

        StaffNameContainsKeywordsPredicate predicate = preparePredicate("Alice");
        FindStaffCommand findStaffCommand =
                new FindStaffCommand(predicate);

        expectedModel.updateFilteredStaffList(predicate);
        String expectedMessage = String.format(MESSAGE_STAFFS_LISTED_PLURAL_OVERVIEW, 2);
        assertCommandSuccess(findStaffCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_staffNameDifferentCase_staffFound() {
        Model model = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalAddressBook(), new UserPrefs());
        Staff dummyStaff = new StaffBuilder().withStaffName("Alice Lau").build();
        Project dummyProject = new ProjectBuilder().withName(VALID_NAME_AMY).build();
        dummyProject.getStaffList().add(dummyStaff);

        // set active project staff list to dummy project's staff list
        model.setFilteredStaffList(dummyProject);
        expectedModel.setFilteredStaffList(dummyProject);

        StaffNameContainsKeywordsPredicate predicate = preparePredicate("alice lau");
        FindStaffCommand findStaffCommand =
                new FindStaffCommand(predicate);

        expectedModel.updateFilteredStaffList(predicate);
        String expectedMessage = String.format(MESSAGE_STAFFS_LISTED_SINGULAR_OVERVIEW, 1);
        assertCommandSuccess(findStaffCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void equals() {

        FindStaffCommand findStaffCommand = new FindStaffCommand(
                preparePredicate(VALID_NAME_AMY));

        // same object -> returns true
        assertTrue(findStaffCommand.equals(findStaffCommand));

        // same values -> returns true
        FindStaffCommand findStaffCommandCopy = new FindStaffCommand(preparePredicate(VALID_NAME_AMY));
        assertTrue(findStaffCommand.equals(findStaffCommandCopy));

        // different types -> returns false
        assertFalse(findStaffCommand.equals("Good day"));

        // null -> returns false
        assertFalse(findStaffCommand.equals(null));

        // different keyword -> returns false
        FindStaffCommand findStaffCommandTwo = new FindStaffCommand(preparePredicate(VALID_NAME_BOB));
        assertFalse(findStaffCommand.equals(findStaffCommandTwo));

    }

    /**
     * Parses {@code userInput} into a {@code StaffNameContainsKeywordsPredicate}.
     */
    private StaffNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new StaffNameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
