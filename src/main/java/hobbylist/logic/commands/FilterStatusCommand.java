package hobbylist.logic.commands;

import static java.util.Objects.requireNonNull;

import hobbylist.commons.core.Messages;
import hobbylist.model.Model;
import hobbylist.model.activity.StatusMatchesGivenStatus;

/**
 * Finds and lists all activities in HobbyList whose status matches the given status.
 * Status matching is case insensitive.
 */
public class FilterStatusCommand extends Command {
    public static final String COMMAND_WORD = "filterStatus";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all activities whose status matches"
            + "the given status (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: STATUS...\n"
            + "Example: " + COMMAND_WORD + "ONGOING";

    private final StatusMatchesGivenStatus predicate;

    public FilterStatusCommand(StatusMatchesGivenStatus predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredActivityList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_ACTIVITIES_LISTED_OVERVIEW, model.getFilteredActivityList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterStatusCommand // instanceof handles nulls
                && predicate.equals(((FilterStatusCommand) other).predicate)); // state check
    }
}
