package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Mod;
import seedu.address.model.person.Person;

/**
 * Unmarks mods of the batchmate specified as not taken.
 */
public class ModUnmarkCommand extends ModCommand {

    public static final String COMMAND_WORD = "unmark";
    public static final String MESSAGE_SUCCESS = "Successfully unmarked the specified mods.";
    public static final String MESSAGE_INVALID_MOD = "This batchmate is not taking all of the modules specified."
            + "\nPlease check the list of mods and try again.";
    private final Index targetIndex;
    private final ObservableList<Mod> mods;

    /**
     * Constructs a command that unmarks all mods specified at the target batchmate as not taken.
     *
     * @param index The index of the batchmate.
     * @param mods The set of mods to be unmarked.
     */
    public ModUnmarkCommand(Index index, ObservableList<Mod> mods) {
        requireNonNull(index);
        requireNonNull(mods);

        this.targetIndex = index;
        this.mods = mods;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(targetIndex.getZeroBased());

        if (personToEdit.canEditMods(mods)) {
            personToEdit.unmarkMods(mods);
        } else {
            throw new CommandException(MESSAGE_INVALID_MOD);
        }

        return new CommandResult(
                String.format(MESSAGE_SUCCESS, personToEdit),
                false,
                false,
                false);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModUnmarkCommand)) {
            return false;
        }

        // state check
        ModUnmarkCommand e = (ModUnmarkCommand) other;
        return targetIndex.equals(e.targetIndex)
                && mods.equals(e.mods);
    }
}
