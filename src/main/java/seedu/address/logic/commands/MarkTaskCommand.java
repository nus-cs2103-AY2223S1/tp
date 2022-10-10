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
 * Marks a task identified by its displayed index in the GUI as completed.
 */
public class MarkTaskCommand extends Command {
    public static final String COMMAND_WORD = "mark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the task identified by the index number used in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_MARK_TASK_SUCCESS = "Marked Task as Complete: %1$s";
    private final Index targetIndex;

    public MarkTaskCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownTaskList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToMark = lastShownTaskList.get(targetIndex.getZeroBased());
        MarkTaskDescriptor configureMarkedTask = new MarkTaskDescriptor();
        configureMarkedTask.setStatus(true);
        Task markedTask = createMarkedTask(taskToMark, configureMarkedTask);
        model.setTask(markedTask, targetIndex);

        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_MARK_TASK_SUCCESS, markedTask));
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private static Task createMarkedTask(Task taskToMark, MarkTaskCommand.MarkTaskDescriptor markTaskDescriptor) {
        assert taskToMark != null;

        String updatedTitle = markTaskDescriptor.getTitle().orElse(taskToMark.getTitle());
        String updatedDeadline = markTaskDescriptor.getDeadline().orElse(taskToMark.getDeadline());
        boolean updatedStatus = markTaskDescriptor.getStatus().orElse(taskToMark.getStatus());
        Set<Tag> updatedTags = markTaskDescriptor.getTags().orElse(taskToMark.getTags());

        return new Task(updatedTitle, updatedDeadline, updatedStatus, updatedTags);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class MarkTaskDescriptor {
        private String title;
        private String deadline;
        private boolean status;
        private Set<Tag> tags;

        public MarkTaskDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public MarkTaskDescriptor(MarkTaskCommand.MarkTaskDescriptor toCopy) {
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
            if (!(other instanceof MarkTaskCommand.MarkTaskDescriptor)) {
                return false;
            }

            // state check
            MarkTaskCommand.MarkTaskDescriptor e = (MarkTaskCommand.MarkTaskDescriptor) other;

            return getTitle().equals(e.getTitle())
                    && getDeadline().equals(e.getDeadline())
                    && getStatus().equals(e.getStatus())
                    && getTags().equals(e.getTags());
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof MarkTaskCommand
            && targetIndex.equals(((MarkTaskCommand) other).targetIndex));
    }
}
