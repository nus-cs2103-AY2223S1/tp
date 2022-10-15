package tuthub.logic.commands;

import static java.util.Objects.requireNonNull;

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

    public static final String MESSAGE_EDIT_TUTOR_SUCCESS = "Viewing Tutor: %1$s";

    private final Index index;

    /**
     * @param index of the tutor in the filtered tutor list to edit
     * @param editTutorDescriptor details to edit the tutor with
     */
    public ViewCommand(Index index) {
        requireNonNull(index);
        this.index = index;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Tutor> lastShownList = model.getFilteredTutorList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TUTOR_DISPLAYED_INDEX);
        }

        Tutor tutorToEdit = lastShownList.get(index.getZeroBased());
        Tutor editedTutor = createEditedTutor(tutorToEdit, editTutorDescriptor);

        if (!tutorToEdit.isSameTutor(editedTutor) && model.hasTutor(editedTutor)) {
            throw new CommandException(MESSAGE_DUPLICATE_TUTOR);
        }

        model.setTutor(tutorToEdit, editedTutor);
        model.updateFilteredTutorList(PREDICATE_SHOW_ALL_TUTORS);
        return new CommandResult(String.format(MESSAGE_EDIT_TUTOR_SUCCESS, editedTutor));
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
