package seedu.intrack.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.intrack.commons.core.Messages;
import seedu.intrack.model.Model;
import seedu.intrack.model.internship.StatusIsKeywordPredicate;

/**
 * Filters the internship list based on the status letter provided
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all internship applications whose current status"
            + " is of the specified status and displays them as a list with index numbers.\n"
            + "Parameters: STATUS (must be either \"o\", \"p\" or \"r\")\n"
            + "Example: " + COMMAND_WORD + " o";

    public static final String FILTER_COMMAND_CONSTRAINTS = "STATUS must be either \"o\" to denote Offered, "
            + "\"p\" to denote in Progress, "
            + "or \"r\" to denote Rejected.";

    private final StatusIsKeywordPredicate predicate;

    /**
     * @param predicate Predicate to filter the list with through status.
     */
    public FilterCommand(StatusIsKeywordPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredInternshipList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_INTERNSHIPS_LISTED_OVERVIEW, model.getFilteredInternshipList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand // instanceof handles nulls
                && predicate.equals(((FilterCommand) other).predicate)); // state check
    }

}
