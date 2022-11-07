package hobbylist.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Optional;

import hobbylist.commons.core.Messages;
import hobbylist.commons.core.index.Index;
import hobbylist.logic.commands.exceptions.CommandException;
import hobbylist.logic.parser.CliSyntax;
import hobbylist.model.Model;
import hobbylist.model.activity.Activity;
import hobbylist.model.activity.Review;

/**
 * Add or modify the rating of an activity.
 */
public class RateCommand extends Command {
    public static final String MESSAGE_EDIT_ACTIVITY_SUCCESS = "Rated Activity: %1$s";
    public static final String MESSAGE_DUPLICATE_ACTIVITY = "This activity already exists in the HobbyList";

    private static String commandWord = "rate";

    public static final String MESSAGE_USAGE = commandWord + ": Adds or modifies the rating and review "
            + "of the activity identified by the index number used in the displayed activity list. "
            + "Existing value will be overwritten by the input value.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + "[" + CliSyntax.PREFIX_RATING + "RATING (1-5)] "
            + "[" + CliSyntax.PREFIX_REVIEW + "REVIEW]\n"
            + "Example: " + commandWord + " 1 "
            + CliSyntax.PREFIX_RATING + "4" + " "
            + CliSyntax.PREFIX_REVIEW + "not too bad.";

    private final Index index;
    private final int rating;
    private final Optional<Review> review;

    /**
     * @param index of the activity in the filtered activity list to edit
     * @param rating rating of the activity (1 to 5)
     */
    public RateCommand(Index index, int rating, Optional<Review> review) {
        requireNonNull(index);

        this.index = index;
        this.rating = rating;
        this.review = review;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Activity> lastShownList = model.getFilteredActivityList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_ACTIVITY_DISPLAYED_INDEX);
        }

        Activity activityToRate = lastShownList.get(index.getZeroBased());
        Activity activityWithRating = createActivityWithRating(activityToRate, rating, review);

        if (!activityToRate.isSameActivity(activityWithRating) && model.hasActivity(activityWithRating)) {
            throw new CommandException(MESSAGE_DUPLICATE_ACTIVITY);
        }

        model.setActivity(activityToRate, activityWithRating);
        model.updateFilteredActivityList(Model.PREDICATE_SHOW_ALL_ACTIVITIES);
        return new CommandResult(String.format(MESSAGE_EDIT_ACTIVITY_SUCCESS, activityWithRating));
    }

    /**
     * Creates and returns an {@code Activity} with the details of {@code activityToRate}
     * with {@code rating} and {@code review}.
     */
    private static Activity createActivityWithRating(Activity activityToRate,
                                                     int rating, Optional<Review> review) {
        assert activityToRate != null;
        return new Activity(activityToRate.getName(), activityToRate.getDescription(),
                activityToRate.getTags(), activityToRate.getDate(), rating, activityToRate.getStatus(),
                review != null ? review : activityToRate.getReview());
    }

    /**
     * Returns the command word.
     * @return the command word.
     */
    public static String getCommandWord() {
        return commandWord;
    }

    /**
     * Sets the command word.
     * @param word the new command word.
     */
    public static void setCommandWord(String word) {
        commandWord = word;
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof RateCommand)) {
            return false;
        }

        // state check
        RateCommand r = (RateCommand) other;
        return index.equals(r.index)
                && rating == r.rating
                && review.equals(r.review);
    }
}
