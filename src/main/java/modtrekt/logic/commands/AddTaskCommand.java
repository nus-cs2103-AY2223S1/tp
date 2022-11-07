package modtrekt.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import modtrekt.logic.commands.exceptions.CommandException;
import modtrekt.logic.parser.converters.DeadlineConverter;
import modtrekt.logic.parser.converters.DescriptionConverter;
import modtrekt.logic.parser.converters.ModCodeConverter;
import modtrekt.logic.parser.converters.PriorityConverter;
import modtrekt.model.Model;
import modtrekt.model.module.ModCode;
import modtrekt.model.task.Deadline;
import modtrekt.model.task.Description;
import modtrekt.model.task.Task;

/**
 * Adds a task or deadline to the task book.
 */
@Parameters(commandDescription = "Adds a task or deadline to the task book.")
public class AddTaskCommand extends Command {

    public static final String COMMAND_WORD = "add task";
    public static final String MESSAGE_SUCCESS = "I added a new task: %1$s! Good Luck!";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the task book. \n"
            + COMMAND_WORD + " <DESCRIPTION> "
            + "-c" + " <MODULE CODE> "
            + "[" + "-d " + "<YYYY-MM-DD>] "
            + "[" + "-p" + " <PRIORITY>]";

    @Parameter(description = "<description>", required = true, converter = DescriptionConverter.class)
    private Description description;

    @Parameter(names = "-c", description = "Module code of the task", converter = ModCodeConverter.class)
    private ModCode modCode;

    @Parameter(names = "-d", description = "Due date of the task", converter = DeadlineConverter.class)
    private LocalDate deadline;

    @Parameter(names = "-p", description = "Priority level of the task", converter = PriorityConverter.class)
    private Task.Priority priority = Task.Priority.NONE; // default priority level is no priority

    /**
     * Constructor with no parameters required, for use with JCommander.
     */
    public AddTaskCommand() {
    }

    /**
     * Creates an AddTaskCommand to add a task with the specified {@code Description, ModCode, LocalDate, Priority}
     */
    public AddTaskCommand(Description ds, ModCode code, LocalDate deadline, Task.Priority priority) {
        requireNonNull(priority);
        this.description = ds;
        this.modCode = code;
        this.deadline = deadline;
        this.priority = priority;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        ModCode currentModCode = model.getCurrentModule();
        // Need module code if not cd'd into a module
        if (currentModCode == null && modCode == null) {
            throw new CommandException(String.format(
                    "You need to specify a module code. Usage:\n%s", MESSAGE_USAGE));
        }
        // If using provided module code, check if it exists
        if (modCode != null && !model.hasModuleWithModCode(modCode)) {
            throw new CommandException(String.format("Module code %s does not exist", modCode));
        }

        Task toAdd;
        if (modCode == null && deadline != null) {
            // Deadline based on cd'd module
            toAdd = new Deadline(description, currentModCode, deadline, false, priority);
        } else if (modCode == null) {
            // Task based on cd'd module
            toAdd = new Task(description, currentModCode, false, priority);
        } else if (deadline != null) {
            // Deadline based on provided module code
            toAdd = new Deadline(description, modCode, deadline, false, priority);
        } else {
            // Task based on provided module code
            toAdd = new Task(description, modCode, false, priority);
        }

        model.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd.getDescription()));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof AddTaskCommand)) {
            return false;
        }
        AddTaskCommand that = (AddTaskCommand) other;
        boolean descriptionIsEqual = this.description.equals(that.description);
        boolean priorityIsEqual = this.priority.equals(that.priority);
        boolean codeIsEqual;
        boolean deadlineIsEqual;
        if (modCode == null) {
            codeIsEqual = that.modCode == null;
        } else {
            codeIsEqual = modCode.equals(that.modCode);
        }
        if (deadline == null) {
            deadlineIsEqual = that.deadline == null;
        } else {
            deadlineIsEqual = deadline.equals(that.deadline);
        }
        return descriptionIsEqual && codeIsEqual && deadlineIsEqual && priorityIsEqual;
    }
}
