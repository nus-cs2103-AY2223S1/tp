package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHARACTERISTICS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OWNER_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.property.Property;


/**
 * Filters and lists all properties in the property list that either has a price that is within the given price,
 * characteristics that match the given characteristics list, or have the given seller.
 * More than one filtering criteria can be accepted, properties that match any of the given criteria are returned.
 * Keyword matching is case-insensitive.
 */
public class MultiFlagFilterPropsCommand extends Command {

    public static final String COMMAND_WORD = "multifilterprops";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Finds all properties in the database that"
            + " either have a price that is within the given price range, characteristics that match the given"
            + " characteristics list, or has the given seller. You can filter by more than one criteria at a time.\n"
            + "Parameters: "
            + "[" + PREFIX_PRICE_RANGE + " PRICE RANGE] "
            + "[" + PREFIX_CHARACTERISTICS + " CHARACTERISTICS] "
            + "[" + PREFIX_OWNER_NAME + " OWNER NAME]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_PRICE_RANGE + " 200000 - 500000 ";

    private final Predicate<Property> predicate;

    public MultiFlagFilterPropsCommand(Predicate<Property> predicate) {
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
                || (other instanceof MultiFlagFilterPropsCommand // instanceof handles nulls
                && predicate.equals(((MultiFlagFilterPropsCommand) other).predicate)); // state check
    }
}

