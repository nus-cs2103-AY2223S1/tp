package longtimenosee.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.function.Predicate;

import longtimenosee.commons.core.Messages;
import longtimenosee.model.Model;
import longtimenosee.model.policy.Policy;

/**
 * Finds and lists all policies in address book which match the given metrics.
 */
public class FindPolicyCommand extends Command {

    public static final String COMMAND_WORD = "findPolicy";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all policies that match the given metrics and "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: [ti/title] [cmp/COMPANY] [cov/COVERAGE]…\n"
            + "Example: " + COMMAND_WORD + " cov/TRAVEL cmp/AIA";

    private final List<Predicate<Policy>> predicates;

    /**
     * Creates a FindPolicyCommand object.
     *
     * @param predicates checks if the specified metrics match any policy.
     */
    public FindPolicyCommand(List<Predicate<Policy>> predicates) {
        this.predicates = predicates;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        Predicate<Policy> finalPredicate = predicates.get(0);
        for (Predicate<Policy> predicate : predicates) {
            if (!predicate.equals(predicates.get(0))) {
                finalPredicate = finalPredicate.and(predicate);
            }
        }
        model.updateFilteredPolicyList(finalPredicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_POLICIES_LISTED_OVERVIEW, model.getFilteredPolicyList().size()),
                true, false);
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        } else {
            if (other instanceof FindPolicyCommand) {
                return predicates.equals(((FindPolicyCommand) other).predicates);
            } else {
                return false;
            }
        }
    }
}
