package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;
import seedu.address.model.internship.SortCriteria;


/**
 * Sorts internships according to sort criteria.
 * Sort criteria can be applied date or interview date.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts applications based on criteria's date.\n"
            + "Parameters: CRITERIA\n"
            + "Valid criteria: \"applied\" \"interview\"\n"
            + "Example: " + COMMAND_WORD + " applied";


    private final SortCriteria sortCriteria;

    /**
     * @param criteria SortCriteria used to sort the internship applications
     */
    public SortCommand(SortCriteria criteria) {
        requireNonNull(criteria);
        this.sortCriteria = criteria;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateSortedInternshipList(SortCriteria.getComparator(sortCriteria));
        return new CommandResult(
                String.format(Messages.MESSAGE_SORT_SUCCESS, sortCriteria));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        if (!(other instanceof SortCommand)) {
            return false;
        }

        SortCommand sc = (SortCommand) other;

        return sortCriteria.equals(sc.sortCriteria);
    }
}
