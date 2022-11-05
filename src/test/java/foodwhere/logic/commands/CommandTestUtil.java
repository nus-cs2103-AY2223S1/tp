package foodwhere.logic.commands;

import static foodwhere.testutil.Assert.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import foodwhere.commons.core.index.Index;
import foodwhere.logic.commands.exceptions.CommandException;
import foodwhere.logic.parser.CliSyntax;
import foodwhere.model.AddressBook;
import foodwhere.model.Model;
import foodwhere.model.commons.Name;
import foodwhere.model.review.Review;
import foodwhere.model.review.ReviewContainsKeywordsPredicate;
import foodwhere.model.stall.Stall;
import foodwhere.model.stall.StallContainsKeywordsPredicate;
import foodwhere.testutil.EditReviewDescriptorBuilder;
import foodwhere.testutil.EditStallDescriptorBuilder;

/**
 * Contains helper methods for testing commands.
 */
public class CommandTestUtil {

    public static final String VALID_NAME_AMY = "Amy Bee";
    public static final String VALID_NAME_BOB = "Bob Choo";
    public static final String VALID_ADDRESS_AMY = "Block 312, Amy Street 1";
    public static final String VALID_ADDRESS_BOB = "Block 123, Bobby Street 3";
    public static final String VALID_DATE_AMY = "20/09/2022";
    public static final String VALID_DATE_BOB = "20/08/2022";
    public static final String VALID_CONTENT_AMY = "Good, 5/5";
    public static final String VALID_CONTENT_BOB = "Bad, 1/5";
    public static final Integer VALID_RATING_AMY = 4;
    public static final Integer VALID_RATING_BOB = 3;
    public static final String VALID_TAG_HUSBAND = "husband";
    public static final String VALID_TAG_FRIEND = "friend";

    public static final String STALL_INDEX_DESC = " " + CliSyntax.PREFIX_STALL_INDEX + "1";
    public static final String DATE_DESC_AMY = " " + CliSyntax.PREFIX_DATE + VALID_DATE_AMY;
    public static final String DATE_DESC_BOB = " " + CliSyntax.PREFIX_DATE + VALID_DATE_BOB;
    public static final String CONTENT_DESC_AMY = " " + CliSyntax.PREFIX_CONTENT + VALID_CONTENT_AMY;
    public static final String CONTENT_DESC_BOB = " " + CliSyntax.PREFIX_CONTENT + VALID_CONTENT_BOB;

    public static final String NAME_DESC_AMY = " " + CliSyntax.PREFIX_NAME + VALID_NAME_AMY;
    public static final String NAME_DESC_BOB = " " + CliSyntax.PREFIX_NAME + VALID_NAME_BOB;
    public static final String ADDRESS_DESC_AMY = " " + CliSyntax.PREFIX_ADDRESS + VALID_ADDRESS_AMY;
    public static final String ADDRESS_DESC_BOB = " " + CliSyntax.PREFIX_ADDRESS + VALID_ADDRESS_BOB;
    public static final String RATING_DESC_AMY = " " + CliSyntax.PREFIX_RATING + VALID_RATING_AMY;
    public static final String RATING_DESC_BOB = " " + CliSyntax.PREFIX_RATING + VALID_RATING_BOB;
    public static final String TAG_DESC_FRIEND = " " + CliSyntax.PREFIX_TAG + VALID_TAG_FRIEND;
    public static final String TAG_DESC_HUSBAND = " " + CliSyntax.PREFIX_TAG + VALID_TAG_HUSBAND;

    public static final String INVALID_DATE_DESC = " "
            + CliSyntax.PREFIX_DATE + ""; //invalid date format

    public static final String INVALID_NAME_DESC = " " + CliSyntax.PREFIX_NAME + "James&"; // '&' not allowed in names
    public static final String INVALID_ADDRESS_DESC = " "
            + CliSyntax.PREFIX_ADDRESS; // empty string not allowed for addresses
    public static final String INVALID_TAG_DESC = " "
            + CliSyntax.PREFIX_TAG + "hubby*"; // '*' not allowed in tags
    public static final String INVALID_STALL_INDEX_A = " "
            + CliSyntax.PREFIX_STALL_INDEX + "hubby*";
    public static final String INVALID_STALL_INDEX_B = " "
            + CliSyntax.PREFIX_STALL_INDEX + "-5";
    public static final String INVALID_STALL_INDEX_C = " "
            + CliSyntax.PREFIX_STALL_INDEX + "test";
    public static final String INVALID_STALL_INDEX_D = " "
            + CliSyntax.PREFIX_STALL_INDEX + "";

    // negative number not allowed in rating
    public static final String INVALID_RATING_DESC = " " + CliSyntax.PREFIX_RATING + "-1";

    public static final String PREAMBLE_WHITESPACE = "\t  \r  \n";
    public static final String PREAMBLE_NON_EMPTY = "NonEmptyPreamble";

    public static final SEditCommand.EditStallDescriptor DESC_AMY;
    public static final SEditCommand.EditStallDescriptor DESC_BOB;

    public static final REditCommand.EditReviewDescriptor DESC_AMY_REVIEW;
    public static final REditCommand.EditReviewDescriptor DESC_BOB_REVIEW;

    static {
        DESC_AMY = new EditStallDescriptorBuilder().withName(VALID_NAME_AMY)
                .withAddress(VALID_ADDRESS_AMY)
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB = new EditStallDescriptorBuilder().withName(VALID_NAME_BOB)
                .withAddress(VALID_ADDRESS_BOB)
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
        DESC_AMY_REVIEW = new EditReviewDescriptorBuilder().withDate(VALID_DATE_AMY)
                .withContent(VALID_CONTENT_AMY)
                .withRating(VALID_RATING_AMY.toString())
                .withTags(VALID_TAG_FRIEND).build();
        DESC_BOB_REVIEW = new EditReviewDescriptorBuilder().withDate(VALID_DATE_BOB)
                .withContent(VALID_CONTENT_BOB)
                .withRating(VALID_RATING_BOB.toString())
                .withTags(VALID_TAG_HUSBAND, VALID_TAG_FRIEND).build();
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - the returned {@link CommandResult} matches {@code expectedCommandResult} <br>
     * - the {@code actualModel} matches {@code expectedModel}
     */
    public static void assertCommandSuccess(Command command, Model actualModel, CommandResult expectedCommandResult,
            Model expectedModel) {
        try {
            CommandResult result = command.execute(actualModel);
            assertEquals(expectedCommandResult, result);
            assertEquals(expectedModel, actualModel);
        } catch (CommandException ce) {
            throw new AssertionError("Execution of command should not fail.", ce);
        }
    }

    /**
     * Convenience wrapper to {@link #assertCommandSuccess(Command, Model, CommandResult, Model)}
     * that takes a string {@code expectedMessage}.
     */
    public static void assertCommandSuccess(Command command, Model actualModel, String expectedMessage,
            Model expectedModel) {
        CommandResult expectedCommandResult = new CommandResult(expectedMessage);
        assertCommandSuccess(command, actualModel, expectedCommandResult, expectedModel);
    }

    /**
     * Executes the given {@code command}, confirms that <br>
     * - a {@code CommandException} is thrown <br>
     * - the CommandException message matches {@code expectedMessage} <br>
     * - the address book, filtered stall list and selected stall in {@code actualModel} remain unchanged
     */
    public static void assertCommandFailure(Command command, Model actualModel, String expectedMessage) {
        // we are unable to defensively copy the model for comparison later, so we can
        // only do so by copying its components.
        AddressBook expectedAddressBook = new AddressBook(actualModel.getAddressBook());
        List<Stall> expectedFilteredList = new ArrayList<>(actualModel.getFilteredStallList());

        assertThrows(CommandException.class, expectedMessage, () -> command.execute(actualModel));
        assertEquals(expectedAddressBook, actualModel.getAddressBook());
        assertEquals(expectedFilteredList, actualModel.getFilteredStallList());
    }
    /**
     * Updates {@code model}'s filtered list to show only the stall at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showStallAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredStallList().size());

        Stall stall = model.getFilteredStallList().get(targetIndex.getZeroBased());
        String name = stall.getName().toString().split(" ")[0];

        model.updateFilteredStallList(new StallContainsKeywordsPredicate(Collections.singletonList(new Name(name)),
                Collections.emptyList()));

        assertEquals(1, model.getFilteredStallList().size());
    }

    /**
     * Updates {@code model}'s filtered list to show only the review at the given {@code targetIndex} in the
     * {@code model}'s address book.
     */
    public static void showReviewAtIndex(Model model, Index targetIndex) {
        assertTrue(targetIndex.getZeroBased() < model.getFilteredStallList().size());

        Review review = model.getFilteredReviewList().get(targetIndex.getZeroBased());
        String name = review.getName().toString().split(" ")[0];
        model.updateFilteredReviewList(new ReviewContainsKeywordsPredicate(Collections.singletonList(new Name(name)),
                Collections.emptyList()));

        assertEquals(1, model.getFilteredReviewList().size());
    }

}
