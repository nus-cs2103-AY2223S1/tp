package seedu.uninurse.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;

import java.util.List;
import java.util.Optional;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.task.DateTime;
import seedu.uninurse.model.task.NonRecurringTask;
import seedu.uninurse.model.task.RecurringTask;
import seedu.uninurse.model.task.Task;
import seedu.uninurse.model.task.TaskList;
import seedu.uninurse.model.task.exceptions.DuplicateTaskException;

/**
 * Edits the details of an existing Task for a patient.
 */
public class EditTaskCommand extends EditGenericCommand {
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the specified task associated with a patient "
            + "by the index number used in the displayed patient list. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: PATIENT INDEX (must be a positive integer) TASK INDEX (must be a positive integer) "
            + "[" + PREFIX_TASK_DESCRIPTION + "TASK]...\n"
            + "Example: " + COMMAND_WORD + " 1 " + " 2 "
            + PREFIX_TASK_DESCRIPTION + "change bandage";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited task %1$d of %2$s:\n"
            + "Before: %3$s\n"
            + "After: %4$s";
    public static final String MESSAGE_NOT_EDITED = "Task to edit must be provided.";

    public static final CommandType EDIT_TASK_COMMAND_TYPE = CommandType.TASK;

    private final Index patientIndex;
    private final Index taskIndex;
    private final EditTaskDescriptor editTaskDescriptor;

    /**
     * @param patientIndex of the patient in the filtered patient list to edit.
     * @param taskIndex of the task to be edited.
     * @param editTaskDescriptor descriptor with details to edit for the task.
     */
    public EditTaskCommand(Index patientIndex, Index taskIndex, EditTaskDescriptor editTaskDescriptor) {
        requireAllNonNull(patientIndex, taskIndex, editTaskDescriptor);

        this.patientIndex = patientIndex;
        this.taskIndex = taskIndex;
        this.editTaskDescriptor = editTaskDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Patient> lastShownList = model.getFilteredPersonList();

        if (patientIndex.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Patient patientToEdit = lastShownList.get(patientIndex.getZeroBased());
        TaskList initialTaskList = patientToEdit.getTasks();

        if (taskIndex.getZeroBased() >= initialTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_INDEX);
        }

        Task initialTask = initialTaskList.get(taskIndex.getZeroBased());
        Task updatedTask;

        //TODO: don't use instanceof
        if (initialTask instanceof RecurringTask) {
            RecurringTask initialRecurringTask = (RecurringTask) initialTask;
            updatedTask = RecurringTask.parseRecurringTask(
                editTaskDescriptor.getDescription().orElse(initialRecurringTask.getTaskDescription()),
                editTaskDescriptor.getDateTime().orElse(initialRecurringTask.getDateTime()),
                editTaskDescriptor.getRecurrenceAndFrequency().orElse(
                        initialRecurringTask.getFrequency() + " " + initialRecurringTask.getRecurrence()));
        } else if (editTaskDescriptor.getRecurrenceAndFrequency().isPresent()) {
            updatedTask = RecurringTask.parseRecurringTask(
                editTaskDescriptor.getDescription().orElse(initialTask.getTaskDescription()),
                editTaskDescriptor.getDateTime().orElse(initialTask.getDateTime()),
                editTaskDescriptor.getRecurrenceAndFrequency().get());
        } else {
            updatedTask = new NonRecurringTask(
                editTaskDescriptor.getDescription().orElse(initialTask.getTaskDescription()),
                editTaskDescriptor.getDateTime().orElse(initialTask.getDateTime()));
        }

        if (patientToEdit.getTasks().hasTask(updatedTask)) {
            throw new CommandException(Messages.MESSAGE_DUPLICATE_TASK);
        }

        try {
            TaskList updatedTaskList = initialTaskList.edit(taskIndex.getZeroBased(), updatedTask);

            Patient editedPatient = new Patient(patientToEdit, updatedTaskList);

            model.setPerson(patientToEdit, editedPatient);
            model.setPatientOfInterest(editedPatient);

            return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, taskIndex.getOneBased(),
                    editedPatient.getName(), initialTask, updatedTask), EDIT_TASK_COMMAND_TYPE);
        } catch (DuplicateTaskException dte) {
            throw new CommandException(Messages.MESSAGE_DUPLICATE_TASK);
        }
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof EditTaskCommand)) {
            return false;
        }

        // state check
        EditTaskCommand o = (EditTaskCommand) other;
        return patientIndex.equals(o.patientIndex)
                && taskIndex.equals(o.taskIndex)
                && editTaskDescriptor.equals(o.editTaskDescriptor);
    }

    /**
     * Stores the details to edit the task with. Each non-empty field value
     * will replace the corresponding field value of the task.
     */
    public static class EditTaskDescriptor {
        private final Optional<String> description;
        private final Optional<DateTime> dateAndTime;
        private final Optional<String> recurAndFreq;
        /**
         * Constructs a {@code EditTaskDescriptor} that contains optional fields to edit an existing task.
         *
         * @param description An optional valid task description.
         * @param dateAndTime An optional valid task date and time.
         * @param recurAndFreq An optional valid recurrence and frequency.
         */
        public EditTaskDescriptor(Optional<String> description, Optional<DateTime> dateAndTime,
                Optional<String> recurAndFreq) {
            this.description = description;
            this.dateAndTime = dateAndTime;
            this.recurAndFreq = recurAndFreq;
        }

        public Optional<String> getDescription() {
            return description;
        }

        public Optional<DateTime> getDateTime() {
            return dateAndTime;
        }

        public Optional<String> getRecurrenceAndFrequency() {
            return recurAndFreq;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof EditTaskDescriptor)) {
                return false;
            }

            // state check
            EditTaskDescriptor o = (EditTaskDescriptor) other;

            return description.equals(o.description)
                    && dateAndTime.equals(o.dateAndTime)
                    && recurAndFreq.equals(o.recurAndFreq);
        }
    }
}
