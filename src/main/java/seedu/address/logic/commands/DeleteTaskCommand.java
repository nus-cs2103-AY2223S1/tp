package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_NO_MODULE_IN_FILTERED_LIST;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NUMBER;

import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.link.Link;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleTitle;
import seedu.address.model.module.task.Task;
import seedu.address.model.module.task.TaskList;

/**
 * Deletes a task from an existing module in Plannit.
 */
public class DeleteTaskCommand extends Command {

    public static final String COMMAND_WORD = "delete-task";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a task "
            + "belonging to the module identified by the module code. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: "
            + PREFIX_MODULE_CODE + "MODULE CODE "
            + PREFIX_TASK_NUMBER + "TASK NUMBER \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE_CODE + "CS3230 "
            + PREFIX_TASK_NUMBER + "1";

    public static final String MESSAGE_DELETE_TASK_SUCCESS =
            "Deleted task from: %1$s";
    public static final String MESSAGE_TASK_NUMBER_DOES_NOT_EXIST =
            "Task number given does not exist.";
    public static final String MESSAGE_MODULE_CODE_DOES_NOT_EXIST =
            "The given module code does not exist!";

    private final DeleteTaskFromModuleDescriptor deleteTaskFromModuleDescriptor;

    /**
     * @param deleteTaskFromModuleDescriptor details of task to be deleted
     */
    public DeleteTaskCommand(DeleteTaskFromModuleDescriptor deleteTaskFromModuleDescriptor) {
        requireNonNull(deleteTaskFromModuleDescriptor);

        this.deleteTaskFromModuleDescriptor =
                new DeleteTaskFromModuleDescriptor(deleteTaskFromModuleDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getFilteredModuleList();
        Module moduleToDeleteTaskFrom = null;
        // Search for module with matching module code.
        for (Module module : lastShownList) {
            if (module.getModuleCode().equals(deleteTaskFromModuleDescriptor.moduleCodeOfModuleWithTaskToDelete)) {
                moduleToDeleteTaskFrom = module;
            }
        }
        if (moduleToDeleteTaskFrom == null) {
            throw new CommandException(String.format(MESSAGE_NO_MODULE_IN_FILTERED_LIST,
                    deleteTaskFromModuleDescriptor.moduleCodeOfModuleWithTaskToDelete));
        }
        int indexOfTaskToDelete = deleteTaskFromModuleDescriptor
                .getTaskIndexToDelete().getZeroBased();
        int numberOfTasksInTaskList = moduleToDeleteTaskFrom.getTasks().size();
        if (indexOfTaskToDelete >= numberOfTasksInTaskList) {
            throw new CommandException(MESSAGE_TASK_NUMBER_DOES_NOT_EXIST);
        }
        Module moduleWithTaskDeleted = createModuleWithDeletedTask(
                moduleToDeleteTaskFrom, deleteTaskFromModuleDescriptor);

        model.setModule(moduleToDeleteTaskFrom, moduleWithTaskDeleted);
        return new CommandResult(String.format(MESSAGE_DELETE_TASK_SUCCESS,
                moduleWithTaskDeleted));
    }

    /**
     * Creates and returns a {@code Module} with the details of
     * {@code moduleToDeleteTaskFrom} and the task indicated in
     * {@code deleteTaskToModuleDescriptor} deleted.
     */
    private static Module createModuleWithDeletedTask(
            Module moduleToDeleteTaskFrom,
            DeleteTaskFromModuleDescriptor deleteTaskToModuleDescriptor) {
        assert moduleToDeleteTaskFrom != null;

        ModuleCode moduleCode = moduleToDeleteTaskFrom.getModuleCode();
        ModuleTitle moduleTitle = moduleToDeleteTaskFrom.getModuleTitle();
        Set<Link> moduleLinks = moduleToDeleteTaskFrom.getLinks();
        ObservableList<Task> moduleTasks = moduleToDeleteTaskFrom.getTasks();
        TaskList updatedTasks = new TaskList(moduleTasks);
        // Delete new task to the list.
        Index indexOfTaskToDelete =
                deleteTaskToModuleDescriptor.getTaskIndexToDelete();
        updatedTasks.remove(indexOfTaskToDelete);
        List<Task> updatedTasksAsList =
                updatedTasks.asUnmodifiableObservableList();
        return new Module(moduleCode, moduleTitle, updatedTasksAsList, moduleLinks);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof DeleteTaskCommand)) {
            return false;
        }

        // state check
        DeleteTaskCommand e = (DeleteTaskCommand) other;
        return deleteTaskFromModuleDescriptor.equals(e.deleteTaskFromModuleDescriptor);
    }

    /**
     * Stores the task number of the {@code Task} to be deleted.
     */
    public static class DeleteTaskFromModuleDescriptor {
        private ModuleCode moduleCodeOfModuleWithTaskToDelete;
        private Index indexOfTaskToDelete;

        public DeleteTaskFromModuleDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code indexOfTaskToDelete} is used internally.
         */
        public DeleteTaskFromModuleDescriptor(DeleteTaskFromModuleDescriptor toCopy) {
            setModuleCodeOfModuleWithTaskToDelete(toCopy.moduleCodeOfModuleWithTaskToDelete);
            setIndexOfTaskToDelete(toCopy.indexOfTaskToDelete);
        }

        /**
         * Sets {@code moduleCodeOfModuleWithTaskToDelete} to the given object's
         * {@code moduleCodeOfModuleWithTaskToDelete}.
         * A defensive copy of {@code moduleCodeOfModuleWithTaskToDelete} is
         * used internally.
         */
        public void setModuleCodeOfModuleWithTaskToDelete(ModuleCode moduleCodeOfModuleWithTaskToDelete) {
            this.moduleCodeOfModuleWithTaskToDelete = moduleCodeOfModuleWithTaskToDelete;
        }

        /**
         * Sets {@code indexOfTaskToDelete} to the given object's {@code
         * indexOfTaskToDelete}.
         * A defensive copy of {@code indexOfTaskToDelete} is used internally.
         */
        public void setIndexOfTaskToDelete(Index indexOfTaskToDelete) {
            this.indexOfTaskToDelete = indexOfTaskToDelete;
        }

        /**
         * Returns the {@code Index} of the task to be deleted.
         */
        public Index getTaskIndexToDelete() {
            return indexOfTaskToDelete;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof DeleteTaskFromModuleDescriptor)) {
                return false;
            }

            // state check
            DeleteTaskFromModuleDescriptor e = (DeleteTaskFromModuleDescriptor) other;

            return moduleCodeOfModuleWithTaskToDelete.equals(e.moduleCodeOfModuleWithTaskToDelete)
                    && getTaskIndexToDelete().equals(e.getTaskIndexToDelete());
        }
    }
}
