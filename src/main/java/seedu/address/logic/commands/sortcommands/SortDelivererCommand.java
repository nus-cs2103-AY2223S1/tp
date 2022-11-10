package seedu.address.logic.commands.sortcommands;

import static java.util.Objects.requireNonNull;

import java.util.Comparator;

import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Deliverer;

/**
 * Sorts the Deliverers' list according to the specified /default attribute.
 */
public class SortDelivererCommand extends SortCommand {

    public static final String MESSAGE_SUCCESS =
            "deliverer list has been sorted successfully";
    public static final String MESSAGE_WRONG_ATTRIBUTE =
            "%1$s is not a supported attribute in sorting deliverer list\n%2$s";
    public static final String MESSAGE_USAGE =
            "Acceptable deliverer attributes are pet, address, email, location, name, phone";

    private final Comparator<Deliverer> comparator;

    /**
     * Constructs a SortDelivererCommand with the specified comparator.
     * @param comparator The specified comparator.
     */
    public SortDelivererCommand(Comparator<Deliverer> comparator) {
        requireNonNull(comparator);
        this.comparator = comparator;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        model.sortDeliverer(comparator);
        model.switchToDelivererList();
        return new CommandResult(MESSAGE_SUCCESS);
    }

}
