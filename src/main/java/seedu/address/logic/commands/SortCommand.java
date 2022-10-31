package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.commons.core.Messages;
import seedu.address.model.Model;

/**
 * Sorts and lists all persons in FinBook according to name, income, or meeting.
 */
public class SortCommand extends Command {

    public static final String COMMAND_WORD = "sort";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Sorts all clients according to given parameters "
            + "in ascending order. Parameters which can be sorted by are Name, Income and Meeting Date.\n"
            + "Parameters: n/ or i/ or m/\n"
            + "Example (by name): " + COMMAND_WORD + " n/"
            + "Example (by income): " + COMMAND_WORD + " i/";

    private final String sortParam;

    public SortCommand(String sortParam) {
        this.sortParam = sortParam;
    }

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.sort(sortParam);
        return new CommandResult(
                String.format(Messages.MESSAGE_PERSONS_LISTED_OVERVIEW, model.getFilteredPersonList().size()), -1);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof SortCommand // instanceof handles nulls
                && sortParam.equals(((SortCommand) other).sortParam)); // state check
    }
}
