package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHARACTERISTICS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SELLER;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.property.AbstractFilterPropsPredicate;

/**
 * Filters and lists all buyers in the buyer list that either have a price range that accepts the given price,
 * characteristics that match the given characteristics list, or have the given tag.
 * Keyword matching is case-insensitive.
 */
public class FilterPropsCommand extends Command {

    public static final String COMMAND_WORD = "filterprops";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all properties in the database that"
            + " either have a price that is within the given price range, characteristics that match the given"
            + " characteristics list, or has the given seller. You must filter by exactly one criteria at a time.\n"
            + "Parameters: " 
            + "[" + PREFIX_PRICE_RANGE + " PRICE RANGE] "
            + "[" + PREFIX_CHARACTERISTICS + " CHARACTERISTICS]"
            + "[" + PREFIX_SELLER + " SELLER]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PRICE_RANGE + " 200000 - 500000 ";

    private final AbstractFilterPropsPredicate predicate;

    public FilterPropsCommand(AbstractFilterPropsPredicate predicate) {
        this.predicate = predicate;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPropertyList(predicate);
        return new CommandResult(
                String.format(Messages.MESSAGE_PROPERTY_LISTED_OVERVIEW, model.getFilteredPropertyList().size()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof FilterPropsCommand // instanceof handles nulls
                && predicate.equals(((FilterPropsCommand) other).predicate)); // state check
    }
}

