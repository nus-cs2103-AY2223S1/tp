package seedu.condonery.logic.commands.property;

import static java.util.Objects.requireNonNull;

import seedu.condonery.commons.core.Messages;
import seedu.condonery.logic.commands.Command;
import seedu.condonery.logic.commands.CommandResult;
import seedu.condonery.model.Model;
import seedu.condonery.model.property.PropertyTypeContainsKeywordsPredicate;

/**
 * Finds and lists all properties in Condonery whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class TypePropertyCommand extends Command {

    public static final String COMMAND_WORD = "type -p";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all properties whose names contain any of "
            + "the specified keywords (case-insensitive) and displays them as a list with index numbers.\n"
            + "Parameters: TYPE [TYPES]...\n"
            + "Example: " + COMMAND_WORD + " HDB Condo";

    private final PropertyTypeContainsKeywordsPredicate predicate;

    /**
     * Creates a TypePropertyCommand to find the specified {@code Property}
     *
     * @param predicate
     */
    public TypePropertyCommand(PropertyTypeContainsKeywordsPredicate predicate) {
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
                || (other instanceof TypePropertyCommand // instanceof handles nulls
                && predicate.equals(((TypePropertyCommand) other).predicate)); // state check
    }
}
