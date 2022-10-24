package seedu.uninurse.logic.commands;

import static java.util.Objects.requireNonNull;

import java.util.List;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.person.Patient;

/**
 * Shows a given patient's details in the uninurse book to the user.
 */
public class ViewPatientCommand extends Command {
    public static final String COMMAND_WORD = "view";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Shows a patient's details identified by the index number used in the displayed patient list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";

    public static final String MESSAGE_SUCCESS = "Showing Patient: %1$s";

    public static final CommandType VIEW_PATIENT_COMMAND_TYPE = CommandType.VIEW_PATIENT;

    private final Index targetIndex;

    public ViewPatientCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient patient = lastShownList.get(targetIndex.getZeroBased());
        model.setPatientOfInterest(patient);

        return new CommandResult(String.format(MESSAGE_SUCCESS, patient.getName()), VIEW_PATIENT_COMMAND_TYPE);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ViewPatientCommand // instanceof handles nulls
                && targetIndex.equals(((ViewPatientCommand) other).targetIndex)); // state check
    }
}
