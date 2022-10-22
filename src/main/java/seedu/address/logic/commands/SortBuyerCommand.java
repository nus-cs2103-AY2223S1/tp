package seedu.address.logic.commands;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Buyer;

import java.util.Comparator;

import static java.util.Objects.requireNonNull;

/**
 * Sorts the Buyer's list.
 */
public class SortBuyerCommand extends SortCommand {

    public static final String MESSAGE_SUCCESS =
            "buyer list has been sorted successfully";
    public static final String MESSAGE_USAGE =
            "Acceptable buyer attributes are order, address, email, location, name, phone";
    public static final String MESSAGE_WRONG_ATTRIBUTE =
            "%1$s is not a supported attribute in sorting buyer list \n%2$s";

    private final Comparator<Buyer> comparator;

    public SortBuyerCommand(Comparator<Buyer> comparator) {
        requireNonNull(comparator);

        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.sortBuyer(comparator);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
