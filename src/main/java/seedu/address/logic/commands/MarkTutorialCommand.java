package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tutorial.Tutorial;


/**
 * Marks a tutorial as complete.
 */
public class MarkTutorialCommand extends Command {

    public static final String COMMAND_WORD = "marktut";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks a tutorial as complete "
            + "by the index number used in the last person listing.\n "
            + "Parameters: INDEX (must be a positive integer)\n "
            + "Example: "
            + COMMAND_WORD + " 1";

    public static final String MESSAGE_MARKTUT_SUCCESS = "Marked:\n%1$s";

    private final Index index;

    /**
     * Creates a MarkTutorialCommand to mark the tutorial
     *
     * @param index The position of the tutorial in the tutorial list
     */
    public MarkTutorialCommand(Index index) {
        requireAllNonNull(index);

        this.index = index;
    }

    @Override
    public boolean equals(Object other) {

        if (other == this) {
            return true;
        }

        if (!(other instanceof MarkTutorialCommand)) {
            return false;
        }

        MarkTutorialCommand e = (MarkTutorialCommand) other;
        return index.equals(e.index);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Tutorial> lastShownList = model.getFilteredTutorialList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TUTORIAL_DISPLAYED_INDEX);
        }

        Tutorial tutorialToEdit = lastShownList.get(index.getZeroBased());
        Tutorial editedTutorial = new Tutorial(
                tutorialToEdit.getGroup(), tutorialToEdit.getContent(), tutorialToEdit.getTime(), true
        );

        model.setTutorial(tutorialToEdit, editedTutorial);
        model.updateFilteredTutorialList(Model.PREDICATE_SHOW_ALL_TUTORIALS);

        return new CommandResult(String.format(MESSAGE_MARKTUT_SUCCESS, editedTutorial));
    }
}
