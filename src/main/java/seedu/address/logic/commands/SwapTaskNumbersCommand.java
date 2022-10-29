package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.core.Messages.MESSAGE_NO_SUCH_TASK_NUMBER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MODULE_CODE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TASK_NUMBERS_TO_SWAP;

import java.util.List;
import java.util.Set;

import javafx.collections.ObservableList;
import seedu.address.commons.core.Messages;
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
 * Swaps the task numbers of two tasks from an existing module in Plannit.
 */
public class SwapTaskNumbersCommand extends Command {

    public static final String COMMAND_WORD = "swap-task";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Swaps the "
            + "task number of two tasks belonging to the module identified by"
            + " the module code.\n"
            + "Parameters: "
            + PREFIX_MODULE_CODE + "MODULE_CODE "
            + PREFIX_TASK_NUMBERS_TO_SWAP + "FIRST_TASK_NUMBER "
            + "SECOND_TASK_NUMBER \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_MODULE_CODE + "CS3230 "
            + PREFIX_TASK_NUMBERS_TO_SWAP + "1 3";

    public static final String MESSAGE_SWAP_TASK_NUMBERS_SUCCESS =
            "Swapped tasks in: %1$s";

    private final SwapTaskNumbersDescriptor swapTaskNumbersDescriptor;

    /**
     * @param swapTaskNumbersDescriptor details of tasks to be swapped
     */
    public SwapTaskNumbersCommand(SwapTaskNumbersDescriptor swapTaskNumbersDescriptor) {
        requireNonNull(swapTaskNumbersDescriptor);

        this.swapTaskNumbersDescriptor =
                new SwapTaskNumbersDescriptor(swapTaskNumbersDescriptor);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ModuleCode moduleCodeOfTaskToSwapTaskNumbers =
                swapTaskNumbersDescriptor.moduleCodeOfModuleWithTasksToSwap;
        Module moduleToSwapTaskNumbers = null;
        try {
            moduleToSwapTaskNumbers =
                    model.getModuleUsingModuleCode(moduleCodeOfTaskToSwapTaskNumbers, true);
        } catch (ModuleNotFoundException e) {
            throw new CommandException(Messages.MESSAGE_NO_SUCH_MODULE);
        }
        assert moduleToSwapTaskNumbers != null;

        List<Index> taskIndexesToSwap = swapTaskNumbersDescriptor.getTaskIndexesToSwap();
        checkIfTaskNumbersExist(moduleToSwapTaskNumbers, taskIndexesToSwap);

        Module moduleWithTasksSwapped = createModuleWithSwappedTaskNumbers(
                moduleToSwapTaskNumbers, swapTaskNumbersDescriptor);

        model.setModule(moduleToSwapTaskNumbers, moduleWithTasksSwapped);
        return new CommandResult(String.format(MESSAGE_SWAP_TASK_NUMBERS_SUCCESS,
                moduleWithTasksSwapped));
    }

    private void checkIfTaskNumbersExist(Module moduleToSwapTaskNumbers,
                                         List<Index> taskIndexesToSwap) throws CommandException {
        int numberOfTasksInTaskList = moduleToSwapTaskNumbers.getTasks().size();
        for (Index taskIndex : taskIndexesToSwap) {
            if (taskIndex.getOneBased() > numberOfTasksInTaskList) {
                throw new CommandException(MESSAGE_NO_SUCH_TASK_NUMBER);
            }
        }
    }

    /**
     * Creates and returns a {@code Module} with the details of
     * {@code moduleToSwapTaskNumbers} and the tasks indicated in
     * {@code swapTaskNumbersDescriptor} swapped.
     */
    private static Module createModuleWithSwappedTaskNumbers(
            Module moduleToSwapTaskNumbers,
            SwapTaskNumbersDescriptor swapTaskNumbersDescriptor) {
        assert moduleToSwapTaskNumbers != null;

        ModuleCode moduleCode = moduleToSwapTaskNumbers.getModuleCode();
        ModuleTitle moduleTitle = moduleToSwapTaskNumbers.getModuleTitle();
        Set<Link> moduleLinks = moduleToSwapTaskNumbers.getLinks();
        Set<Person> modulePersons = moduleToSwapTaskNumbers.getPersons();
        ObservableList<Task> moduleTasks = moduleToSwapTaskNumbers.getTasks();
        TaskList updatedTasks = new TaskList(moduleTasks);
        // Swap tasks in the list.
        List<Index> taskIndexesToSwap =
                swapTaskNumbersDescriptor.getTaskIndexesToSwap();
        updatedTasks.swapTasks(taskIndexesToSwap.get(0),
                taskIndexesToSwap.get(1));
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
        if (!(other instanceof SwapTaskNumbersCommand)) {
            return false;
        }

        // state check
        SwapTaskNumbersCommand e = (SwapTaskNumbersCommand) other;
        return swapTaskNumbersDescriptor.equals(e.swapTaskNumbersDescriptor);
    }

    /**
     * Stores the task numbers of the {@code Task}s to swap.
     */
    public static class SwapTaskNumbersDescriptor {
        private ModuleCode moduleCodeOfModuleWithTasksToSwap;
        private List<Index> taskIndexesToSwap;

        public SwapTaskNumbersDescriptor() {}

        /**
         * Copy constructor.
         * A defensive copy of {@code taskIndexesToSwap} is used internally.
         */
        public SwapTaskNumbersDescriptor(SwapTaskNumbersDescriptor toCopy) {
            setModuleCodeOfModuleToSwapTasks(toCopy.moduleCodeOfModuleWithTasksToSwap);
            setIndexesOfTaskToSwap(toCopy.taskIndexesToSwap);
        }

        /**
         * Sets {@code moduleCodeOfModuleWithTasksToSwap} to the given
         * {@code ModuleCode}.
         * A defensive copy of {@code moduleCodeOfModuleWithTasksToSwap} is
         * used internally.
         */
        public void setModuleCodeOfModuleToSwapTasks(ModuleCode moduleCodeOfModuleWithTasksToSwap) {
            this.moduleCodeOfModuleWithTasksToSwap = moduleCodeOfModuleWithTasksToSwap;
        }

        /**
         * Sets {@code indexesOfTaskToSwap} to the given {@code List} of
         * {@Code Index}es.
         * A defensive copy of {@code indexesOfTaskToSwap} is used internally.
         */
        public void setIndexesOfTaskToSwap(List<Index> taskIndexesToSwap) {
            this.taskIndexesToSwap = taskIndexesToSwap;
        }

        /**
         * Returns the {@code List} of {@code Index}es of the tasks to be
         * swapped.
         */
        public List<Index> getTaskIndexesToSwap() {
            return taskIndexesToSwap;
        }

        @Override
        public boolean equals(Object other) {
            // short circuit if same object
            if (other == this) {
                return true;
            }

            // instanceof handles nulls
            if (!(other instanceof SwapTaskNumbersDescriptor)) {
                return false;
            }

            // state check
            SwapTaskNumbersDescriptor e = (SwapTaskNumbersDescriptor) other;

            return moduleCodeOfModuleWithTasksToSwap.equals(e.moduleCodeOfModuleWithTasksToSwap)
                    && getTaskIndexesToSwap().containsAll(e.getTaskIndexesToSwap())
                    && e.getTaskIndexesToSwap().containsAll(getTaskIndexesToSwap());
        }
    }
}
