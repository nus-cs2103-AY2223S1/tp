package seedu.masslinkers.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import javafx.collections.ObservableList;
import seedu.masslinkers.commons.core.Messages;
import seedu.masslinkers.commons.core.index.Index;
import seedu.masslinkers.logic.commands.exceptions.CommandException;
import seedu.masslinkers.model.Model;
import seedu.masslinkers.model.student.Mod;
import seedu.masslinkers.model.student.Student;
import seedu.masslinkers.ui.MainWindow;

//@author jonasgwt
/**
 * Appends new mods to the batchmate.
 */
public class ModAddCommand extends ModCommand {
    public static final String COMMAND_WORD = "add";
    public static final String MESSAGE_SUCCESS = "Successfully added the specified mod(s).";
    private MainWindow mainWindow;
    private final Index targetIndex;
    private final ObservableList<Mod> mods;

    /**
     * Constructs a command that adds a set of mods to the batchmate
     * with the target index.
     * @param index The index of the batchmate to add to.
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
     * @return feedback message of the operation result for display.
     * @throws CommandException If an error occurs during command execution.
     */
    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Student> lastShownList = model.getFilteredStudentList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_STUDENT_DISPLAYED_INDEX);
        }

        Student studentToEdit = lastShownList.get(targetIndex.getZeroBased());
        studentToEdit.addMods(mods);
        return new CommandResult(String.format(MESSAGE_SUCCESS, studentToEdit), false, false, false, false);
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
