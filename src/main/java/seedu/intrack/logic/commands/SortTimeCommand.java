//@@author kangqiao322
package seedu.intrack.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.intrack.model.Model.PREDICATE_SHOW_ALL_INTERNSHIPS;

import seedu.intrack.logic.commands.exceptions.CommandException;
import seedu.intrack.model.Model;

/**
 * Sorts all the internships in the internship list by the dates and time of their respective tasks
 */
public class SortTimeCommand extends SortCommand {

    public static final String COMMAND_PREFIX = "time";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all the internships by either\n"
            + "a for ascending time, with internships containing tasks with the nearest dates at the top\n"
            + "or d for descending time, with internships containing tasks with the furthest dates at the top\n"
            + "Example: " + SortCommand.COMMAND_WORD + " " + COMMAND_PREFIX + " a";

    public static final String SORT_COMMAND_CONSTRAINTS = "SORT TIME must be either \"a\" to denote ASCENDING or "
            + "\"d\" to denote DESCENDING";

    public static final String MESSAGE_SUCCESS_A = "Sorted all internships in ascending order";

    public static final String MESSAGE_SUCCESS_D = "Sorted all internships in descending order";

    private final String orderType; // will be either A, a, D or d

    public SortTimeCommand(String orderType) {
        this.orderType = orderType;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (orderType.equals("a")) {
            model.ascendSortTime();
            model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
            return new CommandResult(String.format(MESSAGE_SUCCESS_A, model.getFilteredInternshipList().size()));
        } else if (orderType.equals("d")) {
            model.descendSortTime();
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
        if (!(other instanceof SortTimeCommand)) {
            return false;
        }
        SortTimeCommand e = (SortTimeCommand) other;
        return orderType.equals(e.orderType);
    }

}
