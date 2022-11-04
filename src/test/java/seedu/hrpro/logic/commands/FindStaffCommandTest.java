package seedu.hrpro.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.hrpro.commons.core.Messages.MESSAGE_STAFFS_LISTED_PLURAL_OVERVIEW;
import static seedu.hrpro.commons.core.Messages.MESSAGE_STAFFS_LISTED_SINGULAR_OVERVIEW;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.hrpro.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.hrpro.logic.commands.CommandTestUtil.assertCommandSuccess;
import static seedu.hrpro.testutil.TypicalHrPro.getTypicalHrPro;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import seedu.hrpro.model.Model;
import seedu.hrpro.model.ModelManager;
import seedu.hrpro.model.UserPrefs;
import seedu.hrpro.model.project.Project;
import seedu.hrpro.model.staff.Staff;
import seedu.hrpro.model.staff.StaffNameContainsKeywordsPredicate;
import seedu.hrpro.testutil.ProjectBuilder;
import seedu.hrpro.testutil.StaffBuilder;

public class FindStaffCommandTest {
    @Test
    public void execute_zeroKeywords_noStaffFound() {
        Model model = new ModelManager(getTypicalHrPro(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalHrPro(), new UserPrefs());
        String expectedMessage = String.format(MESSAGE_STAFFS_LISTED_PLURAL_OVERVIEW, 0);
        StaffNameContainsKeywordsPredicate predicate = preparePredicate("Alice");
        FindStaffCommand findStaffCommand = new FindStaffCommand(predicate);
        expectedModel.updateFilteredStaffList(predicate);
        assertCommandSuccess(findStaffCommand, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStaffList());
    }

    @Test
    public void execute_staffNameWithSpaces_staffFound() {
        Model model = new ModelManager(getTypicalHrPro(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalHrPro(), new UserPrefs());
        Staff dummyStaff = new StaffBuilder().withStaffName("Alice Lau").build();
        Project dummyProject = new ProjectBuilder().withName(VALID_NAME_AMY).build();
        dummyProject.getStaffList().add(dummyStaff);

        // set active project staff list to dummy project's staff list
        model.setFilteredStaffList(dummyProject.getStaffList());
        expectedModel.setFilteredStaffList(dummyProject.getStaffList());

        StaffNameContainsKeywordsPredicate predicate = preparePredicate("Alice Lau");
        FindStaffCommand findStaffCommand =
                new FindStaffCommand(predicate);

        expectedModel.updateFilteredStaffList(predicate);
        String expectedMessage = String.format(MESSAGE_STAFFS_LISTED_SINGULAR_OVERVIEW, 1);
        assertCommandSuccess(findStaffCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_staffsWithSameFirstName_staffFound() {
        Model model = new ModelManager(getTypicalHrPro(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalHrPro(), new UserPrefs());
        Staff dummyStaff = new StaffBuilder().withStaffName("Alice Lau").build();
        Staff dummyStaffTwo = new StaffBuilder().withStaffName("Alice Ng").build();
        Project dummyProject = new ProjectBuilder().withName(VALID_NAME_AMY).build();
        dummyProject.getStaffList().add(dummyStaff);
        dummyProject.getStaffList().add(dummyStaffTwo);

        // set active project staff list to dummy project's staff list
        model.setFilteredStaffList(dummyProject.getStaffList());
        expectedModel.setFilteredStaffList(dummyProject.getStaffList());

        StaffNameContainsKeywordsPredicate predicate = preparePredicate("Alice");
        FindStaffCommand findStaffCommand =
                new FindStaffCommand(predicate);

        expectedModel.updateFilteredStaffList(predicate);
        String expectedMessage = String.format(MESSAGE_STAFFS_LISTED_PLURAL_OVERVIEW, 2);
        assertCommandSuccess(findStaffCommand, model, expectedMessage, expectedModel);

    }

    @Test
    public void execute_staffNameDifferentCase_staffFound() {
        Model model = new ModelManager(getTypicalHrPro(), new UserPrefs());
        Model expectedModel = new ModelManager(getTypicalHrPro(), new UserPrefs());
        Staff dummyStaff = new StaffBuilder().withStaffName("Alice Lau").build();
        Project dummyProject = new ProjectBuilder().withName(VALID_NAME_AMY).build();
        dummyProject.getStaffList().add(dummyStaff);

        // set active project staff list to dummy project's staff list
        model.setFilteredStaffList(dummyProject.getStaffList());
        expectedModel.setFilteredStaffList(dummyProject.getStaffList());

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
