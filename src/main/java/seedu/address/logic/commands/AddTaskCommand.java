package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_DESCRIPTION;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_MODULES;

import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.link.Link;
import seedu.address.model.module.Module;
import seedu.address.model.module.ModuleCode;
import seedu.address.model.module.ModuleTitle;
import seedu.address.model.module.exceptions.ModuleNotFoundException;
import seedu.address.model.module.task.Task;
import seedu.address.model.module.task.TaskList;

/**
 * Adds a task to an existing module in Plannit.
 */
public class AddTaskCommand extends Command {

    public static final String COMMAND_WORD = "add-task";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task "
            + "to the module identified by the module code. "
            + "Existing values will be overwritten by the input values.\n"
            + "Parameters: "
            + PREFIX_MODULE_CODE + "MODULE CODE "
            + PREFIX_TASK_DESCRIPTION + "TASK DESCRIPTION \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE_CODE + "CS3230 "
            + PREFIX_TASK_DESCRIPTION + "Complete programming assignment";

    public static final String MESSAGE_ADD_TASK_SUCCESS =
            "New task added to: %1$s";
    public static final String MESSAGE_DUPLICATE_TASK =
            "This task already exists in this module.";

    private final AddTaskToModuleDescriptor addTaskToModuleDescriptor;

    /**
     * @param addTaskToModuleDescriptor contains details of Task to be added to
     *                                  the module
     */
    public AddTaskCommand(AddTaskToModuleDescriptor addTaskToModuleDescriptor) {
        requireNonNull(addTaskToModuleDescriptor);
        this.addTaskToModuleDescriptor = new AddTaskToModuleDescriptor(addTaskToModuleDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Module> lastShownList = model.getFilteredModuleList();
        ModuleCode moduleCodeOfModuleToAddTaskTo =
                addTaskToModuleDescriptor.moduleCode;
        Module moduleToAddTaskTo = null;
        try {
            moduleToAddTaskTo =
                    model.getModuleUsingModuleCode(moduleCodeOfModuleToAddTaskTo, true);
        } catch (ModuleNotFoundException e) {
            throw new CommandException(Messages.MESSAGE_NO_SUCH_MODULE);
        }
        assert moduleToAddTaskTo != null;
        Module moduleWithNewTask = createModuleWithNewTask(moduleToAddTaskTo, addTaskToModuleDescriptor);

        Boolean taskWasAdded = !moduleToAddTaskTo.equals(moduleWithNewTask);
        Boolean isDuplicateTask = moduleWithNewTask.hasDuplicateTasks();
        if (taskWasAdded && isDuplicateTask) {
            throw new CommandException(MESSAGE_DUPLICATE_TASK);
        }

        model.setModule(moduleToAddTaskTo, moduleWithNewTask);
        model.updateFilteredModuleList(PREDICATE_SHOW_ALL_MODULES);
        return new CommandResult(
                String.format(MESSAGE_ADD_TASK_SUCCESS, moduleWithNewTask));
    }

    /**
     * Creates and returns a {@code Module} with the new task added.
     * @param moduleToAddTaskTo Module to add the new task to
     * @param addTaskToModuleDescriptor contains details of Task to be added
     *                                  to the module
     */
    private static Module createModuleWithNewTask(Module moduleToAddTaskTo,
                                                  AddTaskToModuleDescriptor addTaskToModuleDescriptor) {
        assert moduleToAddTaskTo != null;

        ModuleCode moduleCode = moduleToAddTaskTo.getModuleCode();
        ModuleTitle moduleTitle = moduleToAddTaskTo.getModuleTitle();
        Set<Link> moduleLinks = moduleToAddTaskTo.getLinks();
        ObservableList<Task> moduleTasks = moduleToAddTaskTo.getTasks();
        TaskList updatedTasks = new TaskList(moduleTasks);
        Task taskToAdd = addTaskToModuleDescriptor.getTask();
        // Add new task to the list.
        updatedTasks.add(taskToAdd);
        List<Task> updatedTasksAsList =
                updatedTasks.asUnmodifiableObservableList();
        return new Module(moduleCode, moduleTitle, updatedTasksAsList,
                moduleLinks);
    }

    @Override
    public boolean equals(Object other) {
        // short circuit if same object
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AddTaskCommand)) {
            return false;
        }

        // state check
        AddTaskCommand e = (AddTaskCommand) other;
        return addTaskToModuleDescriptor.equals(e.addTaskToModuleDescriptor);
    }

    /**
     * Stores the details of task to be added to the module.
     */
    public static class AddTaskToModuleDescriptor {
        private ModuleCode moduleCode;
        private Task newTask;
        public AddTaskToModuleDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code newTask} is used internally.
         */
        public AddTaskToModuleDescriptor(AddTaskToModuleDescriptor toCopy) {
            setModuleCodeOfModuleToAddTaskTo(toCopy.moduleCode);
            setNewTask(toCopy.newTask);
        }

        /**
         * Sets {@code indexOfTaskToDelete} to this object's {@code
         * indexOfTaskToDelete}.
         * A defensive copy of {@code indexOfTaskToDelete} is used internally.
         */
        public void setModuleCodeOfModuleToAddTaskTo(ModuleCode moduleCodeOfModuleToAddTaskTo) {
            this.moduleCode = moduleCodeOfModuleToAddTaskTo;
        }

        /**
         * Sets {@code newTask} to this object's {@code newTask}.
         * A defensive copy of {@code newTask} is used internally.
         */
        public void setNewTask(Task newTask) {
            this.newTask = newTask;
        }


        /**
         * Returns the new task to be added as a {@code Task} object.
         */
        public Task getTask() {
            return newTask;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof AddTaskToModuleDescriptor)) {
                return false;
            }

            // state check
            AddTaskToModuleDescriptor e = (AddTaskToModuleDescriptor) other;

            Boolean hasSameModule = this.moduleCode.equals(e.moduleCode);
            Boolean hasSameTask = getTask().equals(e.getTask());

            return hasSameModule && hasSameTask;
        }
    }
}
