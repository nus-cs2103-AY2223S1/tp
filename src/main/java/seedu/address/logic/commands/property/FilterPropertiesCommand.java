package seedu.address.logic.commands.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHARACTERISTICS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FUZZY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OWNER_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.model.Model;
import seedu.address.model.property.Property;


/**
 * Filters and lists all properties in the property list that have a price that is within the given price,
 * contains all the characteristics in the given characteristics list, or have the given seller.
 * More than one filtering criteria can be accepted, properties matching any OR all of the given criteria can be
 * returned based on whether the user passes in the 'PREFIX_MATCH_ALL' flag.
 * Keyword matching is case-insensitive.
 */
public class FilterPropertiesCommand extends Command {

    public static final String COMMAND_WORD = "filterprops";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all properties in the database that"
            + " either has a price that is within the given price range, contains all characteristics in the given"
            + " characteristics list, or has the given seller. You can filter by more than one criteria at a time.\n"
            + " Pass in " + PREFIX_FUZZY + " for fuzzy filtering, i.e. filtered properties will only need to satisfy"
            + " at least one of the conditions specified. \n"
            + "Parameters: "
            + "[" + PREFIX_PRICE_RANGE + " PRICE RANGE] "
            + "[" + PREFIX_CHARACTERISTICS + " CHARACTERISTICS] "
            + "[" + PREFIX_OWNER_NAME + " OWNER NAME]\n"
            + "[" + PREFIX_FUZZY + "]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PRICE_RANGE + " 200000 - 500000 ";

    private final Predicate<Property> predicate;

    public FilterPropertiesCommand(Predicate<Property> predicate) {
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
                || (other instanceof FilterPropertiesCommand // instanceof handles nulls
                && predicate.equals(((FilterPropertiesCommand) other).predicate)); // state check
    }
}

