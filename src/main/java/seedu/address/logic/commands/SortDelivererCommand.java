package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Deliverer;

/**
 * Sorts the deliverer list.
 */
public class SortDelivererCommand extends SortCommand {

    public static final String MESSAGE_SUCCESS =
            "deliverer list has been sorted successfully";
    public static final String MESSAGE_WRONG_ATTRIBUTE =
            "%1$s is not a supported attribute in sorting deliverer list\n%2$s";
    public static final String MESSAGE_USAGE =
            "Acceptable deliverer attributes are pet, address, email, location, name, phone";

    private final Comparator<Deliverer> comparator;


    public SortDelivererCommand(Comparator<Deliverer> comparator) {
        requireNonNull(comparator);
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.sortDeliverer(comparator);
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
