package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Mod;
import seedu.address.model.person.Person;

/**
 * Marks mods of all batchmates as taken.
 */
public class ModMarkAllCommand extends ModCommand {

    public static final String COMMAND_WORD = "mark all";
    public static final String MESSAGE_SUCCESS = "Successfully marked the specified mods of all batchmates.";

    private final ObservableList<Mod> mods;

    /**
     * Constructs a command that marks all mods specified of all batchmates as taken.
     *
     * @param mods The set of mods to be marked.
     */
    public ModMarkAllCommand(ObservableList<Mod> mods) {
        requireNonNull(mods);

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

        for (Mod mod : mods) {
            for (Person person : lastShownList) {
                person.markModIfExist(mod);
            }
        }

        return new CommandResult(
                String.format(MESSAGE_SUCCESS), false, false, false, false);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModMarkAllCommand)) {
            return false;
        }

        // state check
        ModMarkAllCommand e = (ModMarkAllCommand) other;
        return mods.equals(e.mods);
    }
}
