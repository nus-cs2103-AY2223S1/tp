package seedu.intrack.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.intrack.logic.commands.exceptions.CommandException;
import seedu.intrack.model.Model;

/**
 * Sorts all the internship applications in InTrack by their respective salaries
 */
public class SortSalaryCommand extends SortCommand {

    public static final String SORT_COMMAND_CONSTRAINTS = "SORT_ORDER must be either \"a\" to denote Ascending or "
            + "\"d\" to denote Descending.";

    public static final String MESSAGE_SUCCESS_A = "Sorted all internship applications by salary in ascending order.";

    public static final String MESSAGE_SUCCESS_D = "Sorted all internship applications by salary in descending order.";

    private final String orderType;

    /**
     * @param orderType Order to sort the list by.
     */
    public SortSalaryCommand(String orderType) {
        requireNonNull(orderType);
        this.orderType = orderType;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        switch (orderType) {
        case "a":
            model.ascendSortSalary();
            return new CommandResult(MESSAGE_SUCCESS_A);
        case "d":
            model.descendSortSalary();
            return new CommandResult(MESSAGE_SUCCESS_D);
        default:
            throw new CommandException(SORT_COMMAND_CONSTRAINTS);
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortSalaryCommand // instanceof handles nulls
                && orderType.equals(((SortSalaryCommand) other).orderType)); // state check
    }

}
