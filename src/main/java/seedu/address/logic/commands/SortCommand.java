package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTRIBUTE;

import seedu.address.model.Model;
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

//    public static final String MESSAGE_SUCCESS = "Address book has been sorted by name in alphabetical order!";
    public static final String MESSAGE_SUCCESS = "Address book has been sorted by %1$s in alphabetical order! ";

    private final Order sortOrder;
    private final String attribute;

    public SortCommand(String attribute, Order sortOrder) {
        this.attribute = attribute;
        this.sortOrder = sortOrder;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sortFilteredPersonList(attribute, sortOrder);
        return new CommandResult(MESSAGE_SUCCESS);
    }

//    @Override
//    public CommandResult execute(Model model) {
//        requireNonNull(model);
//        model.sortFilteredPersonList();
//        return new CommandResult(MESSAGE_SUCCESS);
//    }
}
