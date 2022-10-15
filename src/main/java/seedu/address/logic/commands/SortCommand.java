package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTRIBUTE;

import seedu.address.model.Model;
import seedu.address.model.attribute.Attribute;
import seedu.address.model.order.Order;

/**
 * Sorts the address book by name in alphabetic order.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sort the list "
            + "by the attribute specified. \n "
            + "Parameters: ORDER (asc or dsc) "
            + "[" + PREFIX_ATTRIBUTE + "ORDER]...\n "
            + "Example: " + COMMAND_WORD + " asc "
            + PREFIX_ATTRIBUTE + "name";

    public static final String MESSAGE_SORT_SUCCESS = "Address book has been sorted by %1$s in %2$s order!";

    private final Order sortOrder;
    private final Attribute attribute;

    /**
     * Sorts the working list by the attribute in ascending or descending order.
     * @param attribute The attribute to sort the list by
     * @param sortOrder Order to sort the list in
     */
    public SortCommand(Attribute attribute, Order sortOrder) {
        this.attribute = attribute;
        this.sortOrder = sortOrder;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortFilteredStudentList(attribute, sortOrder);
        return new CommandResult(
                String.format(MESSAGE_SORT_SUCCESS, attribute.toString(), sortOrder.toString()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && attribute.equals(((SortCommand) other).attribute) // state check
                && sortOrder.equals(((SortCommand) other).sortOrder)); // state check
    }
}
