package seedu.condonery.logic.commands.property;

import static java.util.Objects.requireNonNull;

import seedu.condonery.commons.core.Messages;
import seedu.condonery.logic.commands.Command;
import seedu.condonery.logic.commands.CommandResult;
import seedu.condonery.model.Model;
import seedu.condonery.model.property.PropertyTagContainsKeywordsPredicate;

/**
 * Finds and lists all properties in Condonery whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class FilterPropertyCommand extends Command {

    public static final String COMMAND_WORD = "filter -p";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all properties whose tags contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: KEYWORD [MORE_KEYWORDS]...\n"
            + "Example: " + COMMAND_WORD + " high-end";

    private final PropertyTagContainsKeywordsPredicate predicate;

    /**
     * Creates a FilterPropertyCommand to filter the specified {@code Property}
     * @param predicate
     */
    public FilterPropertyCommand(PropertyTagContainsKeywordsPredicate predicate) {
        requireNonNull(predicate);
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPropertyList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PROPERTIES_LISTED_OVERVIEW, model.getFilteredPropertyList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterPropertyCommand // instanceof handles nulls
                && predicate.equals(((FilterPropertyCommand) other).predicate)); // state check
    }
}
