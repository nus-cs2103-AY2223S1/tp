package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.module.PlanModContainsKeywordsPredicate;

/**
 * Finds and lists all persons in address book who has a planned module which contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FilterPlanModCommand extends Command {

    public static final String COMMAND_WORD = "filterplanmod";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all persons whose planned modules contain any of"
            + "the specified keyword (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " cs2103t";

    private final PlanModContainsKeywordsPredicate predicate;

    public FilterPlanModCommand(PlanModContainsKeywordsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterPlanModCommand // instanceof handles nulls
                && predicate.equals(((FilterPlanModCommand) other).predicate)); // state check
    }
}
