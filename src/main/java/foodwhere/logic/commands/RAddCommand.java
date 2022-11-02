package foodwhere.logic.commands;

import static foodwhere.commons.core.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static foodwhere.commons.core.Messages.MESSAGE_INVALID_INDEX;
import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import foodwhere.commons.core.index.Index;
import foodwhere.logic.commands.exceptions.CommandException;
import foodwhere.logic.parser.CliSyntax;
import foodwhere.model.Model;
import foodwhere.model.commons.Address;
import foodwhere.model.commons.Name;
import foodwhere.model.commons.Tag;
import foodwhere.model.review.Content;
import foodwhere.model.review.Date;
import foodwhere.model.review.Rating;
import foodwhere.model.review.Review;
import foodwhere.model.stall.Stall;

/**
 * Adds a review to the address book.
 */
public class RAddCommand extends Command {

    public static final String COMMAND_WORD = "radd";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a review to the address book. "
            + "Parameters: "
            + CliSyntax.PREFIX_STALL_INDEX + "STALL_INDEX "
            + CliSyntax.PREFIX_DATE + "DATE "
            + CliSyntax.PREFIX_CONTENT + "CONTENT "
            + CliSyntax.PREFIX_RATING + "RATING "
            + "[" + CliSyntax.PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_STALL_INDEX + "3 "
            + CliSyntax.PREFIX_DATE + "20/09/2022 "
            + CliSyntax.PREFIX_CONTENT + "The food was good, the chicken rice was fresh. "
            + CliSyntax.PREFIX_RATING + "4 "
            + CliSyntax.PREFIX_TAG + "opensDaily "
            + CliSyntax.PREFIX_TAG + "worthyTrip";

    public static final String MESSAGE_SUCCESS = "New review added: %1$s";
    public static final String MESSAGE_DUPLICATE_REVIEW = "This review already exists in the address book";

    public static final String MESSAGE_INVALID_INDEX_ERROR =
            String.format(MESSAGE_INVALID_COMMAND_FORMAT, MESSAGE_INVALID_INDEX)
                    + RAddCommand.MESSAGE_USAGE;

    private final Index stallIndex;
    private final Date date;
    private final Content content;
    private final Rating rating;
    private final Set<Tag> tagList;

    /**
     * Creates an RAddCommand to add the specified {@code Review}
     */
    public RAddCommand(Index stallIndex, Date date, Content content, Rating rating, Set<Tag> tagList) {
        requireNonNull(stallIndex);
        this.stallIndex = stallIndex;
        this.date = date;
        this.content = content;
        this.rating = rating;
        this.tagList = tagList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Stall> lastShownList = model.getFilteredStallList();
        if (stallIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_INVALID_INDEX_ERROR);
        }
        Stall stall = lastShownList.get(stallIndex.getZeroBased());

        Name name = stall.getName();
        Address address = stall.getAddress();

        Review toAdd = new Review(name, address, date, content, rating, tagList);

        if (model.hasReview(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_REVIEW);
        }

        model.addReviewToStall(toAdd, stall);

        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof RAddCommand // instanceof handles nulls
                && content.equals(((RAddCommand) other).content));
    }

    @Override
    public String toString() {
        return content.toString();
    }
}
