package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.internship.InternshipHasApplicationStatusPredicate;

/**
 * Filters internships according to application status.
 * Application status is case-insensitive.
 */
public class FilterCommand extends Command {

    public static final String COMMAND_WORD = "filter";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filter internships based on application status.\n"
            + "Parameters: APPLICATION_STATUS\n"
            + "Valid filters for application status: \"accepted\" \"applied\" \"interviewed\" "
            + "\"shortlisted\" \"rejected\"\n"
            + "Example: " + COMMAND_WORD + " accepted";

    private final InternshipHasApplicationStatusPredicate predicate;

    /**
     * @param predicate Predicate containing application status of the internship
     */
    public FilterCommand(InternshipHasApplicationStatusPredicate predicate) {
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
        if (other == this) {
            return true;
        }

        if (!(other instanceof FilterCommand)) {
            return false;
        }

        FilterCommand fc = (FilterCommand) other;
        return predicate.equals(fc.predicate);
    }
}
