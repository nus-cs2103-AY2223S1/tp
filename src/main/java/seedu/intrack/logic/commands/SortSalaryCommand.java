package seedu.intrack.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.intrack.model.Model.PREDICATE_SHOW_ALL_INTERNSHIPS;

import seedu.intrack.logic.commands.exceptions.CommandException;
import seedu.intrack.model.Model;

/**
 * Sorts all the internships in the internship list by their respective salaries
 */
public class SortSalaryCommand extends SortCommand {

    public static final String COMMAND_PREFIX = "salary";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all the internships by either\n"
            + "a for ascending salary, with internships offering the highest salaries at the top\n"
            + "or d for descending salary, with internships offering the lowest salaries at the top\n"
            + "Example: " + SortCommand.COMMAND_WORD + " " + COMMAND_PREFIX + " a";

    public static final String SORT_COMMAND_CONSTRAINTS = "SORT SALARY must be either \"a\" to denote ASCENDING or "
            + "\"d\" to denote DESCENDING";

    public static final String MESSAGE_SUCCESS_A = "Sorted all internships in ascending order";

    public static final String MESSAGE_SUCCESS_D = "Sorted all internships in descending order";

    private final String orderType; // will be either A, a, D or d

    public SortSalaryCommand(String orderType) {
        this.orderType = orderType;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (orderType.equals("a")) {
            model.ascendSortSalary();
            model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
            return new CommandResult(String.format(MESSAGE_SUCCESS_A, model.getFilteredInternshipList().size()));
        } else if (orderType.equals("d")) {
            model.descendSortSalary();
            model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
            return new CommandResult(String.format(MESSAGE_SUCCESS_D, model.getFilteredInternshipList().size()));
        } else {
            throw new CommandException(SORT_COMMAND_CONSTRAINTS);
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof SortSalaryCommand)) {
            return false;
        }
        SortSalaryCommand e = (SortSalaryCommand) other;
        return orderType.equals(e.orderType);
    }

}
