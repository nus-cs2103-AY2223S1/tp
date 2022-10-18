package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Shows the details of an existing person inside TAB
 */
public class ShowCommand extends Command {
    public static final String COMMAND_WORD = "show";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Shows the details of the person identified "
            + "by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)";

    public static final String MESSAGE_SHOW_PERSON_SUCCESS = "Details displayed successfully!";

    private final Index index;

    /**
     * @param index of the person in the displayed persons list to show details of
     */
    public ShowCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToShow = lastShownList.get(index.getZeroBased());

        return new CommandResult(MESSAGE_SHOW_PERSON_SUCCESS, personToShow, false, false, false, false);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ShowCommand)) {
            return false;
        }

        // state check
        ShowCommand s = (ShowCommand) other;
        return index.equals(s.index);
    }
}
