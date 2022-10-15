package foodwhere.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import foodwhere.commons.core.Messages;
import foodwhere.commons.core.index.Index;
import foodwhere.logic.commands.exceptions.CommandException;
import foodwhere.logic.parser.CliSyntax;
import foodwhere.model.Model;
import foodwhere.model.commons.Name;
import foodwhere.model.commons.Tag;
import foodwhere.model.review.Content;
import foodwhere.model.review.Date;
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
            + "[" + CliSyntax.PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_STALL_INDEX + "3 "
            + CliSyntax.PREFIX_DATE + "2022-09-20 "
            + CliSyntax.PREFIX_CONTENT + "The food was good, the chicken rice was fresh.\n"
            + CliSyntax.PREFIX_TAG + "opensDaily "
            + CliSyntax.PREFIX_TAG + "worthyTrip";


    public static final String MESSAGE_SUCCESS = "New review added: %1$s";
    public static final String MESSAGE_DUPLICATE_REVIEW = "This review already exists in the address book";

    private final Index stallIndex;
    private final Date date;
    private final Content content;
    private final Set<Tag> tagList;

    /**
     * Creates an RAddCommand to add the specified {@code Review}
     */
    public RAddCommand(Index stallIndex, Date date, Content content, Set<Tag> tagList) {
        requireNonNull(stallIndex);
        this.stallIndex = stallIndex;
        this.date = date;
        this.content = content;
        this.tagList = tagList;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Stall> lastShownList = model.getFilteredStallList();
        if (stallIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STALL_DISPLAYED_INDEX);
        }
        Stall stall = lastShownList.get(stallIndex.getZeroBased());

        Name name = stall.getName();

        Review toAdd = new Review(name, date, content, tagList);

        if (model.hasReview(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_REVIEW);
        }

        model.addReview(toAdd);

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
