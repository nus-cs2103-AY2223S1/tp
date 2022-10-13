package seedu.phu.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.phu.model.Model.PREDICATE_SHOW_ALL_INTERNSHIPS;

import seedu.phu.model.Model;
import seedu.phu.model.internship.ComparableCategory;

/**
 * Lists all internships in the internship book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all internships\n"
            + "List of internships can be sorted "
            + "specified by the category (case-insensitive) in ascending or descending order "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: [c/CATEGORY] [REVERSE] "
            + "Examples:\n "
            + COMMAND_WORD + "\n"
            + COMMAND_WORD + " c/website true \n"
            + COMMAND_WORD + " c/p \n";

    public static final String MESSAGE_SUCCESS = "Listed all internships";

    private final ComparableCategory category;
    private final boolean descending;

    /**
     * Creates a ListCommand
     */
    public ListCommand() {
        this.category = null;
        this.descending = false;
    }

    /**
     * Creates a ListCommand sorted by the category in ascending or descending order
     */
    public ListCommand(ComparableCategory category, boolean descending) {
        this.category = category;
        this.descending = descending;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
        if (category != null) {
            model.sortList(category);
            if (descending) {
                model.reverseList();
            }
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
