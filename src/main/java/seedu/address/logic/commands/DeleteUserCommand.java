package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.user.User;

/**
 * Deletes the user from the address book.
 */
public class DeleteUserCommand extends DeleteCommand {

    public static final String MESSAGE_DELETE_USER_SUCCESS = "Deleted User: %1$s";
    public static final String MESSAGE_NO_USER_TO_DELETE = "No user to be deleted!";

    public DeleteUserCommand() {
        super();
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (!model.hasUser()) {
            throw new CommandException(MESSAGE_NO_USER_TO_DELETE);
        }

        User userToDelete = model.getUser();
        model.deleteUser();
        return new CommandResult(String.format(MESSAGE_DELETE_USER_SUCCESS, userToDelete));
    }

    @Override
    public boolean equals(Object other) {
        return other == this; // short circuit if same object
    }
}
