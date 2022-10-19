package tuthub.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tuthub.commons.core.Messages.MESSAGE_TUTORS_LISTED_OVERVIEW;
import static tuthub.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tuthub.testutil.TypicalTutors.HOON;
import static tuthub.testutil.TypicalTutors.IDA;
import static tuthub.testutil.TypicalTutors.JACKSON;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import tuthub.model.Model;
import tuthub.model.ModelManager;
import tuthub.model.Tuthub;
import tuthub.model.UserPrefs;
import tuthub.model.tutor.Tutor;
import tuthub.model.tutor.YearContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the model) for {@code FindByYearCommand}
 */
public class FindByYearCommandTest {
    private List<Tutor> testTaList = Arrays.asList(IDA, HOON, JACKSON);
    private Model model = new ModelManager(getTestTaTuthub(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTestTaTuthub(), new UserPrefs());

    @Test
    public void equals() {
        YearContainsKeywordsPredicate firstPredicate =
                new YearContainsKeywordsPredicate(Collections.singletonList("first"));
        YearContainsKeywordsPredicate secondPredicate =
                new YearContainsKeywordsPredicate(Collections.singletonList("second"));

        FindByYearCommand findFirstCommand = new FindByYearCommand(firstPredicate);
        FindByYearCommand findSecondCommand = new FindByYearCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindByYearCommand findFirstCommandCopy = new FindByYearCommand(firstPredicate);
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
        YearContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindByYearCommand command = new FindByYearCommand(predicate);
        expectedModel.updateFilteredTutorList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTutorList());
    }

    @Test
    public void execute_multipleKeywords_multipleTutorsFound() {
        String expectedMessage = String.format(MESSAGE_TUTORS_LISTED_OVERVIEW, 2);
        YearContainsKeywordsPredicate predicate = preparePredicate("3 4");
        FindByYearCommand command = new FindByYearCommand(predicate);
        expectedModel.updateFilteredTutorList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(IDA, HOON), model.getFilteredTutorList());
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
    private YearContainsKeywordsPredicate preparePredicate(String userInput) {
        return new YearContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
