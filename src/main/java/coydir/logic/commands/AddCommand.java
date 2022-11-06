package coydir.logic.commands;

import static coydir.logic.parser.CliSyntax.PREFIX_ADDRESS;
import static coydir.logic.parser.CliSyntax.PREFIX_DEPARTMENT;
import static coydir.logic.parser.CliSyntax.PREFIX_EMAIL;
import static coydir.logic.parser.CliSyntax.PREFIX_LEAVE;
import static coydir.logic.parser.CliSyntax.PREFIX_NAME;
import static coydir.logic.parser.CliSyntax.PREFIX_PHONE;
import static coydir.logic.parser.CliSyntax.PREFIX_POSITION;
import static coydir.logic.parser.CliSyntax.PREFIX_TAG;
import static java.util.Objects.requireNonNull;

import coydir.logic.commands.exceptions.CommandException;
import coydir.model.Model;
import coydir.model.person.EmployeeId;
import coydir.model.person.Person;

/**
 * Adds a person to the database.
 */
public class AddCommand extends Command {

    public static final String COMMAND_WORD = "add";

    public static final String MESSAGE_USAGE = COMMAND_WORD + ": Adds a person to the database. \n"
            + "** Employee NAME, POSITION, and DEPARTMENT are required **\n"
            + "Parameters: "
            + PREFIX_NAME + "NAME "
            + PREFIX_POSITION + "POSITION "
            + PREFIX_DEPARTMENT + "DEPARTMENT "
            + PREFIX_PHONE + "PHONE "
            + PREFIX_EMAIL + "EMAIL "
            + PREFIX_ADDRESS + "ADDRESS "
            + PREFIX_LEAVE + "LEAVE "
            + "[" + PREFIX_TAG + "TAG]...\n"
            + "Example: " + COMMAND_WORD + " "
            + PREFIX_NAME + "John Doe "
            + PREFIX_PHONE + "98765432 "
            + PREFIX_EMAIL + "johnd@example.com "
            + PREFIX_POSITION + "Recruiter "
            + PREFIX_DEPARTMENT + "Human Resources "
            + PREFIX_ADDRESS + "311, Clementi Ave 2, #02-25 "
            + PREFIX_LEAVE + "20 "
            + PREFIX_TAG + "friends "
            + PREFIX_TAG + "owesMoney";

    public static final String MESSAGE_SUCCESS = "New person added: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "%1$s already exists in the database";

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
        int currId = EmployeeId.getCount();

        if (model.hasPerson(toAdd)) {
            EmployeeId.setCount(--currId);
            throw new CommandException(String.format(MESSAGE_DUPLICATE_PERSON, toAdd.getName()));
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
