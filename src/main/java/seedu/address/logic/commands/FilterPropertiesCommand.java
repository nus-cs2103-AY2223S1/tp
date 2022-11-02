package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CHARACTERISTICS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_FUZZY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_OWNER_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE_RANGE;

import java.util.function.Predicate;

import seedu.address.commons.core.Messages;
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

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Filters all properties in Cobb that satisfy the given"
            + " price range AND contains all the given characteristics AND has the given owner name AND has the given"
            + " owner phone.\n"
            + "Pass in " + PREFIX_FUZZY + " to loosen the filtering criteria, meaning filtered properties will only"
            + " need to satisfy the given price range OR contain at least one given characteristic OR have the given"
            + " owner name or phone.\n"
            + "Parameters: "
            + "[" + PREFIX_PRICE_RANGE + " PRICE RANGE] "
            + "[" + PREFIX_CHARACTERISTICS + " CHARACTERISTICS] "
            + "[" + PREFIX_OWNER_NAME + " OWNER NAME]"
            + "[" + PREFIX_PHONE + " OWNER PHONE"
            + "[" + PREFIX_FUZZY + "]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_CHARACTERISTICS + " Near MRT; Kid-Friendly " + PREFIX_FUZZY;

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

