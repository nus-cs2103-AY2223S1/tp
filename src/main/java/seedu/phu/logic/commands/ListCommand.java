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
            + "specified by the category (case-insensitive) in ascending or descending order\n"
            + "Internships are displayed as a list with index numbers.\n"
            + "Parameters: [c/CATEGORY [DESCENDING = false]]\n"
            + "Examples:\n"
            + COMMAND_WORD + "\n"
            + COMMAND_WORD + " c/company_name false \n"
            + COMMAND_WORD + " c/pr true \n"
            + COMMAND_WORD + " c/p \n";

    public static final String MESSAGE_SUCCESS = "Listed all internships";

    private final ComparableCategory category;
    private final boolean isDescending;

    /**
     * Creates a ListCommand
     */
    public ListCommand() {
        this.category = ComparableCategory.NULL;
        this.isDescending = false;
    }

    /**
     * Creates a ListCommand sorted by the category in ascending or descending order
     */
    public ListCommand(ComparableCategory category, boolean isDescending) {
        this.category = category;
        this.isDescending = isDescending;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredInternshipList(PREDICATE_SHOW_ALL_INTERNSHIPS);
        if (category != ComparableCategory.NULL) {
            model.sortList(category);
            if (isDescending) {
                model.reverseList();
            }
        }
        String additionalMessage = (this.category == ComparableCategory.NULL) ? ""
                : " sorted by " + this.category.toString();
        return new CommandResult(MESSAGE_SUCCESS + additionalMessage);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ListCommand)) {
            return false;
        }

        // state check
        ListCommand l = (ListCommand) other;
        return this.category.equals(l.category)
                && this.isDescending == l.isDescending;
    }
}
