package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_NO_MODULE_IN_FILTERED_LIST;
import static seedu.address.commons.core.Messages.MESSAGE_NO_SUCH_TASK_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NUMBER_TO_DELETE;

import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleTitle;
import seedu.address.model.module.exceptions.ModuleNotFoundException;
import seedu.address.model.module.link.Link;
import seedu.address.model.module.task.Task;
import seedu.address.model.module.task.TaskList;
import seedu.address.model.person.Person;

/**
 * Deletes a task from an existing module in Plannit.
 */
public class DeleteTaskCommand extends Command {

    public static final String COMMAND_WORD = "delete-task";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Deletes a task "
            + "belonging to the module identified by the module code.\n"
            + "Parameters: "
            + PREFIX_MODULE_CODE + "MODULE_CODE "
            + PREFIX_TASK_NUMBER_TO_DELETE + "TASK_NUMBER \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE_CODE + "CS3230 "
            + PREFIX_TASK_NUMBER_TO_DELETE + "1";

    public static final String MESSAGE_DELETE_TASK_SUCCESS =
            "Deleted task from: %1$s";

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
        ModuleCode moduleCodeOfTaskToDeleteTaskFrom =
                deleteTaskFromModuleDescriptor.moduleCodeOfModuleWithTaskToDelete;
        Module moduleToDeleteTaskFrom = null;
        try {
            moduleToDeleteTaskFrom =
                    model.getModuleUsingModuleCode(moduleCodeOfTaskToDeleteTaskFrom, true);
        } catch (ModuleNotFoundException e) {
            throw new CommandException(String.format(MESSAGE_NO_MODULE_IN_FILTERED_LIST,
                    moduleCodeOfTaskToDeleteTaskFrom.getModuleCodeAsUpperCaseString()));
        }
        assert moduleToDeleteTaskFrom != null;
        int indexOfTaskToDelete = deleteTaskFromModuleDescriptor
                .getTaskIndexToDelete().getZeroBased();
        int numberOfTasksInTaskList = moduleToDeleteTaskFrom.getTasks().size();
        if (indexOfTaskToDelete >= numberOfTasksInTaskList) {
            throw new CommandException(MESSAGE_NO_SUCH_TASK_NUMBER);
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
            DeleteTaskFromModuleDescriptor deleteTaskFromModuleDescriptor) {
        assert moduleToDeleteTaskFrom != null;

        ModuleCode moduleCode = moduleToDeleteTaskFrom.getModuleCode();
        ModuleTitle moduleTitle = moduleToDeleteTaskFrom.getModuleTitle();
        ObservableList<Task> moduleTasks = moduleToDeleteTaskFrom.getTasks();
        Set<Link> moduleLinks = moduleToDeleteTaskFrom.getLinks();
        Set<Person> modulePersons = moduleToDeleteTaskFrom.getPersons();

        TaskList updatedTasks = new TaskList(moduleTasks);
        // Delete new task from the list.
        Index indexOfTaskToDelete =
                deleteTaskFromModuleDescriptor.getTaskIndexToDelete();
        updatedTasks.remove(indexOfTaskToDelete);
        List<Task> updatedTasksAsList =
                updatedTasks.asUnmodifiableObservableList();
        return new Module(moduleCode, moduleTitle, updatedTasksAsList,
                moduleLinks, modulePersons);
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
         * Sets {@code moduleCodeOfModuleWithTaskToDelete} to the given
         * {@code ModuleCode}.
         * A defensive copy of {@code moduleCodeOfModuleWithTaskToDelete} is
         * used internally.
         */
        public void setModuleCodeOfModuleWithTaskToDelete(ModuleCode moduleCodeOfModuleWithTaskToDelete) {
            this.moduleCodeOfModuleWithTaskToDelete = moduleCodeOfModuleWithTaskToDelete;
        }

        /**
         * Sets {@code indexOfTaskToDelete} to the given {@code Index}.
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
