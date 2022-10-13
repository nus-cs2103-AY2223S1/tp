package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.task.exceptions.DuplicateTaskException;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;

/**
 * Edits the details of a task in the task list.
 */
public class EditTaskCommand extends Command {

    public static final String COMMAND_WORD = "edittask";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Edits the details of the task identified "
        + "by the index number used in the displayed task list. "
        + "Existing values will be overwritten by the input values.\n"
        + "Parameters: INDEX (must be a positive integer) "
        + "[" + PREFIX_MODULE + "MODULE] "
        + "[" + PREFIX_DESCRIPTION + "DESCRIPTION] "
        + "Example: " + COMMAND_WORD + " 1 "
        + PREFIX_MODULE + "cs2040 "
        + PREFIX_DESCRIPTION + "task 3";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Edited Task: %1$s";
    public static final String MESSAGE_TASK_NOT_EDITED = "No changes are observed.";
    public static final String MESSAGE_DUPLICATE_TASK = "This task already exists in the task list.";
    public static final String MESSAGE_MODULE_NOT_FOUND = "This task's module does not exist";

    private final Index index;
    private final EditTaskDescriptor editTaskDescriptor;

    /**
     * @param index of the task in the filtered task list to edit
     * @param editTaskDescriptor details to edit the task with
     */
    public EditTaskCommand(Index index, EditTaskDescriptor editTaskDescriptor) {
        requireAllNonNull(index, editTaskDescriptor);

        this.index = index;
        this.editTaskDescriptor = editTaskDescriptor;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Task> lastShownList = model.getFilteredTaskList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_TASK_DISPLAYED_INDEX);
        }

        if (editTaskDescriptor.getModule().isPresent() && !model.hasModule(editTaskDescriptor.module)) {
            throw new CommandException(MESSAGE_MODULE_NOT_FOUND);
        }

        Task taskToEdit = lastShownList.get(index.getZeroBased());
        Task editedTask = taskToEdit.edit(editTaskDescriptor);

        if (taskToEdit.isSameTask(editedTask)) {
            throw new CommandException(MESSAGE_TASK_NOT_EDITED);
        }

        try {
            model.replaceTask(taskToEdit, editedTask);
        } catch (DuplicateTaskException e) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        return new CommandResult(String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask));
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
        private Module module;
        private TaskDescription description;

        public EditTaskDescriptor() {
        }

        public void setModule(Module module) {
            this.module = module;
        }

        public Optional<Module> getModule() {
            return Optional.ofNullable(module);
        }

        public void setDescription(TaskDescription description) {
            this.description = description;
        }

        public Optional<TaskDescription> getDescription() {
            return Optional.ofNullable(description);
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

            return module.equals(e.module)
                && description.equals(e.description);
        }
    }
}
