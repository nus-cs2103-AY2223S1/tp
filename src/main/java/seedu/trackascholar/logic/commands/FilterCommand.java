package seedu.trackascholar.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.trackascholar.commons.core.Messages;
import seedu.trackascholar.model.Model;
import seedu.trackascholar.model.applicant.ApplicationStatusPredicate;

/**
 * Filters all applicants in TrackAScholar whose status matches the keyword.
 * Keyword matching is case-insensitive.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all applicants by the specified "
            + "application status (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: pending/accepted/rejected\n"
            + "Example: " + COMMAND_WORD + " pending";

    private final ApplicationStatusPredicate predicate;

    public FilterCommand(ApplicationStatusPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredApplicantList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_APPLICANTS_LISTED_OVERVIEW, model.getFilteredApplicantList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterCommand // instanceof handles nulls
                && predicate.equals(((FilterCommand) other).predicate)); // state check
    }
}
