package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_CURRENTMOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PLANNEDMOD;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PREVIOUSMOD;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.user.User;

/**
 * Adds a user to the address book.
 */
public class UserCommand extends Command {

    public static final String COMMAND_WORD = "user";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a user to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_CURRENTMOD + "MOD]..."
            + "[" + PREFIX_PREVIOUSMOD + "MOD]..."
            + "[" + PREFIX_PLANNEDMOD + "MOD]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "User Tan "
            + PREFIX_PHONE + "92345678 "
            + PREFIX_EMAIL + "usertan@example.com "
            + PREFIX_ADDRESS + "888, Woodlands Ave 4, #05-06 "
            + PREFIX_CURRENTMOD + "CS2103T"
            + PREFIX_CURRENTMOD + "CS2100"
            + PREFIX_PREVIOUSMOD + "CS1231S"
            + PREFIX_PLANNEDMOD + "CS3230";


    public static final String MESSAGE_SUCCESS = "New user added: %1$s";
    public static final String MESSAGE_USER_EXISTS = "There is already a user in the address book";

    private final User toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public UserCommand(User user) {
        requireNonNull(user);
        toAdd = user;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasUser()) {
            throw new CommandException(MESSAGE_USER_EXISTS);
        } else {
            model.addUser(toAdd);
        }
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof UserCommand // instanceof handles nulls
                && toAdd.equals(((UserCommand) other).toAdd));
    }
}
