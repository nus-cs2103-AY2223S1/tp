package seedu.uninurse.logic.commands;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_CONDITION;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_PATIENT_INDEX;

import java.util.List;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.logic.commands.exceptions.DuplicateEntryException;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.PersonListTracker;
import seedu.uninurse.model.condition.Condition;
import seedu.uninurse.model.condition.ConditionList;
import seedu.uninurse.model.condition.exceptions.DuplicateConditionException;
import seedu.uninurse.model.exceptions.PatientNotFoundException;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.person.Person;

/**
 * Adds a medical condition to an existing patient in the patient list.
 */
public class AddConditionCommand extends AddGenericCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX
            + ": Adds a medical condition to a patient.\n"
            + "Format: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " PATIENT_INDEX "
            + PREFIX_CONDITION + "CONDITION\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " 2 "
            + PREFIX_CONDITION + "Hypertension";
    public static final String MESSAGE_SUCCESS = "New condition added to %1$s: %2$s";
    public static final CommandType COMMAND_TYPE = CommandType.EDIT_PATIENT;

    private final Index index;
    private final Condition condition;

    /**
     * Creates an AddConditionCommand to add a condition to the specified person.
     *
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
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient patientToEdit;

        try {
            patientToEdit = model.getPatient(lastShownList.get(index.getZeroBased()));
        } catch (PatientNotFoundException pnfe) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT);
        }

        try {
            ConditionList updatedConditionList = patientToEdit.getConditions().add(condition);

            Patient editedPatient = new Patient(patientToEdit, updatedConditionList);

            PersonListTracker personListTracker = model.setPatient(patientToEdit, editedPatient);
            model.setPatientOfInterest(editedPatient);

            return new CommandResult(String.format(MESSAGE_SUCCESS, editedPatient.getName(), condition),
                    COMMAND_TYPE, personListTracker);
        } catch (DuplicateConditionException dce) {
            model.setPatientOfInterest(patientToEdit);
            throw new DuplicateEntryException(
                    String.format(Messages.MESSAGE_DUPLICATE_CONDITION, patientToEdit.getName()));
        }
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
        AddConditionCommand o = (AddConditionCommand) other;
        return index.equals(o.index) && condition.equals((o.condition));
    }
}
