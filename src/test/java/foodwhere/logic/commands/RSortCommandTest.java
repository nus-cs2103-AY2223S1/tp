package foodwhere.logic.commands;

import static foodwhere.logic.commands.CommandTestUtil.assertCommandSuccess;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import foodwhere.model.Model;
import foodwhere.model.ModelManager;
import foodwhere.model.UserPrefs;
import foodwhere.model.review.comparator.ReviewsComparatorList;
import foodwhere.testutil.TypicalStalls;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RSortCommand.
 */
public class RSortCommandTest {

    private Model model = new ModelManager(TypicalStalls.getTypicalAddressBook(), new UserPrefs());
    private Model expectedModel = new ModelManager(model.getAddressBook(), new UserPrefs());

    @Test
    public void execute_nameCriterion_showsSortedListByName() {
        ReviewsComparatorList comparator = prepareComparator("NAME");
        expectedModel.sortReviews(comparator.getComparator());
        String expectedMessage = String.format(RSortCommand.MESSAGE_SUCCESS, comparator.getCriteria());
        assertCommandSuccess(new RSortCommand(comparator), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_reversedNameCriterion_showsSortedListByReversedName() {
        ReviewsComparatorList comparator = prepareComparator("REVERSEDNAME");
        expectedModel.sortReviews(comparator.getComparator());
        String expectedMessage = String.format(RSortCommand.MESSAGE_SUCCESS, comparator.getCriteria());
        assertCommandSuccess(new RSortCommand(comparator), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_dateCriterion_showsSortedListByDate() {
        ReviewsComparatorList comparator = prepareComparator("DATE");
        expectedModel.sortReviews(comparator.getComparator());
        String expectedMessage = String.format(RSortCommand.MESSAGE_SUCCESS, comparator.getCriteria());
        assertCommandSuccess(new RSortCommand(comparator), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_reversedDateCriterion_showsSortedListByReversedDate() {
        ReviewsComparatorList comparator = prepareComparator("REVERSEDDATE");
        expectedModel.sortReviews(comparator.getComparator());
        String expectedMessage = String.format(RSortCommand.MESSAGE_SUCCESS, comparator.getCriteria());
        assertCommandSuccess(new RSortCommand(comparator), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_ratingCriterion_showsSortedListByRating() {
        ReviewsComparatorList comparator = prepareComparator("RATING");
        expectedModel.sortReviews(comparator.getComparator());
        String expectedMessage = String.format(RSortCommand.MESSAGE_SUCCESS, comparator.getCriteria());
        assertCommandSuccess(new RSortCommand(comparator), model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_reversedRatingCriterion_showsSortedListByReversedRating() {
        ReviewsComparatorList comparator = prepareComparator("REVERSEDRATING");
        expectedModel.sortReviews(comparator.getComparator());
        String expectedMessage = String.format(RSortCommand.MESSAGE_SUCCESS, comparator.getCriteria());
        assertCommandSuccess(new RSortCommand(comparator), model, expectedMessage, expectedModel);
    }

    @Test
    public void equals() {
        RSortCommand sortByName = new RSortCommand(ReviewsComparatorList.valueOf("NAME"));
        RSortCommand sortByDate = new RSortCommand(ReviewsComparatorList.valueOf("DATE"));

        // same object -> returns true
        assertTrue(sortByName.equals(sortByName));

        // same values -> returns true
        RSortCommand sortByNameCopy = new RSortCommand(ReviewsComparatorList.valueOf("NAME"));
        assertTrue(sortByName.equals(sortByNameCopy));

        // different types -> returns false
        assertFalse(sortByName.equals(1));

        // null -> returns false
        assertFalse(sortByName.equals(null));

        // different criterion -> returns false
        assertFalse(sortByName.equals(sortByDate));
    }

    /**
     * Parses {@code userInput} into a {@code ReviewsComparatorList}.
     */
    private ReviewsComparatorList prepareComparator(String userInput) {
        return ReviewsComparatorList.valueOf(userInput);
    }
}
