package swift.logic.commands;

import static java.util.Objects.requireNonNull;
import static swift.logic.parser.CliSyntax.PREFIX_DEADLINE;
import static swift.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static swift.logic.parser.CliSyntax.PREFIX_NAME;
import static swift.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import swift.commons.core.Messages;
import swift.commons.core.index.Index;
import swift.commons.util.CollectionUtil;
import swift.logic.commands.exceptions.CommandException;
import swift.logic.parser.Prefix;
import swift.model.Model;
import swift.model.task.Deadline;
import swift.model.task.Description;
import swift.model.task.Task;
import swift.model.task.TaskName;

/**
 * Edits the details of an existing task in the address book.
 */
public class EditTaskCommand extends Command {

    public static final String COMMAND_WORD = "edit_task";
    public static final ArrayList<Prefix> ARGUMENT_PREFIXES =
            new ArrayList<>(List.of(new Prefix("", "task_index"), PREFIX_NAME, PREFIX_DESCRIPTION, PREFIX_DEADLINE));

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the task identified "
        + "by the index number used in the displayed task list. "
        + "Existing values will be overwritten by the input values.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + "[" + PREFIX_NAME + "NAME] "
        + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
        + "[" + PREFIX_DEADLINE + "DEADLINE] (must be in dd-MM-yyyy HHmm format)\n"
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_NAME + "Finish Assignment "
        + PREFIX_DESCRIPTION + "CS1231 "
        + PREFIX_DEADLINE + "12-12-2022 1800";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s";
    public static final String MESSAGE_NOT_EDITED = "At least one field to edit must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the address book.";

    private final Index index;
    private final EditTaskDescriptor editTaskDescriptor;

    /**
    * @param index of the task in the filtered task list to edit
    * @param editTaskDescriptor details to edit the task with
    */
    public EditTaskCommand(Index index, EditTaskDescriptor editTaskDescriptor) {
        requireNonNull(index);
        requireNonNull(editTaskDescriptor);

        this.index = index;
        this.editTaskDescriptor = new EditTaskDescriptor(editTaskDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToEdit = lastShownList.get(index.getZeroBased());
        Task editedTask = createEditedTask(taskToEdit, editTaskDescriptor);

        if (!taskToEdit.equals(editedTask) && model.hasTask(editedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.setTask(taskToEdit, editedTask);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask), CommandType.TASKS);
    }

    /**
    * Creates and returns a {@code Task} with the details of {@code taskToEdit}
    * edited with {@code editTaskDescriptor}.
    */
    public static Task createEditedTask(Task taskToEdit, EditTaskDescriptor editTaskDescriptor) {
        assert taskToEdit != null;

        UUID updatedId = taskToEdit.getId();
        TaskName updatedTaskName = editTaskDescriptor.getTaskName().orElse(taskToEdit.getName());
        Optional<Description> updatedDescription = editTaskDescriptor.getDescription().or(taskToEdit::getDescription);
        Optional<Deadline> updatedDeadline = editTaskDescriptor.getDeadline().or(taskToEdit::getDeadline);
        boolean updatedisDone = editTaskDescriptor.isDone().orElse(taskToEdit.isDone());

        return new Task(updatedId, updatedTaskName, updatedDescription, updatedDeadline, updatedisDone);
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
        EditTaskCommand e = (EditTaskCommand) other;
        return index.equals(e.index)
            && editTaskDescriptor.equals(e.editTaskDescriptor);
    }

    /**
    * Stores the details to edit the task with. Each non-empty field value will replace the
    * corresponding field value of the task.
    */
    public static class EditTaskDescriptor {
        private TaskName taskName;
        private Description description;
        private Deadline deadline;
        private Index contactIndex;
        private Boolean isDone;

        public EditTaskDescriptor() {}

        /**
        * Copy constructor.
        * A defensive copy of {@code tags} is used internally.
        */
        public EditTaskDescriptor(EditTaskDescriptor toCopy) {
            setTaskName(toCopy.taskName);
            setDescription(toCopy.description);
            setDeadline(toCopy.deadline);
            setContactIndex(toCopy.contactIndex);
            setIsDone(toCopy.isDone);
        }

        /**
        * Returns true if at least one field is edited.
        */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(taskName, description, deadline, contactIndex);
        }

        public void setTaskName(TaskName taskName) {
            this.taskName = taskName;
        }

        public Optional<TaskName> getTaskName() {
            return Optional.ofNullable(taskName);
        }

        public void setDescription(Description description) {
            this.description = description;
        }

        public Optional<Description> getDescription() {
            return Optional.ofNullable(description);
        }

        public void setDeadline(Deadline deadline) {
            this.deadline = deadline;
        }

        public Optional<Deadline> getDeadline() {
            return Optional.ofNullable(deadline);
        }

        public void setContactIndex(Index contactIndex) {
            this.contactIndex = contactIndex;
        }

        public Optional<Index> getContactIndex() {
            return Optional.ofNullable(contactIndex);
        }

        public void setIsDone(Boolean isDone) {
            this.isDone = isDone;
        }

        public Optional<Boolean> isDone() {
            return Optional.ofNullable(isDone);
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
            EditTaskDescriptor e = (EditTaskDescriptor) other;

            return getTaskName().equals(e.getTaskName())
                    && getDescription().equals(e.getDescription())
                    && getDeadline().equals(e.getDeadline())
                    && getContactIndex().equals(e.getContactIndex());
        }
    }
}
