package modtrekt.logic.commands;

import static java.util.Objects.requireNonNull;

import java.time.LocalDate;

import com.beust.jcommander.Parameter;
import com.beust.jcommander.Parameters;

import modtrekt.logic.commands.exceptions.CommandException;
import modtrekt.logic.parser.CliSyntax;
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

    public static final String COMMAND_WORD = "add";
    public static final String COMMAND_IDENTIFIER = "-t";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the task book. \n"
            + "Usage: " + COMMAND_WORD + " "
            + CliSyntax.PREFIX_TASK + " <DESCRIPTION> "
            + "[" + CliSyntax.PREFIX_DEADLINE + "<YYYY-MM-DD> ] "
            + CliSyntax.PREFIX_MOD_CODE + " <MODULE CODE> ";
    public static final String MESSAGE_SUCCESS = "New task added: %1$s";

    @Parameter(names = "-t", description = "Task description", required = true,
            converter = DescriptionConverter.class)
    private Description description;

    @Parameter(names = "-c", description = "Module code for task", required = true,
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
     * Creates an AddTaskCommand to add the specified {@code Task}
     */
    public AddTaskCommand(Description ds, ModCode code, LocalDate deadline) {
        this.description = ds;
        this.modCode = code;
        this.deadline = deadline;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!(model.hasModuleWithModCode(this.modCode))) {
            throw new CommandException("Module code does not exist");
        }

        /* If deadline is not null, a Deadline object is created instead */
        Task newTask = deadline == null
                ? new Task(description, modCode, false, Task.Priority.NONE)
                : new Deadline(description, modCode, deadline, false, Task.Priority.NONE);
        model.addTask(newTask);
        return new CommandResult(String.format(MESSAGE_SUCCESS, newTask));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTaskCommand // instanceof handles nulls
                && this.description.equals(((AddTaskCommand) other).description)
                && this.modCode.equals(((AddTaskCommand) other).modCode)
                && (this.deadline == null || this.deadline.equals(((AddTaskCommand) other).deadline)));
    }
}
