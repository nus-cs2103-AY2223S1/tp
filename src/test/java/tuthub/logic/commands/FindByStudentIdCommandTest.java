package tuthub.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tuthub.commons.core.Messages.MESSAGE_TUTORS_LISTED_OVERVIEW;
import static tuthub.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tuthub.testutil.TypicalTutors.ALICE;
import static tuthub.testutil.TypicalTutors.CARL;
import static tuthub.testutil.TypicalTutors.getTypicalTuthub;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import tuthub.model.Model;
import tuthub.model.ModelManager;
import tuthub.model.UserPrefs;
import tuthub.model.tutor.StudentIdContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindByStudentIdCommand}.
 */
public class FindByStudentIdCommandTest {
    private Model model = new ModelManager(getTypicalTuthub(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTuthub(), new UserPrefs());

    @Test
    public void equals() {
        StudentIdContainsKeywordsPredicate firstPredicate =
                new StudentIdContainsKeywordsPredicate(Collections.singletonList("first"));
        StudentIdContainsKeywordsPredicate secondPredicate =
                new StudentIdContainsKeywordsPredicate(Collections.singletonList("second"));

        FindByStudentIdCommand findFirstCommand = new FindByStudentIdCommand(firstPredicate);
        FindByStudentIdCommand findSecondCommand = new FindByStudentIdCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindByStudentIdCommand findFirstCommandCopy = new FindByStudentIdCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different tutor -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));
    }

    @Test
    public void execute_zeroKeywords_noTutorFound() {
        String expectedMessage = String.format(MESSAGE_TUTORS_LISTED_OVERVIEW, 0);
        StudentIdContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindByStudentIdCommand command = new FindByStudentIdCommand(predicate);
        expectedModel.updateFilteredTutorList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTutorList());
    }

    @Test
    public void execute_multipleKeywords_multipleTutorsFound() {
        String expectedMessage = String.format(MESSAGE_TUTORS_LISTED_OVERVIEW, 2);
        StudentIdContainsKeywordsPredicate predicate = preparePredicate("A1234569L A9875647U");
        FindByStudentIdCommand command = new FindByStudentIdCommand(predicate);
        expectedModel.updateFilteredTutorList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, CARL), model.getFilteredTutorList());
    }

    @Test
    public void execute_multiplePartialKeywords_multipleTutorsFound() {
        String expectedMessage = String.format(MESSAGE_TUTORS_LISTED_OVERVIEW, 2);
        StudentIdContainsKeywordsPredicate predicate = preparePredicate("569L A9875");
        FindByStudentIdCommand command = new FindByStudentIdCommand(predicate);
        expectedModel.updateFilteredTutorList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(ALICE, CARL), model.getFilteredTutorList());
    }

    /**
     * Parses {@code userInput} into a {@code StudentIdContainsKeywordsPredicate}.
     */
    private StudentIdContainsKeywordsPredicate preparePredicate(String userInput) {
        return new StudentIdContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
