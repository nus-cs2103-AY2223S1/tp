package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.model.Model;
import seedu.address.model.Model.ListType;

/**
 * Sorts the current list according to oldest to newest, alphabetically, or reverse order.
 */
public class SortCommand extends Command {

    /**
     * Enum for the different methods available for sorting a list.
     */
    public enum SortBy { DEFAULT, ALPHA, REVERSE }

    public static final String COMMAND_WORD = "sort";

    public static final String FEEDBACK_MESSAGE = "Valid sort command format:\n"
            + "sort default/alpha/reverse\n";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Sort the current list by chronological, alphabetical or reverse order.\n"
            + "\nParameters:\n"
            + "default OR\n"
            + "alpha OR\n"
            + "reverse \n"
            + "\nExample: \n" + COMMAND_WORD + " alpha\n";

    public static final String MESSAGE_SUCCESS = "List sorted %s";

    public static final String DEFAULT_SORT_SUCCESS = "from oldest to newest updated entry";

    public static final String ALPHA_SORT_SUCCESS = "alphabetically";

    public static final String REVERSE_SORT_SUCCESS = "in reverse order";

    private SortBy method;
    private String successMsg;

    /**
     * Returns a {@code Entity} from {@code String entity}.
     */
    public SortCommand(SortBy method) {
        this.method = method;
        switch (method) {
        case DEFAULT:
            this.successMsg = DEFAULT_SORT_SUCCESS;
            break;
        case ALPHA:
            this.successMsg = ALPHA_SORT_SUCCESS;
            break;
        case REVERSE:
            this.successMsg = REVERSE_SORT_SUCCESS;
            break;
        default:
            this.successMsg = "?";
        }
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        ListType type = model.getCurrentListType();
        model.sortList(type, this.method);
        return new CommandResult(String.format(MESSAGE_SUCCESS, this.successMsg));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof SortCommand)) {
            return false;
        }

        // state check
        SortCommand otherSortCommand = (SortCommand) other;
        return method.equals(otherSortCommand.method)
                && successMsg.equals(otherSortCommand.successMsg);
    }
}
