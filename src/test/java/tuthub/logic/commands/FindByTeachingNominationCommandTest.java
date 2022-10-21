package tuthub.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tuthub.commons.core.Messages.MESSAGE_TUTORS_LISTED_OVERVIEW;
import static tuthub.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tuthub.testutil.TypicalTutors.JACKSON;
import static tuthub.testutil.TypicalTutors.KEN;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import tuthub.model.Model;
import tuthub.model.ModelManager;
import tuthub.model.Tuthub;
import tuthub.model.UserPrefs;
import tuthub.model.tutor.TeachingNominationContainKeywordsPredicate;
import tuthub.model.tutor.Tutor;

/**
 * Contains integration tests (interaction with the model) for {@code FindByTeachingNominationCommand}
 */
public class FindByTeachingNominationCommandTest {
    private List<Tutor> testTaList = Arrays.asList(JACKSON, KEN);
    private Model model = new ModelManager(getTestTaTuthub(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTestTaTuthub(), new UserPrefs());

    @Test
    public void equals() {
        TeachingNominationContainKeywordsPredicate firstPredicate =
                new TeachingNominationContainKeywordsPredicate(Collections.singletonList("first"));
        TeachingNominationContainKeywordsPredicate secondPredicate =
                new TeachingNominationContainKeywordsPredicate(Collections.singletonList("second"));

        FindByTeachingNominationCommand findFirstCommand = new FindByTeachingNominationCommand(firstPredicate);
        FindByTeachingNominationCommand findSecondCommand = new FindByTeachingNominationCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindByTeachingNominationCommand findFirstCommandCopy = new FindByTeachingNominationCommand(firstPredicate);
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
        TeachingNominationContainKeywordsPredicate predicate = preparePredicate(" ");
        FindByTeachingNominationCommand command = new FindByTeachingNominationCommand(predicate);
        expectedModel.updateFilteredTutorList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTutorList());
    }

    @Test
    public void execute_multipleKeywords_multipleTutorsFound() {
        String expectedMessage = String.format(MESSAGE_TUTORS_LISTED_OVERVIEW, 2);
        TeachingNominationContainKeywordsPredicate predicate = preparePredicate("1 2");
        FindByTeachingNominationCommand command = new FindByTeachingNominationCommand(predicate);
        expectedModel.updateFilteredTutorList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(JACKSON, KEN), model.getFilteredTutorList());
    }

    private Tuthub getTestTaTuthub() {
        Tuthub testTaTuthub = new Tuthub();
        for (Tutor tutor : testTaList) {
            testTaTuthub.addTutor(tutor);
        }
        return testTaTuthub;
    }

    /**
     * Parses {@code userInput} into a  {@code YearContainsKeywordsPredicate}
     */
    private TeachingNominationContainKeywordsPredicate preparePredicate(String userInput) {
        return new TeachingNominationContainKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
