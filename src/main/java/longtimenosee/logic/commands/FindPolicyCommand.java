package longtimenosee.logic.commands;

import static java.util.Objects.requireNonNull;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_COMPANY;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_COVERAGES;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_TITLE;

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
            + "Parameters: "
            + "[" + PREFIX_TITLE + "TITLE] "
            + "[" + PREFIX_COMPANY + "COMPANY] "
            + "[" + PREFIX_COVERAGES + "COVERAGE]…\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_COVERAGES + "TRAVEL "
            + PREFIX_COMPANY + "AIA";

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
                true, false, false);
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
