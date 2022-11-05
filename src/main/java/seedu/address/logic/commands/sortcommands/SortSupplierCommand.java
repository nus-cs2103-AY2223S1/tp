package seedu.address.logic.commands.sortcommands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Supplier;

/**
 * Sorts the supplier list.
 */
public class SortSupplierCommand extends SortCommand {

    public static final String MESSAGE_SUCCESS =
            "supplier list has been sorted successfully";
    public static final String MESSAGE_WRONG_ATTRIBUTE =
            "%1$s is not a supported attribute in sorting supplier list \n%2$s";
    public static final String MESSAGE_USAGE =
            "Acceptable supplier attributes are order, address, email, location, name, phone";

    private final Comparator<Supplier> comparator;

    /**
     * Constructs a sortSupplierCommand with specified comparator.
     * @param comparator The specified comparator.
     */
    public SortSupplierCommand(Comparator<Supplier> comparator) {
        requireNonNull(comparator);
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.sortSupplier(comparator);
        model.switchToSupplierList();
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
