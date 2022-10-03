package bookface.logic.commands;

import static java.util.Objects.requireNonNull;

import bookface.model.Model;

/**
 * Lists all persons in the address book to the user.
 */
public class ListUsersCommand extends Command {

    public static final String COMMAND_WORD = "list";

    public static final String COMMAND_WORD_USER = " users";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + COMMAND_WORD_USER
            + ": List all users.\n"
            + "Example: " + COMMAND_WORD + COMMAND_WORD_USER;

    public static final String MESSAGE_SUCCESS = "Listed all persons";

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
