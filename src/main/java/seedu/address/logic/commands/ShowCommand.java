package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;
import seedu.address.ui.SecondaryPaneState;

/**
 * Shows a person identified using it's displayed index from the address book.
 */
public class ShowCommand extends Command {

    public static final String COMMAND_WORD = "show";
    public static final String COMMAND_WORD_ALIAS = "s";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows the person identified by the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SHOW_PERSON_SUCCESS = "Showing person: %1$s";

    private final Index targetIndex;

    public ShowCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToShow = lastShownList.get(targetIndex.getZeroBased());
        requireNonNull(personToShow);
        model.setTargetPerson(personToShow);
        return new CommandResult(
                String.format(MESSAGE_SHOW_PERSON_SUCCESS, personToShow.getName().fullName),
                SecondaryPaneState.TARGET_PERSON);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ShowCommand // instanceof handles nulls
                && targetIndex.equals(((ShowCommand) other).targetIndex)); // state check
    }
}
