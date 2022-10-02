package bookface.logic.commands;

import static bookface.logic.parser.CliSyntax.PREFIX_EMAIL;
import static bookface.logic.parser.CliSyntax.PREFIX_NAME;
import static bookface.logic.parser.CliSyntax.PREFIX_PHONE;
import static bookface.logic.parser.CliSyntax.PREFIX_TAG;
import static java.util.Objects.requireNonNull;

import bookface.logic.commands.exceptions.CommandException;
import bookface.model.Model;
import bookface.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddUserCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String COMMAND_WORD_USER = " user";

    public static final String MESSAGE_USAGE = COMMAND_WORD + COMMAND_WORD_USER
            + ": Adds a user to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD
            + COMMAND_WORD_USER
            + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New user added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This user already exists in the address book";

    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddUserCommand(Person person) {
        requireNonNull(person);
        toAdd = person;
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);

        if (model.hasPerson(toAdd)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.addPerson(toAdd);
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddUserCommand // instanceof handles nulls
                && toAdd.equals(((AddUserCommand) other).toAdd));
    }
}
