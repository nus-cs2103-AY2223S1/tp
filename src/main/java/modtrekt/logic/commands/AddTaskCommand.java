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
    public static final String MESSAGE_SUCCESS = "New task added: %1$s";

    @Parameter(description = "Description of the task", required = true,
            converter = DescriptionConverter.class)
    private Description description;

    @Parameter(names = "-c", description = "Module code of the task", required = true,
            converter = ModCodeConverter.class)
    private ModCode modCode;

    @Parameter(names = "-d", description = "Due date of the task",
            converter = DeadlineConverter.class)
    private LocalDate deadline;

    @Parameter(names = "-p", description = "Priority level of the task", converter = PriorityConverter.class)
    private Task.Priority priority = Task.Priority.NONE; // default priority level is no priority

    /**
     * Constructor with no parameters required, for use with JCommander.
     */
    public AddTaskCommand() {
    }

    /**
     * Creates an AddTaskCommand to add the specified {@code Task}
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
        if (!(model.hasModuleWithModCode(this.modCode))) {
            throw new CommandException("Module code does not exist");
        }

        /* If deadline is not null, a Deadline object is created instead */
        Task newTask = deadline == null
                ? new Task(description, modCode, false, priority)
                : new Deadline(description, modCode, deadline, false, priority);
        model.addTask(newTask);
        return new CommandResult(String.format(MESSAGE_SUCCESS, newTask));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTaskCommand // instanceof handles nulls
                && this.description.equals(((AddTaskCommand) other).description)
                && this.modCode.equals(((AddTaskCommand) other).modCode)
                && this.priority.equals(((AddTaskCommand) other).priority)
                && (this.deadline == null || this.deadline.equals(((AddTaskCommand) other).deadline)));
    }
}
