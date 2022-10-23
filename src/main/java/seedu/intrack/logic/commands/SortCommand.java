package seedu.intrack.logic.commands;

import static seedu.intrack.model.Model.PREDICATE_SHOW_ALL_INTERNSHIPS;
import seedu.intrack.model.Model;

public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all the internships by either\n"
            + "a for ascending time, with internships containing tasks with the nearest dates at the top\n"
            + "or d for descending time, with internships containing tasks with the furthest dates at the top\n"
            + "Example: " + COMMAND_WORD + " a";

    public static final String SORT_COMMAND_CONSTRAINTS = "SORT must be either \"a\" to denote ASCENDING or "
            + "\"d\" to denote DESCENDING";

    public static final String MESSAGE_SUCCESS_A = "Sorted all internships in ascending order";

    public static final String MESSAGE_SUCCESS_D = "Sorted all internships in descending order";

    private final String orderType; //will be either A, a, D or d

    public SortCommand(String orderType) {
        this.orderType = orderType;
    }

    @Override
    public CommandResult execute(Model model) {
        if (orderType == "a") {
            model.ascendSort();
            model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
            return new CommandResult(String.format(MESSAGE_SUCCESS_A, model.getFilteredInternshipList().size()));
        } else {
            model.descendSort();
            model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
            return new CommandResult(String.format(MESSAGE_SUCCESS_D, model.getFilteredInternshipList().size()));
        }
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof SortCommand)) {
            return false;
        }
        SortCommand e = (SortCommand) other;
        return orderType.equals(e.orderType);
    }

}
