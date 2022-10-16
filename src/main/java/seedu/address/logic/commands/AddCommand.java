package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MINECRAFT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MINECRAFT_SERVER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SOCIAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIMEZONE;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book.\n\n"
            + "Parameters: \n"
            + PREFIX_NAME + "NAME "
            + PREFIX_MINECRAFT_NAME + "MINECRAFT_NAME \n"
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_TIMEZONE + "TIMEZONE] "
            + "[" + PREFIX_SOCIAL + "SOCIAL_PLATFORM@HANDLE] "
            + "[" + PREFIX_MINECRAFT_SERVER + "SERVER_IP] "
            + "[" + PREFIX_TAG + "TAG]...\n\n"
            + "Example: \n" + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_MINECRAFT_NAME + "john_doe_12345 "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "10 Downing Street "
            + PREFIX_TIMEZONE + "+08:00 "
            + PREFIX_SOCIAL + "fb@Bozheng "
            + PREFIX_SOCIAL + "ig@catherine_33334 "
            + PREFIX_MINECRAFT_SERVER + "192.168.1.255 "
            + PREFIX_MINECRAFT_SERVER + "177.44.44.94 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddCommand(Person person) {
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
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
