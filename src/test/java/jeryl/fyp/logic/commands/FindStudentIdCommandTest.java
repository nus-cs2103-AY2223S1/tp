package jeryl.fyp.logic.commands;

import static jeryl.fyp.commons.core.Messages.MESSAGE_PROJECTS_LISTED_OVERVIEW;
import static jeryl.fyp.logic.commands.CommandTestUtil.assertCommandSuccess;
import static jeryl.fyp.testutil.TypicalStudents.ALICE;
import static jeryl.fyp.testutil.TypicalStudents.BENSON;
import static jeryl.fyp.testutil.TypicalStudents.CARL;
import static jeryl.fyp.testutil.TypicalStudents.DANIEL;
import static jeryl.fyp.testutil.TypicalStudents.ELLE;
import static jeryl.fyp.testutil.TypicalStudents.FIONA;
import static jeryl.fyp.testutil.TypicalStudents.GEORGE;
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
import jeryl.fyp.model.student.StudentIdContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindStudentIdCommand}.
 */
public class FindStudentIdCommandTest {
    private Model model = new ModelManager(getTypicalFypManager(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalFypManager(), new UserPrefs());

    @Test
    public void equals() {
        StudentIdContainsKeywordsPredicate firstPredicate =
                new StudentIdContainsKeywordsPredicate(Collections.singletonList("first"));
        StudentIdContainsKeywordsPredicate secondPredicate =
                new StudentIdContainsKeywordsPredicate(Collections.singletonList("second"));

        FindStudentIdCommand findFirstCommand = new FindStudentIdCommand(firstPredicate);
        FindStudentIdCommand findSecondCommand = new FindStudentIdCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindStudentIdCommand findFirstCommandCopy = new FindStudentIdCommand(firstPredicate);
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
        StudentIdContainsKeywordsPredicate predicate = preparePredicate("abc");
        FindStudentIdCommand command = new FindStudentIdCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }
    @Test
    public void execute_singleKeyword_multipleStudentsFound() {
        String expectedMessage = String.format(MESSAGE_PROJECTS_LISTED_OVERVIEW, 3);
        StudentIdContainsKeywordsPredicate predicate = preparePredicate("14");
        FindStudentIdCommand command = new FindStudentIdCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ELLE, FIONA, GEORGE), model.getFilteredStudentList());
    }

    @Test
    public void execute_multipleKeywords_noStudentsFound() {
        String expectedMessage = String.format(MESSAGE_PROJECTS_LISTED_OVERVIEW, 0);
        StudentIdContainsKeywordsPredicate predicate = preparePredicate("123 /  456 / 7890");
        FindStudentIdCommand command = new FindStudentIdCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredStudentList());
    }

    @Test
    public void execute_multipleKeywords_multipleStudentsFound() {
        String expectedMessage = String.format(MESSAGE_PROJECTS_LISTED_OVERVIEW, 2);
        StudentIdContainsKeywordsPredicate predicate = preparePredicate("2427  / 563");
        FindStudentIdCommand command = new FindStudentIdCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, FIONA), model.getFilteredStudentList());
    }

    @Test
    public void execute_duplicateKeywords_singleStudentFound() {
        String expectedMessage = String.format(MESSAGE_PROJECTS_LISTED_OVERVIEW, 1);
        StudentIdContainsKeywordsPredicate predicate = preparePredicate("2427  / 2427");
        FindStudentIdCommand command = new FindStudentIdCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(FIONA), model.getFilteredStudentList());
    }

    // to address Issue #176, where "find -i/a" will function the same as List
    // since all StudentId begins with "A"
    @Test
    public void execute_keywordContainsA_allStudentsFound() {
        String expectedMessage = String.format(MESSAGE_PROJECTS_LISTED_OVERVIEW, 7);
        StudentIdContainsKeywordsPredicate predicate = preparePredicate("a");
        FindStudentIdCommand command = new FindStudentIdCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE), model.getFilteredStudentList());
    }

    /**
     * Parses {@code userInput} into a {@code StudentIdContainsKeywordsPredicate}.
     */
    private StudentIdContainsKeywordsPredicate preparePredicate(String userInput) {
        return new StudentIdContainsKeywordsPredicate(Arrays.asList(userInput.split("/")));
    }
}
