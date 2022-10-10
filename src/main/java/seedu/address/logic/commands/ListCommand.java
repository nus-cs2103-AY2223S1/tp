package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import seedu.address.model.Model;
import seedu.address.model.person.ComparableCategory;

/**
 * Lists all persons in the address book to the user.
 */
public class ListCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Lists all internships "
            + "specified by the category (case-insensitive) in ascending or descending order "
            + "and displays them as a list with index numbers.\n"
            + "Parameters: [c/CATEGORY] [REVERSE] "
            + "Examples:\n "
            + COMMAND_WORD + "\n"
            + COMMAND_WORD + " c/website true \n"
            + COMMAND_WORD + " c/p \n";

    public static final String MESSAGE_SUCCESS = "Listed all internships";

    private final ComparableCategory category;
    private final boolean reverse;

    /**
     * Creates a ListCommand
     */
    public ListCommand() {
        this.category = null;
        this.reverse = false;
    }

    /**
     * Creates a ListCommand sorted by the category in ascending or descending order
     */
    public ListCommand(ComparableCategory category, boolean reverse) {
        this.category = category;
        this.reverse = reverse;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        if (category != null) {
            model.sortList(category);
        }
        if (reverse) {
            model.reverseList();
        }
        return new CommandResult(MESSAGE_SUCCESS);
    }
}
