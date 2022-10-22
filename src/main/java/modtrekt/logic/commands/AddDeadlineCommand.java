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
 * Adds a deadline.
 */
@Parameters(commandDescription = "Adds a deadline to the task book")
public class AddDeadlineCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public static final String COMMAND_IDENTIFIER = "-t";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a deadline. "
            + "Parameters: "
            + CliSyntax.PREFIX_TASK + "Description "
            + CliSyntax.PREFIX_DEADLINE + "YYYY-MM-DD ";
    public static final String MESSAGE_SUCCESS = "New deadline added: %1$s";

    @Parameter(names = "-t", description = "Deadline description",
        converter = DescriptionConverter.class)
    private Description description;

    @Parameter(names = "-d", description = "Due date", required = true,
            converter = DeadlineConverter.class)
    private LocalDate deadline;

    @Parameter(names = "-c", description = "Module code for deadline", required = true,
            converter = ModCodeConverter.class)
    private ModCode modCode;

    /**
     * Constructor with no parameters, for use with JCommander.
     */
    public AddDeadlineCommand() {

    }
    /**
     * Creates an AddDeadlineCommand to add the specified {@code Deadline}
     */
    public AddDeadlineCommand(Description ds, ModCode code, LocalDate deadline) {
        requireNonNull(ds);
        requireNonNull(code);
        requireNonNull(deadline);
        this.description = ds;
        this.modCode = code;
        this.deadline = deadline;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        System.out.println(modCode);
        if (!(model.hasModuleWithModCode(this.modCode))) {
            throw new CommandException("Module code does not exist");
        }

        Deadline newDeadline = new Deadline(description, modCode, this.deadline, false, Task.Priority.NONE);
        model.addTask(newDeadline);
        return new CommandResult(String.format(MESSAGE_SUCCESS, newDeadline));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddDeadlineCommand // instanceof handles nulls
                && this.description.equals(((AddDeadlineCommand) other).description)
                && this.modCode.equals(((AddDeadlineCommand) other).modCode)
                && this.deadline.equals(((AddDeadlineCommand) other).deadline));
    }

}
