package seedu.uninurse.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;

import java.util.List;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.condition.Condition;
import seedu.uninurse.model.condition.ConditionList;
import seedu.uninurse.model.person.Patient;

/**
 * Deletes a medical condition from a patient identified using its displayed index from the patient list.
 */
public class DeleteConditionCommand extends DeleteGenericCommand {
    // tentative syntax; TODO: integrate with DeleteGenericCommand
    public static final String COMMAND_WORD = "deleteCondition";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Deletes the medical condition identified by the index number in the condition list of the patient "
            + "identified by the index number used in the last patient listing.\n"
            + "Parameters: PATIENT_INDEX (must be a positive integer) "
            + "CONDITION_INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1 2";

    public static final String MESSAGE_DELETE_CONDITION_SUCCESS = "Deleted condition from %1$s: %2$s";

    private final Index patientIndex;
    private final Index conditionIndex;

    /**
     * Creates an DeleteConditionCommand to delete a {@code Condition} from the specified patient.
     *
     * @param patientIndex The index of the patient in the filtered person list to delete the condition.
     * @param conditionIndex The index of the condition in the patient's condition list.
     */
    public DeleteConditionCommand(Index patientIndex, Index conditionIndex) {
        requireAllNonNull(patientIndex, conditionIndex);

        this.patientIndex = patientIndex;
        this.conditionIndex = conditionIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPersonList();

        if (patientIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(patientIndex.getZeroBased());
        ConditionList initialConditionList = patientToEdit.getConditions();

        if (conditionIndex.getZeroBased() >= initialConditionList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_CONDITION_INDEX);
        }

        ConditionList updatedConditionList = patientToEdit.getConditions().delete(conditionIndex.getZeroBased());
        Condition deletedCondition = initialConditionList.get(conditionIndex.getZeroBased());

        Patient editedPerson = new Patient(
                patientToEdit.getName(), patientToEdit.getPhone(), patientToEdit.getEmail(),
                patientToEdit.getAddress(), updatedConditionList, patientToEdit.getTasks(), patientToEdit.getTags());

        model.setPerson(patientToEdit, editedPerson);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);

        return new CommandResult(String.format(MESSAGE_DELETE_CONDITION_SUCCESS, editedPerson.getName(),
                deletedCondition), CommandType.DELETE_CONDITION);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteConditionCommand)) {
            return false;
        }

        // state check
        DeleteConditionCommand e = (DeleteConditionCommand) other;
        return patientIndex.equals(e.patientIndex) && conditionIndex.equals((e.conditionIndex));
    }
}
