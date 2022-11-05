package seedu.modquik.logic.commands.tutorial;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.modquik.commons.core.Messages;
import seedu.modquik.commons.core.index.Index;
import seedu.modquik.logic.commands.Command;
import seedu.modquik.logic.commands.CommandResult;
import seedu.modquik.logic.commands.exceptions.CommandException;
import seedu.modquik.model.Model;
import seedu.modquik.model.ModelType;
import seedu.modquik.model.tutorial.Tutorial;

/**
 * Deletes a tutorial identified using it's displayed index from the modquik book.
 */
public class DeleteTutorialCommand extends Command {

    public static final String COMMAND_WORD = "delete tutorial";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the tutorial identified by the index number used in the displayed tutorial list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_TUTORIAL_SUCCESS = "Deleted Tutorial: %1$s";

    private final Index targetIndex;

    public DeleteTutorialCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Tutorial> lastShownList = model.getFilteredTutorialList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TUTORIAL_DISPLAYED_INDEX);
        }

        Tutorial tutorialToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteTutorial(tutorialToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_TUTORIAL_SUCCESS, tutorialToDelete),
                ModelType.TUTORIAL);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteTutorialCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteTutorialCommand) other).targetIndex)); // state check
    }
}
