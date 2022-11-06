package foodwhere.logic.commands;

import static java.util.Objects.requireNonNull;

import foodwhere.logic.commands.exceptions.CommandException;
import foodwhere.model.Model;
import foodwhere.model.stall.comparator.StallsComparatorList;

/**
 * Sort and list all stalls in FoodWhere to the user.
 */
public class SSortCommand extends Command {

    public static final String COMMAND_WORD = "ssort";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sort the stall list by specified criteria. See user guide for the list of criteria supported.\n"
            + "Parameters: CRITERIA (case-insensitive)\n"
            + "Example: " + COMMAND_WORD + " name";

    public static final String MESSAGE_SUCCESS = "The stall list is now sorted by %1$s";

    private final StallsComparatorList stallsComparator;

    /**
     * Creates an SSortCommand to sort the specified {@code Stall}s based on a criteria.
     *
     * @param stallsComparator Comparator for the {@code Stall}s
     */
    public SSortCommand(StallsComparatorList stallsComparator) {
        this.stallsComparator = stallsComparator;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.sortStalls(stallsComparator.getComparator());
        return new CommandResult(String.format(MESSAGE_SUCCESS, stallsComparator.getCriteria()));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SSortCommand // instanceof handles nulls
                && stallsComparator.equals(((SSortCommand) other).stallsComparator)); // state check
    }
}
