package seedu.uninurse.logic.commands;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_CONDITION;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_PATIENT_INDEX;

import java.util.List;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.PatientListTracker;
import seedu.uninurse.model.condition.Condition;
import seedu.uninurse.model.condition.ConditionList;
import seedu.uninurse.model.condition.exceptions.DuplicateConditionException;
import seedu.uninurse.model.person.Patient;

/**
 * Add a medical condition to an existing patient in the patient list.
 */
public class AddConditionCommand extends AddGenericCommand {
    public static final String MESSAGE_USAGE = "Command: Add a medical condition to a patient.\n"
            + "Format: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " PATIENT_INDEX "
            + PREFIX_CONDITION + "CONDITION\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " 1 "
            + PREFIX_CONDITION + "Hypertension";

    public static final String MESSAGE_ADD_CONDITION_SUCCESS = "New condition added to %1$s: %2$s";
    public static final String MESSAGE_DUPLICATE_CONDITION = "This condition already exists in %1$s's condition list";
    public static final CommandType ADD_CONDITION_COMMAND_TYPE = CommandType.EDIT_PATIENT;

    private final Index index;
    private final Condition condition;

    /**
     * Creates an AddConditionCommand to add a {@code Condition} to the specified person.
     * @param index The index of the person in the filtered person list to add the condition.
     * @param condition The condition of the person to be added to.
     */
    public AddConditionCommand(Index index, Condition condition) {
        requireAllNonNull(index, condition);

        this.index = index;
        this.condition = condition;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);
        List<Patient> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(index.getZeroBased());
        ConditionList updatedConditionList;

        try {
            updatedConditionList = patientToEdit.getConditions().add(condition);
        } catch (DuplicateConditionException dce) {
            throw new CommandException(String.format(MESSAGE_DUPLICATE_CONDITION, patientToEdit.getName()));
        }

        Patient editedPatient = new Patient(patientToEdit, updatedConditionList);

        PatientListTracker patientListTracker = model.setPerson(patientToEdit, editedPatient);
        model.setPatientOfInterest(editedPatient);

        return new CommandResult(String.format(MESSAGE_ADD_CONDITION_SUCCESS, editedPatient.getName(), condition),
                ADD_CONDITION_COMMAND_TYPE, patientListTracker);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddConditionCommand)) {
            return false;
        }

        // state check
        AddConditionCommand command = (AddConditionCommand) other;
        return index.equals(command.index) && condition.equals((command.condition));
    }
}
