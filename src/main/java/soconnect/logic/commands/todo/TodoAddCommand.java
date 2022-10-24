package soconnect.logic.commands.todo;

import static java.util.Objects.requireNonNull;
import static soconnect.logic.parser.CliSyntax.*;

import soconnect.logic.commands.CommandResult;
import soconnect.logic.commands.exceptions.CommandException;
import soconnect.logic.commands.tagcommands.TagCommand;
import soconnect.logic.commands.tagcommands.TagCreateCommand;
import soconnect.model.Model;
import soconnect.model.todo.Todo;

/**
 * Adds a {@code Todo} to the {@code TodoList}.
 */
public class TodoAddCommand extends TodoCommand {

    public static final String SUB_COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + " " + SUB_COMMAND_WORD + ": Adds a Todo to SoConnect. \n"
        + "Parameters: "
        + PREFIX_DESCRIPTION + "DESCRIPTION "
        + PREFIX_DATE + "DATE "
        + PREFIX_PRIORITY + "PRIORITY "
        + "[" + PREFIX_TAG + "TAG]...\n"
        + "Example: " + COMMAND_WORD + " " + SUB_COMMAND_WORD + " "
        + PREFIX_DESCRIPTION + "Watch latest math lecture "
        + PREFIX_DATE + "25/03/2022 " + PREFIX_PRIORITY + "low\n"
        + COMMAND_WORD + " " + SUB_COMMAND_WORD + " " + PREFIX_DESCRIPTION
        + "Submit project deliverables for week 10 "
        + PREFIX_DATE + "21/11/2022" + PREFIX_PRIORITY + "high " + PREFIX_TAG + "CS2103T";

    public static final String MESSAGE_SUCCESS = "New Todo added: %1$s";
    public static final String MESSAGE_DUPLICATE_TODO = "This Todo already exists in SoConnect!";
    public static final String MESSAGE_TAG_DOES_NOT_EXIST = "The tag(s) do not exist, please create them first using `"
        + TagCommand.COMMAND_WORD + " " + TagCreateCommand.COMMAND_WORD + "`.";

    private final Todo toAdd;

    /**
     * Constructs a {@code TodoAddCommand} with the {@code todo} to be added to the {@code TodoList}.
     */
    public TodoAddCommand(Todo todo) {
        requireNonNull(todo);
        toAdd = todo;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasTodo(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_TODO);
        }

        if (!model.areTagsAvailable(toAdd)) {
            throw new CommandException(MESSAGE_TAG_DOES_NOT_EXIST);
        }

        model.addTodo(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
            || (other instanceof TodoAddCommand // instanceof handles nulls
            && toAdd.equals(((TodoAddCommand) other).toAdd));
    }
}
