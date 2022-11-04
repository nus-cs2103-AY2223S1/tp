package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_MODULE_NOT_FOUND;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_TASKS;

import java.util.List;
import java.util.Optional;

import seedu.address.commons.core.Messages;
import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.CollectionUtil;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.task.Task;
import seedu.address.model.task.TaskDescription;
import seedu.address.model.task.exceptions.DuplicateTaskException;

/**
 * Edits the task with the specified index number in the displayed task list.
 */
public class EditTaskCommand extends Command {

    public static final String COMMAND_WORD = "edit";
    public static final String FULL_COMMAND_WORD = "t edit";

    public static final String MESSAGE_USAGE = FULL_COMMAND_WORD + ": Edits the details of the task at the specified "
        + "INDEX in the displayed task list. "
        + "Existing values will be overwritten by the input values.\n"
        + "Parameters: INDEX "
        + "[" + PREFIX_MODULE + "MODULE]* "
        + "[" + PREFIX_DESCRIPTION + "DESCRIPTION]*\n"
        + "Example: " + FULL_COMMAND_WORD + " 1 "
        + PREFIX_MODULE + "cs2040 "
        + PREFIX_DESCRIPTION + "task 3";

    public static final String MESSAGE_EDIT_TASK_SUCCESS = "Successfully Edited Task: %1$s";
    public static final String MESSAGE_NO_FIELDS_PROVIDED =
        "Please provide at least one of the fields to edit: m/MODULE, d/DESCRIPTION";
    public static final String MESSAGE_SAME_FIELDS_PROVIDED =
        "Please provide a m/MODULE or d/DESCRIPTION different from the task's current module and description";
    public static final String MESSAGE_EXAM_UNLINKED = "Warning: The task has been unlinked from its exam.\n";
    public static final String MESSAGE_DUPLICATE_TASK =
        "The edited task is the same as another task in the task list";

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
            throw new CommandException(
                String.format(Messages.MESSAGE_INVALID_TASK_INDEX_TOO_LARGE, lastShownList.size() + 1));
        }

        if (editTaskDescriptor.getModule().isPresent() && !model.hasModule(editTaskDescriptor.module)) {
            throw new CommandException(MESSAGE_MODULE_NOT_FOUND);
        }

        Task taskToEdit = lastShownList.get(index.getZeroBased());
        Task editedTask = taskToEdit.edit(editTaskDescriptor);

        if (taskToEdit.isSameTask(editedTask)) {
            throw new CommandException(MESSAGE_SAME_FIELDS_PROVIDED);
        }

        try {
            model.replaceTask(taskToEdit, editedTask, false);
        } catch (DuplicateTaskException e) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.updateFilteredTaskList(PREDICATE_SHOW_ALL_TASKS);
        if (!taskToEdit.getModule().isSameModule(editedTask.getModule())
            && taskToEdit.isLinked()) {
            return new CommandResult(
                MESSAGE_EXAM_UNLINKED + String.format(MESSAGE_EDIT_TASK_SUCCESS, editedTask));
        }
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

        public EditTaskDescriptor() {}

        /**
         * Copy constructor.
         */
        public EditTaskDescriptor(EditTaskDescriptor toCopy) {
            setModule(toCopy.module);
            setDescription(toCopy.description);
        }

        /**
         * Returns true if at least one field is edited.
         */
        public boolean isAnyFieldEdited() {
            return CollectionUtil.isAnyNonNull(module, description);
        }

        public void setModule(Module module) {
            requireNonNull(module);
            this.module = module;
        }

        public Optional<Module> getModule() {
            return Optional.ofNullable(module);
        }

        public void setDescription(TaskDescription description) {
            requireNonNull(description);
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

            return getModule().equals(e.getModule())
                && getDescription().equals(e.getDescription());
        }
    }
}
