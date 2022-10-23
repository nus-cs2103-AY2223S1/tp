package modtrekt.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

import modtrekt.logic.commands.exceptions.CommandException;
import modtrekt.logic.parser.CliSyntax;
import modtrekt.model.Model;
import modtrekt.model.module.ModCode;
import modtrekt.model.task.Deadline;
import modtrekt.model.task.Description;
import modtrekt.model.task.Task;

/**
 * Adds a task to the task book.
 */
public class AddTaskCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public static final String COMMAND_IDENTIFIER = "-t";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the task book. \n"
            + "Usage: " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_TASK + " <DESCRIPTION> "
            + "[" + CliSyntax.PREFIX_DEADLINE + "<YYYY-MM-DD> ] "
            + CliSyntax.PREFIX_MOD_CODE + " <MODULE CODE> ";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";

    private Description description;
    private ModCode code;
    private LocalDate dueDate;


    /**
     * Creates an AddTaskCommand to add a task with the specified {@code Description, ModCode, LocalDate}
     */
    public AddTaskCommand(Description d, ModCode c, LocalDate l) {
        this.description = d;
        this.code = c;
        this.dueDate = l;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        ModCode currentModCode = model.getCurrentModule();
        // Need module code if not cd'd into a module
        if (currentModCode == null && code == null) {
            throw new CommandException(String.format(
                    "Need to specify a module code. Usage:\n%s", MESSAGE_USAGE));
        }
        // If using provided module code, check if it exists
        if (code != null && !model.hasModuleWithModCode(code)) {
            throw new CommandException("Module code does not exist");
        }

        Task toAdd;
        if (code == null && dueDate != null) {
            // Deadline based on cd'd module
            toAdd = new Deadline(description, currentModCode, dueDate, false, Task.Priority.NONE);
        } else if (code == null) {
            // Task based on cd'd module
            toAdd = new Task(description, currentModCode, false, Task.Priority.NONE);
        } else if (dueDate != null) {
            // Deadline based on provided module code
            toAdd = new Deadline(description, code, dueDate, false, Task.Priority.NONE);
        } else {
            // Task based on provided module code
            toAdd = new Task(description, code, false, Task.Priority.NONE);
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
        boolean dueDateEquality;
        if (code == null) {
            codeEquality = ((AddTaskCommand) other).code == null;
        } else {
            codeEquality = code.equals(((AddTaskCommand) other).code);
        }
        if (dueDate == null) {
            dueDateEquality = ((AddTaskCommand) other).dueDate == null;
        } else {
            dueDateEquality = dueDate.equals(((AddTaskCommand) other).dueDate);
        }
        return descriptionEquality && codeEquality && dueDateEquality;
    }
}
