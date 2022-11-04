package seedu.address.logic.commands.sortcommands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Buyer;

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

    /**
     * Constructs a sortBuyerCommand with specified comparator.
     * @param comparator The specified comparator.
     */
    public SortBuyerCommand(Comparator<Buyer> comparator) {
        requireNonNull(comparator);

        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.sortBuyer(comparator);
        model.switchToBuyerList();
        return new CommandResult(MESSAGE_SUCCESS);
    }

    /*@Override
    public boolean equals(Object object) {
        if (object == this) {
            return true;
        }
        if (!(object instanceof SortBuyerCommand)) {
            return false;
        }

        return comparator.equals(((SortBuyerCommand) object).comparator);
    }*/
}
