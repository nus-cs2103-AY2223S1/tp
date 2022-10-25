package modtrekt.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import modtrekt.logic.commands.exceptions.CommandException;
import modtrekt.logic.parser.converters.DeadlineConverter;
import modtrekt.logic.parser.converters.DescriptionConverter;
import modtrekt.logic.parser.converters.ModCodeConverter;
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
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the task book. \n"
            + "Usage: " + COMMAND_WORD + " "
            + "task" + " <DESCRIPTION> "
            + "[" + "-d" + "<YYYY-MM-DD> ] "
            + "-c" + " <MODULE CODE> ";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";
    @Parameter(description = "Task description", required = true,
            converter = DescriptionConverter.class)
    private Description description;

    @Parameter(names = "-c", description = "Module code for task",
            converter = ModCodeConverter.class)
    private ModCode modCode;

    @Parameter(names = "-d", description = "Due date",
            converter = DeadlineConverter.class)
    private LocalDate deadline;

    /**
     * Constructor with no parameters required, for use with JCommander.
     */
    public AddTaskCommand() {
    }
    /**
     * Creates an AddTaskCommand to add a task with the specified {@code Description, ModCode, LocalDate}
     */

    public AddTaskCommand(Description ds, ModCode code, LocalDate deadline) {
        this.description = ds;
        this.modCode = code;
        this.deadline = deadline;
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
            toAdd = new Deadline(description, currentModCode, deadline, false, Task.Priority.NONE);
        } else if (modCode == null) {
            // Task based on cd'd module
            toAdd = new Task(description, currentModCode, false, Task.Priority.NONE);
        } else if (deadline != null) {
            // Deadline based on provided module code
            toAdd = new Deadline(description, modCode, deadline, false, Task.Priority.NONE);
        } else {
            // Task based on provided module code
            toAdd = new Task(description, modCode, false, Task.Priority.NONE);
        }

        model.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }
        if (!(other instanceof AddTaskCommand)) {
            return false;
        }
        boolean descriptionEquality = description.equals(((AddTaskCommand) other).description);
        boolean codeEquality;
        boolean deadlineEquality;
        if (modCode == null) {
            codeEquality = ((AddTaskCommand) other).modCode == null;
        } else {
            codeEquality = modCode.equals(((AddTaskCommand) other).modCode);
        }
        if (deadline == null) {
            deadlineEquality = ((AddTaskCommand) other).deadline == null;
        } else {
            deadlineEquality = deadline.equals(((AddTaskCommand) other).deadline);
        }
        return descriptionEquality && codeEquality && deadlineEquality;
    }
}
