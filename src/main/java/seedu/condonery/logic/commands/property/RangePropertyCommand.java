package seedu.condonery.logic.commands.property;

import static java.util.Objects.requireNonNull;

import seedu.condonery.commons.core.Messages;
import seedu.condonery.logic.commands.Command;
import seedu.condonery.logic.commands.CommandResult;
import seedu.condonery.model.Model;
import seedu.condonery.model.property.PropertyPriceWithinRangePredicate;

/**
 * Finds and lists all properties in Condonery whose name contains any of the argument keywords.
 * Keyword matching is case insensitive.
 */
public class RangePropertyCommand extends Command {

    public static final String COMMAND_WORD = "range -p";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all properties within specified price range "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: l/[LOWER] u/[UPPER]...\n"
            + "Example: " + COMMAND_WORD + " l/10,000 u/200,000";

    private final PropertyPriceWithinRangePredicate predicate;

    public RangePropertyCommand(PropertyPriceWithinRangePredicate predicate) {
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
                || (other instanceof RangePropertyCommand // instanceof handles nulls
                && predicate.equals(((RangePropertyCommand) other).predicate)); // state check
    }
}
