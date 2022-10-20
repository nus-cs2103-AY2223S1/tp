package tuthub.logic.commands;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static tuthub.commons.core.Messages.MESSAGE_TUTORS_LISTED_OVERVIEW;
import static tuthub.logic.commands.CommandTestUtil.assertCommandSuccess;
import static tuthub.testutil.TypicalTutors.CARL;
import static tuthub.testutil.TypicalTutors.ELLE;
import static tuthub.testutil.TypicalTutors.FIONA;
import static tuthub.testutil.TypicalTutors.getTypicalTuthub;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import tuthub.model.Model;
import tuthub.model.ModelManager;
import tuthub.model.UserPrefs;
import tuthub.model.tutor.NameContainsKeywordsPredicate;

/**
 * Contains integration tests (interaction with the Model) for {@code FindByNameCommand}.
 */
public class FindByNameCommandTest {
    private Model model = new ModelManager(getTypicalTuthub(), new UserPrefs());
    private Model expectedModel = new ModelManager(getTypicalTuthub(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsKeywordsPredicate firstPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("first"));
        NameContainsKeywordsPredicate secondPredicate =
                new NameContainsKeywordsPredicate(Collections.singletonList("second"));

        FindByNameCommand findFirstCommand = new FindByNameCommand(firstPredicate);
        FindByNameCommand findSecondCommand = new FindByNameCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        FindByNameCommand findFirstCommandCopy = new FindByNameCommand(firstPredicate);
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
        NameContainsKeywordsPredicate predicate = preparePredicate(" ");
        FindByNameCommand command = new FindByNameCommand(predicate);
        expectedModel.updateFilteredTutorList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredTutorList());
    }

    @Test
    public void execute_multipleKeywords_multipleTutorsFound() {
        String expectedMessage = String.format(MESSAGE_TUTORS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        FindByNameCommand command = new FindByNameCommand(predicate);
        expectedModel.updateFilteredTutorList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredTutorList());
    }

    @Test
    public void execute_multiplePartialKeywords_multipleTutorsFound() {
        String expectedMessage = String.format(MESSAGE_TUTORS_LISTED_OVERVIEW, 3);
        NameContainsKeywordsPredicate predicate = preparePredicate("urz lle unz");
        FindByNameCommand command = new FindByNameCommand(predicate);
        expectedModel.updateFilteredTutorList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(CARL, ELLE, FIONA), model.getFilteredTutorList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsKeywordsPredicate}.
     */
    private NameContainsKeywordsPredicate preparePredicate(String userInput) {
        return new NameContainsKeywordsPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
