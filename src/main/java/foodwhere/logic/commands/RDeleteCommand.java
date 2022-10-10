package foodwhere.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import foodwhere.commons.core.Messages;
import foodwhere.commons.core.index.Index;
import foodwhere.logic.commands.exceptions.CommandException;
import foodwhere.model.Model;
import foodwhere.model.review.Review;

/**
 * Deletes a review identified using it's displayed index from the address book.
 */
public class RDeleteCommand extends Command {

    public static final String COMMAND_WORD = "rdel";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the review identified by the index number used in the displayed review list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_REVIEW_SUCCESS = "Deleted review: %1$s";

    private final Index targetIndex;

    public RDeleteCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Review> lastShownList = model.getFilteredReviewList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_REVIEW_DISPLAYED_INDEX);
        }

        Review reviewToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteReview(reviewToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_REVIEW_SUCCESS, reviewToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RDeleteCommand // instanceof handles nulls
                && targetIndex.equals(((RDeleteCommand) other).targetIndex)); // state check
    }
}
