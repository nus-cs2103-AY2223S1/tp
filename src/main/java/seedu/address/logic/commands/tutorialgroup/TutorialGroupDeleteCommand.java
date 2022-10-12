package seedu.address.logic.commands.tutorialgroup;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.student.TutorialGroup;



/**
 * Adds a tutorial group to the address book.
 */
public class TutorialGroupDeleteCommand extends Command {
    public static final String COMMAND_WORD = "tutorialDelete";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a tutorial group to the address book. "
        + "Parameters: ";

    public static final String MESSAGE_SUCCESS = "This tutorial group deleted: %1$s";

    public static final String MESSAGE_GROUP_NOT_FOUND = "This tutorial group is not found in the records";

    private Index targetIndex;

    /**
     * Creates an TaskAddCommand to add the specified {@code Person}
     */
    public TutorialGroupDeleteCommand(Index index) {
        targetIndex = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<TutorialGroup> lastShownList = model.getFilteredTutorialGroupList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(MESSAGE_GROUP_NOT_FOUND);
        }

        TutorialGroup toDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteTutorialGroup(toDelete);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                    || (other instanceof TutorialGroupDeleteCommand // instanceof handles nulls
                    && targetIndex.equals(((TutorialGroupDeleteCommand) other).targetIndex));
    }
}
