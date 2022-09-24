package seedu.address.logic.commands.tasks;

import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNEE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNOR;
import static seedu.address.logic.parser.CliSyntax.PREFIX_DESCRIPTION;

import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.CommandResult;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Name;

/**
 * Adds a task to the task book.
 */
public class TaskAddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a task to the taskbook. "
            + "Parameters: "
            + PREFIX_ASSIGNOR + "/" + PREFIX_ASSIGNEE + "/" + "ASSIGNOR/ASSIGNEE "
            + PREFIX_DESCRIPTION + "DESCRIPTION";

    private final Name name;
    private final String description;

    /**
     * Creates an TaskAddCommand to add a task
     * with the specified {@code Name} and {@code Description}
     *
     * @param name
     * @param description
     */
    public TaskAddCommand(Name name, String description) {
        this.name = name;
        this.description = description;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        // Humphrey, you can use the model to query the AddressBook (to be renamed)
        // for the Person using Name.
        throw new CommandException("Task add command not implemented yet.");
    }
}
