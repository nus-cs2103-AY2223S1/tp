package jeryl.fyp.logic.commands;

import static jeryl.fyp.commons.core.Messages.MESSAGE_PROJECTS_LISTED_OVERVIEW;
import static jeryl.fyp.logic.commands.CommandTestUtil.assertCommandSuccess;
import static jeryl.fyp.testutil.TypicalStudents.CARL;
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
    public void execute_multipleKeywords_multipleStudentsFound() {
        String expectedMessage = String.format(MESSAGE_PROJECTS_LISTED_OVERVIEW, 2);
        StudentIdContainsKeywordsPredicate predicate = preparePredicate("de/ woRld/ de ");
        FindStudentIdCommand command = new FindStudentIdCommand(predicate);
        expectedModel.updateFilteredStudentList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, FIONA), model.getFilteredStudentList());
    }

    /**
     * Parses {@code userInput} into a {@code StudentIdContainsKeywordsPredicate}.
     */
    private StudentIdContainsKeywordsPredicate preparePredicate(String userInput) {
        return new StudentIdContainsKeywordsPredicate(Arrays.asList(userInput.split("/")));
    }
}
