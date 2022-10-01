package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;

/**
 * Finds the ID of student in the list.
 */
public class GetIdCommand extends Command {

    public static final String COMMAND_WORD = "getID";

    public static final String GET_ID_SUCCESS = "ID found %d";

    public static final String GET_ID_FAILURE = "Name does not exist in the file.";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": finds the ID in the list identified by the name.\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME \n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe ";

    private final String targetName;

    public GetIdCommand(String targetName) {
        this.targetName = targetName;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        int id = model.getID(targetName);
        if (id == -1) {
            throw new CommandException(GET_ID_FAILURE);
        } else {
            return new CommandResult(String.format(GET_ID_SUCCESS, id));
        }
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof GetIdCommand // instanceof handles nulls
                && targetName.equalsIgnoreCase(targetName));
    }
}
