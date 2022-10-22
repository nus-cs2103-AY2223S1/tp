package seedu.trackascholar.logic.commands;

import seedu.trackascholar.commons.core.Messages;
import seedu.trackascholar.commons.core.index.Index;
import seedu.trackascholar.logic.commands.exceptions.CommandException;
import seedu.trackascholar.model.Model;
import seedu.trackascholar.model.applicant.Applicant;

import java.util.List;

import static java.util.Objects.requireNonNull;

public class PinCommand extends Command {
    public static final String COMMAND_WORD = "pin";
    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Pins the applicant identified by the index number used in the displayed applicant list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_PIN_APPLICANT_SUCCESS = "Pinned Applicant: %1$s";

    private final Index targetIndex;

    public PinCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Applicant> lastShownList = model.getFilteredApplicantList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_APPLICANT_DISPLAYED_INDEX);
        }

        Applicant originalApplicant = lastShownList.get(targetIndex.getZeroBased());
        final Applicant copyOriginalApplicant = originalApplicant;
        originalApplicant.setHasPinnedInPin(true);
        Applicant pinnedApplicant = originalApplicant;
        model.setApplicant(copyOriginalApplicant,pinnedApplicant);
        return new CommandResult(String.format(MESSAGE_PIN_APPLICANT_SUCCESS, copyOriginalApplicant));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof PinCommand // instanceof handles nulls
                && targetIndex.equals(((PinCommand) other).targetIndex)); // state check
    }
}
