package foodwhere.logic.commands;

import static foodwhere.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Arrays;
import java.util.Collections;

import org.junit.jupiter.api.Test;

import foodwhere.commons.core.Messages;
import foodwhere.model.Model;
import foodwhere.model.ModelManager;
import foodwhere.model.UserPrefs;
import foodwhere.model.review.NameContainsStallPredicate;
import foodwhere.testutil.TypicalReviews;
import foodwhere.testutil.TypicalStalls;

/**
 * Contains integration tests (interaction with the Model) for {@code RFindCommand}.
 */
public class RFindCommandTest {
    private Model model = new ModelManager(TypicalStalls.getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(TypicalStalls.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void equals() {
        NameContainsStallPredicate firstPredicate =
                new NameContainsStallPredicate(Collections.singletonList("first"));
        NameContainsStallPredicate secondPredicate =
                new NameContainsStallPredicate(Collections.singletonList("second"));

        RFindCommand findFirstCommand = new RFindCommand(firstPredicate);
        RFindCommand findSecondCommand = new RFindCommand(secondPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));

        // same values -> returns true
        RFindCommand findFirstCommandCopy = new RFindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));

        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));

        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different reviews -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand));

    }

    @Test
    public void execute_zeroKeywords_noReviewFound() {
        String expectedMessage = String.format(Messages.MESSAGE_REVIEWS_LISTED_OVERVIEW, 0);
        NameContainsStallPredicate predicate = preparePredicate(" ");
        RFindCommand command = new RFindCommand(predicate);
        expectedModel.updateFilteredReviewList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredReviewList());
    }

    @Test
    public void execute_multipleKeywords_multipleStallsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_REVIEWS_LISTED_OVERVIEW, 3);
        NameContainsStallPredicate predicate = preparePredicate("Kurz Elle Kunz");
        RFindCommand command = new RFindCommand(predicate);
        expectedModel.updateFilteredReviewList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalReviews.CARL,
                TypicalReviews.ELLE,
                TypicalReviews.FIONA),
                model.getFilteredReviewList());
    }

    /**
     * Parses {@code userInput} into a {@code NameContainsStallPredicate}.
     */
    private NameContainsStallPredicate preparePredicate(String userInput) {
        return new NameContainsStallPredicate(Arrays.asList(userInput.split("\\s+")));
    }
}
