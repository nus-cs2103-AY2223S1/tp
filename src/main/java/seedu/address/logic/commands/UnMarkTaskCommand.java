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
public class UnMarkTaskCommand extends Command {
    public static final String COMMAND_WORD = "unmark";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Un-marks the task identified by the index number used in the displayed task list.\n"
            + "Parameters: INDEX (must be a positive integer)\n"
            + "Example: " + COMMAND_WORD + " 1";
    public static final String MESSAGE_UNMARK_TASK_SUCCESS = "Marked Task as Not Done: %1$s";
    private final Index targetIndex;

    public UnMarkTaskCommand(Index targetIndex) {
        this.targetIndex = targetIndex;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownTaskList = model.getFilteredTaskList();

        if (targetIndex.getZeroBased() >= lastShownTaskList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        Task taskToUnMark = lastShownTaskList.get(targetIndex.getZeroBased());
        UnMarkTaskDescriptor configureUnMarkedTask = new UnMarkTaskDescriptor();
        configureUnMarkedTask.setStatus(false);
        Task unMarkedTask = createUnMarkedTask(taskToUnMark, configureUnMarkedTask);
        model.unMarkTask(unMarkedTask, targetIndex);

        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_UNMARK_TASK_SUCCESS, unMarkedTask));
    }

    /**
     * Creates and returns a {@code Task} with the details of {@code taskToEdit}
     * edited with {@code editUnMarkTaskDescriptor}.
     */
    private static Task createUnMarkedTask(
            Task taskToUnMark, UnMarkTaskCommand.UnMarkTaskDescriptor unMarkTaskDescriptor) {
        assert taskToUnMark != null;

        String updatedTitle = unMarkTaskDescriptor.getTitle().orElse(taskToUnMark.getTitle());
        String updatedDeadline = unMarkTaskDescriptor.getDeadline().orElse(taskToUnMark.getDeadline());
        boolean updatedStatus = unMarkTaskDescriptor.getStatus().orElse(taskToUnMark.getStatus());
        Set<Tag> updatedTags = unMarkTaskDescriptor.getTags().orElse(taskToUnMark.getTags());

        return new Task(updatedTitle, updatedDeadline, updatedStatus, updatedTags);
    }

    /**
     * Stores the details to edit the person with. Each non-empty field value will replace the
     * corresponding field value of the person.
     */
    public static class UnMarkTaskDescriptor {
        private String title;
        private String deadline;
        private boolean status;
        private Set<Tag> tags;

        public UnMarkTaskDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code tags} is used internally.
         */
        public UnMarkTaskDescriptor(UnMarkTaskCommand.UnMarkTaskDescriptor toCopy) {
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
            if (!(other instanceof UnMarkTaskCommand.UnMarkTaskDescriptor)) {
                return false;
            }

            // state check
            UnMarkTaskCommand.UnMarkTaskDescriptor e = (UnMarkTaskCommand.UnMarkTaskDescriptor) other;

            return getTitle().equals(e.getTitle())
                    && getDeadline().equals(e.getDeadline())
                    && getStatus().equals(e.getStatus())
                    && getTags().equals(e.getTags());
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UnMarkTaskCommand
                && targetIndex.equals(((UnMarkTaskCommand) other).targetIndex));
    }
}
