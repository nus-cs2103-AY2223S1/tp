package seedu.uninurse.logic.commands;

import static seedu.uninurse.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_PATIENT_INDEX;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_OPTION_TASK_INDEX;
import static seedu.uninurse.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;

import java.util.List;
import java.util.Optional;

import seedu.uninurse.commons.core.Messages;
import seedu.uninurse.commons.core.index.Index;
import seedu.uninurse.logic.commands.exceptions.CommandException;
import seedu.uninurse.logic.commands.exceptions.DuplicateEntryException;
import seedu.uninurse.logic.commands.exceptions.InvalidAttributeIndexException;
import seedu.uninurse.model.Model;
import seedu.uninurse.model.PersonListTracker;
import seedu.uninurse.model.exceptions.PatientNotFoundException;
import seedu.uninurse.model.person.Patient;
import seedu.uninurse.model.person.Person;
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
    public static final String MESSAGE_USAGE = COMMAND_WORD + " "
            + PREFIX_OPTION_PATIENT_INDEX + " " + PREFIX_OPTION_TASK_INDEX
            + ": Edits a task of a patient.\n"
            + "Format: " + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " PATIENT_INDEX "
            + PREFIX_OPTION_TASK_INDEX + " TASK_INDEX "
            + PREFIX_TASK_DESCRIPTION + "<TASK_DESCRIPTION> | <DATE TIME> | <INTERVAL TIME_PERIOD>\n"
            + "Examples:\n" + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " 2 "
            + PREFIX_OPTION_TASK_INDEX + " 1 "
            + PREFIX_TASK_DESCRIPTION + "Change dressing\n"
            + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " 2 "
            + PREFIX_OPTION_TASK_INDEX + " 1 "
            + PREFIX_TASK_DESCRIPTION + "| 22-4-22 1345\n"
            + COMMAND_WORD + " " + PREFIX_OPTION_PATIENT_INDEX + " 2 "
            + PREFIX_OPTION_TASK_INDEX + " 1 "
            + PREFIX_TASK_DESCRIPTION + "|| 3 weeks\n";
    public static final String MESSAGE_SUCCESS = "Edited task %1$d of %2$s:\n"
            + "Before: %3$s\n"
            + "After: %4$s";
    public static final String MESSAGE_FAILURE = "At least one field to edit in tasks must be provided.";
    public static final CommandType COMMAND_TYPE = CommandType.TASK;

    private final Index patientIndex;
    private final Index taskIndex;
    private final EditTaskDescriptor editTaskDescriptor;

    /**
     * Creates an EditTaskCommand to edit a Task from the specified patient.
     *
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

        TaskList initialTaskList = patientToEdit.getTasks();

        if (taskIndex.getZeroBased() >= initialTaskList.size()) {
            model.setPatientOfInterest(patientToEdit);
            throw new InvalidAttributeIndexException(Messages.MESSAGE_INVALID_TASK_INDEX);
        }

        Task initialTask = initialTaskList.get(taskIndex.getZeroBased());
        Task updatedTask;

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

        try {
            TaskList updatedTaskList = initialTaskList.edit(taskIndex.getZeroBased(), updatedTask);

            Patient editedPatient = new Patient(patientToEdit, updatedTaskList);

            PersonListTracker personListTracker = model.setPatient(patientToEdit, editedPatient);
            model.setPatientOfInterest(editedPatient);

            return new CommandResult(String.format(MESSAGE_SUCCESS,
                    taskIndex.getOneBased(), editedPatient.getName(), initialTask, updatedTask),
                    COMMAND_TYPE, personListTracker);
        } catch (DuplicateTaskException dte) {
            model.setPatientOfInterest(patientToEdit);
            throw new DuplicateEntryException(String.format(Messages.MESSAGE_DUPLICATE_TASK, patientToEdit.getName()));
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
        public static final String MESSAGE_CONSTRAINTS = "Task description should not end with \"|\" ";

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
