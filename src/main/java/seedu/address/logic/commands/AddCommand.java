package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static seedu.address.logic.parser.CliSyntax.PREFIX_COUNTRY;
import static seedu.address.logic.parser.CliSyntax.PREFIX_EMAIL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GAME_TYPE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MINECRAFT_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_MINECRAFT_SERVER;
import static seedu.address.logic.parser.CliSyntax.PREFIX_NAME;
import static seedu.address.logic.parser.CliSyntax.PREFIX_PHONE;
import static seedu.address.logic.parser.CliSyntax.PREFIX_SOCIAL;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TAG;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TIME_INTERVAL;

import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.Person;

/**
 * Adds a person to the address book.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";
    public static final String DESCRIPTION = "Adds a person to the address book.";
    public static final String PARAMETERS =
            PREFIX_NAME + "NAME "
            + PREFIX_MINECRAFT_NAME + "MINECRAFT_NAME \n"
            + "[" + PREFIX_PHONE + "PHONE] "
            + "[" + PREFIX_EMAIL + "EMAIL] "
            + "[" + PREFIX_ADDRESS + "ADDRESS] "
            + "[" + PREFIX_COUNTRY + "COUNTRY] "
            + "[" + PREFIX_SOCIAL + "SOCIAL]* "
            + "[" + PREFIX_MINECRAFT_SERVER + "SERVER]* "
            + "[" + PREFIX_TAG + "TAG]* "
            + "[" + PREFIX_GAME_TYPE + "GAME_TYPE]* "
            + "[" + PREFIX_TIME_INTERVAL + "TIME_INTERVAL]*";
    public static final String EXAMPLES =
            COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_MINECRAFT_NAME + "john_doe_12345 "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_ADDRESS + "10 Downing Street "
            + PREFIX_COUNTRY + "Australia "
            + PREFIX_SOCIAL + "fb@Bozheng "
            + PREFIX_SOCIAL + "ig@catherine_33334 "
            + PREFIX_MINECRAFT_SERVER + "catherine_server@192.168.1.255 "
            + PREFIX_MINECRAFT_SERVER + "hypixel@hypixel.com "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";
    public static final String MESSAGE_USAGE = COMMAND_WORD + ": " + DESCRIPTION + "\n\n"
            + "Parameters: \n"
            + PARAMETERS + "\n\n"
            + "Example: \n" + EXAMPLES;

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book";

    private final Person toAdd;

    public AddCommand() {
        this.toAdd = null;
    }

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
    public String getDescription() {
        return DESCRIPTION;
    }

    @Override
    public String getParameters() {
        return PARAMETERS;
    }

    @Override
    public String getExamples() {
        return EXAMPLES;
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
