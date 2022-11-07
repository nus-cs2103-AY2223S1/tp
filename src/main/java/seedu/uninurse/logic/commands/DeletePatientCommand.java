package seedu.uninurse.logic.commands;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_PATIENT_INDEX;

import java.util.List;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.PersonListTracker;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.person.Person;

/**
 * Deletes a patient identified using its displayed index from the Patient list.
 */
public class DeletePatientCommand extends DeleteGenericCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX
            + ": Deletes a patient.\n"
            + "Format: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " PATIENT_INDEX\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " 2";
    public static final String MESSAGE_SUCCESS = "Deleted Patient: %1$s";
    public static final CommandType COMMAND_TYPE = CommandType.DELETE_PATIENT;

    private final Index targetIndex;

    public DeletePatientCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (targetIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient patientToDelete = model.getPatient(lastShownList.get(targetIndex.getZeroBased()));
        PersonListTracker personListTracker = model.deletePerson(patientToDelete);
        model.setPatientOfInterest(patientToDelete);

        return new CommandResult(String.format(MESSAGE_SUCCESS, patientToDelete),
                COMMAND_TYPE, personListTracker);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof DeletePatientCommand // instanceof handles nulls
                && targetIndex.equals(((DeletePatientCommand) other).targetIndex)); // state check
    }
}
