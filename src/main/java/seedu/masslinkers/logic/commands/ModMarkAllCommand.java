package seedu.masslinkers.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.masslinkers.logic.commands.exceptions.CommandException;
import seedu.masslinkers.model.Model;
import seedu.masslinkers.model.student.Mod;
import seedu.masslinkers.model.student.Student;

/**
 * Marks mods of all batchmates as taken.
 */
public class ModMarkAllCommand extends ModCommand {

    public static final String COMMAND_WORD = "mark all";
    public static final String MESSAGE_SUCCESS = "Successfully marked %s of all batchmates.\n";
    public static final String MESSAGE_INVALID_MOD = "There is no batchmate taking %s, hence it's not marked.\n";
    public static final String MESSAGE_INVALID_MODS = "All mods specified are not being taken by any batchmate.\n"
            + "Please check the list of mods and try again.";

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

        List<Student> lastShownList = model.getFilteredStudentList();
        String message = "";
        int markedModsCount = 0;

        for (Mod mod : mods) {
            int markedModCount = 0;
            for (Student student : lastShownList) {
                markedModCount = markedModCount + student.markModIfExist(mod);
            }

            if (markedModCount == 0) {
                // No batchmate is taking the specified mod.
                message = message + String.format(MESSAGE_INVALID_MOD, mod.getModName());
            } else {
                message = message + String.format(MESSAGE_SUCCESS, mod.getModName());
            }

            markedModsCount = markedModsCount + markedModCount;
        }

        if (markedModsCount == 0) {
            // No mod in mods has been successfully marked.
            throw new CommandException(MESSAGE_INVALID_MODS);
        }

        return new CommandResult(
                message, false, false, false, false);
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
