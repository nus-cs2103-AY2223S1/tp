package tuthub.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tuthub.commons.core.Messages.MESSAGE_TUTORS_LISTED_OVERVIEW;
import static tuthub.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tuthub.testutil.TypicalTutors.LOPEZ;
import static tuthub.testutil.TypicalTutors.NATHAN;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import tuthub.model.Model;
import tuthub.model.ModelManager;
import tuthub.model.Tuthub;
import tuthub.model.UserPrefs;
import tuthub.model.tutor.TagContainsKeywordsPredicate;
import tuthub.model.tutor.Tutor;

/**
 * Contains integration tests (interaction with the model) for {@code FindByTagCommand}.
 */
public class FindByTagCommandTest {
    private List<Tutor> testTaList = Arrays.asList(LOPEZ, NATHAN);
    private Model model = new ModelManager(getTestTaTuthub(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTestTaTuthub(), new UserPrefs());

    @Test
    public void equals() {
        TagContainsKeywordsPredicate firstPredicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("first"));
        TagContainsKeywordsPredicate secondPredicate =
                new TagContainsKeywordsPredicate(Collections.singletonList("second"));

        FindByTagCommand findFirstCommand = new FindByTagCommand(firstPredicate);
        FindByTagCommand findSecondCommand = new FindByTagCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindByTagCommand findFirstCommandCopy = new FindByTagCommand(firstPredicate);
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
        TagContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindByTagCommand command = new FindByTagCommand(predicate);
        expectedModel.updateFilteredTutorList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTutorList());
    }

    @Test
    public void execute_multipleKeywords_multipleTutorsFound() {
        String expectedMessage = String.format(MESSAGE_TUTORS_LISTED_OVERVIEW, 2);
        TagContainsKeywordsPredicate predicate = preparePredicate("friends owesMoney");
        FindByTagCommand command = new FindByTagCommand(predicate);
        expectedModel.updateFilteredTutorList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(LOPEZ, NATHAN), model.getFilteredTutorList());
    }

    @Test
    public void execute_multiplePartialKeywords_multipleTutorsFound() {
        String expectedMessage = String.format(MESSAGE_TUTORS_LISTED_OVERVIEW, 2);
        TagContainsKeywordsPredicate predicate = preparePredicate("iEnd EsmoN");
        FindByTagCommand command = new FindByTagCommand(predicate);
        expectedModel.updateFilteredTutorList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(LOPEZ, NATHAN), model.getFilteredTutorList());
    }

    private Tuthub getTestTaTuthub() {
        Tuthub testTaTuthub = new Tuthub();
        for (Tutor tutor : testTaList) {
            testTaTuthub.addTutor(tutor);
        }
        return testTaTuthub;
    }

    /**
     * Parses {@code userInput} into a {@code TagContainsKeywordsPredicate}.
     */
    private TagContainsKeywordsPredicate preparePredicate(String userInput) {
        return new TagContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
