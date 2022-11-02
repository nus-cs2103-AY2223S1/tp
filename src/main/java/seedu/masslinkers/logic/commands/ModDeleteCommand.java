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

//@author ElijahS67
/**
 * Deletes mods from the batchmate specified.
 */
public class ModDeleteCommand extends ModCommand {

    public static final String COMMAND_WORD = "delete";
    public static final String MESSAGE_SUCCESS = "Successfully deleted the specified mod(s).";
    public static final String MESSAGE_INVALID_MOD = "This batchmate is not taking all of the modules specified."
            + "\nPlease check the list of mods and try again.";
    private final Index targetIndex;
    private final ObservableList<Mod> mods;

    /**
     * Constructs a command that deletes all mods specified from the list of the batchmate at the target index.
     *
     * @param index The index of the batchmate.
     * @param mods The set of mods to be deleted.
     */
    public ModDeleteCommand(Index index, ObservableList<Mod> mods) {
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

        if (studentToEdit.canEditMods(mods)) {
            studentToEdit.deleteMods(mods);
        } else {
            throw new CommandException(MESSAGE_INVALID_MOD);
        }

        return new CommandResult(String.format(MESSAGE_SUCCESS, studentToEdit));
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ModDeleteCommand)) {
            return false;
        }

        // state check
        ModDeleteCommand e = (ModDeleteCommand) other;
        return targetIndex.equals(e.targetIndex)
                && mods.equals(e.mods);
    }
}
