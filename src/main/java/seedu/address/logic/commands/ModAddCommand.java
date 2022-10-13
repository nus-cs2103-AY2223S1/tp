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
import seedu.address.ui.MainWindow;

/**
 * Appends new mods to the person.
 */
public class ModAddCommand extends ModCommand {
    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_SUCCESS = "Successfully added the specified mods.";
    private MainWindow mainWindow;
    private final Index targetIndex;
    private final ObservableList<Mod> mods;

    /**
     * Constructs a command that adds a set of mods to the person
     * with the target index.
     * @param index The index of the person to add to.
     * @param mods The set of mods to add to.
     */
    public ModAddCommand(Index index, ObservableList<Mod> mods) {
        requireNonNull(index);
        requireNonNull(mods);

        this.targetIndex = index;
        this.mods = mods;
    }

    /**
     * Executes the command and returns the result message.
     *
     * @param model {@code Model} which the command should operate on.
     * @return feedback message of the operation result for display
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
        personToEdit.addMods(mods);
        return new CommandResult(String.format(MESSAGE_SUCCESS, personToEdit));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModAddCommand)) {
            return false;
        }

        // state check
        ModAddCommand e = (ModAddCommand) other;
        return targetIndex.equals(e.targetIndex)
                && mods.equals(e.mods);
    }
}
