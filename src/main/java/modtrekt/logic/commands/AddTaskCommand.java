package modtrekt.logic.commands;

import static java.util.Objects.requireNonNull;

import modtrekt.logic.commands.exceptions.CommandException;
import modtrekt.logic.parser.CliSyntax;
import modtrekt.model.Model;
import modtrekt.model.task.Task;

/**
 * Adds a task to the address book.
 */
public class AddTaskCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public static final String COMMAND_IDENTIFIER = "-t";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the task book. "
            + "Parameters: "
            + CliSyntax.PREFIX_TASK + "DESCRIPTION "
            + CliSyntax.PREFIX_MOD_CODE + "MODULE CODE ";

    public static final String MESSAGE_SUCCESS = "New task added: %1$s";

    private final Task toAdd;

    /**
     * Creates an AddTaskCommand to add the specified {@code Task}
     */
    public AddTaskCommand(Task t) {
        requireNonNull(t);
        toAdd = t;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        if (!(model.hasModuleWithModCode(toAdd.getModule()))) {
            throw new CommandException("Module code does not exist");
        }
        model.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddTaskCommand // instanceof handles nulls
                && toAdd.equals(((AddTaskCommand) other).toAdd));
    }
}
