package seedu.address.logic.commands.property;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PRICE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME;
import static seedu.address.logic.sortcomparators.Order.OrderType.ASC;

import java.util.Comparator;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.property.Property;

/**
 * Sorts the properties list.
 */
public class SortPropertiesCommand extends Command {
    public static final String COMMAND_WORD = "sortprops";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sorts properties by name, price or entry time in ascending or descending order."
            + " You can only sort by one criteria at a time.\n"
            + "Parameters: "
            + "[" + PREFIX_NAME + " NAME <ASC/DESC>] "
            + "[" + PREFIX_PRICE + " PRICE <ASC/DESC>]"
            + "[" + PREFIX_TIME + " TIME <ASC/DESC>]\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_NAME + " " + ASC;

    public static final String MESSAGE_SUCCESS = "Sorted properties by: %s";

    private final Comparator<Property> comparator;

    /**
     * Creates a SortPropertiesCommand to add the specified {@code Property}
     * @param comparator
     */
    public SortPropertiesCommand(Comparator<Property> comparator) {
        requireNonNull(comparator);
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        model.sortPropertyList(comparator);
        return new CommandResult(String.format(MESSAGE_SUCCESS, comparator.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortPropertiesCommand // instanceof handles nulls
                && comparator.equals(((SortPropertiesCommand) other).comparator));
    }
}
