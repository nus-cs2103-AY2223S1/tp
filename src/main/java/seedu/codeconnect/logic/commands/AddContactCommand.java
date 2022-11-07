package seedu.codeconnect.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.codeconnect.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.codeconnect.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.codeconnect.logic.parser.CliSyntax.PREFIX_GITHUB;
import static seedu.codeconnect.logic.parser.CliSyntax.PREFIX_MODULE;
import static seedu.codeconnect.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.codeconnect.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.codeconnect.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.codeconnect.logic.parser.CliSyntax.PREFIX_TELEGRAM;

import seedu.codeconnect.logic.commands.exceptions.CommandException;
import seedu.codeconnect.model.Model;
import seedu.codeconnect.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddContactCommand extends Command {

    public static final String COMMAND_WORD = "addc";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book.\n"
            + "Parameters: "
            + PREFIX_NAME + "{name} "
            + PREFIX_PHONE + "{phone_number} "
            + "[" + PREFIX_EMAIL + "{email}] "
            + "[" + PREFIX_ADDRESS + "{address}] "
            + "[" + PREFIX_TAG + "{tag}]... "
            + "[" + PREFIX_MODULE + "{module}...] "
            + "[" + PREFIX_GITHUB + "{github}] "
            + "[" + PREFIX_TELEGRAM + "{telegram}]\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney "
            + PREFIX_MODULE + "CS1101S "
            + PREFIX_MODULE + "ES2660 "
            + PREFIX_GITHUB + "JohnnyDodo "
            + PREFIX_TELEGRAM + "JDo";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Person toAdd;

    /**
     * Creates an AddContactCommand to add the specified {@code Person}
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
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd));
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddContactCommand // instanceof handles nulls
                && toAdd.equals(((AddContactCommand) other).toAdd));
    }
}
