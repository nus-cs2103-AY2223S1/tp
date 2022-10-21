package tuthub.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import tuthub.commons.core.Messages;
import tuthub.commons.core.index.Index;
import tuthub.logic.commands.exceptions.CommandException;
import tuthub.model.Model;
import tuthub.model.tutor.Tutor;

/**
 * Views the full details of an existing tutor in tuthub.
 */
public class ViewCommand extends Command {

    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Views the full details of the tutor identified "
            + "by the index number used in the displayed tutor list. "
            + "Parameters: INDEX (must be a positive integer) "
            + "Example: " + COMMAND_WORD + " 1 ";

    public static final String MESSAGE_VIEW_TUTOR_SUCCESS = "Viewing Tutor: %1$s";

    private final Index index;

    /**
     * @param index of the tutor in the filtered tutor list to view
     * @param viewTutorDescriptor details to view the tutor with
     */
    public ViewCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Tutor> tutorList = model.getFilteredTutorList();

        if (index.getZeroBased() >= tutorList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TUTOR_DISPLAYED_INDEX);
        }

        Tutor tutorToView = tutorList.get(index.getZeroBased());

        model.setTutorToView(tutorToView);
        return new CommandResult(String.format(MESSAGE_VIEW_TUTOR_SUCCESS, tutorToView), true);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof ViewCommand)) {
            return false;
        }

        // state check
        ViewCommand e = (ViewCommand) other;
        return index.equals(e.index);
    }
}
