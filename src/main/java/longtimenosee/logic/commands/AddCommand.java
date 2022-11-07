package longtimenosee.logic.commands;

import static java.util.Objects.requireNonNull;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_BIRTHDAY;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_EMAIL;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_INCOME;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_NAME;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_PHONE;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_RISK_APPETITE;
import static longtimenosee.logic.parser.CliSyntax.PREFIX_TAG;

import longtimenosee.logic.commands.exceptions.CommandException;
import longtimenosee.model.Model;
import longtimenosee.model.person.Person;

/**
 * Adds a Client into the LTNS.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "addClient";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a Client to the LTNS. "
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "Clement Tan "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "clementTan@example.com "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney "
            + PREFIX_BIRTHDAY + "2019-05-05 "
            + PREFIX_INCOME + "10000.00 "
            + PREFIX_RISK_APPETITE + "M";

    public static final String MESSAGE_SUCCESS = "New client added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This client already exists in the address book";

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
        return new CommandResult(String.format(MESSAGE_SUCCESS, toAdd), false, true, false);
    }

    @Override
    public boolean equals(Object other) {
        return other == this // short circuit if same object
                || (other instanceof AddCommand // instanceof handles nulls
                && toAdd.equals(((AddCommand) other).toAdd));
    }
}
