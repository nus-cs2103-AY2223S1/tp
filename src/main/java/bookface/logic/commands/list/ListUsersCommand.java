package bookface.logic.commands.list;

import static java.util.Objects.requireNonNull;

import bookface.logic.commands.CommandResult;
import bookface.model.Model;

/**
 * Lists all users in the user list to the user.
 */
public class ListUsersCommand extends ListCommand {

    public static final String COMMAND_WORD = "users";

    public static final String MESSAGE_USAGE = ListCommand.COMMAND_WORD + " " + COMMAND_WORD
                    + ": List all users.\n" + "Example: " + ListCommand.COMMAND_WORD + " " + COMMAND_WORD;

    public static final String MESSAGE_SUCCESS = "Listed all users";

    @Override
    public CommandResult execute(Model model) {
        requireNonNull(model);
        model.updateFilteredPersonList(Model.PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(MESSAGE_SUCCESS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof ListUsersCommand); // all instances of listuserscommand are equal
    }
}
