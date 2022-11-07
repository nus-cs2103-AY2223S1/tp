package seedu.uninurse.logic.commands;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_CONDITION;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_CONDITION_INDEX;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_PATIENT_INDEX;

import java.util.List;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.logic.commands.exceptions.DuplicateEntryException;
import seedu.uninurse.logic.commands.exceptions.InvalidAttributeIndexException;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.PersonListTracker;
import seedu.uninurse.model.condition.Condition;
import seedu.uninurse.model.condition.ConditionList;
import seedu.uninurse.model.condition.exceptions.DuplicateConditionException;
import seedu.uninurse.model.exceptions.PatientNotFoundException;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.person.Person;

/**
 * Edits the details of an existing condition for a patient.
 */
public class EditConditionCommand extends EditGenericCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
            + PREFIX_OPTION_PATIENT_INDEX + " " + PREFIX_OPTION_CONDITION_INDEX
            + ": Edits a medical condition of a patient.\n"
            + "Format: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " PATIENT_INDEX "
            + PREFIX_OPTION_CONDITION_INDEX + " CONDITION_INDEX " + PREFIX_CONDITION + "CONDITION\n"
            + "Example: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " 2 " + PREFIX_OPTION_CONDITION_INDEX
            + " 1 " + PREFIX_CONDITION + "Hypertension";
    public static final String MESSAGE_SUCCESS = "Edited condition %1$d of %2$s:\n"
            + "Before: %3$s\n"
            + "After: %4$s";
    public static final CommandType COMMAND_TYPE = CommandType.EDIT_PATIENT;

    private final Index patientIndex;
    private final Index conditionIndex;
    private final Condition editedCondition;

    /**
     * Creates an EditConditionCommand to edit a condition from the specified patient.
     *
     * @param patientIndex The index of the patient in the filtered patient list to edit.
     * @param conditionIndex The index of the condition in the patient's condition list.
     * @param editedCondition The edited condition.
     */
    public EditConditionCommand(Index patientIndex, Index conditionIndex, Condition editedCondition) {
        requireAllNonNull(patientIndex, conditionIndex, editedCondition);

        this.patientIndex = patientIndex;
        this.conditionIndex = conditionIndex;
        this.editedCondition = editedCondition;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireAllNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (patientIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient patientToEdit;

        try {
            patientToEdit = model.getPatient(lastShownList.get(patientIndex.getZeroBased()));
        } catch (PatientNotFoundException pnfe) {
            throw new CommandException(Messages.MESSAGE_INVALID_PATIENT);
        }

        ConditionList initialConditionList = patientToEdit.getConditions();

        if (conditionIndex.getZeroBased() >= initialConditionList.size()) {
            model.setPatientOfInterest(patientToEdit);
            throw new InvalidAttributeIndexException(Messages.MESSAGE_INVALID_CONDITION_INDEX);
        }

        try {
            Condition initialCondition = initialConditionList.get(conditionIndex.getZeroBased());
            ConditionList updatedConditionList = initialConditionList.edit(conditionIndex.getZeroBased(),
                    editedCondition);

            Patient editedPatient = new Patient(patientToEdit, updatedConditionList);

            PersonListTracker personListTracker = model.setPatient(patientToEdit, editedPatient);
            model.setPatientOfInterest(editedPatient);

            return new CommandResult(String.format(MESSAGE_SUCCESS,
                    conditionIndex.getOneBased(), editedPatient.getName(), initialCondition, editedCondition),
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
        if (!(other instanceof EditConditionCommand)) {
            return false;
        }

        // state check
        EditConditionCommand o = (EditConditionCommand) other;
        return patientIndex.equals(o.patientIndex)
                && conditionIndex.equals(o.conditionIndex)
                && editedCondition.equals(o.editedCondition);
    }
}
