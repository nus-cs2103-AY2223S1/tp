package foodwhere.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;

import foodwhere.commons.core.Messages;
import foodwhere.commons.core.index.Index;
import foodwhere.logic.commands.exceptions.CommandException;
import foodwhere.logic.parser.CliSyntax;
import foodwhere.model.Model;
import foodwhere.model.commons.Detail;
import foodwhere.model.commons.Name;
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
            + "[" + CliSyntax.PREFIX_DETAIL + "DETAIL]...\n"
            + "Example: " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_STALL_INDEX + "3 "
            + CliSyntax.PREFIX_DATE + "2022-09-20 "
            + CliSyntax.PREFIX_CONTENT + "The food was good, the chicken rice was fresh.\n"
            + CliSyntax.PREFIX_DETAIL + "opensDaily "
            + CliSyntax.PREFIX_DETAIL + "worthyTrip";


    public static final String MESSAGE_SUCCESS = "New review added: %1$s";
    public static final String MESSAGE_DUPLICATE_REVIEW = "This review already exists in the address book";

    private final Index stallIndex;
    private final Date date;
    private final Content content;
    private final Set<Detail> detailList;

    /**
     * Creates an RAddCommand to add the specified {@code Review}
     */
    public RAddCommand(Index stallIndex, Date date, Content content, Set<Detail> detailList) {
        requireNonNull(stallIndex);
        this.stallIndex = stallIndex;
        this.date = date;
        this.content = content;
        this.detailList = detailList;
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

        Review toAdd = new Review(name, date, content, detailList);

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
}
