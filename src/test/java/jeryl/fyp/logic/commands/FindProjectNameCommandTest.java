package jeryl.fyp.logic.commands;

import static jeryl.fyp.commons.core.Messages.MESSAGE_PROJECTS_LISTED_OVERVIEW;
import static jeryl.fyp.logic.commands.CommandTestUtil.assertCommandSuccess;
import static jeryl.fyp.testutil.TypicalStudents.ALICE;
import static jeryl.fyp.testutil.TypicalStudents.CARL;
import static jeryl.fyp.testutil.TypicalStudents.DANIEL;
import static jeryl.fyp.testutil.TypicalStudents.ELLE;
import static jeryl.fyp.testutil.TypicalStudents.FIONA;
import static jeryl.fyp.testutil.TypicalStudents.getTypicalFypManager;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import jeryl.fyp.model.Model;
import jeryl.fyp.model.ModelManager;
import jeryl.fyp.model.UserPrefs;
import jeryl.fyp.model.student.ProjectNameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindProjectNameCommand}.
 */
public class FindProjectNameCommandTest {
    private Model model = new ModelManager(getTypicalFypManager(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalFypManager(), new UserPrefs());

    @Test
    public void equals() {
        ProjectNameContainsKeywordsPredicate firstPredicate =
                new ProjectNameContainsKeywordsPredicate(Collections.singletonList("first"));
        ProjectNameContainsKeywordsPredicate secondPredicate =
                new ProjectNameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindProjectNameCommand findFirstCommand = new FindProjectNameCommand(firstPredicate);
        FindProjectNameCommand findSecondCommand = new FindProjectNameCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindProjectNameCommand findFirstCommandCopy = new FindProjectNameCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different student -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noStudentFound() {
        String expectedMessage = String.format(MESSAGE_PROJECTS_LISTED_OVERVIEW, 0);
        ProjectNameContainsKeywordsPredicate predicate = preparePredicate("abc");
        FindProjectNameCommand command = new FindProjectNameCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    @Test
    public void execute_singleKeyword_multipleStudentsFound() {
        String expectedMessage = String.format(MESSAGE_PROJECTS_LISTED_OVERVIEW, 4);
        ProjectNameContainsKeywordsPredicate predicate = preparePredicate("in");
        FindProjectNameCommand command = new FindProjectNameCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, DANIEL, ELLE, FIONA), model.getFilteredStudentList());
    }

    @Test
    public void execute_multipleKeywords_noStudentsFound() {
        String expectedMessage = String.format(MESSAGE_PROJECTS_LISTED_OVERVIEW, 0);
        ProjectNameContainsKeywordsPredicate predicate = preparePredicate("chemiStRy /  Blockchain");
        FindProjectNameCommand command = new FindProjectNameCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    @Test
    public void execute_multipleKeywords_multipleStudentsFound() {
        String expectedMessage = String.format(MESSAGE_PROJECTS_LISTED_OVERVIEW, 3);
        ProjectNameContainsKeywordsPredicate predicate = preparePredicate("de/ woRld/ Mathematics ");
        FindProjectNameCommand command = new FindProjectNameCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, DANIEL, FIONA), model.getFilteredStudentList());
    }

    @Test
    public void execute_duplicateKeywords_multipleStudentsFound() {
        String expectedMessage = String.format(MESSAGE_PROJECTS_LISTED_OVERVIEW, 2);
        ProjectNameContainsKeywordsPredicate predicate = preparePredicate("de/ woRld/ de ");
        FindProjectNameCommand command = new FindProjectNameCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, FIONA), model.getFilteredStudentList());
    }
    /**
     * Parses {@code userInput} into a {@code ProjectNameContainsKeywordsPredicate}.
     */
    private ProjectNameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new ProjectNameContainsKeywordsPredicate(Arrays.asList(userInput.split("/")));
    }
}
