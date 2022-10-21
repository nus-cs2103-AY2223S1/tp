package seedu.trackascholar.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.trackascholar.model.Model.PREDICATE_SHOW_ALL_APPLICANTS;

import java.util.Comparator;

import seedu.trackascholar.commons.core.Messages;
import seedu.trackascholar.model.Model;
import seedu.trackascholar.model.applicant.Applicant;

/**
 * Sorts all applicants in TrackAScholar whose status matches the keyword.
 * Keyword matching is case-insensitive.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all applicants by the specified "
            + "parameter (case-insensitive) in ascending order and displays them as a list with index numbers.\n"
            + "To sort in descending order, add a \"-r\" flag at the end of the input.\n"
            + "Parameters: name/scholarship/status [-r]\n"
            + "Example: " + COMMAND_WORD + " name -r";

    private final Comparator<Applicant> comparator;

    public SortCommand(Comparator<Applicant> comparator) {
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortApplicants(comparator);
        model.updateFilteredApplicantList(PREDICATE_SHOW_ALL_APPLICANTS);
        return new CommandResult(
                String.format(Messages.MESSAGE_APPLICANTS_LISTED_OVERVIEW, model.getFilteredApplicantList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && comparator.equals(((SortCommand) other).comparator)); // state check
    }
}
