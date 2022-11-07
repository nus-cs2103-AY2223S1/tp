package foodwhere.logic.commands;

import static foodwhere.logic.commands.CommandTestUtil.assertCommandSuccess;
import static foodwhere.logic.commands.RAddCommand.MESSAGE_INVALID_INDEX_ERROR;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

import foodwhere.commons.core.index.Index;
import foodwhere.model.AddressBook;
import foodwhere.model.Model;
import foodwhere.model.ModelManager;
import foodwhere.model.UserPrefs;
import foodwhere.model.review.Review;
import foodwhere.model.review.ReviewBuilder;
import foodwhere.model.stall.Stall;
import foodwhere.testutil.TypicalIndexes;
import foodwhere.testutil.TypicalStalls;

/**
 * Contains integration tests (interaction with the Model) and unit tests for RAddCommand.
 */
public class RAddCommandTest {

    private Model model = new ModelManager(TypicalStalls.getTypicalAddressBook(), new UserPrefs());

    @Test
    public void execute_withTagsUnfilteredList_success() {
        Index indexLastStall = Index.fromOneBased(model.getFilteredStallList().size());
        Stall lastStall = model.getFilteredStallList().get(indexLastStall.getZeroBased());
        String name = lastStall.getName().toString();
        String address = lastStall.getAddress().toString();

        Review review = new ReviewBuilder().withName(name)
                .withAddress(address).withTags(CommandTestUtil.VALID_TAG_FRIEND).build();
        RAddCommand rAddCommand = new RAddCommand(indexLastStall, review.getDate(),
                review.getContent(), review.getRating(), review.getTags());
        String expectedMessage = String.format(RAddCommand.MESSAGE_SUCCESS, review);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.addReview(review);

        assertCommandSuccess(rAddCommand, model, expectedMessage, expectedModel);

        assertEquals(review.getContent().value, rAddCommand.toString());
    }

    @Test
    public void execute_noTagsUnfilteredList_success() {
        Index indexLastStall = Index.fromOneBased(model.getFilteredStallList().size());
        Stall lastStall = model.getFilteredStallList().get(indexLastStall.getZeroBased());
        String name = lastStall.getName().toString();
        String address = lastStall.getAddress().toString();

        Review review = new ReviewBuilder().withName(name).withAddress(address).build();
        RAddCommand rAddCommand = new RAddCommand(indexLastStall, review.getDate(),
                review.getContent(), review.getRating(), review.getTags());
        String expectedMessage = String.format(RAddCommand.MESSAGE_SUCCESS, review);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.addReview(review);

        assertCommandSuccess(rAddCommand, model, expectedMessage, expectedModel);

        assertEquals(review.getContent().value, rAddCommand.toString());
    }


    @Test
    public void execute_filteredList_success() {
        CommandTestUtil.showStallAtIndex(model, TypicalIndexes.INDEX_FIRST_STALL);

        Stall stallInFilteredList =
                model.getFilteredStallList().get(TypicalIndexes.INDEX_FIRST_STALL.getZeroBased());
        String name = stallInFilteredList.getName().toString();
        String address = stallInFilteredList.getAddress().toString();

        Review review = new ReviewBuilder().withName(name).withAddress(address).build();

        RAddCommand rAddCommand = new RAddCommand(TypicalIndexes.INDEX_FIRST_STALL, review.getDate(),
                review.getContent(), review.getRating(), review.getTags());
        String expectedMessage = String.format(RAddCommand.MESSAGE_SUCCESS, review);

        Model expectedModel = new ModelManager(new AddressBook(model.getAddressBook()), new UserPrefs());
        expectedModel.addReview(review);

        assertCommandSuccess(rAddCommand, model, expectedMessage, expectedModel);
    }

    @Test
    public void execute_duplicateReviewUnfilteredList_failure() {
        Review firstReview = model.getFilteredReviewList().get(TypicalIndexes.INDEX_FIRST_REVIEW.getZeroBased());
        Review review = new ReviewBuilder(firstReview).build();
        RAddCommand rAddCommand = new RAddCommand(TypicalIndexes.INDEX_FIRST_STALL, review.getDate(),
                review.getContent(), review.getRating(), review.getTags());

        CommandTestUtil.assertCommandFailure(rAddCommand, model, RAddCommand.MESSAGE_DUPLICATE_REVIEW);
    }


    @Test
    public void execute_invalidStallIndexUnfilteredList_failure() {
        Index outOfBoundIndex = Index.fromOneBased(model.getFilteredReviewList().size() + 1);
        Review review = new ReviewBuilder().build();
        RAddCommand rAddCommand = new RAddCommand(outOfBoundIndex, review.getDate(),
                review.getContent(), review.getRating(), review.getTags());

        CommandTestUtil.assertCommandFailure(rAddCommand, model, MESSAGE_INVALID_INDEX_ERROR);
    }


    @Test
    public void equals() {
        Review review = new ReviewBuilder().build();
        final RAddCommand standardCommand = new RAddCommand(TypicalIndexes.INDEX_FIRST_REVIEW, review.getDate(),
                review.getContent(), review.getRating(), review.getTags());

        // same values -> returns true
        RAddCommand commandWithSameValues = new RAddCommand(TypicalIndexes.INDEX_FIRST_STALL,
                review.getDate(), review.getContent(), review.getRating(), review.getTags());
        assertTrue(standardCommand.equals(commandWithSameValues));

        // same object -> returns true
        assertTrue(standardCommand.equals(standardCommand));

        // null -> returns false
        assertFalse(standardCommand.equals(null));

        // different types -> returns false
        assertFalse(standardCommand.equals(new ClearCommand()));


    }

}
