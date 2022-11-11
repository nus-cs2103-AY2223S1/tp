package swift.logic.commands;

import static java.util.Objects.requireNonNull;
import static swift.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static swift.logic.parser.CliSyntax.PREFIX_EMAIL;
import static swift.logic.parser.CliSyntax.PREFIX_NAME;
import static swift.logic.parser.CliSyntax.PREFIX_PHONE;
import static swift.logic.parser.CliSyntax.PREFIX_TAG;

import java.util.ArrayList;
import java.util.List;

import swift.logic.commands.exceptions.CommandException;
import swift.logic.parser.Prefix;
import swift.model.Model;
import swift.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddContactCommand extends Command {

    public static final String COMMAND_WORD = "add_contact";
    public static final ArrayList<Prefix> ARGUMENT_PREFIXES = new ArrayList<>(
            List.of(PREFIX_NAME, PREFIX_PHONE, PREFIX_EMAIL, PREFIX_ADDRESS, PREFIX_TAG));

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a contact to the address book. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New contact added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This contact already exists in the address book";

    private final Person toAdd;

    /**
     * Creates an AddCommand to add the specified {@code Person}
     */
    public AddContactCommand(Person person) {
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
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), CommandType.CONTACTS);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddContactCommand // instanceof handles nulls
                && toAdd.equals(((AddContactCommand) other).toAdd));
    }
}
