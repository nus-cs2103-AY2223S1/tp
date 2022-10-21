package foodwhere.logic.commands;

import static foodwhere.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.Test;

import foodwhere.commons.core.Messages;
import foodwhere.model.Model;
import foodwhere.model.ModelManager;
import foodwhere.model.UserPrefs;
import foodwhere.model.commons.Name;
import foodwhere.model.commons.Tag;
import foodwhere.model.review.ReviewContainsKeywordsPredicate;
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
        ReviewContainsKeywordsPredicate firstPredicate =
                new ReviewContainsKeywordsPredicate(Collections.singleton(new Name("first")),
                        Collections.singleton(new Tag("first")));
        ReviewContainsKeywordsPredicate secondPredicate =
                new ReviewContainsKeywordsPredicate(Collections.singleton(new Name("second")),
                        Collections.singleton(new Tag("first")));

        ReviewContainsKeywordsPredicate thirdPredicate =
                new ReviewContainsKeywordsPredicate(Collections.singleton(new Name("first")),
                        Collections.singleton(new Tag("second")));
        ReviewContainsKeywordsPredicate fourthPredicate =
                new ReviewContainsKeywordsPredicate(Collections.singleton(new Name("second")),
                        Collections.singleton(new Tag("second")));

        RFindCommand findFirstCommand = new RFindCommand(firstPredicate);
        RFindCommand findSecondCommand = new RFindCommand(secondPredicate);
        RFindCommand findThirdCommand = new RFindCommand(thirdPredicate);
        RFindCommand findFourthCommand = new RFindCommand(fourthPredicate);

        // same object -> returns true
        assertTrue(findFirstCommand.equals(findFirstCommand));
        // same values -> returns true
        RFindCommand findFirstCommandCopy = new RFindCommand(firstPredicate);
        assertTrue(findFirstCommand.equals(findFirstCommandCopy));
        // different types -> returns false
        assertFalse(findFirstCommand.equals(1));
        // null -> returns false
        assertFalse(findFirstCommand.equals(null));

        // different name or tag -> returns false
        assertFalse(findFirstCommand.equals(findSecondCommand)); //different names
        assertFalse(findFirstCommand.equals(findThirdCommand)); //different tags
        assertFalse(findFirstCommand.equals(findFourthCommand)); //different names and tags
    }

    @Test
    public void execute_zeroKeywords_noReviewFound() {
        String expectedMessage = String.format(Messages.MESSAGE_REVIEWS_LISTED_OVERVIEW, 0);
        ReviewContainsKeywordsPredicate predicate = preparePredicate(" ");
        RFindCommand command = new RFindCommand(predicate);
        expectedModel.updateFilteredReviewList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Collections.emptyList(), model.getFilteredReviewList());
    }

    @Test
    public void execute_multipleKeywords_multipleStallsFound() {
        String expectedMessage = String.format(Messages.MESSAGE_REVIEWS_LISTED_OVERVIEW, 3);
        ReviewContainsKeywordsPredicate predicate = preparePredicate("Kurz Elle Kunz");
        RFindCommand command = new RFindCommand(predicate);
        expectedModel.updateFilteredReviewList(predicate);
        assertCommandSuccess(command, model, expectedMessage, expectedModel);
        assertEquals(Arrays.asList(TypicalReviews.CARL,
                TypicalReviews.ELLE,
                TypicalReviews.FIONA),
                model.getFilteredReviewList());
    }

    /**
     * Parses {@code userInput} into a {@code ReviewContainsKeywordsPredicate}.
     */
    private ReviewContainsKeywordsPredicate preparePredicate(String userInput) {
        List<String> lst = Arrays.asList(userInput.split("\\s+"));
        List<Name> nameList = new ArrayList<>();
        for (String s : lst) {
            nameList.add(new Name(s));
        }
        return new ReviewContainsKeywordsPredicate(nameList, Collections.emptyList());
    }
}
