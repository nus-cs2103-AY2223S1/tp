package modtrekt.logic.commands;

import static java.util.Objects.requireNonNull;

import modtrekt.logic.commands.exceptions.CommandException;
import modtrekt.logic.parser.CliSyntax;
import modtrekt.model.Model;
import modtrekt.model.task.Task;

/**
 * Adds a deadline.
 */
public class AddDeadlineCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a deadline. "
            + "Parameters: "
            + CliSyntax.PREFIX_TASK + "Description "
            + CliSyntax.PREFIX_DEADLINE + "YYYY-MM-DD ";

    public static final String MESSAGE_SUCCESS = "New deadline added: %1$s";

    private final Task toAdd;
    /**
     * Creates an AddDeadlineCommand to add the specified {@code Deadline}
     */
    public AddDeadlineCommand(Task t) {
        requireNonNull(t);
        toAdd = t;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        model.addTask(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddDeadlineCommand // instanceof handles nulls
                && toAdd.equals(((AddDeadlineCommand) other).toAdd));
    }

}
