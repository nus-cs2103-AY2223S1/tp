package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.Set;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.tag.Tag;
import seedu.address.model.task.Task;

/**
 * Updates a task to the taskList at the specified index with specified details.
 */
public class UpdateTaskCommand extends Command {
    public static final String COMMAND_WORD = "updateTask";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Updates a task in the taskList\n"
            + "Parameters: Index, Task\n"
            + "Example: " + COMMAND_WORD + " 1 d/Purchase milk dl/2022-10-18 t/Food";
    public static final String MESSAGE_UPDATE_TASK_SUCCESS = "Task update complete: %1$s";
    public static final String MESSAGE_NOT_UPDATED = "At least one field to update must be provided.";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the address book";

    private final Index index;
    private final UpdateTaskDescriptor updateTaskDescriptor;

    /**
     * Creates an UpdateTaskCommand to update the specified {@code Task} at the specified {@code Index}.
     */
    public UpdateTaskCommand(Index index, UpdateTaskDescriptor updateTaskDescriptor) {
        requireNonNull(index);
        requireNonNull(updateTaskDescriptor);

        this.index = index;
        this.updateTaskDescriptor = new UpdateTaskDescriptor(updateTaskDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownTaskList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToUpdate = lastShownTaskList.get(index.getZeroBased());
        Task updatedTask = createUpdatedTask(taskToUpdate, updateTaskDescriptor);

        if (!taskToUpdate.equals(updatedTask) && model.hasTask(updatedTask)) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.setTask(updatedTask, index);
        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_UPDATE_TASK_SUCCESS, updatedTask));
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToUpdate}
     * updated with {@code updateTaskDescriptor}.
     */
    private static Task createUpdatedTask(Task taskToUpdate, UpdateTaskCommand.UpdateTaskDescriptor updateTaskDescriptor) {
        assert taskToUpdate != null;

        String updatedTitle = updateTaskDescriptor.getTitle().orElse(taskToUpdate.getTitle());
        String updatedDeadline = updateTaskDescriptor.getDeadline().orElse(taskToUpdate.getDeadline());
        boolean updatedStatus = updateTaskDescriptor.getStatus().orElse(taskToUpdate.getStatus());
        Set<Tag> updatedTags = updateTaskDescriptor.getTags().orElse(taskToUpdate.getTags());

        return new Task(updatedTitle, updatedDeadline, updatedStatus, updatedTags);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UpdateTaskCommand // instanceof handles nulls
                && index.equals(((UpdateTaskCommand) other).index)
                && updateTaskDescriptor.equals(((UpdateTaskCommand) other).updateTaskDescriptor));
    }

    /**
     * Stores the details to update the task with. Each non-empty field value will replace the
     * corresponding field value of the task.
     */
    public static class UpdateTaskDescriptor {
        private String title;
        private String deadline;
        private boolean status;
        private Set<Tag> tags;

        public UpdateTaskDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public UpdateTaskDescriptor(UpdateTaskCommand.UpdateTaskDescriptor toCopy) {
            setTitle(toCopy.title);
            setDeadline(toCopy.deadline);
            setStatus(toCopy.status);
            setTags(toCopy.tags);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(title, deadline, status, tags);
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Optional<String> getTitle() {
            return Optional.ofNullable(title);
        }

        public void setDeadline(String deadline) {
            this.deadline = deadline;
        }

        public Optional<String> getDeadline() {
            return Optional.ofNullable(deadline);
        }

        public void setStatus(boolean status) {
            this.status = status;
        }

        public Optional<Boolean> getStatus() {
            return Optional.ofNullable(status);
        }

        /**
         * Sets {@code tags} to this object's {@code tags}.
         * A defensive copy of {@code tags} is used internally.
         */
        public void setTags(Set<Tag> tags) {
            this.tags = (tags != null) ? new HashSet<>(tags) : null;
        }

        /**
         * Returns an unmodifiable tag set, which throws {@code UnsupportedOperationException}
         * if modification is attempted.
         * Returns {@code Optional#empty()} if {@code tags} is null.
         */
        public Optional<Set<Tag>> getTags() {
            return (tags != null) ? Optional.of(Collections.unmodifiableSet(tags)) : Optional.empty();
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof UpdateTaskCommand.UpdateTaskDescriptor)) {
                return false;
            }

            // state check
            UpdateTaskCommand.UpdateTaskDescriptor e = (UpdateTaskCommand.UpdateTaskDescriptor) other;

            return getTitle().equals(e.getTitle())
                    && getDeadline().equals(e.getDeadline())
                    && getStatus().equals(e.getStatus())
                    && getTags().equals(e.getTags());
        }
    }
}