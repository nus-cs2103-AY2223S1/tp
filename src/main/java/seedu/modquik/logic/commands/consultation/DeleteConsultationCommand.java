package seedu.modquik.logic.commands.consultation;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.modquik.commons.core.Messages;
import seedu.modquik.commons.core.index.Index;
import seedu.modquik.logic.commands.Command;
import seedu.modquik.logic.commands.CommandResult;
import seedu.modquik.logic.commands.exceptions.CommandException;
import seedu.modquik.model.Model;
import seedu.modquik.model.ModelType;
import seedu.modquik.model.consultation.Consultation;

/**
 * Deletes a consultation identified using it's displayed index from ModQuik.
 */
public class DeleteConsultationCommand extends Command {
    public static final String COMMAND_WORD = "delete consultation";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the consultation identified by the index number used in the displayed consultation list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_DELETE_CONSULTATION_SUCCESS = "Deleted Consultation: %1$s";

    private final Index targetIndex;

    public DeleteConsultationCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Consultation> lastShownList = model.getFilteredConsultationList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONSULTATION_DISPLAYED_INDEX);
        }

        Consultation consultationToDelete = lastShownList.get(targetIndex.getZeroBased());
        model.deleteConsultation(consultationToDelete);
        return new CommandResult(String.format(MESSAGE_DELETE_CONSULTATION_SUCCESS, consultationToDelete),
                ModelType.CONSULTATION);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeleteConsultationCommand // instanceof handles nulls
                && targetIndex.equals(((DeleteConsultationCommand) other).targetIndex)); // state check
    }
}
