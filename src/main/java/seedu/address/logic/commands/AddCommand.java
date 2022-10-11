package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.FLAG_ADDRESS_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_EMAIL_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_NAME_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_PHONE_STR;
import static seedu.address.logic.parser.CliSyntax.FLAG_TAG_STR;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the address book. "
            + "Parameters: "
            + "-" + FLAG_NAME_STR + " NAME "
            + "-" + FLAG_PHONE_STR + " PHONE "
            + "-" + FLAG_EMAIL_STR + " EMAIL "
            + "-" + FLAG_ADDRESS_STR + " ADDRESS "
            + "[-" + FLAG_TAG_STR + " TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + "-" + FLAG_NAME_STR + " \"John Doe\" "
            + "-" + FLAG_PHONE_STR + " 98765432 "
            + "-" + FLAG_EMAIL_STR + " johnd@example.com "
            + "-" + FLAG_ADDRESS_STR + " \"311, Clementi Ave 2, #02-25\" "
            + "-" + FLAG_TAG_STR + " friends "
            + "-" + FLAG_TAG_STR + " owesMoney";

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
