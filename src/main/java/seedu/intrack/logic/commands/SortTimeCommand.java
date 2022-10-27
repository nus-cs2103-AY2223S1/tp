package seedu.intrack.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.intrack.logic.commands.exceptions.CommandException;
import seedu.intrack.model.Model;

/**
 * Sorts all the internships in the internship list by the dates and time of their respective tasks
 */
public class SortTimeCommand extends SortCommand {

    public static final String SORT_COMMAND_CONSTRAINTS = "SORT_ORDER must be either \"a\" to denote Ascending or "
            + "\"d\" to denote Descending.";

    public static final String MESSAGE_SUCCESS_A =
            "Sorted all internships applications of by their upcoming tasks in ascending order";

    public static final String MESSAGE_SUCCESS_D =
            "Sorted all internships applications of by their upcoming tasks in descending order";

    private final String orderType;

    /**
     * @param orderType Order to sort the list by.
     */
    public SortTimeCommand(String orderType) {
        this.orderType = orderType;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        switch (orderType) {
        case "a":
            model.ascendSortTime();
            return new CommandResult(MESSAGE_SUCCESS_A);
        case "d":
            model.descendSortTime();
            return new CommandResult(MESSAGE_SUCCESS_D);
        default:
            throw new CommandException(SORT_COMMAND_CONSTRAINTS);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortTimeCommand // instanceof handles nulls
                && orderType.equals(((SortTimeCommand) other).orderType)); // state check
    }

}
